package br.jus.tjsp.movjud.business.configuracao.service;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.DiaSemanaType;
import br.jus.tjsp.movjud.persistence.base.types.PeriodicidadeType;
import br.jus.tjsp.movjud.persistence.configuracao.dao.AvisoDAO;
import br.jus.tjsp.movjud.persistence.configuracao.dao.AvisoEstruturaDAO;
import br.jus.tjsp.movjud.persistence.configuracao.dao.ConfiguracaoAvisoDAO;
import br.jus.tjsp.movjud.persistence.configuracao.dao.TipoAbrangenciaAvisoDAO;
import br.jus.tjsp.movjud.persistence.configuracao.dao.TipoAvisoDAO;
import br.jus.tjsp.movjud.persistence.configuracao.dao.TipoPeriodicidadeDAO;
import br.jus.tjsp.movjud.persistence.entity.Aviso;
import br.jus.tjsp.movjud.persistence.entity.AvisoEstrutura;
import br.jus.tjsp.movjud.persistence.entity.ConfiguracaoAviso;
import br.jus.tjsp.movjud.persistence.entity.TipoAbrangenciaAviso;
import br.jus.tjsp.movjud.persistence.entity.TipoAviso;
import br.jus.tjsp.movjud.persistence.entity.TipoPeriodicidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.UsuarioDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

@Stateless(name = "ConfiguracaoService", mappedName = "MovJudModel")
public class ConfiguracaoServiceImpl implements ConfiguracaoService {

    private final static Logger logger = Logger.getLogger(ConfiguracaoServiceImpl.class);

    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static int DIA_PREENCHIMENTO = 10;

    @EJB
    private AvisoDAO avisoDAO;

    @EJB
    private AvisoEstruturaDAO avisoEstruturaDAO;

    @EJB
    private ConfiguracaoAvisoDAO configuracaoAvisoDAO;

    @EJB
    private TipoAvisoDAO tipoAvisoDAO;

    @EJB
    private TipoPeriodicidadeDAO tipoPeriodicidadeDAO;

    @EJB
    private TipoAbrangenciaAvisoDAO tipoAbrangenciaAvisoDAO;

    @EJB
    private UsuarioDAO usuarioDAO;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ConfiguracaoAviso> listarConfiguracaoAvisoComFiltroPaginacao(ConfiguracaoAviso configuracaoAviso,
                                                                             Paginacao paginacao) {
        return configuracaoAvisoDAO.listarComFiltro(configuracaoAviso, paginacao);
    }

    @Override
    public ConfiguracaoAviso salvarConfiguracaoAviso(ConfiguracaoAviso configuracaoAviso) {
        return configuracaoAvisoDAO.salvar(configuracaoAviso);
    }

