package uz.jurayev.academy.rest.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EduRequestInfo {

    private Integer id;
    private String category;
    private String level;
    private String description;
}
