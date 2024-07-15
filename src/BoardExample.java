import board.io.BoardDBIO;
import board.vo.Board;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import lib.BoardMenu;

public class BoardExample extends BoardDBIO {

  //싱글톤 패턴
  private static final BoardExample BOARD_EXAMPLE = new BoardExample();
  private BoardExample(){}
  public static BoardExample getBoardExample() {
    return BOARD_EXAMPLE;
  }

  //BoardMenu
  BoardMenu boardMenu = new BoardMenu();

  private void printBoards(ArrayList<Board> boards) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    for (Board board : boards) {
      System.out.printf("%d\t\t%s\t\t%s\t\t\t%s\n", board.getBno(), board.getBwriter(),
          dateFormat.format(board.getBdate()), board.getBtitle());
    }
  }

  private void createBoard() {
    Board board = boardMenu.createBoard();
    int okay = boardMenu.okayMenu();
    if (okay == 1) {
      super.insertBoard(board);
    }
  }

  //list 메서드
  void list() {
    boolean exit = false;

    while (!exit) {
      //게시글 전체 출력
      //전체 출력 메뉴 문구
      boardMenu.printBoardList();
      //DB에서 Boards 가져오기
      ArrayList<Board> boards = getBoardList();
      //전체 출력 메서드 printBoards 실행
      printBoards(boards);
      super.stclose();
      //메인 메뉴 출력 및 실행
      int menu = boardMenu.mainMenu();

      switch (menu) {
        //Create 실행
        case 1:
          createBoard();
          break;
          //Read 실행
        case 2:
          //bno과 일치하는 ResultSet 한 줄 만 출력
          int bno = boardMenu.readMenu();
          break;
        case 3:
          System.out.println("[게시물 전체 삭제]");
          int ok = boardMenu.okayMenu();
          dropBoard(ok);
          break;
        case 4: exit = true; break;
      }
    }
  }

  private void selectOneBoard(int bno){

  }
}
