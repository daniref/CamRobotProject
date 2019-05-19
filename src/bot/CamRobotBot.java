package bot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class CamRobotBot extends TelegramLongPollingBot {
	
	private static String to_start="/start";
    @Override
    public void onUpdateReceived(Update update) {


        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
        	
            //---------------------Set variables
            String user_first_name = update.getMessage().getChat().getFirstName();
            String user_last_name = update.getMessage().getChat().getLastName();
            String user_username = update.getMessage().getChat().getUserName();
            long user_id = update.getMessage().getChat().getId();
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
     
            
            //--------Ricerca se l'utente è già registrato
            manager_utenti mu = manager_utenti.getInstance();
            utente search_utente = new utente();
            search_utente=mu.getUtenteById(user_id);
            boolean registrato=false;
            if(search_utente.getUser_id()==user_id) {
        	    registrato=true;
            }
            else {
        	    registrato=false;
            }

            ////////////////////////////////
            String answer="";			//////----vedere



	    if(message_text.equals(to_start)) { //Controlla se il bot è stato appena avviato
		    
		    if(registrato) {
        		    answer="Benvenuto in CamRobot Service "+user_first_name+".\n"
        		    		+ "Attraverso questo Bot è possibile ricevere "
        		    		+ "le segnalazioni di allarme riguardanti la tua abitazione.\n"
        		            	+ "Grazie per aver scelto noi per proteggere la tua casa!\n"
        		            	+ "Always the best! D&G.";
			    SendMessage message = new SendMessage() // Create a message object object
        	                            .setChatId(chat_id)
        	                            .setText(answer);
        		    try {
				execute(message);
        		    } catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
        		    } // Sending our message object to user

		    }
		    else {
			    answer="Benvenuto in CamRobot Service!\n"
				    + "Attraverso questo Bot è possibile ricevere "
				    + "le segnalazioni di allarme riguardanti la tua abitazione.\n"
				    + "Sfortunatamente il tuo account non risulta ancora registrato.\n"
				    + "Per registrarsi è davvero semplicissimo, ti basta cliccare il pulsante sotto: REGISTRAMI.\n"
				    + "Grazie per aver scelto noi per proteggere la tua casa!\n"
				    + "Always the best! D&G.";       			    
			    
			    SendMessage message = new SendMessage()
	        		            .setChatId(chat_id)
	        		            .setText(answer);

	        		    // create keyboard
	        		    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
	        		    message.setReplyMarkup(replyKeyboardMarkup);
	        		    replyKeyboardMarkup.setSelective(true);
	        		    replyKeyboardMarkup.setResizeKeyboard(true);
	        		    replyKeyboardMarkup.setOneTimeKeyboard(true);

	        		    // new list
	        		    List<KeyboardRow> keyboard = new ArrayList<>();

	        		    // first keyboard line
	        		    KeyboardRow keyboardFirstRow = new KeyboardRow();
	        		    KeyboardButton keyboardButton = new KeyboardButton();
	        		    keyboardButton.setText("REGISTRA >").setRequestContact(true);
	        		    keyboardFirstRow.add(keyboardButton);

	        		    // add array to list
	        		    keyboard.add(keyboardFirstRow);

	        		    // add list to our keyboard
	        		    replyKeyboardMarkup.setKeyboard(keyboard);
	        		    try {
					execute(message);
	        		    } catch (TelegramApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	        		    } // Sending our message object to user
	
		    }

	    }
            //log a console
            log(user_first_name, user_last_name, Long.toString(user_id), chat_id, message_text, answer);
        }
        
        
        if (update.getMessage().getContact()!=null){
        	System.out.println("#############");
        	System.out.println(update.getMessage().getContact());
        	System.out.println("#############");
    	    
	    	manager_utenti mu = manager_utenti.getInstance();
	        utente new_utente = new utente();
	        new_utente.setFirst_name(update.getMessage().getContact().getFirstName());
	        new_utente.setLast_name(update.getMessage().getContact().getLastName());
	        new_utente.setUser_id(update.getMessage().getContact().getUserID());
	        new_utente.setPhone(update.getMessage().getContact().getPhoneNumber());
	        mu.add_utente(new_utente);
	        System.out.println("Nuovo utente aggiunto con successo!");
	        
	        
        }
        
    }
    


    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "CamRobotBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "886559313:AAH0VVFLQRfzYMkigIxv25JbBYMwOqsTFAA";
    }
    private void log(String first_name, String last_name, String user_id, long chat_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Chat id:"+chat_id);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }
}





