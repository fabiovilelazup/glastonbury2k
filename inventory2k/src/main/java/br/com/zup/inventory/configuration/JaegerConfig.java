package br.com.zup.inventory.configuration;

import org.springframework.context.annotation.Bean;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.opentracing.Tracer;

@org.springframework.context.annotation.Configuration
public class JaegerConfig {

	@Bean
	public static Tracer getTracer() {
		SamplerConfiguration samplerConfig = SamplerConfiguration.fromEnv()
				.withType("const").withParam(1);
		ReporterConfiguration reporterConfig = ReporterConfiguration.fromEnv()
				.withLogSpans(true);
		Configuration config = new Configuration("inventory").withSampler(samplerConfig)
				.withReporter(reporterConfig);
		return config.getTracer();
	}
}
