package co.com.poli.springboot_taller1_pds.persistence.repository;

import co.com.poli.springboot_taller1_pds.persistence.entity.Row;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RowRepository extends JpaRepository<Row, Integer> {
}
