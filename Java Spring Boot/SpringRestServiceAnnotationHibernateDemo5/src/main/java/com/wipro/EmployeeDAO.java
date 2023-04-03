package com.wipro;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

public class EmployeeDAO {
	
	HibernateTemplate template;
	
	
	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public String addEmployee(Employee e) {
		template.save(e);
		return "Data Added Successfully";
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
	

	/*static List<Employee> emplist = new ArrayList<Employee>();

	public Employee addEmployee(Employee e) {
		emplist.add(e);
		return e;
	}

	public List<Employee> getAllEmployees() {
		return emplist;
	}

	public Employee getEmployeeById(int eid) {

		Employee e = null;
		for (Employee emp : emplist) {
			if (emp.getEid() == eid) {
				e = emp;
				break;
			}
		}
		return e;
	}

	public Employee updateEmployee(int eid, Employee e) {

		Employee emp = getEmployeeById(eid);

		if (emp != null) {
			emp.setEname(e.getEname());
		}
		return emp;
	}

	public String deleteEmployee(int eid) {

		Employee emp = getEmployeeById(eid);
		if (emp != null) {
			emplist.remove(emp);
			return "Deleted Successfully";
		} else {
			return "Object not found with given ID";
		}
	}*/
}
