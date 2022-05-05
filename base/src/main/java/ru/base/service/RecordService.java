package ru.base.service;

import ru.base.model.Record;
import java.util.Collection;

public interface RecordService {
   
    Record get(int id, int userId);

    void  delete(int id, int userId);

    Collection<Record> getAll(int userId);

    Record update(Record record, int userId);

    Record save(Record record, int userId);

}