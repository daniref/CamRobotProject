package centraleOperativa.ProxyComunicazioneAsincrona;


//import java.util.Date;
//import java.text.SimpleDateFormat;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class proxysetup {
	// URL del JMS server	
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;//    private static String url = "failover://tcp://127.0.0.1:61616";
	private static String queueAllarmi = "Queue - Segnalazioni d'Allarme";
    private static String queueKeep = "Queue - KeepAlive";
	private MessageConsumer consumerAllarmi,consumerKeep; 
	private Connection connessione;


	public proxysetup() {}
	
	public void setup() throws JMSException {
      	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
    	connessione = connectionFactory.createConnection();
    	connessione.start();
    	Session session = connessione.createSession(false,Session.AUTO_ACKNOWLEDGE);

    	// Destination rappresenta la coda in cui vengono depositati gli eventi sul server JMS
    	Destination dAllarmi = session.createQueue(queueAllarmi);
    	Destination dKeep = session.createQueue(queueKeep);
    	consumerAllarmi = session.createConsumer(dAllarmi);
    	consumerKeep = session.createConsumer(dKeep);
//    	System.out.println("Consumer ALLARMI : "+consumerAllarmi);
//    	System.out.println("Consumer KEEP"+consumerKeep);
    }
	
	public void chiudi() throws JMSException {
		this.getConnessione().close();
		System.out.println("********************CONNESSIONE CHIUSA*********************");
	}

	public MessageConsumer getConsumerAllarmi() {
		return consumerAllarmi;
	}

	public MessageConsumer getConsumerKeep() {
		return consumerKeep;
	}

	public Connection getConnessione() {
		return connessione;
	}

    


}