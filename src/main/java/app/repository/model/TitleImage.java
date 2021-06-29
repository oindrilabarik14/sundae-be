/* (C) 2021 */
package app.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.net.URL;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitleImage {
  private int height;
  private String id;
  private URL url;
  private int width;
}
