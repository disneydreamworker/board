package lib.properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Properties {
  public static final String DRIVER, URL, ID, PWD;

  static {
    java.util.Properties properties= new java.util.Properties();

    try(FileReader fileReader = new FileReader("src/lib/properties/connectionFactory.properties")) {
      properties.load(fileReader);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    DRIVER = properties.getProperty("driver");
    URL = properties.getProperty("url");
    ID = properties.getProperty("id");
    PWD = properties.getProperty("pwd");
  }


}
