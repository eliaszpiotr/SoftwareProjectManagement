package com.uep.wap.model;

import javax.persistence.*;
import java.util.Date;

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

    @Column(length = 255) // Nullable, więc nie potrzeba `nullable = false`
    private String subject;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date sentAt;

    @Column(nullable = false)
    private boolean readStatus;

    // Konstruktor bezargumentowy jest wymagany przez JPA
    public Message() {
    }

    // Konstruktor z argumentami dla łatwiejszego tworzenia obiektów
    public Message(User sender, User recipient, String subject, String content, Date sentAt, boolean readStatus) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
        this.sentAt = sentAt;
        this.readStatus = readStatus;
    }

    // Gettery i settery
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


}
