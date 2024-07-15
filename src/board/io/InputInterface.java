package board.io;

import board.vo.Board;

public interface InputInterface extends OutputInterface {
  public boolean insertBoard(Board board);
}
