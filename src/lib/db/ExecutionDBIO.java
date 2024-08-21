package lib.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecutionDBIO extends ObjectDBIO {
  private Connection connection = null;
  private Statement stmt = null;
  private PreparedStatement psmt = null;

  //************************************executeRead : getBoardList(), selectBoard()******************************************
  public ResultSet excRead(String query) throws SQLException {
    try {
      connection = open();
      if (connection != null) {
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
      } else if (connection == null) {
        throw new SQLException("DB 커넥션 실패");
      }
    } catch (SQLException e) {
      throw new SQLException("DB에서 전체 게시물을 가져오는 중 문제가 발생했습니다.");
    }
    return null;
  }

  //************************************************exc : insert(), update(), delete() 쿼리문************************************
  public void exc(String query) throws SQLException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = open();
      if (connection != null) {
        psmt = connection.prepareStatement(query);
        psmt.execute();
      } else if (connection == null) {
        throw new SQLException("DB 커넥션 실패");
      }
    } catch (SQLException e) {
      throw new SQLException("DB에서 쿼리를 수행하던 중 문제가 발생했습니다.");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }


  //************************************************************Statement close************************************************
  public void stmtClose() throws SQLException {
    try {
      stmt.close();
    } catch (SQLException e) {
      throw new SQLException("Statement 종료가 실패했습니다.");
    }
  }

  public void psmtClose() throws SQLException {
    try {
      psmt.close();
    } catch (SQLException e) {
      throw new SQLException("PreparedStatement 종료가 실패했습니다.");
    }
  }
}
