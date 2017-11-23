/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ortizol.recursos;

import co.ortizol.dao.DAOUsuario;
import co.ortizol.modelos.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("usuarios")
public class UsuariosRecurso {

    private static final Logger LOG = Logger.getLogger(UsuariosRecurso.class.getName());
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> get(){
        try{
            return DAOUsuario.getInstance().listar();
        } catch(SQLException e){
            LOG.log(Level.SEVERE, "Problema al listar los usuarios: {0}", e.getMessage());
        }
        
        return null;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarioPorRol(@PathParam("id")int idRol){
        try{
            return DAOUsuario.getInstance().buscarPorRol(idRol);
        } catch(SQLException e){
            LOG.log(Level.SEVERE, "Problema al listar usuarios por rol: {0}", e.getMessage());
        }
        
        return null;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario post(Usuario usuario){
        try{
            return DAOUsuario.getInstance().buscar(usuario.getNombre(), usuario.getContraseghnia(), usuario.getIdRol());
        } catch(SQLException e){
            LOG.log(Level.SEVERE, "Problem al buscar el usuario: {0}", e.getMessage());
        }
        
        return null;
    }
}
