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
package br.ufes.inf.nemo.infotur.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.infotur.domain.Cidade;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class CidadeJPADAO extends BaseJPADAO<Cidade> implements CidadeDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Class<Cidade> getDomainClass() {
		return Cidade.class;
		
	}
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
		
	}
}
