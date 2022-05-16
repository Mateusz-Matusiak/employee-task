package com.example.employeetask.employee;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class EmployeeRepository extends JdbcDaoSupport {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(DataSource dataSource) {
        setDataSource(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Employee insert(Employee employee){
        String sql = "INSERT INTO\n" +
                "    employee(first_name, last_name, department_id, job_title)\n" +
                "VALUES\n" +
                "    (?, ?, ?, ?);";
        jdbcTemplate.update(sql, employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle());
        return employee;
    }

    public List<Employee> loadAllEmployees(){
        String sql = "SELECT * FROM employee";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        List<Employee> result = new ArrayList<Employee>();

        for(Map<String, Object> row:rows){
            Employee temporary = new Employee();
            temporary.setId((Long) row.get("employee_id"));
            temporary.setFirstName((String) row.get("first_name"));
            temporary.setLastName((String) row.get("last_name"));
            temporary.setJobTitle((String) row.get("job_title"));
            temporary.setDepartmentId((Long) row.get("department_id"));
            result.add(temporary);
        }
        return result;
    }

    public Employee loadEmployeeById(Long id){

        String sql = "SELECT * FROM employee WHERE employee_id = ?";

        try{

            Employee result = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Employee employee = new Employee();
                employee.setId(rs.getLong("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setJobTitle(rs.getString("job_title"));
                employee.setDepartmentId(rs.getLong("department_id"));
                return employee;
            }, id);

            return result;

        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public void update(Long id, Employee employee){

        String sql = "update employee set first_name = ?, last_name = ?, job_title = ?, department_id = ? where employee_id = ?";

        jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getDepartmentId(), id);
    }


}
