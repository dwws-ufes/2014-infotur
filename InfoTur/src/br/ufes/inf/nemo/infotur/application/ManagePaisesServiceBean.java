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
package br.ufes.inf.nemo.infotur.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.infotur.domain.Pais;
import br.ufes.inf.nemo.infotur.persistence.PaisDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManagePaisesServiceBean extends
		CrudServiceBean<Pais> implements ManagePaisesService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PaisDAO paisDAO;
		
	
	@Override
	public BaseDAO<Pais> getDAO() {
		return paisDAO;
	}
	
	@Override
	protected Pais createNewEntity() {
		return new Pais();
	}
	
}
