package centralinaRobot.Control;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import amministratore.Boundary.TerminaleAmministratore;
import centralinaRobot.Sense.SensoreInterface;



public class Display {

	public JFrame frame;
	private JLabel lblDataora;

    private ArrayList<JLabel> VettLabelSens = new ArrayList<JLabel>(); 
    private ArrayList<JLabel> VettValoreSens = new ArrayList<JLabel>(); 
    private ArrayList<JLabel> VettIDSens = new ArrayList<JLabel>(); 
    private ArrayList<JLabel> VettSoglieSens = new ArrayList<JLabel>(); 
    private JLabel Jlfunzionamento;
    private JLabel lblImmFunzionamento;
	private final Image imgFunctError = new ImageIcon(this.getClass().getResource("/incorrect.png")).getImage();
	private final Image imgFunctOK = new ImageIcon(this.getClass().getResource("/correct.png")).getImage();

    	
    //private static DisplaySingl DisplayInstance;

   public Display (ArrayList<SensoreInterface> Sens, String id_r, ArrayList <Float> ss){
    	initialize(Sens,id_r,ss);
    	clock();
    }  

   
    //funzione in cui si inizializza i valori statici e si imposta la giusta grandezza del display!
    private void initialize(ArrayList<SensoreInterface> Sens, String id_robot, ArrayList<Float> soglie){
		frame = new JFrame("DISPLAY - Centralina");

		frame.setBounds(100, 100, 688, 333+70*Sens.size());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(TerminaleAmministratore.class.getResource("/logo.png")));

		//logo:
		JLabel lblLabelimg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logo2.png")).getImage();
		lblLabelimg.setIcon(new ImageIcon(img));
		lblLabelimg.setBounds(35, 13, 144, 136);
		frame.getContentPane().add(lblLabelimg);		
		
		//scritta sotto il logo
		JLabel lblRobot = new JLabel("MY ROBOT");
		lblRobot.setFont(new Font("MV Boli", Font.BOLD, 15));
		lblRobot.setBounds(59, 130, 134, 53);
		frame.getContentPane().add(lblRobot);
		
		//si mostra l'ID del Robot
		JLabel Jlabel_lD_Robot = new JLabel(id_robot);
		Jlabel_lD_Robot.setFont(new Font("Tahoma", Font.BOLD, 27));
		Jlabel_lD_Robot.setBounds(215, 69, 150, 66);
		frame.getContentPane().add(Jlabel_lD_Robot);

		JLabel lblID2 = new JLabel("ID Sensore");
		lblID2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblID2.setBounds(340, 230, 94, 22);
		frame.getContentPane().add(lblID2);
		
		//mostra 'Valore'
		JLabel lblValore = new JLabel("Valore");
		lblValore.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblValore.setBounds(450, 230, 60, 22);
		frame.getContentPane().add(lblValore);
		
		//mostra 'Soglia'
		JLabel lblSoglia = new JLabel("Soglia");
		lblSoglia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSoglia.setBounds(550, 230, 94, 22);
		frame.getContentPane().add(lblSoglia);

		//aggiungo al frame tante label quanti sono i sensori - in cui ho inizializzato i 
		JLabel jl1, jl2, jl3,jl4;
		for(int i=0; i< Sens.size();i++) {
			//Tipo, ID
			jl1 = new JLabel(""+Sens.get(i).getTipoEsteso()); //per Tipo  e ID
			jl1.setFont(new Font("Tahoma", Font.BOLD, 18));
			jl1.setBounds(34, 253+50*i, 300, 35);
			frame.getContentPane().add(jl1);
			VettLabelSens.add(jl1);

			jl2 = new JLabel("val "+ i); //valore misurato
			jl2.setFont(new Font("Tahoma", Font.BOLD, 18));
			jl2.setBounds(450, 253+50*i, 80, 35);
			frame.getContentPane().add(jl2);
			VettValoreSens.add(jl2);

			
			jl3 = new JLabel("("+Sens.get(i).getID()+")"); //ID misurato
			jl3.setFont(new Font("Tahoma", Font.BOLD, 18));
			jl3.setBounds(330, 253+50*i,100, 35);
			frame.getContentPane().add(jl3);
			VettIDSens.add(jl3);
			
			
			jl4 = new JLabel(String.format ("%.2f",soglie.get(i))); //soglia
			jl4.setFont(new Font("Tahoma", Font.BOLD, 18));
			jl4.setBounds(545, 253+50*i, 150, 35);
			frame.getContentPane().add(jl4);
			VettSoglieSens.add(jl4);
		}	
	
		//per funzionamento - immagini e valore
		Jlfunzionamento = new JLabel("OK");
		Jlfunzionamento.setFont(new Font("Tahoma", Font.BOLD, 25));
		Jlfunzionamento.setBounds(410, 80, 200, 124);
		frame.getContentPane().add(Jlfunzionamento);
				

		lblImmFunzionamento = new JLabel("");
		lblImmFunzionamento.setBounds(425, 11, 110, 124);
		lblImmFunzionamento.setIcon(new ImageIcon(imgFunctOK));
		lblImmFunzionamento.setVisible(true);
		frame.getContentPane().add(lblImmFunzionamento);		
		
//		JLabel lblFunzionamentoOK = new JLabel("");
//		lblFunzionamentoOK.setBounds(500, 11, 110, 124);
//	
//		lblFunzionamentoOK.setIcon(new ImageIcon(imgFunctOK));
//		lblFunzionamentoOK.setVisible(true);
//		frame.getContentPane().add(lblFunzionamentoOK);

		
//		JLabel lblFunzionamentoError = new JLabel("");
//		lblFunzionamentoError.setBounds(370, 11, 110, 124);
//		Image imgFunctError = new ImageIcon(this.getClass().getResource("/incorrect.png")).getImage();
//		lblFunzionamentoError.setIcon(new ImageIcon(imgFunctError));
//		lblFunzionamentoError.setVisible(true);
//		frame.getContentPane().add(lblFunzionamentoError);
		
		lblDataora = new JLabel("");
		lblDataora.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblDataora.setBounds(84, 177, 485, 41);
		frame.getContentPane().add(lblDataora);
		

	}



//public void showsoglie(ArrayList <Integer> soglie) {
//	for(int i=0;i<soglie.size();i++)	{
//		VettSoglieSens.get(i).setText(""+soglie.get(i));	
//	}
//}

//serve per aggiornare la JLabel associata all'iesimo sensore
public void showval(int index, float val) {

		VettValoreSens.get(index).setText(String.format ("%.2f",val));
		System.out.println("Ho aggornato il sensore "+index+" con il valore: "+String.format ("%.1f",val));
}

//stringa
public void showval(int index, String val) {
	VettValoreSens.get(index).setText(""+val);
	System.out.println("Ho aggornato il sensore "+index+" con il valore"+val);
}


public void showFunzionamento(boolean b) {
	if(b)
		{
		Jlfunzionamento.setText("In Funzione");
		lblImmFunzionamento.setIcon(new ImageIcon(imgFunctOK));

		}
	else {
		Jlfunzionamento.setText("    Error   ");
		lblImmFunzionamento.setIcon(new ImageIcon(imgFunctError));
	}
}


//aggiornamento orologio automatico
public void clock() {
	Thread clock=new Thread()
	{
		public void run() {
			for(;;) {
				try {
					String dataora=(new SimpleDateFormat( "dd/MM/yyyy - HH:mm:ss")).format( Calendar.getInstance().getTime());
					lblDataora.setText(dataora);
					sleep(1000);
					}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	};
	clock.start();
}
}
