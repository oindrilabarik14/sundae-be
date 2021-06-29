/* (C) 2021 */
package app;

import app.repository.MovieRepo;
import app.repository.impl.MovieRestRepoImpl;
import app.service.MovieService;
import app.service.MovieServiceImpl;
import app.util.ObjectMapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

  @Bean
  public MovieRepo getMovieRepository() {
    return new MovieRestRepoImpl();
  }

  @Bean
  public MovieService getMovieService() {
    return new MovieServiceImpl();
  }

  @Bean
  public ObjectMapperUtil getObjectMapper() {
    return new ObjectMapperUtil();
  }
}
