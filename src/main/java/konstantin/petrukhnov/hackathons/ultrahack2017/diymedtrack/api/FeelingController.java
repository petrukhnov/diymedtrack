package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.api;

import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model.Feeling;
import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.service.FeelingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-04-21.
 */
@Slf4j
@RestController
@RequestMapping("/feeling")
public class FeelingController {

    @Autowired
    private FeelingService feelingService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Feeling add(@RequestBody Feeling entry) {
        log.info("post data: {}", entry.getValue());
        return feelingService.add(entry);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Feeling> getAll() {
        return feelingService.getList();
    }
}
