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
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

import br.ufes.inf.nemo.infotur.application.RDFService;
import br.ufes.inf.nemo.infotur.bean.ResultadoGeonames;
import br.ufes.inf.nemo.infotur.domain.Cidade;
import br.ufes.inf.nemo.infotur.domain.Pais;
import br.ufes.inf.nemo.infotur.enums.TipoLocal;

/**
 * MB para as funcionalidades de turismo.
 * 
 * @author vinicius
 * 
 */
@Named("turismoController")
@SessionScoped
public class TurismoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Cidade> listaCidadeBean = new ArrayList<Cidade>();
    private List<Pais> listaPaisesBean = new ArrayList<Pais>();
    private Cidade cidadeBean = new Cidade();
    private Pais paisBean = new Pais();
    private Pais paisSelecionado;
    private TipoLocal tipoLocal = TipoLocal.CIDADE;
    private transient String informacoesDBPedia;
    private transient List<ResultadoGeonames> informacoesGeonames;
   // private transient List<String> informacoesBancoRDF;
    private transient Writer recurso;

    @Inject
    private ManageCidadesController cidadesController;
    @Inject
    private ManagePaisesController paisesController;
    @Inject
    private ImagemController imagemController;
    @EJB
    private RDFService rdfService;

    @PostConstruct
    public void init() {
	listaCidadeBean = cidadesController.getCrudService().getDAO().retrieveAll();
	listaPaisesBean = paisesController.getCrudService().getDAO().retrieveAll();
    }

    /**
     * Salva uma nova cidade.
     */
    public void salvarCidade() {
	System.out.println("Salvando cidade " + cidadeBean.getNome());

	Cidade cidade = cidadesController.createNewEntity();
	cidade.setNome(cidadeBean.getNome());
	cidade.setDescricao(cidadeBean.getDescricao());
	cidade.setImagem(cidadeBean.getImagem());
	cidade.setPais(paisSelecionado);

	cidadesController.getCrudService().create(cidade);

	String mensagemSucesso = "Cidade " + cidadeBean.getNome() + " armazenada com sucesso!";

	System.out.println(mensagemSucesso);
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemSucesso, null));

	cidadeBean = new Cidade();
	imagemController.limparImagemTemp();
	paisSelecionado = null;
	listaCidadeBean.clear();
	listaCidadeBean = cidadesController.getCrudService().getDAO().retrieveAll();
    }
    
    /**
     * Salva um novo pais.
     */
    public void salvarPais() {
	System.out.println("Salvando pais " + paisBean.getNome());

	Pais pais = paisesController.createNewEntity();
	pais.setNome(paisBean.getNome());
	pais.setDescricao(paisBean.getDescricao());
	pais.setImagem(paisBean.getImagem());

	paisesController.getCrudService().create(pais);

	String mensagemSucesso = "Pais " + paisBean.getNome() + " armazenado com sucesso!";

	System.out.println(mensagemSucesso);
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemSucesso, null));

	paisBean = new Pais();
	imagemController.limparImagemTemp();
	listaPaisesBean.clear();
	listaPaisesBean = paisesController.getCrudService().getDAO().retrieveAll();
    }

    /**
     * Remove a cidade selcionada.
     * 
     * @param cidade
     *            - Cidade a ser removida.
     */
    public void removerCidade(Cidade cidade) {

	String nomeCidade = cidade.getNome();
	System.out.println("Removendo cidade " + nomeCidade);

	cidadesController.getCrudService().delete(cidade);

	String mensagemSucesso = "Cidade " + nomeCidade + " removida com sucesso!";

	System.out.println(mensagemSucesso);
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemSucesso, null));

	listaCidadeBean.clear();
	listaCidadeBean = cidadesController.getCrudService().getDAO().retrieveAll();
    }

    /**
     * Recebe a imagem via fileupload.
     * 
     * @param event
     */
    public void receberImagemPais(FileUploadEvent event) {
	try {

	    byte[] imagemRecebida = IOUtils.toByteArray(event.getFile().getInputstream());

	    paisBean.setImagem(imagemRecebida);
	    imagemController.setImagemTemp(imagemRecebida);
	} catch (Throwable ex) {

	    System.out.print("Erro ao receber a imagem via file upload: " + ex.getMessage());

	    FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ocorreu um erro no upload da imagem: "
		    + ex.getMessage(), null);

	    FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
    }

    /**
     * Recebe a imagem via fileupload.
     * 
     * @param event
     */
    public void receberImagemCidade(FileUploadEvent event) {
	try {

	    byte[] imagemRecebida = IOUtils.toByteArray(event.getFile().getInputstream());
	    System.out.println("Imagem recebida: " + imagemRecebida);

	    cidadeBean.setImagem(imagemRecebida);
	    imagemController.setImagemTemp(imagemRecebida);
	} catch (Throwable ex) {

	    System.out.print("Erro ao receber a imagem via file upload: " + ex.getMessage());

	    FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ocorreu um erro no upload da imagem: "
		    + ex.getMessage(), null);

	    FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
    }

    public void buscarConteudoExtraCidadeDBPedia(Cidade cidade) {

	informacoesDBPedia = rdfService.buscarInformacoesExtrasDBPedia(cidade.getNome());

	RequestContext.getCurrentInstance().execute("PF('extras-dbpedia').show()");
    }

    public void buscarConteudoExtraCidadeGeonames(Cidade cidade) {
	informacoesGeonames = rdfService.buscarInformacoesExtrasGeonames(cidade.getNome());

	RequestContext.getCurrentInstance().execute("PF('extras-geonames').show()");
    }

    public void exibirCidadesCadastradasRDF() {
	List<Cidade> cidades = listaCidadeBean;
	recurso = new StringWriter();
		
	// create an empty model
	Model model = ModelFactory.createDefaultModel();
	
	for (Cidade cidade : cidades) {
	    
	    Resource recursoCidade = model.createResource();
	    try {
		recursoCidade.addProperty(VCARD.NAME, cidade.getNome());

		recursoCidade.addProperty(VCARD.LABEL, cidade.getDescricao());
		
	    
		recursoCidade.addProperty(VCARD.Country, cidade.getPais().getNome());	    }
	    catch (Throwable e) {
		System.out.println("Erro ao transformar dados em RDF: " + e.getMessage());
	    }
			    
	}
	    model.write(recurso, "RDF/XML-ABBREV");		
	    model.write(System.out, "Turtle");	    
    }
	
    
    // Getters and Setters...
    public List<Cidade> getListaCidadeBean() {
	return listaCidadeBean;
    }

    public void setListaCidadeBean(List<Cidade> listaCidadeBean) {
	this.listaCidadeBean = listaCidadeBean;
    }

    public List<Pais> getListaPaisesBean() {
	return listaPaisesBean;
    }

    public void setListaPaisesBean(List<Pais> listaPaisesBean) {
	this.listaPaisesBean = listaPaisesBean;
    }

    public Cidade getCidadeBean() {
	return cidadeBean;
    }

    public void setCidadeBean(Cidade cidadeBean) {
	this.cidadeBean = cidadeBean;
    }

    public Pais getPaisBean() {
	return paisBean;
    }

    public void setPaisBean(Pais paisBean) {
	this.paisBean = paisBean;
    }

    public Pais getPaisSelecionado() {
	return paisSelecionado;
    }

    public void setPaisSelecionado(Pais paisSelecionado) {
	this.paisSelecionado = paisSelecionado;
    }

    public TipoLocal getTipoLocal() {
	return tipoLocal;
    }

    public void setTipoLocal(TipoLocal tipoLocal) {
	this.tipoLocal = tipoLocal;
    }

    public String getInformacoesDBPedia() {
	return informacoesDBPedia;
    }

    public List<ResultadoGeonames> getInformacoesGeonames() {
	return informacoesGeonames;
    }

    public Writer getRecurso() {
	exibirCidadesCadastradasRDF();
	return recurso;
    }
    
}
