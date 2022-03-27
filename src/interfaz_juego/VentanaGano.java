package interfaz_juego;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaGano extends JDialog {
	private boolean apretoSi;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaGano dialog = new VentanaGano(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaGano(JFrame parent) {
		super(parent, true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setAutoRequestFocus(false);
		setBounds(100, 100, 270, 203);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¡Ganaste!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(40, 11, 181, 45);
		contentPanel.add(lblNewLabel);
		
		JLabel lbldeseasContinuar = new JLabel("¿Deseas continuar?");
		lbldeseasContinuar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbldeseasContinuar.setBounds(70, 67, 135, 14);
		contentPanel.add(lbldeseasContinuar);
		{
			JButton siButton = new JButton("Sí");
			siButton.setBounds(52, 108, 41, 23);
			contentPanel.add(siButton);
			siButton.setActionCommand("Sí");
			getRootPane().setDefaultButton(siButton);
		}
		{
			JButton noButton = new JButton("No");
			noButton.setBounds(160, 108, 45, 23);
			contentPanel.add(noButton);
			noButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			noButton.setActionCommand("No");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	
	public boolean apretoSi() {
		return apretoSi();
	}
	
}
