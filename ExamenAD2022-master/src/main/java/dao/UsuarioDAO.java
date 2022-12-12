package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

public class UsuarioDAO {

    private Connection connection;
    public static final String URL
            = "jdbc:mysql://localhost:3306/examen"
            + "?zeroDateTimeBehavior=CONVERT_TO_NULL";
    public static final String USER = "root";
    public static final String PASSWORD = "admin";

    /* Completar consultas */
    static final String INSERT_QUERY = """
                INSERT INTO `usuario` 
                        (`id`, `nombre`, `apellidos`, `dni`) 
                VALUES  (NULL, ?, ?, ?);""";
    static final String LIST_QUERY = " SELECT * FROM usuario ORDER BY id";
    static final String GET_BY_DNI = "SELECT * FROM usuario where dni = ?";

    public void connect() {
        try {
            /* completar código de conexión */
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Base de datos conectada");
        } catch (Exception ex) {
            System.out.println("Error al conectar a la base de datos");
            System.out.println("ex");
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");
        }
    }

    public void save(Usuario user) {

        /**
         * Completar código para guardar un usuario Este método no debe generar
         * el id del usuario, ya que la base de datos es la que lo genera. Una
         * vez obtenido el id del usuario tras la consulta sql, hay que
         * modificarlo en el objeto que se pasa como parámetro de forma que pase
         * a tener el id correcto.
         */
        try ( var pst = connection.prepareStatement(INSERT_QUERY, RETURN_GENERATED_KEYS)) {

            pst.setString(1, user.getNombre());
            pst.setString(2, user.getApellidos());
            pst.setString(3, user.getDni());

            if (pst.executeUpdate() > 0) {
                var keys = pst.getGeneratedKeys();
            }

            //System.out.println("Método save no completado");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Usuario> list() {
        /* Completar código para devolver un arraylist con todos los usuarios */

        var salida = new ArrayList<Usuario>(0);

        try ( var pst = connection.prepareStatement(LIST_QUERY)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                var user = new Usuario();

                user.setId(rs.getLong("id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellidos(rs.getString("apellidos"));
                user.setDni(rs.getString("dni"));
                salida.add(user);
            }

            //System.out.println("Método list no completado");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }

    public Usuario getByDNI(String dni) {

        Usuario salida = new Usuario();

        /**
         * Completar código para devolver el usuario que tenga ese dni. Si no
         * existe, se debe devolver null
         */
        
        try(var pst = connection.prepareStatement(GET_BY_DNI )){
            
            pst.setString(1, dni);
            
            ResultSet resultado = pst.executeQuery();
            
            if(resultado.next()) {
                salida.setId(resultado.getLong("id"));
                salida.setNombre(resultado.getString("nombre"));
                salida.setApellidos(resultado.getString("apellidos"));
                salida.setDni(dni);
                return salida;
                
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
       // System.out.println("Método getByDNI no completado");

        return salida;
    }
}


