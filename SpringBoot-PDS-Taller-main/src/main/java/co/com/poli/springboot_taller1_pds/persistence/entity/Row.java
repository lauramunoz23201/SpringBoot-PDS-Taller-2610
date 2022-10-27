package co.com.poli.springboot_taller1_pds.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "rows")
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;
    @Column(name = "duration")
    private Integer duration;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return id.equals(row.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
