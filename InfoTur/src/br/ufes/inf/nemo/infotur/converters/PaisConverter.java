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
package br.ufes.inf.nemo.infotur.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.ufes.inf.nemo.infotur.control.ManagePaisesController;
import br.ufes.inf.nemo.infotur.domain.Pais;

@FacesConverter("paisConverter")
public class PaisConverter implements Converter {

    @Inject
    private ManagePaisesController paisesController;

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
	try {	        	    
	    Pais pais = paisesController.getCrudService().getDAO().retrieveById(Long.valueOf(string));
	    return pais;
	} catch (Throwable ex) {
	    ex.printStackTrace();
	    return null;
	}
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
	try {	    
	    return String.valueOf(((Pais) obj).getId());
	} catch (Throwable ex) {
	    ex.printStackTrace();
	    return null;
	}
    }

}
