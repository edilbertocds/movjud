package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Formulario;
import br.jus.tjsp.movjud.persistence.entity.PermissaoUnidadeTemporaria;
import br.jus.tjsp.movjud.persistence.entity.TipoSituacao;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;


import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class FormularioDAOImpl extends BaseDAOImpl<Formulario> implements FormularioDAO {
    public FormularioDAOImpl() {
        super();
    }

    @Override
    public List<Formulario> listarFormulariosPorCodigo(Formulario filter, Paginacao paginacao) {
        return listarComFiltroOrdenacao(filter, "metadadosFormulario.descricaoNome", true, paginacao);

    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Formulario filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
        if (filter.getMetadadosFormulario() != null) {
            parametros.add(new ParametroOperacao("metadadosFormulario.descricaoNome",
                                                 filter.getMetadadosFormulario().getDescricaoNome(),
                                                 SQLOperatorType.LikeFullNoCase));
        }
        if (filter.getUnidade() != null) {
            parametros.add(new ParametroOperacao("unidade.idUnidade", filter.getUnidade().getIdUnidade(),
                                                 SQLOperatorType.Equal));
            parametros.add(new ParametroOperacao("unidade.nomeUnidade", filter.getUnidade().getNomeUnidade(),
                                                 SQLOperatorType.LikeFullNoCase));
        }
        parametros.add(new ParametroOperacao("ano", filter.getAno(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("mes", filter.getMes(), SQLOperatorType.Equal));
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Formulario filter) {
        return Collections.emptyList();
    }

    @Override
    public Class<Formulario> getPersistentClass() {
        return Formulario.class;
    }

    @Override
    public List<Formulario> listarFormulariosOrdenadoPorDescricao(Formulario filter) {
        return listarComFiltroOrdenacao(filter, "metadadosFormulario.descricaoNome", true);
    }

    @Override
    public List<Formulario> listarFormulariosPorSituacoes(String... tiposSituacao) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select formulario from Formulario formulario where ");
        jpaQl.append(" formulario.tipoSituacao.codigoSituacao  in(");
        for (int i = 0; i < tiposSituacao.length; i++) {
            jpaQl.append("'").append(tiposSituacao[i]).append("'");
            if (i < tiposSituacao.length - 1) {
                jpaQl.append(",");
            }
        }
        jpaQl.append(")");
        return getPersistenceManager().listarPorJPQL(jpaQl);
    }

    @Override
    public List<Formulario> listarFormulariosDoUsuario(Formulario filtro, Usuario usuario, Paginacao paginacao) {
        List<Formulario> listaFormularios = new ArrayList<Formulario>();

        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select new br.jus.tjsp.movjud.persistence.entity.Formulario(metadadosFormulario, formVinculacao.unidade), " +
                     " formulario from MetadadosFormulario metadadosFormulario" +
                     " left join metadadosFormulario.formularios formulario" +
                     " inner join metadadosFormulario.formulariosVinculacao formVinculacao" +
                     " where metadadosFormulario.metadadosTipoSituacao.tipoSituacao = 'EM_USO'" +
                     " and (formulario.tipoSituacao is null or formulario.tipoSituacao.idTipoSituacao in (1,3,4))" +
                     " and formVinculacao.unidade.usuario.idUsuario = ?1");

        if (filtro.getMetadadosFormulario() != null && filtro.getMetadadosFormulario().getDescricaoNome() != null) {
            jpaQl.append(" and UPPER(formulario.metadadosFormulario.descricaoNome) like UPPER('%" +
                         filtro.getMetadadosFormulario().getDescricaoNome() + "%')");
        }
        if (filtro.getUsuarioAprovacao() != null && filtro.getUsuarioAprovacao().getNome() != null &&
            !filtro.getUsuarioAprovacao().getNome().isEmpty()) {
            jpaQl.append(" and UPPER(formulario.usuarioAprovacao.nome) like UPPER('%" +
                         filtro.getUsuarioAprovacao().getNome() + "%')");
        }
        if (filtro.getUsuarioPreenchimento() != null && filtro.getUsuarioPreenchimento().getNome() != null &&
            !filtro.getUsuarioPreenchimento().getNome().isEmpty()) {
            jpaQl.append(" and UPPER(formulario.usuarioPreenchimento.nome) like UPPER('%" +
                         filtro.getUsuarioPreenchimento().getNome() + "%')");
        }
        if (filtro.getUnidade() != null) {
            if (filtro.getUnidade().getIdUnidade() != null && !new Long(0).equals(filtro.getUnidade().getIdUnidade())) {
                jpaQl.append(" and formulario.unidade.idUnidade = " + filtro.getUnidade().getIdUnidade());
            } else if (filtro.getUnidade().getForo() != null && filtro.getUnidade().getForo().getIdForo() != null) {
                jpaQl.append(" and formulario.unidade.foro.idForo = " + filtro.getUnidade().getForo().getIdForo());
            }
        }
        if (filtro.getMes() != null && !new Long(0).equals(filtro.getMes())) {
            jpaQl.append(" and formulario.mes = " + filtro.getMes());
        }
        if (filtro.getAno() != null && !new Long(0).equals(filtro.getAno())) {
            jpaQl.append(" and formulario.ano = " + filtro.getAno());
        }

        List listaForms = getPersistenceManager().listarPorJPQL(jpaQl, paginacao, usuario.getIdUsuario()).getLista();

        for (Object[] item : (List<Formulario[]>) listaForms) {
            Formulario formParcial = (Formulario) item[0];
            Formulario formCompleto = (Formulario) item[1];

            listaFormularios.add(formCompleto != null && formCompleto.getIdFormulario() != null ? formCompleto :
                                 formParcial);
        }
        paginacao.setLista(listaFormularios);
        return listaFormularios;
    }

    @Override
    public List<Formulario> listarFormularioGeral(Formulario filtro, List<String> listaTipoSituacao) {
        List<Formulario> lista = new ArrayList<>();

        lista.addAll(getPersistenceManager().listarPorJPQL(gerarQueryFormularios(filtro, listaTipoSituacao)));

        return lista;
    }

    @Override
    public List<Formulario> listarFormularioGeral(Formulario filtro, List<String> listaTipoSituacao,
                                                  List<PermissaoUnidadeTemporaria> listaPermissao) {
        List<Formulario> lista = new ArrayList<>();

        lista.addAll(getPersistenceManager().listarPorJPQL(gerarQueryFormulariosPermissaoTemporaria(filtro,
                                                                                                    listaTipoSituacao,
                                                                                                    listaPermissao)));

        return lista;
    }

    @Override
    public List<Formulario> listarFormularioGeralComPaginacao(Formulario filtro, Paginacao paginacao,
                                                              List<String> listaTipoSituacao) {
        List<Formulario> lista = new ArrayList<>();
        lista.addAll(getPersistenceManager().listarPorJPQL(gerarQueryFormularios(filtro, listaTipoSituacao),
                                                           paginacao).getLista());
        return lista;
    }

    @Override
    public List<Formulario> listarFormularioGeralComPaginacaoPermissao(Formulario filtro, Paginacao paginacao,
                                                                       List<String> listaTipoSituacao,
                                                                       List<PermissaoUnidadeTemporaria> listaPermissao) {
        List<Formulario> lista = new ArrayList<>();

        lista.addAll(getPersistenceManager().listarPorJPQL(gerarQueryFormulariosPermissaoTemporaria(filtro,
                                                                                                    listaTipoSituacao,
                                                                                                    listaPermissao),
                                                           paginacao).getLista());

        return lista;
    }

    @Override
    public List<Formulario> listarFormularioGeralComPaginacaoUnidade(Formulario filtro, Paginacao paginacao,
                                                                     List<String> listaTipoSituacao,
                                                                     List<Unidade> listaUnidade) {
        List<Formulario> lista = new ArrayList<>();

        lista.addAll(getPersistenceManager().listarPorJPQL(gerarQueryFormulariosUnidade(filtro, listaTipoSituacao,
                                                                                        listaUnidade),
                                                           paginacao).getLista());

        return lista;
    }

    private StringBuilder gerarQueryFormularios(Formulario filtro, List<String> listaTipoSituacao) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select formulario from Formulario formulario where 1=1 ");
        if (filtro.getMetadadosFormulario() != null && filtro.getMetadadosFormulario().getDescricaoNome() != null) {
            jpaQl.append(" and UPPER(formulario.metadadosFormulario.descricaoNome) like UPPER('%" +
                         filtro.getMetadadosFormulario().getDescricaoNome() + "%')");
        }
        if (filtro.getUsuarioAprovacao() != null && filtro.getUsuarioAprovacao().getNome() != null &&
            !filtro.getUsuarioAprovacao().getNome().isEmpty()) {
            jpaQl.append(" and UPPER(formulario.usuarioAprovacao.nome) like UPPER('%" +
                         filtro.getUsuarioAprovacao().getNome() + "%')");
        }

        if (filtro.getUsuarioPreenchimento() != null && filtro.getUsuarioPreenchimento().getNome() != null && !filtro.getUsuarioPreenchimento().getNome().isEmpty()) {
            jpaQl.append(" and UPPER(formulario.usuarioPreenchimento.nome) like UPPER('%" +
                         filtro.getUsuarioPreenchimento().getNome() + "%')");
        }
        if (filtro.getUnidade() != null) {
            if (filtro.getUnidade().getIdUnidade() != null && !new Long(0).equals(filtro.getUnidade().getIdUnidade())) {
                jpaQl.append(" and formulario.unidade.idUnidade = " + filtro.getUnidade().getIdUnidade());
            } else if (filtro.getUnidade().getForo() != null && filtro.getUnidade().getForo().getIdForo() != null) {
                jpaQl.append(" and formulario.unidade.foro.idForo = " + filtro.getUnidade().getForo().getIdForo());
            }
        }
        
        if (filtro.getDataFechamento() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            jpaQl.append(" and FUNC('TRUNC', formulario.dataFechamento) = FUNC('TO_DATE', '" +
                         sdf.format(filtro.getDataFechamento()) + "', 'dd/MM/YYYY')");
        }
        if (filtro.getMes() != null && !new Long(0).equals(filtro.getMes())) {
            jpaQl.append(" and formulario.mes = " + filtro.getMes());
        }
        if (filtro.getAno() != null && !new Long(0).equals(filtro.getAno())) {
            jpaQl.append(" and formulario.ano = " + filtro.getAno());
        }
        if (filtro.getTipoSituacao() != null && filtro.getTipoSituacao().getIdTipoSituacao() != null &&
            !new Long(0).equals(filtro.getTipoSituacao().getIdTipoSituacao())) {
            jpaQl.append(" and formulario.tipoSituacao.idTipoSituacao = " +
                         filtro.getTipoSituacao().getIdTipoSituacao());
        } else {
            jpaQl.append(" and formulario.tipoSituacao.codigoSituacao in ( ");
            StringBuffer parameter = new StringBuffer();
            for (String situacao : listaTipoSituacao) {
                parameter.append("'" + situacao + "',");
            }
            if (parameter.length() > 0) {
                jpaQl.append(parameter.toString().substring(0, parameter.length() - 1));
                jpaQl.append(")");
            }

        }
        if (filtro.getUsuarioAprovacao() != null && filtro.getUsuarioAprovacao().getIdUsuario() != null &&
            !new Long(0).equals(filtro.getUsuarioAprovacao().getIdUsuario())) {
            jpaQl.append(" and formulario.usuarioAprovacao.idUsuario = " + filtro.getUsuarioAprovacao().getIdUsuario());
        }

        jpaQl.append(" order by formulario.ano DESC, formulario.mes DESC, formulario.unidade.foro.nomeForo, formulario.unidade.nomeUnidade");

        return jpaQl;
    }

    private StringBuilder gerarQueryFormulariosPermissaoTemporaria(Formulario filtro, List<String> listaTipoSituacao,
                                                                   List<PermissaoUnidadeTemporaria> listaPermissao) {
        StringBuilder jpaQl = new StringBuilder();

        jpaQl.append("select formulario from Formulario formulario where 1=1 ");
        if (filtro.getMetadadosFormulario() != null && filtro.getMetadadosFormulario().getDescricaoNome() != null) {
            jpaQl.append(" and UPPER(formulario.metadadosFormulario.descricaoNome) like UPPER('%" +
                         filtro.getMetadadosFormulario().getDescricaoNome() + "%')");
        }
        if (filtro.getUsuarioAprovacao() != null && filtro.getUsuarioAprovacao().getNome() != null && !filtro.getUsuarioAprovacao().getNome().isEmpty()){
            jpaQl.append(" and UPPER(formulario.usuarioAprovacao.nome) like UPPER('%" +
                         filtro.getUsuarioAprovacao().getNome() + "%')");
        }

        if (filtro.getUsuarioPreenchimento() != null && filtro.getUsuarioPreenchimento().getNome() != null && !filtro.getUsuarioPreenchimento().getNome().isEmpty()) {
            jpaQl.append(" and UPPER(formulario.usuarioPreenchimento.nome) like UPPER('%" +
                         filtro.getUsuarioPreenchimento().getNome() + "%')");
        }
        
        if (filtro.getDataFechamento() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
                    jpaQl.append(" and FUNC('TRUNC', formulario.dataFechamento) = FUNC('TO_DATE', '" +
                                 sdf.format(filtro.getDataFechamento()) + "', 'dd/MM/YYYY')");
        }

        if (filtro.getUnidade() != null) {
            if (filtro.getUnidade().getForo() != null && filtro.getUnidade().getForo().getIdForo() != null) {
                jpaQl.append(" and formulario.unidade.foro.idForo = " + filtro.getUnidade().getForo().getIdForo());
            } else {
                if (filtro.getUnidade().getIdUnidade() != null &&
                    !new Long(0).equals(filtro.getUnidade().getIdUnidade())) {
                    jpaQl.append(" and formulario.unidade.idUnidade = " + filtro.getUnidade().getIdUnidade());
                }
            }
        } else {
            StringBuffer parameter = new StringBuffer();
            parameter.append(" and (");
            for (PermissaoUnidadeTemporaria permissao : listaPermissao) {

                Calendar inicio = Calendar.getInstance();
                inicio.setTime(permissao.getDataInicio());
                Calendar fim = Calendar.getInstance();
                fim.setTime(permissao.getDataValidade());
                calcularDatasReferencia(inicio);
                calcularDatasReferencia(fim);
                parameter.append(" (formulario.unidade.idUnidade = " + permissao.getUnidade().getIdUnidade());
                parameter.append(" and formulario.mes between " + inicio.get(Calendar.MONTH) + " and " +
                                 fim.get(Calendar.MONTH));
                parameter.append(" and formulario.ano between " + inicio.get(Calendar.YEAR) + " and " +
                                 fim.get(Calendar.YEAR));

                parameter.append(") or");
            }
            if (parameter.length() > 0) {
                jpaQl.append(parameter.toString().substring(0, parameter.length() - 2));
                jpaQl.append(")");
            }
        }


        if (filtro.getMes() != null && !new Long(0).equals(filtro.getMes())) {
            jpaQl.append(" and formulario.mes = " + filtro.getMes());
        } else {

        }
        if (filtro.getAno() != null && !new Long(0).equals(filtro.getAno())) {
            jpaQl.append(" and formulario.ano = " + filtro.getAno());
        } else {

        }
        if (filtro.getTipoSituacao() != null && filtro.getTipoSituacao().getIdTipoSituacao() != null &&
            !new Long(0).equals(filtro.getTipoSituacao().getIdTipoSituacao())) {
            jpaQl.append(" and formulario.tipoSituacao.idTipoSituacao = " +
                         filtro.getTipoSituacao().getIdTipoSituacao());
        } else {
            jpaQl.append(" and formulario.tipoSituacao.codigoSituacao in ( ");
            StringBuffer parameter = new StringBuffer();
            for (String situacao : listaTipoSituacao) {
                parameter.append("'" + situacao + "',");
            }
            if (parameter.length() > 0) {
                jpaQl.append(parameter.toString().substring(0, parameter.length() - 1));
                jpaQl.append(")");
            }

        }
        if (filtro.getUsuarioAprovacao() != null && filtro.getUsuarioAprovacao().getIdUsuario() != null &&
            !new Long(0).equals(filtro.getUsuarioAprovacao().getIdUsuario())) {
            jpaQl.append(" and formulario.usuarioAprovacao.idUsuario = " + filtro.getUsuarioAprovacao().getIdUsuario());
        }

        jpaQl.append(" order by formulario.ano DESC, formulario.mes DESC, formulario.unidade.foro.nomeForo, formulario.unidade.nomeUnidade");

        return jpaQl;
    }

    private StringBuilder gerarQueryFormulariosUnidade(Formulario filtro, List<String> listaTipoSituacao,
                                                       List<Unidade> listaUnidade) {
        StringBuilder jpaQl = new StringBuilder();

        jpaQl.append("select formulario from Formulario formulario where 1=1 ");
        if (filtro.getMetadadosFormulario() != null && filtro.getMetadadosFormulario().getDescricaoNome() != null) {
            jpaQl.append(" and UPPER(formulario.metadadosFormulario.descricaoNome) like UPPER('%" +
                         filtro.getMetadadosFormulario().getDescricaoNome() + "%')");
        }
        if (filtro.getUsuarioAprovacao() != null && filtro.getUsuarioAprovacao().getNome() != null && !filtro.getUsuarioAprovacao().getNome().isEmpty()) {
            jpaQl.append(" and UPPER(formulario.usuarioAprovacao.nome) like UPPER('%" +
                         filtro.getUsuarioAprovacao().getNome() + "%')");
        }

        if (filtro.getUsuarioPreenchimento() != null && filtro.getUsuarioPreenchimento().getNome() != null && !filtro.getUsuarioPreenchimento().getNome().isEmpty()) {
            jpaQl.append(" and UPPER(formulario.usuarioPreenchimento.nome) like UPPER('%" +
                         filtro.getUsuarioPreenchimento().getNome() + "%')");
        }
        
        if (filtro.getDataFechamento() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
                    jpaQl.append(" and FUNC('TRUNC', formulario.dataFechamento) = FUNC('TO_DATE', '" +
                                 sdf.format(filtro.getDataFechamento()) + "', 'dd/MM/YYYY')");
        }

        if (filtro.getUnidade() != null) {
            if (filtro.getUnidade().getForo() != null && filtro.getUnidade().getForo().getIdForo() != null) {
                jpaQl.append(" and formulario.unidade.foro.idForo = " + filtro.getUnidade().getForo().getIdForo());
            } else {
                if (filtro.getUnidade().getIdUnidade() != null &&
                    !new Long(0).equals(filtro.getUnidade().getIdUnidade())) {
                    jpaQl.append(" and formulario.unidade.idUnidade = " + filtro.getUnidade().getIdUnidade());
                }
            }
        } else {
            StringBuffer parameter = new StringBuffer();
            parameter.append(" and formulario.unidade.idUnidade in(");
            for (Unidade unidade : listaUnidade) {
                parameter.append(unidade.getIdUnidade());
                parameter.append(",");
            }
            if (parameter.length() > 0) {
                jpaQl.append(parameter.toString().substring(0, parameter.length() - 1));
                jpaQl.append(")");
            }
        }


        if (filtro.getMes() != null && !new Long(0).equals(filtro.getMes())) {
            jpaQl.append(" and formulario.mes = " + filtro.getMes());
        } else {

        }
        if (filtro.getAno() != null && !new Long(0).equals(filtro.getAno())) {
            jpaQl.append(" and formulario.ano = " + filtro.getAno());
        } else {

        }
        if (filtro.getTipoSituacao() != null && filtro.getTipoSituacao().getIdTipoSituacao() != null &&
            !new Long(0).equals(filtro.getTipoSituacao().getIdTipoSituacao())) {
            jpaQl.append(" and formulario.tipoSituacao.idTipoSituacao = " +
                         filtro.getTipoSituacao().getIdTipoSituacao());
        } else {
            jpaQl.append(" and formulario.tipoSituacao.codigoSituacao in ( ");
            StringBuffer parameter = new StringBuffer();
            for (String situacao : listaTipoSituacao) {
                parameter.append("'" + situacao + "',");
            }
            if (parameter.length() > 0) {
                jpaQl.append(parameter.toString().substring(0, parameter.length() - 1));
                jpaQl.append(")");
            }

        }
        if (filtro.getUsuarioAprovacao() != null && filtro.getUsuarioAprovacao().getIdUsuario() != null &&
            !new Long(0).equals(filtro.getUsuarioAprovacao().getIdUsuario())) {
            jpaQl.append(" and formulario.usuarioAprovacao.idUsuario = " + filtro.getUsuarioAprovacao().getIdUsuario());
        }

        jpaQl.append(" order by formulario.ano DESC, formulario.mes DESC, formulario.unidade.foro.nomeForo, formulario.unidade.nomeUnidade");

        return jpaQl;
    }

    private void calcularDatasReferencia(Calendar c) {
        Integer mes = c.get(Calendar.MONTH);
        Integer ano = c.get(Calendar.YEAR);
        calcularDatasReferencia(mes, ano);
    }

    private void calcularDatasReferencia(Integer mes, Integer ano) {
        if (mes == 0) {
            ano = ano - 1;
            mes = 11;
        } else {
            mes = mes - 1;
        }
    }

    @Override
    public Long countFormulariosAnoMesReferencia(int ano, int mes) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select count(Formulario) from Formulario formulario where");
        jpaQl.append(" formulario.metadadosFormulario.metadadosTipoSituacao.tipoSituacao = 'EM_USO' and");
        jpaQl.append(" formulario.ano = ?1 and formulario.mes = ?2");
        return getPersistenceManager().procurarPorJPQLSingleResult(jpaQl, ano, mes);
    }

    @Override
    public List<Formulario> listarFormulariosComFiltros(List<Long> listaAno, List<Long> listaMes, Long idUnidade,
                                                        String descricaoSourceFormulario) {

        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select formulario from Formulario formulario where ");
        jpaQl.append("formulario.metadadosFormulario.descricaoSourceFormulario = ?1 and");
        jpaQl.append(" formulario.unidade.idUnidade = ?2");
        inList(jpaQl, "formulario.ano", listaAno);
        inList(jpaQl, "formulario.mes", listaMes);
        return getPersistenceManager().listarPorJPQL(jpaQl, descricaoSourceFormulario, idUnidade);
    }

    @Override
    public Formulario recuperarFormularioMesAnterior(Formulario formularioAtual) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select formulario from Formulario formulario where ");
        jpaQl.append("formulario.metadadosFormulario.descricaoSourceFormulario = ?1 and");
        jpaQl.append(" formulario.unidade.idUnidade = ?2 and");
        jpaQl.append(" formulario.ano = ?3 and");
        jpaQl.append(" formulario.mes = ?4");

        Long ano = 0L;
        Long mes = 0L;
        if (formularioAtual.getMes().intValue() == 1) {
            ano = formularioAtual.getAno() - 1;
            mes = 12L;
        } else {
            ano = formularioAtual.getAno();
            mes = formularioAtual.getMes() - 1;
        }
        return getPersistenceManager().procurarPorJPQLSingleResult(jpaQl,
                                                                   formularioAtual.getMetadadosFormulario().getDescricaoSourceFormulario(),
                                                                   formularioAtual.getUnidade().getIdUnidade(), ano,
                                                                   mes);
    }

    @Override
    public Formulario recuperarFormularioAnoMesReferencia(Formulario filtro) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select formulario from Formulario formulario where ");
        jpaQl.append("formulario.metadadosFormulario.descricaoSourceFormulario = ?1 and");
        jpaQl.append(" formulario.unidade.idUnidade = ?2 and");
        jpaQl.append(" formulario.ano = ?3 and");
        jpaQl.append(" formulario.mes = ?4");
        return getPersistenceManager().procurarPorJPQLSingleResult(jpaQl,
                                                                   filtro.getMetadadosFormulario().getDescricaoSourceFormulario(),
                                                                   filtro.getUnidade().getIdUnidade(), filtro.getAno(),
                                                                   filtro.getMes());
    }

    @Override
    public Formulario recuperarPrimeiroFormularioUnidade(Formulario filtro) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select formulario from Formulario formulario where");
        jpaQl.append(" formulario.metadadosFormulario.descricaoSourceFormulario = ?1 and");
        jpaQl.append(" formulario.unidade.idUnidade = ?2 and");
        jpaQl.append(" formulario.idFormulario in ");
        jpaQl.append(" (select min(formulario.idFormulario)" + " from Formulario formulario where" +
                     " formulario.metadadosFormulario.descricaoSourceFormulario = ?1 and" +
                     " formulario.unidade.idUnidade = ?2)");
        return getPersistenceManager().procurarPorJPQLSingleResult(jpaQl,
                                                                   filtro.getMetadadosFormulario().getDescricaoSourceFormulario(),
                                                                   filtro.getUnidade().getIdUnidade());
    }
    
    @Override
    public Formulario recuperarFormularioPorIdFormulario(Long idFormulario) {
        return getPersistenceManager().getManager().find(Formulario.class, idFormulario);
    }

    @Override
    public void updateSituacaoFormulario(Long idFormulario, Long idSituacaoAntiga, Long idSituacaoNova, Long idUsuario, String motivo) {
        if (idFormulario != null && idSituacaoAntiga != null && idSituacaoNova != null) {
            TipoSituacao tipoSituacaoAntiga = getPersistenceManager().getManager().find(TipoSituacao.class, idSituacaoAntiga);
            TipoSituacao tipoSituacaoNova = getPersistenceManager().getManager().find(TipoSituacao.class, idSituacaoNova);
            if(tipoSituacaoNova != null && tipoSituacaoAntiga != null) {
                getPersistenceManager().atualizarPorJPQL(
                    "update Formulario " + 
                    "set Formulario.tipoSituacao=?1 " +
                    "where Formuario.idFormulario=?2 and " + 
                    "Formulario.tipoSituacao=?3",
                    tipoSituacaoNova, 
                    idFormulario, 
                    tipoSituacaoAntiga);
                Formulario form = recuperarFormularioPorIdFormulario(idFormulario);
                if (idUsuario != null) {
                    Usuario usuario = getPersistenceManager().getManager().find(Usuario.class, idUsuario);
                    getPersistenceManager()
                        .atualizarPorJPQL("insert FormularioHistorico (formulario, tipoSituacao, usuario, dataCriacao, descricaoComentario) " +
                                          "values (?1,?2,?3,?4,?5)", form, tipoSituacaoNova, usuario, new Date(), motivo);
                }                
            }
        }
    }
}
