package com.dtg.meetroom.repository;

import com.dtg.meetroom.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
