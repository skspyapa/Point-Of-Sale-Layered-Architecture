package business.custom.impl;

import business.custom.CustomerBO;
import dao.CrudUtil;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.impl.CustomerDAOimpl;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomerBOimpl implements CustomerBO {
    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

    public List<CustomerDTO> getAll() throws SQLException {
        return customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary())).collect(Collectors.toList());
}
            public boolean save(CustomerDTO dto) throws SQLException {
                Customer customer = new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary());
                 return customerDAO.save(customer);
            }

            public boolean remove(String  id) throws SQLException {
                return customerDAO.delete(id);
            }

            public boolean update(CustomerDTO dto) throws SQLException {
                Customer customer = new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary());
                return customerDAO.update(customer);
            }
            public CustomerDTO get(String id) throws SQLException {
                Customer customer = customerDAO.find(id);
                return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());
            }
            public List<CustomerDTO> getCustomerId() throws SQLException {
                return customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId())).collect(Collectors.toList());
            }
            public String getMaxCustId() throws SQLException {
                return customerDAO.findMaxCustId();
            }
}
