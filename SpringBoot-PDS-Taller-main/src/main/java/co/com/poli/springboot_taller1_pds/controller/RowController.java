package co.com.poli.springboot_taller1_pds.controller;

import co.com.poli.springboot_taller1_pds.exceptions.UTRException;
import co.com.poli.springboot_taller1_pds.persistence.entity.Row;
import co.com.poli.springboot_taller1_pds.service.RowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/row")
public class RowController {
    private final RowService rowService;

    @GetMapping
    public List<Row> findAll() {
        return rowService.findAll();
    }

    @GetMapping("/{id}")
    public Row findById(@PathVariable("id") Integer id) {
        return rowService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createRow(@RequestBody Row row) {
        int duration = row.getDuration();
        if(duration < 1 || duration > 60){
            throw  new UTRException("The duration should be in the range of 1-60", HttpStatus.BAD_REQUEST);
        }
        Row row1 = rowService.createRow(row);
        return ResponseEntity.ok(row1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFila(@PathVariable("id") Integer id, @RequestBody Row row) {
        int duration = row.getDuration();
        if(duration < 1 || duration > 60){
            throw  new UTRException("The duration should be in the range of 1-60", HttpStatus.BAD_REQUEST);
        }
        Row row1 = this.rowService.updateRow(row, id);
        if (Objects.isNull(row1)) {
            throw new UTRException("Row not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(row1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFila(@PathVariable("id") Integer id) {
        Row row = this.rowService.deleteRow(id);
        if (Objects.isNull(row)) {
            throw new UTRException("Row not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }
}
