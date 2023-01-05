package Model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Links")
@Data
@Builder
public class Links {
    @Id
    private String id;
    private LocalDateTime creationDate;
    private String hashedLink;
    private String originalString;
}
