package tarassov.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tarassov.project.dto.DemoRequest;
import tarassov.project.model.DemoModel;
import tarassov.project.repository.DemoRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class DemoService {

    private final DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public List<DemoModel> readAllDemos() {
        log.info("Reading demos from database");
        var result = demoRepository.findAll();
        log.info("Found demos " + result);
        return result;
    }

    @Transactional
    public Long saveDemo(DemoRequest demoEntity) {
        log.info("Entity to save [{}]", demoEntity);

        try {
            var demoObject = new DemoModel();
            demoObject.setId(demoEntity.getId());
            demoObject.setFirstName(demoEntity.getFirst_name());
            demoObject.setLastName(demoEntity.getLast_name());
            demoRepository.save(demoObject);
            return demoObject.getId();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
