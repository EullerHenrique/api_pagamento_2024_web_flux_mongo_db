package com.api.pagamento.service.util.http.response;

import com.api.pagamento.domain.dto.response.error.MessageErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HttpResponseUtilService {
	public ResponseEntity<Object> obterMessagerErrorResponse(int status, String error, String message) {
		MessageErrorResponseDto messageErrorResponseDto = MessageErrorResponseDto.builder().status(status).error(error).message(message)
				.build();
		return ResponseEntity.status(status).body(messageErrorResponseDto);
	}
}
