package com.mymediainventory.backend.controller;

import com.mymediainventory.backend.model.VideoGame;
import com.mymediainventory.backend.repository.VideoGameRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class VideoGameController {

    private final VideoGameRepository repository;

    public VideoGameController(VideoGameRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<VideoGame> getAllGames() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public VideoGame getGame(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
}
