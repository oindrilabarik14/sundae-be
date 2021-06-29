/* (C) 2021 */
package app.service;

import app.repository.MovieRepo;
import app.repository.model.TitleBriefEntity;
import app.repository.model.TitleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MovieServiceImpl implements MovieService {

  @Autowired private MovieRepo repo;

  @Override
  public Mono<TitleEntity> getTitle(String id) {
    return repo.findTitleById(id);
  }

  @Override
  public Flux<TitleEntity> getTopRatedTitles(int limit) {
    Flux<TitleEntity> topRatedMovies = repo.findTopRatedMovies();
    Flux<TitleEntity> topRatedTvShows = repo.findTopRatedTvShows();

    return topRatedMovies.concatWith(topRatedTvShows).take(limit);
  }

  @Override
  public Flux<TitleBriefEntity> getSubstringMatches(String sub) {
    return repo.findSubstringMatches(sub);
  }
}
