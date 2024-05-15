package com.uep.wap.controller;

import com.uep.wap.dto.MessageDTO;
import com.uep.wap.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getMessage(@PathVariable Long id) {
        MessageDTO message = messageService.getMessageById(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAllMessages() {
        List<MessageDTO> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageDTO message) {
        MessageDTO createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok(createdMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }
}