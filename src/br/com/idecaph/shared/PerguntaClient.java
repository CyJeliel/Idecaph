package br.com.idecaph.shared;

import br.com.idecaph.client.view.colunas.InformacaoTabela;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PerguntaClient extends InformacaoTabela implements IsSerializable {
	private String pergunta;
	private boolean selecionada;
	private Long id;

	public PerguntaClient() {
	}

	public PerguntaClient(Long id, String pergunta) {
		this.id = id;
		this.pergunta = pergunta;
	}

	public String getPergunta() {
		return pergunta;
	}

	public String getDisplayPergunta() {
		return pergunta;
	}

	public void seleciona() {
		this.selecionada = true;
	}

	public boolean isSelecionada() {
		return selecionada;
	}

	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		if (obj instanceof PerguntaClient) {
			PerguntaClient pergunta = (PerguntaClient) obj;
			if (pergunta.getPergunta().equals(this.pergunta)) {
				retorno = true;
			}
		}
		return retorno;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}