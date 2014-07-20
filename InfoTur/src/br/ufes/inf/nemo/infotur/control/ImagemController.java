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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.ufes.inf.nemo.infotur.domain.Cidade;
import br.ufes.inf.nemo.infotur.domain.Pais;
import br.ufes.inf.nemo.infotur.persistence.CidadeDAO;
import br.ufes.inf.nemo.infotur.persistence.PaisDAO;

/**
 * Bean auxiliar para renderizar a imagem dos formulários.
 * 
 * @author Vinicius
 */
@Named("imagemController")
@ApplicationScoped
public class ImagemController implements Serializable {

    private static final long serialVersionUID = 1L;
    private byte[] imagemTemp;

    @EJB
    private CidadeDAO cidadeDAO;
    @EJB
    private PaisDAO paisDAO;

    /**
     * Método chamado pelos formulários para exibir as imagens de cidades.
     * 
     * @return StreamedContent
     */
    public synchronized StreamedContent getImagemCidade() {
	try {
	    if (FacesContext.getCurrentInstance().getRenderResponse()) {
		// Fase RenderResponse. Fará outra requisicao.
		return getImagemPadrao();
	    } else {
		// Confere se o ID da cidade foi informado como parâmetro.
		if (!FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("id"))
		    return getImagemPadrao();

		// Busca a cidade no banco.
		Long id = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
		Cidade cidade = cidadeDAO.retrieveById(id);

		if (cidade.getImagem() == null)
		    return getImagemPadrao();

		// Exibe a imagem da cidade.
		return new DefaultStreamedContent(new ByteArrayInputStream(cidade.getImagem()));

	    }
	} catch (Throwable e) {
	    System.out.println("Erro ao carregar a imagem: " + e.getMessage());

	    // Carrega a imagem padrão
	    return getImagemPadrao();
	}
    }

    /**
     * Método chamado pelos formulários para exibir as imagens de países.
     * 
     * @return StreamedContent
     */
    public synchronized StreamedContent getImagemPais() {
	try {
	    if (FacesContext.getCurrentInstance().getRenderResponse()) {
		// Fase RenderResponse. Fará outra requisicao.
		return getImagemPadrao();
	    } else {
		// Confere se o ID da cidade foi informado como parâmetro.
		if (!FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("id"))
		    return getImagemPadrao();

		// Busca a cidade no banco.
		Long id = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
		Pais pais = paisDAO.retrieveById(id);

		if (pais.getImagem() == null)
		    return getImagemPadrao();

		// Exibe a imagem da cidade.
		return new DefaultStreamedContent(new ByteArrayInputStream(pais.getImagem()));

	    }
	} catch (Throwable e) {
	    System.out.println("Erro ao carregar a imagem: " + e.getMessage());

	    // Carrega a imagem padrão
	    return getImagemPadrao();
	}
    }
    
    
    /**
     * Método para retornar a imagem temporária.
     * 
     * @return StreamedContent - Imagem temporária.
     */
    public synchronized StreamedContent getImagemTemp() {
	
	if (imagemTemp == null)
	    return getImagemPadrao();

	// Carrega a imagem recebida pelo FileUploadEvent
	return new DefaultStreamedContent(new ByteArrayInputStream(imagemTemp));
    }

    /**
     * Método para configurar a foto temporaria a ser exibida;
     * 
     * @param fotoTemporaria
     */
    public void setImagemTemp(byte[] fotoTemporaria) {
	imagemTemp = fotoTemporaria;
    }

    /**
     * Método para limpar a foto temporária.
     */
    public void limparImagemTemp() {
	imagemTemp = null;
    }

    /**
     * Método para retornar a imagem padrão.
     * 
     * @return StreamedContent - Imagem padrão.
     */
    private StreamedContent getImagemPadrao() {
	InputStream semFotoStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/no-image.jpeg");
	return new DefaultStreamedContent(semFotoStream, "image/jpeg", "no-image");
    }
}
