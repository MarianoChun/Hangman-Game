package interfaz_juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ahorcado.Ahorcado;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class GUIAhorcado {

	private JFrame frame;
	private JTextField textLetra;
	private Ahorcado a = new Ahorcado("hola");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAhorcado window = new GUIAhorcado();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIAhorcado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// palabra a adivinar
		JLabel lblPalabraConGuiones = new JLabel("");
		lblPalabraConGuiones.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPalabraConGuiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalabraConGuiones.setBounds(129, 109, 162, 34);
		frame.getContentPane().add(lblPalabraConGuiones);
		lblPalabraConGuiones.setText(a.obtenerPalabraAAdivinar().toString());
		
		// verificar letra ingresada
		JButton btnVerificarLetra = new JButton("Verificar letra");
		btnVerificarLetra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char letraUsuario = textLetra.getText().charAt(0);
				a.adivinarLetra(letraUsuario);
				lblPalabraConGuiones.setText(a.obtenerPalabraAAdivinar().toString());
			}
		});
		btnVerificarLetra.setBounds(138, 199, 143, 23);
		frame.getContentPane().add(btnVerificarLetra);

		textLetra = new JTextField();
		textLetra.setBounds(138, 166, 143, 20);
		frame.getContentPane().add(textLetra);
		textLetra.setColumns(10);

		// texto
		JLabel lblPalabra = new JLabel("Palabra a adivinar");
		lblPalabra.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalabra.setBounds(119, 64, 172, 34);
		frame.getContentPane().add(lblPalabra);

		JLabel lblPuntaje = new JLabel("Puntaje: " + a.getPuntaje());
		lblPuntaje.setBounds(10, 11, 93, 23);
		frame.getContentPane().add(lblPuntaje);

		JLabel lblNewLabel = new JLabel("Intentos: " + a.getIntentos());
		lblNewLabel.setBounds(340, 11, 84, 23);
		frame.getContentPane().add(lblNewLabel);

	}
}
