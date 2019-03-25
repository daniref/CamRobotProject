package amministratore.Boundary;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import centraleOperativa.ProxyComunicazioneAsincrona.*;

import javax.jms.JMSException;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.SystemColor;


public class TerminaleAmministratore {
	Thread t1,t2;
	
	private JFrame frmTerminaleAmministratore;
	private JTextField txtCentraleOperativa;
	static TimerProxy tp1;
	static TimerProxy tp2;
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
		final Boolean iniziato= new Boolean(false);
		Started started=new Started();
		proxysetup proxyAsincrona= proxysetup.getIstance();
		//proxyAsincrona.setup();

		
		frmTerminaleAmministratore = new JFrame();
		frmTerminaleAmministratore.getContentPane().setBackground(new Color(245, 245, 245));
//		Image imgTerminale = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
	//	frmTerminaleAmministratore.setIconImage(Toolkit.getDefaultToolkit().getImage(eIcon(imgTerminale));

		frmTerminaleAmministratore.setIconImage(Toolkit.getDefaultToolkit().getImage(TerminaleAmministratore.class.getResource("/logo.png")));
		frmTerminaleAmministratore.setTitle("Terminale Amministratore");
		frmTerminaleAmministratore.setBounds(100, 100, 450, 300);
		frmTerminaleAmministratore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTerminaleAmministratore.getContentPane().setLayout(null);
		
		txtCentraleOperativa = new JTextField();
		txtCentraleOperativa.setBackground(new Color(245, 245, 245));
		txtCentraleOperativa.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtCentraleOperativa.setForeground(Color.BLUE);
		txtCentraleOperativa.setText("CENTRALE OPERATIVA");
		txtCentraleOperativa.setBounds(29, 67, 298, 61);
		frmTerminaleAmministratore.getContentPane().add(txtCentraleOperativa);
		txtCentraleOperativa.setColumns(10);
		
		JButton btnPower = new JButton("");
		btnPower.setForeground(new Color(245, 245, 245));
		btnPower.setBackground(new Color(245, 245, 245));
		btnPower.setIcon(new ImageIcon(this.getClass().getResource("/offv.png")));

		btnPower.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPower.setBounds(339, 30, 68, 150);
		btnPower.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub		
				final Image imgOn = new ImageIcon(this.getClass().getResource("/onv.png")).getImage();
				final Image imgOff = new ImageIcon(this.getClass().getResource("/offv.png")).getImage();
				if(!started.isB()) {
					try {
						started.setB(true);
						proxyAsincrona.setup();	//proxy che fa in modo di ricevere le segnalazioni d'allarme
						tp1 = new TimerProxy(0,proxyAsincrona.getConsumerAllarmi());
						tp2 = new TimerProxy(1,proxyAsincrona.getConsumerKeep());
						tp1.start();
						tp2.start();
						btnPower.setIcon(new ImageIcon(imgOn));
						}
					 catch (JMSException e1) {
						 e1.printStackTrace();
					 	}
					}
				else {
					try {
						
						tp1.stoppa();
						tp2.stoppa();
					//	proxysetup.getIstance();
						proxyAsincrona.chiudi();
						started.setB(false);
						btnPower.setIcon(new ImageIcon(imgOff));

//						btnPower.setIcon(new ImageIcon("C:\\Users\\giann\\Desktop\\offv.png"));
//						btnPower.setIcon(new ImageIcon("/offv.png"));
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
//					JOptionPane.showMessageDialog(null,"Monitoraggio già attivo","DisplayMessage",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});
		frmTerminaleAmministratore.getContentPane().add(btnPower);
		
	}
	
	
	public static void start(proxysetup p) throws JMSException, InterruptedException{
		p.setup();	//proxy che fa in modo di ricevere le segnalazioni d'allarme
		tp1 = new TimerProxy(0,p.getConsumerAllarmi());
		tp2 = new TimerProxy(1,p.getConsumerKeep());
		tp1.start();
		tp2.start();
	}

	
	public static void stop(proxysetup p) throws JMSException {//, TimerProxy tp1, TimerProxy tp2)  throws JMSException {
		tp1.stoppa();
		tp2.stoppa();
		try
        {
            p.chiudi();
        }
        catch (JMSException e)
        {
			e.printStackTrace();

        }
		System.out.println("[stop-function](2)");
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



