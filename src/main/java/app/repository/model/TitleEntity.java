/* (C) 2021 */
package app.repository.model;

import app.model.TitleType;
import app.util.AttributeUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;

// Source: RapidAPI
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitleEntity {
  @Getter(AccessLevel.NONE)
  private String id;

  private String title;
  private TitleImage image;
  private TitleType titleType;

  public String getId() {
    return AttributeUtils.extractId(id);
  }
}
