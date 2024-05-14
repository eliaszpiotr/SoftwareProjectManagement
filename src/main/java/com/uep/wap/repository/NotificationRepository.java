package com.uep.wap.repository;

import com.uep.wap.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByRecipientUserId(Long recipientId);

    List<Notification> findByRecipientUserIdAndStatus(Long recipientId, String status);

    List<Notification> findByTypeAndStatus(String type, String status);

    List<Notification> findByCreatedAtBefore(Date date);

    List<Notification> findByCreatedAtAfter(Date date);

    List<Notification> findByRecipientUserIdAndType(Long recipientId, String type);
}
