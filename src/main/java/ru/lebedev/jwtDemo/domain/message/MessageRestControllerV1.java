package ru.lebedev.jwtDemo.domain.message;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    //@PreAuthorize("hasAuthority('message:write')")
    public Message create(@RequestBody Message message){
        return messageService.save(message);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('message:write')")
    public Message update(@PathVariable Long id, @RequestBody Message message){
        message.setId(id);
        return messageService.save(message);
    }
}
