package leetcode;

public class A551_Student_Attendance_Record_I {
	public boolean checkRecord(String s) {
        return !s.matches(".*A.*A.*") && !s.matches(".*LLL.*");
    }
}
