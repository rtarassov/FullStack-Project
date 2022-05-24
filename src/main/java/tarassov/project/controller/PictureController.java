package tarassov.project.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import tarassov.project.model.Picture;
import tarassov.project.repository.PictureRepository;
import tarassov.project.service.PictureService;

import java.net.URI;

@RestController
@RequestMapping("picture")
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }


    @PostMapping("picture")
    public ResponseEntity<Long> uploadPicture(@RequestParam MultipartFile multipartFile) throws Exception {
        pictureService.uploadPicture(multipartFile);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/picture/{pictureId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource downloadPicture(@PathVariable Long pictureId) {
        return pictureService.downloadPicture(pictureId);
    }
}
