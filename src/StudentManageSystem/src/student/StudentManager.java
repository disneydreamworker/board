package StudentManageSystem.src.student;

import java.util.ArrayList;

//싱글톤 패턴
public class StudentManager extends StudentDBIO{
  //본인 객체 한 개 생성
  private static final StudentManager instance = new StudentManager();

  //생성자 private으로 선언
  private StudentManager(){}

  //외부에서 StudentManager 객체에 접근할 수 있도록 getInstance 설정
  public static StudentManager getInstance(){
    return instance;
  }

  //학생정보 입력기능 학생 객체 생성 후 학생 정보 저장
  public boolean insertStudent(Student student){
    return false;
  }

  //제너릭<>을 이용해서 저장소에 Student 객체만 저장하도록 타입을 제한
//  public ArrayList<Student> getStudentList(){
//    return new ArrayList<>();
//  }

  public ArrayList<Student> searchStudent(String sno){
    //정보 조회 기능
    return new ArrayList<>();
  }

  public ArrayList<Student> getSortedStudent(){
    //학생 석차 처리 기능
    return new ArrayList<>();
  }

}
