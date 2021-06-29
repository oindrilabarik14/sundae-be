/* (C) 2021 */
package app.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitleOverviewEntity {
  private TitleEntity title;
  private String[] genres;
  private TitleSummaryEntity plotSummary;
}
