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
package br.ufes.inf.nemo.infotur.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.fileupload.FileUploadRenderer;

/**
 * Implementação de renderer para corrigir bug do FileUploadRenderer do
 * Primefaces
 * 
 * Fonte:
 * http://stackoverflow.com/questions/19262356/file-upload-doesnt-work-with
 * -ajax-in-primefaces-4-0-running-on-jsf-2-2-x/19752138#19752138
 * 
 * @author vinicius
 * 
 */
public class InfoturFileUploadRenderer extends FileUploadRenderer {

	@Override
	public void decode(FacesContext context, UIComponent component) {
		if (context.getExternalContext().getRequestContentType().toLowerCase()
				.startsWith("multipart/")) {
			super.decode(context, component);
		}
	}

}