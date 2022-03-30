package uz.jurayev.academy.util.responsemapper;

import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.classificators.CreativePotentialCategory;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.CreativePotential;
import uz.jurayev.academy.util.Mapper;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreativePotentialResponseMapper implements Mapper<List<CreativePotentialCategory>, List<CreativePotential>,List<CreativePotential>> {

    @Override
    public List<CreativePotential> mapFrom(List<CreativePotentialCategory> entity) {

        List<CreativePotential> list = new ArrayList<>();

        entity.forEach(e -> {
                    CreativePotential creativePotentialDto = new CreativePotential();
                    List<String> category = new ArrayList<>();
                    category.add(e.getValue());
                    creativePotentialDto.setCategory(category);
                    creativePotentialDto.setType(e.getCreativePotential().getType());
                        list.add(creativePotentialDto);

    });
        return list;
}
}
