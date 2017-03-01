package project.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import project.domain.Chef;
import project.domain.DishOrder;
import project.domain.RestOrder;
import project.domain.Waiter;

@Component
public class CookMessenger {

	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendRequestTo(Chef dto, DishOrder ord) {
		String topic = "cooks?userID=" + dto.getUserID();
		this.template.convertAndSend("/topic/" + topic, ord);
	}
	
	public void sendRequestTo(Waiter dto, DishOrder ord) {
		String topic = "cooks?userID=" + dto.getUserID();
		this.template.convertAndSend("/topic/" + topic, ord);
	}
	
	public void sendUpdateTo(Waiter dto, DishOrder ord) {
		String topic = "cooks?userID=" + dto.getUserID();
		this.template.convertAndSend("/topic/" + topic, ord);
	}
	
	public void sendUpdateTo(Chef dto, DishOrder ord) {
		String topic = "cooks?userID=" + dto.getUserID();
		this.template.convertAndSend("/topic/" + topic, ord);
	}
	
}
