package com.uep.wap.repository;

import com.uep.wap.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderId(Long senderId);

    List<Message> findByRecipientId(Long recipientId);

    List<Message> findByRecipientIdAndReadStatusFalse(Long recipientId);

    List<Message> findBySentAtBetween(Date startDate, Date endDate);

    List<Message> findBySubjectContaining(String keyword);
}
