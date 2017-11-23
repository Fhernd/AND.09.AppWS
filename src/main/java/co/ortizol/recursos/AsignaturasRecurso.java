package co.ortizol.recursos;

import co.ortizol.dao.DAOAsignatura;
import co.ortizol.modelos.Asignatura;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("asignaturas")
public class AsignaturasRecurso {

    private static final Logger LOG = Logger.getLogger(AsignaturasRecurso.class.getName());
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Asignatura> get() {
        try {
            return DAOAsignatura.getInstance().listar();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Problema al listar las asignaturas: {0}", e.getMessage());
        }

        return null;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Asignatura getAsignaturaPorId(@PathParam("id")int id){
        try{
            return DAOAsignatura.getInstance().buscar(id);
        } catch(SQLException e){
            LOG.log(Level.SEVERE, "Problema al buscar asignatura por ID: {0}", e.getMessage());
        }
        
        return null;
    }
}
