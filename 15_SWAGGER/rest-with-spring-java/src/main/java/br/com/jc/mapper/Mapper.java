package br.com.jc.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;

//import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

   private static final com.github.dozermapper.core.Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    //private static final ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();

        origin.forEach(o -> {
            destinationObjects.add(mapper.map(o, destination));
        });

        return destinationObjects;
    }

}
