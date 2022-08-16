package io.javamonkey.liqubase.demo.dal.repository;

import io.javamonkey.liqubase.demo.dal.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
