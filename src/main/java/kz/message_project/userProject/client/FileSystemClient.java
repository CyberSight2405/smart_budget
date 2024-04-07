package kz.message_project.userProject.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-system-service", url = "${service.file-system-service.url}")
public interface FileSystemClient {
    @GetMapping(value = "/file/getImage")
    byte[] downloadFromMinio(@RequestParam("imageMinioName") String imageNameMinio);

    @PostMapping(value = "/file/uploadImage")
    String uploadToMinio(@RequestPart("image") MultipartFile image);

    @GetMapping(value = "/file/jsonFeign")
    String sendAndConsumeJson(@RequestBody String jsonFile);
}
