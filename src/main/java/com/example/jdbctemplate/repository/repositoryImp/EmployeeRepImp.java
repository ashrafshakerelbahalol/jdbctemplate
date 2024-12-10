package com.example.jdbctemplate.repository.repositoryImp;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.jdbctemplate.Mapper.EmployeeMapper;
import com.example.jdbctemplate.model.Employee;
import com.example.jdbctemplate.repository.EmployeeReps;

@Repository
@Qualifier("EmployeeRepImp")
public class EmployeeRepImp implements EmployeeReps {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employee", Integer.class);
    }

    @Override
    public Employee getById(long id) {

        return jdbcTemplate.queryForObject("SELECT id ,name, salary FROM employee where id=?", new EmployeeMapper(),
                id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT id ,name, salary FROM employee ", new EmployeeMapper());
    }

    @Override
    public int insertEmployee(Employee employee) {

        return jdbcTemplate.update("insert into employee values (?,?,?);", new Object[] {
                employee.getId(), employee.getName(), employee.getSalary()
        });
    }

    @Override
    public int updateEmployee(Employee employee) {
        return jdbcTemplate.update("update employee set salary=? where id=? ;", new Object[] {
                employee.getSalary(), employee.getId()
        });
    }

    @Override
    public int deleteEmployee(Employee employee) {
        return jdbcTemplate.update("delete from employee where id=? ;", new Object[] {
          employee.getId()
    });
      
    }

}
