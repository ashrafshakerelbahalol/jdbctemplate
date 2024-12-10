package com.example.jdbctemplate.repository.repositoryImp;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.jdbctemplate.Mapper.EmployeeMapper;
import com.example.jdbctemplate.model.Employee;
import com.example.jdbctemplate.repository.EmployeeReps;

@Repository
@Qualifier("EmployeeRepParameteriziedJdbcTemplateImp")
public class EmployeeRepParameteriziedJdbcTemplateImp   implements EmployeeReps {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employee", Integer.class);
    }

    @Override
    public Employee getById(long id) {

        return namedParameterJdbcTemplate.queryForObject("SELECT id ,name, salary FROM employee where id=:id",
        new MapSqlParameterSource("id",id) ,new EmployeeMapper()
                );
    }
    public Employee getByNameAndSalary(String name, double salary) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name",name );
        mapSqlParameterSource.addValue("salary", salary);
        return namedParameterJdbcTemplate.queryForObject("SELECT id ,name, salary FROM employee where name=:name and salary=:salary;",
        mapSqlParameterSource ,new EmployeeMapper()
                );
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT id ,name, salary FROM employee ", new EmployeeMapper());
    }

    @Override
    public int insertEmployee(Employee employee) {

        return namedParameterJdbcTemplate.update("insert into employee values (:id,:name,:salary);",
         new BeanPropertySqlParameterSource(employee) );
    }

    @Override
    public int updateEmployee(Employee employee) {
        return namedParameterJdbcTemplate.update("update employee set salary=:salary where id=:id ;", 
        new BeanPropertySqlParameterSource(employee));
    }

    @Override
    public int deleteEmployee(Employee employee) {
        return namedParameterJdbcTemplate.update("delete from employee where id=:id ;",  
        new BeanPropertySqlParameterSource(employee));
      
    }

}
