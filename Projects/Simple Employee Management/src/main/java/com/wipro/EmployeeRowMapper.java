package com.wipro;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		int eid = rs.getInt(1);
		String ename=rs.getString(2);
		return new Employee(eid, ename);
	}

}
