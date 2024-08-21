package StudentManageSystem.src.student;

import java.util.ArrayList;

public interface SearchStudent extends StudentOutput {
  public ArrayList<Student> searchStudent(String sno);
}

