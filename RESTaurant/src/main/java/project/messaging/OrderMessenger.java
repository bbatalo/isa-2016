package project.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import project.domain.RestOrder;
import project.domain.Waiter;

@Component
public class OrderMessenger {

	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendRequestTo(Waiter dto, RestOrder ord) {
		String topic = "ordersw?userID=" + dto.getUserID();
		this.template.convertAndSend("/topic/" + topic, ord);
	}
	
	public void sendUpdateTo(Waiter dto, RestOrder ord) {
		String topic = "ordersw?userID=" + dto.getUserID();
		this.template.convertAndSend("/topic/" + topic, ord);
	}
	
	public void sendRequestTo(RestOrder dto, Waiter wtr) {
		String topic = "orders?userID=" + dto.getId();
		this.template.convertAndSend("/topic/" + topic, wtr);
	}
	
	public void sendUpdateTo(RestOrder dto, Waiter wtr) {
		String topic = "orders?userID=" + dto.getId();
		this.template.convertAndSend("/topic/" + topic, wtr);
	}
}
