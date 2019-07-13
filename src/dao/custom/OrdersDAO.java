package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.CustomEntity;
import entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends CrudDAO<Orders,String> {

    String findMaxId() throws SQLException;
}
