package com.api.pagamento.domain.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Converter {
	private final ModelMapper modelMapper;

	public <T> T originToDestiny(Object origem, Class<T> destino) {
		return modelMapper.map(origem, destino);
	}

	public <T> List<T> originToDestiny(List<?> origem, Class<T> destino) {
		return origem.stream().map(o -> modelMapper.map(o, destino)).toList();
	}
}
