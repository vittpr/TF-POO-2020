package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class CadastroVeiculos {
    private final LinkedList<Veiculo> veiculos;

    public CadastroVeiculos() {
        this.veiculos = new LinkedList<>();
    }

    public void cadastrar(Veiculo veiculo){
        if(veiculo != null){
            this.veiculos.add(veiculo);
        }
    }


    public void listarVeiculos(){
        LinkedList<Veiculo> temp;
        temp = veiculos;
        Collections.sort(temp);

        System.out.println("\nVeiculos em ordem por ano de fabricação:");
        for (Veiculo v : temp) {
            if (v instanceof VeiculoCargas){
                VeiculoCargas aux = (VeiculoCargas)v;
                System.out.println("\n\t"+"Ano: "+v.getAnoFabricacao()+" | Modelo: "+v.getModelo()+" | Tipo: "+v.getClass().getName()+" | Placa: "+v.getPlaca()+" | Capacidade Carga: "+aux.getCapacidadeCarga());
            } else if (v instanceof VeiculoPassageiros){
                VeiculoPassageiros aux = (VeiculoPassageiros)v;
                System.out.println("\n\t"+"Ano: "+v.getAnoFabricacao()+" | Modelo: "+v.getModelo()+" | Tipo: "+v.getClass().getName()+" | Placa: "+v.getPlaca()+" | Lotação Máxima: "+aux.getLotacaoMaxima());
            } else {
                System.out.println("\n\t"+"Ano: "+v.getAnoFabricacao()+" | Modelo: "+v.getModelo()+" | Tipo: "+v.getClass().getName()+" | Placa: "+v.getPlaca());
            }
        }


    }

    public Veiculo getByPlaca(String placa){
        Veiculo temp = null;
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)){
                temp = v;
            }
        }
        if (temp == null){
            Scanner sc = new Scanner(System.in);
            System.out.println("Veiculo não cadastrado. Digite uma placa para busca:");
            this.listarVeiculos();
            String placa1 = sc.nextLine();

            return getByPlaca(placa1);
        } else {
            return temp;
        }
    }

    public void listarVeiculosLivres(){
        System.out.println("\nVeiculos Livres:");
        veiculos.stream()
                .filter(Veiculo::isLivre)
                .forEach(vL -> {
                    if (vL instanceof VeiculoCargas){
                        VeiculoCargas temp = (VeiculoCargas)vL;
                        System.out.println("\n\tVeiculo:"+vL.getModelo()+" | Tipo: "+vL.getClass().getName()+" | Placa: "+vL.getPlaca()+" | Peso: "+temp.getPeso()+" | Capacidade Carga: "+temp.getCapacidadeCarga());
                    } else if (vL instanceof VeiculoPassageiros){
                        VeiculoPassageiros temp = (VeiculoPassageiros)vL;
                        System.out.println("\n\tVeiculo:"+vL.getModelo()+" | Tipo: "+vL.getClass().getName()+" | Placa: "+vL.getPlaca()+" | Lotação Máxima: "+temp.getLotacaoMaxima());
                    } else {
                        System.out.println("\n\tVeiculo:"+vL.getModelo()+" | Tipo: "+vL.getClass().getName()+" | Placa: "+vL.getPlaca());
                    }

                });

    }
}
