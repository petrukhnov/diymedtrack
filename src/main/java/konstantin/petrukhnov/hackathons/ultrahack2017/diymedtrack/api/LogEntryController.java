package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.api;

import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model.Feeling;
import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model.LogEntry;
import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.service.LogEntryService;
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
@RequestMapping("/log")
public class LogEntryController {

    @Autowired
    private LogEntryService logEntryService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public LogEntry add(@RequestBody LogEntry entry) {
        log.info("post data: {}", entry.getDeviceId());
        return logEntryService.add(entry);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<LogEntry> getAll() {
        return logEntryService.getList();
    }
}
