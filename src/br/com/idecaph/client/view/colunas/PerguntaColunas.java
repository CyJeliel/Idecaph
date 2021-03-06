package br.com.idecaph.client.view.colunas;

import java.util.ArrayList;
import java.util.List;

import br.com.idecaph.shared.Pergunta;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class PerguntaColunas extends ListaColunas {
	private List<Coluna<Pergunta>> definicoesColunas = new ArrayList<Coluna<Pergunta>>();

	public PerguntaColunas() {
		super("Não há perguntas cadastradas.");
		definicoesColunas.add(new Coluna<Pergunta>() {
			boolean selecionado = false;

			@Override
			public Widget render(Pergunta dado) {
				CheckBox checkBox = new CheckBox();
				checkBox.setValue(selecionado);
				return checkBox;
			}

			public boolean isSelectable() {
				return true;
			}

			@Override
			public void setIsSelecionado(boolean selecionado) {
				this.selecionado = selecionado;
			}

		});
		definicoesColunas.add(new Coluna<Pergunta>() {

			@Override
			public Widget render(Pergunta dado) {
				return new HTML(dado.getDisplayPergunta());
			}
		});
		definicoesColunas.add(new Coluna<Pergunta>() {

			@Override
			public Widget render(Pergunta dado) {
				Image image = new Image("/img/editar.png");
				image.setTitle("Editar pergunta");
				return image;
			}

			public boolean isClickable() {
				return true;
			}
		});
		definicoesColunas.add(new Coluna<Pergunta>() {

			@Override
			public Widget render(Pergunta dado) {
				Image image = new Image("/img/excluir.png");
				image.setTitle("Excluir pergunta");
				return image;
			}

			public boolean isClickable() {
				return true;
			}
		});
	}

	public List<Coluna<Pergunta>> getDefinicoesColunas() {
		return definicoesColunas;
	}
}
