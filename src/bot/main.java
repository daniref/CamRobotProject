package bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		
		
		try {
			botsApi.registerBot(new CamRobotBot());
			System.out.println("Bot Avviato!");
			
		}
		catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
