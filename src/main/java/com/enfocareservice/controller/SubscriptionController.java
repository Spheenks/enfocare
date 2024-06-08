package com.enfocareservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enfocareservice.model.Subscription;
import com.enfocareservice.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@GetMapping
	public List<Subscription> getAllSubscriptions() {
		return subscriptionService.getAllSubscriptions();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Integer id) {
		Subscription subscription = subscriptionService.getSubscriptionById(id);
		if (subscription != null) {
			return ResponseEntity.ok(subscription);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public Subscription createSubscription(@RequestBody Subscription subscription,
			@RequestParam String subscriptionType) {
		return subscriptionService.createSubscription(subscription, subscriptionType);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Subscription> updateSubscription(@PathVariable Integer id,
			@RequestBody Subscription subscription, @RequestParam String subscriptionType) {
		Subscription updatedSubscription = subscriptionService.updateSubscription(id, subscription, subscriptionType);
		if (updatedSubscription != null) {
			return ResponseEntity.ok(updatedSubscription);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSubscription(@PathVariable Integer id) {
		subscriptionService.deleteSubscription(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/check-expiry")
	public ResponseEntity<Void> checkAndDeleteExpiredSubscriptions() {
		subscriptionService.checkAndDeleteExpiredSubscriptions();
		return ResponseEntity.noContent().build();
	}
}