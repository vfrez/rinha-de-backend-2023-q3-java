Testes feitos em uma maquina 
CPU FX-8300
RAM 16gb DD3 1333mhz
armazenamento SSD
---
## Terceira versão

Criação de coluna para armazenar dados das colunas apelido, nome e stack para facilitar a consulta por termo, não melhorou a performance. =(

- Criação de cache local para fazer brincadeiras sem acessar base de dados com frequência. 
  - Agora ao criar um dado na API de criação, o mesmo será armazenado em cache.
  - API de busca por ID usará os dados do cache q a API de criação gerou, sem acessar o banco.
  - API de busca por termo usará os dados do cache q a API de criação gerou, sem acessar o banco.

#### Quantidade de registros criados -> 18.629 ~ 18.917
![Resultado 3](.\resultado3.png)

---
## Segunda versão

Ajustes de validação e response da location na criação
- Retornando Id da pessoa no HEADER da API de criação. 
- Ajustes na validação dos itens da lista de stack. 
  - Cada item pode ter no máximo 32 caracteres 
  - Não pode ter item nulo
  - Ajuste de tamanho da tabela no banco de dados

#### Quantidade de registros criados -> 17.555

![Resultado 2](.\resultado2.png)

---

## Primeira versão
![Resultado 1](.\resultado1.png "Resultado 1")