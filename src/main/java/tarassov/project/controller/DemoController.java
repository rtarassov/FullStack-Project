package tarassov.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tarassov.project.dto.DemoRequest;
import tarassov.project.model.DemoModel;
import tarassov.project.service.DemoService;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200/") // TODO: Remove this line when I don't host on my own computer anymore.
@RequestMapping("/demo")
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/read-all")
    public List<DemoModel> findAllDemos() {
        log.info("findAllDemos() was called from controller");
        return demoService.readAllDemos();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDemo(@RequestBody DemoRequest newDemo) {
    log.info("Saving entity [{}]", newDemo);
    var id = demoService.saveDemo(newDemo);
    return ResponseEntity.created(URI.create("/demo/create/%d"
            .formatted(newDemo.getId())))
            .body(newDemo);
    }
}
