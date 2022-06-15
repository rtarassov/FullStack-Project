package tarassov.project.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tarassov.project.service.PictureService;


@RestController
@RequestMapping("/picture")
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PostMapping
    public ResponseEntity<Long> uploadPicture(@RequestParam MultipartFile multipartFile,
                                              @RequestParam String name) {
        pictureService.uploadPicture(multipartFile, name);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{pictureId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource downloadPicture(@PathVariable Long pictureId) {
        return pictureService.downloadPicture(pictureId);
    }
}
