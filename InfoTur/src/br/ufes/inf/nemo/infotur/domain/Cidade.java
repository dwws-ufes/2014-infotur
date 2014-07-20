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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


/**
 * Classe de modelo Cidade.
 * 
 * @author vinicius
 * 
 */

@Entity
public class Cidade extends Local {

	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Pais pais;

	/**
	 * Construtor.
	 */
	public Cidade() {
	}

	/**
	 * Construtor.
	 * 
	 * @param pais
	 */
	public Cidade(Pais pais) {
		super();
		this.pais = pais;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
