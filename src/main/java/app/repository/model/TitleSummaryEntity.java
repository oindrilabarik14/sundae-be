/* (C) 2021 */
package app.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitleSummaryEntity {
  private String author;
  private String text;
}
