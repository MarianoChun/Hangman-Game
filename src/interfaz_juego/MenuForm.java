package interfaz_juego;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuForm extends JFrame {

	private JFrame frmMenu;
	private JComboBox dificultadComboBox;
	private JComboBox idiomaComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuForm window = new MenuForm();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.getContentPane().setFont(new Font("Dialog", Font.BOLD, 14));
		frmMenu.setForeground(Color.WHITE);
		frmMenu.getContentPane().setForeground(Color.WHITE);
		frmMenu.setResizable(false);
		frmMenu.setTitle("Juego Ahorcado");
		frmMenu.setBackground(Color.PINK);
		frmMenu.setBounds(100, 100, 589, 375);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		frmMenu.setVisible(true);

		JLabel lblMenuPrincipal = new JLabel("Menú Principal");
		lblMenuPrincipal.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuPrincipal.setBounds(209, 63, 133, 28);
		frmMenu.getContentPane().add(lblMenuPrincipal);

		JButton btnIniciarJuego = new JButton("Iniciar juego");
		btnIniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainForm juego = new MainForm();
				frmMenu.setVisible(false);

			}
		});
		btnIniciarJuego.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnIniciarJuego.setBounds(209, 102, 117, 26);
		frmMenu.getContentPane().add(btnIniciarJuego);

		JComboBox<String> dificultadComboBox = new JComboBox<String>();
		dificultadComboBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		dificultadComboBox.setBounds(209, 166, 117, 22);
		dificultadComboBox.addItem("Fácil");
		dificultadComboBox.addItem("Normal");
		dificultadComboBox.addItem("Difícil");
		frmMenu.getContentPane().add(dificultadComboBox);
		this.dificultadComboBox = dificultadComboBox;

		JLabel lblDificultad = new JLabel("Dificultad");
		lblDificultad.setHorizontalAlignment(SwingConstants.CENTER);
		lblDificultad.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDificultad.setBounds(218, 139, 92, 14);
		frmMenu.getContentPane().add(lblDificultad);

		JComboBox<String> idiomaComboBox = new JComboBox<String>();
		idiomaComboBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		idiomaComboBox.setBounds(209, 223, 117, 22);
		idiomaComboBox.addItem("Español");
		idiomaComboBox.addItem("English");
		this.idiomaComboBox = idiomaComboBox;
		frmMenu.getContentPane().add(idiomaComboBox);

		JLabel idiomaLabel = new JLabel("Idioma");
		idiomaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idiomaLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		idiomaLabel.setBounds(219, 198, 92, 14);
		frmMenu.getContentPane().add(idiomaLabel);

	}

	public String getDificultad() {
		return (String) this.dificultadComboBox.getSelectedItem();
	}

	public String getIdioma() {
		return (String) this.idiomaComboBox.getSelectedItem();
	}

}
