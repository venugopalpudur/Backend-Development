package com.wipro;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDAO {
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
		String sql="create table empaug22(eid int, ename varchar(15))";
		template.execute(sql);
		System.out.println("Table created successfully");
		
	}
	
	public void insertData(Employee e) {
		int eid=e.getEid();
		String ename=e.getEname();
		String sql="insert into empaug22 values("+eid+",'"+ ename+"')";
		template.update(sql);
		System.out.println("Data inserted successfully");
	}
	
	public List<Employee> getEmployees(){
		
		String sql="select * from empaug22";
		return template.query(sql, new EmployeRowMapper());
	}
	
	public Employee getEmployeeById(Integer eid){
		
		String sql="select * from empaug22 where eid=?";
		return template.queryForObject(sql, new Object[] {eid}, new EmployeRowMapper());
	}
	
	// update & delete
}
