package br.com.zup.order.orchestrator.configuration;

import javax.ws.rs.BadRequestException;

import org.camunda.bpm.engine.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.order.OrderApi;
import feign.Feign;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Configuration
public class FeignConfig {

	private String inventoryUrl;
	private String orderUrl;
	private ObjectMapper objectMapper;

	public FeignConfig(@Value(value = "${inventory.url}") String inventoryUrl,
			@Value(value = "${order.url}") String orderUrl, ObjectMapper objectMapper) {
		this.inventoryUrl = inventoryUrl;
		this.orderUrl = orderUrl;
		this.objectMapper = objectMapper;
	}

	@Bean
    public ErrorDecoder errorDecoder() {
		return new ErrorDecoder() {

			@Override
            public Exception decode(String methodKey, Response response) {
         
                switch (response.status()){
                    case 400:
                        return new BadRequestException();
                    case 404:
                        return new NotFoundException();
                    default:
                        return new Exception("Generic error");
                }
            }
		};
    }

	@Bean
	public InventoryApi inventoryApi() {
		return Feign.builder().encoder(new JacksonEncoder(objectMapper)).decoder(new JacksonDecoder(objectMapper))
				.errorDecoder(errorDecoder()).logLevel(feign.Logger.Level.FULL)
				.target(InventoryApi.class, inventoryUrl);
	}

	@Bean
	public OrderApi orderApi() {
		return Feign.builder().encoder(new JacksonEncoder(objectMapper)).decoder(new JacksonDecoder(objectMapper))
				.errorDecoder(errorDecoder()).logLevel(feign.Logger.Level.FULL).target(OrderApi.class, orderUrl);
	}
}
