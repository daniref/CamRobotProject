package centralinaRobot.proxyComunicazioneAsincrona;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class CentraleOperativaProxy {
/****/
	// URL del JMS server	
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; //http://localhost:8161
    //nome della queue
    private static String queueAllarmi = "Queue - Segnalazioni d'Allarme";
    private static String queueKeep = "Queue - KeepAlive";

    public void GeneraKeep(String idrobot) throws JMSException{
				String dataora=(new SimpleDateFormat( "dd:MM:yyyy-HH:mm:ss")).format( Calendar.getInstance().getTime());
				String msg_to_send=(idrobot+";"+dataora+".");
		    	// Creazione di una connessione JMS con il server, reperibile all'indirizzo 'url'
		    	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		    	Connection connection = connectionFactory.createConnection();
		    	connection.start();
		    	// Creazione di una sessione per l'invio dei messaggi 
		    	Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		    	// Destination rappresenta la coda in cui vengono depositati gli eventi sul server JMS
		    	Destination destination = session.createQueue(queueKeep);
		    	// Creazione del MessageProducer per l'invio di messaggi alla Centrale Operativa 
		    	MessageProducer producer = session.createProducer(destination);
		    	// Creazione del Messaggio di allarme 
		    	TextMessage message = session.createTextMessage(msg_to_send);
		    	// Invio del Messaggio
		    	producer.send(message);
		    	System.out.println("[DEBUG][Proxy] - messaggio KEEP inviato: {"+ msg_to_send+"}");
		    	connection.close();
		    }
    
    public void GeneraAllarme(String idrobot, String idsensore, float val) throws JMSException{
				String dataora=(new SimpleDateFormat( "dd:MM:yyyy-HH:mm:ss")).format( Calendar.getInstance().getTime());
				String msg_to_send=(idrobot+";"+idsensore+";"+String.format("%.2f", val).replace(",", ".")+";"+dataora+".");
				
		    	// Creazione di una connessione JMS con il server, reperibile all'indirizzo 'url'
		    	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		    	Connection connection = connectionFactory.createConnection();
		    	connection.start();
		    	// Creazione di una sessione per l'invio dei messaggi 
		    	Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		    	// Destination rappresenta la coda in cui vengono depositati gli eventi sul server JMS
		    	Destination destination = session.createQueue(queueAllarmi);
		    	// Creazione del MessageProducer per l'invio di messaggi alla Centrale Operativa 
		    	MessageProducer producer = session.createProducer(destination);
		    	// Creazione del Messaggio di allarme 
		    	TextMessage message = session.createTextMessage(msg_to_send);
		    	// Invio del Messaggio
		    	producer.send(message);
		    	System.out.println("[DEBUG][Proxy] - messaggio SEGNALAZIONE inviato: {"+ msg_to_send+"}");
		    	connection.close();
		    }
	}



