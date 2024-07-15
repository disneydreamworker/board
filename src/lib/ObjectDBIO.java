package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class ObjectDBIO {

  private Connection connection = null;
  private Statement obj = null;
  private PreparedStatement psmt = null;

  private final String db_url = "jdbc:mysql://localhost:3306/employees";
  private final String db_id = "root";
  private final String db_pwd = "0000";

  //Connection
  private boolean open() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(db_url, db_id, db_pwd);
      //System.out.println(connection);
      return true;
    } catch (ClassNotFoundException e) {
      System.err.println(e.getMessage());
      return false;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  protected boolean close() {
    try {
      connection.close();
      return true;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  protected boolean stclose() {
    try {
      obj.close();
      return true;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  protected boolean psmtclose() {
    try {
      psmt.close();
      return true;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  protected ResultSet execute(String query, ResultSet rs) {
    try {
      //connection db
      open();
      obj = connection.createStatement();
      rs = obj.executeQuery(query);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return rs;
  }

  //executeUpdate : insert, update, delete 쿼리문
  protected boolean execute(String query) {
    boolean result = false;
    try {
      open();
      psmt = connection.prepareStatement(query);
      result = psmt.execute();
//      if (!result) {
//        System.out.println("게시글 등록이 실패했습니다.");
//      }
      return result;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return result;
  }
}
