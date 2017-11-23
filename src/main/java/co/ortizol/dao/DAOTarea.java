package co.ortizol.dao;

import co.ortizol.modelos.Tarea;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author johno
 */
public class DAOTarea implements IDAO<Tarea> {
    
    private PreparedStatement insertar;
    private PreparedStatement buscar;
    private PreparedStatement eliminar;
    private PreparedStatement listar;
    private PreparedStatement actualizar;
    
    private static DAOTarea instance;
    
    private DAOTarea() {}
    
    public static DAOTarea getInstance() {
        if(instance == null){
            instance = new DAOTarea();
        }
        
        return instance;
    }

    @Override
    public void insertar(Tarea entidad) throws SQLException {
        String query = "INSERT INTO TAREA (tar_nombre, tar_nota, fk_id_asignatura, fk_id_usuario_alumno, fk_id_usuario_profesor) VALUES (?, ?, ?, ?, ?)";
        
        insertar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        insertar.setString(1, entidad.getNombre());
        insertar.setDouble(2, entidad.getNota());
        insertar.setInt(3, entidad.getIdAsignatura());
        insertar.setInt(4, entidad.getIdUsuarioEstudiante());
        insertar.setInt(5, entidad.getIdUsuarioProfesor());
        
        insertar.executeUpdate();
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        String query = "DELETE FROM TAREA WHERE id_tarea = ?";
        
        eliminar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        eliminar.setInt(1, id);
        
        eliminar.executeUpdate();
    }

    @Override
    public List<Tarea> listar() throws SQLException {
        String query = "SELECT * FROM TAREA";
        
        listar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        ResultSet set = listar.executeQuery();
        
        ArrayList<Tarea> tareas = new ArrayList<>();
        while(set.next()){
            tareas.add(cargar(set));
        }
        
        return tareas;
    }
    
    public List<Tarea> listar(int idUsuario) throws SQLException {
        String query = "SELECT * FROM TAREA WHERE fk_id_usuario = ?";
        
        listar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        listar.setInt(1, idUsuario);
        
        ResultSet set = listar.executeQuery();
        
        ArrayList<Tarea> tareas = new ArrayList<>();
        while(set.next()){
            tareas.add(cargar(set));
        }
        
        return tareas;
    }

    @Override
    public Tarea buscar(Integer id) throws SQLException {
        String query = "SELECT * FROM TAREA WHERE id_usuario = ?";
        
        buscar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        buscar.setInt(1, id);
        
        ResultSet set = buscar.executeQuery();
        
        return set != null && set.next() ? cargar(set) : null;
    }

    @Override
    public void actualizar(Tarea entidad) throws SQLException {
        String query = "UPDATE TAREA SET tar_nombre = ?, tar_nota = ?, fk_id_asignatura = ?, fk_id_usuario_alumno = ?, fk_id_usuario_profesor = ? WHERE id_tarea = ?";
        
        actualizar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        actualizar.setString(1, entidad.getNombre());
        actualizar.setDouble(2, entidad.getNota());
        actualizar.setInt(3, entidad.getIdAsignatura());
        actualizar.setInt(4, entidad.getIdUsuarioEstudiante());
        actualizar.setInt(5, entidad.getIdUsuarioProfesor());
        actualizar.setInt(6, entidad.getId());
        
        actualizar.executeUpdate();
    }
    
    private Tarea cargar(ResultSet set) throws SQLException{
        Tarea tarea = new Tarea();
        
        tarea.setId(set.getInt("id_tarea"));
        tarea.setNombre(set.getString("tar_nombre"));
        tarea.setNota(set.getDouble("tar_nota"));
        tarea.setIdAsignatura(set.getInt("fk_id_asignatura"));
        tarea.setIdUsuarioEstudiante(set.getInt("fk_id_usuario_alumno"));
        tarea.setIdUsuarioProfesor(set.getInt("fk_id_usuario_profesor"));
        
        return tarea;
    }

    public List<Tarea> listarProfesores(int idProfesor) throws SQLException {
        String query = "SELECT * FROM TAREA WHERE fk_id_usuario_profesor = ?";
        
        listar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        listar.setInt(1, idProfesor);
        
        ResultSet set = listar.executeQuery();
        
        ArrayList<Tarea> tareas = new ArrayList<>();
        while(set.next()){
            tareas.add(cargar(set));
        }
        
        return tareas;
    }
}
