package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class FretamentoVeiculoPassageiros extends Fretamento{

    public FretamentoVeiculoPassageiros(int id, VeiculoPassageiros veiculo, FuncionarioMotorista condutor, LocalDate dataInicio, LocalDate dataTermino, double distancia) {
        super(id,veiculo, condutor, dataInicio, dataTermino, distancia);
        this.calculaValor(veiculo,this);
        System.out.println("Frete de Passageiros cadastrado com sucesso!");
    }

    @Override
    void calculaValor(Veiculo veiculo,Fretamento frete) {
        double total = 0;

        VeiculoPassageiros v = (VeiculoPassageiros)veiculo;

        if (v.getLotacaoMaxima() == 15){
            total = total + (410 * (ChronoUnit.DAYS.between(frete.getDataInicio(),frete.getDataTermino()))) + (getDistancia() * 2.20);
            this.setValor(total);
        } else if (v.getLotacaoMaxima() == 26){
            total = total + (490 * (ChronoUnit.DAYS.between(this.getDataInicio(),this.getDataTermino()))) + (this.getDistancia() * 2.80);
            this.setValor(total);
        } else {
            total = total + (560 * (ChronoUnit.DAYS.between(this.getDataInicio(),this.getDataTermino()))) + (this.getDistancia() * 3);
            this.setValor(total);
        }
    }
}