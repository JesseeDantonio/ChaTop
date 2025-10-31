package fr.jessee.chatop.controller;

import fr.jessee.chatop.dto.in.MessageCreateDTO;
import fr.jessee.chatop.dto.out.MessageDTO;
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
    public List<MessageDTO> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public MessageDTO getMessageById(@PathVariable Integer id) {
        return messageService.getMessageById(id);
    }

    @GetMapping("/user/{id}")
    public List<MessageEntity> getUserById(@PathVariable int id) {
        return messageService.getUserById(id);
    }

    @GetMapping("/rental/{id}")
    public List<MessageEntity> getRentalById(@PathVariable int id) {
        return messageService.getRentalById(id);
    }


    @PostMapping
    public MessageCreateDTO createMessage(@RequestBody MessageCreateDTO message) {
        return messageService.createMessage(message);
    }

    @PutMapping("/{id}")
    public MessageDTO updateMessage(@PathVariable Integer id, @RequestBody MessageDTO message) {
        messageService.updateMessage(id, message);
        return message;
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Integer id) {
        messageService.deleteMessage(id);
    }
}
