package org.example.app.repository.employee;

import org.example.app.config.HibernateUtil;
import org.example.app.dto.employee.EmployeeDtoRequest;
import org.example.app.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;


public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public void save(EmployeeDtoRequest request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            String hql = "INSERT INTO Employee (firstName, position, phone) " +
                    "VALUES (:firstName, :position, :phone)";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("firstName", request.firstName());
            query.setParameter("position", request.position());
            query.setParameter("phone", request.phone());
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<List<Employee>> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;

            transaction = session.beginTransaction();
            List<Employee> list =
                    session.createQuery("FROM Employee", Employee.class).list();

            transaction.commit();

            return Optional.of(list);
        } catch (Exception e) {

            return Optional.empty();
        }
    }



    @Override
    public Optional<Employee> getById(Long id) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            Query<Employee> query =
                    session.createQuery("FROM Employee WHERE id = :id", Employee.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            Employee employee = query.uniqueResult();

            transaction.commit();

            return Optional.ofNullable(employee);
        } catch (Exception e) {

            return Optional.empty();
        }
    }

    @Override
    public void update(Long id, EmployeeDtoRequest request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            String hql = "UPDATE Employee SET firstName = :firstName," +
                    " position = :position, phone = :phone" +
                    " WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("firstName", request.firstName());
            query.setParameter("position", request.position());
            query.setParameter("phone", request.phone());
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            String hql = "DELETE FROM Employee WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public Optional<Employee> getLastEntity() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            Query<Employee> query =
                    session.createQuery("FROM Employee ORDER BY id DESC", Employee.class);
            query.setMaxResults(1);
            Employee employee = query.uniqueResult();

            transaction.commit();
            return Optional.of(employee);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return Optional.empty();
        }
    }
}