/* (C) 2021 */
package app.controller;

import app.repository.model.TitleBriefEntity;
import app.repository.model.TitleEntity;
import app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/content")
public class MovieController {

  @Autowired private MovieService movieService;

  @GetMapping("titles")
  public Flux<TitleBriefEntity> getSubstringMatches(@RequestParam String q) {
    return movieService.getSubstringMatches(q);
  }

  @GetMapping("top-rated")
  public Flux<TitleEntity> getTopRated(
      @RequestParam(required = false, defaultValue = "10") int limit) {
    return movieService.getTopRatedTitles(limit);
  }

  @GetMapping("titles/{titleId}")
  public Mono<TitleEntity> getTitle(@PathVariable("titleId") String titleId) {
    return movieService.getTitle(titleId);
  }
}
