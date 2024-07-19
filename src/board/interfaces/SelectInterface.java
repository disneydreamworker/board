package board.interfaces;

import board.vo.Board;

public interface SelectInterface extends OutputInterface{

  Board selectBoard(int bno);
}
