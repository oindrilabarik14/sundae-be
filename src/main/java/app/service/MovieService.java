/* (C) 2021 */
package app.service;

import app.repository.model.TitleBriefEntity;
import app.repository.model.TitleEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

  Mono<TitleEntity> getTitle(String id);

  Flux<TitleEntity> getTopRatedTitles(int limit);

  Flux<TitleBriefEntity> getSubstringMatches(String sub);
}
