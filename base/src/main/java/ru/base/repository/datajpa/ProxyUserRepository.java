package ru.base.repository.datajpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import ru.base.model.User;

public interface ProxyUserRepository extends JpaRepository<User,Integer> {

    @Override
    @Transactional
    @Modifying
    void deleteById(Integer integer);

    @Override
    @Transactional
    User save(User user);

   
    @Override
    List<User> findAll(Sort sort);

    @Override
    Optional<User> findById(Integer integer);

    User getByEmail(String email);
}
