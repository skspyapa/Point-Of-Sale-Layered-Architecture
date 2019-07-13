package dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO {
    boolean save(T entity) throws SQLException;

    boolean update(T entity) throws SQLException;

    boolean delete(ID entityId) throws SQLException;

    T find(ID entityId) throws SQLException;

    List<T> findAll() throws SQLException;

}
