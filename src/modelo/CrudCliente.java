package modelo;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class CrudCliente extends Conexion {

    public boolean Registrar(Clientes cli) {

        PreparedStatement ps = null;

        Connection con = getConexion();
        String sql = "INSERT INTO clientes (Nombres, Apellidos, Edad, Correo, Cargo) VALUES (?,?,?,?,?)";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, cli.getNombres());
            ps.setString(2, cli.getApellidos());
            ps.setInt(3, cli.getEdad());
            ps.setString(4, cli.getCorreo());
            ps.setString(5, cli.getCargo());
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }

    public boolean Modificar(Clientes cli) {

        PreparedStatement ps = null;

        Connection con = getConexion();
        String sql = "UPDATE clientes  SET Nombres = ?, Apellidos = ?, Edad = ?, Correo = ?, Cargo = ? WHERE Id = ?";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, cli.getNombres());
            ps.setString(2, cli.getApellidos());
            ps.setInt(3, cli.getEdad());
            ps.setString(4, cli.getCorreo());
            ps.setString(5, cli.getCargo());
            ps.setInt(6, cli.getId());
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    public boolean Eliminar(Clientes cli) {

        PreparedStatement ps = null;

        Connection con = getConexion();
        String sql = "DELETE FROM clientes WHERE Id = ?";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setInt(1, cli.getId());
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    public DefaultTableModel Consultar() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();          
        String sql = "SELECT * FROM clientes";      
        DefaultTableModel modelo = new DefaultTableModel();

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);            
            rs = (ResultSet) ps.executeQuery();

            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsmd.getColumnCount();
            
            modelo.addColumn("Id");
            modelo.addColumn("Nombres");
            modelo.addColumn("Apellidos");
            modelo.addColumn("Edad");
            modelo.addColumn("Correo");
            modelo.addColumn("Cargo");
            
            while (rs.next()) {
                
                Object[] filas = new Object[cantidadColumnas];
                
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                
                modelo.addRow(filas);                
            }           
           
            return modelo;

        } catch (SQLException e) {
            System.out.println(e);  
            return modelo;
        } 
    }
    
    
}
