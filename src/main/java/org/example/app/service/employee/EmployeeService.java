package org.example.app.service.employee;


import org.example.app.dto.employee.EmployeeDtoRequest;
import org.example.app.entity.Employee;
import org.example.app.service.AppService;

import java.util.List;

public interface EmployeeService extends AppService<Employee, EmployeeDtoRequest> {

    Employee create(EmployeeDtoRequest request);

    List<Employee> getAll();


    Employee getById(Long id);

    Employee update(Long id, EmployeeDtoRequest request);

    boolean deleteById(Long id);
}
