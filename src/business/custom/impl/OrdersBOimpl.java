package business.custom.impl;

import business.custom.OrdersBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.ItemDAO;
import dao.custom.ItemDetailDAO;
import dao.custom.OrdersDAO;
import dbpos.DBConnection;
import dto.ItemDetailDTO;
import dto.OrdersDTO;
import entity.Item;
import entity.ItemDetail;
import entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersBOimpl implements OrdersBO {
    private OrdersDAO ordersDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);
    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    private ItemDetailDAO itemDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM_DETAIL);

    public List<OrdersDTO> getAll() throws SQLException {

        List<Orders> allOrders = ordersDAO.findAll();
        List<OrdersDTO> dtos = new ArrayList<>();
        for (Orders orders : allOrders) {
            OrdersDTO ordersDTO = new OrdersDTO(orders.getId(), orders.getDate(), orders.getCustomerId());
            dtos.add(ordersDTO);
        }
        return dtos;
    }

    public boolean save(OrdersDTO dto) throws SQLException {
        Orders orders = new Orders(dto.getId(), dto.getDate(), dto.getCustomerId());
        return ordersDAO.save(orders);
    }

    public boolean remove(String id) throws SQLException {
        return ordersDAO.delete(id);
    }

    public boolean update(OrdersDTO dto) throws SQLException {
        Orders orders = new Orders(dto.getId(), dto.getDate(), dto.getCustomerId());
        return ordersDAO.update(orders);
    }

    @Override
    public OrdersDTO get(String id) throws SQLException {
        Orders orders = ordersDAO.find(id);
        return new OrdersDTO(orders.getId(), orders.getDate(), orders.getCustomerId());
    }

    public String getdMaxId() throws SQLException {
        return ordersDAO.findMaxId();
    }

    public boolean placeOrder(OrdersDTO dto) {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            result = ordersDAO.save(new Orders(dto.getId(), dto.getDate(), dto.getCustomerId()));
            if (!result) {
                connection.rollback();
                return false;
            }
            for (ItemDetailDTO itemDetailDTO : dto.getItemDetailDTOS()) {
                result = itemDetailDAO.save(new ItemDetail(itemDetailDTO.getOrderId(), itemDetailDTO.getItemCode(), itemDetailDTO.getQty(), itemDetailDTO.getUnitPrice()));
                if (!result) {
                    connection.rollback();
                    return false;
                }
                Item item = itemDAO.find(itemDetailDTO.getItemCode());
                int qty = item.getQtyOnHand() - itemDetailDTO.getQty();
                item.setQtyOnHand(qty);
                result = itemDAO.update(item);
                if (!result) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
            return false;
        } catch (Throwable t) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            t.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
