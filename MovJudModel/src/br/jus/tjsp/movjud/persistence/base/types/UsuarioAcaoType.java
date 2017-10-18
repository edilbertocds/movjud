package br.jus.tjsp.movjud.persistence.base.types;


import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.persistence.entity.Acao;
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.UsuarioAcao;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAcaoType {

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public enum PermissaoAcaoType{
            PADRAO_TRUE, 
            PADRAO_FALSE, 
            CONCEDIDO, 
            REMOVIDO;
        }
    
    
    public UsuarioAcaoType(PermissaoAcaoType permissaoAcao, Acao acao) {
        switch (permissaoAcao){
            case PADRAO_TRUE:
            this.marcado = true;
            this.imagem = "/resource/image/lista_vazia.png";
            this.acao = acao;
            this.permissaoAcao = permissaoAcao;
           break;
            case PADRAO_FALSE:
            this.marcado = false;
            this.imagem = "/resource/image/lista_vazia.png";
            this.acao = acao;
            this.permissaoAcao = permissaoAcao;
            break;
            case CONCEDIDO:
            this.marcado = true;
            this.imagem = "/resource/image/lista_add.png";
            this.acao = acao;
            this.permissaoAcao = permissaoAcao;
            break;
            case REMOVIDO:
            this.marcado = false;
            this.imagem = "/resource/image/lista_remover.png";
            this.acao = acao;
            this.permissaoAcao = permissaoAcao;
            break;
        }
    }

    public void setPermissaoAcao(UsuarioAcaoType.PermissaoAcaoType permissaoAcao) {
        this.permissaoAcao = permissaoAcao;
    }

    public UsuarioAcaoType.PermissaoAcaoType getPermissaoAcao() {
        return permissaoAcao;
    }

    public boolean isMarcado() {
        return marcado;
    }
    
    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Acao getAcao() {
        return acao;
    }
    
    PermissaoAcaoType permissaoAcao;
    boolean marcado;
    boolean habilitado;
           
    String imagem;
    Acao acao;

    public static UsuarioAcaoType alterarTipoPermissao(Perfil perfilSelecionado, UsuarioAcaoType acaoCorrente, boolean novaMarcacao,  boolean novoHabilitado){
        if(perfilSelecionado.getAcoes().contains(acaoCorrente.getAcao())&&novaMarcacao==false){
            acaoCorrente.setPermissaoAcao(PermissaoAcaoType.REMOVIDO); 
            acaoCorrente.setImagem("/resource/image/lista_remover.png");
        }else if(!perfilSelecionado.getAcoes().contains(acaoCorrente.getAcao())&&novaMarcacao==true){
            acaoCorrente.setPermissaoAcao(PermissaoAcaoType.CONCEDIDO); 
            acaoCorrente.setImagem("/resource/image/lista_add.png");
        }else if(perfilSelecionado.getAcoes().contains(acaoCorrente.getAcao())&&novaMarcacao==true){
            acaoCorrente.setPermissaoAcao(PermissaoAcaoType.PADRAO_TRUE); 
            acaoCorrente.setImagem("/resource/image/lista_vazia.png");
        }else if(!perfilSelecionado.getAcoes().contains(acaoCorrente.getAcao())&&novaMarcacao==false){
            acaoCorrente.setPermissaoAcao(PermissaoAcaoType.PADRAO_FALSE); 
            acaoCorrente.setImagem("/resource/image/lista_vazia.png");
        }
        acaoCorrente.setMarcado(novaMarcacao);
        acaoCorrente.setHabilitado(novoHabilitado);
        return acaoCorrente;
    }
    
    public static List<UsuarioAcaoType> recuperarListaPermissoes(List<Acao> listaCompleta, List<Acao> listaPerfilAcao, List<UsuarioAcao> listaUsuarioAcao, boolean novoHabilitado){
        List<UsuarioAcaoType> listaUsuarioAcaoType = new ArrayList<UsuarioAcaoType>();
        for (Acao acaoComp : listaCompleta) {
            UsuarioAcaoType usuarioAcaoItem = null;
            if(listaPerfilAcao!=null  && (listaPerfilAcao.contains(acaoComp) || listaPerfilAcao.isEmpty())){
                usuarioAcaoItem = vaidarFlagPermitido(listaUsuarioAcao, acaoComp);
                if(usuarioAcaoItem==null && !listaPerfilAcao.isEmpty()){
                    usuarioAcaoItem = new UsuarioAcaoType(PermissaoAcaoType.PADRAO_TRUE, acaoComp);
                }else if(usuarioAcaoItem==null && listaPerfilAcao.isEmpty()){
                    usuarioAcaoItem = new UsuarioAcaoType(PermissaoAcaoType.PADRAO_FALSE, acaoComp);
                }
            }else{
                usuarioAcaoItem = vaidarFlagPermitido(listaUsuarioAcao, acaoComp);
                if(usuarioAcaoItem==null){
                    usuarioAcaoItem = new UsuarioAcaoType(PermissaoAcaoType.PADRAO_FALSE, acaoComp);
                }
            }
            usuarioAcaoItem.setHabilitado(novoHabilitado);
            listaUsuarioAcaoType.add(usuarioAcaoItem);
        }       
        return listaUsuarioAcaoType;
    }

    
    private static UsuarioAcaoType vaidarFlagPermitido(List<UsuarioAcao> listaUsuarioAcao, Acao acaoComp){
        UsuarioAcaoType usuarioAcaoType = null;
        if(listaUsuarioAcao!=null){
            for (UsuarioAcao usuarioAcao : listaUsuarioAcao) {
                if(acaoComp.getCodigoAcao().equals(usuarioAcao.getAcao().getCodigoAcao())){
                    if(usuarioAcao.getFlagPermitido().toUpperCase().equals(ConstantesMovjud.FLAG_SITUACAO_NAO)){
                        usuarioAcaoType = new UsuarioAcaoType(PermissaoAcaoType.REMOVIDO, usuarioAcao.getAcao());
                        break;
                    }else if(usuarioAcao.getFlagPermitido().toUpperCase().equals(ConstantesMovjud.FLAG_SITUACAO_SIM)){
                         usuarioAcaoType = new UsuarioAcaoType(PermissaoAcaoType.CONCEDIDO, usuarioAcao.getAcao());
                         break;
                     }
                }
            }
        }
        return usuarioAcaoType;
    }

}
