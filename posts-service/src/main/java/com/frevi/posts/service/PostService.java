package com.frevi.posts.service;

import com.frevi.posts.dto.PostRequest;
import com.frevi.posts.dto.PostResponse;
import com.frevi.posts.entity.Post;
import com.frevi.posts.mapper.PostMapper;
import com.frevi.posts.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final MinioService minioService;

    private PostResponse getResponse(Post post) throws Exception {
        PostResponse postResponse = postMapper.toResponse(post);

        for (String m : post.getMediaFileNames()) {
            postResponse.getMediaUrls().add(
                    minioService.downloadFile(m)
            );
        }

        return postResponse;
    }

    private void uploadMedia(MultipartFile multipartFile) throws Exception {
        minioService.uploadFile(
                multipartFile.getName(),
                multipartFile.getInputStream(),
                multipartFile.getSize(),
                multipartFile.getContentType()
        );
    }

    @CacheEvict(key = "#postRequest.userId()", value = "posts")
    @SneakyThrows
    public PostResponse createPost(PostRequest postRequest) {
        postRequest.media().forEach(this::uploadMedia);

        Post post = postRepository.save(postMapper.toJpaEntity(postRequest));

        return getResponse(post);
    }

    @Cacheable(key = "#id", value = "post")
    public PostResponse findById(long id) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return getResponse(post);
    }

    @SneakyThrows
    public Page<PostResponse> findAll(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(this::getResponse);
    }

    @Cacheable(key = "#userId", value = "posts")
    @SneakyThrows
    public Page<PostResponse> findByUserId(long userId, Pageable pageable) {
        return postRepository.findByUserId(pageable, userId)
                .map(this::getResponse);
    }
}
