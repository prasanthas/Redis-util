package au.com.flexisoft.redisutil.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AccountSummary implements Serializable {
    @JsonProperty("Id")
    private Integer Id;
    @JsonProperty("Summary")
    private String Summary;
    @JsonProperty("Value")
    private Double Value;
}
