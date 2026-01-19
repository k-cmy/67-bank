package com.sixseven.sixsevenBank.notification.repo;

import com.sixseven.sixsevenBank.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Integer> {
}
