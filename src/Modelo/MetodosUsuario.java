package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MetodosUsuario extends Conexion {

    public ArrayList<Usuario> login(String user, String clave) {

        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        Usuario cuenta;
        ArrayList list = new ArrayList();

        try {
            if (con != null) {

                //SELECT CAST(AES_DECRYPT(Pass, "key")AS char(50)) FROM `usuarios` WHERE 1                
                String sql = "SELECT User, Pass FROM usuarios WHERE User =?   AND Pass=AES_ENCRYPT(?, 'key')";

                ps = con.prepareStatement(sql);
                ps.setString(1, user);
                ps.setString(2, clave);

                rs = ps.executeQuery();

                if (rs.next()) {
                    cuenta = new Usuario();
                    cuenta.setUser(rs.getString("User"));
                    cuenta.setPass(rs.getString("Pass"));
                    list.add(cuenta);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al realizar la operacion, intente mas tarde", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
        }
        return list;
    }
}
