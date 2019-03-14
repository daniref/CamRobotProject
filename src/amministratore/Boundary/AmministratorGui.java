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


public class AmministratorGui {
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
		proxysetup proxyAsincrona=new proxysetup();
		CentraleOperativaController coc = CentraleOperativaController.getIstance();
		System.out.println("*****CENTRALE****");
		
//funziona start & stop!

		proxyAsincrona.setup();
//		CentraleOperativaController coc = new CentraleOperativaController();
//		System.out.println("[initialize] (1) DATI proxy: "+proxyAsincrona);
//		System.out.println("[initialize] (2) DATI proxy: "+proxyAsincrona.toString());
//		System.out.println("[initialize] (3) DATI proxy connessione: "+proxyAsincrona.getConnessione());
//		System.out.println("[initialize] (4) DATI proxy: consumer Allarmi:"+proxyAsincrona.getConsumerAllarmi());
//		System.out.println("[initialize] (4) DATI proxy: consumer Keep:"+proxyAsincrona.getConsumerKeep());
//		TimerProxy tp1 = new TimerProxy(0,proxyAsincrona.getConsumerAllarmi());
//		TimerProxy tp2 = new TimerProxy(1,proxyAsincrona.getConsumerKeep());
//		tp1.start();
//		tp2.start();
//		for(int i=0;i<10;i++) {
//			System.out.println("*************");
//			Thread.sleep(1000);
//		}
		//stop(proxyAsincrona);

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AmministratorGui window = new AmministratorGui(proxyAsincrona);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */

	public AmministratorGui(proxysetup p) {//, TimerProxy timer1, TimerProxy timer2  ) {
//		initialize(p);
		initialize(p);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(proxysetup Proxy) {
		
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
					start(Proxy);
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
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
					stop(Proxy);
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
		System.out.println("Thread che schedula le segnalazioni stoppato");
		System.out.println("Thread che schedula le i Keep Alive stoppato");
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



