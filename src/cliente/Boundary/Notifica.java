package cliente.Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cliente.ProxyComunicazioneSincrona.Cliente_CentraleOperativaProxy;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Notifica {

	private JFrame frmFormInvioNotifiche;
	private JTextField txtIDSegnalazione;

	/**
	 * Launch the application.
	 */
	public static void WindowNotifica() {
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
		frmFormInvioNotifiche.setBounds(1050, 120, 514, 202);
		//frmFormInvioNotifiche.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFormInvioNotifiche.getContentPane().setLayout(null);
		
		JLabel lblInserisciIdSegnalazione = new JLabel("Inserisci ID Segnalazione");
		lblInserisciIdSegnalazione.setBounds(12, 24, 167, 30);
		frmFormInvioNotifiche.getContentPane().add(lblInserisciIdSegnalazione);
		
		txtIDSegnalazione = new JTextField();
		txtIDSegnalazione.setBounds(169, 19, 179, 41);
		frmFormInvioNotifiche.getContentPane().add(txtIDSegnalazione);
		txtIDSegnalazione.setColumns(10);
		
		JLabel lblSelezionaTipologia = new JLabel("Seleziona Tipologia");
		lblSelezionaTipologia.setBounds(12, 85, 167, 30);
		frmFormInvioNotifiche.getContentPane().add(lblSelezionaTipologia);
		String[] opzioni = {"Termometro", "Rilevatore di Fumo", "Sensore di Prossimità"};
		JComboBox comboBox = new JComboBox(opzioni);
		comboBox.setBounds(169, 77, 167, 38);
		frmFormInvioNotifiche.getContentPane().add(comboBox);
		
		JButton btnClick = new JButton("Invia");
		btnClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String buffer=txtIDSegnalazione.getText();
				if(buffer.length()==6){
					if(buffer.substring(0, 2).compareTo("sg")==0){
						try{
							Integer.parseInt(buffer.substring(2));
						    Cliente_CentraleOperativaProxy ccop= new Cliente_CentraleOperativaProxy();
						    String tipo;
							switch(comboBox.getSelectedIndex()) {
							case 0: tipo="T";
									break;
							case 1: tipo="F";
									break;
							case 2: tipo="P";
									break;
							default: tipo ="G"; 
							}

						    if(ccop.NotificaLetturaSegnalazione(buffer,tipo)) JOptionPane.showMessageDialog(frmFormInvioNotifiche, "Notifica inviata e segnalazione <"+buffer+"> chiusa regolarmente");
						    else{ JOptionPane.showMessageDialog(frmFormInvioNotifiche, "Notifica inviata per la segnalazione <"+ buffer +">, ma è stata ignorata ");}
						    frmFormInvioNotifiche.setVisible(false);
							}
						catch(Exception e){
							JOptionPane.showMessageDialog(frmFormInvioNotifiche, "i caratteri che seguono sg devono essere numeri es: sg0004");						
							}
						}
					else {
						JOptionPane.showMessageDialog(frmFormInvioNotifiche, "i primi due caratteri devono essere 'sg'");						
					}
				}					
				
				else {
					JOptionPane.showMessageDialog(frmFormInvioNotifiche, "Numero caratteri Segnalazione Errato - devono essere 6");
					}

				
			}
		});		btnClick.setBounds(369, 56, 92, 25);
		frmFormInvioNotifiche.getContentPane().add(btnClick);
	}
}
