package tarassov.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tarassov.project.repository.StorageRepository;

@Service
@Slf4j
public class StorageService {

    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    // TODO: Finish implementation
}
