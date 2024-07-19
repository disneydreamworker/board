package board.service;

import board.vo.Board;
import java.util.ArrayList;

public interface BoardService {

  //Create
  void insertBoard(Board board);

  //Read
  ArrayList<Board> getBoardList();

  Board selectBoard(int bno);

  //Update
  void updateBoard(Board board);

  //Delete
  void deleteBoard(int bno);

  void dropBoard();
}
