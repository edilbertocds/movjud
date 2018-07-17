package br.jus.tjsp.movjud.web.relatorio;

import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SubSecaoDTO;
import br.jus.tjsp.movjud.business.formulario.service.FormularioService;
import br.jus.tjsp.movjud.persistence.base.types.TipoSituacaoType;
import br.jus.tjsp.movjud.persistence.entity.Formulario;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.text.SimpleDateFormat;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

public class RelatorioFormulario extends RelatorioImpl {

    private EstruturaJudiciariaService estruturaJudiciariaService;
    private FormularioService formularioService;

    public RelatorioFormulario() {
        super();
        estruturaJudiciariaService = BaseBean.getBean(EstruturaJudiciariaService.class);
        formularioService = BaseBean.getBean(FormularioService.class);
    }

    public Map<String, Object> obterParametros(FormularioDTO formulario,SubSecaoDTO subSecaoProcessoConclusoDTO) {
        Map<String, Object> parametros = super.obterParametros();
        parametros.put("nomeFormulario", formulario.getNomeFormulario());
        parametros.put("nomeForo", formulario.getNomeForo());
        parametros.put("nomeUnidade",formulario.getNomeUnidade());
        parametros.put("mesAno",  formulario.getMes()+ "/" + formulario.getAno());
        parametros.put("codigo", formulario.getCodigoFormulario());
        parametros.put("versao", formulario.getVersao());
        parametros.put("subSecaoProcessoConclusoDTO", subSecaoProcessoConclusoDTO);
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy 'as' HH:mm:ss");
        
        if(formulario.getSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().equalsIgnoreCase(TipoSituacaoType.ENVIADO_CGJ.toString()) ||
           formulario.getSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_ENVIADA_AO_CGJ.toString())){
            Formulario formularioEntity = formularioService.recuperarFormularioPorIdFormulario(formulario.getIdFormulario());
            parametros.put("enviadoPor", formulario.getNomeMagistrado()+", em "+format.format(formularioEntity.getDataAtualizacao()));
        }else
            parametros.put("enviadoPor", "Formulário ainda não enviado");
        
        Unidade unidade = new Unidade(formulario.getIdUnidade());
        unidade = estruturaJudiciariaService.procurarUnidadePorCodigoUnidade(unidade);
        parametros.put("responsavelUnidade", unidade.getUsuario().getNome());
        
        parametros.put("situacaoFormulario", formulario.getSituacaoFormularioDTO().getLabelSituacaoFormulario());
        
        return parametros;
    }

    public JRDataSource obterDataSourceColecao(FormularioDTO formulario) {
        return listToDataSource(formulario.getListaSecoes());
    }
}
