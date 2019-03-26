package cliente.Boundary;


import cliente.ProxyComunicazioneSincrona.*;



import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class TerminaleCliente {

	private JFrame frame;
	private JTextField txtIDSegnalazione;
	private ArrayList<JLabel> ListaLabelSensore1 = new ArrayList<JLabel>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TerminaleCliente window = new TerminaleCliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public TerminaleCliente() throws MalformedURLException, RemoteException, NotBoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	private void initialize() throws MalformedURLException, RemoteException, NotBoundException {

		frame = new JFrame("CLIENTE 1");
		frame.setBounds(100, 100, 450, 389);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		for(int i=0;i<5;i++) {
			JLabel lblDati = new JLabel("Dati sensore :" +(i+1));
    		lblDati.setFont(new Font("Tahoma", Font.BOLD, 13));
    		lblDati.setBounds(105, 140+i*35, 315, 38);
    		lblDati.setVisible(false);
    		frame.getContentPane().add(lblDati);
    		
    		ListaLabelSensore1.add(lblDati);
		}	
		
		JLabel lblRiep = new JLabel("Riepilogo");
		lblRiep.setBounds(12, 142, 81, 47);
		lblRiep.setVisible(false);
		frame.getContentPane().add(lblRiep);
		
		txtIDSegnalazione = new JTextField();
		txtIDSegnalazione.setBounds(134, 43, 116, 22);
		frame.getContentPane().add(txtIDSegnalazione);
		txtIDSegnalazione.setColumns(10);
		
		JButton btnLeggi = new JButton("Visualizza ultimi valori misurati");
		btnLeggi.setBounds(31, 91, 376, 38);
		btnLeggi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
				    Cliente_CentralinaRobotProxy ccrp= new Cliente_CentralinaRobotProxy();
				    ArrayList<String> buffer= new ArrayList<String> ();
				    buffer=ccrp.MonitoraggioRemoto();
					lblRiep.setVisible(true);
				    for(int i=0;i<buffer.size();i++) {
			    		ListaLabelSensore1.get(i).setText(buffer.get(i));
			    		System.out.println(buffer.get(i));
			    		ListaLabelSensore1.get(i).setVisible(true);
						    }	
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated method stub
					
			
					// TODO Auto-generated catch block
				
				}
			});
		frame.getContentPane().add(btnLeggi);
		
	
		

		

		
		
		JLabel lblIdSegnalazione = new JLabel("Id Segnalazione");
		lblIdSegnalazione.setBounds(33, 31, 103, 47);
		frame.getContentPane().add(lblIdSegnalazione);
		
		JButton btnNewButton = new JButton("Notiifca\r\n Lettura");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String buffer=txtIDSegnalazione.getText();
				if(buffer.length()==6){
					if(buffer.substring(0, 2).compareTo("sg")==0){
						try{
							Integer.parseInt(buffer.substring(2));
						    Cliente_CentraleOperativaProxy ccop= new Cliente_CentraleOperativaProxy();
						    if(ccop.NotificaLetturaSegnalazione(buffer)) JOptionPane.showMessageDialog(frame, "Notifica inviata e segnalazione <"+buffer+"> chiusa regolarmente");
						    else{ JOptionPane.showMessageDialog(frame, "Notifica inviata, ma la segnalazione <"+ buffer +"> e' stata risolta contattando un GESTORE ESTERNO");}

							}
						catch(Exception e){
							JOptionPane.showMessageDialog(frame, "i caratteri che seguono sg devono essere numeri es: sg0004");						
							}
						}
					else {
						JOptionPane.showMessageDialog(frame, "i primi due caratteri devono essere 'sg'");						
					}
				}					
				
				else {
					JOptionPane.showMessageDialog(frame, "Numero caratteri Segnalazione Errato - devono essere 6");
					}

				
			}
		});
		btnNewButton.setBounds(280, 35, 127, 38);
		frame.getContentPane().add(btnNewButton);
		
		
		
	}
	
	public boolean isformatoSegnalazione(String a) {
		if (a.length()!=6)return false;
//		System.out.print("Inserire un formato di segnalazione valido : es: sg0413");
		if(a.substring(0,2)!="sg") return false;
//		System.out.print("Inserire un formato di segnalazione valido : es: sg0413");
		try {
			Integer.parseInt(a.substring(2,6));
		}
		catch (Exception e){
			return false;
//			System.out.print("Parsing non andato a buon fine");
		}
		
		return true;
	}
	
}
