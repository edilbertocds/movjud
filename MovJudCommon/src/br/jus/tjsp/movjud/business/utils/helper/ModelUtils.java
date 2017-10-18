package br.jus.tjsp.movjud.business.utils.helper;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelUtils{

    public static <T extends Serializable> List<ItemSelecionado<T>> toListaSelecionados(List<T> listaCompleta, List<T> listaParcial) {
        List<ItemSelecionado<T>> listaSelecionados = new ArrayList<ItemSelecionado<T>>();
        
        for(T model :listaCompleta) {
            ItemSelecionado<T> item = new ItemSelecionado<T>();
            item.setModel(model);
            item.setMarcado(listaParcial.contains(model));
          
            listaSelecionados.add(item);
        }
                  
        return listaSelecionados;
    }
    
    public static <T extends Serializable> List<T> toListaModels(List<ItemSelecionado<T>> listaSelecionados) {
        List<T> listaModel = new ArrayList<T>();
           
        for(ItemSelecionado<T> item : listaSelecionados) {
            if(item.isMarcado()){
                listaModel.add(item.getModel());    
            }
        }  
       
        return listaModel;
    }
    
    public static <T extends Serializable> List<T> diffListasModel(List<T> listaAnterior, List<T> listaAtual) {
        List<T> listaDiffModel = new ArrayList<T>();
        
        for(T item: listaAtual) {
            if(!listaAnterior.contains(item)) {
                listaDiffModel.add(item);    
            }
        }
           
        return listaDiffModel;
    }
    
    public static String formatarDataToStr(Date data) {
        return formatarDataToStr(data, "dd/MM/yyyy - HH:mm:ss");
    }
    
    public static String formatarDataToStr(Date data, String mascara) {
        SimpleDateFormat sdf = new SimpleDateFormat(mascara);     
        String dataStr = sdf.format(data);   
        return dataStr;
    }
    
    public static String formatarComEspacosEsquerda(int numEspacos, String strOriginal) {
        return formatarComEspacos(numEspacos, strOriginal, true);
    }
    
    public static String formatarComEspacosDireita(int numEspacos, String strOriginal) {
        return formatarComEspacos(numEspacos, strOriginal, false);
    }
    
    private static String formatarComEspacos(int numEspacos, String strOriginal, boolean ladoEsquerdo){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < numEspacos; i++){
            sb.append(" ");
        }
        return ladoEsquerdo ? sb.toString() + strOriginal : strOriginal + sb.toString();
    }
    
    public static void main (String args[]) {
        List<String> listaStrAnterior = new ArrayList<String>();
        listaStrAnterior.add("Str1"); 
        listaStrAnterior.add("Str2"); 
        listaStrAnterior.add("Str3");         
        
        List<String> listaStrAtual = new ArrayList<String>();
        listaStrAtual.add("Str1"); 
        listaStrAtual.add("Str2"); 
        listaStrAtual.add("Str3");
        
        System.out.println("--------------------------------------");
        System.out.println("listaStrAnterior ");
        System.out.println("--------------------------------------");
        
        for(String item: listaStrAnterior) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------");
        
        System.out.println("--------------------------------------");
        System.out.println("listaStrAtual");
        System.out.println("--------------------------------------");
        
        for(String item: listaStrAtual) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------");
        
        System.out.println("--------------------------------------");
        System.out.println("Diff Inserir");
        System.out.println("--------------------------------------");
        List<String> listaDiffInserir = diffListasModel(listaStrAnterior, listaStrAtual);
       
        for(String item: listaDiffInserir) {
            System.out.println("Inserir: " + item);
        }
        System.out.println("--------------------------------------");
        
        
        System.out.println("--------------------------------------");
        System.out.println("Diff Deletar");
        System.out.println("--------------------------------------");
        List<String> listaDiffDeletar = diffListasModel(listaStrAtual, listaStrAnterior);
        for(String item: listaDiffDeletar) {
            System.out.println("Deletar: " + item);
        }
        System.out.println("--------------------------------------");

    }
}
