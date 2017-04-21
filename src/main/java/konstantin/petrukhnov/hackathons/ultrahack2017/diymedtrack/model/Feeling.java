package konstantin.petrukhnov.hackathons.ultrahack2017.diymedtrack.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Additional data about user condition, recorded by user.
 *
 * Created by konstantin.petrukhnov@gmail.com on 2017-04-21.
 */
@Data
public class Feeling {
    @Id
    private String id;

    private Integer value; //1-5, worst, best
    private String comment;
    private Long timeAdded;
    private String logEntryId;
}
