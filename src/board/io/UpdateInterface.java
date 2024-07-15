package board.io;

import board.vo.Board;

public interface UpdateInterface extends SelectInterface{
  public boolean updateBoard(Board board);
}
