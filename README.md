# PowerGuard - Sistema de Monitoramento de Queda de Energia 

## Visão Geral
Sistema para cadastro de usuários e registro de quedas de energia com integração à API ViaCEP para autocompletar endereços.

## Funcionalidades Principais
- **Cadastro de Usuários** com autocompletar de endereço via CEP
- **Registro de Quedas de Energia** com localização precisa
- **Consulta de Ocorrências** por região ou usuário
- **Validação Avançada** de CEPs 

## Tecnologias
- **Backend** : Java 17, Spring Boot 3, WebFlux
- **Banco** : H2 
- **Ferramentas** :  JPA
- **API Externa** : ViaCEP (consulta CEP) 

## Pré-requisitos
- JDK 17
- Maven 3.8
- Postman/Insomnia

## Instalação e Execução
```bash
git clone https://github.com/seu-usuario/powerguard.git
cd powerguard
mvn clean install
mvn spring-boot:run
```
## Endpoints
- GET: ``` /api/usuarios/{id} ``` (Busca usuário por ID)
- POST: ``` /api/usuarios ``` (Cadastra novo usuário)
  ```Json
  {
    "nome": "Danilo Urze",
    "email": "danilo@email.com",
    "telefone": "11988887777",
    "cpf": "98765432100",
    "cep": "01001000"
  }
  ```
- GET: ``` /api/queda-energia/por-cep ``` (Lista quedas por CEP)
- POST: ``` /api/queda-energia ``` (Reporta nova queda)
   ```Json
  {
    "descricao": "Falta de energia no condomínio",
    "userId": 1,
    "cepAfetado": "01311000",
    "dataHoraInicio": "2023-08-20T14:30:00",
    "dataHoraFim": null
  }
  ```

## Integrantes 
- Pedro Henrique Rm550689
- Danilo Urze Rm99465
- Gabriel Pacheco Rm550191
