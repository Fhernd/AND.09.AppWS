package co.ortizol.recursos;

import co.ortizol.dao.DAOTarea;
import co.ortizol.modelos.Tarea;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("tareas")
public class TareasRecurso {

    private static final Logger LOG = Logger.getLogger(TareasRecurso.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tarea> get() {
        try {
            return DAOTarea.getInstance().listar();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Problema al listar las tareas: {0}", e.getMessage());
        }

        return null;
    }
    
    @GET
    @Path("{idProfesor}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tarea> getTareasPorProfesor(@PathParam("idProfesor") int idProfesor) {
        try {
            return DAOTarea.getInstance().listarProfesores(idProfesor);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Problema al listar las tareas: {0}", e.getMessage());
        }

        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(Tarea tarea) {
        try {
            DAOTarea.getInstance().insertar(tarea);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Problema al guardar la tarea: {0}", e.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        try {
            DAOTarea.getInstance().eliminar(id);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Problema al borrar la tarea: {0}", e.getMessage());
        }
    }
    
    @PUT
    @Path("{id}")
    public void put(@PathParam("id") int id, Tarea tarea){
        tarea.setId(id);
        
        try{
            DAOTarea.getInstance().actualizar(tarea);
        } catch(SQLException e){
            LOG.log(Level.SEVERE, "Problema al listar las tareas: {0}", e.getMessage());
        }
    }
}
