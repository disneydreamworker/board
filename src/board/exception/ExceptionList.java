package board.exception;


//ENUM 으로 만들기
//테스트 케이스 추가하기
public class ExceptionList {
  public boolean isValidNumber(String input)  {
    try {
      int num = Integer.parseInt(input);
      if (num >= 1 && num <= 4) {
        return true;
      } else {
        System.out.println("1~4까지의 숫자를 입력하세요.");
        return false;
      }
    } catch (NumberFormatException e) {
      throw new NumberFormatException("1~4까지의 숫자를 입력하세요." + e.getMessage());
    }
  }

  public boolean isValidSubNumber(String input)  {
    try {
      int num = Integer.parseInt(input);
      if (num >= 1 && num <= 3) {
        return true;
      }
      else {
        System.out.println("1~3까지의 숫자를 입력하세요.");
        return false;
      }
    } catch (NumberFormatException e) {
      throw new NumberFormatException("1~3숫자를 입력하세요." + e.getMessage());
    }
  }

  public boolean isValidKey(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      throw new NumberFormatException("숫자를 입력해주세요." + e.getMessage());
    }
  }

}