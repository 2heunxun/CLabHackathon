package com.example.demo.service;

import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ImageService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;
    @Value("${spring.cloud.gcp.storage.project-id}")
    private String projectId;

    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

    //업로드할 이미지를 file 형태로 주면, 해당 파일을 클라우드에 업로드 후, 조회 가능한 String url로 변환하여 반환
    public Optional<String> uploadPostImage(MultipartFile multipartFile) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String ext = multipartFile.getContentType();
        BlobId blobId = BlobId.of(bucketName, uuid);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(ext)
                .build();
        try(WriteChannel writeChannel = storage.writer(blobInfo)) {
            byte[] imageData = multipartFile.getBytes();
            writeChannel.write(ByteBuffer.wrap(imageData));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileUrl = "https://storage.cloud.google.com/" + bucketName + "/" + uuid;
        return fileUrl.describeConstable();
    }
}