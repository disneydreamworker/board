package lib;

import board.exception.ExceptionList;
import board.service.BoardServiceImpl;
import board.vo.Board;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


public class BoardMenu {

  //*******************************************필드 선언부*******************************************************
  BoardServiceImpl impl = new BoardServiceImpl();
  ExceptionList exception = new ExceptionList();
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

  //*******************************************메서드 선언부*******************************************************
  //printBoardsMenu : 전체 게시글 출력 타이틀
  public void printBoardsMenu() {
    System.out.println("[게시글 목록]");
    System.out.println("---------------------------------------------------");
    System.out.println("no\t\twriter\t\tdate\t\ttitle");
    System.out.println("---------------------------------------------------");
  }


  //printBoards: 전체 게시글 출력 Optional + 람다식 사용
  public void printBoards() {
    ArrayList<Board> boards = impl.getBoardList();
    Optional<ArrayList<Board>> optional = Optional.ofNullable(boards);
    optional.ifPresentOrElse(boardsArray -> boardsArray.forEach(board -> {
      System.out.print(board.getBno() + "\t");
      System.out.print(board.getBwriter() + "\t\t");
      System.out.print(dateFormat.format(board.getBdate()) + "\t\t");
      System.out.print(board.getBtitle() + "\n");

    }), () -> System.out.println("게시글이 존재하지 않습니다."));
  }


  //showMainMenu : 메인 메뉴 출력
  public int showMainMenu() {
    try {
      System.out.println("---------------------------------------------------");
      System.out.println("메인 메뉴 : 1. Create | 2. Read | 3. Clear | 4. Exit");
      System.out.print("메뉴 선택 : ");
      String input = br.readLine();
      System.out.println();
      if (exception.isValidNumber(input)) {
        return Integer.parseInt(input);
      }
    } catch (IOException e) {
      System.out.println("입력 과정에서 문제가 발생했습니다." + e.getMessage());
      return 4; //문제가 생기면 프로그램 종료하기
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage());
    }
    return 4;
  }


  //createBoardMenu : 1.Create 메뉴 -> 사용자 입력값으로 boards DB에 Board 객체 데이터 입력
  public Board createBoardMenu() {
    String title, content, writer;
    Date date;

    try {
      System.out.println("[새 게시물 입력]");
      System.out.print("제목 : ");
      title = br.readLine();
      System.out.print("내용 : ");
      content = br.readLine();
      System.out.print("작성자 : ");
      writer = br.readLine();
      date = new Date();

      return new Board(title, content, writer, date);
    } catch (IOException e) {
      System.out.println("입력 과정에서 문제가 발생했습니다." + e.getMessage());
      return null;
    }
  }


  //createBoard : 1.Create 메뉴
  public void createBoard(Board board) {
    if (okayMenu() == 1) {
      impl.insertBoard(board);
    }
  }


  //getBoardKey : 2. Read 메뉴 -> 사용자 입력 key가 DB에 존재하는지 검사
  public int getBoardKey() {
    try {
      System.out.println("[게시물 읽기]");
      System.out.print("bno : ");
      String input = br.readLine();
      int key;
      //입력값이 숫자가 맞는지 확인
      if (exception.isValidKey(input)) {
        key = Integer.parseInt(input);
        //입력한 숫자가 DB의 no 필드에 존재하는지 확인
        if (impl.selectBoard(key) != null) {
          return key;
        }
      }
    } catch (IOException | NumberFormatException e) {
      System.out.println(e.getMessage());
    }
    return -1;
  }


  //readBoard : 2.Read 메뉴 -> 사용자 입력 primary key로 boards DB의 해당 레코드 데이터 파싱 후 출력
  public Board readBoard(int bno) {
    Board board = impl.selectBoard(bno);
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    System.out.println("##############");
    System.out.println("번호 : " + board.getBno());
    System.out.println("제목 : " + board.getBtitle());
    System.out.println("내용 : " + board.getBcontent());
    System.out.println("작성자 : " + board.getBwriter());
    System.out.println("날짜 : " + board.getBdate());
    System.out.println("##############");
    return board;
  }


  //showSubMenu : 2.Read 메뉴의 보조메뉴
  public void showSubMenu(Board board) {
    try {
      System.out.println("---------------------------------------------------");
      System.out.println("보조 메뉴 : 1. Update | 2. Delete | 3. List ");
      System.out.print("메뉴 선택 : ");
      String input = br.readLine();
      if (exception.isValidSubNumber(input)) {
        int sub = Integer.parseInt(input);
        switch (sub) {
          case 1:
            Board boardUpdate = updateBoard(board);
            if (okayMenu() == 1) {
              impl.updateBoard(boardUpdate);
            }
            break;
          case 2:
            System.out.println("[게시물 삭제]");
            if (okayMenu() == 1) {
              impl.deleteBoard(board.getBno());
            }
            break;
          case 3:
            break;
        }
      }
    } catch (IOException | NumberFormatException e) {
      System.out.println(e.getMessage());
    }
  }


  //dropBoard : 3.Clear -> 게시물 전체 삭제
  public void dropBoard() {
    System.out.println("[게시물 전체 삭제]");
    if (okayMenu() == 1) {
      impl.dropBoard();
    }
  }


  //updateBoard : 2.Read 메뉴의 보조메뉴인 수정 메뉴
  public Board updateBoard(Board board) {
    try {
      System.out.println("[수정 내용 입력]");
      System.out.print("제목 : ");
      board.setBtitle(br.readLine());
      System.out.print("내용 : ");
      board.setBcontent(br.readLine());
      System.out.print("작성자 : ");
      board.setBwriter(br.readLine());
      board.setBdate(new Date());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return board;
  }


  //okayMenu : 보조 메뉴 ok cancel
  public int okayMenu() {
    try {
      System.out.println("---------------------------------------------------");
      System.out.println("보조 메뉴 : 1. OK | 2. Cancel");
      System.out.print("메뉴 선택 : ");
      String input = br.readLine();
      if (exception.isValidNumber(input) && Integer.parseInt(input) == 1) {
        return 1;
      } else if (exception.isValidNumber(input) || Integer.parseInt(input) == 2) {
        return 2;
      }
    } catch (IOException | NumberFormatException e) {
      System.err.println(e.getMessage());
    }
    return -1;
  }
}
