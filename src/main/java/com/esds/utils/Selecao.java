package com.esds.utils;

import com.esds.enumeracoes.ComposicaoDomicilio;
import com.esds.enumeracoes.CondicaoDaResidencia;
import com.esds.enumeracoes.LocalidadeDomicilio;
import com.esds.enumeracoes.QuanMembrosIdosos;
import com.esds.enumeracoes.QuantMembrosCrianca;
import com.esds.enumeracoes.QuantMembrosFamilia;
import com.esds.enumeracoes.RendaPerCapita;
import com.esds.enumeracoes.TipoDeTrabalho;
import com.esds.modelo.DadosSocioEconomicos;

public class Selecao {
	
	private float pontuacao;
	
	public float calcularPontuacao(DadosSocioEconomicos dados) {
		
		this.pontuacao = 
				this.calcComposicaoDomicilio(dados.getComposicaoDomicilio()) +
				this.calcCondicaoResidencia(dados.getCondicaoResidencia()) +
				this.calcLocalidadeDomicilio(dados.getLocalidade()) +
				this.calcQuantMembrosCrianca(dados.getQuantMembrosCriancas()) +
				this.calcQuantMembrosFamilia(dados.getQuantMembrosFamilia()) +
				this.calcQuantMembrosIdosos(dados.getQuantMembrosIdosos()) +
				this.calcRendaPerCapita(dados.getRendaPerCapita()) +
				this.calcTipoDeTrabalho(dados.getTipoDeTrabalho()) +
				this.temBanheiro(dados) +
				this.aguaEncanada(dados) +
				this.energiaEletrica(dados)+
				this.coletaEsgoto(dados) +
				this.familiaIndigena(dados) +
				this.familiaQuilombola(dados) +
				this.membroEmpregado(dados) +
				this.doencaCronica(dados) +
				this.membroDeficiente(dados);
		
		return pontuacao;
				
	}
	
	private float calcComposicaoDomicilio(ComposicaoDomicilio composicao) {
		
		float valor = 0;
		
		switch (composicao) {
		case ALVENARIA:
			valor = 6;
			break;
		case MADEIRA:
			valor = 7;
			break;
		case TAIPA:
			valor = 7;
			break;
		case SITUACAO_DE_RUA:
			valor = 10;
			break;
		default:
			valor = 0;
			break;
		}
		return valor;
	}

	private float calcCondicaoResidencia(CondicaoDaResidencia condicao) {
		
		float valor = 0;
		
		switch (condicao) {
		case SITUACAO_DE_RUA:
			valor = 5;
			break;
		case ALUGADA:
			valor = 4;
			break;		
		case CEDIDA:
			valor = 4;
			break;
		case PROPRIA_QUITADA:
			valor = 2;
			break;
		case PROPRIA_FINANCIADA:
			valor = 2;
			break;
		default:
			valor = 0;
			break;
		}
		return valor;
	}
	
	private float calcLocalidadeDomicilio(LocalidadeDomicilio localidade) {
		
		float valor = 0;
		
		switch (localidade) {
		case URBANA:
			valor = 8;
			break;
		case RURAL:
			valor = 9;
			break;
		default:
			break;
		}
		return valor;
	}
	
	private float calcQuantMembrosFamilia(QuantMembrosFamilia familia) {
		
		float valor = 0;
		
		switch (familia) {
		case UM:
			valor = 1;
			break;
		case DOIS:
			valor = 2;
			break;		
		case TRES:
			valor = 3;
			break;
		case QUATRO:
			valor = 6;
			break;
		case ENTRE_CINCO_SETE:
			valor = 8;
			break;
		case ENTRE_OITO_DEZ:
			valor = 10;
			break;
		case MAIS_DE_DEZ:
			valor = 12;
			break;
		default:
			break;
		}
		return valor;
	}
	
