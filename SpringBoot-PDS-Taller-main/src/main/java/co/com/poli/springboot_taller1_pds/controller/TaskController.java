package co.com.poli.springboot_taller1_pds.controller;

import co.com.poli.springboot_taller1_pds.exceptions.UTRException;
import co.com.poli.springboot_taller1_pds.persistence.entity.Task;
import co.com.poli.springboot_taller1_pds.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/task")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> findAll() {
        return this.taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable("id") Integer id) {
        return this.taskService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        Task task1 = this.taskService.createTask(task);
        return ResponseEntity.ok(task1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        Task task1 = this.taskService.updateTask(task, id);

        if (Objects.isNull(task1)) {
            throw new UTRException("Task not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(task1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Integer id) {
        Task task = this.taskService.deleteTask(id);
        if (Objects.isNull(task)) {
            throw new UTRException("Task not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }
}
