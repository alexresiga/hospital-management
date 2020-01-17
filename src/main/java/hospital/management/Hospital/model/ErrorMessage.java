package hospital.management.Hospital.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Data
public class ErrorMessage implements Serializable {
    private boolean error;
    private String errorMessage;
}
