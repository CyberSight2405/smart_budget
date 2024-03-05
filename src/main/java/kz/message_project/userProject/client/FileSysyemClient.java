package kz.message_project.userProject.client;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-system-service", url = "${service.file-system-service.url}")
public interface FileSysyemClient {
    @GetMapping(value = "/file/getImage")
    byte[] downloadFromMinio(@RequestParam("imageMinioName") String imageNameMinio);

    @PostMapping(value = "/file/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadToMinio(@RequestPart("image") MultipartFile image);
}
