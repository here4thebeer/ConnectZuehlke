package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.FilterSubscriptionChanged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationSimulator {
    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/api/notify")
    public String getNotification() {

        template.convertAndSend("/topic/notification", new FilterSubscriptionChanged());

        return "Notifications successfully sent to Angular !";
    }
}
