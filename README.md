# order-management-test

## Ingestão de Dados do Produto Externo A

Para realizar a ingestão de alto volume de dados do produto externo A utilizei a arquitetura de microsserviços com kafka.  
A ingestão de dados a partir do kafka pode ser escalada horizontalmente aumentando o número de instâncias do microsserviço, desde que o número de partições do tópico seja maior ou igual ao número de consumidores.
(Se o número de partições for menor que o número de consumidores, o paralelismo do consumo será limitado pelo número de partições).

## Banco de Dados

Para este projeto escolhi o banco de dados PostgreSQL.  
Segundo o benchmark abaixo, ele realiza seguramente mais de 2000 operações por segundo em uma carga de trabalho OLTP típica, 
sendo mais do que suficiente para atender a demanda de 200k pedidos por dia.  
Também é mais eficiente que o MongoDB para esse tipo e volume de trabalho.

PostgreSQL vs MongoDB benchmark: https://info.enterprisedb.com/rs/069-ALB-339/images/PostgreSQL_MongoDB_Benchmark-WhitepaperFinal.pdf

## Consistência e Concorrência

Considerando o cenário de ingestão concorrente, para garantir a consistência dos dados é recomendado que a chave da mensagem no kafka seja configurada com o id do pedido (orderId).  
No kafka, o particionamento das mensagens é realizado com um hash na chave da mensagem.  
Dessa maneira, garantimos que o consumo das mensagens de cada pedido será sempre feito pelo mesmo consumidor, de maneira sequencial, evitando problemas de concorrência e consistência.

## Consulta de Dados

Para a consulta de dados, assumi que será feita de maneira aleatória.  
Por isso decidi disponibilizar no formato REST.

## Garantindo a Disponibilidade do Serviço

O serviço de consumo de mensagens pode ser garantido por redundância dos microsserviços.  
O serviço de consultas está presente em todas as instâncias dos microsserviços também. 
Dessa forma eles atuam com redundância e podem ser colocados atrás de um load balancer para ser transparente ao cliente (produto externo b).

## Duplicação de Pedidos

Para o projeto não ficar muito complexo, estou apenas ignorando os pedidos que já foram recebidos.
Futuramente poderia ser implementada uma lógica de upsert.

## Observações

O projeto foi implementado de maneira simples e idiomática, se apoiando no uso do framework para melhor legibilidade e entendimento.  
Melhorias de performance pontuais podem ser feitas a partir do resultado de um teste de carga ou mensuração de performance em produção.