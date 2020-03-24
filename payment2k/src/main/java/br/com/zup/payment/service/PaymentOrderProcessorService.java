package br.com.zup.payment.service;

import br.com.zup.shared.event.WaitPaymentEvent;

public interface PaymentOrderProcessorService {

	void process(WaitPaymentEvent waitPaymentEvent);
}
