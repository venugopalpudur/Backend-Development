package com.wipro;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class StudentDAO {
	
	@Autowired
	JdbcTemplate template;
	//DriverManagerDataSource ds;// not needed

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public void createTable() {
		String sql="create table student(id int, name varchar(15), hn int, city varchar(15), state varchar(15), country varchar(15))";
		template.execute(sql);
		System.out.println("Table created successfully");
		
	}
	
	public void preload(List<Student> list){
		Iterator itr = list.iterator();
		while(itr.hasNext()) {
			Student stu = (Student) itr.next();
			String sql="insert into student values("+stu.getStudentId()+",'"+ stu.getStudentName()+"',"+stu.getAddress().getHno()+",'"+stu.getAddress().getCity()+"','"+stu.getAddress().getState()+"','"+stu.getAddress().getCountry()+"')";
			template.update(sql);
		}
		System.out.println("Preload Data Added successfully");
	}
	
	public void addStudent(Student e) {
		int id=e.getStudentId();
		String name=e.getStudentName();
		Address address=e.getAddress();
		String sql="insert into student values("+id+",'"+ name+"',"+address.getHno()+",'"+address.getCity()+"','"+address.getState()+"','"+address.getCountry()+"')";
		template.update(sql);
		System.out.println("Data inserted successfully");
	}
	
	public List<Student> getAllDetails(){
		
		String sql="select * from student";
		return template.query(sql, new StudentRowMapper());
	}
	
	public Student getDetails(Integer eid){
		
		String sql="select * from student where id=?";
		return template.queryForObject(sql, new Object[] {eid}, new StudentRowMapper());
	}
	
	// update & delete
	
	public void setStudentsById(int id, String name, int hn, String city, String state, String country) {
		String sql="update student SET name='"+name+"',hn='"+hn+"', city='"+city+"', state='"+state+"', country='"+country+"'where id="+id;
		template.update(sql);
		System.out.println("Data Updated Successfully");
	}
	
	public void deleteStudentsById(int eid) {
		String sql="delete from student where id="+eid;
		template.update(sql);
		System.out.println("Data Deleted Successfully");
	}
	
	public void deleteStudents() {
		String sql="delete from student";
		template.update(sql);
		System.out.println("All Data Deleted Successfully");
	}
}
