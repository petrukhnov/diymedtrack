package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.service;

import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model.LogEntry;
import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.repository.LogEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-04-21.
 */
@Slf4j
@Service
public class LogEntryService {

    @Autowired
    private LogEntryRepository logEntryRepository;

    public LogEntry add(LogEntry entry) {
        entry.setTimeUsed(System.currentTimeMillis());
        return logEntryRepository.save(entry);
    }

    public List<LogEntry> getList() {
        ArrayList<LogEntry> list = new ArrayList<>();
        Iterable<LogEntry> iterable = logEntryRepository.findAll();
        iterable.forEach(list::add);
        return list;
    }
}
