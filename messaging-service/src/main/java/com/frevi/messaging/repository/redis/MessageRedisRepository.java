package com.frevi.messaging.repository.redis;

import com.frevi.messaging.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRedisRepository extends CrudRepository<Message, Long> {
    long countByChatId(Long chatId);

    Page<Message> findByChatId(Long chatId, Pageable pageable);
}
