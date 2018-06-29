package br.jus.tjsp.movjud.business.formula.utils;

import br.jus.tjsp.movjud.business.formula.type.FuncaoCalculoType;
import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SubSecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ValidacaoDTO;
import br.jus.tjsp.movjud.business.formulario.helper.FormularioFakeUtils;
import br.jus.tjsp.movjud.business.formulario.helper.FormularioUtils;
import br.jus.tjsp.movjud.business.formulario.types.SecaoType;
import br.jus.tjsp.movjud.business.formulario.types.TipoCampoType;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

//import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public class FormulaCalculo {
    private static boolean loggerInfoHabilitado = false;
    private static final String VALOR_DEFAULT = "1";
    private static final String VALOR_ZERO = "0";
    private static final String VALOR_VAZIO = "";
    private static final String SEPARADOR_PARAMETROS = ",";
    private static final String PATTNER_FORMULA = "@\\{[^\\}]*\\}";
    private static final String PATTNER_FUNCAO_SUFIXO = "\\[[^\\]]*\\]";
    private static final String PATTNER_PARAMETRO_PREFIXO = "\\[";
    private static final String PATTNER_PARAMETRO_SUFIXO = "\\]";
    private static final String PATTNER_FORMULA_PREFIXO = "@\\{";
    private static final String PATTNER_FORMULA_SUFIXO = "\\]\\}";
    private static final String EXPRESSAO_ABRIR = "(";
    private static final String EXPRESSAO_FECHAR = ")";
    private static final String OPERADOR_MAIS = " + ";
    private static final String MASK_FORMATACAO_DECIMAL = "#.#";//"0"; //"##0.##";
    private static final String ERRO_FORMULA = "Erro na fómula";
    public static final String TIPO_FORMULA = "fórmula";
    public static final String TIPO_VALIDACAO = "validação";
  
    
    // <epr>debug logger, introduzido na versão 0.6.4.debug
    static Logger logger = null;//Logger.getLogger(FormulaCalculo.class);// LogFactory.getLog(FormulaCalculo.class);

