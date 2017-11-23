package co.ortizol.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    void insertar(T entidad) throws SQLException;
    
    void eliminar(Integer id) throws SQLException;
    
    List<T> listar() throws SQLException;
    
    T buscar(Integer id) throws SQLException;
    
    void actualizar(T entidad) throws SQLException;
}
