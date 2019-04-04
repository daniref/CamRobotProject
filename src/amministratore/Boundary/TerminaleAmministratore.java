package amministratore.Boundary;


import javax.swing.JFrame;
import javax.swing.JTextField;

import amministratore.ProxyComunicazioneSincrona.Amministratore_CentraleOperativaProxy;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;

import javax.jms.JMSException;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.UIManager;


public class TerminaleAmministratore {
	
	private JFrame frmTerminaleAmministratore;
	private JTextField txtCentraleOperativa;

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws JMSException, InterruptedException {
		System.out.println("*****CENTRALE****");


		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TerminaleAmministratore window = new TerminaleAmministratore();
					window.frmTerminaleAmministratore.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		}

	/**
	 * Create the application.
	 * @throws JMSException 
	 * @wbp.parser.entryPoint
	 */

	public TerminaleAmministratore() throws JMSException {//, TimerProxy timer1, TimerProxy timer2  ) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws JMSException 
	 */
	private void initialize() throws JMSException {
		Started avviato=new Started();	
		frmTerminaleAmministratore = new JFrame();
		frmTerminaleAmministratore.getContentPane().setBackground(new Color(245, 245, 245));
		ImageIcon icona= new ImageIcon("/admin.png");
		frmTerminaleAmministratore.setIconImage(Toolkit.getDefaultToolkit().getImage(TerminaleAmministratore.class.getResource("/admin.png"))); //getImage(icona));
		// frmTerminaleAmministratore.setIconImages((List<? extends Image>) (new ImageIcon(this.getClass().getResource("/admin.png")));;
		//setIcon(new ImageIcon(this.getClass().getResource("/offv.png")));
		frmTerminaleAmministratore.setTitle("Terminale Amministratore");
		frmTerminaleAmministratore.setBounds(100, 100, 450, 300);
		frmTerminaleAmministratore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTerminaleAmministratore.getContentPane().setLayout(null);
		
		txtCentraleOperativa = new JTextField();
		txtCentraleOperativa.setToolTipText("");
		txtCentraleOperativa.setBackground(new Color(245, 245, 245));
		txtCentraleOperativa.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtCentraleOperativa.setForeground(Color.BLUE);
		txtCentraleOperativa.setText("CENTRALE OPERATIVA");
		txtCentraleOperativa.setBounds(29, 67, 298, 61);
		frmTerminaleAmministratore.getContentPane().add(txtCentraleOperativa);
		txtCentraleOperativa.setColumns(10);
		
		JButton btnPower = new JButton("");
		btnPower.setForeground(new Color(245, 245, 245));
		btnPower.setBackground((new Color(245, 245, 245)));
		btnPower.setIcon(new ImageIcon(this.getClass().getResource("/offv.png")));

		btnPower.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPower.setBounds(339, 30, 68, 150);
		btnPower.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final Image imgOn = new ImageIcon(this.getClass().getResource("/onv.png")).getImage();
				final Image imgOff = new ImageIcon(this.getClass().getResource("/offv.png")).getImage();
				if(!avviato.isB()) {
						avviato.setB(true);
						Amministratore_CentraleOperativaProxy acp= new Amministratore_CentraleOperativaProxy();
						try {
							acp.start();
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (NotBoundException e1) {
							e1.printStackTrace();
						}
						btnPower.setIcon(new ImageIcon(imgOn));
						}
				else {
					Amministratore_CentraleOperativaProxy acp= new Amministratore_CentraleOperativaProxy();
					try {
						acp.stop();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					avviato.setB(false);
					btnPower.setIcon(new ImageIcon(imgOff));
					}
		}
		
	});
	frmTerminaleAmministratore.getContentPane().add(btnPower);
	
}		
					

	public class Started {
		boolean b;
		public Started() {
			this.b=false;
		}
		public boolean isB() {
			return b;
		}
		public void setB(boolean b) {
			this.b = b;
		}
		
	}
	
}



