package StudentManageSystem.src.student;

public class Student {

//  private String sno;
  private String name;
  private int[] record = new int[4]; //각 과목 점수 저장 레코드
  private int total;
  private double average;
  private String grade;
  private final int rank = 1;
  enum GRADE {A, B, C, D, F}

  public Student() {
  }

  //db에 입력할 때 사용할 생성자
  public Student(String name, int kor, int eng, int math, int sci) {
    //인스턴스 멤버 초기화
    this.name = name;
    this.record = new int[]{kor, eng, math, sci};
    this.makeScores(); //총합 구하기, insert 할 때
  }

  //DB에서 데이터 가져올 때 사용할 생성자
  public Student(String name, int kor, int eng, int math, int sci, String grade, int total, double average) {
    this.name = name;
    this.record = new int[]{kor, eng, math, sci};
    this.grade = grade;
    this.total = total;
    this.average = average;
  }

  private void makeScores() {
    for (int score : record) {
      this.total += score;
    }
    this.average = (double) this.total / this.record.length;
    this.makeGrade();
  }

  private void makeGrade() {
    int base = (int) average / 10;
    switch (base) {
      case 10, 9 -> grade = String.valueOf(GRADE.A);
      case 8 -> grade = String.valueOf(GRADE.B);
      case 7 -> grade = String.valueOf(GRADE.C);
      case 6 -> grade = String.valueOf(GRADE.D);
      default -> grade = String.valueOf(GRADE.F);
    }
  }

  //Getters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int[] getRecord() {
    return record;
  }

  public void setRecord(int[] record) {
    this.record = record;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public double getAverage() {
    return average;
  }

  public void setAverage(double average) {
    this.average = average;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public int getRank() {
    return rank;
  }
}
