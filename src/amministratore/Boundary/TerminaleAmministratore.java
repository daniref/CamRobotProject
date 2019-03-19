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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import centraleOperativa.ProxyComunicazioneAsincrona.*;
import centraleOperativa.Control.*;

import javax.jms.JMSException;
import java.awt.Toolkit;


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
		CentraleOperativaController coc = CentraleOperativaController.getIstance();
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
		Iniziato started=new Iniziato();
		proxysetup proxyAsincrona= proxysetup.getIstance();
		//proxyAsincrona.setup();

		
		frmTerminaleAmministratore = new JFrame();
//		Image imgTerminale = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
	//	frmTerminaleAmministratore.setIconImage(Toolkit.getDefaultToolkit().getImage(eIcon(imgTerminale));

		frmTerminaleAmministratore.setIconImage(Toolkit.getDefaultToolkit().getImage(TerminaleAmministratore.class.getResource("/logo.png")));
		frmTerminaleAmministratore.setTitle("Terminale Amministratore");
		frmTerminaleAmministratore.setBounds(100, 100, 450, 300);
		frmTerminaleAmministratore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTerminaleAmministratore.getContentPane().setLayout(null);
		
		txtCentraleOperativa = new JTextField();
		txtCentraleOperativa.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtCentraleOperativa.setForeground(Color.BLUE);
		txtCentraleOperativa.setText("CENTRALE OPERATIVA");
		txtCentraleOperativa.setBounds(80, 13, 298, 61);
		frmTerminaleAmministratore.getContentPane().add(txtCentraleOperativa);
		txtCentraleOperativa.setColumns(10);
		
		JButton btnStart = new JButton("start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStart.setBounds(12, 118, 164, 54);
		btnStart.addActionListener(new ActionListener() {
		

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub			
				if(!started.isB()) {
					try {
						started.setB(true);
						proxyAsincrona.setup();	//proxy che fa in modo di ricevere le segnalazioni d'allarme
						tp1 = new TimerProxy(0,proxyAsincrona.getConsumerAllarmi());
						tp2 = new TimerProxy(1,proxyAsincrona.getConsumerKeep());
						tp1.start();
						tp2.start();
						}
					 catch (JMSException e1) {
						 e1.printStackTrace();
					 	}
					}
				else {
					JOptionPane.showMessageDialog(null,"Monitoraggio già attivo","DisplayMessage",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});
		frmTerminaleAmministratore.getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("stop");
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStop.setBounds(239, 118, 164, 54);
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(started.isB()) {
							try {
								
								tp1.stoppa();
								tp2.stoppa();
							//	proxysetup.getIstance();
								proxyAsincrona.chiudi();
								started.setB(false);
							} catch (JMSException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				else {
					JOptionPane.showMessageDialog(null,"Monitoraggio già disattivato, si prega di farlo ripartire","DisplayMessage",JOptionPane.INFORMATION_MESSAGE);
				}
				}
			
		});
		frmTerminaleAmministratore.getContentPane().add(btnStop);
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
	
	public class Iniziato {
		boolean b;
		public Iniziato() {
			this.b=false;
		}
		public synchronized boolean isB() {
			return b;
		}
		public synchronized void setB(boolean b) {
			this.b = b;
		}
		
	}
	
}



