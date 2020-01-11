package hospital.management.Hospital.controller;

import hospital.management.Hospital.dto.RoomDto;
import hospital.management.Hospital.model.Room;
import hospital.management.Hospital.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/api/rooms")
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/api/rooms/{id}")
    public RoomDto getRoomById(@PathVariable("id") Integer id) {
        return roomService.getRoomById(id);
    }

    @PostMapping("/api/rooms")
    public RoomDto createRoom(@RequestBody RoomDto data) {
        return roomService.createRoom(data);
    }

    @PutMapping("/api/rooms/{id}")
    public RoomDto updateRoom(@PathVariable("id") Integer id, @RequestBody RoomDto data) {
        return roomService.updateRoom(id, data);
    }

    @DeleteMapping("/api/rooms/{id}")
    public boolean deleteRoom(@PathVariable("id") Integer id) {
        return roomService.deleteRoom(id);
    }
}
