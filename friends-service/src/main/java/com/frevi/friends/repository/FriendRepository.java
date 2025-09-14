package com.frevi.friends.repository;

import com.frevi.friends.entity.FriendRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<FriendRecord, Long> {
}
