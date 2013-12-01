package br.com.idecaph.client.presenter;

import java.util.List;

import br.com.idecaph.client.display.CabecalhoAdministracaoDisplay;
import br.com.idecaph.client.eventos.EventoListarPesquisas;
import br.com.idecaph.client.eventos.EventoLogout;
import br.com.idecaph.client.eventos.EventoNovaPesquisa;
import br.com.idecaph.client.eventos.EventoRelatorios;
import br.com.idecaph.client.interfaces.PesquisaService;
import br.com.idecaph.client.interfaces.PesquisaServiceAsync;
import br.com.idecaph.client.utils.FuncionariosHelper;
import br.com.idecaph.shared.PesquisaClient;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CabecalhoAdministracaoPresenter extends
		Presenter<CabecalhoAdministracaoDisplay> implements CabecalhoPresenter {
	private static HandlerRegistration handler;

	private PesquisaServiceAsync rpcService = GWT.create(PesquisaService.class);

	public CabecalhoAdministracaoPresenter(
			CabecalhoAdministracaoDisplay display, HandlerManager eventBus) {
		super(display, eventBus);
	}

	@Override
	public void bind() {
		CabecalhoAdministracaoDisplay display = super.getDisplay();

		HasClickHandlers funcionarios = display.getLinkFuncionarios();
		funcionarios.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				carregaTelaFuncionarios();
			}
		});

		HasClickHandlers pesquisas = display.getLinkPesquisas();
		pesquisas.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EventoNovaPesquisa(null));
			}
		});

		HasClickHandlers relatorios = display.getLinkRelatorios();
		relatorios.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EventoRelatorios());
			}
		});

		HasClickHandlers responderPesquisa = display.getLinkResponderPesquisa();
		responderPesquisa.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				listarPesquisas();
			}
		});

		if (handler != null) {
			handler.removeHandler();
		}

		HasClickHandlers logout = display.getLinkLogout();
		handler = logout.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EventoLogout());
			}
		});
	}

	private void carregaTelaFuncionarios() {
		FuncionariosHelper helper = new FuncionariosHelper(eventBus, true);
		helper.getFuncionarios();
	}

	private void listarPesquisas() {
		rpcService
				.getPesquisasPorParticipante(new AsyncCallback<List<PesquisaClient>>() {

					@Override
					public void onSuccess(List<PesquisaClient> result) {
						eventBus.fireEvent(new EventoListarPesquisas(result));
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Erro ao carregar as pesquisas existentes. Por favor, contate o administrador do sistema.");
					}
				});
	}

}
