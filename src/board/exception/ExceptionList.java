package board.exception;


public class ExceptionList {
  //이 부분 함수형 인터페이스로 만들면 좋을 듯 ..!
  public boolean isValidNumber(String input)  {
    try {
      int num = Integer.parseInt(input);
      return num >= 1 && num <= 4;
    } catch (NumberFormatException e) {
      throw new NumberFormatException("숫자를 입력해주세요.\" + e.getMessage()");
    }
  }

  public boolean isValidSubNumber(String input)  {
    try {
      int num = Integer.parseInt(input);
      return num >= 1 && num <= 3;
    } catch (NumberFormatException e) {
      throw new NumberFormatException("1~3숫자를 입력해주세요.\" + e.getMessage()");
    }
  }

  public boolean isValidKey(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      throw new NumberFormatException("숫자를 입력해주세요.\" + e.getMessage()");
    }
  }

}