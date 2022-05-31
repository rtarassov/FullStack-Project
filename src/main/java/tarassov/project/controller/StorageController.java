package tarassov.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tarassov.project.dto.StorageDTO;
import tarassov.project.dto.SubStorageToStorageDTO;
import tarassov.project.model.Storage;
import tarassov.project.service.StorageService;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping("all")
    public List<Storage> findAllStorages() {
        log.info("findAllStorages was called from :[{}]", StorageController.class);
        return storageService.findAllStorages();
    }

    @GetMapping("{id}")
    public ResponseEntity<Storage> findStorageById(@PathVariable("id") Long storageId) {
        log.info("findStorageById() was called from :[{}]", StorageController.class);
        var storage = storageService.findStorageById(storageId);
        return storage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StorageDTO> saveStorage(@RequestBody StorageDTO storageDTO) {
        log.info("Trying to save storage: [{}]", storageDTO);
        var id = storageService.saveStorageToDB(storageDTO);
        return ResponseEntity.created(URI.create("/storage/%d"
                .formatted(id)))
                .body(storageDTO);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deleteStorageById(@PathVariable("id") Long id) {
        log.info("deleteStorageById() called from [{}]", StorageController.class);
        boolean deleted = storageService.deleteStorageById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStorageById(@PathVariable("id") Long id,
                                               @RequestBody StorageDTO storageDTO) {
        log.info("updateStorageById() called from: [{}]", StorageController.class);
        var storageId = storageService.updateStorageById(id, storageDTO);

        return ResponseEntity
                .created(URI.create("/storage/update/%d"
                        .formatted(storageId)))
                        .body(storageDTO);
    }

    @PutMapping("/substorage-to-storage")
    public ResponseEntity<Void> addSubStorageToStorage(@RequestBody SubStorageToStorageDTO subStorageToStorageDTO) {
        storageService.addSubStorageToStorage(subStorageToStorageDTO);
        return ResponseEntity.ok().build();
    }

}
