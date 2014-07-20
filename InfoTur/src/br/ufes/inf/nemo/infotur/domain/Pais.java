/**
 *  Copyright 2014 Erick Casagrande Bastos - Vinícius Soares Fonseca.
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
﻿ * ﻿ you may not use this file except in compliance with the License.
﻿ * ﻿ You may obtain a copy of the License at
 *
﻿ * ﻿ http://www.apache.org/licenses/LICENSE-2.0
 *
﻿ *﻿  Unless required by applicable law or agreed to in writing, software
﻿ * ﻿ distributed under the License is distributed on an "AS IS" BASIS,
﻿ *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
﻿ * ﻿ See the License for the specific language governing permissions and
 * ﻿ limitations under the License.  
 */
package br.ufes.inf.nemo.infotur.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Classe de modelo Pais
 * 
 * @author vinicius
 * 
 */
@Entity
public class Pais extends Local {

	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="pais")
	private List<Cidade> cidades;

	/**
	 * Construtor.
	 */
	public Pais() {
	}

	/**
	 * Construtor.
	 * 
	 * @param cidades
	 */
	public Pais(List<Cidade> cidades) {
		super();
		this.cidades = cidades;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

}
