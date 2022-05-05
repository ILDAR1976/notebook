package ru.base.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ru.base.model.Record;
import java.util.Collection;



public interface ProxyRecordRepository extends JpaRepository<Record,Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Record r WHERE r.id=:id AND r.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Record save(Record record);

    @Query("SELECT r FROM Record r WHERE r.user.id=:userId ORDER BY r.dateTime DESC")
    Collection<Record> getAll(@Param("userId") int userId);

   
}
