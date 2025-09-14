package com.frevi.posts.mapper;

import com.frevi.posts.dto.PostRequest;
import com.frevi.posts.dto.PostResponse;
import com.frevi.posts.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Mapping(target = "media", source = "mediaFileNames", qualifiedByName = "mapMedia")
    public abstract Post toJpaEntity(PostRequest request);

    public abstract PostResponse toResponse(Post entity);

    public List<String> mapMedia(List<MultipartFile> media) {
        return media.stream()
                .map(MultipartFile::getName)
                .toList();
    }
}
