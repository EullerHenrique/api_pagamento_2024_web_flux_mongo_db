package com.api.pagamento.config.model_mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Realiza um estorno
     *
     * @author Euller Henrique
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
