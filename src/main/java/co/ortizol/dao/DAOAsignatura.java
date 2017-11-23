package co.ortizol.dao;

import co.ortizol.modelos.Asignatura;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOAsignatura implements IDAO<Asignatura>{

    private PreparedStatement insertar;
    private PreparedStatement buscar;
    private PreparedStatement eliminar;
    private PreparedStatement listar;
    private PreparedStatement actualizar;
    
    private static DAOAsignatura instance;
    
    private DAOAsignatura(){}
    
    public static DAOAsignatura getInstance() {
        if(instance == null){
            instance = new DAOAsignatura();
        }
        
        return instance;
    }
    
    @Override
    public void insertar(Asignatura entidad) throws SQLException {
        String query = "INSERT INTO ASIGNATURA (asi_nombre, asi_creditos, asi_descripcion) VALUES (?, ?, ?)";
        
        insertar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        insertar.setString(1, entidad.getNombre());
        insertar.setInt(2, entidad.getCreditos());
        insertar.setString(3, entidad.getDescripcion());
        
        insertar.executeUpdate();
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        String query = "DELETE FROM ASIGNATURA WHERE id_asignatura = ?";
        
        eliminar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        eliminar.setInt(1, id);
        
        eliminar.executeUpdate();
    }

    @Override
    public List<Asignatura> listar() throws SQLException {
        String query = "SELECT * FROM ASIGNATURA";
        
        listar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        ResultSet set = listar.executeQuery();
        
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        while(set.next()){
            asignaturas.add(cargar(set));
        }
        
        return asignaturas;
    }

    @Override
    public Asignatura buscar(Integer id) throws SQLException {
        String query = "SELECT * FROM ASIGNATURA WHERE id_asignatura = ?";
        
        buscar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        buscar.setInt(1, id);
        
        ResultSet set = buscar.executeQuery();
        
        return set != null && set.next() ? cargar(set) : null;
    }

    @Override
    public void actualizar(Asignatura entidad) throws SQLException {
        String query = "UPDATE ASIGNATURA SET asi_nombre = ?, asi_creditos = ?, asi_descripcion = ? WHERE id_asignatura = ?";
        
        actualizar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        actualizar.setString(1, entidad.getNombre());
        actualizar.setInt(2, entidad.getCreditos());
        actualizar.setString(3, entidad.getDescripcion());
        actualizar.setInt(4, entidad.getId());
        
        actualizar.executeUpdate();
    }
    
    private Asignatura cargar(ResultSet set) throws SQLException{
        Asignatura asignatura = new Asignatura();
        
        asignatura.setId(set.getInt("id_asignatura"));
        asignatura.setNombre(set.getString("asi_nombre"));
        asignatura.setCreditos(set.getInt("asi_creditos"));
        asignatura.setDescripcion(set.getString("asi_descripcion"));
        
        return asignatura;
    }
}
