package helpers;


/**
 * Загрузчик данных из файла для параметризации тестов
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.tngtech.java.junit.dataprovider.DataProvider;

import org.junit.runners.model.FrameworkMethod;

import tests.CalcTest;

public class DataProviders {

  @DataProvider
  public static Object[][] dataSourceLoader(FrameworkMethod testMethod) throws IOException {
    DataSource ds = testMethod.getAnnotation(DataSource.class);
    if (ds == null) {
      throw new Error("Test method has no @DataSource annotation: " + testMethod.getName());
    }
    switch (ds.type()) {

      case RESOURCE:
        return loadDataFromResource(ds.value());

      default:
        throw new Error("Data source type is not supported: " + ds.type());
    }
  }

  /**
   * Чтение данных из файла
   * @param value имя файла
   * @return  массив прочитанных значений из файла csv
   * @throws IOException
   */
  private static Object[][] loadDataFromResource(String value) throws IOException {
    return loadDataFromInputStream(DataProviders.class.getResourceAsStream(value));
  }
  /**
   * Парсер csv
   * @param input - поток, содержащий данные, для loadDataFromFile
   * @return массив прочитанных значений из файла csv
   * @throws IOException
   */
  private static Object[][] loadDataFromInputStream(InputStream input) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(input));

    List<Object[]> userData = new ArrayList<Object[]>();
    String line = in.readLine();
    while (line != null) {
      userData.add(line.split(";"));
      line = in.readLine();
    }

    in.close();

    return userData.toArray(new Object[][]{});
  }

}
