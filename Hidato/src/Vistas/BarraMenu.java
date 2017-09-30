/*  Class BarraMenu:
    Descripcion: Vista para la barra de menu de la aplicacion.
    Autor: alex.catarineu
    Revisado: 21/12/2009 06:27 */

package Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

public class BarraMenu extends JMenuBar {

    /* Menu Archivo */
    private JMenu menuArchivo;
    private JMenuItem itemNuevaPartida;
    private JSeparator separador1;
    private JMenuItem itemAbandonarPartida;
    private JSeparator separador3;
    private JMenuItem itemImprimir;
    private JSeparator separador4;
    private JMenuItem itemSalir;

    /* Menu Editar */
    private JMenu menuEditar;
    private JMenuItem itemDeshacer;
    private JSeparator separador5;
    private JMenuItem itemResetearPartida;

    /* PRE: - */
    public BarraMenu() {

        initComponents();
    }
    /* POST: Crea la instancia de BarraMenu */

    /* PRE: - */
    private void initComponents() {

        itemNuevaPartida = new JMenuItem();
        itemNuevaPartida.setText("Nueva Partida...");
        itemNuevaPartida.setToolTipText("Crear una nueva partida");
        itemNuevaPartida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
          InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
        itemNuevaPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ControladorVistas.getInstance().nuevaPartida();
            }
        });

        separador1 = new JSeparator();

        itemAbandonarPartida = new JMenuItem();
        itemAbandonarPartida.setText("Abandonar Partida");
        itemAbandonarPartida.setToolTipText("Abandonar la partida actual");
        itemAbandonarPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ControladorVistas.getInstance().abandonarPartida();
            }
        });

        separador3 = new JSeparator();

        itemImprimir = new JMenuItem();
        itemImprimir.setText("Imprimir...");
        itemImprimir.setToolTipText("Imprimir el tablero");
        itemImprimir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
          InputEvent.CTRL_MASK));
        itemImprimir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ControladorVistas.getInstance().imprimir();
            }
        });

        separador4 = new JSeparator();

        itemSalir = new JMenuItem();
        itemSalir.setText("Salir");
        itemSalir.setToolTipText("Salir de la aplicación");
        itemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
          InputEvent.CTRL_MASK));
        itemSalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ControladorVistas.getInstance().salir();
            }
        });

        menuArchivo = new JMenu();
        menuArchivo.setText("Archivo ");
        menuArchivo.add(itemNuevaPartida);
        menuArchivo.add(separador1);
        menuArchivo.add(itemAbandonarPartida);
        menuArchivo.add(separador3);
        menuArchivo.add(itemImprimir);
        menuArchivo.add(separador4);
        menuArchivo.add(itemSalir);

        itemDeshacer = new JMenuItem();
        itemDeshacer.setText("Deshacer");
        itemDeshacer.setToolTipText("Deshacer el último movimiento");
        itemDeshacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
          InputEvent.CTRL_MASK));
        itemDeshacer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ControladorVistas.getInstance().deshacer();
            }
        });

        separador5 = new JSeparator();

        itemResetearPartida = new JMenuItem();
        itemResetearPartida.setText("Resetear Partida");
        itemResetearPartida.setToolTipText("Resetear la partida actual");
        itemResetearPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ControladorVistas.getInstance().resetearPartida();
            }
        });

        menuEditar = new JMenu();
        menuEditar.setText("Editar");
        menuEditar.add(itemDeshacer);
        menuEditar.add(separador5);
        menuEditar.add(itemResetearPartida);

 

        add(menuArchivo);
        add(menuEditar);
    }
    /* POST: Crea e inicializa correctamente todos los componentes y variables usadas por
        la barra de menu */
}
