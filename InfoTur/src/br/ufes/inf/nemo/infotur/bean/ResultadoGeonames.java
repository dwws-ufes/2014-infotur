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
package br.ufes.inf.nemo.infotur.bean;

public class ResultadoGeonames {

    private String nome;
    private String nomesAlternativos;
    private Double latitude;
    private Double longitude;
    private Long populacao;

    public ResultadoGeonames() {

    }

    public ResultadoGeonames(String nome, String nomesAlternativos, Double latitude, Double longitude, Long populacao) {
	super();
	this.nome = nome;
	this.nomesAlternativos = nomesAlternativos;
	this.latitude = latitude;
	this.longitude = longitude;
	this.populacao = populacao;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getNomesAlternativos() {
	return nomesAlternativos;
    }

    public void setNomesAlternativos(String nomesAlternativos) {
	this.nomesAlternativos = nomesAlternativos;
    }

    public Double getLatitude() {
	return latitude;
    }

    public void setLatitude(Double latitude) {
	this.latitude = latitude;
    }

    public Double getLongitude() {
	return longitude;
    }

    public void setLongitude(Double longitude) {
	this.longitude = longitude;
    }

    public Long getPopulacao() {
	return populacao;
    }

    public void setPopulacao(Long populacao) {
	this.populacao = populacao;
    }

}
