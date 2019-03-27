package cliente.Boundary;


import cliente.ProxyComunicazioneSincrona.*;
import cliente.Boundary.*;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

public class TerminaleCliente {

	private JFrame frame;
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

		frame = new JFrame("Terminale Cliente");
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
		
		JButton btnLeggi = new JButton("Visualizza ultimi valori misurati");
		btnLeggi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLeggi.setForeground(Color.BLUE);
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
		
		JButton btnNewButton = new JButton("Notiifca\r\n Lettura SMS Segnalazione");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Notifica n= new Notifica();
				n.WindowNotifica();
			}
		});
		btnNewButton.setBounds(31, 35, 376, 38);
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
