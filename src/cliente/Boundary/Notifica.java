package cliente.Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Notifica {

	private JFrame frmFormInvioNotifiche;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notifica window = new Notifica();
					window.frmFormInvioNotifiche.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Notifica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFormInvioNotifiche = new JFrame();
		frmFormInvioNotifiche.setTitle("Finestra Notifiche");
		frmFormInvioNotifiche.setBounds(100, 100, 514, 202);
		frmFormInvioNotifiche.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFormInvioNotifiche.getContentPane().setLayout(null);
		
		JLabel lblInserisciIdSegnalazione = new JLabel("Inserisci ID Segnalazione");
		lblInserisciIdSegnalazione.setBounds(12, 24, 167, 30);
		frmFormInvioNotifiche.getContentPane().add(lblInserisciIdSegnalazione);
		
		textField = new JTextField();
		textField.setBounds(169, 19, 179, 41);
		frmFormInvioNotifiche.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSelezionaTipologia = new JLabel("Seleziona Tipologia");
		lblSelezionaTipologia.setBounds(12, 85, 167, 30);
		frmFormInvioNotifiche.getContentPane().add(lblSelezionaTipologia);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Segnalazione D'allarme\r\nSensore di Funo\r\npippo");
		comboBox.setBounds(169, 77, 167, 38);
		frmFormInvioNotifiche.getContentPane().add(comboBox);
		
		JButton btnClick = new JButton("Invia");
		btnClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnClick.setBounds(369, 56, 92, 25);
		frmFormInvioNotifiche.getContentPane().add(btnClick);
	}
}
