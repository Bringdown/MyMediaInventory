package com.mymediainventory.backend.repository;

import com.mymediainventory.backend.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
}
