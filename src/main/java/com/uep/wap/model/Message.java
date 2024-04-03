package com.uep.wap.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @Column(length = 255)
    private String subject;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date sentAt;

    @Column(nullable = false)
    private boolean readStatus;

    // Constructors
    public Message() {
        // Default constructor
    }

    public Message(User sender, User recipient, String subject, String content, Date sentAt, boolean readStatus) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
        this.sentAt = sentAt;
        this.readStatus = readStatus;
    }

    // Getters and setters

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message message)) return false;
        return isReadStatus() == message.isReadStatus() && Objects.equals(getMessageId(), message.getMessageId()) && Objects.equals(getSender(), message.getSender()) && Objects.equals(getRecipient(), message.getRecipient()) && Objects.equals(getSubject(), message.getSubject()) && Objects.equals(getContent(), message.getContent()) && Objects.equals(getSentAt(), message.getSentAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessageId(), getSender(), getRecipient(), getSubject(), getContent(), getSentAt(), isReadStatus());
    }

    // toString

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", sentAt=" + sentAt +
                ", readStatus=" + readStatus +
                '}';
    }
}
