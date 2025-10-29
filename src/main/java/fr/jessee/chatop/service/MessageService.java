package fr.jessee.chatop.service;

import fr.jessee.chatop.dto.in.MessageCreateDTO;
import fr.jessee.chatop.dto.in.RentalCreateDTO;
import fr.jessee.chatop.dto.out.MessageDTO;
import fr.jessee.chatop.dto.out.RentalDTO;
import fr.jessee.chatop.entity.MessageEntity;
import fr.jessee.chatop.entity.RentalEntity;
import fr.jessee.chatop.entity.UserEntity;
import fr.jessee.chatop.repository.MessageRepository;
import fr.jessee.chatop.repository.RentalRepository;
import fr.jessee.chatop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, RentalRepository rentalRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
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

    public MessageCreateDTO createMessage(MessageEntity message) {
        return messageRepository.save(toEntity(message));
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

    private MessageDTO toDTO(MessageDTO entity) {
        MessageDTO dto = new MessageDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setReceiverId(entity.getReceiverId());
        dto.setSenderId(entity.getSenderId());
        return dto;
    }

    public MessageEntity toEntity(MessageCreateDTO dto) {
        MessageEntity entity = new MessageEntity();
        entity.setMessage(dto.getContent());

        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        RentalEntity rental = rentalRepository.findById(dto.getRentalId())
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        entity.setUserId(user);
        entity.setRentalId(rental);
        entity.setTimestamp(dto.getTimestamp());
        return entity;
    }
}
