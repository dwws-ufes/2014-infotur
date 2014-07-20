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

import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;


public class HelloRDFWorld {
    public static void main(String[] args) {
	    String service = "http://dbpedia.org/sparql";
	    String sparqlQueryString = " SELECT ?abstract " +
                    " WHERE {{ " +
                    "   <http://dbpedia.org/resource/Vila_Velha> " +
                    "      <http://dbpedia.org/ontology/abstract> " +
                    "          ?abstract . "
                    + "FILTER langMatches( lang(?abstract), 'pt')}}";
	    Query query = QueryFactory.create(sparqlQueryString);
	    ARQ.getContext().setTrue(ARQ.useSAX);
	    QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
	    try {
	        ResultSet results = qe.execSelect();

	        while (results.hasNext()) {

	            
	            QuerySolution sol = results.nextSolution();

	            System.out.println(sol.get("?abstract"));
	        	    
	        }

	    }catch(Exception e){

	        e.printStackTrace();
	    }
	    finally {

	       qe.close();
	    }
	} // end method
} // end class

/*
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.sparql.engine.http.QueryExceptionHTTP;

public class HelloRDFWorld {

    public static void main(String[] args) {
        String service = "http://dbpedia.org/sparql";
        String query = "ASK { }";
        QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
        try {
            if (qe.execAsk()) {
                System.out.println(service + " is UP");
            } // end if
        } catch (QueryExceptionHTTP e) {
            System.out.println(service + " is DOWN");
        } finally {
            qe.close();
        } // end try/catch/finally
    } // end method

} // end class
*/