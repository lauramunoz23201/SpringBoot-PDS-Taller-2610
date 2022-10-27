package co.com.poli.springboot_taller1_pds.persistence.repository;

import co.com.poli.springboot_taller1_pds.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
