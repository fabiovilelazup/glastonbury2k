package br.com.zup.payment.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.zup.payment.entity.Customer;
import br.com.zup.payment.service.CustomerService;
import br.com.zup.payment.service.PaymentOrderProcessorService;
import br.com.zup.shared.event.AbstractOrderEvent;
import br.com.zup.shared.event.ApprovedOrderPaymentEvent;
import br.com.zup.shared.event.RejectedOrderPaymentEvent;
import br.com.zup.shared.event.WaitPaymentEvent;

@Service
public class PaymentOrderProcessorServiceImpl implements PaymentOrderProcessorService {

	private CustomerService customerService;
	private KafkaTemplate<String, AbstractOrderEvent> kafkaTemplate;

	@Autowired
	public PaymentOrderProcessorServiceImpl(CustomerService customerService,
			KafkaTemplate<String, AbstractOrderEvent> kafkaTemplate) {
		this.customerService = customerService;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void process(WaitPaymentEvent waitPaymentEvent) {

		Customer customer = customerService.findById(waitPaymentEvent.getCustomerId());

		if (customer.getBalance().equals(BigDecimal.ZERO)
				|| (customer.getBalance().compareTo(waitPaymentEvent.getAmount()) <= -1)) {

			this.kafkaTemplate.send("rejected-orders-payment", new RejectedOrderPaymentEvent(
					waitPaymentEvent.getOrderId(), waitPaymentEvent.getCustomerId(), waitPaymentEvent.getAmount(),waitPaymentEvent.getItems()));
		} else {

			customer.setBalance(customer.getBalance().subtract(waitPaymentEvent.getAmount()));
			customerService.update(customer);

			this.kafkaTemplate.send("approved-orders-payment", new ApprovedOrderPaymentEvent(
					waitPaymentEvent.getOrderId(), waitPaymentEvent.getCustomerId(), waitPaymentEvent.getAmount()));
		}
	}
}
