package ru.base.web.record;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.base.model.Record;

import java.util.Collection;

import java.net.URI;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = RecordRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RecordRestController extends AbstractRecordController {

    static final String REST_URL = "/rest/profile/records";

    @Override
    @GetMapping("/{id}")
    public Record get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public Collection<Record> getAll() {
        var records = super.getAll();
        return records;
        
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Record record, @PathVariable int id) {
        super.update(record, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Record> createWithLocation(@RequestBody Record record) {
        record.setId(null);
        Record created = super.create(record);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}