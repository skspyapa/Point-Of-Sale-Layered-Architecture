package business;

import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface SuperBO<T,ID> {
     List<T> getAll() throws SQLException;

     boolean save(T dto) throws SQLException;

     boolean remove(ID  dtoId) throws SQLException;

     boolean update(T dtoId) throws SQLException;

     T get(ID dtoId) throws SQLException;
}
