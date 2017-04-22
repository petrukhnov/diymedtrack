package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.api;

import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model.Feeling;
import konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.service.FeelingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by khanguyen on 4/22/17.
 */
@Slf4j
@RestController
@RequestMapping("/feeling")
public class FeelingController {
    @Autowired
    FeelingService feelingService;
    @RequestMapping("/add")
    public ResponseEntity<?> addFeeling(@RequestBody Feeling feeling) {
        try {
            return new ResponseEntity<>(feelingService.addFeeling(feeling), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(feeling, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping("/get")
    public ResponseEntity<?> getAllFeeling() {
        return new ResponseEntity<>(feelingService.getAllFeeling(), HttpStatus.OK);
    }

}
