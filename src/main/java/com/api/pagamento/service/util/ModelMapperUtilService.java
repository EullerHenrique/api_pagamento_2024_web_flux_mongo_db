package com.api.pagamento.service.util;

import org.modelmapper.ModelMapper;

public class ModelMapperUtilService {
    private ModelMapperUtilService() {
    }
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Object convert(Object origem, Class<?> destino) {
        return modelMapper.map(origem, destino);
    }
}
