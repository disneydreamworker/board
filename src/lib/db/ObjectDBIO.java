package lib.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lib.properties.Properties;

//Exception 내부에서 바로 처리
public abstract class ObjectDBIO {
  private Connection connection = null;

  //************************************************DB Connection******************************************************
  public Connection open() {
    try {
      Class.forName(Properties.DRIVER);
      connection = DriverManager.getConnection(Properties.URL, Properties.ID, Properties.PWD);
      //System.out.println("DB 커넥션 성공 " + connection);
      return connection;
    } catch (ClassNotFoundException e) {
      System.out.println("드라이버 클래스를 찾지 못했습니다." + e.getMessage());
    } catch (SQLException e) {
      System.out.println("DB 커넥션 실패 " + e.getMessage());
    }
    return connection;
  }

  public void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      System.out.println("DB 커넥션 종료 실패 " + e.getMessage());
    }
  }

}
