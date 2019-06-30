package com.middleware.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.middleware.io.entity.EventEntity;

@Repository
public interface EventRepository extends CrudRepository<EventEntity,Long> {
	EventEntity findByEventid(String id);
}
