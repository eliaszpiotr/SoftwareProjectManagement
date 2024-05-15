package com.uep.wap.service;

import com.uep.wap.dto.MessageDTO;
import com.uep.wap.model.Message;
import com.uep.wap.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    // Convert Message to MessageDTO
    private MessageDTO convertToDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessageId(message.getMessageId());
        messageDTO.setSenderId(message.getSender().getUserId());
        messageDTO.setRecipientId(message.getRecipient().getUserId());
        messageDTO.setContent(message.getContent());
        messageDTO.setTimestamp(message.getSentAt().toString());
        return messageDTO;
    }

    // Convert MessageDTO to Message
    private Message convertToEntity(MessageDTO messageDTO) {
        Message message = new Message();
        // Note: You need to fetch the User entities and set them as the sender and recipient
        // message.setSender(sender);
        // message.setRecipient(recipient);
        message.setContent(messageDTO.getContent());
        // Note: You need to convert the timestamp string to a Date or LocalDateTime object
        // message.setTimestamp(timestamp);
        return message;
    }

    // Creating a new message
    @Transactional
    public MessageDTO createMessage(MessageDTO messageDTO) {
        Message newMessage = convertToEntity(messageDTO);
        return convertToDTO(messageRepository.save(newMessage));
    }

    // Getting a message by ID
    public MessageDTO getMessageById(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message with ID " + messageId + " not found"));
        return convertToDTO(message);
    }

    // Deleting a message
    @Transactional
    public void deleteMessage(Long messageId) {
        if (!messageRepository.existsById(messageId)) {
            throw new IllegalArgumentException("Message with ID " + messageId + " not found");
        }
        messageRepository.deleteById(messageId);
    }

    // Getting all messages
    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}