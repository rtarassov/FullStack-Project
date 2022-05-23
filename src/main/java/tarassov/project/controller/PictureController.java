package tarassov.project.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import tarassov.project.model.Picture;
import tarassov.project.repository.PictureRepository;

@RestController
public class PictureController {

    private final PictureRepository pictureRepository;

    public PictureController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @PostMapping("picture")
    Long uploadPicture(@RequestParam MultipartFile multipartFile) throws Exception {
        Picture picture = new Picture();
        picture.setName(multipartFile.getName());
        picture.setContent(multipartFile.getBytes());

        return pictureRepository.save(picture).getId();
    }

    @GetMapping(value = "/picture/{pictureId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource downloadPicture(@PathVariable Long pictureId) {
        byte[] picture = pictureRepository.findById(pictureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(picture);
    }
}
