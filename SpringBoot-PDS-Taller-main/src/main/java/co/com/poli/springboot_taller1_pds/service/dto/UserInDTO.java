package co.com.poli.springboot_taller1_pds.service.dto;

import co.com.poli.springboot_taller1_pds.persistence.entity.Dependency;
import co.com.poli.springboot_taller1_pds.persistence.entity.Profile;
import co.com.poli.springboot_taller1_pds.persistence.entity.Row;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserInDTO {
    private Integer id;
    private Date birtDate;
    private Dependency dependency;
    private List<Profile> perfil;
    private List<Row> rows;
}
