package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.service;

import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model.Feeling;
import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.repository.FeelingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by khanguyen on 4/22/17.
 */
@Slf4j
@Service
public class FeelingService {
    //Repo
    @Autowired
    private FeelingRepository feelingRepository;
    
    public Feeling addFeeling(Feeling feeling) {
        return feelingRepository.save(feeling);
    }

    public Iterable<Feeling> getAllFeeling() {
        return feelingRepository.findAll();
    }
    
    @PostConstruct
    public void constructFeeling() {
        //TODO: build data
    }
}
