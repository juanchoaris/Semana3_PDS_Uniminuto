package controlador;

import modelo.CrudCliente;
import modelo.Clientes;
import vista.frmClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ctrlClientes implements ActionListener {

    private frmClientes view;
    private Clientes clientes;
    private CrudCliente crudCliente;

    public ctrlClientes(frmClientes _view, Clientes _clientes) {
        this.view = _view;
        this.clientes = _clientes;
        this.view.btnIngresar.addActionListener(BotonGuardar);
        this.view.btnEliminar.addActionListener(BotonEliminar);
        this.view.btnModificar.addActionListener(BotonModificar);
    }

    public void iniciar() {
        view.setTitle("Gestión de Clientes de Supemercado");
        view.setLocationRelativeTo(null);
        Consultar();

    }

    ActionListener BotonGuardar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientes.setNombres(view.txtNombres.getText());
            clientes.setApellidos(view.txtApellidos.getText());
            clientes.setEdad(Integer.parseInt(view.txtEdad.getText()));
            clientes.setCorreo(view.txtCorreo.getText());
            clientes.setCargo(view.txtCargo.getText());

            crudCliente = new CrudCliente();

            if (crudCliente.Registrar(clientes)) {
                JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente....");
                limpiar();
                Consultar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
                limpiar();
            }
        }
    };

    ActionListener BotonEliminar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientes.setId(Integer.parseInt(view.txtId.getText()));

            crudCliente = new CrudCliente();

            if (crudCliente.Eliminar(clientes)) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente....");
                limpiar();
                Consultar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiar();
            }
        }
    };

    ActionListener BotonModificar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            clientes.setNombres(view.txtNombres.getText());
            clientes.setApellidos(view.txtApellidos.getText());
            clientes.setEdad(Integer.parseInt(view.txtEdad.getText()));
            clientes.setCorreo(view.txtCorreo.getText());
            clientes.setCargo(view.txtCargo.getText());
            clientes.setId(Integer.parseInt(view.txtId.getText()));

            crudCliente = new CrudCliente();

            if (crudCliente.Modificar(clientes)) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente....");
                limpiar();
                Consultar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar");
                limpiar();
            }
        }
    };

    public void limpiar() {
        view.txtId.setText("");
        view.txtNombres.setText("");
        view.txtApellidos.setText("");
        view.txtEdad.setText("");
        view.txtCorreo.setText("");
        view.txtCargo.setText("");
    }

    public void Consultar() {
        crudCliente = new CrudCliente();
        DefaultTableModel Modelo = crudCliente.Consultar();
        view.jtClientes.setModel(Modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
