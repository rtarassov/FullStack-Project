package tarassov.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tarassov.project.dto.StorageDTO;
import tarassov.project.dto.SubStorageToStorageDTO;
import tarassov.project.model.Storage;
import tarassov.project.repository.StorageRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StorageService {

    private final StorageRepository storageRepository;
    private final ServiceValidations serviceValidations;

    public List<Storage> findAllStorages() {
        log.info("Trying to find all storages from database");
        var storages = storageRepository.findAll();
        log.info("Storages found: " + storages);
        return storages;
    }

    public Optional<Storage> findStorageById(Long id) {
        log.info("Trying to find product by id: [{}]", id);
        var storage = storageRepository.findById(id);

        log.info("Found Storage from database: [{}]", storage);
        return storage;
    }

    // Needed so ProductService doesn't use StorageRepository and uses StorageService instead.
    public Storage getStorageById(Long id) {
        return storageRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

    public void addSubStorageToStorage(SubStorageToStorageDTO subStorageToStorageDTO) {
        try {
            var storageObject = storageRepository.getById(subStorageToStorageDTO.getStorageId());
            var subStorageObject = storageRepository.getById(subStorageToStorageDTO.getSubStorageId());

            storageObject.getSubStorageList().add(subStorageObject);
            storageRepository.save(storageObject);
        } catch (IllegalArgumentException e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public Long saveStorageToDB(StorageDTO storageDTO) {
        log.info("Entity to save: [{}]", storageDTO);

        try {
            var storageObject = new Storage();
            if (storageDTO.getSubStorageId() != null) {
                var subStorageObject = storageRepository.getById(storageDTO.getSubStorageId());
                var subStorageList = subStorageObject.getSubStorageList();
                if (storageRepository.existsById(subStorageObject.getId())) {
                    storageObject.setSubStorageList(subStorageList);
                }
            }
            if (serviceValidations.isValidCharacters(storageDTO.getName())) {
                storageObject.setName(storageDTO.getName());
            } else {
                throw new IllegalArgumentException("Name is not at least 3 characters or contains symbols.");
            }
            storageObject.setDescription(storageDTO.getDescription());

            storageRepository.save(storageObject);
            return storageObject.getId();
        } catch (IllegalArgumentException e)  {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean deleteStorageById(Long id) {
        log.info("Trying to delete Storage by id: [{}]", id);

        boolean result = false;

        if (storageRepository.existsById(id)) {
            storageRepository.deleteById(id);
            result = true;
        }
        return result;
    }

    public Long updateStorageById(Long id, StorageDTO storageDTO) {
        log.info("Trying to update storage [{}]", storageRepository.getById(id));
        log.info("With data: [{}]", storageDTO);

        try {
            var storageObject = storageRepository.getById(id);

            if (storageDTO.getName() != null) {
                if (serviceValidations.isValidCharactersIgnoreLength(storageDTO.getName())) {
                    storageObject.setName(storageDTO.getName());
                } else {
                    throw new IllegalArgumentException("Name is not at least 3 characters or contains symbols.");
                }
            }
            if (storageDTO.getDescription() != null) {
                storageObject.setDescription(storageDTO.getDescription());
            }

            storageRepository.save(storageObject);
            return storageObject.getId();
        } catch (IllegalArgumentException e)  {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
