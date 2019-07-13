package business.custom;

import business.SuperBO;
import dto.OrdersDTO;

import java.sql.SQLException;

public interface OrdersBO extends SuperBO<OrdersDTO,String> {

    String getdMaxId() throws SQLException;

    boolean placeOrder(OrdersDTO dto);
}
