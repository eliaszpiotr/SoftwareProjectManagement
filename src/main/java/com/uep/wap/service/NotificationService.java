package com.uep.wap.service;

import com.uep.wap.model.Notification;
import com.uep.wap.model.User;
import com.uep.wap.repository.NotificationRepository;
import com.uep.wap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    // Tworzenie nowego powiadomienia
    @Transactional
    public Notification createNotification(Long recipientId, Notification.NotificationType type, String message, Notification.NotificationStatus status) {
        User recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new IllegalArgumentException("Recipient with ID " + recipientId + " not found"));
        Notification newNotification = new Notification(recipient, type, message, status);
        return notificationRepository.save(newNotification);
    }

    // Pobieranie powiadomienia po ID
    public Optional<Notification> getNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId);
    }

    // Aktualizacja powiadomienia
    @Transactional
    public Notification updateNotification(Long notificationId, Notification.NotificationStatus newStatus) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification with ID " + notificationId + " not found"));
        notification.setStatus(newStatus);
        return notificationRepository.save(notification);
    }

    // Pobieranie wszystkich powiadomień dla danego użytkownika
    public List<Notification> getNotificationsByRecipientId(Long recipientId) {
        return notificationRepository.findByRecipientUserId(recipientId);
    }

    // Usuwanie powiadomienia
    @Transactional
    public void deleteNotification(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new IllegalArgumentException("Notification with ID " + notificationId + " not found");
        }
        notificationRepository.deleteById(notificationId);
    }
}
