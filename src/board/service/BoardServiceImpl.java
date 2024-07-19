package board.service;

import board.vo.Board;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import lib.ExecutionDBIO;

public class BoardServiceImpl implements BoardService {

  ExecutionDBIO exc = new ExecutionDBIO();

  //******************************************Create************************************************
  @Override
  public void insertBoard(Board board) {
    try {
      if (board != null) {
        String title = board.getBtitle();
        String content = board.getBcontent();
        String writer = board.getBwriter();
        Date date = board.getBdate();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String query =
            "INSERT INTO boards (title, content, writer, date) VALUES ('"
                + title + "', '"
                + content + "', '"
                + writer + "', '"
                + sqlDate + "')";
        exc.exc(query);
        System.out.println("게시글 생성이 성공했습니다.");
      } else {
        throw new NoSuchElementException("createBoard 에러 발생");
      }
    } catch (SQLException e) {
      System.out.println("게시글 생성이 실패했습니다. 다시 시도해주세요." + e.getMessage());
    }
  }


  //******************************************Read************************************************
  @Override
  public ArrayList<Board> getBoardList() {
    String query = "select * from boards";
    ResultSet rs = null;
    ArrayList<Board> boardlist = new ArrayList<>();
    try {
      rs = exc.excRead(query);
      while (rs.next()) {
        int bno = rs.getInt("no");
        String btitle = rs.getString("title");
        String bwriter = rs.getString("writer");
        String bcontent = rs.getString("content");
        Date bdate = rs.getDate("date");
        boardlist.add(new Board(bno, btitle, bcontent, bwriter, bdate));
      }
    } catch (SQLException e) {
      System.out.println("게시글 전체 조회가 실패했습니다. 다시 시도해주세요.");
      System.out.println(e.getMessage());
    }
    return boardlist;
  }


  @Override
  public Board selectBoard(int bno) {
    Board board = null;
    String query = "select * from boards where no =" + bno;
    ResultSet rs;
    try {
      rs = exc.excRead(query);
      if (rs != null) {
        while(rs.next()) {
          board = new Board(rs.getInt(1),
              rs.getString(2),
              rs.getString(3),
              rs.getString(4),
              rs.getDate(5));
        }
        return board;
      }
    } catch (SQLException e) {
      System.out.println("게시글 조회가 실패했습니다. 다시 시도해주세요.");
      System.out.println(e.getMessage());
    }
    return board;
  }


  //******************************************Update************************************************
  @Override
  public void updateBoard(Board board) {
    String title = board.getBtitle();
    String content = board.getBcontent();
    String writer = board.getBwriter();
    Date date = board.getBdate();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    String query = "UPDATE boards SET "
        + "title = '"+ title
        + "', content = '" + content
        + "', writer = '" + writer
        + "', date = '" + sqlDate
        + "' WHERE no = " + board.getBno();
    try {
      exc.exc(query);
      System.out.println("게시글 수정이 성공했습니다.");
    } catch (SQLException e) {
      System.out.println("게시글 수정이 실패했습니다. 다시 시도해주세요.");
      System.out.println(e.getMessage());
    }
  }


  //************************************************Delete******************************************************
  @Override
  public void deleteBoard(int bno) {
    String query = "delete from boards where no =" + bno;
    try {
      exc.exc(query);
      System.out.println("게시글 삭제가 성공했습니다.");
    } catch (SQLException e) {
      System.out.println("게시글 삭제가 실패했습니다. 다시 시도해주세요.");
      System.out.println(e.getMessage());
    }
  }


  @Override
  public void dropBoard() {
    try {
      String query = "TRUNCATE TABLE boards";
      exc.exc(query);
      System.out.println("게시글 전체 삭제가 성공했습니다.");
    } catch (SQLException e) {
      System.out.println("게시글 전체 삭제가 실패했습니다. 다시 시도해주세요.");
      System.out.println(e.getMessage());
    }
  }
}
