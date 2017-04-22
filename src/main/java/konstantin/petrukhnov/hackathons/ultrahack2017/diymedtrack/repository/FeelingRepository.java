package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.repository;

import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model.Feeling;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by khanguyen on 4/22/17.
 */
@Repository
public interface FeelingRepository extends CrudRepository<Feeling, String> {
}
