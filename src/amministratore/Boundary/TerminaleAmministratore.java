package amministratore.Boundary;


import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import centraleOperativa.ProxyComunicazioneAsincrona.*;
import centraleOperativa.Control.*;

import javax.jms.JMSException;


public class TerminaleAmministratore {
	Thread t1,t2;
	
	private JFrame frame;
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
					window.frame.setVisible(true);
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

		proxysetup proxyAsincrona= proxysetup.getIstance();
		//proxyAsincrona.setup();

		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtCentraleOperativa = new JTextField();
		txtCentraleOperativa.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtCentraleOperativa.setForeground(Color.BLUE);
		txtCentraleOperativa.setText("CENTRALE OPERATIVA");
		txtCentraleOperativa.setBounds(80, 13, 298, 61);
		frame.getContentPane().add(txtCentraleOperativa);
		txtCentraleOperativa.setColumns(10);
		
		JButton btnStart = new JButton("start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStart.setBounds(12, 118, 164, 54);
		btnStart.addActionListener(new ActionListener() {
		

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub			
				try {
				//	proxysetup.getIstance();
					proxyAsincrona.setup();	//proxy che fa in modo di ricevere le segnalazioni d'allarme
					tp1 = new TimerProxy(0,proxyAsincrona.getConsumerAllarmi());
					tp2 = new TimerProxy(1,proxyAsincrona.getConsumerKeep());
					tp1.start();
					tp2.start();
					
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		frame.getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("stop");
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStop.setBounds(239, 118, 164, 54);
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					tp1.stoppa();
					tp2.stoppa();
				//	proxysetup.getIstance();
					proxyAsincrona.chiudi();
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		frame.getContentPane().add(btnStop);
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
	
	
}



