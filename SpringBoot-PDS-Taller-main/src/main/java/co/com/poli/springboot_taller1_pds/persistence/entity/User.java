package co.com.poli.springboot_taller1_pds.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "birth_date")
    private Date birtDate;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "dependency")
    private Dependency dependency;
    @ElementCollection(targetClass = Profile.class)
    @JoinTable(name = "profiles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "profile", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Profile> profile;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Row> rows;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
