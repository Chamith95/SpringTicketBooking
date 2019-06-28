package com.middleware.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.middleware.io.entity.OrgEntity;

@Repository
public interface OrgRepository extends CrudRepository<OrgEntity, Long> {
	OrgEntity findByEmail(String email);
}
