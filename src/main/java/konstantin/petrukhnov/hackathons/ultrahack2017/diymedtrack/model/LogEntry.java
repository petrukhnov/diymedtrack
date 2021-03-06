package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Record of inhaler use
 *
 * Created by konstantin.petrukhnov@gmail.com on 2017-04-21.
 */
@Data
public class LogEntry {

    @Id
    private String id;

    private String deviceId;
    private Long timeUsed;

}
