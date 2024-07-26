## Considerações

- Foi utilizado apenas o Lombok e apenas na camada de infraestrutura para reduzir a quantidade de código boilerplate. Dessa forma, o projeto depende o mínimo possível de bibliotecas externas para seguir os padrões da arquitetura escolhida.

- O projeto não utiliza bibliotecas de conversão entre entidades como ModelMapper. Apesar do código ser mais verboso, ganhamos em simplicidade no entendimento e controle no processo de construção das entidades.

- Um script `insert.sql` com massa para testes esta localizado no diretório resouces do modulo `infrastructure`

## Resposta da Questão Aberta L4

- **Transações simultâneas:** Dado que o mesmo cartão de crédito pode ser utilizado em diferentes serviços online, existe uma pequena mas existente probabilidade de ocorrerem duas transações ao mesmo tempo. O que você faria para garantir que apenas uma transação por conta fosse processada em um determinado momento? Esteja ciente do fato de que todas as solicitações de transação são síncronas e devem ser processadas rapidamente (menos de 100 ms), ou a transação atingirá o timeout.

**Resposta:** Faria o uso de Locks pelo número da conta.
1. Em um sistema de única instância, podemos considerar o uso de locks da própria linguagem Java, como `synchronized` e `ReentrantLock`, pois possuem um bom desempenho já que não envolvem comunicação de rede e sua implementação é simples.

2. Para sistemas com alta concorrência, podemos considerar o uso de locks com Redis. São indicados para sincronização entre múltiplas instâncias e, se bem configurados e relativamente próximos dos servidores da aplicação, não terão uma latência adicional muito alta.

## Projeto

### Tecnologias
- Java 17
- Spring 3.1.3
- JUnit 5.8.1
- Hibernate
- H2 database

### Bibliotecas Externas
- Lombok

### Arquitetura
- Clean Architecture

### Endpoints
- `POST /api/transactions`

#### JSON de Entrada
```json
{
    "account": "123",
    "totalAmount": 100.00,
    "mcc": "5811",
    "merchant": "PADARIA DO ZE               SAO PAULO BR"
}
```
### Possíveis respostas

- `{ "code": "00" }` se a transação é **aprovada**
- `{ "code": "51" }` se a transação é **rejeitada**, porque não tem saldo suficiente
- `{ "code": "07" }` se acontecer qualquer outro problema que impeça a transação de ser processada

O HTTP Status Code é sempre `200`.
