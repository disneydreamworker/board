package board.io;

import board.vo.Board;
import java.util.ArrayList;

public interface SelectInterface extends OutputInterface{
  public ArrayList<Board> selectBoard(int bno);
}
