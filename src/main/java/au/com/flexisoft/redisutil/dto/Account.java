package au.com.flexisoft.redisutil.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Account implements Serializable {
    private Integer id;
    private String type;
    private Double amount;
    private List<AccountSummary> accountSummaries;
}
