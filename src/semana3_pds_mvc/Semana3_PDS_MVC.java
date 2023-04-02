
package semana3_pds_mvc;

import controlador.ctrlClientes;
import modelo.Clientes;
import vista.frmClientes;


public class Semana3_PDS_MVC {

    
    public static void main(String[] args) {
        frmClientes view = new frmClientes();
        Clientes clientes = new Clientes();
        
        ctrlClientes ctrlCli = new ctrlClientes(view, clientes);
        ctrlCli.iniciar();
        
        view.setVisible(true);
    }
    
}
