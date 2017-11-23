/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ortizol.dao;

import co.ortizol.modelos.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author johno
 */
public class DAOUsuario implements IDAO<Usuario>{

    private PreparedStatement insertar;
    private PreparedStatement buscar;
    private PreparedStatement eliminar;
    private PreparedStatement listar;
    private PreparedStatement actualizar;
    
    private static DAOUsuario instance;
    
    private DAOUsuario(){}
    
    public static DAOUsuario getInstance() {
        if(instance == null){
            instance = new DAOUsuario();
        }
        
        return instance;
    }
    
    @Override
    public void insertar(Usuario entidad) throws SQLException {
        if(DAOUsuario.getInstance().buscar(entidad.getNombre(), entidad.getContraseghnia()) == null){
            String query = "INSERT INTO USUARIO (usr_nombre, usr_contraseghnia, fk_id_rol VALUES(?, ?, ?)";
            
            insertar = Conexion.getInstance().getConnection().prepareStatement(query);
            
            insertar.setString(1, entidad.getNombre());
            insertar.setString(2, entidad.getContraseghnia());
            insertar.setInt(3, entidad.getIdRol());
            
            insertar.executeUpdate();
        }
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        String query = "DELETE FROM USUARIO WHERE id_usuario = ?";
        
        eliminar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        eliminar.setInt(1, id);
        
        eliminar.executeUpdate();
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        String query = "SELECT * FROM USUARIO";
        
        listar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        ResultSet set = listar.executeQuery();
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        while(set.next()){
            usuarios.add(cargar(set));
        }
        
        return usuarios;
    }

    @Override
    public Usuario buscar(Integer id) throws SQLException {
        String query = "SELECT * FROM USUARIO WHERE id_usuario = ?";
        
        buscar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        buscar.setInt(1, id);
        
        ResultSet set = buscar.executeQuery();
        
        return set != null && set.next() ? cargar(set) : null;
    }

    @Override
    public void actualizar(Usuario entidad) throws SQLException {
        String query = "UPDATE USUARIO SET usr_nombre = ?, usr_contraseghnia = ?, fk_id_rol = ? WHERE id_usuario = ?";
        
        actualizar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        actualizar.setString(1, entidad.getNombre());
        actualizar.setString(2, entidad.getContraseghnia());
        actualizar.setInt(3, entidad.getIdRol());
        actualizar.setInt(4, entidad.getId());
        
        actualizar.executeUpdate();
    }
    
    
    public Usuario buscar(String nombre, String contraseghnia) throws SQLException{
        String query = "SELECT * FROM USUARIO WHERE usr_nombre = ? AND usr_contraseghnia = ?";
        
        buscar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        buscar.setString(1, nombre);
        buscar.setString(2, contraseghnia);
        
        ResultSet set = buscar.executeQuery();
        
        return set != null && set.next() ? cargar(set) : null;
    }
    
    public Usuario buscar(String nombre, String contraseghnia, int idRol) throws SQLException{
        String query = "SELECT * FROM USUARIO WHERE usr_nombre = ? AND usr_contraseghnia = ? AND fk_id_rol = ?";
        
        buscar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        buscar.setString(1, nombre);
        buscar.setString(2, contraseghnia);
        buscar.setInt(3, idRol);
        
        ResultSet set = buscar.executeQuery();
        
        return set != null && set.next() ? cargar(set) : null;
    }

    private Usuario cargar(ResultSet set) throws SQLException{
        Usuario usuario = new Usuario();
        
        usuario.setId(set.getInt("id_usuario"));
        usuario.setNombre(set.getString("usr_nombre"));
        usuario.setContraseghnia(set.getString("usr_contraseghnia"));
        usuario.setIdRol(set.getInt("fk_id_rol"));
        
        return usuario;
    }

    public List<Usuario> buscarPorRol(int idRol) throws SQLException{
        String query = "SELECT * FROM USUARIO WHERE fk_id_rol = ?";
        
        listar = Conexion.getInstance().getConnection().prepareStatement(query);
        
        listar.setInt(1, idRol);
        
        ResultSet set = listar.executeQuery();
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        while(set.next()){
            usuarios.add(cargar(set));
        }
        
        return usuarios;
    }
}
