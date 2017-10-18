package br.jus.tjsp.movjud.business.formulario.helper;

import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.GrupoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SubSecaoDTO;
import br.jus.tjsp.movjud.business.formulario.types.SecaoType;
import br.jus.tjsp.movjud.business.formulario.types.TipoCampoType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FormularioFakeUtils {
    
    private static Random rand = new Random();
    private static final int MAX_RANGE_RANDOM = 50;
    
    public FormularioFakeUtils() {

    }

    public static void main(String[] args) {
	List<FormularioDTO> listaFormularios = listarFormulariosFake(2, 3, 5, Arrays.asList(SecaoType.values()));
	for (FormularioDTO formulario : listaFormularios) {
	    printFormularioDTO(formulario);
	}
    }

    public static void printFormularioDTO(FormularioDTO formulario) {
	System.out.println("======================================================================");
	System.out.println("Formulario DTO");
	System.out.println("======================================================================");
	System.out.println("  " + formulario.getCodigoFormulario() + " - " + formulario.getNomeFormulario());
	System.out.println("    Secoes");
	for (SecaoDTO secao : formulario.getListaSecoes()) {
	    System.out.println("      " + secao.getCodigoSecao() + " - " + secao.getLabelSecao());
	    System.out.println("      SubSecoes");
	    for (SubSecaoDTO subSecao : secao.getListaSubSecoes()) {
		System.out.println("        " + subSecao.getCodigoSubSecao() + "- " + subSecao.getLabelSecao());
		System.out.println("        Grupos");
		for (GrupoDTO grupo : subSecao.getListaGrupos()) {
		    System.out.println("          " + grupo.getCodigoGrupo() + "- " + grupo.getLabelGrupo());
		    System.out.println("          Campos");
		    for (CampoDTO campo : grupo.getListaCampos()) {
			if (TipoCampoType.FORMULA.equals(campo.getTipoCampo())) {
			    System.out.println("            " + campo.getCodigoCampo() + " - " + campo.getLabelCampo() + " - " + campo.getTipoCampo().getLabelTipoCampo() + " - " +
					       campo.getFormula().getExpressao());
			} else {
			    System.out.println("            " + campo.getCodigoCampo() + " - " + campo.getLabelCampo() + " - " + campo.getTipoCampo().getLabelTipoCampo() + " - " +
					       campo.getValorCampo());

			}
		    }
		}
	    }
	}
	System.out.println("======================================================================");
    }


    public static List<FormularioDTO> listarFormulariosFake(int qtdeFormularios, int qtdeGrupos, int qtdeCampos, List<SecaoType> secoes) {
	List<FormularioDTO> listaFormularioDTO = new ArrayList<FormularioDTO>();

	for (int i = 1; i <= qtdeFormularios; i++) {
	    listaFormularioDTO.add(gerarFormularioFake(secoes, "Form" + i, "Formulario" + i, qtdeGrupos, qtdeCampos));
	}

	return listaFormularioDTO;
    }

    public static FormularioDTO gerarFormularioFake(List<SecaoType> secoes, String codigoFormulario, String nomeFormulario, int qtdeGrupos, int qtdeCampos) {
	FormularioDTO formularioDTO = new FormularioDTO();

	formularioDTO.setCodigoFormulario(codigoFormulario);
	formularioDTO.setNomeFormulario(nomeFormulario);

	for (SecaoType secao : secoes) {
	    formularioDTO.getListaSecoes().add(gerarSecaoFake(secao, qtdeGrupos, qtdeCampos));
	}
	return formularioDTO;
    }


    public static SecaoDTO gerarSecaoFake(SecaoType secaoType, int qtdeGrupos, int qtdeCampos) {
	SecaoDTO secaoDTO = new SecaoDTO(secaoType);
	int qtdeSubSecao = (SecaoType.MAGISTRADO.equals(secaoType) || SecaoType.MATERIA.equals(secaoType)) ? 5 : 1;

	for (int i = 1; i <= qtdeSubSecao; i++) {
	    secaoDTO.getListaSubSecoes().add(gerarSubSecaoFake(secaoDTO, secaoType, qtdeGrupos, qtdeCampos));
	}
	return secaoDTO;
    }

    public static SubSecaoDTO gerarSubSecaoFake(SecaoDTO secao, SecaoType secaoType, int qtdeGrupos, int qtdeCampos) {
	SubSecaoDTO subSecaoDTO = new SubSecaoDTO(secao);
	subSecaoDTO.setCodigoSubSecao(secao.getCodigoSecao());
	subSecaoDTO.setLabelSecao(secao.getLabelSecao());
	for (int i = 1; i <= qtdeGrupos; i++) {
	    subSecaoDTO.getListaGrupos().add(gerarGrupoFake(secaoType.getCodigoSecao() + "G" + (i), i, qtdeCampos));
	}
	return subSecaoDTO;
    }

    public static GrupoDTO gerarGrupoFake(String nomeGrupo, int ordemGrupo, int qtdeCampos) {
	GrupoDTO grupo = new GrupoDTO();
	grupo.setCodigoGrupo(nomeGrupo);
	grupo.setLabelGrupo("Grupo " + nomeGrupo);
	grupo.setOrdemGrupo((long) ordemGrupo);

	for (int i = 1; i <= qtdeCampos; i++) {
	    grupo.getListaCampos().add(gerarCampoFake(nomeGrupo + "C" + i, i));
	}
	return grupo;
    }

    public static CampoDTO gerarCampoFake(String nomeCampo, int ordemCampo) {
	CampoDTO campo = new CampoDTO();
	campo.setCodigoCampo(nomeCampo);
	campo.setLabelCampo("Campo " + nomeCampo);
	campo.setValorCampo(String.valueOf(ordemCampo));

	if (ordemCampo >= 0 && ordemCampo <= 2) {
	    
	    campo.setTipoCampo(TipoCampoType.NUMERO);
	    campo.setValorCampo(Integer.toString(rand.nextInt(FormularioFakeUtils.MAX_RANGE_RANDOM) + 1));
	    campo.setValorCampoMesAnterior(Integer.toString(rand.nextInt(FormularioFakeUtils.MAX_RANGE_RANDOM) + 1));
	} else if (ordemCampo == 3) {
	    campo.setTipoCampo(TipoCampoType.TEXTO);
	    campo.setValorCampo("Str" + Long.toString(ordemCampo));
	    campo.setValorCampoMesAnterior("StrAntigo" + Long.toString(ordemCampo));
	} else {
	    campo.setTipoCampo(TipoCampoType.FORMULA);
	    campo.setValorCampo("");
	    campo.setValorCampoMesAnterior("");
	    
	    if (ordemCampo == 4)
	        campo.getFormula().setExpressao("10 + 2 * " + (rand.nextInt(FormularioFakeUtils.MAX_RANGE_RANDOM) + 1));
	    else
		campo.getFormula().setExpressao("@{valor[SUG1C1]} + 6 - " + (rand.nextInt(FormularioFakeUtils.MAX_RANGE_RANDOM) + 1));
	}

	campo.setOrdemCampo((long) ordemCampo);
	campo.setHint("Ajuda do campo" + campo.getCodigoCampo());

	return campo;
    }


}
