package Principal;

import Controlador.Controlador;
/**
 * @author Mario
 */
public class Principal 
{    
    public static void main(String[] args) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                Controlador.obtenerInstancia().ejecutar();
            }
        });
    }
    
}
