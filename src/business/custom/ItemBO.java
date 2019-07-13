package business.custom;

import business.SuperBO;
import dto.ItemDTO;

import java.sql.SQLException;

public interface ItemBO extends SuperBO<ItemDTO,String> {

    String getMaxItemCode() throws SQLException;
}
