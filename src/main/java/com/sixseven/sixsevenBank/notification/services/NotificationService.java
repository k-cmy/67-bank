package com.sixseven.sixsevenBank.notification.services;

import com.sixseven.sixsevenBank.auth_users.entity.User;
import com.sixseven.sixsevenBank.notification.dtos.NotificationDTO;

public interface NotificationService {
    void sendEmail(NotificationDTO notificationDTO, User user);
}
