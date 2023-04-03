package com.wipro;

import java.util.List;

public class Student {

	private String studentId;
	private String studentName;
	private List<Test> studentTest;
	
	public Student() {
	}
	
	public Student(String studentId, String studentName, List<Test> studentTest) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentTest = studentTest;
	}

	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public List<Test> getStudentTest() {
		return studentTest;
	}
	public void setStudentTest(List<Test> studentTest) {
		this.studentTest = studentTest;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentTest=" + studentTest
				+ "]";
	}
	
}
