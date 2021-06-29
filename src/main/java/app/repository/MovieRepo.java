/* (C) 2021 */
package app.repository;

import app.repository.model.TitleBriefEntity;
import app.repository.model.TitleEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieRepo {

  Mono<TitleEntity> findTitleById(String id);

  Flux<TitleEntity> findTopRatedMovies();

  Flux<TitleEntity> findTopRatedTvShows();

  Flux<TitleBriefEntity> findSubstringMatches(String substring);
}