/*    
    static{
        System.setProperty(LogFactory.FACTORY_PROPERTY,
              "weblogic.logging.commons.LogFactoryImpl");
            logger = Logger.getLogger(FormulaCalculo.class); //LogFactory.getFactory().getInstance(FormulaCalculo.class);
    }
    
    // </epr>
*/    
    private FormulaCalculo() {
    }

    public static void main(String[] args) {
        testarFormulaCalculo();
    }
    
    private static String removerFormatoValor(String valor) {
        if(valor != null && !valor.isEmpty()) {
            valor = valor.replaceAll("\\.", "");
            valor = valor.replaceAll(",", ".");
        }
        return valor;
    }

    public static String executarFormula(String formula, FormularioDTO formularioDTO, FormularioDTO formularioMesAnteriorDTO, SubSecaoDTO subSecaoDTO, String campoIdAtual) throws FormulaInvalidaException {
        formula = montarExpressao(formula, formularioDTO, formularioMesAnteriorDTO, subSecaoDTO, campoIdAtual);
        formula = executarExpressao(formula);
        return formula;
    }

    public static boolean testarFormula(String formula, String campoIdAtual) {
        boolean status = false;
        try {
            formula = montarExpressao(formula, null, null, null, campoIdAtual);
            executarExpressao(formula);

            status = true;
        } catch (FormulaInvalidaException e) {
            loggerError("Exceção não tratada: "+e.toString());
        }
        return status;
    }

    public static boolean executarValidacao(String formula, FormularioDTO formularioDTO, FormularioDTO formularioMesAnterior) throws FormulaInvalidaException {
        boolean status = false;

        try {
            formula = montarExpressao(formula, formularioDTO, formularioMesAnterior, null, null);
            status = validarExpressao(formula);
        } catch (Exception e) {
            loggerError("Exceção não tratada: "+e.toString());
        }
        return status;
    }

    public static boolean testarValidacao(String formula) {
        boolean status = false;

        try {
            formula = montarExpressao(formula, null, null, null, null);
            validarExpressao(formula);

            status = true;
        } catch (Exception e) {
            loggerError("Exceção não tratada: "+e.toString());
        }
        return status;
    }
    
    // <epr> utilidades geração de log
    // introduzidas em 0.6.4.debug
    private static void loggerInfo(String text) {
        //System.out.println("INFO: " + text);
        
        if (!loggerInfoHabilitado) return;
        //System.out.println("INFO: " + text);
        if(logger == null || text == null || text.isEmpty()) return;
        if((logger.getLevel() == null) || (logger.getLevel().toInt() >= Level.INFO.intValue())) {
            logger.info(text);
        }
    }
    
    private static void loggerWarn(String text) {
        //System.out.println("WARN: " + text);
        if(logger == null || text == null || text.isEmpty()) return;
        if((logger.getLevel() == null) || (logger.getLevel().toInt() >= Level.WARNING.intValue())) {
            logger.warn(text);
        }
    }
    
    private static void loggerError(String text) {
        //System.out.println("ERROR: " + text);
        if(logger == null || text == null || text.isEmpty()) return;
        if((logger.getLevel() == null) || (logger.getLevel().toInt() >= Level.SEVERE.intValue())) {
            logger.error(text);
        }
    }
    
    private static void logValidacao(CampoDTO campoDTO) {
        if((campoDTO != null) && (campoDTO.getListaValidacoes() != null) && !campoDTO.getListaValidacoes().isEmpty()) {
            loggerInfo("      validacoes do campo:");
            List<ValidacaoDTO> listaValidacaoDTO = campoDTO.getListaValidacoes();
            for(ValidacaoDTO item : listaValidacaoDTO) {
                if(item != null) {
                    loggerInfo("        tipo: "+item.getCodigoTipoValidacao());
                    loggerInfo("        validacao: "+((item.getFormula() != null && item.getFormula().getExpressao() != null) ? item.getFormula().getExpressao() : "nulo"));
                } else {
                    loggerInfo("        nulo");
                }
            }
        }
    }
    // </epr> utilidades geração de logs
    
    public static String montarExpressao(String formula, FormularioDTO formularioDTO, FormularioDTO formularioMesAnteriorDTO, SubSecaoDTO subSecaoDTO, String campoIdAtual) {
        try {
        loggerInfo("inicio montarExpressao:");
        loggerInfo("  formula: " + ((formula != null) ? formula : "null"));
        loggerInfo("  formulario: " + ((formularioDTO != null) ? Long.toString(formularioDTO.getIdFormulario()) : "nulo"));
        loggerInfo("  formularioMesAnterior: " + ((formularioMesAnteriorDTO != null) ? Long.toString(formularioMesAnteriorDTO.getIdFormulario()) : "nulo"));
        loggerInfo("  subSecao: " + ((subSecaoDTO != null) ? Long.toString(subSecaoDTO.getId()): "nulo"));
        loggerInfo("  idCampoAtual: " + ((campoIdAtual != null) ? campoIdAtual : "nulo"));
        }catch(Exception e) {
            loggerError("Erro gerando informações de montarExpressao formula: "+e.toString());
        }
        try {
        formula = funcaoValorMesAnterior(formula, formularioMesAnteriorDTO, subSecaoDTO);
        formula = funcaoValor(formula, formularioDTO, formularioMesAnteriorDTO, subSecaoDTO, campoIdAtual);
        formula = funcaoSomaSecao(formula, formularioDTO);
        }catch(Exception e) {
            loggerError("Erro gerando informações de montarExpressao formula: "+e.toString());
        }
        loggerInfo("  formula: " + ((formula != null) ? formula : "nulo"));        
        loggerInfo("fim montarExpressao");
        
        return formula;
    }

    private static String funcaoValor(String formula, FormularioDTO formularioDTO, 
                                      // <epr> (1. inclusão do parâmetro) 0.7.7.debug transferência de formularioMesAnteriorDTO
                                      FormularioDTO formularioMesAnteriorDTO, 
                                      // </epr> (1. inclusão do parâmetro) 0.7.7.debug transferência de formularioMesAnteriorDTO
                                      SubSecaoDTO subSecaoDTO, String campoIdAtual) {
        loggerInfo("    inicio funcaoValor:");
        Pattern pattern = Pattern.compile(PATTNER_FORMULA, Pattern.MULTILINE);
        Pattern patternValor = Pattern.compile(FuncaoCalculoType.VALOR_CAMPO.getNomeFuncao() + PATTNER_FUNCAO_SUFIXO, Pattern.MULTILINE);
        Matcher matcherFormula = pattern.matcher(formula);

        while (matcherFormula.find()) {
            String itemFormula = matcherFormula.group();
            Matcher matcherValor = patternValor.matcher(itemFormula);
            String campoId = null;

            while (matcherValor.find()) {
                String valor = matcherValor.group();
                campoId = valor.replaceFirst(FuncaoCalculoType.VALOR_CAMPO.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO, VALOR_VAZIO).replaceAll(PATTNER_PARAMETRO_SUFIXO, VALOR_VAZIO);
            }

            if (campoId != null) {
                loggerInfo("    campoID: " + campoId);
                if (formularioDTO != null) {
                    SubSecaoDTO subSecao = FormularioUtils.encontrarSubSecao(formularioDTO, subSecaoDTO);
                    CampoDTO campoDTO = null;
                    if (subSecao != null) {
                        loggerInfo("      procurar em subSecao: " + subSecao.getCodigoSubSecao());
                        campoDTO = FormularioUtils.encontrarCampo(subSecao, campoId);
                    } else {
                        loggerInfo("      procurar em cad_campo: ");
                        campoDTO = FormularioUtils.encontrarCadCampo(formularioDTO, campoId);
                    }
                    if(campoDTO != null) {
                        loggerInfo("        campo: " + ((campoDTO.getLabelCampo() != null) ? "["+campoDTO.getLabelCampo()+"]" : "nulo"));
                        loggerInfo("           id: " + (campoDTO.getIdCampo() != null ? campoDTO.getIdCampo().toString() : "nulo"));
                        loggerInfo("           md: " + (campoDTO.getIdMetadadosCampo() != null ? campoDTO.getIdMetadadosCampo().toString() : "nulo"));
                    }
                    logValidacao(campoDTO);
                    
                    if (campoDTO != null && campoDTO.getValorCampo() != null) {
                        if (!campoId.equalsIgnoreCase(campoIdAtual)) {
                            if (TipoCampoType.FORMULA.equals(campoDTO.getTipoCampo())) {
                                loggerInfo("      expandindo campo formula: " + ((formula != null) ? formula : "nulo"));
                                formula =
                                    formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.VALOR_CAMPO.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoId + PATTNER_FORMULA_SUFIXO,
                                                       EXPRESSAO_ABRIR + campoDTO.getFormula().getExpressao() + EXPRESSAO_FECHAR);
                                formula = montarExpressao(formula, formularioDTO, 
                                                          // <epr> (2. transferência do parâmetro) 0.7.7.debug transferência de formularioMesAnteriorDTO
                                                          // null,
                                                          formularioMesAnteriorDTO, 
                                                          // </epr> (2. transferência do parâmetro) 0.7.7.debug transferência de formularioMesAnteriorDTO
                                                          /*null: CEJUSC*/subSecaoDTO, null);
                                loggerInfo("      formula expandida: " + ((formula != null) ? formula : "nulo"));
                            } else {
                                loggerInfo("      substitui valor: " + ((formula != null) ? formula : "nulo"));
                                formula = formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.VALOR_CAMPO.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoId + PATTNER_FORMULA_SUFIXO, removerFormatoValor(campoDTO.getValorCampo()));
                                loggerInfo("      formula valor substituido: " + ((formula != null) ? formula : "nulo"));
                            }
                        }
                    } else {
                        // <epr> atribuição de VALOR_ZERO para campo não encontrado, conforme informado em 04/07/17 pela Eliane if (campoDTO != null) </epr>
                            loggerInfo("      substituicao de valor nao encontrado por 0: " + ((formula != null) ? formula : "nulo"));
                            formula = formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.VALOR_CAMPO.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoId + PATTNER_FORMULA_SUFIXO, VALOR_ZERO);
                        loggerInfo("      formula com valor nao encontrado substituido por 0: " + ((formula != null) ? formula : "nulo"));
                    }
                } else {
                    loggerInfo("      formulario nulo");
                    if (campoIdAtual == null || !campoId.equalsIgnoreCase(campoIdAtual)) {
                        loggerInfo("      formulario campoAtual nulo, formula: " + ((formula != null) ? formula : "nulo"));
                        formula = formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.VALOR_CAMPO.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoId + PATTNER_FORMULA_SUFIXO, VALOR_DEFAULT);
                        loggerInfo("      formula onde formulario nulo e campoAtual nulo: " + ((formula != null) ? formula : "nulo"));
                    }
                }
            }
        }
        loggerInfo("      formula: " + ((formula != null) ? formula : "nulo"));
        loggerInfo("    fim funcaoValor");
        return formula;
    }

    private static String funcaoValorMesAnterior(String formula, FormularioDTO formularioDTO, SubSecaoDTO subSecaoDTO) {
        loggerInfo("    inicio funcaoValorMesAnterior");
        Pattern pattern = Pattern.compile(PATTNER_FORMULA, Pattern.MULTILINE);
        Pattern patternValor = Pattern.compile(FuncaoCalculoType.VALOR_CAMPO_MES_ANTERIOR.getNomeFuncao() + PATTNER_FUNCAO_SUFIXO, Pattern.MULTILINE);
        Matcher matcherFormula = pattern.matcher(formula);

        while (matcherFormula.find()) {
            String itemFormula = matcherFormula.group();
            Matcher matcherValor = patternValor.matcher(itemFormula);
            String campoId = null;

            while (matcherValor.find()) {
                String valor = matcherValor.group();
                campoId = valor.replaceFirst(FuncaoCalculoType.VALOR_CAMPO_MES_ANTERIOR.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO, VALOR_VAZIO).replaceAll(PATTNER_PARAMETRO_SUFIXO, VALOR_VAZIO);
            }

            if (campoId != null) {
                loggerInfo("    campoId: " + campoId);
                if (formularioDTO != null) {
                    SubSecaoDTO subSecaoMesAnterior = FormularioUtils.encontrarSubSecao(formularioDTO, subSecaoDTO);
                    CampoDTO campoDTO = null;
                    if (subSecaoMesAnterior != null) {
                        loggerInfo("    procurar em subSecaoMesAnterior: " + subSecaoMesAnterior.getCodigoSubSecao());
                        campoDTO = FormularioUtils.encontrarCampo(subSecaoMesAnterior, campoId); 
                        loggerInfo("      campo: " + ((campoDTO != null) ? ((campoDTO.getLabelCampo() != null) ? "["+campoDTO.getLabelCampo()+"]" : "[]") : "nulo"));
                    } else {
                        loggerInfo("    procurar em cad_campo:");
                        campoDTO = FormularioUtils.encontrarCadCampo(formularioDTO, campoId);
                        loggerInfo("      campo: " + ((campoDTO != null) ? ((campoDTO.getLabelCampo() != null) ? "["+campoDTO.getLabelCampo()+"]" : "[]") : "nulo"));
                    }
                    logValidacao(campoDTO);                    
                    if (campoDTO != null && campoDTO.getValorCampo() != null) {
                        //if (TipoCampoType.FORMULA.equals(campoDTO.getTipoCampo())) {
                        //    formula = formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.VALOR_CAMPO_MES_ANTERIOR.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoId + PATTNER_FORMULA_SUFIXO,
                        //                                 EXPRESSAO_ABRIR + campoDTO.getFormula().getExpressao() + EXPRESSAO_FECHAR);
                        //    formula = montarExpressao(formula, formularioDTO, null, null);
                        //} else {
                        loggerInfo("      substitui valor em: " + ((formula != null) ? formula : "nulo"));
                        formula = formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.VALOR_CAMPO_MES_ANTERIOR.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoId + PATTNER_FORMULA_SUFIXO, removerFormatoValor(campoDTO.getValorCampo()));
                        loggerInfo("      formula com valor substituido: " + ((formula != null) ? formula : "nulo"));                        
                        //}
                    } else {
                        loggerInfo("      substituicao de valor nao encontrado por 0: " + ((formula != null) ? formula : "nulo"));
                        formula = formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.VALOR_CAMPO_MES_ANTERIOR.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoId + PATTNER_FORMULA_SUFIXO, VALOR_ZERO);
                        loggerInfo("      formula com valor nao encontrado substituido por 0: " + ((formula != null) ? formula : "nulo"));
                    }
                } else {
                    loggerInfo("      substituicao de valor nao encontrado (formulario nulo) por 0: " + ((formula != null) ? formula : "nulo"));                    
                    formula = formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.VALOR_CAMPO_MES_ANTERIOR.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoId + PATTNER_FORMULA_SUFIXO, VALOR_ZERO);
                    loggerInfo("      formula com valor nao encontrado (formulario nulo) substituido por 0: " + ((formula != null) ? formula : "nulo"));
                }
            }
        }
        loggerInfo("      formula: " + ((formula != null) ? formula : "nulo"));
        loggerInfo("    fim funcaoValorMesAnterior");        
        return formula;
    }


    private static String funcaoSomaSecao(String formula, FormularioDTO formularioDTO) {
        loggerInfo("    inicio funcaoSomaSecao");
        Pattern pattern = Pattern.compile(PATTNER_FORMULA, Pattern.MULTILINE);
        Pattern patternValor = Pattern.compile(FuncaoCalculoType.SOMA_SECAO.getNomeFuncao() + PATTNER_FUNCAO_SUFIXO, Pattern.MULTILINE);
        Matcher matcherFormula = pattern.matcher(formula);

        while (matcherFormula.find()) {
            String itemFormula = matcherFormula.group();
            Matcher matcherValor = patternValor.matcher(itemFormula);

            String nomeCampo = null;
            String nomeSecao = null;

            while (matcherValor.find()) {
                String valor = matcherValor.group();
                String campoSecao = valor.replaceFirst(FuncaoCalculoType.SOMA_SECAO.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO, VALOR_VAZIO).replaceAll("\\]", VALOR_VAZIO);

                if (campoSecao != null && campoSecao.contains(SEPARADOR_PARAMETROS)) {
                    nomeCampo = campoSecao.split(SEPARADOR_PARAMETROS)[0].toUpperCase();
                    nomeSecao = campoSecao.split(SEPARADOR_PARAMETROS)[1].trim().toUpperCase();
                }

                if (formularioDTO != null) {
                    List<CampoDTO> listaCampos = FormularioUtils.encontrarCadCampoDasSubSecoes(formularioDTO, nomeCampo);

                    if (listaCampos != null && listaCampos.size() > 0) {
                        String strSomaCampo = "";

                        for (int contCampo = 0; contCampo < listaCampos.size(); contCampo++) {
                            CampoDTO campoDTO = listaCampos.get(contCampo);
                            if(campoDTO == null) {
                                loggerWarn("campo " + Integer.toString(contCampo) + "nulo para formularioID " + Long.toString(formularioDTO.getIdFormulario()));
                                continue;
                            }
                            loggerInfo("      processando campo: "+campoDTO.getCodigoCampo());
                            String strCampo;

                            if (TipoCampoType.FORMULA.equals(campoDTO.getTipoCampo())) {
                                if((campoDTO.getFormula() == null) || (campoDTO.getFormula().getExpressao() == null)) {
                                    loggerWarn("campo " + ((campoDTO.getLabelCampo() != null) ? campoDTO.getLabelCampo() : "nulo") + " com formula ou expressao nulo." );
                                    continue;
                                }
                                loggerInfo("      expandindo expressao na formula: " + campoDTO.getFormula().getExpressao());
                                strCampo = EXPRESSAO_ABRIR + montarExpressao(campoDTO.getFormula().getExpressao(), formularioDTO, null, campoDTO.getGrupoRecursivo().getSubSecao(), null) + EXPRESSAO_FECHAR;
                                loggerInfo("      resultado expansao da formula: " + ((strCampo != null) ? strCampo : "nulo"));
                            } else if (campoDTO != null && campoDTO.getValorCampo() != null) {
                                strCampo = removerFormatoValor(campoDTO.getValorCampo());
                                loggerInfo("      resultado substituicao valor campo: " + ((strCampo != null) ? strCampo : "nulo"));
                            } else {
                                strCampo = VALOR_ZERO;
                                loggerInfo("      campo nao encontrado, atribuindo valor zero");
                            }
                            logValidacao(campoDTO);
                            if (listaCampos.size() > 1) {
                                if (contCampo == 0)
                                    strSomaCampo += EXPRESSAO_ABRIR + strCampo + OPERADOR_MAIS;
                                else if (contCampo == listaCampos.size() - 1)
                                    strSomaCampo += strCampo + EXPRESSAO_FECHAR;
                                else
                                    strSomaCampo += strCampo + OPERADOR_MAIS;
                            } else {
                                strSomaCampo = strCampo;
                            }
                        }
                        formula = formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.SOMA_SECAO.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoSecao + PATTNER_FORMULA_SUFIXO, strSomaCampo);
                        loggerInfo("      formula apos processamento do campo: " + ((formula != null) ? formula : "nulo"));
                    } /* <epr> 0.6.4 substituição para função soma em secao não encontrada com valor zerto */ else {
                        formula = formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.SOMA_SECAO.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoSecao + PATTNER_FORMULA_SUFIXO, VALOR_ZERO);
                        loggerInfo("      formula apos processamento do campo nao encontrado em campos filho: " + ((formula != null) ? formula : "nulo"));
                    } // </epr> 0.6.4 substituição para função soma em secao não encontrada com valor aero
                } else {
                    formula = formula.replaceAll(PATTNER_FORMULA_PREFIXO + FuncaoCalculoType.SOMA_SECAO.getNomeFuncao() + PATTNER_PARAMETRO_PREFIXO + campoSecao + PATTNER_FORMULA_SUFIXO, VALOR_DEFAULT);
                    loggerInfo("      formula apos processamento do campo nao encontrado na secao '" + ((nomeSecao != null) ? nomeSecao : "nulo") + "': " + ((formula != null) ? formula : "nulo"));
                }
            }
        }
        loggerInfo("      formula: " + ((formula != null) ? formula : "nulo"));
        loggerInfo("    fim funcaoSomaSecao");
        return formula;
    }

    static ScriptEngine engineJavaScript;

    static{
        ScriptEngineManager factory = new ScriptEngineManager();
        engineJavaScript = factory.getEngineByName("JavaScript");
    }

    private static String executarExpressao(String formula) throws FormulaInvalidaException {
        String strRetorno = "";
        try {
            Object resultadoCalculo = engineJavaScript.eval(formula);
            DecimalFormat df = new DecimalFormat(MASK_FORMATACAO_DECIMAL);
            strRetorno = df.format(resultadoCalculo);
        } catch (ScriptException e) {
            throw new FormulaInvalidaException(FormulaInvalidaException.FORMULA_ERRO_SCRIPT_INVALIDO);
        } catch (Exception e) {
            throw new FormulaInvalidaException(FormulaInvalidaException.FORMULA_ERRO_CALCULO);
        }
        return strRetorno;
    }

    private static boolean validarExpressao(String expressao) throws FormulaInvalidaException {
        boolean status = false;

        try {
            Object resultadoValidaExpressao;
            resultadoValidaExpressao = engineJavaScript.eval(expressao);

            if (resultadoValidaExpressao instanceof Integer)
                throw new FormulaInvalidaException(FormulaInvalidaException.FORMULA_ERRO_VALIDACAO);
            else
                status = Boolean.valueOf(resultadoValidaExpressao.toString());
        } catch (ScriptException e) {
            throw new FormulaInvalidaException(FormulaInvalidaException.FORMULA_ERRO_CALCULO);
        }

        return status;
    }

    public static FormularioDTO calcularFormulasDoFormulario(FormularioDTO formularioDTO, FormularioDTO formularioMesAnteriorDTO) {
        loggerInfo("######## processando formulas para formularioId: " + ((formularioDTO != null && formularioDTO.getIdFormulario() != null) ? Long.toString(formularioDTO.getIdFormulario()) : "nulo"));
        List<TipoCampoType> tiposCampoFormula = new ArrayList<TipoCampoType>();
        tiposCampoFormula.add(TipoCampoType.FORMULA);
        List<CampoDTO> listaCamposTipoFormula = FormularioUtils.recuperarCamposFormularioPorTipo(formularioDTO, tiposCampoFormula);
        for (CampoDTO campoDTO : listaCamposTipoFormula) {
            try {campoDTO.getLabelCampo().contains("");
                loggerInfo("#### processando formula para o campo '" +
                           ((campoDTO.getLabelCampo() != null) ? campoDTO.getLabelCampo() : "nulo") + "': " +
                           ((campoDTO.getFormula() != null && campoDTO.getFormula().getExpressao() != null) ?
                            campoDTO.getFormula().getExpressao() : "nulo") + ", id_campo: " +
                           ((campoDTO.getIdCampo() != null) ? Long.toString(campoDTO.getIdCampo()) : "nulo"));
                logValidacao(campoDTO); 
                campoDTO.getFormula()
                    .setResultadoExpressao(executarFormula(campoDTO.getFormula().getExpressao(), formularioDTO,
                                                           formularioMesAnteriorDTO,
                                                           campoDTO.getGrupoRecursivo().getSubSecao(),
                                                           campoDTO.getCodigoCampo()));
                // Erro 116 - Após executar a formula dos campos tipo formula, não estava atribuindo o resultado a propriedade 'valorCampo', fazendo com que os campos que dependem de outros campos formulas viessem com valor Zero(0) - Paula Covo 04.09.2017
                campoDTO.setValorCampo(campoDTO.getFormula().getResultadoExpressao());
                loggerInfo("#### processamento completado com sucesso.");
            } catch (FormulaInvalidaException e) {
                campoDTO.getFormula().setResultadoExpressao(ERRO_FORMULA);
                loggerInfo("#### processamento completado com erros.");
            }
        }
        loggerInfo("######## encerrando processamento de formulas para formularioId: " + ((formularioDTO != null && formularioDTO.getIdFormulario() != null) ? Long.toString(formularioDTO.getIdFormulario()) : "nulo"));
        return formularioDTO;
    }

    public static FormularioDTO aplicarValidacoesCamposDoFormulario(FormularioDTO formularioDTO, FormularioDTO formularioMesAnterior) {
        List<TipoCampoType> tiposCampo = new ArrayList<TipoCampoType>();
        tiposCampo.add(TipoCampoType.FORMULA);
        tiposCampo.add(TipoCampoType.NUMERO);

        List<CampoDTO> listaCampos = FormularioUtils.recuperarCamposFormularioPorTipo(formularioDTO, tiposCampo);
        for (CampoDTO campoDTO : listaCampos) {
            for (ValidacaoDTO validacao : campoDTO.getListaValidacoes()) {
                boolean status = false;

                try {
                    status = executarValidacao(validacao.getFormula().getExpressao(), formularioDTO, formularioMesAnterior);
                } catch (FormulaInvalidaException e) {

                }

                validacao.setStatusValidacao(status);
            }
        }
        return formularioDTO;
    }


    private static void testarFormulaCalculo() {
        FormularioDTO formularioDTO = FormularioFakeUtils.gerarFormularioFake(Arrays.asList(new SecaoType[] { SecaoType.DADOS_UNIDADES, SecaoType.MAGISTRADO }), "FM", "Meu Form", 3, 10);
        FormularioDTO formularioMesAnteriorDTO = FormularioFakeUtils.gerarFormularioFake(Arrays.asList(new SecaoType[] { SecaoType.DADOS_UNIDADES, SecaoType.MAGISTRADO }), "FM", "Meu Form", 3, 10);

        FormularioFakeUtils.printFormularioDTO(formularioDTO);

        List<CampoDTO> listaCampos = FormularioUtils.encontrarCadCampoDasSubSecoes(formularioDTO, "MG3C2");

        loggerInfo("listaCampos  : " + listaCampos.size());

        for (CampoDTO campo : listaCampos) {
            if (TipoCampoType.FORMULA.equals(campo.getTipoCampo())) {
                loggerInfo("- " + campo.getCodigoCampo() + " - " + campo.getLabelCampo() + " - " + campo.getTipoCampo().getLabelTipoCampo() + " - " + campo.getFormula().getExpressao());
            } else {
                loggerInfo("- " + campo.getCodigoCampo() + " - " + campo.getLabelCampo() + " - " + campo.getTipoCampo().getLabelTipoCampo() + " - " + removerFormatoValor(campo.getValorCampo()));

            }

        }

        String strFormula = "@{somaSecao[MG1C2, magistrado]} - (@{valor[SUG2C4]} - @{valorMesAnterior[SUG1C1]} / 13) + 2 + 3 * 4 - 1";
        String strValidacao = strFormula + " == 14";

        try {
            loggerInfo("====================================================");
            loggerInfo("[Formula Calculo] - Processo da Formula");
            loggerInfo("====================================================");
            loggerInfo("strFormula                                : " + strFormula);
            loggerInfo("testarFormula(strFormula)                 : " + testarFormula(strFormula, null));
            loggerInfo("montarExpressao(strFormula, formularioDTO): " + montarExpressao(strFormula, formularioDTO, formularioMesAnteriorDTO, null, "MG1C2"));
            loggerInfo("executarFormula(strFormula, formularioDTO): " + executarFormula(strFormula, formularioDTO, formularioMesAnteriorDTO, null, "MG1C2"));
            loggerInfo("====================================================");

        } catch (FormulaInvalidaException e) {
            e.printStackTrace();
        }


        try {
            loggerInfo("====================================================");
            loggerInfo("[Formula Calculo] - Processo de Validacao");
            loggerInfo("====================================================");
            loggerInfo("strValidacao                                  : " + strValidacao);
            loggerInfo("testarValidacao(strValidacao)                 : " + testarValidacao(strValidacao));
            loggerInfo("montarExpressao(strValidacao, formularioDTO)  : " + montarExpressao(strValidacao, formularioDTO, null, null, "MG1C2"));
            // <epr> 0.7.14> loggerInfo("executarValidacao(strValidacao, formularioDTO): " + executarValidacao(strValidacao, formularioDTO)); </epr>
            loggerInfo("====================================================");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
