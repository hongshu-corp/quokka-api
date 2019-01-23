package info.hongshu.quokka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "auth_codes")
public class AuthCode extends ModelBase {
    @Column(nullable = false)
    @JsonProperty
    private String keyCode;

    @Column(nullable = false)
    @JsonProperty
    private String code;
}