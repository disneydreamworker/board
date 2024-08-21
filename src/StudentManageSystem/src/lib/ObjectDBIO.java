package StudentManageSystem.src.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ObjectDBIO {

  private Connection connection = null;
  private String db_url = "jdbc:mysql://localhost:3306/employees";
  private String db_id = "root";
  private String db_pwd = "0000";

  public void setDb_url(String db_url) {
    this.db_url = db_url;
  }

  public void setDb_id(String db_id) {
    this.db_id = db_id;
  }

  public void setDb_pwd(String db_pwd) {
    this.db_pwd = db_pwd;
  }

  //DB Connection
  private boolean open() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver"); //해당하는 클래스가 있는지
      connection = DriverManager.getConnection(db_url, db_id, db_pwd);
      System.out.println(connection);
      return true;
    } catch (ClassNotFoundException e) {
      //Driver가 없으면
      System.err.println(e.getMessage());
      return false;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  //DB Disconnection
  protected boolean close() {
    try {
      connection.close();
      return true;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  //executeQuery : search 실행문
  protected ResultSet execute(String query, ResultSet rs) {
    try {
      //connection db
      open();
      Statement obj = connection.createStatement();
      rs = obj.executeQuery(query);
      close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return rs;
  }

  //executeUpdate : insert, update, delete 쿼리문
  protected boolean execute(String query) {
    boolean result1 = false;
    try {
      open();
      Statement obj = connection.createStatement();
      int result = obj.executeUpdate(query);
      result1 = true;
      close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return result1;
    }
    return result1;
  }
}
