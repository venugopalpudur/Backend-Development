package com.wipro;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class EmployeeDAO {
    JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public String createTable() {
        String sql = "create table emp(eid int,ename varchar(25))";
        template.execute(sql);
        return "Table Created!";
    }

    public String insertData(Employee e) {
        int eid = e.getEid();
        String ename = e.getEname();
        String sql = "insert into emp(eid,ename) values(" + eid + ",'" + ename + "')";
        template.update(sql);
        return "Data Inserted! "+e.toString();
    }

    public List<Employee> getEmployees() {
        String sql = "select * from emp";
        // template.execute(sql);
        return template.query(sql, new EmployeeRowMapper());
    }


    public Employee getEmployeesById(int eid) {
        Employee emp = null;
        // template.execute(sql);
         String sql = "select * from emp where eid=?"; 
        return template.queryForObject(sql, new Object[] {eid},new EmployeeRowMapper());
    }

    public String updateEmployee(int eid, Employee e) {
        Employee emp = getEmployeesById(eid);
        if(emp!=null)
        {
            String sql = "update emp set eid="+e.getEid()+",ename='"+e.getEname()+"' where eid="+eid;
            template.update(sql);
            return "Updated! ";
        }else {
            return "No Employee Found with ID: "+eid; 
        }
    }

    public String deleteEmployee(int eid) {
        Employee emp = getEmployeesById(eid);
        if(emp!=null)
        {
            String sql = "delete from emp where eid="+eid;
            template.update(sql);
            return "Employee Deleted!";
        }else {
            return "No Employee Found with ID: "+eid; 
        }
    }

}