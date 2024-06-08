package com.enfocareservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enfocareservice.entity.SubscriptionEntity;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
}