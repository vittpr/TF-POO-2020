package com.company;

import java.time.LocalDate;

public abstract class Fretamento {
    private final int id;
    private Veiculo veiculo;
    private final Funcionario condutor;
    private final LocalDate dataInicio;
    private final LocalDate dataTermino;
    private final double distancia;
    private double valor;

    public Fretamento(int id,Veiculo veiculo, FuncionarioMotorista condutor, LocalDate dataInicio, LocalDate dataTermino, double distancia){
        if (validaCondutor(veiculo, condutor)){
            this.id = id;
            this.veiculo = veiculo;
            this.condutor = condutor;
            this.dataInicio = dataInicio;
            this.dataTermino = dataTermino;
            this.distancia = distancia;
            veiculo.setLivre(false);
            condutor.setOcupado(true);
        } else throw new IllegalArgumentException();
    }

    private boolean validaCondutor(Veiculo veiculo, FuncionarioMotorista condutor){
        if (veiculo instanceof VeiculoPassageiros){
            return condutor.getCategoriaCNH().equals("D") && (condutor.cursoTransportePassageiros());
        } else if (veiculo instanceof VeiculoCargas){
            VeiculoCargas vc = (VeiculoCargas)veiculo;

            if (veiculo.getPeso() <= 3500){
                return true;
            } else if (veiculo.getPeso() > 3500){
                return condutor.getCategoriaCNH().equalsIgnoreCase("C") || condutor.getCategoriaCNH().equalsIgnoreCase("D") || condutor.getCategoriaCNH().equalsIgnoreCase("E");
            } else if ((vc.unidadeAcoplada()) && (vc.getPeso() >= 6000)){
                return condutor.getCategoriaCNH().equalsIgnoreCase("E");
            } else return false;
        } else return false;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Funcionario getCondutor() {
        return condutor;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getValor() {
        return valor;
    }

    protected void setValor(double valor){
        this.valor = valor;
    }

    abstract void calculaValor(Veiculo v,Fretamento f);

    public int getId(){
        return this.id;
    }

}