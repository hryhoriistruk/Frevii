package com.frevi.posts.service;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinioService {
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String minioBucket;

    private void createBucketIfNotExists() throws Exception {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().build());

        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().build());
        }
    }

    public void uploadFile(String objectName, InputStream stream, long size, String contentType) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioBucket)
                        .object(objectName)
                        .stream(stream, size, -1)
                        .contentType(contentType)
                        .build()
        );
    }

    public String downloadFile(String objectName) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(minioBucket)
                        .expiry(60 * 10)
                        .object(objectName)
                        .build()
        );
    }

    public Iterable<Result<Item>> listFiles() {
        return minioClient.listObjects(
                ListObjectsArgs.builder().bucket(minioBucket).build()
        );
    }
}
