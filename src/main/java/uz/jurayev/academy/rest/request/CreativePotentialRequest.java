package uz.jurayev.academy.rest.request;

import lombok.*;
import java.util.List;

@Data
public class CreativePotentialRequest {

    private List<Integer> studentId;
    private List<String> categories;
    private String type;
}
