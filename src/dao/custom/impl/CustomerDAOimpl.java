package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOimpl implements CustomerDAO {
    public boolean save(Customer customer) throws SQLException {
        return CrudUtil.execute("INSERT INTO customer VALUES(?,?,?,?)",customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());
    }

    public boolean update(Customer customer) throws SQLException {
        return CrudUtil.execute("UPDATE customer set name=?,address=?,salary=? WHERE id=?",customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());

    }
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM customer WHERE id=?",id);
    }

    public Customer find(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * customer WHERE id=?",id);
        return rst.next() ? new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4)) : null;
    }

    public List<Customer> findAll() throws SQLException {
        ResultSet rst =  CrudUtil.execute("SELECT * FROM customer");
        List<Customer> allCustomer = new ArrayList<>();
        while (rst.next()) {
            allCustomer.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4)));
        }
        return allCustomer;
    }
    public String findMaxCustId() throws SQLException {
        ResultSet rst=CrudUtil.execute("SELECT MAX(id) FROM customer");
    return rst.next()?rst.getString(1):"0";
    }
}
