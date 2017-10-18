package br.jus.tjsp.movjud.web.configuracao.bean;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.configuracao.service.ConfiguracaoService;
import br.jus.tjsp.movjud.business.utils.helper.DateUtils;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.VariavelType;
import br.jus.tjsp.movjud.persistence.entity.Aviso;
import br.jus.tjsp.movjud.persistence.entity.ConfiguracaoAviso;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.persistence.entity.Variavel;

import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.text.ParseException;

import java.util.Date;

import java.util.HashMap;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.dms.table.Row;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

@ManagedBean(name = "apresentacaoAvisoBean")
@ViewScoped
public class ApresentacaoAvisoBean extends BaseBean<ConfiguracaoAviso> {

    private ConfiguracaoService configuracaoService;

    private List<Aviso> avisosUsuario;

    private String conteudoAvisoAtual;

    private String numeroMensagensNaoLidas;


    public ApresentacaoAvisoBean() {
        configuracaoService = getBean(ConfiguracaoService.class);
    }

    @PostConstruct
    public void init() {
        super.init();
        numeroMensagensNaoLidas();
    }
    
    public void numeroMensagensNaoLidas() {
        numeroMensagensNaoLidas = configuracaoService.countAvisosNaoLidos(getLoginBean().getUsuarioSessao()).toString();
    }


    private void carregarAvisosUsuario() {
        try {
            this.avisosUsuario =
                configuracaoService.carregarAvisosUsuario(getLoginBean().getUsuarioSessao(), paginacao);
        } catch (ParseException pe) {
        }
    }

    public void selecionarAvisoParaVisualizacao(SelectionEvent selectionEvent) {

        // EXIBE O CONTEUDO DO AVISO
        RichTable richTable = (RichTable) selectionEvent.getSource();
        Aviso avisoSelecionado = (Aviso) richTable.getSelectedRowData();


        if (avisoSelecionado.getFlagLido().equals(ConstantesMovjud.FLAG_SITUACAO_NAO)) {
            avisoSelecionado.setFlagLido(ConstantesMovjud.FLAG_SITUACAO_SIM);

            avisoSelecionado.setDataLido(new Date());
            configuracaoService.salvarAviso(avisoSelecionado);


            //RowKeySet rowSet = richTable.getSelectedRowKeys();
            atualizarComponenteDeTela(richTable);
            //richTable.setRowKey(rowSet);
        }
        conteudoAvisoAtual = recuperarConteudoAviso(avisoSelecionado);
        numeroMensagensNaoLidas();
    }

    private String recuperarConteudoAviso(Aviso aviso) {
        // RECUPERA O CONTEUDO DO AVISO E SUBSTITUI AS VARIAVEIS PELOS RESPECTIVOS VALORES
        conteudoAvisoAtual = aviso.getConfiguracaoAviso().getAvisoEstrutura().getDescricaoConteudo();
        for (Variavel variavel : aviso.getConfiguracaoAviso().getAvisoEstrutura().getTipoAviso().getVariaveis()) {
            if (VariavelType.ANO_ATUAL.getCodigo().equals(variavel.getNomeVariavel())) {
                conteudoAvisoAtual =
                    conteudoAvisoAtual.replaceAll(VariavelType.ANO_ATUAL.getCodigoRegex(),
                                                  DateUtils.yearInDate(aviso.getDataEnvio()));

            } else if (VariavelType.DATA_ATUAL.getCodigo().equals(variavel.getNomeVariavel())) {
                conteudoAvisoAtual =
                    conteudoAvisoAtual.replaceAll(VariavelType.DATA_ATUAL.getCodigoRegex(),
                                                  DateUtils.dateToStringddMMyyyy(aviso.getDataEnvio()));

            } else if (VariavelType.MES_ATUAL.getCodigo().equals(variavel.getNomeVariavel())) {
                conteudoAvisoAtual =
                    conteudoAvisoAtual.replaceAll(VariavelType.MES_ATUAL.getCodigoRegex(),
                                                  DateUtils.monthInDate(aviso.getDataEnvio()));

            } else if (VariavelType.USUARIO.getCodigo().equals(variavel.getNomeVariavel())) {
                conteudoAvisoAtual =
                    conteudoAvisoAtual.replaceAll(VariavelType.USUARIO.getCodigoRegex(),
                                                  getLoginBean().getUsuarioSessao().getNome());
            }
        }
        return conteudoAvisoAtual;
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        // TODO Implement this method
    }

    @Override
    public String pesquisarEntidade() {
        carregarAvisosUsuario();
        return null;
    }

    @Override
    public String excluirEntidade() {
        // TODO Implement this method
        return null;
    }

    @Override
    public Class<ConfiguracaoAviso> getClasseEntidade() {
        return ConfiguracaoAviso.class;
    }


    public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    public ConfiguracaoService getConfiguracaoService() {
        return configuracaoService;
    }

    public void setAvisosUsuario(List<Aviso> avisosUsuario) {
        this.avisosUsuario = avisosUsuario;
    }

    public List<Aviso> getAvisosUsuario() {
        return avisosUsuario;
    }

    public void setConteudoAvisoAtual(String conteudoAvisoAtual) {
        this.conteudoAvisoAtual = conteudoAvisoAtual;
    }

    public String getConteudoAvisoAtual() {
        return conteudoAvisoAtual;
    }


    public void setNumeroMensagensNaoLidas(String numeroMensagensNaoLidas) {
        this.numeroMensagensNaoLidas = numeroMensagensNaoLidas;
    }

    public String getNumeroMensagensNaoLidas() {
        return numeroMensagensNaoLidas;
    }


}