	private float calcQuantMembrosIdosos(QuanMembrosIdosos idosos) {
		
		float valor = 0;
		
		switch (idosos) {
		case ZERO:
			valor = 0;
			break;
		case UM:
			valor = 2;
			break;
		case DOIS:
			valor = 4;
			break;		
		case TRES:
			valor = 6;
			break;
		case MAIS_DE_TRES:
			valor = 8;
			break;
		default:
			break;
		}
		return valor;
	}
	
	private float calcQuantMembrosCrianca(QuantMembrosCrianca criancas) {
		
		float valor = 0;
		
		switch (criancas) {
		case ZERO:
			valor = 0;
			break;
		case UM:
			valor = 5;
			break;
		case DOIS:
			valor = 6;
			break;		
		case TRES:
			valor = 7;
			break;
		case MAIS_DE_TRES:
			valor = 9;
			break;
		default:
			break;
		}
		return valor;
	}
	
	private float calcTipoDeTrabalho(TipoDeTrabalho tipoTrabalho) {
		
		float valor = 0;
		
		switch (tipoTrabalho) {
		case DESEMPREGADO:
			valor = 10;
			break;
		case INFORMAL:
			valor = 8;
			break;		
		case TEMPORARIO_RURAL:
			valor = 7;
			break;
		case AUTONOMO:
			valor = 6;
			break;
		case CARTEIRA_ASSINADA:
			valor = 5;
			break;
		case NAO_REMUNERADO:
			valor = 4;
			break;
		case FUNCIONARIO_PUBLICO:
			valor = 3;
			break;
		case EMPREGADOR:
			valor = 2;
			break;
		default:
			break;
		}
		return valor;
	}
	
	private float calcRendaPerCapita(RendaPerCapita rendaPerCapita) {
		
		float valor = 0;
		
		switch (rendaPerCapita) {
		case MENOR_UM_SM:
			valor = 10;
			break;
		case IGUAL_UM_SM:
			valor = 5;
			break;		
		case ATE_UM_SM_E_MEIO:
			valor = 4;
			break;
		case ENTRE_UM_E_TRES_SM:
			valor = 3;
			break;
		case ENTRE_TRES_E_CINCO_SM:
			valor = 2;
			break;
		case MAIOR_CINCO_SM:
			valor = 0;
			break;
		default:
			break;
		}
		return valor;
	}
	
	public float temBanheiro(DadosSocioEconomicos dados) {
		
		if(dados.isTemBanheiro()) {
			return 0;
		}else {
			return 1;
		}
		
	}

	public float aguaEncanada(DadosSocioEconomicos dados) {
		
		if(dados.isAguaEncanada()) {
			return 0;
		}else {
			return 1;
		}			
	}

	public float energiaEletrica(DadosSocioEconomicos dados) {
		if(dados.isEnergiaEletrica()) {
			return 0;
		}else {
			return 1;
		}
	}

	public float coletaEsgoto(DadosSocioEconomicos dados) {
		if(dados.isColetaEsgoto()) {
			return 0;
		}else {
			return 1;
		}
	}

	public float familiaIndigena(DadosSocioEconomicos dados) {
		if(dados.isFamiliaIndigena()) {
			return 1;
		}else {
			return 0;
		}
	}

	public float familiaQuilombola(DadosSocioEconomicos dados) {
		if(dados.isFamiliaQuilombola()) {
			return 1;
		}else {
			return 0;
		}
	}

	public float membroEmpregado(DadosSocioEconomicos dados) {
		if(dados.isMembroEmpregado()) {
			return 0;
		}else {
			return 1;
		}
	}

	public float doencaCronica(DadosSocioEconomicos dados) {
		if(dados.isDoencaCronica()) {
			return 1;
		}else {
			return 0;
		}	
	}

	public float membroDeficiente(DadosSocioEconomicos dados) {
		if(dados.isMembroDeficiente()) {
			return 1;
		}else {
			return 0;
		}
	}

	
	public float getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(float pontuacao) {
		this.pontuacao = pontuacao;
	}

	
}
