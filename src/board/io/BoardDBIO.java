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
    boolean result = super.execute(query);
    return result;
  }

  @Override
  public boolean dropBoard(int ok) {
    if (ok == 1) {
      String query = "TRUNCATE TABLE boards";
      return super.execute(query);
    } else {}
    return false;
  }

  @Override
  public boolean insertBoard(Board board) {
    String title = board.getBtitle();
    String content = board.getBcontent();
    String writer = board.getBwriter();
    Date date = board.getBdate();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    String query =
        "INSERT INTO boards (title, content, writer, date) VALUES ('" + title + "', '" + content
            + "', '" + writer + "', '" + sqlDate + "')";
    return super.execute(query);
  }

  @Override
  public boolean updateBoard(Board board) {
    String title = board.getBtitle();
    String content = board.getBcontent();
    String writer = board.getBwriter();
    Date date = board.getBdate();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    String query = "UPDATE boards SET title = '" + title + "', content = '" + content + "', writer = '" + writer + "', date = '" + sqlDate + "' WHERE no = " + board.getBno();
    return super.execute(query);
  }

  @Override
  public Board selectBoard(int bno) {
    String query = "select * from boards where no =" + bno;
    ResultSet rs = null;
    rs = super.execute(query, rs);
    Board board = null;
    try {
      if (rs.next()) {
        board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
            rs.getDate(5));
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      System.out.println("해당하는 게시물이 존재하지 않습니다.");
    }
    return board;
  }

  @Override
  public ArrayList<Board> getBoardList() {
    String query = "select * from boards";
    ResultSet rs = null;
    rs = super.execute(query, rs);
    ArrayList<Board> boardlist = new ArrayList<>();
    try {
      while (rs.next()) {
        int bno = rs.getInt("no");
        String btitle = rs.getString("title");
        String bwriter = rs.getString("writer");
        String bcontent = rs.getString("content");
        Date bdate = rs.getDate("date");

        Board board = new Board(bno, btitle, bcontent, bwriter, bdate);
        boardlist.add(board);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return boardlist;
  }
}

