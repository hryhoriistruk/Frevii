package com.frevi.messaging.repository.jpa;

import com.frevi.messaging.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageJpaRepository extends JpaRepository<Message, Long> {
    long countByChatId(Long chatId);

    Page<Message> findByChatId(Long chatId, Pageable pageable);
}
