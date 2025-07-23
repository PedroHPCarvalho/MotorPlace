# Arquitetura do Projeto MotorPlace

## Visão Geral da Arquitetura

O projeto **MotorPlace** foi estruturado utilizando uma arquitetura modular e separação por responsabilidades, buscando escalabilidade, manutenção facilitada e organização clara entre os domínios da aplicação.

Ele é composto por três camadas principais:

1. **Frontend (web-front)**: Interface web desenvolvida em React.js.
2. **Backend For Frontend (bff-back)**: Camada intermediária que orquestra as chamadas entre frontend e core.
3. **Core (core-back)**: Contém as regras de negócio da aplicação, estruturado como uma API REST em Java com Spring Boot.

Essa arquitetura promove baixo acoplamento e alta coesão entre as partes, facilitando evolução e testes de forma isolada.

---

## Organização dos Repositórios e Pastas

A estrutura dos diretórios segue a seguinte hierarquia:

```
MotorPlace/
├── web-front/       # Projeto React (frontend)
├── bff-back/        # Projeto Java Spring Boot (BFF)
└── core-back/       # Projeto Java Spring Boot (Core)
```

Cada um desses projetos é independente e possui sua própria configuração e ciclo de build. Isso permite o desenvolvimento desacoplado de funcionalidades.

### Estrutura Interna do core-back e bff-back

```
core-back/
├── src/
│   ├── main/
│   │   ├── java/com/motorplace/core/    # Pacotes da aplicação
│   │   └── resources/                   # Configurações (application.yml, etc.)
│   └── test/                            # Testes automatizados
├── pom.xml                              # Dependências Maven
```

### Estrutura Interna do web-front

```
web-front/
├── public/              # Arquivos públicos
├── src/
│   ├── assets/          # Imagens e estilos
│   ├── components/      # Componentes reutilizáveis
│   ├── pages/           # Páginas da aplicação
│   ├── services/        # Consumo de APIs
│   └── App.jsx          # Componente principal
├── package.json         # Dependências Node
```

---

## Tecnologias Utilizadas

### Frontend (web-front)

- React.js
- Axios
- Vite
- Tailwind CSS (ou outra lib de estilização, conforme escolha do time)

### BFF (bff-back)

- Java 17
- Spring Boot
- Spring Web
- Spring DevTools
- Lombok
- (Futuramente) Spring Security

### Core (core-back)

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok
- MapStruct (opcional para mapeamento entre DTOs e entidades)
- Flyway ou Liquibase (opcional para versionamento de banco)

---

## Integração entre camadas

- O **frontend** se comunica exclusivamente com o **BFF** por meio de chamadas HTTP (REST).
- O **BFF** atua como uma ponte, realizando chamadas internas para o **core**, podendo aplicar lógicas de adaptação ou agregação de dados.

Essa separação favorece:

- Flexibilidade para mudanças de UI sem impactar diretamente a lógica de negócio.
- Segurança e adaptação de contratos REST de forma isolada no BFF.

---

## Boas Práticas de Organização

### Backend

- Separar claramente `controller`, `service`, `repository` e `model`.
- Usar DTOs para comunicação entre camadas.
- Utilizar injeção de dependência via `@Service`, `@Repository`, `@RestController`.
- Configurações sensíveis em arquivos `.yml`.
- Reaproveitamento de lógica comum em `utils` ou `helpers`.

### Frontend

- Dividir o projeto por domínio ou tipo de componente.
- Componentes reutilizáveis devem ficar em `components`.
- Evitar lógica de negócio diretamente nas páginas.
- Utilizar serviços centralizados (`services/`) para chamadas ao backend.

### Geral

- Comentários claros e objetivos.
- Padrão de commits definido no `CONTRIBUTING.md`.
- Testes unitários nas camadas de lógica.

---

## Considerações Finais

A arquitetura modular adotada no projeto **MotorPlace** proporciona uma base sólida e escalável. Com os papéis bem definidos entre frontend, BFF e core, a equipe pode evoluir as partes de forma isolada, garantindo qualidade e clareza no desenvolvimento.

Esse documento deve ser mantido atualizado conforme novas decisões arquiteturais forem adotadas no projeto.

