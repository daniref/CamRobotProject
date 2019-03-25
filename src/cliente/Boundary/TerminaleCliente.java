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
	private JTextField txtAdd1;
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
		
//		JLabel lblSensore1 = new JLabel("New label");
//		lblSensore1.setFont(new Font("Tahoma", Font.BOLD, 13));
//		lblSensore1.setBounds(105, 129, 315, 38);
//		frame.getContentPane().add(lblSensore1);
//		
//		
//		JLabel lblSensore2 = new JLabel("New label");
//		lblSensore2.setFont(new Font("Tahoma", Font.BOLD, 13));
//		lblSensore2.setBounds(105, 165, 302, 38);
//		frame.getContentPane().add(lblSensore2);
//
//		JLabel labelSensore3 = new JLabel("New label");
//		labelSensore3.setFont(new Font("Tahoma", Font.BOLD, 13));
//		labelSensore3.setBounds(105, 202, 302, 38);
//		frame.getContentPane().add(labelSensore3);

		
		txtAdd1 = new JTextField();
		txtAdd1.setBounds(134, 43, 116, 22);
		frame.getContentPane().add(txtAdd1);
		txtAdd1.setColumns(10);
		
		JButton btnLeggi = new JButton("Visualizza ultimi valori misurati");
		btnLeggi.setBounds(31, 91, 376, 38);
		btnLeggi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
				    Cliente_CentralinaRobotProxy ccrp= new Cliente_CentralinaRobotProxy();
				    ArrayList<String> buffer= new ArrayList<String> ();
				    buffer=ccrp.MonitoraggioRemoto();
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
		
	
		
		JLabel lblSomma = new JLabel("Riepilogo");
		lblSomma.setBounds(12, 142, 81, 47);
		frame.getContentPane().add(lblSomma);
		

		
		
		JLabel lblIdSegnalazione = new JLabel("Id Segnalazione");
		lblIdSegnalazione.setBounds(33, 31, 103, 47);
		frame.getContentPane().add(lblIdSegnalazione);
		
		JButton btnNewButton = new JButton("Notiifca\r\n Lettura");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(280, 35, 127, 38);
		frame.getContentPane().add(btnNewButton);
		
		
		
	}
}
