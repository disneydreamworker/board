package StudentManageSystem.src;

import StudentManageSystem.src.student.Student;
import StudentManageSystem.src.student.StudentManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class IntegrationSystem_study {

  //	 콘솔 입력 버퍼 생성
  //Reader : 키보드로 부터 입력되는 데이터의 흐름을 '문자열'형태로 읽어서 처리할게
  //BufferedReader : Buffer 메모리 공간에 문자열을 미리 저장해서 내가 원할 때 빠르게 입력받은 문자열을 처리할 수 있도록 만들어주는 필터
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    IntegrationSystem_study Instance = new IntegrationSystem_study();
    String buffer = null;

    while (true) {
      System.out.println("====== [ 메뉴를 선택 하세요 ] ======");
      System.out.println("1. 학생 관리 시스템");
      System.out.println("2. 시스템 종료");
      System.out.println("--------------------------------");

      int nSel = System.in.read() - 48;
      System.in.skip(System.in.available());

      switch (nSel) {

        case 1:
          while (true) {
            System.out.println("======= [ 학생 관리 시스템 ] =======");
            System.out.println("1. 학생 추가");
            System.out.println("2. 학생 전체 보기");
            System.out.println("3. 학생 검색");
            System.out.println("4. 학생 석차순으로 보기");
            System.out.println("5. 이전으로");
            System.out.println("--------------------------------");
            nSel = System.in.read() - 48;
            System.in.skip(System.in.available());
            switch (nSel) {
              case 1:
                Instance.insertStudent();
                continue;
              case 2:
                Instance.getStudentList();
                continue;
              case 3:
                Instance.searchStudent();
                continue;
              case 4:
                Instance.sortedStudent();
                continue;
              case 5:
                break;
              default:
                continue;
            }
            break;
          }
          continue;
        case 3:
          break;
        default:
          continue;
      }
      break;
    }
  }

  //학생 정보 입력 기능 = 학생 객체 StudentVo를 생성 후 학생 정보 저장
  public void insertStudent() throws IOException {
    System.out.println("학생 데이터를 입력하세요! : 이름, 국어, 영어, 수학, 과학");
    System.out.println("입력 종료 : exit");
    System.out.println("=================================================");

    String buffer = null;
    String name = null;
    Student student = null;
    StudentManager studentManager = null;

    try {
      while (!(buffer = br.readLine()).equals("exit")) {
        try {
          //토큰 구분자를 등록해서 학생 객체 생성
          //ssg,100,90,90,90
          StringTokenizer st = new StringTokenizer(buffer, ",");
          System.out.println("++++StringTokenizer Start+++++");

          while (st.hasMoreTokens()) {
//          if ((name = st.nextToken()) != null) {
            name = st.nextToken();

            int[] record = new int[4];
            record[0] = Integer.parseInt(st.nextToken());
            record[1] = Integer.parseInt(st.nextToken());
            record[2] = Integer.parseInt(st.nextToken());
            record[3] = Integer.parseInt(st.nextToken());

            //학생 객체 VO에 각 데이터를 옮긴다
            student = new Student(name, record[0], record[1], record[2], record[3]);
            boolean result = studentManager.insertStudent(student);

//          }
          }
        } catch (NoSuchElementException e) {
          System.out.println("입력 형식에 알맞지 않은 입력입니다. 다시 입력해주세요");
          System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
          System.out.println("성적 입력은 숫자로만 가능합니ㅣ다. 다시 입력해주세요.");
        }
      }
      System.out.println("학생 정보 입력이 완료되었습니다.");
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  //학생 석차 처리 기능
  public void sortedStudent() {

  }

  //2번 메뉴 학생 전체 보기 기능
  public void getStudentList() {

  }

  //3번 메뉴 학번으로 검색하여 학생 정보 조회 기능
  public void searchStudent() {

  }


}