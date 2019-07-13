package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDetailDAO;
import entity.ItemDetail;
import entity.ItemDetailPK;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDetailDAOimpl implements ItemDetailDAO {
    public boolean save(ItemDetail itemDetail) throws SQLException {
        return CrudUtil.execute("INSERT INTO itemDetail VALUES(?,?,?,?)",itemDetail.getItemdetailPK().getOrderId(),itemDetail.getItemdetailPK().getItemCode(),
                itemDetail.getQty(),itemDetail.getUnitPrice());

    }

    public boolean update(ItemDetail itemDetail) throws SQLException {
        return CrudUtil.execute("UPDATE itemDetail set qty=?,unitPrice=? WHERE orderId=? AND itemCode=?",itemDetail.getItemdetailPK().getOrderId(),
                itemDetail.getItemdetailPK().getItemCode(),itemDetail.getQty(),itemDetail.getUnitPrice());
    }

    public boolean delete(ItemDetailPK itemDetailPK) throws SQLException {
        return CrudUtil.execute("DELETE itemDetail WHERE orderId=? AND itemCode=?",itemDetailPK.getOrderId(),itemDetailPK.getItemCode());
    }

    public ItemDetail find(ItemDetailPK itemDetailPK) throws SQLException {
        return CrudUtil.execute("SELECT itemDetail WHERE orderId=? AND itemCode=?",itemDetailPK.getOrderId(),itemDetailPK.getItemCode());
    }

    public List<ItemDetail> findAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM itemDetail");
        List<ItemDetail> allItemDetail = new ArrayList<>();
        while (rst.next()) {
            allItemDetail.add(new ItemDetail(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDouble(4)));
        }
        return allItemDetail;
    }
}


