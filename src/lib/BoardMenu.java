package lib;

import board.vo.Board;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class BoardMenu {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public void printBoardList(){
    System.out.println("[게시글 목록]");
    System.out.println("---------------------------------------------------");
    System.out.println("no\t\twriter\t\tdate\t\t\ttitle");
    System.out.println("---------------------------------------------------");
  }

  public int mainMenu(){
    System.out.println("---------------------------------------------------");
    System.out.println("메인 메뉴 : 1. Create | 2. Read | 3. Clear | 4. Exit");
    System.out.print("메뉴 선택 : ");
    int menu = 0;
    try {
      String input = br.readLine();
      menu = Integer.parseInt(input.trim());
      if (menu < 1 || menu > 4){
        throw new IOException("1, 2, 3, 4 숫자만 입력해주세요.");
      }
    } catch (IOException | NumberFormatException e) {
      System.err.println(e.getMessage());
    }
    System.out.println();
    return menu;
  }

  public Board createBoard(){
    String btitle = null;
    String bcontent = null;
    String bwriter = null;
    Date bdate = null;

    try {
      System.out.println("[새 게시물 입력]");
      System.out.print("제목 : ");
      btitle = br.readLine();
      System.out.println();
      System.out.print("내용 : ");
      bcontent = br.readLine();
      System.out.println();
      System.out.print("작성자 : ");
      bwriter = br.readLine();
      bdate = new Date();
    } catch (IOException | NumberFormatException e) {
      System.err.println(e.getMessage());
    }
    return new Board(btitle, bcontent, bwriter, bdate);
  }

  public int subMenu(){
    System.out.println("---------------------------------------------------");
    System.out.println("보조 메뉴 : 1. Update | 2. Delete | 3. List ");
    System.out.print("메뉴 선택 : ");
    int menu = 0;
    try {
      String input = br.readLine();
      menu = Integer.parseInt(input.trim());

      if (menu < 1 || menu > 3) {
        throw new IOException("1, 2, 3 숫자만 입력해주세요.");
      }
    } catch (IOException | NumberFormatException e) {
      System.err.println(e.getMessage());
    }
    System.out.println();
    return menu;
  }

  public int okayMenu(){
    System.out.println("---------------------------------------------------");
    System.out.println("보조 메뉴 : 1. OK | 2. Cancel");
    System.out.print("메뉴 선택 : ");
    int menu = 0;
    try {
      String input = br.readLine();
      menu = Integer.parseInt(input.trim());
      if (menu != 1 && menu != 2) {
        throw new IOException("1, 2 숫자만 입력해주세요.");
      }
    } catch (IOException | NumberFormatException e) {
      System.err.println(e.getMessage());
    }
    System.out.println();
    return menu;
  }

  public int readMenu() {
    System.out.println("[게시물 읽기]");
    System.out.print("bno : ");
    int menu = 0;
    try {
      String input = br.readLine();
      menu = Integer.parseInt(input.trim());
    } catch (IOException | NumberFormatException e) {
      System.err.println(e.getMessage());
    }
    return menu;
  }

  public Board updateBoard(Board board){
    System.out.println("[수정 내용 입력]");
    System.out.print("제목 : ");
    try {
      board.setBtitle(br.readLine());
      System.out.println("내용 : ");
      board.setBcontent(br.readLine());
      System.out.println("작성자 : ");
      board.setBwriter(br.readLine());
      board.setBdate(new Date());
      System.out.println("---------------------------------------------------");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return board;
  }
}
