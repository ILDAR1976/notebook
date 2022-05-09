package ru.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.base.model.Record;
import ru.base.repository.RecordRepository;
import ru.base.util.exception.ExceptionUtil;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository repository;


    @Override
    public Record get(int id, int userId) {
        return ExceptionUtil.check(repository.get(id,userId),id);
    }

    @Override
    public void delete(int id, int userId) {
        ExceptionUtil.check(repository.delete(id,userId),id);
    }


    @Override
    public Collection<Record> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Record update(Record record, int userId) {
        return ExceptionUtil.check(repository.save(record,userId),record.getId());
    }

    @Override
    public Record save(Record record, int userId) {
        return repository.save(record, userId);
    }



}
