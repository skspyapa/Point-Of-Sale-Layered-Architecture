package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrdersDAO;
import entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOimpl implements OrdersDAO {
    public boolean save(Orders orders) throws SQLException {
        return CrudUtil.execute("INSERT INTO orders VALUES(?,?,?)",orders.getId(),orders.getDate(),orders.getCustomerId());
    }

    public boolean update(Orders orders) throws SQLException {
        return CrudUtil.execute("UPDATE orders set date=?,customerId=? WHERE id=?",orders.getId(),orders.getDate(),orders.getCustomerId());
            }

    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE orders WHERE id=?",id);
    }

    public Orders find(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT orders WHERE id=?",id);
        return rst.next() ? new Orders(rst.getString(1), rst.getDate(2).toLocalDate(), rst.getString(3)) : null;


    }

    public List<Orders> findAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM orders");;
        List<Orders> allOrders = new ArrayList<>();
        while (rst.next()) {
            allOrders.add(new Orders(rst.getString(1), rst.getDate(2).toLocalDate(), rst.getString(3)));
        }
        return allOrders;
    }

    @Override
    public String findMaxId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT max(id) FROM orders");
        return rst.next()?rst.getString(1):"0";
    }
}

