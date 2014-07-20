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

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.geonames.Style;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import br.ufes.inf.nemo.infotur.bean.ResultadoGeonames;

import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

/**
 * Classe de serviços RDF.
 * 
 * @author vinicius
 * 
 */
@Stateless
public class RDFService {

    /**
     * Busca informações extras (abstract) no DBPedia usando SPARQL.
     * 
     * @param nomeLocal
     * @return
     */
    public String buscarInformacoesExtrasDBPedia(String nome) {

	String service = "http://dbpedia.org/sparql";
	StringBuilder sparqlQueryBuilder = new StringBuilder();

	// Remove acentos e substitui espacos em branco por underline.
	nome = nome.replace(" ", "_");
	nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
	nome = nome.replaceAll("[^\\p{ASCII}]", "");

	sparqlQueryBuilder.append(" SELECT ?abstract ");
	sparqlQueryBuilder.append(" WHERE {{ ");
	sparqlQueryBuilder.append("   <http://dbpedia.org/resource/" + nome + "> ");
	sparqlQueryBuilder.append("   <http://dbpedia.org/ontology/abstract> ");
	sparqlQueryBuilder.append("   ?abstract . ");
	sparqlQueryBuilder.append(" FILTER (");
	sparqlQueryBuilder.append("  langMatches(lang(?abstract), 'pt') || langMatches(lang(?abstract), 'en')");
	sparqlQueryBuilder.append(")}}");

	Query query = QueryFactory.create(sparqlQueryBuilder.toString());
	ARQ.getContext().setTrue(ARQ.useSAX);

	QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);

	String resultado = "Nenhuma informação extra encontrada.";

	try {
	    ResultSet results = qe.execSelect();

	    while (results.hasNext()) {
		QuerySolution sol = results.nextSolution();
		resultado = sol.get("?abstract").toString();
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    qe.close();
	}

	resultado = "Informações do DBPedia: \n\n" + resultado;

	return resultado;
    }

    public List<ResultadoGeonames> buscarInformacoesExtrasGeonames(String nome) {

	WebService.setUserName("infotur");

	ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
	searchCriteria.setName(nome);
	searchCriteria.setNameEquals(nome);
	searchCriteria.setStyle(Style.FULL);

	ToponymSearchResult searchResult;
	List<ResultadoGeonames> resultado = new ArrayList<ResultadoGeonames>();
	
	try {
	    searchResult = WebService.search(searchCriteria);
	    
	    for (Toponym toponym : searchResult.getToponyms()) {			
		
		ResultadoGeonames rg = new ResultadoGeonames();
		
		rg.setNome(toponym.getName());
		rg.setNomesAlternativos(toponym.getAlternateNames());
		rg.setLatitude(toponym.getLatitude());
		rg.setLongitude(toponym.getLongitude());
		rg.setPopulacao(toponym.getPopulation());
		
		resultado.add(rg);			
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return resultado;
    }
}
