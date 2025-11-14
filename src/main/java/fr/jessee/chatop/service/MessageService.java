package fr.jessee.chatop.service;

import fr.jessee.chatop.dto.in.MessageCreateDTO;
import fr.jessee.chatop.dto.out.MessageDTO;
import fr.jessee.chatop.dto.out.RentalDTO;
import fr.jessee.chatop.entity.MessageEntity;
import fr.jessee.chatop.entity.RentalEntity;
import fr.jessee.chatop.entity.UserEntity;
import fr.jessee.chatop.repository.MessageRepository;
import fr.jessee.chatop.repository.RentalRepository;
import fr.jessee.chatop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public MessageDTO getById(int id) {
        MessageEntity rental = messageRepository.findById(id).orElse(null);
        if (rental != null) {
            return toDTO(rental);
        }
        return null;
    }

    public List<MessageDTO> getAllMessages() {
        List<MessageEntity> entities = messageRepository.findAll();

        List<MessageDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> {
            MessageDTO dto = new MessageDTO();
            dto.setId(entity.getId());
            dto.setTimestamp(entity.getTimestamp());
            dto.setMessage(entity.getMessage());
            dto.setRentalId((long) entity.getRentalId().getId());
            dto.setRentalId(entity.getUserId().getId());
            dtos.add(dto);
        });

        return dtos;
    }

    public List<MessageEntity> getRentalById(int id) {
         return messageRepository.findByRentalId(id);
    }

    public List<MessageEntity> getUserById(int id) {
        return messageRepository.findByUserId(id);
    }

    public MessageDTO getMessageById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return messageRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Message not found with id: " + id));
    }

    public MessageCreateDTO createMessage(MessageCreateDTO message) {
        messageRepository.save(toEntity(message));
        return message;
    }

    public MessageDTO updateMessage(Integer id, MessageDTO message) {
        MessageEntity existingMessage = messageRepository.findById(id).orElse(null);
        if (existingMessage != null) {
            existingMessage.setMessage(message.getMessage());
            existingMessage.setUpdatedAt(LocalDate.now().toString());
            messageRepository.save(existingMessage);
            return toDTO(existingMessage);
        } else {
            return null;
        }
    }

    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }

    private MessageDTO toDTO(MessageEntity entity) {
        MessageDTO dto = new MessageDTO();
        dto.setId(entity.getId());
        dto.setMessage(entity.getMessage());
        dto.setTimestamp(entity.getTimestamp());
        dto.setRentalId((long) entity.getRentalId().getId());
        dto.setUserId(entity.getUserId().getId());
        return dto;
    }

    public MessageEntity toEntity(MessageCreateDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("Le message ne doit pas être null");
        }
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("userId ne doit pas être null");
        }
        if (dto.getRentalId() == null) {
            throw new IllegalArgumentException("rentalId ne doit pas être null");
        }

        MessageEntity entity = new MessageEntity();
        entity.setMessage(dto.getMessage());
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        RentalEntity rental = rentalRepository.findById(dto.getRentalId())
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        entity.setUserId(user);
        entity.setRentalId(rental);
        entity.setUpdatedAt(LocalDate.now().toString());
        entity.setCreatedAt(LocalDate.now().toString());
        entity.setTimestamp(Instant.now().toString());
        return entity;
    }
}
