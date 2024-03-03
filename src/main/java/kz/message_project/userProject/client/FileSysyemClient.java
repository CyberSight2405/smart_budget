package kz.message_project.userProject.client;

import kz.message_project.userProject.dto.ImageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-system-service", url = "${service.file-system-service.url}")
public interface FileSysyemClient {
    @GetMapping(value = "/file/getImage")
    byte[] downloadFromMinio(@RequestParam("imageMinioName") String imageNameMinio);

    @PostMapping(value = "/file/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void uploadToMinio(@RequestParam("image") MultipartFile image);
}
