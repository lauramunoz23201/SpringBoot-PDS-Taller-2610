package co.com.poli.springboot_taller1_pds.controller;

import co.com.poli.springboot_taller1_pds.exceptions.UTRException;
import co.com.poli.springboot_taller1_pds.persistence.entity.User;
import co.com.poli.springboot_taller1_pds.service.UserService;
import co.com.poli.springboot_taller1_pds.service.dto.UserInDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public User findByID(@PathVariable("id") Integer id) {
        return this.userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserInDTO userInDTO) {
        User user = this.userService.createUser(userInDTO);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = user.getBirtDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 18) {
            throw new UTRException("Minor user", HttpStatus.BAD_REQUEST);
        }
        User user1 = this.userService.updateUser(user, id);

        if (Objects.isNull(user1)) {
            throw new UTRException("User not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Integer id) {
        User user = this.userService.deleteUser(id);
        if (Objects.isNull(user)) {
            throw new UTRException("User not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }
}
