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
package br.ufes.inf.nemo.infotur.control;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import br.ufes.inf.nemo.infotur.application.ManageCidadesService;
import br.ufes.inf.nemo.infotur.domain.Cidade;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@ApplicationScoped
public class ManageCidadesController extends CrudController<Cidade> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageCidadesService manageCidadesService;

	
	@Override
	protected Cidade createNewEntity() {
		return new Cidade();
	}

	@Override
	protected CrudService<Cidade> getCrudService() {
		return manageCidadesService;
	}
	
	@Override
	protected void initFilters() {
	//	to do 
		//addFilter(new SimpleFilter("manageSemesters.filter.byYear", "year", getI18nMessage("msgs", "manageSemesters.text.filter.byYear")));
	}
}