    @Override
    public void excluirConfiguracaoAviso(ConfiguracaoAviso configuracaoAviso) {
        configuracaoAvisoDAO.excluir(configuracaoAviso);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AvisoEstrutura> listarAvisoEstruturaComFiltroPaginacao(AvisoEstrutura avisoEstrutura,
                                                                       Paginacao paginacao) {
        return avisoEstruturaDAO.listarComFiltro(avisoEstrutura, paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AvisoEstrutura> listarAvisoEstruturaOrdenadoPorNome() {
        return avisoEstruturaDAO.listarAvisoEstruturaOrdenadoPorNome();
    }

    @Override
    public AvisoEstrutura salvarAvisoEstrutura(AvisoEstrutura avisoEstrutura) {
        return avisoEstruturaDAO.salvar(avisoEstrutura);
    }

    @Override
    public void excluirAvisoEstrutura(AvisoEstrutura avisoEstrutura) {
        avisoEstruturaDAO.excluir(avisoEstrutura);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<TipoAviso> listarTiposAviso() {
        return tipoAvisoDAO.listar();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<TipoPeriodicidade> listarTiposPeriodicidade() {
        return tipoPeriodicidadeDAO.listar();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<TipoAbrangenciaAviso> listarTiposAbrangenciaAviso() {
        return tipoAbrangenciaAvisoDAO.listar();
    }

    @Override
    public Aviso salvarAviso(Aviso aviso) {
        return avisoDAO.salvar(aviso);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Aviso> carregarAvisosUsuario(Usuario usuario, Paginacao paginacao) throws ParseException {
        // CARREGAR OS AVISOS DO USUARIO EXISTENTES
        List<Aviso> avisosExistentesUsuario = avisoDAO.listarAvisosExistentesDoUsuario(usuario);

        // CARREGAR AS CONFIGURACOES QUE SE APLICAO AO USUARIO
        List<ConfiguracaoAviso> configuracoesAvisoUsuario =
            configuracaoAvisoDAO.listarConfiguracoesAvisoDoUsuario(usuario);

        // PARA CADA CONFIGURACAO RETORNADA, CRIAR OBJETOS POPULADOS COM AVISO
        List<Aviso> avisosNovos = new ArrayList<Aviso>();
        if (configuracoesAvisoUsuario != null) {
            for (ConfiguracaoAviso configuracaoAviso :
                 configuracoesAvisoUsuario) {
                // CRIAR UM OBJETO DE AVISO
                List<Aviso> avisosNovosAtual =
                                       getAvisosNovosDaConfiguracao(avisosExistentesUsuario, configuracaoAviso,
                                                                    usuario);
                avisosNovos.addAll(avisosNovosAtual);
            }
        }

        // SALVAR OS AVISOS NOVOS
        if (!avisosNovos.isEmpty()) {
            avisosExistentesUsuario.addAll(avisosNovos);
            usuario.setAvisos(avisosExistentesUsuario);
            usuarioDAO.salvar(usuario);
        }

        // RETORNAR
        avisosExistentesUsuario = avisoDAO.listarAvisosExistentesDoUsuario(usuario, paginacao);
        return avisosExistentesUsuario;
    }

    private List<Aviso> getAvisosNovosDaConfiguracao(List<Aviso> avisosExistentesUsuario,
                                                     ConfiguracaoAviso configuracaoAviso,
                                                     Usuario usuario) throws ParseException {
        // VARIAVEIS
        Calendar initialDate = Calendar.getInstance();
        initialDate.setTime(simpleDateFormat.parse(simpleDateFormat.format(configuracaoAviso.getDataInclusao())));
        Calendar finalDate = Calendar.getInstance();
        finalDate.setTime(simpleDateFormat.parse(simpleDateFormat.format(Calendar.getInstance().getTime())));
        List<Aviso> avisosNovos = new ArrayList<Aviso>();

        // RECUPERA OS NOVOS AVISOS UMA VEZ
        if (configuracaoAviso.getTipoPeriodicidade().getCodigoPeriodicidade().equals(PeriodicidadeType.UMA_VEZ.getCodigo())) {
            avisosNovos = getAvisosNovosUmaVez(avisosExistentesUsuario, configuracaoAviso, usuario, initialDate);

            // RECUPERA OS NOVOS AVISOS DIARIOS
        } else if (configuracaoAviso.getTipoPeriodicidade().getCodigoPeriodicidade().equals(PeriodicidadeType.DIARIO.getCodigo())) {
            avisosNovos =
                getAvisosNovosDiario(avisosExistentesUsuario, configuracaoAviso, usuario, initialDate, finalDate);

            // RECUPERA OS NOVOS AVISOS SEMANAL
        } else if (configuracaoAviso.getTipoPeriodicidade().getCodigoPeriodicidade().equals(PeriodicidadeType.SEMANAL.getCodigo())) {
            avisosNovos =
                getAvisosNovosSemanal(avisosExistentesUsuario, configuracaoAviso, usuario, initialDate, finalDate);

            // RECUPERA OS NOVOS AVISOS DIAS ANTES ACAO
        } else if (configuracaoAviso.getTipoPeriodicidade().getCodigoPeriodicidade().equals(PeriodicidadeType.DIAS_ANTES_ACAO.getCodigo())) {
            avisosNovos =
                getAvisosNovosDiasAntesAcao(avisosExistentesUsuario, configuracaoAviso, usuario, initialDate,
                                            finalDate);

            // RECUPERA OS NOVOS AVISOS DATA ESPECIFICA
        } else if (configuracaoAviso.getTipoPeriodicidade().getCodigoPeriodicidade().equals(PeriodicidadeType.DATA_ESPECIFICA.getCodigo())) {
            avisosNovos =
                getAvisosNovosDataEspecifica(avisosExistentesUsuario, configuracaoAviso, usuario, initialDate,
                                             finalDate);
        }
        return avisosNovos;
    }

    private Aviso getAvisoNovo(ConfiguracaoAviso configuracaoAviso, Usuario usuario, Calendar dataEnvio) {
        // CRIA UM OBJETO NOVO DE AVISO
        Aviso avisoNovo = new Aviso();
        avisoNovo.setConfiguracaoAviso(configuracaoAviso);
        avisoNovo.setUsuario(usuario);
        avisoNovo.setDataEnvio(dataEnvio.getTime());
        avisoNovo.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        avisoNovo.setFlagExcluido(ConstantesMovjud.FLAG_SITUACAO_NAO);
        avisoNovo.setFlagLido(ConstantesMovjud.FLAG_SITUACAO_NAO);
        return avisoNovo;
    }

    private List<Aviso> getAvisosNovosUmaVez(List<Aviso> avisosExistentesUsuario, ConfiguracaoAviso configuracaoAviso,
                                             Usuario usuario, Calendar initialDate) {
        // RECUPERA O AVISO NOVO UMA VEZ
        List<Aviso> avisosNovos = new ArrayList<Aviso>();
        try {
            Aviso avisoNovo = getAvisoNovo(configuracaoAviso, usuario, initialDate);
            if (!avisosExistentesUsuario.contains(avisoNovo)) {
                avisosNovos.add(avisoNovo);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return avisosNovos;
    }

    private List<Aviso> getAvisosNovosDiario(List<Aviso> avisosExistentesUsuario, ConfiguracaoAviso configuracaoAviso,
                                             Usuario usuario, Calendar initialDate, Calendar finalDate) {
        // RECUPERA OS AVISOS NOVOS DIARIOS
        List<Aviso> avisosNovos = new ArrayList<Aviso>();
        try {
            for (; !initialDate.after(finalDate); initialDate.add(Calendar.DATE, 1)) {
                Aviso avisoNovo = getAvisoNovo(configuracaoAviso, usuario, initialDate);
                if (!avisosExistentesUsuario.contains(avisoNovo)) {
                    avisosNovos.add(avisoNovo);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return avisosNovos;
    }

    private List<Aviso> getAvisosNovosSemanal(List<Aviso> avisosExistentesUsuario, ConfiguracaoAviso configuracaoAviso,
                                              Usuario usuario, Calendar initialDate, Calendar finalDate) {
        // RECUPERA OS AVISOS NOVOS SEMANAIS
        List<Aviso> avisosNovos = new ArrayList<Aviso>();
        if (configuracaoAviso.getValorPeriodicidade() != null) {
            try {
                DiaSemanaType diaSemanaConfiguracao =
                    DiaSemanaType.valueOf(configuracaoAviso.getValorPeriodicidade().trim());
                while (initialDate.get(Calendar.DAY_OF_WEEK) != diaSemanaConfiguracao.getSequencia()) {
                    initialDate.add(Calendar.DATE, 1);
                }
                for (; !initialDate.after(finalDate); initialDate.add(Calendar.DATE, 7)) {
                    Aviso avisoNovo = getAvisoNovo(configuracaoAviso, usuario, initialDate);
                    if (!avisosExistentesUsuario.contains(avisoNovo)) {
                        avisosNovos.add(avisoNovo);
                    }
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return avisosNovos;
    }

    private List<Aviso> getAvisosNovosDiasAntesAcao(List<Aviso> avisosExistentesUsuario,
                                                    ConfiguracaoAviso configuracaoAviso, Usuario usuario,
                                                    Calendar initialDate, Calendar finalDate) {
        // RECUPERA OS AVISOS NOVOS DIAS ANTES DA ACAO
        List<Aviso> avisosNovos = new ArrayList<Aviso>();
        if (configuracaoAviso.getValorPeriodicidade() != null) {
            try {
                Integer qtdeDiasAntesAcao = Integer.parseInt(configuracaoAviso.getValorPeriodicidade().trim());
                for (; !initialDate.after(finalDate); initialDate.add(Calendar.DATE, 1)) {
                    if (isDiaAvisoDiasAntesAcao(initialDate, qtdeDiasAntesAcao)) {
                        Aviso avisoNovo = getAvisoNovo(configuracaoAviso, usuario, initialDate);
                        if (!avisosExistentesUsuario.contains(avisoNovo)) {
                            avisosNovos.add(avisoNovo);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return avisosNovos;
    }

    private boolean isDiaAvisoDiasAntesAcao(Calendar initialDate, Integer qtdeDiasAntesAcao) {
        Integer countDias = 0;
        Calendar initialDateClone = (Calendar) initialDate.clone();
        while (initialDateClone.get(Calendar.DATE) != DIA_PREENCHIMENTO) {
            initialDateClone.add(Calendar.DATE, 1);
            countDias++;
        }
        if (countDias == qtdeDiasAntesAcao) {
            return true;
        } else {
            return false;
        }
    }

    private List<Aviso> getAvisosNovosDataEspecifica(List<Aviso> avisosExistentesUsuario,
                                                     ConfiguracaoAviso configuracaoAviso, Usuario usuario,
                                                     Calendar initialDate, Calendar finalDate) throws ParseException {
        // RECUPERA OS AVISOS NOVOS DE UMA DATA ESPECIFICA
        List<Aviso> avisosNovos = new ArrayList<Aviso>();
        try {
            Calendar dataEspecifica = Calendar.getInstance();
            dataEspecifica.setTime(simpleDateFormat.parse(configuracaoAviso.getValorPeriodicidade()));
            if (!dataEspecifica.after(finalDate)) {
                for (; !initialDate.after(finalDate); initialDate.add(Calendar.DATE, 1)) {
                    if (initialDate.equals(dataEspecifica)) {
                        Aviso avisoNovo = getAvisoNovo(configuracaoAviso, usuario, initialDate);
                        if (!avisosExistentesUsuario.contains(avisoNovo)) {
                            avisosNovos.add(avisoNovo);
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return avisosNovos;
    }

    @Override
    public Long countAvisosNaoLidos(Usuario usuario) {
       return avisoDAO.countAvisosNaoLidos(usuario);
    }
}
