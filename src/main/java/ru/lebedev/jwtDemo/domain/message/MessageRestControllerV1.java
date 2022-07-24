package ru.lebedev.jwtDemo.domain.message;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageRestControllerV1 {

    private final MessageService messageService;

    @GetMapping
    public List<Message> all(){
        return messageService.findAll();
    }

    @GetMapping("/{id}")
    public Message one(@PathVariable Long id){
        return messageService.findById(id);
    }

    @PostMapping
    public Message one(@RequestBody Message message){
        return messageService.save(message);
    }
}
