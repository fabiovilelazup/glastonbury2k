package br.com.zup.inventory.configuration;

import org.springframework.context.annotation.Bean;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;

@org.springframework.context.annotation.Configuration
//TODO : Verificar questões de autoconfiguration, baseando nos arquivo de propriedades.
//@AutoConfigureBefore(io.opentracing.contrib.spring.tracer.configuration.TracerAutoConfiguration.class)
//@EnableConfigurationProperties(JaegerConfigurationProperties.class)
public class JaegerConfig {

	@Bean
	public static Tracer getTracer() {
		SamplerConfiguration samplerConfig = SamplerConfiguration.fromEnv().withType(ConstSampler.TYPE).withParam(1);

		SenderConfiguration senderConfiguration = SenderConfiguration.fromEnv().withAgentHost("jaeger")
				.withAgentPort(6831);

		ReporterConfiguration reporterConfig = ReporterConfiguration.fromEnv().withLogSpans(true)
				.withSender(senderConfiguration);

		Configuration config = new Configuration("inventory").withSampler(samplerConfig).withReporter(reporterConfig);

		Tracer tracer = config.getTracer();

		return tracer;
	}
}
