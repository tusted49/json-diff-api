package com.weas.jsondiffapi.gateways;

import com.weas.jsondiffapi.domains.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<Data, Long> {
}
