package uz.jurayev.academy.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String message;
    private Boolean success;
    private Object object;

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
