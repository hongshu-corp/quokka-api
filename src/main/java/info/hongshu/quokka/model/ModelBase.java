package info.hongshu.quokka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ModelBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Getter
    @Setter
//    @JsonDeserialize(using = HashIdDeserializer.class)
//    @JsonSerialize(using = HashIdSerializer.class)
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    @JsonProperty
    protected Date createdTime;

    @LastModifiedDate
    @JsonProperty
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    protected Date updatedTime;
}
