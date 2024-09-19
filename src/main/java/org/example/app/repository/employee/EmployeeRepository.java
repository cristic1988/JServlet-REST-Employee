package org.example.app.repository.employee;


import org.example.app.dto.employee.EmployeeDtoRequest;
import org.example.app.entity.Employee;
import org.example.app.repository.AppRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends AppRepository<Employee, EmployeeDtoRequest> {

    void save(EmployeeDtoRequest request);

    Optional<List<Employee>> getAll();


    Optional<Employee> getById(Long id);

    void update(Long id, EmployeeDtoRequest request);

    boolean deleteById(Long id);


    Optional<Employee> getLastEntity();

}
