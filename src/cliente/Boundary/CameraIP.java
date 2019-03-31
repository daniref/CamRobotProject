package cliente.Boundary;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.sun.glass.events.WindowEvent;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

/**
 *
 * 
 */
public class CameraIP  {
    
//public static void main(String[] args) throws Exception {

public static void windowCamera() throws Exception {
//final String urlcasaNapoli ="http://192.168.1.93:8080/video?dummy=param.mjpg";
final String urlTecchio ="http://100.102.35.143:8080/video?dummy=param.mjpg"; //<---- NON VA!
final String urlHotspotRedmi4pro ="http://192.168.43.1:8080/video?dummy=param.mjpg";
final String urlLapio ="http://192.168.0.102:8080/video?dummy=param.mjpg"; 
final String urlCamLapio="http://192.168.0.104:8081/video?dummy=param.mjpg";

	OpenCVFrameGrabber frameGrabber = new OpenCVFrameGrabber(urlCamLapio); 
    frameGrabber.setFormat("mjpeg");
    CanvasFrame canvasFrame = null;
    try {
		frameGrabber.start();
		 IplImage iPimg = frameGrabber.grab();
		    canvasFrame = new CanvasFrame("Camera");
		    canvasFrame.getCanvas().setBounds(0, 25, 432, 228);
		    canvasFrame.setCanvasSize(iPimg.width(), iPimg.height());
		   // canvasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//    canvasFrame.getContentPane().setLayout(null);
		    canvasFrame.getCanvas().setForeground(Color.WHITE);
		    canvasFrame.setCanvasSize(iPimg.width(), iPimg.height());
		  Date orario_corrente = new Date();
	
		    Date now;
		   do {
			   now=new Date();
			   canvasFrame.showImage(iPimg);
			   long i=orario_corrente.getTime()-now.getTime();
			   System.out.println("Confronto: "+i);
			   if (i<-15000)canvasFrame.setVisible(false);
		   }
		  while (canvasFrame.isVisible() && (iPimg = frameGrabber.grab()) != null); 
		  JOptionPane.showMessageDialog(canvasFrame, "Sono stati visualizzati 15 secondi di diretta - per continuare ripremere sul pulsante della vidiocamera");						
  	    	  
		    frameGrabber.stop();
		    canvasFrame.dispose();

	} catch (Exception e) {
		//quando non trasmette allora si spegne
	    frameGrabber.stop();
	    canvasFrame.dispose();	
	}

}
}