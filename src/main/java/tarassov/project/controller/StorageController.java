package tarassov.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tarassov.project.model.Storage;
import tarassov.project.service.StorageService;

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

}
