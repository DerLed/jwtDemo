package ru.lebedev.jwtDemo.domain.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public Message findById(Long id){
        return messageRepository.findById(id).orElseThrow();
    }

    public Message save(Message message){
        return messageRepository.save(message);
    }
}
