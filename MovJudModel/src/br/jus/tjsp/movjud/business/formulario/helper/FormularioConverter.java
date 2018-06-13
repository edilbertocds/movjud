package br.jus.tjsp.movjud.business.formulario.helper;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.CompetenciaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.EstabelecimentoEntidadeDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormulaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ForoOrigemDTO;
import br.jus.tjsp.movjud.business.formulario.dto.GrupoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.HistoricoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ItemSelecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.MateriaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.MetadadoSituacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ProcessoConclusoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ReuDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SegmentoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SituacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SubSecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoConclusoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoFilaProcessoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoMateriaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoRegraDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ValidacaoDTO;
import br.jus.tjsp.movjud.business.formulario.types.ProcessoConclusoType;
import br.jus.tjsp.movjud.business.formulario.types.SecaoType;
import br.jus.tjsp.movjud.business.formulario.types.TipoCampoType;
import br.jus.tjsp.movjud.business.formulario.types.TipoJuizType;
import br.jus.tjsp.movjud.business.formulario.types.TipoValidacaoType;
import br.jus.tjsp.movjud.persistence.base.types.TipoSituacaoType;
import br.jus.tjsp.movjud.persistence.entity.Campo;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.Formulario;
import br.jus.tjsp.movjud.persistence.entity.FormularioForoOrigem;
import br.jus.tjsp.movjud.persistence.entity.FormularioHistorico;
import br.jus.tjsp.movjud.persistence.entity.FormularioVinculacao;
import br.jus.tjsp.movjud.persistence.entity.Foro;
import br.jus.tjsp.movjud.persistence.entity.Grupo;
import br.jus.tjsp.movjud.persistence.entity.InspecaoEstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.MetadadosCampo;
import br.jus.tjsp.movjud.persistence.entity.MetadadosCampoCampo;
import br.jus.tjsp.movjud.persistence.entity.MetadadosFormulario;
import br.jus.tjsp.movjud.persistence.entity.MetadadosGrupo;
import br.jus.tjsp.movjud.persistence.entity.MetadadosGrupoCampo;
import br.jus.tjsp.movjud.persistence.entity.MetadadosListaSelecao;
import br.jus.tjsp.movjud.persistence.entity.MetadadosSecao;
import br.jus.tjsp.movjud.persistence.entity.MetadadosTipoRegra;
import br.jus.tjsp.movjud.persistence.entity.MetadadosTipoSituacao;
import br.jus.tjsp.movjud.persistence.entity.MetadadosValidacaoCampo;
import br.jus.tjsp.movjud.persistence.entity.ProcessoConcluso;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorio;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorioHistorico;
import br.jus.tjsp.movjud.persistence.entity.Secao;
import br.jus.tjsp.movjud.persistence.entity.TipoArea;
import br.jus.tjsp.movjud.persistence.entity.TipoCompetencia;
import br.jus.tjsp.movjud.persistence.entity.TipoConcluso;
import br.jus.tjsp.movjud.persistence.entity.TipoFilaProcesso;
import br.jus.tjsp.movjud.persistence.entity.TipoMateria;
import br.jus.tjsp.movjud.persistence.entity.TipoNaturezaPrisao;
import br.jus.tjsp.movjud.persistence.entity.TipoSegmento;
import br.jus.tjsp.movjud.persistence.entity.TipoSituacao;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.UnidadeEstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class FormularioConverter {

    public FormularioConverter() {
        super();
    }

    public static Long anoCorrente() {
        Long ano = Long.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        if (Calendar.getInstance().get(Calendar.MONTH) == 0) {
            ano = ano - 1;
        }
        return ano;
    }

    public static Long mesAnterior() {
        Long mes = Long.valueOf(Calendar.getInstance().get(Calendar.MONTH));
        if (Calendar.getInstance().get(Calendar.MONTH) == 0) {
            mes = 12L;
        }
        return mes;
    }

    //Entidade para DTO

    public static List<HistoricoFormularioDTO> parseListaFormularioHistoricoParaListaHistoricoFormularioDTO(List<FormularioHistorico> listaFormularioHistorico) {
        List<HistoricoFormularioDTO> listaHistoricoFormularioDTO = new ArrayList<HistoricoFormularioDTO>();
        for (FormularioHistorico formularioHistorico : listaFormularioHistorico) {
            if (formularioHistorico != null) {
                listaHistoricoFormularioDTO.add(parseFormularioHistoricoParaHistoricoFormularioDTO(formularioHistorico));
            }
        }
        return listaHistoricoFormularioDTO;
    }

    // <epr> (1) correção otipo regra?
    /*
    public static List<TipoRegraDTO> parseFormularioParaListaTipoRegraDTO(Formulario formulario) {
        List<TipoRegraDTO> listaTiposRegraDTO = null;
        if (formulario.getUnidade() != null && formulario.getUnidade().getFormulariosVinculacao() != null) {
            for (FormularioVinculacao formularioVinculacao : formulario.getUnidade().getFormulariosVinculacao()) {
                if (formularioVinculacao.getMetadadosFormulario().equals(formulario.getMetadadosFormulario())) {
                    listaTiposRegraDTO =
                        parseListaMetadadosTipoRegraParaListaTipoRegraDTO(formularioVinculacao.getListaMetadadosTipoRegra());
                }
            }
        }
        return listaTiposRegraDTO;
    }*/
    public static List<TipoRegraDTO> parseFormularioParaListaTipoRegraDTO(Formulario formulario) {
        List<TipoRegraDTO> listaTiposRegraDTO = parseListaMetadadosTipoRegraParaListaTipoRegraDTO(formulario.getListaMetadadosTipoRegra());
        if (formulario.getUnidade() != null && formulario.getUnidade().getFormulariosVinculacao() != null) {
            for (FormularioVinculacao formularioVinculacao : formulario.getUnidade().getFormulariosVinculacao()) {
                if (formularioVinculacao.getMetadadosFormulario().equals(formulario.getMetadadosFormulario())) {
                    List<TipoRegraDTO> listaTRFV =
                        parseListaMetadadosTipoRegraParaListaTipoRegraDTO(formularioVinculacao.getListaMetadadosTipoRegra());
                    for(TipoRegraDTO tipoRegra : listaTRFV) {
                        if(tipoRegra.getCodigoTipoRegra().intValue() == 8){//para retirar a regra de primeiro preenchimento caso nao seja o primeiro preenchimento
                            listaTiposRegraDTO.remove(tipoRegra);
                            continue;
                        }
                        if(!listaTiposRegraDTO.stream().anyMatch(r -> (r != null) && (r.getCodigoTipoRegra() != null) && r.getCodigoTipoRegra().equals(tipoRegra.getCodigoTipoRegra()))) {
                            listaTiposRegraDTO.add(tipoRegra);
                        }
                    }
                }
            }
        }
        return listaTiposRegraDTO;
    }    
    // </epr> (1) correçao tipo regra

    public static List<FormularioDTO> parseListaFormularioParaListaFormularioDTO(List<Formulario> listaFormularios, Boolean paraPaginacao) {
        List<FormularioDTO> listaFormularioDTO = null;
        if (listaFormularios != null && !listaFormularios.isEmpty()) {
            listaFormularioDTO = new ArrayList<FormularioDTO>();
            for (Formulario formulario : listaFormularios) {
                if(paraPaginacao)
                    listaFormularioDTO.add(parseFormularioParaFormularioDTOParaPaginacao(formulario));
                else
                    listaFormularioDTO.add(parseFormularioParaFormularioDTO(formulario));
            }
        }
        return listaFormularioDTO;
    }

    public static List<FormularioDTO> parseListaFormularioParaListaFormularioDTO(List<Formulario> listaFormularios) {
        return parseListaFormularioParaListaFormularioDTO(listaFormularios, false);
    }

    public static FormularioDTO parseFormularioParaFormularioDTO(Formulario formulario) {
        FormularioDTO formularioDTO = null;
        if (formulario != null) {
            List<Secao> listaSecoesPrincipais = new ArrayList<Secao>();
            // 20180604-retornando código original
            for (Secao secao : formulario.getSecoes()) {
                if (secao.getSecaoPai() == null || secao.getSecaoPai().equals(secao)) {
                    listaSecoesPrincipais.add(secao);
                }
            }
            // 20180604
            formulario.setSecoes(listaSecoesPrincipais);
            formularioDTO = parseMetadadosFormularioParaFormularioDTO(formulario.getMetadadosFormulario());
            formularioDTO.setIdFormulario(formulario.getIdFormulario());
            formularioDTO.setNomeUnidade(formulario.getUnidade().getNomeUnidade());
            formularioDTO.setIdUnidade(formulario.getUnidade().getIdUnidade());
            formularioDTO.setListaTipoRegrasFormulario(parseFormularioParaListaTipoRegraDTO(formulario));
            // 20180604
            
            formularioDTO.setListaSecoes(parseListaSecoesParaListaSecoesDTO(formulario.getSecoes(),
                                                                            formularioDTO.getListaSecoes(),
                                                                            formularioDTO));
            // 20180604
            if (formulario.getAno() != null) {
                formularioDTO.setAno(formulario.getAno());
            } else {
                formularioDTO.setAno(anoCorrente());
            }
            if (formulario.getMes() != null) {
                formularioDTO.setMes(formulario.getMes());
            } else {
                formularioDTO.setMes(mesAnterior());
            }
            if (formulario.getUnidade().getForo() != null) {
                formularioDTO.setNomeForo(formulario.getUnidade().getForo().getNomeForo());
                formularioDTO.setIdForo(formulario.getUnidade().getForo().getIdForo());
            }
            if (formulario.getUsuarioAprovacao() != null) {
                formularioDTO.setIdMagistrado(formulario.getUsuarioAprovacao().getIdUsuario());
                formularioDTO.setNomeMagistrado(formulario.getUsuarioAprovacao().getNome());
            }
            if (formulario.getUsuarioPreenchimento() != null) {
                formularioDTO.setIdUsuarioPreenchimento(formulario.getUsuarioPreenchimento().getIdUsuario());
                formularioDTO.setNomeUsuarioPreenchimento(formulario.getUsuarioPreenchimento().getNome());
            }
            if (formulario.getDataFechamento() != null) {
                formularioDTO.setDataConclusao(formulario.getDataFechamento());
            }
            formularioDTO.setSituacaoFormularioDTO(parseTipoSituacaoParaSituacaoFormularioDTO(formulario.getTipoSituacao()));
            /*formularioDTO.setListaHistoricoFormulario(parseListaFormularioHistoricoParaListaHistoricoFormularioDTO(formulario.getFormulariosHistorico()));*/
        }
        return formularioDTO;
    }
    
    public static FormularioDTO parseFormularioParaFormularioDTOliberacao(Formulario formulario) {
        FormularioDTO formularioDTO = null;
        
        if (formulario != null) {
            /*List<Secao> listaSecoesPrincipais = new ArrayList<Secao>();
            for (Secao secao : formulario.getSecoes()) {
                if (secao.getSecaoPai() == null || secao.getSecaoPai().equals(secao)) {
                    listaSecoesPrincipais.add(secao);
                }
            }
            formulario.setSecoes(listaSecoesPrincipais);*/
            formularioDTO = parseMetadadosFormularioParaFormularioDTOliberacao(formulario.getMetadadosFormulario());
            formularioDTO.setIdFormulario(formulario.getIdFormulario());
            formularioDTO.setNomeUnidade(formulario.getUnidade().getNomeUnidade());
            formularioDTO.setIdUnidade(formulario.getUnidade().getIdUnidade());
            formularioDTO.setListaTipoRegrasFormulario(parseFormularioParaListaTipoRegraDTO(formulario));
            
            /*formularioDTO.setListaSecoes(parseListaSecoesParaListaSecoesDTO(formulario.getSecoes(),
                                                                            formularioDTO.getListaSecoes(),
                                                                            formularioDTO));*/
            
            if (formulario.getAno() != null) {
                formularioDTO.setAno(formulario.getAno());
            } else {
                formularioDTO.setAno(anoCorrente());
            }
            if (formulario.getMes() != null) {
                formularioDTO.setMes(formulario.getMes());
            } else {
                formularioDTO.setMes(mesAnterior());
            }
            if (formulario.getUnidade().getForo() != null) {
                formularioDTO.setNomeForo(formulario.getUnidade().getForo().getNomeForo());
                formularioDTO.setIdForo(formulario.getUnidade().getForo().getIdForo());
            }
            if (formulario.getUsuarioAprovacao() != null) {
                formularioDTO.setIdMagistrado(formulario.getUsuarioAprovacao().getIdUsuario());
                formularioDTO.setNomeMagistrado(formulario.getUsuarioAprovacao().getNome());
            }
            if (formulario.getUsuarioPreenchimento() != null) {
                formularioDTO.setIdUsuarioPreenchimento(formulario.getUsuarioPreenchimento().getIdUsuario());
                formularioDTO.setNomeUsuarioPreenchimento(formulario.getUsuarioPreenchimento().getNome());
            }
            if (formulario.getDataFechamento() != null) {
                formularioDTO.setDataConclusao(formulario.getDataFechamento());
            }
            formularioDTO.setSituacaoFormularioDTO(parseTipoSituacaoParaSituacaoFormularioDTO(formulario.getTipoSituacao()));
            formularioDTO.setListaHistoricoFormulario(parseListaFormularioHistoricoParaListaHistoricoFormularioDTO(formulario.getFormulariosHistorico()));
        }
        return formularioDTO;
    }
        
    public static FormularioDTO parseFormularioParaFormularioDTOParaPaginacao(Formulario formulario) {
        FormularioDTO formularioDTO = null;
        if (formulario != null) {
            
            //Formulário
            formularioDTO = parseMetadadosFormularioParaFormularioDTOParaPaginacao(formulario.getMetadadosFormulario());
            formularioDTO.setIdFormulario(formulario.getIdFormulario());
            
            //Foro
            if (formulario.getUnidade().getForo() != null) {
                formularioDTO.setNomeForo(formulario.getUnidade().getForo().getNomeForo());
                //formularioDTO.setIdForo(formulario.getUnidade().getForo().getIdForo());
            }
            
            //Unidade
            formularioDTO.setNomeUnidade(formulario.getUnidade().getNomeUnidade());
             
            //Ano
            if (formulario.getAno() != null) {
                formularioDTO.setAno(formulario.getAno());
            } else {
                formularioDTO.setAno(anoCorrente());
            }
           
            //Mês
            if (formulario.getMes() != null) {
                formularioDTO.setMes(formulario.getMes());
            } else {
                formularioDTO.setMes(mesAnterior());
            }
            
            //Situação
            formularioDTO.setSituacaoFormularioDTO(parseTipoSituacaoParaSituacaoFormularioDTOParaPaginacao(formulario.getTipoSituacao()));
            
            //Concluído
            if (formulario.getDataFechamento() != null) {
                formularioDTO.setDataConclusao(formulario.getDataFechamento());
            }
            
            //Magistrado
            if (formulario.getUsuarioAprovacao() != null) {
                //formularioDTO.setIdMagistrado(formulario.getUsuarioAprovacao().getIdUsuario());
                formularioDTO.setNomeMagistrado(formulario.getUsuarioAprovacao().getNome());
            }
            
            //Usuário
            if (formulario.getUsuarioPreenchimento() != null) {
                //formularioDTO.setIdUsuarioPreenchimento(formulario.getUsuarioPreenchimento().getIdUsuario());
                formularioDTO.setNomeUsuarioPreenchimento(formulario.getUsuarioPreenchimento().getNome());
            }
            
            //formularioDTO.setIdUnidade(formulario.getUnidade().getIdUnidade());
            //formularioDTO.setListaTipoRegrasFormulario(parseFormularioParaListaTipoRegraDTO(formulario));
            
        }
        return formularioDTO;
    }

    public static List<SecaoDTO> parseListaSecoesParaListaSecoesDTO(List<Secao> listaSecoes,
                                                                    List<SecaoDTO> listaSecoesDTO,
                                                                    FormularioDTO formularioDTO) {
        if(listaSecoes.size() != listaSecoesDTO.size()) {
            System.out.println("Tamanho de listaSecoes != listaSecoesDTO");
        }
        for (Secao secao : listaSecoes) {
            List<SecaoDTO> smss = listaSecoesDTO.stream()
                .filter(s -> s.getIdMetadadosSecao().equals(secao.getMetadadosSecao().getIdMetadadosSessao()))
                .collect(Collectors.toList());
            
            //for (SecaoDTO secaoDTO : listaSecoesDTO) {
            if((smss != null) && (smss.size() == 1 && (smss.get(0) != null))) {
                SecaoDTO secaoDTO = smss.get(0);
                if (secaoDTO.getIdMetadadosSecao().equals(secao.getMetadadosSecao().getIdMetadadosSessao())) {
                    secaoDTO.setIdSecao(secao.getIdSecao());
                    secaoDTO.setFormulario(formularioDTO);
                    secaoDTO.setListaSubSecoes(parseListaSubSecoesParaListaSubSecoesDTO(secao, secaoDTO));
                } else {
                    System.out.println("ID_METADADOS não coincidentes: secao=" + 
                        (secao.getMetadadosSecao().getIdMetadadosSessao() != null ? secao.getMetadadosSecao().getIdMetadadosSessao().toString() : "nulo") +
                                       ", secaoDTO=" + (secaoDTO.getIdMetadadosSecao() != null ? secaoDTO.getIdMetadadosSecao().toString() : "nulo"));
                }
            } else {
                System.out.println("ID_MEDATADADOS não encontrado secao=" + (secao.getMetadadosSecao().getIdMetadadosSessao() != null ? secao.getMetadadosSecao().getIdMetadadosSessao().toString() : "nulo"));
            }
        }
        return listaSecoesDTO;
    }

    public static List<SubSecaoDTO> parseListaSubSecoesParaListaSubSecoesDTO(Secao secao, SecaoDTO secaoDTO) {
        List<SubSecaoDTO> listaSubSecaoDTO = new ArrayList<SubSecaoDTO>();
        listaSubSecaoDTO.addAll(secaoDTO.getListaSubSecoes());
        for (Secao subSecao : secao.getSubSecoes()) {
            if (secaoDTO.getCodigoSecao().equals(SecaoType.MATERIA.getCodigoSecao()) ||
                secaoDTO.getCodigoSecao().equals(SecaoType.MAGISTRADO.getCodigoSecao())) {
                SubSecaoDTO subSecaoDTO = new SubSecaoDTO(secaoDTO);
                subSecaoDTO = parseSubSecaoParaSubSecaoDTO(subSecao, subSecaoDTO, secaoDTO);
                subSecaoDTO.setIdSecao(secao.getIdSecao());
                if (secaoDTO.getCodigoSecao().equals(SecaoType.MATERIA.getCodigoSecao())) {
                    subSecaoDTO.setTipoMateria(parseTipoMateriaParaTipoMateriaDTO(subSecao.getTipoMateria()));
                } else if (secaoDTO.getCodigoSecao().equals(SecaoType.MAGISTRADO.getCodigoSecao())) {
                    subSecaoDTO.setIdMagistrado(subSecao.getMagistrado().getIdUsuario());
                    subSecaoDTO.setNomeMagistrado(subSecao.getMagistrado().getNome());
                    subSecaoDTO.setConclusos(secaoDTO.isConclusos());
                    subSecaoDTO.setTipoJuiz(TipoJuizType.recuperarTipoJuizPorCodigo(subSecao.getTipoJuiz()));
                }
                // <epr> (1) 0.7.6 correção da ordenação
                //   secaoDTO.getListaSubSecoes().add(subSecaoDTO);
                //   Collections.sort(secaoDTO.getListaSubSecoes());
                listaSubSecaoDTO.add(subSecaoDTO);
                // </epr> (1) 0.7.6 correção da ordenação
            } else {
                for (SubSecaoDTO subSecaoDTO : listaSubSecaoDTO /* <epr> (2) 0.7.6 correção da ordenação secaoDTO.getListaSubSecoes() </epr> */) {
                    if (subSecaoDTO.getIdMetadadosSecao().equals(subSecao.getMetadadosSecao().getIdMetadadosSessao())) {
                        subSecaoDTO = parseSubSecaoParaSubSecaoDTO(subSecao, subSecaoDTO, secaoDTO);
                        subSecaoDTO.setIdSecao(secao.getIdSecao());
                        if (subSecaoDTO.getCodigoSubSecao().equals(SecaoType.ESTABELECIMENTOS_PRISIONAIS.getCodigoSecao())) {
                            if (contemTipoRegra(parseFormularioParaListaTipoRegraDTO(secao.getFormulario()))) {
                                List<EstabelecimentoEntidadeDTO> listaEstabelecimentosDTO =
                                    parseListaUnidadeEstabelecimentosPrisionaisParaListaEstabelecimentoEntidadeDTO(secao.getFormulario().getUnidade().getUnidadeEstabelecimentosPrisionais(), secao.getFormulario().getAno(), secao.getFormulario().getMes());
                                    
                                if (listaEstabelecimentosDTO != null) {
                                    subSecaoDTO.setListaEstabelecimentosEntidade(filtrarTipoInternacaoPrisional(listaEstabelecimentosDTO,
                                                                                                                subSecaoDTO));
                                    subSecaoDTO.setListaEstabelecimentosEntidade(parseListaInspecaoEstabelecimentoParaListaEstabelecimentoEntidadeDTO(subSecao.getInspecoesEstabelecimentoPrisional(),
                                                                                                                                                      subSecaoDTO.getListaEstabelecimentosEntidade()));
                                }
                            }
                        } else if (subSecaoDTO.getCodigoSubSecao().equals(SecaoType.DADOS_UNIDADES.getCodigoSecao())) {
                            subSecaoDTO.setListaForosOrigem(parseFormularioForoOrigemParaForoOrigemDTO(secao.getFormulario().getUnidade().getForosRecursais(),
                                                                                                       subSecao.getFormularioForosOrigem(),
                                                                                                       secao.getFormulario().getUnidade()));
                        }
                    }
                }
            }
        }
        // <epr> (3) 0.7.6 correção da ordenação
        Collections.sort(listaSubSecaoDTO);
        secaoDTO.setListaSubSecoes(listaSubSecaoDTO);
        // </epr> (3) 0.7.6 correção da ordenação
        return secaoDTO.getListaSubSecoes();
    }

    public static List<ForoOrigemDTO> parseFormularioForoOrigemParaForoOrigemDTO(List<Foro> listaForoOrigem,
                                                                                 List<FormularioForoOrigem> listaFormularioForoOrigem,
                                                                                 Unidade unidade) {
        List<ForoOrigemDTO> listaForosOrigemDTO = new ArrayList<ForoOrigemDTO>();
        for (Foro foroOrigem : listaForoOrigem) {
            ForoOrigemDTO foroOrigemDTO = new ForoOrigemDTO();
            foroOrigemDTO.setIdForo(foroOrigem.getIdForo());
            foroOrigemDTO.setNomeForo(foroOrigem.getNomeForo());
            foroOrigemDTO.setIdUnidade(unidade.getIdUnidade());
            foroOrigemDTO.setNomeUnidade(unidade.getNomeUnidade());
            for (FormularioForoOrigem formularioForoOrigem : listaFormularioForoOrigem) {
                if (formularioForoOrigem.getForo().getIdForo().equals(foroOrigem.getIdForo())) {
                    foroOrigemDTO.setValorCampo(formularioForoOrigem.getValorCampo());
                    foroOrigemDTO.setIdFormularioForoOrigem(formularioForoOrigem.getIdFormularioForoOrigem());
                    if (formularioForoOrigem.getSecao() != null)
                        foroOrigemDTO.setIdSecao(formularioForoOrigem.getSecao().getIdSecao());
                }
            }
            listaForosOrigemDTO.add(foroOrigemDTO);
        }
        // <epr> 0.7.12 - ordenação Foro Origem
        Comparator<ForoOrigemDTO> comparator = new Comparator<ForoOrigemDTO>() {
            @Override
            public int compare(ForoOrigemDTO e1, ForoOrigemDTO e2) {
                if(e1 == e2) {
                    return 0;
                }
                if((e1 == null) || (e1.getNomeForo() == null)) {
                    return -1;
                } else if((e2 == null) || (e2.getNomeForo() == null)) {
                    return 1;
                }
                return e1.getNomeForo().compareTo(e2.getNomeForo());
            }
        };
        Collections.sort(listaForosOrigemDTO, comparator);
        // </epr> 0.7.12 - ordenação Foro Origem
        return listaForosOrigemDTO;
    }

    public static List<EstabelecimentoEntidadeDTO> filtrarTipoInternacaoPrisional(List<EstabelecimentoEntidadeDTO> listaEstabelecimentosDTO,
                                                                                  SubSecaoDTO subSecaoDTO) {
        List<EstabelecimentoEntidadeDTO> listaEstabelecimentosRetorno = new ArrayList<EstabelecimentoEntidadeDTO>();
        if (listaEstabelecimentosDTO != null) {
            for (EstabelecimentoEntidadeDTO estabelecimentoEntidadeDTO : listaEstabelecimentosDTO) {
                if ((subSecaoDTO.isTipoInternacao() && estabelecimentoEntidadeDTO.isTipoInternacao()) ||
                    (subSecaoDTO.isTipoPrisional() && estabelecimentoEntidadeDTO.isTipoPrisional())) {
                    listaEstabelecimentosRetorno.add(estabelecimentoEntidadeDTO);
                }
            }
        }
        return listaEstabelecimentosRetorno;
    }

    public static boolean contemTipoRegra(List<TipoRegraDTO> listaTipoRegraDTO) {
        boolean retorno = false;
        if (listaTipoRegraDTO != null) {
            for (TipoRegraDTO tipoRegraDTO : listaTipoRegraDTO) {
                if (tipoRegraDTO.getDescricaoTipoRegra().equals("VISITESTAB")) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public static List<TipoFilaProcessoDTO> parseListaTipoFilaProcessoParaListaTipoFilaProcessoDTO(List<TipoFilaProcesso> listaTipoFilaProcesso) {
        List<TipoFilaProcessoDTO> listaTipoFilaProcessoDTO = new ArrayList<TipoFilaProcessoDTO>();
        if (listaTipoFilaProcesso != null) {
            for (TipoFilaProcesso tipoFilaProcesso : listaTipoFilaProcesso) {
                listaTipoFilaProcessoDTO.add(parseTipoFilaProcessoParaTipoFilaProcessoDTO(tipoFilaProcesso));
            }
        }
        return listaTipoFilaProcessoDTO;
    }

    public static List<ProcessoConclusoDTO> parseListaProcessosConclusosParaListaProcessosConclusosDTO(List<ProcessoConcluso> listaProcessosConclusos) {
        List<ProcessoConclusoDTO> listaProcessosConclusosDTO = new ArrayList<ProcessoConclusoDTO>();
        if (listaProcessosConclusos != null) {
            for (ProcessoConcluso processoConcluso : listaProcessosConclusos) {
                listaProcessosConclusosDTO.add(parseProcessoConclusoParaProcessoConclusoDTO(processoConcluso));
            }
        }
        return listaProcessosConclusosDTO;
    }

    public static ProcessoConclusoDTO parseProcessoConclusoParaProcessoConclusoDTO(ProcessoConcluso processoConcluso) {
        ProcessoConclusoDTO processoConclusoDTO = new ProcessoConclusoDTO();
        processoConclusoDTO.setAno(processoConcluso.getAno());
        processoConclusoDTO.setAnoProcesso(processoConcluso.getAnoProcesso());
        processoConclusoDTO.setCodigoProcessoSaj(processoConcluso.getCodigoProcessoSaj());
        processoConclusoDTO.setDataBaixa(processoConcluso.getDataBaixa());
        processoConclusoDTO.setDataConclusao(processoConcluso.getDataConclusao());
        processoConclusoDTO.setIdBaseOrigemSaj(processoConcluso.getIdBaseOrigemSaj());
        processoConclusoDTO.setIdEntidadeProcessoConcluso(processoConcluso.getIdProcessoConcluso());
        processoConclusoDTO.setMes(processoConcluso.getMes());
        processoConclusoDTO.setNumeroProcesso(processoConcluso.getNumeroProcesso());
        processoConclusoDTO.setSrcFormulario(processoConcluso.getSourceFormulario());
        if (processoConcluso.getUnidade() != null)
            processoConclusoDTO.setIdUnidadeProcesso(processoConcluso.getUnidade().getIdUnidade());
        if (processoConcluso.getUsuario() != null)
            processoConclusoDTO.setIdMagistradoProcesso(processoConcluso.getUsuario().getIdUsuario());
        if (processoConcluso.getTipoConcluso() != null)
            processoConclusoDTO.setTipoConclusoDTO(parseTipoConclusoParaProcessoConclusoDTO(processoConcluso.getTipoConcluso()));
        if (processoConcluso.getTipoFilaProcesso() != null)
            processoConclusoDTO.setTipoFilaProcessoDTO(parseTipoFilaProcessoParaTipoFilaProcessoDTO(processoConcluso.getTipoFilaProcesso()));            
        
        // 03.05.2018
        processoConclusoDTO.setDsAssunto(processoConcluso.getDsAssunto());
        processoConclusoDTO.setDsMovimentacao(processoConcluso.getDsMovimentacao());
        processoConclusoDTO.setFkCadUsuarioManutencao(processoConcluso.getFkCadUsuarioManutencao());
        processoConclusoDTO.setDtDesignacaoInicio(processoConcluso.getDtDesignacaoInicio());
        processoConclusoDTO.setDtDesignacaoFim(processoConcluso.getDtDesignacaoFim());
        // FIM - 03.05.2018
        
        return processoConclusoDTO;
    
    
    
    
    
    
    
    
    
    
    
    
    }

    public static List<ReuDTO> parseListaReusProvisoriosParaListaReusDTO(List<ReuProvisorio> listaReusProvisorios, Integer ano, Integer mes) {
        List<ReuDTO> listaReusDTO = new ArrayList<ReuDTO>();
        for (ReuProvisorio reuProvisorio : listaReusProvisorios) {
            listaReusDTO.add(parseReuProvisorioParaReuDTO(reuProvisorio, ano, mes));
        }
        return listaReusDTO;
    }

    public static ReuDTO parseReuProvisorioParaReuDTO(ReuProvisorio reuProvisorio, Integer ano, Integer mes) {
        ReuDTO reuDTO = new ReuDTO();
        reuDTO.setIdReuProvisorio(reuProvisorio.getIdReuProvisorio());
        reuDTO.setNomeReuProvisorio(reuProvisorio.getNomeReuProvisorio());
        reuDTO.setNomeMaeReuProvisorio(reuProvisorio.getNomeMaeReuProvisorio());
        reuDTO.setCodigoPessoaSaj(reuProvisorio.getCodigoPessoaSaj());
        reuDTO.setIdBaseOrigemSaj(reuProvisorio.getIdBaseOrigemSaj());
        reuDTO.setAno(ano);
        reuDTO.setMes(mes);
        if (reuProvisorio.getUnidade() != null) {
            reuDTO.setIdUnidade(reuProvisorio.getUnidade().getIdUnidade());
        }
        reuDTO.setSexo(reuProvisorio.getSexo());
        
        if(!reuProvisorio.getHistoricosReuProvisorio().isEmpty()){
            for (ReuProvisorioHistorico reuProvisorioHistorico : reuProvisorio.getHistoricosReuProvisorio()) {
                if (reuProvisorioHistorico.getAno().equals(reuDTO.getAno()) && 
                    reuProvisorioHistorico.getMes().equals(reuDTO.getMes())) {
                        reuDTO.setDataBaixa(reuProvisorioHistorico.getDataBaixa());
                        reuDTO.setDtDataBaixa(reuProvisorioHistorico.getDtDataBaixa());
                        reuDTO.setDataLevadoMagistrado(reuProvisorioHistorico.getDataLevadoMagistrado());
                        reuDTO.setDataPrisao(reuProvisorioHistorico.getDataPrisao());
                        if (reuProvisorioHistorico.getTipoMotivoBaixa() != null) {
                            reuDTO.setDescricaoMotivoBaixa(reuProvisorioHistorico.getTipoMotivoBaixa().getDescricaoTipoMotivoBaixa());
                            reuDTO.setIdMotivoBaixa(reuProvisorioHistorico.getTipoMotivoBaixa().getIdTipoMotivoBaixa());
                        }
                        reuDTO.setDescricaoRelatorioCgj(reuProvisorioHistorico.getDescricaoRelatorioCgj());
                        
                        if (reuProvisorioHistorico.getUsuario() != null) {
                            reuDTO.setIdMagistrado(reuProvisorioHistorico.getUsuario().getIdUsuario());
                            reuDTO.setNomeMagistrado(reuProvisorioHistorico.getUsuario().getNome());
                        }
                        if (reuProvisorioHistorico.getEstabelecimentoPrisional() != null) {
                            reuDTO.setIdEstabelecimentoPrisional(reuProvisorioHistorico.getEstabelecimentoPrisional().getIdEstabelecimentoPrisional());
                            reuDTO.setNomeEstabelecimentoPrisional(reuProvisorioHistorico.getEstabelecimentoPrisional().getNomeEstabelecimentoPrisional());
                        }
                    
                        break;
                }
            }
        }
        
        reuDTO = parseListaReuProvisoriosParaReuDTOMesAnoReferencia(reuDTO, reuProvisorio.getHistoricosReuProvisorio());
        return reuDTO;
    }

    public static ReuDTO parseListaReuProvisoriosParaReuDTOMesAnoReferencia(ReuDTO reuDTO,
                                                                            List<ReuProvisorioHistorico> listaReuProvisorioHistorico) {
        if (listaReuProvisorioHistorico != null) {
            for (ReuProvisorioHistorico reuProvisorioHistorico : listaReuProvisorioHistorico) {
                if (reuProvisorioHistorico.getAno().equals(reuDTO.getAno()) && 
                    reuProvisorioHistorico.getMes().equals(reuDTO.getMes())) {
                        reuDTO.setIdReuHistorico(reuProvisorioHistorico.getIdReuProvisorioHistorico());
                        reuDTO.setDataUltimoMovimento(reuProvisorioHistorico.getDataUltimaMovimentacao());
                        reuDTO.setConteudoUltimoMovimento(reuProvisorioHistorico.getDescricaoConteudoUltimaMovimentacao());
                        reuDTO.setNumeroControle(reuProvisorioHistorico.getNumeroControleOrdem());
                        reuDTO.setNumeroProcesso(reuProvisorioHistorico.getNumeroProcesso());
                        reuDTO.setAno(reuProvisorioHistorico.getAno());
                        reuDTO.setMes(reuProvisorioHistorico.getMes());
                        if (reuProvisorioHistorico.getTipoNaturezaPrisao() != null) {
                            reuDTO.setDescricaoNaturezaPrisao(reuProvisorioHistorico.getTipoNaturezaPrisao().getDescricaoTipoNatureza());
                            reuDTO.setIdNaturezaPrisao(reuProvisorioHistorico.getTipoNaturezaPrisao().getIdTipoNaturezaPrisao());
                        }
                        
                        break;
                }
            }
        }
        return reuDTO;
    }

    public static List<EstabelecimentoEntidadeDTO> parseListaInspecaoEstabelecimentoParaListaEstabelecimentoEntidadeDTO(List<InspecaoEstabelecimentoPrisional> listaInspecaoEstabelecimento,
                                                                                                                        List<EstabelecimentoEntidadeDTO> listaEstabelecimentoDTO) {
        for (EstabelecimentoEntidadeDTO estabelecimentoEntidadeDTO : listaEstabelecimentoDTO) {
            for (InspecaoEstabelecimentoPrisional inspecaoEstabelecimentoPrisional : listaInspecaoEstabelecimento) {
                if (estabelecimentoEntidadeDTO.getIdEstabelecimentoEntidade().equals(inspecaoEstabelecimentoPrisional.getEstabelecimentoPrisional().getIdEstabelecimentoPrisional())) {
                    estabelecimentoEntidadeDTO.setIdInspecaoEstabelecimento(inspecaoEstabelecimentoPrisional.getIdInspecaoEstabelecimento());
                    estabelecimentoEntidadeDTO.setDataInspecao(inspecaoEstabelecimentoPrisional.getDataInspecao());
                    estabelecimentoEntidadeDTO.setMotivoInspecaoNaoRealizada(inspecaoEstabelecimentoPrisional.getDescricaoNaoInspecao());
                    estabelecimentoEntidadeDTO.setIdEstabelecimentoEntidade(inspecaoEstabelecimentoPrisional.getEstabelecimentoPrisional().getIdEstabelecimentoPrisional());
                    estabelecimentoEntidadeDTO.setInspecaoNaoRealizada(inspecaoEstabelecimentoPrisional.getFlagInspecaoNaoRealizada().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                                                       true : false);
                    if (inspecaoEstabelecimentoPrisional.getUsuario() != null) {
                        estabelecimentoEntidadeDTO.setIdMagistrado(inspecaoEstabelecimentoPrisional.getUsuario().getIdUsuario());
                        estabelecimentoEntidadeDTO.setNomeMagistrado(inspecaoEstabelecimentoPrisional.getUsuario().getNome());
                    }
                }
            }
        }
        return listaEstabelecimentoDTO;
    }

    public static SubSecaoDTO parseSubSecaoParaSubSecaoDTO(Secao subSecao, SubSecaoDTO subSecaoDTO, SecaoDTO secaoDTO) {
        subSecaoDTO.setIdSubSecao(subSecao.getIdSecao());
        subSecaoDTO.setCodigoSubSecao(subSecao.getMetadadosSecao().getCodigoSigla());
        subSecaoDTO.setSecao(secaoDTO);
        subSecaoDTO.setListaGrupos(parseListaGrupoParaListaGrupoDTO(subSecao.getGrupos(), subSecaoDTO.getListaGrupos(),
                                                                    subSecaoDTO));
        return subSecaoDTO;
    }

    public static List<GrupoDTO> parseListaGrupoParaListaGrupoDTO(List<Grupo> listaGrupos,
                                                                  List<GrupoDTO> listaGruposDTO,
                                                                  SubSecaoDTO subSecaoDTO) {
        if(listaGrupos.size() != listaGruposDTO.size()) {
            System.out.println("Tamanho listaGrupos != listaGruposDTO");
        }
        for (Grupo grupo : listaGrupos) {
            List<GrupoDTO> gmgs = listaGruposDTO.stream()
                .filter(g -> g.getIdMetadadosGrupo().equals(grupo.getMetadadosGrupo().getIdMetadadosGrupo()))
                .collect(Collectors.toList());
            //for (GrupoDTO grupoDTO : listaGruposDTO) {
            if((gmgs != null) && (gmgs.size() == 1) && (gmgs.get(0) != null)) {
                GrupoDTO grupoDTO = gmgs.get(0);
                if (grupoDTO.getIdMetadadosGrupo().equals(grupo.getMetadadosGrupo().getIdMetadadosGrupo())) {
                    grupoDTO.setIdGrupo(grupo.getIdGrupo());
                    grupoDTO.setSubSecao(subSecaoDTO);
                    grupoDTO.setListaCampos(parseListaCamposParaListaCamposDTO(grupo.getCampos(),
                                                                               grupoDTO.getListaCampos(), grupoDTO));
                } else {
                    System.out.println("ID_METADADOS não coincidentes: grupo=" + 
                                       (grupo.getMetadadosGrupo().getIdMetadadosGrupo() != null ? grupo.getMetadadosGrupo().getIdMetadadosGrupo().toString() : "nulo") +
                                       ", grupoDTO=" +
                                       (grupoDTO.getIdMetadadosGrupo() != null ? grupoDTO.getIdMetadadosGrupo().toString() : "nulo"));
                }
            } else {
                System.out.println("ID_METADADOS não encontrado: " + (grupo.getMetadadosGrupo().getIdMetadadosGrupo() != null ? grupo.getMetadadosGrupo().getIdMetadadosGrupo().toString() : "nulo"));
            }
        }
        return listaGruposDTO;
    }

    public static List<CampoDTO> parseListaCamposParaListaCamposDTO(List<Campo> listaCampos,
                                                                    List<CampoDTO> listaCamposDTO, GrupoDTO grupoDTO) {
        if(listaCampos.size() != listaCamposDTO.size()) {
            System.out.println("(1) Tamanho de listaCampos != listaCamposDTO");
        }
        for (Campo campo : listaCampos) {
            List<CampoDTO> cmcs = listaCamposDTO.stream()
                .filter(g -> g.getIdMetadadosCampo().equals(campo.getMetadadosCampo().getIdMetadadosCampo()))
                .collect(Collectors.toList());            
            //for (CampoDTO campoDTO : listaCamposDTO) {
            if((cmcs != null) && (cmcs.size() == 1) && (cmcs.get(0) != null)) {
                CampoDTO campoDTO = cmcs.get(0);
                if (campoDTO.getIdMetadadosCampo().equals(campo.getMetadadosCampo().getIdMetadadosCampo())) {
                    campoDTO.setIdCampo(campo.getIdCampo());
                    campoDTO.setValorCampo(campo.getValorCampo());
                    campoDTO.setFormula((FormulaDTO) campoDTO.getFormula().clonar());
                    if (campo.getCampoPai() != null)
                        campoDTO.setIdCampoPai(campo.getCampoPai().getIdCampo());
                    if (campo.getGrupo() != null)
                        campoDTO.setIdGrupo(campo.getGrupo().getIdGrupo());
                    campoDTO.setGrupo(grupoDTO);
                    campoDTO.setNivelCampo(1);
                    campoDTO.setListaCampos(parseListaCamposParaListaCamposDTO(campo.getCampos(),
                                                                               campoDTO.getListaCampos(), campoDTO,
                                                                               campoDTO.getNivelCampo() + 1));
                } else {
                    System.out.println("(1) ID_METADADOS não coincidentes: campoDTO="+ (campoDTO.getIdMetadadosCampo() != null ? campoDTO.getIdMetadadosCampo().toString() : "nulo") + ", campo=" + (campo.getMetadadosCampo().getIdMetadadosCampo() != null ? campo.getMetadadosCampo().getIdMetadadosCampo().toString() : "nulo"));
                }
            } else {
                System.out.println("(1) ID_METADADOS não encontrado campo=" + (campo.getMetadadosCampo().getIdMetadadosCampo() != null ? campo.getMetadadosCampo().getIdMetadadosCampo().toString() : "nulo"));
            }
        }
        return listaCamposDTO;
    }

    public static List<CampoDTO> parseListaCamposParaListaCamposDTO(List<Campo> listaCampos,
                                                                    List<CampoDTO> listaCamposDTO, CampoDTO campoPaiDTO,
                                                                    Integer nivelCampo) {
        if(listaCampos.size() != listaCamposDTO.size()) {
            System.out.println("(2) Tamanho de listaCampos != listaCamposDTO");
        }
        for (Campo campo : listaCampos) {
            List<CampoDTO> cmcs = listaCamposDTO.stream()
                .filter(g -> g.getIdMetadadosCampo().equals(campo.getMetadadosCampo().getIdMetadadosCampo()))
                .collect(Collectors.toList());            
            if((cmcs != null) && (cmcs.size() == 1) && (cmcs.get(0) != null)) {
                CampoDTO campoDTO = cmcs.get(0);
            // for (CampoDTO campoDTO : listaCamposDTO) {
                if (campoDTO.getIdMetadadosCampo().equals(campo.getMetadadosCampo().getIdMetadadosCampo())) {
                    campoDTO.setIdCampo(campo.getIdCampo()); 
                    campoDTO.setValorCampo(campo.getValorCampo());
                    campoDTO.setFormula((FormulaDTO) campoDTO.getFormula().clonar());
                    if (campo.getCampoPai() != null)
                        campoDTO.setIdCampoPai(campo.getCampoPai().getIdCampo());
                    if (campo.getGrupo() != null)
                        campoDTO.setIdGrupo(campo.getGrupo().getIdGrupo());
                    campoDTO.setCampoPai(campoPaiDTO);
                    campoDTO.setNivelCampo(nivelCampo);
                    campoDTO.setListaCampos(parseListaCamposParaListaCamposDTO(campo.getCampos(),
                                                                               campoDTO.getListaCampos(), campoPaiDTO,
                                                                               campoDTO.getNivelCampo() + 1));
                } else {
                    System.out.println("(2) ID_METADADOS não coincidentes: campoDTO="+ (campoDTO.getIdMetadadosCampo() != null ? campoDTO.getIdMetadadosCampo().toString() : "nulo") + ", campo=" + (campo.getMetadadosCampo().getIdMetadadosCampo() != null ? campo.getMetadadosCampo().getIdMetadadosCampo().toString() : "nulo"));
                }
            } else {
                System.out.println("(2) ID_METADADOS não encontrado campo=" + (campo.getMetadadosCampo().getIdMetadadosCampo() != null ? campo.getMetadadosCampo().getIdMetadadosCampo().toString() : "nulo"));
            }
        }
        return listaCamposDTO;
    }

    // <epr> 0.7.10 - filtrar pela data de início e fim da relação UnidadeXEstab.Prisional
    // incluido parametros ano e mes e processamento do filtro (*)
    public static List<EstabelecimentoEntidadeDTO> parseListaUnidadeEstabelecimentosPrisionaisParaListaEstabelecimentoEntidadeDTO(List<UnidadeEstabelecimentoPrisional> listaUnidadeEstabelecimentosPrisionais, Long ano, Long mes) {
        // <epr> 0.7.10 (*)
        Calendar dataInicio = new java.util.GregorianCalendar(ano.intValue(), mes.intValue()-1, 1);
        //Calendar dataFim = new GregorianCalendar(ano.intValue(), mes.intValue()-1, dataInicio.getActualMaximum(Calendar.DAY_OF_MONTH));
        List<EstabelecimentoEntidadeDTO> listaEstabelecimentoEntidadeDTO = new ArrayList<EstabelecimentoEntidadeDTO>();
        // </epr> 0.7.10 (*)
        boolean adiciona = false;      
        for (UnidadeEstabelecimentoPrisional unidadeEstabelecimentoPrisional : listaUnidadeEstabelecimentosPrisionais) {
            // <epr> 0.7.10 e 0.7.11 (*) if seguinte
            // alteracao para tratar dataFim como nao nula, caso seja nao entrara no formulario - 8/6/18 - Luis Ramalho
            if(
               (
                (unidadeEstabelecimentoPrisional.getDataInicio() == null) || 
                (unidadeEstabelecimentoPrisional.getDataInicio().compareTo(dataInicio.getTime()) <= 0)
               ) && 
                (unidadeEstabelecimentoPrisional.getDataFim() == null)
            ) {
                //(unidadeEstabelecimentoPrisional.getDataFim().compareTo(dataFim.getTime()) >= 0) retirado pois estava apresentando mais registros do esperado
                    adiciona = true;
            } else {
                adiciona = (unidadeEstabelecimentoPrisional.getDataInicio().getMonth() == mes && unidadeEstabelecimentoPrisional.getDataInicio().getYear() == ano);
            }
            
            if (adiciona) {
                // </epr> 0.7.10 e 0.7.11 (*)
                EstabelecimentoEntidadeDTO estabelecimentoEntidadeDTO = new EstabelecimentoEntidadeDTO();
                estabelecimentoEntidadeDTO.setIdEstabelecimentoEntidade(unidadeEstabelecimentoPrisional.getEstabelecimentoPrisional().getIdEstabelecimentoPrisional());
                estabelecimentoEntidadeDTO.setIdVinculoEstabelecimentoEntidade(unidadeEstabelecimentoPrisional.getIdUnidadeEstabelecimentoPrisional());
                estabelecimentoEntidadeDTO.setNomeEstabelecimentoEntidade(unidadeEstabelecimentoPrisional.getEstabelecimentoPrisional().getNomeEstabelecimentoPrisional());
                estabelecimentoEntidadeDTO.setTipoInternacao((unidadeEstabelecimentoPrisional.getEstabelecimentoPrisional().getFlagInternacao().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                                              true : false));
                estabelecimentoEntidadeDTO.setTipoPrisional((unidadeEstabelecimentoPrisional.getEstabelecimentoPrisional().getFlagPrisional().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                                             true : false));
                listaEstabelecimentoEntidadeDTO.add(estabelecimentoEntidadeDTO);                

                adiciona = false;
            }
        }
        return listaEstabelecimentoEntidadeDTO;
    }
    // </epr> 0.7.10 - filtrar pela data de início e fim da relação UnidadeXEstab.Prisional

    public static List<FormularioDTO> parseListaMetadadosFormularioParaListaFormularioDTO(List<MetadadosFormulario> listaMetadadosFormulario) {
        List<FormularioDTO> listaFormularioDTO = null;
        if (listaMetadadosFormulario != null && !listaMetadadosFormulario.isEmpty()) {
            listaFormularioDTO = new ArrayList<FormularioDTO>();
            for (MetadadosFormulario metadadosFormulario : listaMetadadosFormulario) {
                listaFormularioDTO.add(parseMetadadosFormularioParaFormularioDTO(metadadosFormulario));
            }
        }
        return listaFormularioDTO;
    }

    public static FormularioDTO parseMetadadosFormularioParaFormularioDTO(MetadadosFormulario metadadosFormulario) {
        FormularioDTO formularioDTO = new FormularioDTO();
        formularioDTO.setIdMetadadosFormulario(metadadosFormulario.getIdMetadadosFormulario());
        formularioDTO.setNomeFormulario(metadadosFormulario.getDescricaoNome());
        formularioDTO.setCodigoFormulario(metadadosFormulario.getDescricaoSourceFormulario());
        formularioDTO.setAviso(metadadosFormulario.getDescricaoTextoInformativo());
        formularioDTO.setDataCriacao(metadadosFormulario.getDataInclusao());
        formularioDTO.setDataInclusao(metadadosFormulario.getDataInclusao());
        formularioDTO.setInstancia(String.valueOf(metadadosFormulario.getNumeroInstancia()));
        if (metadadosFormulario.getTipoArea() != null)
            formularioDTO.setArea(parseTipoAreaParaAreaDTO(metadadosFormulario.getTipoArea()));
        if (metadadosFormulario.getTipoSegmento() != null)
            formularioDTO.setSegmento(parseTipoSegmentoParaSegmentoDTO(metadadosFormulario.getTipoSegmento()));
        if (metadadosFormulario.getTiposCompetencia() != null && !metadadosFormulario.getTiposCompetencia().isEmpty())
            formularioDTO.setListaCompetencias(parseListaTipoCompetenciaParaListaCompetenciaDTO(metadadosFormulario.getTiposCompetencia()));
        formularioDTO.setSituacao(metadadosFormulario.getFlagTipoSituacao());
        formularioDTO.setVersao(metadadosFormulario.getNumeroVersao());
        formularioDTO.setMetadadoSituacaoFormularioDTO(parseMetadadoTipoSituacaoParaMetadadoSituacaoFormularioDTO(metadadosFormulario.getMetadadosTipoSituacao()));
        /*if (metadadosFormulario.getMetadadosSecoes() != null)
            formularioDTO.setListaSecoes(parseListaMetadadosSecaoParaListaSecaoDTO(metadadosFormulario.getMetadadosSecoes(),
                                                                                   formularioDTO));*/ 
        return formularioDTO;
    }
    
    public static FormularioDTO parseMetadadosFormularioParaFormularioDTOliberacao(MetadadosFormulario metadadosFormulario) {
        FormularioDTO formularioDTO = new FormularioDTO();
        formularioDTO.setIdMetadadosFormulario(metadadosFormulario.getIdMetadadosFormulario());
        formularioDTO.setNomeFormulario(metadadosFormulario.getDescricaoNome());
        formularioDTO.setCodigoFormulario(metadadosFormulario.getDescricaoSourceFormulario());
        formularioDTO.setAviso(metadadosFormulario.getDescricaoTextoInformativo());
        formularioDTO.setDataCriacao(metadadosFormulario.getDataInclusao());
        formularioDTO.setDataInclusao(metadadosFormulario.getDataInclusao());
        formularioDTO.setInstancia(String.valueOf(metadadosFormulario.getNumeroInstancia()));
        if (metadadosFormulario.getTipoArea() != null)
            formularioDTO.setArea(parseTipoAreaParaAreaDTO(metadadosFormulario.getTipoArea()));
        if (metadadosFormulario.getTipoSegmento() != null)
            formularioDTO.setSegmento(parseTipoSegmentoParaSegmentoDTO(metadadosFormulario.getTipoSegmento()));
        if (metadadosFormulario.getTiposCompetencia() != null && !metadadosFormulario.getTiposCompetencia().isEmpty())
            formularioDTO.setListaCompetencias(parseListaTipoCompetenciaParaListaCompetenciaDTO(metadadosFormulario.getTiposCompetencia()));
        formularioDTO.setSituacao(metadadosFormulario.getFlagTipoSituacao());
        formularioDTO.setVersao(metadadosFormulario.getNumeroVersao());
        formularioDTO.setMetadadoSituacaoFormularioDTO(parseMetadadoTipoSituacaoParaMetadadoSituacaoFormularioDTO(metadadosFormulario.getMetadadosTipoSituacao()));
        if (metadadosFormulario.getMetadadosSecoes() != null)
            formularioDTO.setListaSecoes(parseListaMetadadosSecaoParaListaSecaoDTO(metadadosFormulario.getMetadadosSecoes(),
                                                                                   formularioDTO));
        return formularioDTO;
    }
    
    public static FormularioDTO parseMetadadosFormularioParaFormularioDTOParaPaginacao(MetadadosFormulario metadadosFormulario) {
        FormularioDTO formularioDTO = new FormularioDTO();
        //formularioDTO.setIdMetadadosFormulario(metadadosFormulario.getIdMetadadosFormulario());
        formularioDTO.setNomeFormulario(metadadosFormulario.getDescricaoNome());
        /*formularioDTO.setCodigoFormulario(metadadosFormulario.getDescricaoSourceFormulario());
        formularioDTO.setAviso(metadadosFormulario.getDescricaoTextoInformativo());
        formularioDTO.setDataCriacao(metadadosFormulario.getDataInclusao());
        formularioDTO.setDataInclusao(metadadosFormulario.getDataInclusao());
        formularioDTO.setInstancia(String.valueOf(metadadosFormulario.getNumeroInstancia()));
        if (metadadosFormulario.getTipoArea() != null)
            formularioDTO.setArea(parseTipoAreaParaAreaDTO(metadadosFormulario.getTipoArea()));
        if (metadadosFormulario.getTipoSegmento() != null)
            formularioDTO.setSegmento(parseTipoSegmentoParaSegmentoDTO(metadadosFormulario.getTipoSegmento()));
        if (metadadosFormulario.getTiposCompetencia() != null && !metadadosFormulario.getTiposCompetencia().isEmpty())
            formularioDTO.setListaCompetencias(parseListaTipoCompetenciaParaListaCompetenciaDTO(metadadosFormulario.getTiposCompetencia()));
        formularioDTO.setSituacao(metadadosFormulario.getFlagTipoSituacao());
        formularioDTO.setVersao(metadadosFormulario.getNumeroVersao());
        formularioDTO.setMetadadoSituacaoFormularioDTO(parseMetadadoTipoSituacaoParaMetadadoSituacaoFormularioDTO(metadadosFormulario.getMetadadosTipoSituacao()));*/
 
        return formularioDTO;
    }

    public static List<MetadadoSituacaoFormularioDTO> parseListaMetadadosTipoSituacaoParaSituacaoFormularioDTO(List<MetadadosTipoSituacao> listaMetadadosTipoSituacao) {
        List<MetadadoSituacaoFormularioDTO> listaSituacaoFormularioDTO = null;
        if (listaMetadadosTipoSituacao != null) {
            listaSituacaoFormularioDTO = new ArrayList<MetadadoSituacaoFormularioDTO>();
            for (MetadadosTipoSituacao metadadosTipoSituacao : listaMetadadosTipoSituacao) {
                listaSituacaoFormularioDTO.add(parseMetadadoTipoSituacaoParaMetadadoSituacaoFormularioDTO(metadadosTipoSituacao));
            }
        }
        return listaSituacaoFormularioDTO;
    }

    public static MetadadoSituacaoFormularioDTO parseMetadadoTipoSituacaoParaMetadadoSituacaoFormularioDTO(MetadadosTipoSituacao metadadosTipoSituacao) {
        MetadadoSituacaoFormularioDTO situacaoFormularioDTO = new MetadadoSituacaoFormularioDTO();
        situacaoFormularioDTO.setCodigoSituacaoFormulario(metadadosTipoSituacao.getIdMetadadosTipoSituacao());
        situacaoFormularioDTO.setLabelSituacaoFormulario(metadadosTipoSituacao.getDescricaoSituacao());
        situacaoFormularioDTO.setIdentificadorSituacaoFormulario(metadadosTipoSituacao.getTipoSituacao());
        situacaoFormularioDTO.setSituacao(metadadosTipoSituacao.getFlagTipoSituacao());
        situacaoFormularioDTO.setDataInclusao(metadadosTipoSituacao.getDataInclusao());
        return situacaoFormularioDTO;
    }

    public static List<SituacaoFormularioDTO> parseListaTipoSituacaoParaSituacaoFormularioDTO(List<TipoSituacao> listaTipoSituacao) {
        List<SituacaoFormularioDTO> listaSituacaoFormularioDTO = null;
        if (listaTipoSituacao != null) {
            listaSituacaoFormularioDTO = new ArrayList<SituacaoFormularioDTO>();
            for (TipoSituacao tipoSituacao : listaTipoSituacao) {
                listaSituacaoFormularioDTO.add(parseTipoSituacaoParaSituacaoFormularioDTO(tipoSituacao));
            }
        }
        return listaSituacaoFormularioDTO;
    }

    public static SituacaoFormularioDTO parseTipoSituacaoParaSituacaoFormularioDTO(TipoSituacao tipoSituacao) {
        SituacaoFormularioDTO situacaoFormularioDTO = null;
        if (tipoSituacao != null) {
            situacaoFormularioDTO = new SituacaoFormularioDTO();
            situacaoFormularioDTO.setCodigoSituacaoFormulario(tipoSituacao.getIdTipoSituacao());
            situacaoFormularioDTO.setLabelSituacaoFormulario(tipoSituacao.getDescricaoSituacao());
            situacaoFormularioDTO.setIdentificadorSituacaoFormulario(tipoSituacao.getCodigoSituacao());
            situacaoFormularioDTO.setSituacao(tipoSituacao.getTipoSituacao());
            situacaoFormularioDTO.setDataInclusao(tipoSituacao.getDataInclusao());
        }
        return situacaoFormularioDTO;
    }
    
    public static SituacaoFormularioDTO parseTipoSituacaoParaSituacaoFormularioDTOParaPaginacao(TipoSituacao tipoSituacao) {
        SituacaoFormularioDTO situacaoFormularioDTO = null;
        if (tipoSituacao != null) {
            situacaoFormularioDTO = new SituacaoFormularioDTO();
            //situacaoFormularioDTO.setCodigoSituacaoFormulario(tipoSituacao.getIdTipoSituacao());
            situacaoFormularioDTO.setLabelSituacaoFormulario(tipoSituacao.getDescricaoSituacao());
            //situacaoFormularioDTO.setIdentificadorSituacaoFormulario(tipoSituacao.getCodigoSituacao());
            //situacaoFormularioDTO.setSituacao(tipoSituacao.getTipoSituacao());
            //situacaoFormularioDTO.setDataInclusao(tipoSituacao.getDataInclusao());
        }
        return situacaoFormularioDTO;
    }

    public static List<SecaoDTO> parseListaMetadadosSecaoParaListaSecaoDTO(List<MetadadosSecao> listaMetadadosSecao,
                                                                           FormularioDTO formularioDTO) {
        List<SecaoDTO> listaSecaoDTO = null;
        if (listaMetadadosSecao != null && !listaMetadadosSecao.isEmpty()) {
            listaSecaoDTO = new ArrayList<SecaoDTO>();
            for (MetadadosSecao metadadosSecao : listaMetadadosSecao) {
                listaSecaoDTO.add(parseEntidadeSecaoParaSecaoDTO(metadadosSecao, formularioDTO));
                Collections.sort(listaSecaoDTO);
            }
        }
        return listaSecaoDTO;
    }

    public static SecaoDTO parseEntidadeSecaoParaSecaoDTO(MetadadosSecao metadadosSecao, FormularioDTO formularioDTO) {
        SecaoDTO secaoDTO = new SecaoDTO();
        secaoDTO.setCodigoSecao(metadadosSecao.getCodigoSigla());
        secaoDTO.setIdMetadadosSecao(metadadosSecao.getIdMetadadosSessao());
        secaoDTO.setLabelSecao(metadadosSecao.getDescricaoNome());
        secaoDTO.setTextoInformativo(metadadosSecao.getDescricaoTextoInformativo());
        secaoDTO.setTotalizadores((metadadosSecao.getFlagExibeTotais().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                   true : false));
        secaoDTO.setConclusos((metadadosSecao.getFlagConclusoPara().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ? true :
                               false));
        secaoDTO.setTipoInternacao((metadadosSecao.getFlagInternacao().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                    true : false));
        secaoDTO.setTipoPrisional((metadadosSecao.getFlagPrisional().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ? true :
                                   false));
        secaoDTO.setTabelaProcessos((metadadosSecao.getFlagTemProcesso().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                     true : false));
        secaoDTO.setOrdemSecao(metadadosSecao.getNumeroOrdem());
        secaoDTO.setDataInclusao(metadadosSecao.getDataInclusao());
        secaoDTO.setSituacao(metadadosSecao.getFlagTipoSituacao());
        secaoDTO.setCodigoMagistrado(metadadosSecao.getIndiceSecaoMagistrado());
        secaoDTO.setLabelMagistrado(metadadosSecao.getDescricaoLabelSecaoMagistrado());
        secaoDTO.setInformativoMagistrado(metadadosSecao.getDescricaoTextoInformativoSecaoMagistrado());
        secaoDTO.setListaMaterias(parseListaTipoMateriaParaListaTipoMateriaDTO(metadadosSecao.getTiposMateria()));
        if (metadadosSecao.getMetadadosGrupos() != null)
            secaoDTO.setListaGrupos(parseListaEntidadeGrupoParaListaGrupoDTO(metadadosSecao.getMetadadosGrupos(),
                                                                             secaoDTO));
        secaoDTO.setFormulario(formularioDTO);
        if (metadadosSecao.getCodigoSigla().equals(SecaoType.DADOS_UNIDADES.getCodigoSecao())) {
            secaoDTO.getListaSubSecoes().add(new SubSecaoDTO(secaoDTO));
        } else if (metadadosSecao.getCodigoSigla().equals(SecaoType.ESTABELECIMENTOS_PRISIONAIS.getCodigoSecao())) {
            secaoDTO.getListaSubSecoes().add(new SubSecaoDTO(secaoDTO));
        } else if (metadadosSecao.getCodigoSigla().equals(SecaoType.REUS.getCodigoSecao())) {
            secaoDTO.getListaSubSecoes().add(new SubSecaoDTO(secaoDTO));
        }
        return secaoDTO;
    }

    public static List<TipoMateriaDTO> parseListaTipoMateriaParaListaTipoMateriaDTO(List<TipoMateria> listaTipoMateria) {
        List<TipoMateriaDTO> listaTipoMateriaDTO = null;
        if (listaTipoMateria != null) {
            listaTipoMateriaDTO = new ArrayList<TipoMateriaDTO>();
            for (TipoMateria tipoMateria : listaTipoMateria) {
                listaTipoMateriaDTO.add(parseTipoMateriaParaTipoMateriaDTO(tipoMateria));
            }
        }
        return listaTipoMateriaDTO;
    }

    public static TipoMateriaDTO parseTipoMateriaParaTipoMateriaDTO(TipoMateria tipoMateria) {
        TipoMateriaDTO tipoMateriaDTO = new TipoMateriaDTO();
        tipoMateriaDTO.setCodigoTipoMateria(tipoMateria.getIdTipoMateria());
        tipoMateriaDTO.setNomeTipoMateria(tipoMateria.getDescricaoMateria());
        tipoMateriaDTO.setDataInclusao(tipoMateria.getDataInclusao());
        tipoMateriaDTO.setSituacao(tipoMateria.getFlagTipoSituacao());
        return tipoMateriaDTO;
    }


    public static List<GrupoDTO> parseListaEntidadeGrupoParaListaGrupoDTO(List<MetadadosGrupo> listaMetadadosGrupo,
                                                                          SecaoDTO secaoDTO) {
        List<GrupoDTO> listaGruposDTO = null;
        if (listaMetadadosGrupo != null && !listaMetadadosGrupo.isEmpty()) {
            listaGruposDTO = new ArrayList<GrupoDTO>();
            for (MetadadosGrupo metadadosGrupo : listaMetadadosGrupo) {
                listaGruposDTO.add(parseEntidadeGrupoParaGrupoDTO(metadadosGrupo, secaoDTO));
                Collections.sort(listaGruposDTO);
            }
        }
        return listaGruposDTO;
    }

    public static GrupoDTO parseEntidadeGrupoParaGrupoDTO(MetadadosGrupo metadadosGrupo, SecaoDTO secaoDTO) {
        GrupoDTO grupoDTO = new GrupoDTO();
        grupoDTO.setIdMetadadosGrupo(metadadosGrupo.getIdMetadadosGrupo());
        grupoDTO.setCodigoGrupo(metadadosGrupo.getCodigoSigla());
        grupoDTO.setLabelGrupo(metadadosGrupo.getDescricaoNome());
        grupoDTO.setOrdemGrupo(metadadosGrupo.getNumeroOrdem());
        grupoDTO.setDominioBI(metadadosGrupo.getCodigoDominioBI());
        grupoDTO.setTextoInformativo(metadadosGrupo.getDescricaoTextoInformativo());
        grupoDTO.setSituacao(metadadosGrupo.getFlagTipoSituacao());
        grupoDTO.setDataInclusao(metadadosGrupo.getDataInclusao());
        grupoDTO.setTipoRegraDTO(parseMetadadosTipoRegraParaTipoRegraDTO(metadadosGrupo.getMetadadosTipoRegra()));
        if (grupoDTO.getTipoRegraDTO() != null)
            grupoDTO.getTipoRegraDTO().setInverterRegra((metadadosGrupo.getFlagInverterTipoRegra().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                                         true : false));
        if (metadadosGrupo.getMetadadosGruposCampo() != null)
            grupoDTO.setListaCampos(parseListaGrupoCampoParaListaCampoDTO(metadadosGrupo.getMetadadosGruposCampo(),
                                                                          grupoDTO));
        grupoDTO.setSecao(secaoDTO);
        return grupoDTO;
    }

    public static List<CampoDTO> parseListaGrupoCampoParaListaCampoDTO(List<MetadadosGrupoCampo> listaMetadadosGrupoCampo,
                                                                       GrupoDTO grupoDTO) {
        List<CampoDTO> listaCamposDTO = null;
        if (listaMetadadosGrupoCampo != null && !listaMetadadosGrupoCampo.isEmpty()) {
            listaCamposDTO = new ArrayList<CampoDTO>();
            for (MetadadosGrupoCampo metadadosGrupoCampo : listaMetadadosGrupoCampo) {
                listaCamposDTO.add(parseEntidadeGrupoCampoParaCampoDTO(metadadosGrupoCampo, grupoDTO));
                Collections.sort(listaCamposDTO);
            }
        }
        return listaCamposDTO;
    }

    public static List<CampoDTO> parseListaMetadadosCampoParaListaCampoDTO(List<MetadadosCampo> listaMetadadosCampo) {
        List<CampoDTO> listaCamposDTO = null;
        if (listaMetadadosCampo != null) {
            listaCamposDTO = new ArrayList<CampoDTO>();
            for (MetadadosCampo metadadosCampo : listaMetadadosCampo) {
                listaCamposDTO.add(parseMetadadosCampoParaCampoDTO(metadadosCampo));
            }
        }
        return listaCamposDTO;
    }

    public static CampoDTO parseMetadadosCampoParaCampoDTO(MetadadosCampo metadadosCampo) {
        CampoDTO campoDTO = new CampoDTO();
        campoDTO.setCasasDecimais(metadadosCampo.getNumeroCasasDecimais());
        campoDTO.setCodigoBI(metadadosCampo.getCodigoDominioBI());
        campoDTO.setHint(metadadosCampo.getDescricaoTextoInformativo());
        campoDTO.setIdMetadadosCampo(metadadosCampo.getIdMetadadosCampo());
        campoDTO.setLabelCampo(metadadosCampo.getNomeCampo());
        campoDTO.setListaItensSelecaoDTO(parseListaMetadadosListaItemSelecaoParaListaItemSelecaoDTO(metadadosCampo.getMetadadosListaSelecao()));
        campoDTO.setListaValidacoes(parseListaMetadadosValidacaoCampoParaListaValidacaoDTO(metadadosCampo.getMetadadosValidacaoCampo()));
        campoDTO.setNumeroMaximoCaracteres(metadadosCampo.getNumeroMaximoCaracteres());
        campoDTO.setNumeroMinimoCaracteres(metadadosCampo.getNumeroMinimoCaracteres());
        campoDTO.setRequerido(true);
        campoDTO.setTipoCampo(TipoCampoType.retornarTipoCampoEnum(metadadosCampo.getTipoCampo()));
        campoDTO.setDataInclusao(metadadosCampo.getDataInclusao());
        campoDTO.setTipoRegraDTO(parseMetadadosTipoRegraParaTipoRegraDTO(metadadosCampo.getMetadadosTipoRegra()));
        if (campoDTO.getTipoRegraDTO() != null)
            campoDTO.getTipoRegraDTO().setInverterRegra((metadadosCampo.getFlagInverterTipoRegra().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                                         true : false));
        return campoDTO;
    }

    public static CampoDTO parseEntidadeGrupoCampoParaCampoDTO(MetadadosGrupoCampo mdGrupoCampo, GrupoDTO grupoDTO) {
        CampoDTO campoDTO = new CampoDTO(); 
        if (mdGrupoCampo != null) {
            campoDTO.setIdMetadadosGrupoCampo(mdGrupoCampo.getIdMetadadosGrupoCampo());
            campoDTO.setIdMetadadosCampo(mdGrupoCampo.getMetadadosCampo().getIdMetadadosCampo());
            campoDTO.setDataInclusao(mdGrupoCampo.getDataInclusao());
            campoDTO.setSituacao(mdGrupoCampo.getFlagTipoSituacao());
            campoDTO.setHint(mdGrupoCampo.getMetadadosCampo().getDescricaoTextoInformativo());
            campoDTO.setLabelCampo(mdGrupoCampo.getMetadadosCampo().getNomeCampo());
            campoDTO.setTipoCampo(TipoCampoType.retornarTipoCampoEnum(mdGrupoCampo.getMetadadosCampo().getTipoCampo()));
            campoDTO.setRequerido(true);
            campoDTO.setCasasDecimais(mdGrupoCampo.getMetadadosCampo().getNumeroCasasDecimais());
            campoDTO.setCodigoBI(mdGrupoCampo.getMetadadosCampo().getCodigoDominioBI());
            campoDTO.setCodigoCampo(mdGrupoCampo.getCodigoSigla());
            if (mdGrupoCampo.getDescricaoFormula() != null && !mdGrupoCampo.getDescricaoFormula().isEmpty())
                campoDTO.setFormula(new FormulaDTO(mdGrupoCampo.getDescricaoFormula()));
            campoDTO.setTipoRegraDTO(parseMetadadosTipoRegraParaTipoRegraDTO(mdGrupoCampo.getMetadadosCampo().getMetadadosTipoRegra()));
            if (campoDTO.getTipoRegraDTO() != null)
                campoDTO.getTipoRegraDTO().setInverterRegra((mdGrupoCampo.getMetadadosCampo().getFlagInverterTipoRegra().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                                             true : false));
            campoDTO.setIndiceCampo(mdGrupoCampo.getDescricaoSequencia());
            campoDTO.setListaItensSelecaoDTO(parseListaMetadadosListaItemSelecaoParaListaItemSelecaoDTO(mdGrupoCampo.getMetadadosCampo().getMetadadosListaSelecao()));
            campoDTO.setListaValidacoes(parseListaMetadadosValidacaoCampoParaListaValidacaoDTO(mdGrupoCampo.getMetadadosCampo().getMetadadosValidacaoCampo()));
            campoDTO.setNumeroMaximoCaracteres(mdGrupoCampo.getMetadadosCampo().getNumeroMaximoCaracteres());
            campoDTO.setNumeroMinimoCaracteres(mdGrupoCampo.getMetadadosCampo().getNumeroMinimoCaracteres());
            campoDTO.setOrdemCampo(mdGrupoCampo.getNumeroOrdem());
            campoDTO.setTipoCampo(TipoCampoType.retornarTipoCampoEnum(mdGrupoCampo.getMetadadosCampo().getTipoCampo()));
            campoDTO.setListaCampos(parseListaMetadadosCampoCampoParaListaCampoDTO(mdGrupoCampo.getMetadadosCampo().getMetadadosCamposPai(),
                                                                                   campoDTO));
            campoDTO.setGrupo(grupoDTO);
        }
        return campoDTO;
    }

    public static List<CampoDTO> parseListaMetadadosCampoCampoParaListaCampoDTO(List<MetadadosCampoCampo> listaMetadadosCampoCampo,
                                                                                CampoDTO campoDTO) {
        List<CampoDTO> listaCamposDTO = new ArrayList<CampoDTO>();
        for (MetadadosCampoCampo metadadosCampoCampo : listaMetadadosCampoCampo) {
            listaCamposDTO.add(parseMetadadosCampoCampoParaCampoDTO(metadadosCampoCampo, campoDTO));
            Collections.sort(listaCamposDTO);
        }
        return listaCamposDTO;
    }

    public static CampoDTO parseMetadadosCampoCampoParaCampoDTO(MetadadosCampoCampo metadadosCampoCampo,
                                                                CampoDTO campoPaiDTO) {
        CampoDTO campoDTO = new CampoDTO();
        if (metadadosCampoCampo != null) {
            campoDTO.setIdMetadadosCampoCampo(metadadosCampoCampo.getIdCampoCampo());
            campoDTO.setIdMetadadosCampo(metadadosCampoCampo.getMetadadosCampoFilho().getIdMetadadosCampo());
            campoDTO.setDataInclusao(metadadosCampoCampo.getDataInclusao());
            campoDTO.setSituacao(metadadosCampoCampo.getFlagTipoSituacao());
            campoDTO.setHint(metadadosCampoCampo.getMetadadosCampoFilho().getDescricaoTextoInformativo());
            campoDTO.setLabelCampo(metadadosCampoCampo.getMetadadosCampoFilho().getNomeCampo());
            campoDTO.setTipoCampo(TipoCampoType.retornarTipoCampoEnum(metadadosCampoCampo.getMetadadosCampoFilho().getTipoCampo()));
            campoDTO.setRequerido(true);
            campoDTO.setCasasDecimais(metadadosCampoCampo.getMetadadosCampoFilho().getNumeroCasasDecimais());
            campoDTO.setCodigoBI(metadadosCampoCampo.getMetadadosCampoFilho().getCodigoDominioBI());
            campoDTO.setCodigoCampo(metadadosCampoCampo.getCodigoSigla());
            if (metadadosCampoCampo.getDescricaoFormula() != null &&
                !metadadosCampoCampo.getDescricaoFormula().isEmpty())
                campoDTO.setFormula(new FormulaDTO(metadadosCampoCampo.getDescricaoFormula()));
            campoDTO.setTipoRegraDTO(parseMetadadosTipoRegraParaTipoRegraDTO(metadadosCampoCampo.getMetadadosCampoFilho().getMetadadosTipoRegra()));
            if (campoDTO.getTipoRegraDTO() != null)
                campoDTO.getTipoRegraDTO().setInverterRegra((metadadosCampoCampo.getMetadadosCampoFilho().getFlagInverterTipoRegra().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                                             true : false));
            campoDTO.setIndiceCampo(metadadosCampoCampo.getDescricaoSequencia());
            campoDTO.setListaItensSelecaoDTO(parseListaMetadadosListaItemSelecaoParaListaItemSelecaoDTO(metadadosCampoCampo.getMetadadosCampoFilho().getMetadadosListaSelecao()));
            campoDTO.setListaValidacoes(parseListaMetadadosValidacaoCampoParaListaValidacaoDTO(metadadosCampoCampo.getMetadadosCampoFilho().getMetadadosValidacaoCampo()));
            campoDTO.setNumeroMaximoCaracteres(metadadosCampoCampo.getMetadadosCampoFilho().getNumeroMaximoCaracteres());
            campoDTO.setNumeroMinimoCaracteres(metadadosCampoCampo.getMetadadosCampoFilho().getNumeroMinimoCaracteres());
            campoDTO.setOrdemCampo(metadadosCampoCampo.getNumeroOrdem());
            campoDTO.setTipoCampo(TipoCampoType.retornarTipoCampoEnum(metadadosCampoCampo.getMetadadosCampoFilho().getTipoCampo()));
            campoDTO.setListaCampos(parseListaMetadadosCampoCampoParaListaCampoDTO(metadadosCampoCampo.getMetadadosCampoFilho().getMetadadosCamposPai(),
                                                                                   campoDTO));
            campoDTO.setCampoPai(campoPaiDTO);
        }
        return campoDTO;
    }

    public static List<ValidacaoDTO> parseListaMetadadosValidacaoCampoParaListaValidacaoDTO(List<MetadadosValidacaoCampo> listaMetadadosValidacaoCampo) {
        List<ValidacaoDTO> listaValidacaoDTO = null;
        if (listaMetadadosValidacaoCampo != null) {
            listaValidacaoDTO = new ArrayList<ValidacaoDTO>();
            for (MetadadosValidacaoCampo metadadosValidacaoCampo : listaMetadadosValidacaoCampo) {
                listaValidacaoDTO.add(parseValidacaoDTOParaMetadadosValidacaoCampo(metadadosValidacaoCampo));
            }
        }
        return listaValidacaoDTO;
    }

    public static ValidacaoDTO parseValidacaoDTOParaMetadadosValidacaoCampo(MetadadosValidacaoCampo metadadosValidacaoCampo) {
        ValidacaoDTO validacaoDTO = new ValidacaoDTO();
        validacaoDTO.setCodigoValidacao(metadadosValidacaoCampo.getIdMetadadosValidacaoCampo());
        validacaoDTO.setMensagem(metadadosValidacaoCampo.getDescricaoMensagem());
        validacaoDTO.setSituacao(metadadosValidacaoCampo.getFlagTipoSituacao());
        validacaoDTO.setTipoValidacao(TipoValidacaoType.getTipoValidacaoByCodigo(metadadosValidacaoCampo.getFlagTipoValidacao()));
        validacaoDTO.setFormula(new FormulaDTO(metadadosValidacaoCampo.getDescricaoFormula()));
        validacaoDTO.setDataInclusao(metadadosValidacaoCampo.getDataInclusao());
        validacaoDTO.setSituacao(metadadosValidacaoCampo.getFlagTipoSituacao());
        return validacaoDTO;
    }

    public static List<SegmentoDTO> parseListaTipoSegmentoParaListaSegmentoDTO(List<TipoSegmento> listaTipoSegmento) {
        List<SegmentoDTO> listaDTO = new ArrayList<SegmentoDTO>();
        for (TipoSegmento tipoSegmento : listaTipoSegmento) {
            listaDTO.add(parseTipoSegmentoParaSegmentoDTO(tipoSegmento));
        }
        return listaDTO;
    }

    public static SegmentoDTO parseTipoSegmentoParaSegmentoDTO(TipoSegmento tipoSegmento) {
        SegmentoDTO segmentoDTO = new SegmentoDTO();
        segmentoDTO.setCodigoSegmento(tipoSegmento.getIdTipoSegmento());
        segmentoDTO.setLabelSegmento(tipoSegmento.getDescricaoSegmento());
        segmentoDTO.setSituacao(tipoSegmento.getFlagTipoSituacao());
        segmentoDTO.setDataInclusao(tipoSegmento.getDataInclusao());
        segmentoDTO.setSituacao(tipoSegmento.getFlagTipoSituacao());
        return segmentoDTO;
    }

    public static List<MateriaDTO> parseListaTipoAreaParaListaAreaDTO(List<TipoArea> listaTipoArea) {
        List<MateriaDTO> listaDTO = new ArrayList<MateriaDTO>();
        for (TipoArea tipoArea : listaTipoArea) {
            listaDTO.add(parseTipoAreaParaAreaDTO(tipoArea));
        }
        return listaDTO;
    }

    public static MateriaDTO parseTipoAreaParaAreaDTO(TipoArea tipoArea) {
        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setCodigoArea(tipoArea.getIdTipoArea());
        materiaDTO.setLabelArea(tipoArea.getDescricaoArea());
        materiaDTO.setSituacao(tipoArea.getFlagTipoSituacao());
        materiaDTO.setDataInclusao(tipoArea.getDataInclusao());
        materiaDTO.setSituacao(tipoArea.getFlagTipoSituacao());
        return materiaDTO;
    }

    public static List<CompetenciaDTO> parseListaTipoCompetenciaParaListaCompetenciaDTO(List<TipoCompetencia> listaTipoCompetencia) {
        List<CompetenciaDTO> listaCompetenciasDTO = new ArrayList<CompetenciaDTO>();
        for (TipoCompetencia competencia : listaTipoCompetencia) {
            CompetenciaDTO competenciaDTO = parseTipoCompetenciaParaCompetenciaDTO(competencia);
            if (competenciaDTO != null)
                listaCompetenciasDTO.add(competenciaDTO);
        }
        return listaCompetenciasDTO;
    }

    public static CompetenciaDTO parseTipoCompetenciaParaCompetenciaDTO(TipoCompetencia tipoCompetencia) {
        CompetenciaDTO competenciaDTO = null;
        if (tipoCompetencia != null && tipoCompetencia.getCodigoCompetenciaSaj() != null) {
            competenciaDTO = new CompetenciaDTO();
            competenciaDTO.setCodigoCompetencia(tipoCompetencia.getIdTipoCompetencia());
            competenciaDTO.setLabelCompetencia(tipoCompetencia.getDescricaoCompetenciaSaj());
            competenciaDTO.setSituacao(tipoCompetencia.getFlagTipoSituacao());
            competenciaDTO.setDataInclusao(tipoCompetencia.getDataInclusao());
            competenciaDTO.setSituacao(tipoCompetencia.getFlagTipoSituacao());
        }
        return competenciaDTO;
    }

    public static List<TipoRegraDTO> parseListaMetadadosTipoRegraParaListaTipoRegraDTO(List<MetadadosTipoRegra> listaMetadadosTipoRegra) {
        // <epr> 0.7.13 - erro ao liberar formulario vinculação (item 93)
        // abaixo, listaTipoRegraDTO era inicializado com null
        // </epr> 0.7.13
        List<TipoRegraDTO> listaTipoRegraDTO = new ArrayList<TipoRegraDTO>();
        if (listaMetadadosTipoRegra != null) {
            // <epr> 0.7.13 - erro ao liberar formulario vinculação (item 93)
            // listaTipoRegraDTO = new ArrayList<TipoRegraDTO>();
            // </epr:
            for (MetadadosTipoRegra metadadosTipoRegra : listaMetadadosTipoRegra) {
                listaTipoRegraDTO.add(parseMetadadosTipoRegraParaTipoRegraDTO(metadadosTipoRegra));
            }
        }
        return listaTipoRegraDTO;
    }

    public static TipoRegraDTO parseMetadadosTipoRegraParaTipoRegraDTO(MetadadosTipoRegra metadadosTipoRegra) {
        TipoRegraDTO tipoRegraDTO = null;
        if (metadadosTipoRegra != null) {
            tipoRegraDTO = new TipoRegraDTO();
            tipoRegraDTO.setCodigoTipoRegra(metadadosTipoRegra.getIdMetadadosTipoRegra());
            tipoRegraDTO.setDescricaoTipoRegra(metadadosTipoRegra.getDescricaoTipoRegra());
            tipoRegraDTO.setNomeTipoRegra(metadadosTipoRegra.getDescricaoNome());
            tipoRegraDTO.setSituacao(metadadosTipoRegra.getFlagTipoSituacao());
            tipoRegraDTO.setDataInclusao(metadadosTipoRegra.getDataInclusao());
        }
        return tipoRegraDTO;
    }

    //Entidade para DTO

    public static TipoArea parseAreaDTOParaTipoArea(MateriaDTO materiaDTO) {
        TipoArea tipoArea = null;
        if (materiaDTO != null && materiaDTO.getCodigoArea() != null) {
            tipoArea = new TipoArea();
            tipoArea.setIdTipoArea(materiaDTO.getCodigoArea());
            tipoArea.setDescricaoArea(materiaDTO.getLabelArea());
            if (materiaDTO.getSituacao() == null || materiaDTO.getSituacao().isEmpty()) {
                tipoArea.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            } else {
                tipoArea.setFlagTipoSituacao(materiaDTO.getSituacao());
            }
            tipoArea.setDataInclusao(materiaDTO.getDataInclusao());
        }
        return tipoArea;
    }

    public static TipoSegmento parseSegmentoDTOParaTipoSegmento(SegmentoDTO segmentoDTO) {
        TipoSegmento tipoSegmento = null;
        if (segmentoDTO != null && segmentoDTO.getCodigoSegmento() != null) {
            tipoSegmento = new TipoSegmento();
            tipoSegmento.setIdTipoSegmento(segmentoDTO.getCodigoSegmento());
            tipoSegmento.setDescricaoSegmento(segmentoDTO.getLabelSegmento());
            if (segmentoDTO.getSituacao() == null || segmentoDTO.getSituacao().isEmpty()) {
                tipoSegmento.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            } else {
                tipoSegmento.setFlagTipoSituacao(segmentoDTO.getSituacao());
            }
            tipoSegmento.setDataInclusao(segmentoDTO.getDataInclusao());
        }
        return tipoSegmento;
    }

    public static TipoCompetencia parseCompetenciaDTOParaTipoCompetencia(CompetenciaDTO competenciaDTO) {
        TipoCompetencia tipoCompetencia = new TipoCompetencia();
        tipoCompetencia.setIdTipoCompetencia(competenciaDTO.getCodigoCompetencia());
        tipoCompetencia.setDescricaoCompetenciaSaj(competenciaDTO.getLabelCompetencia());
        if (competenciaDTO.getSituacao() == null || competenciaDTO.getSituacao().isEmpty()) {
            tipoCompetencia.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        } else {
            tipoCompetencia.setFlagTipoSituacao(competenciaDTO.getSituacao());
        }
        tipoCompetencia.setDataInclusao(competenciaDTO.getDataInclusao());
        return tipoCompetencia;
    }

    public static List<TipoCompetencia> parseListaCompetenciaDTOParaListaTipoCompetencia(List<CompetenciaDTO> listaCompetenciaDTO) {
        List<TipoCompetencia> listaTipoCompetencia = new ArrayList<TipoCompetencia>();
        for (CompetenciaDTO competenciaDTO : listaCompetenciaDTO) {
            listaTipoCompetencia.add(parseCompetenciaDTOParaTipoCompetencia(competenciaDTO));
        }
        return listaTipoCompetencia;
    }

    public static MetadadosTipoSituacao parseMetadadoSituacaoFormularioDTOParaMetadadosTipoSituacao(MetadadoSituacaoFormularioDTO situacaoFormularioDTO) {
        MetadadosTipoSituacao metadadosTipoSituacao = null;
        if (situacaoFormularioDTO != null) {
            metadadosTipoSituacao = new MetadadosTipoSituacao();
            metadadosTipoSituacao.setIdMetadadosTipoSituacao(situacaoFormularioDTO.getCodigoSituacaoFormulario());
            metadadosTipoSituacao.setDescricaoSituacao(situacaoFormularioDTO.getLabelSituacaoFormulario());
            if (situacaoFormularioDTO.getSituacao() == null || situacaoFormularioDTO.getSituacao().isEmpty()) {
                metadadosTipoSituacao.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            } else {
                metadadosTipoSituacao.setFlagTipoSituacao(situacaoFormularioDTO.getSituacao());
            }
            metadadosTipoSituacao.setDataInclusao(situacaoFormularioDTO.getDataInclusao());
        }
        return metadadosTipoSituacao;
    }

    public static TipoSituacao parseTipoSituacaoFormularioDTOParaTipoSituacao(SituacaoFormularioDTO situacaoFormularioDTO) {
        TipoSituacao tipoSituacao = null;
        if (situacaoFormularioDTO != null) {
            tipoSituacao = new TipoSituacao();
            tipoSituacao.setIdTipoSituacao(situacaoFormularioDTO.getCodigoSituacaoFormulario());
            tipoSituacao.setDescricaoSituacao(situacaoFormularioDTO.getLabelSituacaoFormulario());
            if (situacaoFormularioDTO.getSituacao() == null || situacaoFormularioDTO.getSituacao().isEmpty()) {
                tipoSituacao.setTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            } else {
                tipoSituacao.setTipoSituacao(situacaoFormularioDTO.getSituacao());
            }
            tipoSituacao.setDataInclusao(situacaoFormularioDTO.getDataInclusao());
        }
        return tipoSituacao;
    }

    //DTOs para Entidades
    private static FormularioHistorico parseFormularioDTOParaFormularioHistorico(FormularioDTO formularioDTO,
                                                                                 Formulario formulario) {
        FormularioHistorico formularioHistorico = null;
        if (formularioDTO.getNovaSituacaoFormulario() != null) {
            /**
             *  Validacao para gerar o Historico do Formulario
             **/
            if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.ABERTO.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Aberto'");
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.EM_PREENCHIMENTO.getCodigo())) {
                if (formularioDTO.getSituacaoFormularioDTO() == null ||
                    !formularioDTO.getSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().equals(formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario())) {
                    formularioDTO.setObservacaoHistorico("Situação alterada para 'Em Preenchimento'");
                    formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
                }
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.CONCLUIDO.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Concluído'");
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.DEVOLVIDO.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Devolvido' - Motivo: " +
                                                     formularioDTO.getObservacaoHistorico());
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.ENVIADO_CGJ.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Enviado CGJ'");
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.RETIFICACAO_SOLICITADA.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Retificação Solicitada' - Motivo: " +
                                                     formularioDTO.getObservacaoHistorico());
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.RETIFICACAO_REPROVADA.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Retificaçao Reprovada' - Motivo: " +
                                                     formularioDTO.getObservacaoHistorico());
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.RETIFICACAO_APROVADA.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Retificação Aprovada' - Motivo: " +
                                                     formularioDTO.getObservacaoHistorico());
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.RETIFICACAO_EM_PREENCHIMENTO.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Retificação em Preenchimento'");
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.RETIFICACAO_CONCLUIDA.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Retificação Concluída'");
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.RETIFICACAO_DEVOLVIDA.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Retificação Devolvida' - Motivo: " +
                                                     formularioDTO.getObservacaoHistorico());
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            } else if (formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.RETIFICACAO_ENVIADA_AO_CGJ.getCodigo())) {
                formularioDTO.setObservacaoHistorico("Situação alterada para 'Retificação Enviada ao CGJ'");
                formularioHistorico = setValoresFormularioHistorico(formulario, formularioDTO);
            }
        }
        return formularioHistorico;
    }

    public static FormularioHistorico setValoresFormularioHistorico(Formulario formulario,
                                                                    FormularioDTO formularioDTO) {
        FormularioHistorico formularioHistorico = new FormularioHistorico();
        if (formulario != null) {
            formularioHistorico.setFormulario(formulario);
        }
        formularioHistorico.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        formularioHistorico.setTipoSituacao(parseTipoSituacaoFormularioDTOParaTipoSituacao(formularioDTO.getNovaSituacaoFormulario()));
        if(formularioDTO.getNovaSituacaoFormulario().getIdentificadorSituacaoFormulario().equals(TipoSituacaoType.EM_PREENCHIMENTO.getCodigo())) {
        if (formularioDTO.getIdUsuarioPreenchimento() != null) {
            formularioHistorico.setUsuario(new Usuario(formularioDTO.getIdUsuarioPreenchimento()));
        }
        } else if (formularioDTO.getIdUsuarioAlteracao() != null){
            formularioHistorico.setUsuario(new Usuario(formularioDTO.getIdUsuarioAlteracao()));
        }
        formularioHistorico.setDataCriacao(new Date());
        formularioHistorico.setDescricaoComentario(formularioDTO.getObservacaoHistorico());
        return formularioHistorico;
    }

    public static HistoricoFormularioDTO parseFormularioHistoricoParaHistoricoFormularioDTO(FormularioHistorico formularioHistorico) {
        HistoricoFormularioDTO historico = new HistoricoFormularioDTO();
        historico.setIdFormularioHistorico(formularioHistorico.getIdFormularioHistorico());
        historico.setIdFormulario(formularioHistorico.getFormulario().getIdFormulario());
        historico.setSituacao(formularioHistorico.getFlagTipoSituacao());
        historico.setSituacaoFormularioDTO(parseTipoSituacaoParaSituacaoFormularioDTO(formularioHistorico.getTipoSituacao()));
        if (formularioHistorico.getUsuario() != null) {
            historico.setIdUsuario(formularioHistorico.getUsuario().getIdUsuario());
            historico.setNomeUsuario(formularioHistorico.getUsuario().getNome());
        }
        historico.setDataCriacao(formularioHistorico.getDataCriacao());
        historico.setDescricaoComentario(formularioHistorico.getDescricaoComentario());
        return historico;
    }


    public static Formulario parseFormularioDTOParaFormulario(FormularioDTO formularioDTO, boolean novaVersao) {
        Formulario formulario = new Formulario();
        if (formularioDTO.getIdUnidade() != null && !new Long(0L).equals(formularioDTO.getIdUnidade())) {
            formulario.setUnidade(new Unidade(formularioDTO.getIdUnidade()));
        } else if (formularioDTO.getIdForo() != null && !new Long(0L).equals(formularioDTO.getIdForo())) {
            Foro f = new Foro();
            f.setIdForo(formularioDTO.getIdForo());
            Unidade u = new Unidade();
            u.setForo(f);
            formulario.setUnidade(u);
        }
        formulario.setIdFormulario(formularioDTO.getIdFormulario());
        formulario.getFormulariosHistorico().add(parseFormularioDTOParaFormularioHistorico(formularioDTO, formulario));
        formulario.setMetadadosFormulario(parseFormularioDTOParaMetadadosFormulario(formularioDTO, novaVersao));
        formulario.setAno(formularioDTO.getAno());
        formulario.setMes(formularioDTO.getMes());
        for (SecaoDTO secaoDTO : formularioDTO.getListaSecoes()) {
            Secao secao = parseSecaoDTOParaSecao(secaoDTO, formulario, novaVersao);
            secao.setFormulario(formulario);
            formulario.getSecoes().add(secao);
        }
        //Consultar Geral
        if (formularioDTO.getNovaSituacaoFormulario() != null) {
            formulario.setTipoSituacao(parseTipoSituacaoFormularioDTOParaTipoSituacao(formularioDTO.getNovaSituacaoFormulario()));
        } else if (formularioDTO.getSituacaoFormularioDTO() != null) {
            formulario.setTipoSituacao(parseTipoSituacaoFormularioDTOParaTipoSituacao(formularioDTO.getSituacaoFormularioDTO()));
        } else {
            formulario.setTipoSituacao(new TipoSituacao(3L));
        }
       
        formulario.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
       
        formulario.setUsuarioAprovacao(parseUsuarioMagistrado(formularioDTO));
       
        formulario.setUsuarioPreenchimento(parseUsuarioPreenchimento(formularioDTO));
        
        if (formularioDTO.getDataConclusao() != null) {
            formulario.setDataFechamento(formularioDTO.getDataConclusao());
        }
        return formulario;
    }

    public static Usuario parseUsuarioMagistrado(FormularioDTO formularioDTO){
        if (formularioDTO.getIdMagistrado() != null && formularioDTO.getNomeMagistrado() != null) {
            return new Usuario(formularioDTO.getIdMagistrado(), formularioDTO.getNomeMagistrado());
        }else if(formularioDTO.getIdMagistrado() != null){
            return new Usuario(formularioDTO.getIdMagistrado());
        }else{
            return null;
        }
    }

    public static Usuario parseUsuarioPreenchimento(FormularioDTO formularioDTO) {
        if (formularioDTO.getIdUsuarioPreenchimento() != null && formularioDTO.getNomeUsuarioPreenchimento() != null) {
            return new Usuario(formularioDTO.getIdUsuarioPreenchimento(), formularioDTO.getNomeUsuarioPreenchimento());
        } else if (formularioDTO.getIdUsuarioPreenchimento() != null) {
            return new Usuario(formularioDTO.getIdUsuarioPreenchimento());
        } else {
            return null;
        }
    }

    public static Secao parseSecaoDTOParaSecao(SecaoDTO secaoDTO, Formulario formulario, boolean novaVersao) {
        Secao secao = new Secao();
        secao.setMetadadosSecao(parseSecaoDTOParaMetadadosSecao(secaoDTO, novaVersao));
        secao.setIdSecao(secaoDTO.getIdSecao());
        secao.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        for (SubSecaoDTO subSecaoDTO : secaoDTO.getListaSubSecoes()) {
            Secao subSecao = parseSubSecaoDTOParaSubSecao(subSecaoDTO, formulario, novaVersao);
            subSecao.setSecaoPai(secao);
            subSecao.setFormulario(formulario);
            subSecao.setMetadadosSecao(parseSecaoDTOParaMetadadosSecao(secaoDTO, novaVersao));
            secao.getSubSecoes().add(subSecao);
        }
        return secao;
    }

    public static Secao parseSubSecaoDTOParaSubSecao(SubSecaoDTO subSecaoDTO, Formulario formulario,
                                                     boolean novaVersao) {
        Secao subSecao = new Secao();
        subSecao.setIdSecao(subSecaoDTO.getIdSubSecao());
        subSecao.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        if (subSecaoDTO.getTipoMateria() != null)
            subSecao.setTipoMateria(parseTipoMateriaDTOParaTipoMateria(subSecaoDTO.getTipoMateria()));
        if (subSecaoDTO.getIdMagistrado() != null)
            subSecao.setMagistrado(new Usuario(subSecaoDTO.getIdMagistrado()));
        if (subSecaoDTO.getTipoJuiz() != null)
            subSecao.setTipoJuiz(subSecaoDTO.getTipoJuiz().getCodigoTipoJuiz());
        subSecao.setInspecoesEstabelecimentoPrisional(parseListaEstabelecimentoEntidadeDTOParaListaInspecaoEstabelecimentoPrisional(subSecaoDTO.getListaEstabelecimentosEntidade(),
                                                                                                                                    subSecao));
        //subSecao.setReusProvisorios(parseListaReuDTOParaListaReuProvisorio(subSecaoDTO.getListaReus(), subSecao, formulario));
        //subSecao.setProcessosConclusos(parseListaProcessosConclusosDTOParaListaProcessosConclusos(subSecaoDTO.getListaProcessosConclusos(), subSecao, formulario));
        subSecao.setFormularioForosOrigem(parseListaForoOrigemDTOParaListaFormularioForoOrigem(subSecaoDTO.getListaForosOrigem(),
                                                                                               subSecaoDTO));
        for (GrupoDTO grupoDTO : subSecaoDTO.getListaGrupos()) {
            Grupo grupoAdd = parseGrupoDTOParaGrupo(grupoDTO, novaVersao);
            grupoAdd.setMetadadosGrupo(parseGrupoDTOParaMetadadosGrupo(grupoDTO, novaVersao));
            grupoAdd.setSecao(subSecao);
            grupoAdd.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            subSecao.getGrupos().add(grupoAdd);
        }
        return subSecao;
    }

    public static List<FormularioForoOrigem> parseListaForoOrigemDTOParaListaFormularioForoOrigem(List<ForoOrigemDTO> listaForosOrigemDTO,
                                                                                                  SubSecaoDTO subSecaoDTO) {
        List<FormularioForoOrigem> listaFormulariosForoOrigem = new ArrayList<FormularioForoOrigem>();
        for (ForoOrigemDTO foroOrigemDTO : listaForosOrigemDTO) {
            listaFormulariosForoOrigem.add(parseForoOrigemDTOParaFormularioForoOrigem(foroOrigemDTO, subSecaoDTO));
        }
        return listaFormulariosForoOrigem;
    }

    public static FormularioForoOrigem parseForoOrigemDTOParaFormularioForoOrigem(ForoOrigemDTO foroOrigemDTO,
                                                                                  SubSecaoDTO subSecaoDTO) {
        FormularioForoOrigem formularioForoOrigem = new FormularioForoOrigem();
        if (foroOrigemDTO.getIdFormularioForoOrigem() != null)
            formularioForoOrigem.setIdFormularioForoOrigem(foroOrigemDTO.getIdFormularioForoOrigem());
        formularioForoOrigem.setSecao(new Secao(subSecaoDTO.getIdSubSecao()));
        formularioForoOrigem.setUnidade(new Unidade(foroOrigemDTO.getIdUnidade()));
        formularioForoOrigem.setForo(new Foro(foroOrigemDTO.getIdForo()));
        formularioForoOrigem.setValorCampo(foroOrigemDTO.getValorCampo());
        return formularioForoOrigem;
    }

    public static Grupo parseGrupoDTOParaGrupo(GrupoDTO grupoDTO, boolean novaVersao) {
        Grupo grupo = new Grupo();
        grupo.setIdGrupo(grupoDTO.getIdGrupo());
        for (CampoDTO campoDTO : grupoDTO.getListaCampos()) {
            Campo campoAdd = new Campo();
            campoAdd.setIdCampo(campoDTO.getIdCampo());
            campoAdd.setMetadadosCampo(parseCampoDTOParaMetadadosCampo(campoDTO, novaVersao));
            /* 20171009 - teste de correção de valor alterado indevidamente
            if (campoDTO.getTipoCampo().getValoTipoCampo().equals(TipoCampoType.FORMULA.getValoTipoCampo()) &&
                campoDTO.getFormula() != null) {
                campoAdd.setValorCampo(campoDTO.getFormula().getResultadoExpressao());
            } else {
                campoAdd.setValorCampo(campoDTO.getValorCampo());
            }
            */
            campoAdd.setValorCampo(campoDTO.getValorCampo());
            // /20171009 - teste de correção de valor alterado indevidamente
            campoAdd.setGrupo(grupo);
            campoAdd.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            for (CampoDTO subCampoDTO : campoDTO.getListaCampos()) {
                Campo subCampo = new Campo();
                subCampo = parseSubCampoDTOParaSubCampo(subCampoDTO, novaVersao);
                subCampo.setCampoPai(campoAdd);
                campoAdd.getCampos().add(subCampo);
            }
            grupo.getCampos().add(campoAdd);
        }
        return grupo;
    }

    public static Campo parseSubCampoDTOParaSubCampo(CampoDTO campoDTO, boolean novaVersao) {
        Campo subCampo = new Campo();
        subCampo.setIdCampo(campoDTO.getIdCampo());
        subCampo.setMetadadosCampo(parseCampoDTOParaMetadadosCampo(campoDTO, novaVersao));
        /* 20171009 - teste de correção de valor alterado indevidamente
        if (campoDTO.getTipoCampo().getValoTipoCampo().equals(TipoCampoType.FORMULA.getValoTipoCampo()) &&
            campoDTO.getFormula() != null) {
            subCampo.setValorCampo(campoDTO.getFormula().getResultadoExpressao());
        } else {
            subCampo.setValorCampo(campoDTO.getValorCampo());
        }
        */
        subCampo.setValorCampo(campoDTO.getValorCampo());
        // /20171009 - teste de correção de avalor alterado indevidamente
        subCampo.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        for (CampoDTO subCampoDTO : campoDTO.getListaCampos()) {
            Campo subCampoAdd = new Campo();
            subCampoAdd = parseSubCampoDTOParaSubCampo(subCampoDTO, novaVersao);
            subCampoAdd.setCampoPai(subCampo);
            subCampo.getCampos().add(subCampoAdd);
        }
        return subCampo;
    }

    public static List<ProcessoConcluso> parseListaProcessosConclusosDTOParaListaProcessosConclusos(List<ProcessoConclusoDTO> listaProcessoConclusoDTO) {
        List<ProcessoConcluso> listaProcessosConclusos = new ArrayList<ProcessoConcluso>();
        for (ProcessoConclusoDTO processoDTO : listaProcessoConclusoDTO) {
            listaProcessosConclusos.add(parseProcessoConclusoDTOParaProcessoConcluso(processoDTO));
        }
        return listaProcessosConclusos;
    }

    public static ProcessoConcluso parseProcessoConclusoDTOParaProcessoConcluso(ProcessoConclusoDTO processoConclusoDTO) {
        ProcessoConcluso processoConcluso = new ProcessoConcluso();
        processoConcluso.setAno(processoConclusoDTO.getAno());
        processoConcluso.setAnoProcesso(processoConclusoDTO.getAnoProcesso());
        processoConcluso.setDataConclusao(processoConclusoDTO.getDataConclusao());
        processoConcluso.setCodigoProcessoSaj(processoConclusoDTO.getCodigoProcessoSaj());
        processoConcluso.setDataBaixa(processoConclusoDTO.getDataBaixa());
        processoConcluso.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        processoConcluso.setIdBaseOrigemSaj(processoConclusoDTO.getIdBaseOrigemSaj());
        processoConcluso.setIdProcessoConcluso(processoConclusoDTO.getIdEntidadeProcessoConcluso());
        processoConcluso.setMes(processoConclusoDTO.getMes());
        processoConcluso.setSourceFormulario(processoConclusoDTO.getSrcFormulario());
        processoConcluso.setNumeroProcesso(processoConclusoDTO.getNumeroProcesso());
        if (processoConclusoDTO.getTipoConclusoDTO() != null)
            processoConcluso.setTipoConcluso(new TipoConcluso(processoConclusoDTO.getTipoConclusoDTO().getIdTipoProcessoConcluso()));
        if (processoConclusoDTO.getTipoFilaProcessoDTO() != null)
            processoConcluso.setTipoFilaProcesso(new TipoFilaProcesso(processoConclusoDTO.getTipoFilaProcessoDTO().getId()));
        processoConcluso.setUnidade(new Unidade(processoConclusoDTO.getIdUnidadeProcesso()));
        processoConcluso.setUsuario(new Usuario(processoConclusoDTO.getIdMagistradoProcesso()));
        
        // 03.05.2018
        processoConcluso.setDsAssunto(processoConclusoDTO.getDsAssunto());
        processoConcluso.setDsMovimentacao(processoConclusoDTO.getDsMovimentacao());
        processoConcluso.setFkCadUsuarioManutencao(processoConclusoDTO.getFkCadUsuarioManutencao());
        processoConcluso.setDtDesignacaoInicio(processoConclusoDTO.getDtDesignacaoInicio());
        processoConcluso.setDtDesignacaoFim(processoConclusoDTO.getDtDesignacaoFim());
        // FIM - 03.05.2018
        
        return processoConcluso;
    }

    public static List<TipoConclusoDTO> parseListaTipoConclusoParaListaProcessoConclusoDTO(List<TipoConcluso> listaTipoConcluso) {
        List<TipoConclusoDTO> listaProcessoConclusoDTO = new ArrayList<TipoConclusoDTO>();
        if (listaTipoConcluso != null) {
            for (TipoConcluso tipoConcluso : listaTipoConcluso) {
                if(tipoConcluso.getId() != -1) {
                    listaProcessoConclusoDTO.add(parseTipoConclusoParaProcessoConclusoDTO(tipoConcluso));
                }            
            }
        }

        return listaProcessoConclusoDTO;
    }

    public static TipoConclusoDTO parseTipoConclusoParaProcessoConclusoDTO(TipoConcluso tipoConcluso) {
        TipoConclusoDTO tipoConclusoDTO = new TipoConclusoDTO();
        tipoConclusoDTO.setIdTipoProcessoConcluso(tipoConcluso.getIdTipoConcluso());
        tipoConclusoDTO.setDescricaoTipoProcessoConcluso(tipoConcluso.getDescricaoTipoConcluso());
        tipoConclusoDTO.setTipoProcessoConcluso(ProcessoConclusoType.recuperarTipoProcessoConclusoPorCodigo(tipoConcluso.getCodigoTipoConcluso()));
        return tipoConclusoDTO;
    }
    
    public static TipoFilaProcessoDTO parseTipoFilaProcessoParaTipoFilaProcessoDTO(TipoFilaProcesso tipoFilaProcesso) {
        TipoFilaProcessoDTO tipoFilaProcessoDTO = new TipoFilaProcessoDTO();
        tipoFilaProcessoDTO.setDtInclusao(tipoFilaProcesso.getDtInclusao());
        tipoFilaProcessoDTO.setDtAtualizacao(tipoFilaProcesso.getDtAtualizacao());
        tipoFilaProcessoDTO.setDsTipoFilaProcesso(tipoFilaProcesso.getDsTipoFilaProcesso());
        tipoFilaProcessoDTO.setId(tipoFilaProcesso.getId());
        tipoFilaProcessoDTO.setTpSituacao(tipoFilaProcesso.getTpSituacao());
        return tipoFilaProcessoDTO;
    }

    public static List<ReuProvisorio> parseListaReuDTOParaListaReuProvisorio(List<ReuDTO> listaReuDTO) {
        List<ReuProvisorio> listaReusProvisorios = new ArrayList<ReuProvisorio>();
        for (ReuDTO reuDTO : listaReuDTO) {
            listaReusProvisorios.add(parseReuDTOParaReuProvisorio(reuDTO));
        }
        return listaReusProvisorios;
    }

    public static ReuProvisorio parseReuDTOParaReuProvisorio(ReuDTO reuDTO) {
        ReuProvisorio reuProvisorio = new ReuProvisorio();
        reuProvisorio.setIdReuProvisorio(reuDTO.getIdReuProvisorio());
        reuProvisorio.setCodigoPessoaSaj(reuDTO.getCodigoPessoaSaj());
        // <epr> (1) 0.7.9 - dataBaixa deve ser informado pelo usuário
        /*
        if (reuDTO.getIdMotivoBaixa() != null)
            reuProvisorio.setDataBaixa(new Date());
        */
        // </epr> (1) 0.7.9 - dataBaixa deve ser informado pelo usuário
        reuProvisorio.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        reuProvisorio.setIdBaseOrigemSaj(reuDTO.getIdBaseOrigemSaj());
        reuProvisorio.setNomeMaeReuProvisorio(reuDTO.getNomeMaeReuProvisorio());
        reuProvisorio.setNomeReuProvisorio(reuDTO.getNomeReuProvisorio());
        reuProvisorio.setSexo(reuDTO.getSexo());
        if (reuDTO.getIdUnidade() != null)
            reuProvisorio.setUnidade(new Unidade(reuDTO.getIdUnidade()));

        if (reuDTO.getIdNaturezaPrisao() != null) {
            reuProvisorio.getHistoricosReuProvisorio().add(new ReuProvisorioHistorico(reuDTO.getIdReuHistorico(),
                                                                                      new TipoNaturezaPrisao(reuDTO.getIdNaturezaPrisao()),
                                                                                      reuDTO.getMes(), reuDTO.getAno(),
                                                                                      reuDTO.getNumeroProcesso(),
                                                                                      reuDTO.getNumeroControle(),
                                                                                      reuDTO.getDataUltimoMovimento(),
                                                                                      reuDTO.getConteudoUltimoMovimento(),
                                                                                      ConstantesMovjud.FLAG_SITUACAO_ATIVA,
                                                                                      reuProvisorio, reuDTO));
        }
        return reuProvisorio;
    }

    public static List<InspecaoEstabelecimentoPrisional> parseListaEstabelecimentoEntidadeDTOParaListaInspecaoEstabelecimentoPrisional(List<EstabelecimentoEntidadeDTO> listaEstabelecimentosEntidadeDTO,
                                                                                                                                       Secao secao) {
        List<InspecaoEstabelecimentoPrisional> inspecoesEstabeleciemtnoPrisional =
            new ArrayList<InspecaoEstabelecimentoPrisional>();
        for (EstabelecimentoEntidadeDTO estabelecimentoEntidadeDTO : listaEstabelecimentosEntidadeDTO) {
            InspecaoEstabelecimentoPrisional inspecaoEstabelecimentoPrisional =
                parseEstabelecimentoEntidadeDTOParaInspecaoEstabelecimentoPrisional(estabelecimentoEntidadeDTO, secao);
            inspecoesEstabeleciemtnoPrisional.add(inspecaoEstabelecimentoPrisional);
        }
        return inspecoesEstabeleciemtnoPrisional;
    }

    public static InspecaoEstabelecimentoPrisional parseEstabelecimentoEntidadeDTOParaInspecaoEstabelecimentoPrisional(EstabelecimentoEntidadeDTO estabelecimentoEntidadeDTO,
                                                                                                                       Secao secao) {
        InspecaoEstabelecimentoPrisional inspecaoEstabelecimentoPrisional = new InspecaoEstabelecimentoPrisional();
        if (!estabelecimentoEntidadeDTO.isInspecaoNaoRealizada()) {
            if (estabelecimentoEntidadeDTO.getIdMagistrado() != null)
                inspecaoEstabelecimentoPrisional.setUsuario(new Usuario(estabelecimentoEntidadeDTO.getIdMagistrado()));
            inspecaoEstabelecimentoPrisional.setDataInspecao(estabelecimentoEntidadeDTO.getDataInspecao());
        } else {
            inspecaoEstabelecimentoPrisional.setDescricaoNaoInspecao(estabelecimentoEntidadeDTO.getMotivoInspecaoNaoRealizada());
        }
        inspecaoEstabelecimentoPrisional.setIdInspecaoEstabelecimento(estabelecimentoEntidadeDTO.getIdInspecaoEstabelecimento());
        inspecaoEstabelecimentoPrisional.setEstabelecimentoPrisional(new EstabelecimentoPrisional(estabelecimentoEntidadeDTO.getIdEstabelecimentoEntidade()));
        inspecaoEstabelecimentoPrisional.setFlagInspecaoNaoRealizada(estabelecimentoEntidadeDTO.isInspecaoNaoRealizada() ?
                                                                     ConstantesMovjud.FLAG_SITUACAO_SIM :
                                                                     ConstantesMovjud.FLAG_SITUACAO_NAO);
        inspecaoEstabelecimentoPrisional.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        inspecaoEstabelecimentoPrisional.setSecao(secao);
        return inspecaoEstabelecimentoPrisional;
    }

    public static MetadadosFormulario parseFormularioDTOParaMetadadosFormulario(FormularioDTO formularioDTO,
                                                                                boolean novaVersao) {
        MetadadosFormulario metadadosFormulario = new MetadadosFormulario();
        if (!novaVersao)
            metadadosFormulario.setIdMetadadosFormulario(formularioDTO.getIdMetadadosFormulario());
        metadadosFormulario.setMetadadosTipoSituacao(parseMetadadoSituacaoFormularioDTOParaMetadadosTipoSituacao(formularioDTO.getMetadadoSituacaoFormularioDTO()));
        metadadosFormulario.setTipoArea(parseAreaDTOParaTipoArea(formularioDTO.getArea()));
        metadadosFormulario.setTipoSegmento(parseSegmentoDTOParaTipoSegmento(formularioDTO.getSegmento()));
        metadadosFormulario.setTiposCompetencia(parseListaCompetenciaDTOParaListaTipoCompetencia(formularioDTO.getListaCompetencias()));
        metadadosFormulario.setDataFiltroCriacao(formularioDTO.getDataCriacao());
        metadadosFormulario.setDataInclusao(formularioDTO.getDataInclusao());
        if (formularioDTO.getSituacao() == null || formularioDTO.getSituacao().isEmpty()) {
            metadadosFormulario.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        } else {
            metadadosFormulario.setFlagTipoSituacao(formularioDTO.getSituacao());
        }
        metadadosFormulario.setDescricaoTextoInformativo(formularioDTO.getAviso());
        metadadosFormulario.setDescricaoNome(formularioDTO.getNomeFormulario());
        metadadosFormulario.setDescricaoSourceFormulario(formularioDTO.getCodigoFormulario());
        if (formularioDTO.getInstancia() != null && !formularioDTO.getInstancia().isEmpty()) {
            metadadosFormulario.setNumeroInstancia(Long.valueOf(formularioDTO.getInstancia()));
        } else {
            metadadosFormulario.setNumeroInstancia(1L);
        }
        if (formularioDTO.getVersao() != null) {
            metadadosFormulario.setNumeroVersao(formularioDTO.getVersao());
        }
        metadadosFormulario.setMetadadosSecoes(new ArrayList<MetadadosSecao>());
        for (SecaoDTO secaoDTO : formularioDTO.getListaSecoes()) {
            MetadadosSecao secaoAdd = parseSecaoDTOParaMetadadosSecao(secaoDTO, novaVersao);
            secaoAdd.setMetadadosFormulario(metadadosFormulario);
            metadadosFormulario.getMetadadosSecoes().add(secaoAdd);
        }

        return metadadosFormulario;
    }

    public static MetadadosSecao parseSecaoDTOParaMetadadosSecao(SecaoDTO secaoDTO, boolean novaVersao) {
        MetadadosSecao metadadosSecao = new MetadadosSecao();
        if (!novaVersao)
            metadadosSecao.setIdMetadadosSessao(secaoDTO.getIdMetadadosSecao());
        metadadosSecao.setCodigoSigla(secaoDTO.getCodigoSecao());
        metadadosSecao.setDescricaoNome(secaoDTO.getLabelSecao());
        metadadosSecao.setDescricaoTextoInformativo(secaoDTO.getTextoInformativo());
        metadadosSecao.setDataInclusao(secaoDTO.getDataInclusao());
        metadadosSecao.setFlagConclusoPara((secaoDTO.isConclusos() ? ConstantesMovjud.FLAG_SITUACAO_SIM :
                                            ConstantesMovjud.FLAG_SITUACAO_NAO));
        metadadosSecao.setFlagExibeTotais((secaoDTO.isTotalizadores() ? ConstantesMovjud.FLAG_SITUACAO_SIM :
                                           ConstantesMovjud.FLAG_SITUACAO_NAO));
        metadadosSecao.setFlagInternacao((secaoDTO.isTipoInternacao() ? ConstantesMovjud.FLAG_SITUACAO_SIM :
                                          ConstantesMovjud.FLAG_SITUACAO_NAO));
        metadadosSecao.setFlagPrisional((secaoDTO.isTipoPrisional() ? ConstantesMovjud.FLAG_SITUACAO_SIM :
                                         ConstantesMovjud.FLAG_SITUACAO_NAO));
        metadadosSecao.setFlagTemProcesso((secaoDTO.isTabelaProcessos() ? ConstantesMovjud.FLAG_SITUACAO_SIM :
                                           ConstantesMovjud.FLAG_SITUACAO_NAO));
        metadadosSecao.setDescricaoTextoInformativoSecaoMagistrado(secaoDTO.getInformativoMagistrado());
        metadadosSecao.setDescricaoLabelSecaoMagistrado(secaoDTO.getLabelMagistrado());
        metadadosSecao.setIndiceSecaoMagistrado(secaoDTO.getCodigoMagistrado());
        if (secaoDTO.getSituacao() == null || secaoDTO.getSituacao().isEmpty()) {
            metadadosSecao.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        } else {
            metadadosSecao.setFlagTipoSituacao(secaoDTO.getSituacao());
        }
        metadadosSecao.setNumeroOrdem(secaoDTO.getOrdemSecao());
        metadadosSecao.setMetadadosGrupos(new ArrayList<MetadadosGrupo>());
        metadadosSecao.setTiposMateria(parseListaTipoMateriaDTOParaListaTipoMateria(secaoDTO.getListaMaterias(),
                                                                                    novaVersao));
        Long i = 0L;
        for (GrupoDTO grupoDTO : secaoDTO.getListaGrupos()) {
            MetadadosGrupo grupoAdd = parseGrupoDTOParaMetadadosGrupo(grupoDTO, novaVersao);
            grupoAdd.setMetadadosSecao(metadadosSecao);
            grupoAdd.setNumeroOrdem(i);
            metadadosSecao.getMetadadosGrupos().add(grupoAdd);
            i++;
        }
        return metadadosSecao;
    }

    public static List<TipoMateria> parseListaTipoMateriaDTOParaListaTipoMateria(List<TipoMateriaDTO> listaTipoMateriaDTO,
                                                                                 boolean novaVersao) {
        List<TipoMateria> listaTipoMateria = null;
        if (listaTipoMateriaDTO != null) {
            listaTipoMateria = new ArrayList<TipoMateria>();
            for (TipoMateriaDTO tipoMateriaDTO : listaTipoMateriaDTO) {
                listaTipoMateria.add(parseTipoMateriaDTOParaTipoMateria(tipoMateriaDTO));
            }
        }
        return listaTipoMateria;
    }

    public static TipoMateria parseTipoMateriaDTOParaTipoMateria(TipoMateriaDTO tipoMateriaDTO) {
        TipoMateria tipoMateria = new TipoMateria();
        tipoMateria.setIdTipoMateria(tipoMateriaDTO.getCodigoTipoMateria());
        tipoMateria.setDescricaoMateria(tipoMateriaDTO.getNomeTipoMateria());
        tipoMateria.setDataInclusao(tipoMateriaDTO.getDataInclusao());
        tipoMateria.setFlagTipoSituacao(tipoMateriaDTO.getSituacao());
        return tipoMateria;
    }

    public static MetadadosGrupo parseGrupoDTOParaMetadadosGrupo(GrupoDTO grupoDTO, boolean novaVersao) {
        MetadadosGrupo metadadosGrupo = new MetadadosGrupo();
        if (!novaVersao)
            metadadosGrupo.setIdMetadadosGrupo(grupoDTO.getIdMetadadosGrupo());
        metadadosGrupo.setCodigoDominioBI(grupoDTO.getDominioBI());
        metadadosGrupo.setCodigoSigla(grupoDTO.getCodigoGrupo());
        metadadosGrupo.setDescricaoNome(grupoDTO.getLabelGrupo());
        metadadosGrupo.setDescricaoTextoInformativo(grupoDTO.getTextoInformativo());
        metadadosGrupo.setMetadadosTipoRegra(parseTipoRegraDTOParaMetadadosTipoRegra(grupoDTO.getTipoRegraDTO()));
        if (grupoDTO.getTipoRegraDTO() != null)
            metadadosGrupo.setFlagInverterTipoRegra((grupoDTO.getTipoRegraDTO().isInverterRegra() ?
                                                     ConstantesMovjud.FLAG_SITUACAO_SIM :
                                                     ConstantesMovjud.FLAG_SITUACAO_NAO));
        metadadosGrupo.setDataInclusao(grupoDTO.getDataInclusao());
        if (grupoDTO.getSituacao() == null || grupoDTO.getSituacao().isEmpty()) {
            metadadosGrupo.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        } else {
            metadadosGrupo.setFlagTipoSituacao(grupoDTO.getSituacao());
        }
        metadadosGrupo.setNumeroOrdem(grupoDTO.getOrdemGrupo());
        metadadosGrupo.setMetadadosGruposCampo(new ArrayList<MetadadosGrupoCampo>());
        Long i = 0L;
        for (CampoDTO campoDTO : grupoDTO.getListaCampos()) {
            MetadadosGrupoCampo grupoCampoAdd = parseCampoDTOParaMetadadosGrupoCampo(campoDTO, novaVersao);
            grupoCampoAdd.setMetadadosGrupo(metadadosGrupo);
            grupoCampoAdd.setNumeroOrdem(i);
            metadadosGrupo.getMetadadosGruposCampo().add(grupoCampoAdd);
            i++;
        }
        return metadadosGrupo;
    }

    public static MetadadosGrupoCampo parseCampoDTOParaMetadadosGrupoCampo(CampoDTO campoDTO, boolean novaVersao) {
        MetadadosGrupoCampo metadadosGrupoCampo = new MetadadosGrupoCampo();
        if (!novaVersao)
            metadadosGrupoCampo.setIdMetadadosGrupoCampo(campoDTO.getIdMetadadosGrupoCampo());
        MetadadosCampo metadadosCampo = parseCampoDTOParaMetadadosCampo(campoDTO, novaVersao);
        if (campoDTO.getSituacao() == null || campoDTO.getSituacao().isEmpty()) {
            metadadosGrupoCampo.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        } else {
            metadadosGrupoCampo.setFlagTipoSituacao(campoDTO.getSituacao());
        }
        metadadosGrupoCampo.setDataInclusao(campoDTO.getDataInclusao());
        if (campoDTO.getFormula() != null)
            metadadosGrupoCampo.setDescricaoFormula(campoDTO.getFormula().getExpressao());
        metadadosGrupoCampo.setDescricaoSequencia(campoDTO.getIndiceCampo());
        metadadosGrupoCampo.setMetadadosCampo(metadadosCampo);
        metadadosGrupoCampo.setCodigoSigla(campoDTO.getCodigoCampo());
        return metadadosGrupoCampo;
    }

    public static MetadadosCampo parseCampoDTOParaMetadadosCampo(CampoDTO campoDTO, boolean novaVersao) {
        MetadadosCampo metadadosCampo = setCampoDTOParaMetadadosCampo(campoDTO, novaVersao);
        if (campoDTO.getListaCampos() != null && campoDTO.getListaCampos().size() > 0) {
            Long i = 0L;
            metadadosCampo.setMetadadosCamposFilho(new ArrayList<MetadadosCampoCampo>());
            for (CampoDTO subcampo : campoDTO.getListaCampos()) {
                MetadadosCampo MetadadosSubcampo = setCampoDTOParaMetadadosCampo(subcampo, novaVersao);
                MetadadosCampoCampo metadadosCampoCampo =
                    parseCampoDTOParaEntidadeRelacionamentoSubcampos(MetadadosSubcampo, metadadosCampo, subcampo,
                                                                     novaVersao);
                metadadosCampoCampo.setNumeroOrdem(i);
                metadadosCampoCampo.setDescricaoSequencia(subcampo.getIndiceCampo());
                metadadosCampoCampo.setCodigoSigla(subcampo.getCodigoCampo());
                if (subcampo.getFormula() != null)
                    metadadosCampoCampo.setDescricaoFormula(subcampo.getFormula().getExpressao());
                if (campoDTO.getSituacao() == null || campoDTO.getSituacao().isEmpty()) {
                    metadadosCampoCampo.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
                } else {
                    metadadosCampoCampo.setFlagTipoSituacao(campoDTO.getSituacao());
                }
                if (subcampo.getDataInclusao() == null) {
                    metadadosCampoCampo.setDataInclusao(new Date());
                } else {
                    metadadosCampoCampo.setDataInclusao(subcampo.getDataInclusao());
                }
                metadadosCampo.getMetadadosCamposFilho().add(metadadosCampoCampo);
                i++;
            }
        }
        return metadadosCampo;
    }

    public static MetadadosCampoCampo parseCampoDTOParaEntidadeRelacionamentoSubcampos(MetadadosCampo metadadosCampoFilho,
                                                                                       MetadadosCampo metadadosCampoPai,
                                                                                       CampoDTO campoFilhoDto,
                                                                                       boolean novaVersao) {
        MetadadosCampoCampo metadadosCampoCampo = null;
        if (campoFilhoDto != null && campoFilhoDto.getListaCampos() != null &&
            campoFilhoDto.getListaCampos().size() > 0) {
            Long i = 0L;
            metadadosCampoFilho.setMetadadosCamposFilho(new ArrayList<MetadadosCampoCampo>());
            for (CampoDTO subcampo : campoFilhoDto.getListaCampos()) {
                MetadadosCampo MetadadosSubcampo = setCampoDTOParaMetadadosCampo(subcampo, novaVersao);
                metadadosCampoCampo =
                    parseCampoDTOParaEntidadeRelacionamentoSubcampos(MetadadosSubcampo, metadadosCampoFilho, subcampo,
                                                                     novaVersao);
                metadadosCampoCampo.setNumeroOrdem(i);
                metadadosCampoCampo.setDescricaoSequencia(subcampo.getIndiceCampo());
                metadadosCampoCampo.setCodigoSigla(subcampo.getCodigoCampo());
                if (subcampo.getFormula() != null)
                    metadadosCampoCampo.setDescricaoFormula(subcampo.getFormula().getExpressao());
                if (subcampo.getSituacao() == null || subcampo.getSituacao().isEmpty()) {
                    metadadosCampoCampo.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
                } else {
                    metadadosCampoCampo.setFlagTipoSituacao(subcampo.getSituacao());
                }
                if (subcampo.getDataInclusao() == null) {
                    metadadosCampoCampo.setDataInclusao(new Date());
                } else {
                    metadadosCampoCampo.setDataInclusao(subcampo.getDataInclusao());
                }
                metadadosCampoFilho.getMetadadosCamposFilho().add(metadadosCampoCampo);
                i++;
            }
        }
        metadadosCampoFilho.setMetadadosCamposPai(new ArrayList<MetadadosCampoCampo>());
        metadadosCampoCampo = new MetadadosCampoCampo();
        metadadosCampoCampo.setMetadadosCampoPai(metadadosCampoPai);
        metadadosCampoCampo.setMetadadosCampoFilho(metadadosCampoFilho);
        if (!novaVersao)
            metadadosCampoCampo.setIdCampoCampo(campoFilhoDto.getIdMetadadosCampoCampo());
        metadadosCampoFilho.getMetadadosCamposPai().add(metadadosCampoCampo);
        return metadadosCampoCampo;
    }

    public static MetadadosCampo setCampoDTOParaMetadadosCampo(CampoDTO campoDTO, boolean novaVersao) {
        MetadadosCampo metadadosCampo = new MetadadosCampo();
        if (!novaVersao)
            metadadosCampo.setIdMetadadosCampo(campoDTO.getIdMetadadosCampo());
        metadadosCampo.setDescricaoTextoInformativo(campoDTO.getHint());
        metadadosCampo.setNomeCampo(campoDTO.getLabelCampo());
        if (campoDTO.getSituacao() == null || campoDTO.getSituacao().isEmpty()) {
            metadadosCampo.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        } else {
            metadadosCampo.setFlagTipoSituacao(campoDTO.getSituacao());
        }
        if (campoDTO.getDataInclusao() == null) {
            metadadosCampo.setDataInclusao(new Date());
        } else {
            metadadosCampo.setDataInclusao(campoDTO.getDataInclusao());
        }
        metadadosCampo.setMetadadosTipoRegra(parseTipoRegraDTOParaMetadadosTipoRegra(campoDTO.getTipoRegraDTO()));
        metadadosCampo.setCodigoDominioBI(campoDTO.getCodigoBI());
        if (campoDTO.getTipoCampo() != null)
            metadadosCampo.setTipoCampo(campoDTO.getTipoCampo().getValoTipoCampo());
        metadadosCampo.setMetadadosValidacaoCampo(parseListaMetadadosValidacaoCampoParaListaValidacaoDTO(campoDTO.getListaValidacoes(),
                                                                                                         metadadosCampo,
                                                                                                         novaVersao));
        metadadosCampo.setFlagObrigatorio(ConstantesMovjud.FLAG_SITUACAO_SIM);
        if (campoDTO.getTipoRegraDTO() != null)
            metadadosCampo.setFlagInverterTipoRegra((campoDTO.getTipoRegraDTO().isInverterRegra() ?
                                                     ConstantesMovjud.FLAG_SITUACAO_SIM :
                                                     ConstantesMovjud.FLAG_SITUACAO_NAO));
        metadadosCampo.setNumeroMinimoCaracteres(campoDTO.getNumeroMinimoCaracteres());
        metadadosCampo.setNumeroMaximoCaracteres(campoDTO.getNumeroMaximoCaracteres());
        metadadosCampo.setNumeroCasasDecimais(campoDTO.getCasasDecimais());
        metadadosCampo.setMetadadosListaSelecao(parseListaSelecaoParaMetadadosListaSelecao(campoDTO.getListaItensSelecaoDTO(),
                                                                                           metadadosCampo, novaVersao));
        if (campoDTO.getTipoCampo() != null)
            metadadosCampo.setTipoCampo(campoDTO.getTipoCampo().getValoTipoCampo());
        return metadadosCampo;
    }

    public static List<MetadadosValidacaoCampo> parseListaMetadadosValidacaoCampoParaListaValidacaoDTO(List<ValidacaoDTO> listaValidacaoDTO,
                                                                                                       MetadadosCampo metadadosCampo,
                                                                                                       boolean novaVersao) {
        List<MetadadosValidacaoCampo> listaMetadadosValidacaoCampo = null;
        if (listaValidacaoDTO != null) {
            listaMetadadosValidacaoCampo = new ArrayList<MetadadosValidacaoCampo>();
            for (ValidacaoDTO validacaoDTO : listaValidacaoDTO) {
                listaMetadadosValidacaoCampo.add(parseMetadadosValidacaoCampoParaValidacaoDTO(validacaoDTO,
                                                                                              metadadosCampo,
                                                                                              novaVersao));
            }
        }
        return listaMetadadosValidacaoCampo;
    }

    public static MetadadosValidacaoCampo parseMetadadosValidacaoCampoParaValidacaoDTO(ValidacaoDTO validacaoDTO,
                                                                                       MetadadosCampo metadadosCampo,
                                                                                       boolean novaVersao) {
        MetadadosValidacaoCampo metadadosValidacaoCampo = new MetadadosValidacaoCampo();
        if (!novaVersao)
            metadadosValidacaoCampo.setIdMetadadosValidacaoCampo(validacaoDTO.getCodigoValidacao());
        metadadosValidacaoCampo.setDescricaoFormula(validacaoDTO.getFormula().getExpressao());
        metadadosValidacaoCampo.setFlagTipoValidacao(validacaoDTO.getTipoValidacao().getCodigoValidacao());
        if (validacaoDTO.getSituacao() == null || validacaoDTO.getSituacao().isEmpty()) {
            metadadosValidacaoCampo.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        } else {
            metadadosValidacaoCampo.setFlagTipoSituacao(validacaoDTO.getSituacao());
        }
        metadadosValidacaoCampo.setDescricaoMensagem(validacaoDTO.getMensagem());
        metadadosValidacaoCampo.setDataInclusao(validacaoDTO.getDataInclusao());
        metadadosValidacaoCampo.setMetadadosCampo(metadadosCampo);
        return metadadosValidacaoCampo;
    }

    public static List<MetadadosListaSelecao> parseListaSelecaoParaMetadadosListaSelecao(List<ItemSelecaoDTO> listaSelecao,
                                                                                         MetadadosCampo metadadosCampo,
                                                                                         boolean novaVersao) {
        List<MetadadosListaSelecao> metadadosListaSelecao = new ArrayList<MetadadosListaSelecao>();
        for (ItemSelecaoDTO itemSelecaoDTO : listaSelecao) {
            MetadadosListaSelecao metadadosItemSelecao = new MetadadosListaSelecao();
            metadadosItemSelecao.setDescricaoSelecao(itemSelecaoDTO.getLabelItemSelecao());
            metadadosItemSelecao.setMetadadosCampo(metadadosCampo);
            if (itemSelecaoDTO.getSituacao() == null || itemSelecaoDTO.getSituacao().isEmpty()) {
                metadadosItemSelecao.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            } else {
                metadadosItemSelecao.setFlagTipoSituacao(itemSelecaoDTO.getSituacao());
            }
            metadadosItemSelecao.setDataInclusao(itemSelecaoDTO.getDataInclusao());
            if (!novaVersao && itemSelecaoDTO.getCodigoItemSelecao() != null)
                metadadosItemSelecao.setIdMetadadosListaSelecao(Long.valueOf(itemSelecaoDTO.getCodigoItemSelecao()));
            metadadosListaSelecao.add(metadadosItemSelecao);
        }
        return metadadosListaSelecao;
    }

    public static List<ItemSelecaoDTO> parseListaMetadadosListaItemSelecaoParaListaItemSelecaoDTO(List<MetadadosListaSelecao> listaMetadadosItemSelecao) {
        List<ItemSelecaoDTO> listaItemSelecaoDTO = new ArrayList<ItemSelecaoDTO>();
        for (MetadadosListaSelecao metadadosListaSelecao : listaMetadadosItemSelecao) {
            listaItemSelecaoDTO.add(parseListaMetadadosItemSelecaoParaItemSelecaoDTO(metadadosListaSelecao));
        }
        return listaItemSelecaoDTO;
    }

    public static ItemSelecaoDTO parseListaMetadadosItemSelecaoParaItemSelecaoDTO(MetadadosListaSelecao metadadosItemSelecao) {
        ItemSelecaoDTO itemSelecaoDTO = new ItemSelecaoDTO();
        itemSelecaoDTO.setCodigoItemSelecao(Long.toString(metadadosItemSelecao.getIdMetadadosListaSelecao()));
        itemSelecaoDTO.setLabelItemSelecao(metadadosItemSelecao.getDescricaoSelecao());
        if (itemSelecaoDTO.getSituacao() == null || itemSelecaoDTO.getSituacao().isEmpty()) {
            metadadosItemSelecao.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        } else {
            metadadosItemSelecao.setFlagTipoSituacao(itemSelecaoDTO.getSituacao());
        }
        itemSelecaoDTO.setDataInclusao(metadadosItemSelecao.getDataInclusao());
        itemSelecaoDTO.setIdEntidadeCampo(metadadosItemSelecao.getMetadadosCampo().getIdMetadadosCampo());
        return itemSelecaoDTO;
    }

    public static MetadadosTipoRegra parseTipoRegraDTOParaMetadadosTipoRegra(TipoRegraDTO tipoRegraDTO) {
        MetadadosTipoRegra metadadosTipoRegra = null;
        if (tipoRegraDTO != null) {
            metadadosTipoRegra = new MetadadosTipoRegra();
            metadadosTipoRegra.setIdMetadadosTipoRegra(tipoRegraDTO.getCodigoTipoRegra());
            metadadosTipoRegra.setDescricaoNome(tipoRegraDTO.getNomeTipoRegra());
            metadadosTipoRegra.setDescricaoTipoRegra(tipoRegraDTO.getDescricaoTipoRegra());
            if (tipoRegraDTO.getSituacao() == null || tipoRegraDTO.getSituacao().isEmpty()) {
                metadadosTipoRegra.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            } else {
                metadadosTipoRegra.setFlagTipoSituacao(tipoRegraDTO.getSituacao());
            }
            metadadosTipoRegra.setDataInclusao(tipoRegraDTO.getDataInclusao());
        }
        return metadadosTipoRegra;
    }
}
