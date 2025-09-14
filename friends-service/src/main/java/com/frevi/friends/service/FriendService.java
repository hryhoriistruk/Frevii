package com.frevi.friends.service;

import com.frevi.friends.dto.FriendRequest;
import com.frevi.friends.dto.FriendResponse;
import com.frevi.friends.entity.FriendRecord;
import com.frevi.friends.mapper.FriendMapper;
import com.frevi.friends.repository.FriendRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final FriendMapper friendMapper;

    @Caching(evict = {
            @CacheEvict(key = "#{friendRequest.firstUserId()}", value = "friends_ids"),
            @CacheEvict(key = "#{friendRequest.secondUserId()}", value = "friends_ids")
    })
    public FriendResponse createFriend(FriendRequest friendRequest) {
        FriendRecord friendRecord = friendRepository.save(friendMapper
                .fromRequest(friendRequest));

        return friendMapper.toResponse(friendRecord);
    }

    @Cacheable(key="#id", value = "friends")
    public FriendResponse findById(long id) throws EntityNotFoundException {
        FriendRecord friendRecord = friendRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return friendMapper.toResponse(friendRecord);
    }

    @Cacheable(key = "#userId", value = "friends_ids")
    public List<Long> findAllForUser(long userId) {
        return friendRepository.findAll()
                .stream()
                .filter(f -> f.getFirstUserId() == userId
                        || f.getSecondUserId() == userId)
                .map(f -> {
                    if (f.getFirstUserId() == userId)
                        return f.getSecondUserId();

                    return f.getFirstUserId();
                })
                .toList();
    }

    public Page<FriendResponse> findAll(Pageable pageable) {
        return friendRepository.findAll(pageable).map(friendMapper::toResponse);
    }
}
