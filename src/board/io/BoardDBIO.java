package board.io;

import board.vo.Board;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import lib.ObjectDBIO;

public class BoardDBIO extends ObjectDBIO implements BoardIO {

  @Override
  public boolean deleteBoard(int bno) {
    String query = "delete from boards where no =" + bno;
    boolean result = false;
    try {
      result = super.execute(query);
    } catch (SQLException e) {
      throw new RuntimeException(e + "게시물 삭제가 실패했습니다.");
    }
    return result;
  }

  @Override
  public boolean dropBoard(int ok) {
    boolean result = false;
    if (ok == 1) {
      String query = "TRUNCATE TABLE boards";
      try {
        result = super.execute(query);
      } catch (SQLException e) {
        throw new RuntimeException("게시물 전체 삭제가 실패했습니다.");
      }
    }
    return result;
  }

  @Override
  public boolean insertBoard(Board board) {
    boolean result = false;
    String title = board.getBtitle();
    String content = board.getBcontent();
    String writer = board.getBwriter();
    Date date = board.getBdate();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    String query =
        "INSERT INTO boards (title, content, writer, date) VALUES ('" + title + "', '" + content
            + "', '" + writer + "', '" + sqlDate + "')";
    try {
      result = super.execute(query);
    } catch (SQLException e) {
      System.out.println("게시물 생성이 실패했습니다.");
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public boolean updateBoard(Board board) {
    boolean result = false;
    String title = board.getBtitle();
    String content = board.getBcontent();
    String writer = board.getBwriter();
    Date date = board.getBdate();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    String query = "UPDATE boards SET title = '" + title + "', content = '" + content + "', writer = '" + writer + "', date = '" + sqlDate + "' WHERE no = " + board.getBno();
    try {
      result = super.execute(query);
    } catch (SQLException e) {
      throw new RuntimeException("게시물 수정이 실패했습니다.");
    }
    return result;
  }

  @Override
  public Board selectBoard(int bno) {
    String query = "select * from boards where no =" + bno;
    ResultSet rs = null;
    Board board = null;
    try {
      rs = super.execute(query, rs);
      if (rs.next()) {
        board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
            rs.getDate(5));
      }
    } catch (SQLException | NullPointerException e) {
      System.out.println("해당하는 게시물이 존재하지 않습니다.");
      System.err.println(e.getMessage());
    }
    return board;
  }

  @Override
  public ArrayList<Board> getBoardList() {
    String query = "select * from boards";
    ResultSet rs = null;
    ArrayList<Board> boardlist = new ArrayList<>();
    try {
      rs = super.execute(query, rs);
      while (rs.next()) {
        int bno = rs.getInt("no");
        String btitle = rs.getString("title");
        String bwriter = rs.getString("writer");
        String bcontent = rs.getString("content");
        Date bdate = rs.getDate("date");

        Board board = new Board(bno, btitle, bcontent, bwriter, bdate);
        boardlist.add(board);
      }
    } catch (SQLException | NullPointerException e) {
      System.err.println(e.getMessage());
    }
    return boardlist;
  }
}

