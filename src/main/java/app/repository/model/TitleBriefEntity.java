/* (C) 2021 */
package app.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

// Source: OMDB
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitleBriefEntity {
  @JsonProperty("imdbID")
  private String id;

  @JsonProperty("Title")
  private String title;
}
