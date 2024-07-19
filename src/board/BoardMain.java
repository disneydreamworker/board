package board;


import board.service.BoardManager;
import board.vo.Board;
import lib.BoardMenu;

public class BoardMain {

  //*******************************************메인 메서드 선언부*******************************************************
  public static void main(String[] args) {
    BoardMain boardMain = new BoardMain();
    boardMain.list();
  }

  //*******************************************list 메서드 선언부*******************************************************
  void list() {
    BoardMenu menu = new BoardMenu();
    boolean loop = false;
    try {
      while (!loop) {
        System.out.println();
        menu.printBoardsMenu();
        menu.printBoards();
        int num = menu.showMainMenu();
        switch (num) {
          case 1 -> {
            Board createBoard = menu.createBoardMenu();
            menu.createBoard(createBoard);
          }
          case 2 -> {
            int key = menu.getBoardKey();
            if (key != -1) {
              Board readBoard = menu.readBoard(key);
              menu.showSubMenu(readBoard);
            }
          }
          case 3 -> menu.dropBoard();
          case 4 -> loop = true;
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      System.out.println("프로그램을 종료합니다.");
    }
  }
}
