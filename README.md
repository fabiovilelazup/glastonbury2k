Resumo das Implementações (Em construção)

1 - Observabillity

    Desafio!!!
    - A aplicação deverá apresentar o tracing da request no Jaeger. 
    - A aplicação deverá logar em formato Json utilizando logback contendo o campo spanId(opentracing) e que seja possível consultar esse log no Kibana.
    - Inicie um Prometheus com Docker e faça-o conectar na sua aplicação.

1.1 Trace

  Utilizar odocker-compose para subir o Jaeger.
  
1.2 Log

  Utilizar o docker-compose para subir a stack EFK.
  
1.3 Métricas

   Dica: atenção a rede do Docker.
