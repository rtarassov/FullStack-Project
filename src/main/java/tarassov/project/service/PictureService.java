package tarassov.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import tarassov.project.model.Picture;
import tarassov.project.repository.PictureRepository;

@Service
@Slf4j
public class PictureService {

    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Resource downloadPicture(Long pictureId) {
        byte[] picture = pictureRepository.findById(pictureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(picture);
    }

    public Long uploadPicture(MultipartFile multipartFile) {
        Picture picture = new Picture();

        try {
            picture.setName(multipartFile.getName());
            picture.setContent(multipartFile.getBytes());

            pictureRepository.save(picture);
            return picture.getId();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
