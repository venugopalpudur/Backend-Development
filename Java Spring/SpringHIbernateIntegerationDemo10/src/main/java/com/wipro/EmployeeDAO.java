package com.wipro;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dialect.H2Dialect;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

public class EmployeeDAO {
	HibernateTemplate template;

	// LocalSessionFactoryBean sf;

	// BasicDataSource ds;

	// H2Dialect h2;

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public void insertData(Employee e) {
		template.save(e);
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = template.loadAll(Employee.class);
		return list;
	}

	public Employee getEmployeeById(Integer eid) {
		Employee e = template.get(Employee.class, eid);
		return e;
	}

	public Employee updateEmployee(Integer eid, Employee emp) {
		Employee e = getEmployeeById(eid);

		if (e != null) {
			e.setEname(emp.getEname());

			Session s = template.getSessionFactory().openSession();
			Transaction tx = s.beginTransaction();
			s.update(e);
			tx.commit();
			s.close();
		}
		return e;
	}

	public String deleteEmployee(Integer eid) {
		Employee e = getEmployeeById(eid);

		if (e != null) {

			Session s = template.getSessionFactory().openSession();
			Transaction tx = s.beginTransaction();
			s.delete(e);
			tx.commit();
			s.close();
			return "Employee deleted sucessfully";
		}
		return "Employee doesn't exit with give ID";

	}
}