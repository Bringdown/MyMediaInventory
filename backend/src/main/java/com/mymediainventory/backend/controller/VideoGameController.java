package com.mymediainventory.backend.controller;

import com.mymediainventory.backend.model.VideoGame;
import com.mymediainventory.backend.repository.VideoGameRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping
    public VideoGame addGame(@RequestBody VideoGame videoGame) {
        return repository.save(videoGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoGame> updateGame(@PathVariable Long id, @RequestBody VideoGame videoGame) {
        VideoGame updatedGame = repository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));

        updatedGame.setName(videoGame.getName());
        updatedGame.setPlatform(videoGame.getPlatform());
        updatedGame.setReleaseYear(videoGame.getReleaseYear());
        updatedGame.setDeveloper(videoGame.getDeveloper());
        updatedGame.setPublisher(videoGame.getPublisher());

        repository.save(videoGame);

        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteGame(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Game not found"));
        }

        repository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Game deleted successfully"));
    }



}
