package Controlador;

import Modelo.MetodosProducto;
import Modelo.Producto;
import Vista.FormularioProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorProducto implements ActionListener {

    private final Producto modelo;
    private final MetodosProducto consultas;
    private final FormularioProducto vista;

    public ControladorProducto(Producto modelo, MetodosProducto consultas, FormularioProducto vista) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnModificar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnBuscar().addActionListener(this);
        this.vista.getBtnLimpiar().addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Productos");
        vista.setLocationRelativeTo(null);
        vista.getTxtId().setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.getBtnGuardar()) {
            modelo.setCodigo(vista.getTxtCodigo().getText());
            modelo.setNombre(vista.getTxtNombre().getText());
            modelo.setPrecio(Double.parseDouble(vista.getTxtPrecio().getText()));
            modelo.setCantidad(Integer.parseInt(vista.getTxtCantidad().getText()));

            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                consultas.limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                consultas.limpiar();
            }
        }

        if (e.getSource() == vista.getBtnModificar()) {
            modelo.setId(Integer.parseInt(vista.getTxtId().getText()));
            modelo.setCodigo(vista.getTxtCodigo().getText());
            modelo.setNombre(vista.getTxtNombre().getText());
            modelo.setPrecio(Double.parseDouble(vista.getTxtPrecio().getText()));
            modelo.setCantidad(Integer.parseInt(vista.getTxtCantidad().getText()));

            if (consultas.modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                consultas.limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                consultas.limpiar();
            }
        }

        if (e.getSource() == vista.getBtnEliminar()) {
            modelo.setId(Integer.parseInt(vista.getTxtId().getText()));

            if (consultas.eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                consultas.limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                consultas.limpiar();
            }
        }

        if (e.getSource() == vista.getBtnBuscar()) {
            modelo.setCodigo(vista.getTxtCodigo().getText());

            if (consultas.buscar(modelo)) {
                vista.getTxtId().setText(String.valueOf(modelo.getId()));
                vista.getTxtCodigo().setText(modelo.getCodigo());
                vista.getTxtNombre().setText(modelo.getNombre());
                vista.getTxtPrecio().setText(String.valueOf(modelo.getPrecio()));
                vista.getTxtCantidad().setText(String.valueOf(modelo.getCantidad()));

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro registro");
                consultas.limpiar();
            }
        }

        if (e.getSource() == vista.getBtnLimpiar()) {
            consultas.limpiar();
        }
    }

    
}
