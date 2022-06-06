package com.company;

import java.util.Collections;
import java.util.LinkedList;

public class CadastroFretamentos{
    private final LinkedList<Fretamento> fretamentosAtivos;
    private final LinkedList<Fretamento> fretamentosHistorico;

    public CadastroFretamentos() {
        this.fretamentosAtivos = new LinkedList<>();
        this.fretamentosHistorico = new LinkedList<>();
    }

    public int getNumeroFretes(){
        return fretamentosHistorico.size();
    }
    public void cadastrar(Fretamento frete){
        if (frete != null){
            this.fretamentosAtivos.add(frete);
            this.fretamentosHistorico.add(frete);
        }
    }

    public void listarHistoricoFretamentos(){
        System.out.println("Histórico de Fretes:");
        fretamentosHistorico.forEach(frete -> {
            System.out.println("\tFrete ID: "+ frete.getId()+ " | Placa do veiculo:"+frete.getVeiculo().getPlaca()+" | CPF Condutor:"+frete.getCondutor().getCpf());
            System.out.println("\t\tData de inicio:" + frete.getDataInicio()+" | Data Termino:"+frete.getDataTermino()+" | Valor: R$"+frete.getValor()+"\n");
        });
    }


    public void cincoMaisLucrativos(){
        System.out.println("\nTop 5 Fretes mais Lucrativos:");
        fretamentosHistorico.stream()
                .map(f -> f.getValor() + " ID:"+f.getId())
                .sorted(Collections.reverseOrder())
                .limit(5)
                .forEach(f -> System.out.println("R$"+f));
    }



}