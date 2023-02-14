package org.ebanda.service;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Log4j
public class MainService {
    private final ProducerService producerService;

    public MainService(ProducerService producerService) {
        this.producerService = producerService;
    }

    public void processMessageUpdate(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Обработано");
        producerService.produceAnswerMessage(sendMessage);
    }
}
