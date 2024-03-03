package kz.message_project.userProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImageDto {
    private String originalFileName;
    private Long size;
    private String contentType;
    private String minioName;
    private Boolean isPreviewImage;
    private byte[] bytes;
}
