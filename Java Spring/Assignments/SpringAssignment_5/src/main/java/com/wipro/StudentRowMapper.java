package com.wipro;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt(1);
		String name=rs.getString(2);
		Address ad=new Address(rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
		return new Student(id, name, ad);
	}

}
