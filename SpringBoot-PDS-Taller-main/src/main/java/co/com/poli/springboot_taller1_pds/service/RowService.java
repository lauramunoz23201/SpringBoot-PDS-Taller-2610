package co.com.poli.springboot_taller1_pds.service;

import co.com.poli.springboot_taller1_pds.persistence.entity.Row;
import co.com.poli.springboot_taller1_pds.persistence.repository.RowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RowService {
    private final RowRepository rowRepository;

    public List<Row> findAll(){
        return this.rowRepository.findAll();
    }

    public Row findById(Integer id){
        Optional<Row> row = this.rowRepository.findById(id);
        return row.orElse(null);
    }

    public Row createRow(Row row){
        return this.rowRepository.save(row);
    }

    @Transactional
    public Row updateRow(Row row, Integer id){
        Row row1 = findById(id);
        if(row1 != null){
            row1.setDuration(row.getDuration());
            row1.setTask(row.getTask());
            this.rowRepository.save(row1);
        }
        return row1;
    }

    @Transactional
    public Row deleteRow(Integer id){
        Row row = findById(id);
        if(row != null){
            this.rowRepository.delete(row);
        }
        return row;
    }
}
