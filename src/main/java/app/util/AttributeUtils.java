/* (C) 2021 */
package app.util;

public class AttributeUtils {

  private static final String ID_PREFIX = "title";

  public static String extractId(String longId) {
    String processedId = longId.replace("/", "");
    return processedId.replace(ID_PREFIX, "");
  }
}
