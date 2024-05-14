package com.uep.wap.repository;

import com.uep.wap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(String role);
    List<User> findByLastName(String lastName);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    List<User> findAllByReceivedMessagesMessageId(Long messageId);
    List<User> findAllBySentMessagesMessageId(Long messageId);
    List<User> findAllByTasksTaskId(Long taskId);
    List<User> findAllByNotificationsNotificationId(Long notificationId);
    User findByEmail(String email);


}
