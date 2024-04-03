package com.uep.wap.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationType type;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationStatus status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Constructors
    public Notification() {
    }

    public Notification(User recipient, NotificationType type, String message, NotificationStatus status) {
        this.recipient = recipient;
        this.type = type;
        this.message = message;
        this.status = status;
        // 'createdAt' is managed automatically by @CreationTimestamp
    }

    // Getters and Setters

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification that)) return false;
        return Objects.equals(getNotificationId(), that.getNotificationId()) && Objects.equals(getRecipient(), that.getRecipient()) && getType() == that.getType() && Objects.equals(getMessage(), that.getMessage()) && getStatus() == that.getStatus() && Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNotificationId(), getRecipient(), getType(), getMessage(), getStatus(), getCreatedAt());
    }

    // toString

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", recipient=" + recipient +
                ", type=" + type +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}

enum NotificationType {
    MESSAGE, TASK, ALERT
}

enum NotificationStatus {
    DELIVERED, READ, UNREAD
}
