package fr.jessee.chatop.service;

import fr.jessee.chatop.entity.MessageEntity;
import fr.jessee.chatop.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageEntity> getAllMessages() {
        return messageRepository.findAll();
    }

    public MessageEntity getRentalById(int id) {
        return messageRepository.findByRentalId(id);
    }

    public MessageEntity getUserById(int id) {
        return messageRepository.findByUserId(id);
    }

    public MessageEntity getMessageById(Integer id) {
        return messageRepository.findById(id).orElse(null);
    }

    public MessageEntity createMessage(MessageEntity message) {
        return messageRepository.save(message);
    }

    public MessageEntity updateMessage(Integer id, MessageEntity message) {
        MessageEntity existingMessage = messageRepository.findById(id).orElse(null);
        if (existingMessage != null) {
            existingMessage.setMessage(message.getMessage());
            existingMessage.setUpdatedAt(message.getUpdatedAt());
            return messageRepository.save(existingMessage);
        } else {
            return null;
        }
    }

    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }
}
