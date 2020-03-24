package br.com.zup.payment.event.listener;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.payment.service.PaymentOrderProcessorService;
import br.com.zup.shared.event.WaitPaymentEvent;

@Configuration
public class WaitPaymentEventListener {

	private ObjectMapper objectMapper;

	private PaymentOrderProcessorService paymentOrderProcessorServiceImpl;

	public WaitPaymentEventListener(ObjectMapper objectMapper,
			PaymentOrderProcessorService paymentOrderProcessorServiceImpl) {
		super();
		this.objectMapper = objectMapper;
		this.paymentOrderProcessorServiceImpl = paymentOrderProcessorServiceImpl;
	}

	@KafkaListener(topics = "wait-payment", groupId = "payment-group-id")
	public void listen(String message) throws IOException {
		WaitPaymentEvent event = this.objectMapper.readValue(message, WaitPaymentEvent.class);

		paymentOrderProcessorServiceImpl.process(event);
	}
}
