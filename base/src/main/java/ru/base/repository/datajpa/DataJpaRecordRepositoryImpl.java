package ru.base.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.base.model.Record;
import ru.base.repository.RecordRepository;

import java.util.Collection;

@Repository
public class DataJpaRecordRepositoryImpl implements RecordRepository {

    @Autowired
    private ProxyRecordRepository recordProxy;

    @Autowired
    private ProxyUserRepository userProxy;


    @Override
    public Record save(Record record, int userId) {
        record.setUser(userProxy.findById(userId).orElse(null));
        if (!record.isNew() && get(record.getId(), userId) == null) {
            return null;
        }
        return recordProxy.save(record);
    }

    @Override
    public boolean delete(int id, int userId) {
        return recordProxy.delete(id, userId) != 0;
    }

    @Override
    public Record get(int id, int userId) {
        return recordProxy.findById(id).filter(meal -> meal.getUser().getId() == userId).orElse(null);
    }

    @Override
    public Collection<Record> getAll(int userId) {
        return recordProxy.getAll(userId);
    }
}
