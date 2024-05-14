package com.uep.wap.service;

import com.uep.wap.model.Message;
import com.uep.wap.model.User;
import com.uep.wap.repository.MessageRepository;
import com.uep.wap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository; // Dodano repozytorium użytkowników

    // Wysyłanie nowej wiadomości
    @Transactional
    public Message sendMessage(Long senderId, Long recipientId, String subject, String content) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Sender with ID " + senderId + " not found"));
        User recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new IllegalArgumentException("Recipient with ID " + recipientId + " not found"));

        Message message = new Message(sender, recipient, subject, content, new Date(), false);
        return messageRepository.save(message);
    }

    // Pobieranie wiadomości po ID
    public Optional<Message> getMessageById(Long messageId) {
        return messageRepository.findById(messageId);
    }

    // Aktualizacja statusu przeczytania wiadomości
    @Transactional
    public Message markMessageAsRead(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message with ID " + messageId + " not found"));
        message.setReadStatus(true);
        return messageRepository.save(message);
    }

    // Pobieranie wszystkich wiadomości wysłanych przez danego użytkownika
    public List<Message> getMessagesBySenderId(Long senderId) {
        return messageRepository.findBySenderId(senderId);
    }

    // Pobieranie wszystkich wiadomości otrzymanych przez danego użytkownika
    public List<Message> getMessagesByRecipientId(Long recipientId) {
        return messageRepository.findByRecipientId(recipientId);
    }

    // Usuwanie wiadomości
    @Transactional
    public void deleteMessage(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message with ID " + messageId + " not found"));
        messageRepository.delete(message);
    }
}
