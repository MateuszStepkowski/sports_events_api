package pl.coderslab.sports_events_api.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

@Component
public class JmsProducer {


    JmsTemplate jmsTemplate;

    @Autowired
    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    public void send(String eventJson, String destinationQueue) {

        jmsTemplate.convertAndSend(destinationQueue, eventJson);
    }
}