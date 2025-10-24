package fr.jessee.chatop.controller;

import fr.jessee.chatop.entity.MessageEntity;
import fr.jessee.chatop.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    // Route GET /api/messages
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageEntity> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public MessageEntity getMessageById(@PathVariable Integer id) {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public MessageEntity createMessage(@RequestBody MessageEntity message) {
        return messageService.createMessage(message);
    }

    @PutMapping("/{id}")
    public MessageEntity updateMessage(@PathVariable Integer id, @RequestBody MessageEntity message) {
        return messageService.updateMessage(id, message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Integer id) {
        messageService.deleteMessage(id);
    }
}
