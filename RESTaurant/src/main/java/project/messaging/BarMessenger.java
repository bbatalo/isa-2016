package project.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import project.domain.Bartender;
import project.domain.DrinkOrder;
import project.domain.Waiter;

@Component
public class BarMessenger {

	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendRequestTo(Bartender dto, DrinkOrder ord) {
		String topic = "bars?userID=" + dto.getUserID();
		this.template.convertAndSend("/topic/" + topic, ord);
	}
	
	public void sendRequestTo(Waiter dto, DrinkOrder ord) {
		String topic = "bars?userID=" + dto.getUserID();
		this.template.convertAndSend("/topic/" + topic, ord);
	}
	
	public void sendUpdateTo(Waiter dto, DrinkOrder ord) {
		String topic = "bars?userID=" + dto.getUserID();
		this.template.convertAndSend("/topic/" + topic, ord);
	}
	
	public void sendUpdateTo(Bartender dto, DrinkOrder ord) {
		String topic = "bars?userID=" + dto.getUserID();
		this.template.convertAndSend("/topic/" + topic, ord);
	}
	
}
