package com.enfocareservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enfocareservice.entity.SubscriptionEntity;
import com.enfocareservice.model.Subscription;
import com.enfocareservice.model.mapper.SubscriptionMapper;
import com.enfocareservice.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionMapper subscriptionMapper;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public List<Subscription> getAllSubscriptions() {
		return subscriptionRepository.findAll().stream().map(subscriptionMapper::map).collect(Collectors.toList());
	}

	public Subscription getSubscriptionById(Integer id) {
		Optional<SubscriptionEntity> subscriptionEntity = subscriptionRepository.findById(id);
		return subscriptionEntity.map(subscriptionMapper::map).orElse(null);
	}

	public Subscription createSubscription(Subscription subscription, String subscriptionType) {
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		subscriptionEntity.setEmail(subscription.getEmail());
		subscriptionEntity.setExpiry(calculateExpiryDate(subscriptionType));
		SubscriptionEntity savedEntity = subscriptionRepository.save(subscriptionEntity);
		return subscriptionMapper.map(savedEntity);
	}

	public Subscription updateSubscription(Integer id, Subscription subscription, String subscriptionType) {
		Optional<SubscriptionEntity> subscriptionEntityOptional = subscriptionRepository.findById(id);
		if (subscriptionEntityOptional.isPresent()) {
			SubscriptionEntity subscriptionEntity = subscriptionEntityOptional.get();
			subscriptionEntity.setEmail(subscription.getEmail());
			subscriptionEntity.setExpiry(calculateExpiryDate(subscriptionType));
			SubscriptionEntity updatedEntity = subscriptionRepository.save(subscriptionEntity);
			return subscriptionMapper.map(updatedEntity);
		}
		return null;
	}

	public void deleteSubscription(Integer id) {
		subscriptionRepository.deleteById(id);
	}

	public void checkAndDeleteExpiredSubscriptions() {
		List<SubscriptionEntity> subscriptions = subscriptionRepository.findAll();
		for (SubscriptionEntity subscription : subscriptions) {
			if (subscription.getExpiry().isBefore(LocalDateTime.now())) {
				subscriptionRepository.deleteById(subscription.getId());
			}
		}
	}

	private LocalDateTime calculateExpiryDate(String subscriptionType) {
		LocalDateTime now = LocalDateTime.now();
		switch (subscriptionType.toLowerCase()) {
		case "weekly":
			return now.plusWeeks(1);
		case "monthly":
			return now.plusMonths(1);
		case "annually":
			return now.plusYears(1);
		default:
			throw new IllegalArgumentException("Invalid subscription type: " + subscriptionType);
		}
	}

}