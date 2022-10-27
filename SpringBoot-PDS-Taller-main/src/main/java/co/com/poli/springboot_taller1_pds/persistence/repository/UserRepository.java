package co.com.poli.springboot_taller1_pds.persistence.repository;

import co.com.poli.springboot_taller1_pds.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query(value = "DELETE FROM ROWS WHERE user_id=:idUser", nativeQuery = true)
    void deleteRowByUser(@Param("idUser") Integer idUser);
}
