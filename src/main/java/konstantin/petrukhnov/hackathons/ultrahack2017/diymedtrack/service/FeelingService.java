package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.service;

import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model.Feeling;
import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.repository.FeelingRepository;
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
public class FeelingService {

    @Autowired
    private FeelingRepository feelingRepository;

    public Feeling add(Feeling entry) {
        entry.setTimeAdded(System.currentTimeMillis());
        return feelingRepository.save(entry);
    }

    public List<Feeling> getList() {
        ArrayList<Feeling> list = new ArrayList<>();
        Iterable<Feeling> iterable = feelingRepository.findAll();
        iterable.forEach(list::add);
        return list;
    }
}
