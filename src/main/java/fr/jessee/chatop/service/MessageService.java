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

    public MessageEntity getRentalById(Long id) {
        return messageRepository.findByRentalId(id);
    }

    public MessageEntity getUserById(Long id) {
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
            existingMessage.setCreatedAt(message.getCreatedAt());
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
