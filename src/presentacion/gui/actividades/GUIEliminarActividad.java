package presentacion.gui.actividades;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import negocio.actividades.TransferActividad;
import presentacion.controlador.ControladorAplicacion;
import presentacion.controlador.comandos.factoria.EventoNegocio;
import presentacion.controlador.dispatcher.EventoVista;
import presentacion.gui.GUIVista;

@SuppressWarnings("serial")
public class GUIEliminarActividad extends JFrame implements GUIVista {
	
	private static GUIEliminarActividad instancia;
	private JPanel contentPane;
	private JTextField textId;
	private JLabel lblId;
	private JButton btnBuscar;
	
	private GUIEliminarActividad() {
		initComponents();
	}
	
	public static GUIEliminarActividad obtenerInstancia(){
		if(instancia == null){
				instancia = new GUIEliminarActividad();
		}
		return instancia;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIEliminarActividad frame = new GUIEliminarActividad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initComponents() {
		setFont(new Font("Courier New", Font.PLAIN, 12));
		setTitle("ELIMINAR ACTIVIDAD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textId = new JTextField();
		textId.setFont(new Font("Calibri", Font.PLAIN, 12));
		textId.setBounds(164, 64, 86, 20);
		contentPane.add(textId);
		textId.setColumns(10);
		
		lblId = new JLabel("INTRODUCE ID:");
		lblId.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblId.setBounds(45, 67, 98, 14);
		contentPane.add(lblId);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionBuscarActionPerformed(e);
			}
		});
		btnBuscar.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnBuscar.setBounds(267, 63, 89, 23);
		contentPane.add(btnBuscar);
	}
	
	private void seleccionBuscarActionPerformed(ActionEvent evt) {
		TransferActividad actividad = new TransferActividad();
		actividad.setId(Integer.parseInt(textId.getText()));
		try {
			ControladorAplicacion.obtenerInstancia().accion(EventoNegocio.ELIMINAR_ACTIVIDAD, actividad);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR \n" + e.getMessage(), "Eliminar Actividad", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actualizar(int id_evento_vista, Object datos) {
		if (id_evento_vista == EventoVista.ELIMINAR_ACTIVIDAD_EXITO) {
			JOptionPane.showMessageDialog(null, "La actividad se ha eliminado con �xito", "Eliminar Actividad", JOptionPane.OK_OPTION);
			this.setVisible(false);
			textId.setText("");
		} else if (id_evento_vista == EventoVista.ELIMINAR_ACTIVIDAD_FALLO) {
			JOptionPane.showMessageDialog(null, "ERROR No se ha podido eliminar la actividad", "Eliminar Actividad", JOptionPane.ERROR_MESSAGE);
		}
	}
}
