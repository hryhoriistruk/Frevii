package com.frevi.messaging.repository.jpa;

import com.frevi.messaging.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatJpaRepository extends JpaRepository<Chat, Long> {
}
