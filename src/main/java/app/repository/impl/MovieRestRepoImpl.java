/* (C) 2021 */
package app.repository.impl;

import app.repository.MovieRepo;
import app.repository.model.TitleBriefEntity;
import app.repository.model.TitleEntity;
import app.repository.model.TitleOverviewEntity;
import app.util.AttributeUtils;
import app.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MovieRestRepoImpl implements MovieRepo {
  private WebClient rapidApiWebClient;
  private WebClient omdbApiWebClient;

  @Autowired private ObjectMapperUtil jsonMapper;

  private final Logger LOGGER = LoggerFactory.getLogger(MovieRestRepoImpl.class);

  public MovieRestRepoImpl() {
    rapidApiWebClient =
        WebClient.builder()
            .baseUrl("https://imdb8.p.rapidapi.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("x-rapidapi-key", "95a115e155msh8280114e82979b2p1cc483jsn01ca25889f32")
            .build();
    Map<String, String> defaultRequestParams = new HashMap<>();
    defaultRequestParams.put("apikey", "9a07a46e");
    omdbApiWebClient =
        WebClient.builder()
            .baseUrl("https://omdbapi.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(defaultRequestParams)
            .build();
  }

  @Override
  public Mono<TitleEntity> findTitleById(String id) {
    String sanitizedId = AttributeUtils.extractId(id);
    return rapidApiWebClient
        .get()
        .uri("/title/get-overview-details?tconst=" + sanitizedId)
        .retrieve()
        .bodyToMono(TitleOverviewEntity.class)
        .onErrorResume(e -> Mono.empty())
        .filter(Objects::nonNull)
        .map(TitleOverviewEntity::getTitle);
  }

  @Override
  public Flux<TitleEntity> findTopRatedMovies() {
    LOGGER.info("Finding top rated movies...");
    return rapidApiWebClient
        .get()
        .uri("/title/get-coming-soon-movies")
        .retrieve()
        .bodyToMono(String[].class)
        .flatMapIterable(Arrays::asList)
        .flatMap(this::findTitleById)
        .filter(Objects::nonNull);
  }

  @Override
  public Flux<TitleEntity> findTopRatedTvShows() {
    LOGGER.info("Finding top rated tv shows...");
    return rapidApiWebClient
        .get()
        .uri("/title/get-coming-soon-tv-shows")
        .retrieve()
        .bodyToMono(String[].class)
        .flatMapIterable(Arrays::asList)
        .flatMap(this::findTitleById)
        .filter(Objects::nonNull);
  }

  @Override
  public Flux<TitleBriefEntity> findSubstringMatches(String substring) {
    LOGGER.info("Matching title for substring [{}]", substring);
    return omdbApiWebClient
        .get()
        .uri("?apikey=9a07a46e&s=" + substring)
        .retrieve()
        .bodyToMono(Map.class)
        .flatMapIterable(searchResult -> (ArrayList) searchResult.get("Search"))
        .flatMap(
            result -> {
              try {
                return Mono.just(
                    (TitleBriefEntity) jsonMapper.castObjectToPojo(result, TitleBriefEntity.class));
              } catch (JsonProcessingException e) {
                return Mono.error(e);
              }
            });
  }
}
