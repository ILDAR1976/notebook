package ru.base.repository;

import java.util.Collection;
import ru.base.model.Record;

public interface RecordRepository {
    Record save(Record user, int userId);
    boolean delete(int id, int userId);
    Record get(int id, int userId);
    Collection<Record> getAll(int userId);
}