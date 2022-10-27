package co.com.poli.springboot_taller1_pds.service;

import co.com.poli.springboot_taller1_pds.persistence.entity.Task;
import co.com.poli.springboot_taller1_pds.persistence.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAll(){
        return this.taskRepository.findAll();
    }

    public Task findById(Integer id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElse(null);
    }

    public Task createTask(Task task){
        return this.taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Task task, Integer id){
        Task task1 = findById(id);
        if(task1 != null){
            task1.setNombre(task.getNombre());
            this.taskRepository.save(task1);
        }
        return task1;
    }

    @Transactional
    public Task deleteTask(Integer id){
        Task task = findById(id);
        if(task != null){
            this.taskRepository.delete(task);
        }
        return task;
    }
}
