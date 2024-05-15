package com.uep.wap.service;

import com.uep.wap.dto.NotificationDTO;
import com.uep.wap.model.Notification;
import com.uep.wap.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Convert Notification to NotificationDTO
    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getNotificationId());
        notificationDTO.setUserId(notification.getRecipient().getUserId());
        notificationDTO.setTitle(notification.getType().toString());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setStatus(notification.getStatus().toString());
        return notificationDTO;
    }

    // Convert NotificationDTO to Notification
    private Notification convertToEntity(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        // Note: You need to fetch the User entity and set it as the recipient
        // notification.setRecipient(user);
        notification.setType(Notification.NotificationType.valueOf(notificationDTO.getTitle()));
        notification.setMessage(notificationDTO.getMessage());
        notification.setStatus(Notification.NotificationStatus.valueOf(notificationDTO.getStatus()));
        return notification;
    }

    // Creating a new notification
    @Transactional
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification newNotification = convertToEntity(notificationDTO);
        return convertToDTO(notificationRepository.save(newNotification));
    }

    // Getting a notification by ID
    public NotificationDTO getNotificationById(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification with ID " + notificationId + " not found"));
        return convertToDTO(notification);
    }

    // Updating notification data
    @Transactional
    public NotificationDTO updateNotification(Long notificationId, NotificationDTO notificationDTO) {
        Notification existingNotification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification with ID " + notificationId + " not found"));
        existingNotification.setType(Notification.NotificationType.valueOf(notificationDTO.getTitle()));
        existingNotification.setMessage(notificationDTO.getMessage());
        existingNotification.setStatus(Notification.NotificationStatus.valueOf(notificationDTO.getStatus()));
        return convertToDTO(notificationRepository.save(existingNotification));
    }

    // Deleting a notification
    @Transactional
    public void deleteNotification(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new IllegalArgumentException("Notification with ID " + notificationId + " not found");
        }
        notificationRepository.deleteById(notificationId);
    }

    // Getting all notifications
    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}