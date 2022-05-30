package tarassov.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tarassov.project.model.Storage;
import tarassov.project.repository.StorageRepository;

import java.util.List;

@Service
@Slf4j
public class StorageService {

    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    // TODO: Finish implementation

    public List<Storage> findAllStorages() {
        log.info("Trying to find all storages from database");
        var storages = storageRepository.findAll();
        log.info("Storages found: " + storages);
        return storages;
    }
}
