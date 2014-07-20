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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.infotur.enums.TipoLocal;

@Named("configuracaoController")
@ApplicationScoped
public class ConfiguracaoController implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getDataHoraAtual() {
		Calendar calendar = Calendar.getInstance();
		return new SimpleDateFormat("dd/MM/yyyy HH:MM").format(calendar
				.getTime()) + "h";
	}
	
	public TipoLocal[] getTiposLocais() {
        return TipoLocal.values();
    }

}
