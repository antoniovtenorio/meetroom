package com.dtg.meetroom.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.dtg.meetroom.exception.ResourceNotFoundException;
import com.dtg.meetroom.mappers.RoomMapper;
import com.dtg.meetroom.model.Room;
import com.dtg.meetroom.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController  {

    @Autowired
    private RoomRepository roomRepository;

    private static final RoomMapper roomMapper = RoomMapper.INSTANCE;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Integer roomId) throws ResourceNotFoundException {
        Room room = findById(roomId);
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@Valid @RequestBody Room room, @PathVariable("id") Integer roomId) throws ResourceNotFoundException {
        findById(roomId);
        Room roomFinded = roomMapper.toModel(room);
        roomFinded.setId(roomId);
        return ResponseEntity.ok().body(roomRepository.save(roomFinded)); 
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Integer roomId) throws ResourceNotFoundException {
        Room room = findById(roomId);
        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    private Room findById(Integer id) throws ResourceNotFoundException {
        return roomRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Room not found:: " + id));
    }
}
