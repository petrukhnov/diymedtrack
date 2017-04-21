package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.service;

import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model.LogEntry;
import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.repository.LogEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-04-21.
 */
@Slf4j
@Service
public class LogEntryService {

    @Autowired
    private LogEntryRepository logEntryRepository;

    public LogEntry add(LogEntry entry) {
        return logEntryRepository.save(entry);
    }

    @PostConstruct
    public void generateDummyData() {
        if (logEntryRepository.count() < 1) {
            //generate data
            //// TODO: 10

        }
    }
}
