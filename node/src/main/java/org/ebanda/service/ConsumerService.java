package org.ebanda.service;


import lombok.extern.log4j.Log4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.ebanda.model.RabbitQueue.MESSAGE_UPDATE;

@Service
@Log4j
public class ConsumerService {
    private final MainService mainService;

    public ConsumerService(MainService mainService) {
        this.mainService = mainService;
    }

    @RabbitListener(queues = MESSAGE_UPDATE)
    public void consumeMessageUpdates(Update update) {
        log.info("Text message is received: '" +
                update.getMessage().getText() +
                "', from: " +
                update.getMessage().getChatId());
        mainService.processMessageUpdate(update);
    }
}
