package business.custom;

import business.SuperBO;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO<CustomerDTO,String> {
     List<CustomerDTO> getCustomerId() throws SQLException;

     String getMaxCustId() throws SQLException;
}
