package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.repository;

import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model.LogEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-04-21.
 */
@Repository
public interface  LogEntryRepository extends CrudRepository<LogEntry,String> {

}
