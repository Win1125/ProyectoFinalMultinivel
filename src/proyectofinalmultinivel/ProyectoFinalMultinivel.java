/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinalmultinivel;
import Controlador.*;
import Modelo.*;
import Vista.*;
/**
 *
 * @author Edwin Fandiño
 */
public class ProyectoFinalMultinivel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Producto mod = new Producto();
        MetodosProducto modC = new MetodosProducto();
        FormularioProducto frm = new FormularioProducto();

        ControladorProducto control = new ControladorProducto(mod, modC, frm);
        control.iniciar();
        frm.setVisible(true);
    }
    
}
