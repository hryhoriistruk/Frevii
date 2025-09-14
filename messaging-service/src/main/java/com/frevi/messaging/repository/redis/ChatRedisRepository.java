package com.frevi.messaging.repository.redis;

import com.frevi.messaging.entity.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRedisRepository extends CrudRepository<Chat, Long> {
}
