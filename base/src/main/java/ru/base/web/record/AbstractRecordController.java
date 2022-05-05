package ru.base.web.record;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ru.base.model.Record;
import ru.base.service.RecordService;
import ru.base.web.SecurityUtil;

import java.util.Collection;

import static ru.base.util.ValidationUtil.checkNew;

public abstract class AbstractRecordController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RecordService service;

    public Collection<Record> getAll() {
        int userId = SecurityUtil.authUserId();
        LOG.info("getAll");
        return service.getAll(userId);
    }

    public Record get(int id) {
        int userId = SecurityUtil.authUserId();
        LOG.info("get {}", id);
        return service.get(id, userId);
    }

    public Record create(Record record) {
        int userId = SecurityUtil.authUserId();
        LOG.info("create {}", record);
        checkNew(record);
        return service.save(record, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        LOG.info("delete {}", id);
        service.delete(id, userId);
    }
   
    public void update(Record record, int id) {
        int userId = SecurityUtil.authUserId();
        LOG.info("update {} with id={}", record, id);
        //assureIdConsistent(record, userId);
        service.update(record, userId);
    }
    
}