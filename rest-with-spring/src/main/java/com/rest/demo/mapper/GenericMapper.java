package com.rest.demo.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

public class GenericMapper {

    private static Mapper mapper = new DozerBeanMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<D>();
        origin.forEach(obj -> destinationObjects.add(mapper.map(obj, destination)));
        return destinationObjects;
    }
}
