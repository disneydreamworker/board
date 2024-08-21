package StudentManageSystem.src.student;

import StudentManageSystem.src.lib.ObjectDBIO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class StudentDBIO extends ObjectDBIO implements StudentIO {

  //db에서 읽어오기
  public ArrayList<Student> getStudentList() {
    ArrayList<Student> stulist = new ArrayList<Student>();
    String query = "select * from student;";
    ResultSet rs = null;
    try {
      rs = super.execute(query, rs);
      while (rs.next()) {
        String sno = String.valueOf(rs.getInt(1));
        String name = rs.getString(2);
        int kor = rs.getInt(3);
        int eng = rs.getInt(4);
        int math = rs.getInt(5);
        int sci = rs.getInt(6);
        String grade = rs.getString(7);
        int total = rs.getInt(8);
        double average = rs.getDouble(9);

        Student student = new Student(name, kor, eng, math, sci, grade, total, average);
        stulist.add(student);
      }
      rs.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return stulist;
  }

  //학생 점수 db에 저장하기
  public boolean insertStudent(Student student) {
    String query = "insert into student values (?,?,?,?,?,?,?,?)"; //sno는 auto increment라 제외하고 입력
    String name = student.getName();
    int kor = student.getRecord()[0];
    int eng = student.getRecord()[1];
    int math = student.getRecord()[2];
    int sci = student.getRecord()[3];
    String grade = student.getGrade();
    int total = student.getTotal();
    double average = student.getAverage();
    query = "insert into student values ('" + name + "', '" +
        kor + "', '" +
        eng + "', '" +
        math + "', '" +
        sci + "', '" +
        grade + "', '" +
        total + "', '" +
        average + "')";

        /*String query =
        "insert into student values(" + student.getName() + "," + student.getRecord()[0] + ","
            + student.getRecord()[1] + "," + student.getRecord()[2] + "," + student.getRecord()[3]
            + "," + student.getGrade() + "," + student.getTotal() + "," + student.getAverage()
            + ");";*/
    return super.execute(query);
  }

  public ArrayList<Student> searchStudentList(String sno) {
    String query = "select * from student where sno = " + sno + ";";
    ArrayList<Student> stulist = new ArrayList<Student>();
    ResultSet rs = null;
    try {
      rs = super.execute(query, rs);
      while (rs.next()) {
        sno = String.valueOf(rs.getInt(1));
        String name = rs.getString(2);
        int kor = rs.getInt(3);
        int eng = rs.getInt(4);
        int math = rs.getInt(5);
        int sci = rs.getInt(6);
        String grade = rs.getString(7);
        int total = rs.getInt(8);
        double average = rs.getDouble(9);
        Student student = new Student(name, kor, eng, math, sci, grade, total, average);
        stulist.add(student);
      }
      rs.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    return stulist;
  }
}
