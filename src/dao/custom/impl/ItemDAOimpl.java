package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOimpl implements ItemDAO {
    public boolean save(Item item) throws SQLException {
        return CrudUtil.execute("INSERT INTO item VALUES(?,?,?,?)",item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    public boolean update(Item item) throws SQLException {
        return CrudUtil.execute("UPDATE item set description=?,unitPrice=?,qtyOnHand=? WHERE code=?",item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),item.getCode());
    }

    public boolean delete(String code) throws SQLException {
        return CrudUtil.execute("DELETE item WHERE code=?",code);
            }

    public Item find(String code) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * from item WHERE code=?", code);
        return rst.next() ? new Item(rst.getString(1), rst.getString(2), rst.getDouble(3),rst.getInt(4)) : null;

    }

    public List<Item> findAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item");
        List<Item> allItem = new ArrayList<>();
        while (rst.next()) {
            allItem.add(new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4)));
        }
        return allItem;
    }
    public String findMaxItemCode() throws SQLException {
        ResultSet rst=CrudUtil.execute("SELECT MAX(code) FROM item");
        return rst.next()?rst.getString(1):"0";
    }
}

