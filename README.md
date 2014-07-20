InfoTur - Informações Turísticas
================================
```
   Copyright 2014 Erick Casagrande Bastos - Vinícius Soares Fonseca
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
###1 - Descrição

O InfoTur é uma aplicação para cadastro de lugares turísticos criada pelos alunos Erick Casagrande Bastos e Vinícius Soares Fonseca. Esta aplicação foi criada como trabalho da disciplina de Desenvolvimento Web e Web Semântica, lecionada no Mestrado em Informática	da PPGI/UFES.

No menu Locais Turísticos é possível ver todos os locais turísticos cadastrados e realizar uma busca de informações 
extras sobre cada local através de uma consulta SPARQL na bases de dados abertas DBPedia e Geonames.

Através do menu Cadastro de Locais é possível cadastrar locais de turismo, informando o nome de um local, 
sua descrição e fazer upload de uma imagem.

O menu Locais em RDF exibe a representação da base de dados do InfoTur no padrão Resource Description Framework - RDF. 

###2 - Instruções de instalação
####2.1 - Ambiente

- WildFly Runtime Server 8.1.0.CR2
- IDE Eclipse Kepler - Version: Kepler Service Release 2 - Build id: 20140224-0627
- MySQL Server 5.5.37
- Acesso à Internet (para consultas na DBPedia e Geonames)

####2.2 - Banco de dados

1.  Criar um esquema chamado infotur.
2.  Criar um usuário chamado infotur (senha infotur).
3.  Atribuír controle total (GRANT ALL) a esse esquema para o usuário infotur.

####2.3 - Servidor de aplicação

1.  Criar um DataSource JTA informando os dados do passo 2.2 com o nome: java:/jboss/datasources/InfoTur

#### 2.4 - Execução

1.  Fazer a construção e implantação do InfoTur no servidor WildFly através da IDE Eclipse.

 




