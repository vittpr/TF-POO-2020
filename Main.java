package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        CadastroFuncionarios cadastroFuncionarios = new CadastroFuncionarios();
        CadastroVeiculos cVeiculos = new CadastroVeiculos();
        CadastroFretamentos cFretamentos = new CadastroFretamentos();

        cadastroFuncionarios.cadastrar(new FuncionarioAdminstrativo("Pedro",LocalDate.parse("1995-08-05"),"13910460054"));
        cadastroFuncionarios.cadastrar(new FuncionarioAdminstrativo("Flavio", LocalDate.parse("1998-06-04"),"46817068096"));
        cadastroFuncionarios.cadastrar(new FuncionarioManobrista("Bernardo", LocalDate.parse("2000-02-05"),"26424255095","512315133","C",LocalDate.parse("2022-05-06")));
        cadastroFuncionarios.cadastrar(new FuncionarioMotorista("Ana", LocalDate.parse("1998-12-05"),"69049646000","123156445","D",LocalDate.parse("2024-04-07"),true,true));
        cadastroFuncionarios.cadastrar(new FuncionarioMotorista("Maria",LocalDate.parse("1998-08-05"),"27442982042","989545454", "E", LocalDate.parse("2023-08-05"),true,true));
        cadastroFuncionarios.cadastrar(new FuncionarioMotorista("Mathias",LocalDate.parse("1991-10-14"),"866678800332","189544122", "B", LocalDate.parse("2022-03-20"),false,false));
        cadastroFuncionarios.cadastrar(new FuncionarioMotorista("Julia",LocalDate.parse("1985-04-21"),"45514681009","429518549", "D", LocalDate.parse("2025-04-07"),false,true));

        cVeiculos.cadastrar(new VeiculoPasseio("CRD7769", "Toyota Corolla", 2021, 1500.0));
        cVeiculos.cadastrar(new VeiculoUtilitarios("APO4589", "Hyundai Tucson", 2014, 2100.0));
        cVeiculos.cadastrar(new VeiculoCargas("DNM4480", "FORD F4000", 2008, 2400, 5000, 4, true));
        cVeiculos.cadastrar(new VeiculoCargas("NIM0134", "Iveco DAILY 70 C 16", 2010, 2600, 5000, 4, false));
        cVeiculos.cadastrar(new VeiculoCargas("AQU1234", "Volks 8.120 EURO III", 2015, 3600, 10000, 8, true));
        cVeiculos.cadastrar(new VeiculoCargas("JGY1384", "Truck", 2019, 13000, 28000, 6, false));
        cVeiculos.cadastrar(new VeiculoPassageiros("JYW2362", "Renault Master", 2013, 2450, 15));
        cVeiculos.cadastrar(new VeiculoPassageiros("TOI6872", "Ford Transit", 2020, 3600, 46));
        cVeiculos.cadastrar(new VeiculoPassageiros("JUV5361", "Ford", 2018, 3400, 26));

        Fretamento ftPassageiros = new FretamentoVeiculoPassageiros(1,(VeiculoPassageiros)cVeiculos.getByPlaca("JYW2362"),(FuncionarioMotorista)cadastroFuncionarios.getByCPF("69049646000"),LocalDate.parse("2020-12-05"),LocalDate.parse("2020-12-07"),600);
        Fretamento ftCarga = new FretamentoVeiculoCarga(2,(VeiculoCargas)cVeiculos.getByPlaca("JGY1384"), (FuncionarioMotorista)cadastroFuncionarios.getByCPF("27442982042"), LocalDate.parse("2020-08-05"),LocalDate.parse("2020-08-07"), 300, true);
        cFretamentos.cadastrar(ftCarga);
        cFretamentos.cadastrar(ftPassageiros);

        mostraMenu(cadastroFuncionarios,cVeiculos,cFretamentos);
    }
    public static void solicitaEscolhas(CadastroFuncionarios cadastroFuncionarios,CadastroVeiculos cVeiculos,CadastroFretamentos cFretamentos,int escolha){
        if (escolha < 0 || escolha > 11){
            System.out.println("Opção inválida, tente novamente.");
        } else {
            int x = -1;
            Scanner sc = new Scanner(System.in);
            switch (escolha) {
                case 0:
                    System.out.println("Programa encerrado.");
                    break;
                case 1:
                    while (x != 0){
                        System.out.println("Digite o tipo de funcionário a ser adicionado:");
                        System.out.println("1-Funcionario Administrativo");
                        System.out.println("2-Funcionario Manobrista");
                        System.out.println("3-Funcionario Motorista");
                        System.out.println("0-Escolher outra funcionalidade.");
                        x = sc.nextInt();
                        switch (x) {
                            case 0:
                                x = 0;
                                break;
                            case 1:
                                System.out.println("Digite o nome:");
                                sc.nextLine();
                                String nome = sc.nextLine();
                                System.out.println("Digite a data de nascimento: \nxxxx-xx-xx");
                                String dataNascimento = sc.nextLine();
                                System.out.println("Digite o CPF:");
                                String cpf = sc.nextLine();
                                cadastroFuncionarios.cadastrar(new FuncionarioAdminstrativo(nome, validaData(dataNascimento), cpf));
                                break;
                            case 2:
                                System.out.println("Digite o nome:");
                                sc.nextLine();
                                String nomeMano = sc.nextLine();
                                System.out.println("Digite a data de nascimento: \nxxxx-xx-xx");
                                String dataNascimentoMano = sc.nextLine();
                                System.out.println("Digite o CPF:");
                                String cpfMano = sc.nextLine();
                                System.out.println("Digite o numero da CNH:");
                                String numeroCNH = sc.nextLine();
                                System.out.println("Digite a categoria da CNH: \nB-C-D-E");
                                String categoriaCNH = sc.nextLine();
                                System.out.println("Digite a data de vencimento da CNH: \nxxxx-xx-xx");
                                String dataVencimentoCNH = sc.nextLine();
                                cadastroFuncionarios.cadastrar(new FuncionarioManobrista(nomeMano, validaData(dataNascimentoMano), cpfMano,numeroCNH,validaCNH(categoriaCNH),validaData(dataVencimentoCNH)));
                                break;
                            case 3:
                                System.out.println("Digite o nome:");
                                sc.nextLine();
                                String nomeMoto = sc.nextLine();
                                System.out.println("Digite a data de nascimento: \nxxxx-xx-xx");
                                String dataNascimentoMoto = sc.nextLine();
                                System.out.println("Digite o CPF:");
                                String cpfMoto = sc.nextLine();
                                System.out.println("Digite o numero da CNH:");
                                String numeroCNH_Moto = sc.nextLine();
                                System.out.println("Digite a categoria da CNH: \nB-C-D-E");
                                String categoriaCNH_Moto = sc.nextLine();
                                System.out.println("Digite a data de vencimento da CNH: \nxxxx-xx-xx");
                                String dataVencimentoCNH_Moto = sc.nextLine();
                                System.out.println("Possui curso de carga perigosa? \nSim-Nao");
                                String cargaPerigosa = sc.nextLine();
                                System.out.println("Possui curso de transporte de Passageiros? \nSim-Nao");
                                String tPassageiros = sc.nextLine();
                                cadastroFuncionarios.cadastrar(new FuncionarioMotorista(nomeMoto, validaData(dataNascimentoMoto), cpfMoto,numeroCNH_Moto, Objects.requireNonNull(validaCNH(categoriaCNH_Moto)),validaData(dataVencimentoCNH_Moto),validaCursoCargaPerigosa(cargaPerigosa),validaCursoPassageiros(tPassageiros)));
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 2:
                    cadastroFuncionarios.listarFuncionarios();
                    break;
                case 3:
                    System.out.println("Digite o CPF que deseja buscar:");
                    String cpf3 = sc.nextLine();
                    Funcionario f = cadastroFuncionarios.getByCPF(cpf3);
                    System.out.println(f == null ? "Funcionario não cadastrado." : ""+f.getNome()+" CPF:"+f.getCpf()+" | "+f.getClass().getName());
                    break;
                case 4:
                    while (x != 0){
                        System.out.println("Digite o tipo de veículo a ser adicionado:");
                        System.out.println("1-Veiculo Carga");
                        System.out.println("2-Veiculo Passageiros");
                        System.out.println("3-Veiculo Passeio");
                        System.out.println("4-Veiculo Utilitários");
                        System.out.println("0-Escolher outra funcionalidade.");
                        x = sc.nextInt();
                        switch (x) {
                            case 0:
                                x = 0;
                                break;
                            case 1:
                                System.out.println("Digite a placa:");
                                sc.nextLine();
                                String placa = sc.nextLine();
                                System.out.println("Digite o modelo:");
                                String modelo = sc.nextLine();
                                System.out.println("Digite o ano de fabricação:");
                                int anoFabricacao = sc.nextInt();
                                System.out.println("Digite o peso:");
                                double peso = sc.nextDouble();
                                System.out.println("Digite a capacidade de Carga:");
                                double capacidadeCarga = sc.nextDouble();
                                System.out.println("Digite o numero de Eixos:");
                                int nrEixos = sc.nextInt();
                                System.out.println("Possui unidade acoplada? \nSim-Nao");
                                sc.nextLine();
                                String unidadeAcoplada = sc.nextLine();
                                cVeiculos.cadastrar(new VeiculoCargas(placa, modelo, anoFabricacao, peso, capacidadeCarga, nrEixos, validaUnidadeAcoplada(unidadeAcoplada)));
                                break;
                            case 2:
                                System.out.println("Digite a placa:");
                                sc.nextLine();
                                String placaP = sc.nextLine();
                                System.out.println("Digite o modelo:");
                                String modeloP = sc.nextLine();
                                System.out.println("Digite o ano de fabricação:");
                                int anoFabricacaoP = sc.nextInt();
                                System.out.println("Digite o peso:");
                                double pesoP = sc.nextDouble();
                                System.out.println("Digite a lotação Máxima do veiculo: \n15-26-46");
                                int lotacaoMaxima = sc.nextInt();
                                cVeiculos.cadastrar(new VeiculoPassageiros(placaP, modeloP, anoFabricacaoP, pesoP, validaLotacaoMaxima(lotacaoMaxima)));
                                break;
                            case 3:
                                System.out.println("Digite a placa:");
                                sc.nextLine();
                                String placaPasseio = sc.nextLine();
                                System.out.println("Digite o modelo:");
                                String modeloPasseio = sc.nextLine();
                                System.out.println("Digite o ano de fabricação:");
                                int anoFabricacaoPasseio = sc.nextInt();
                                System.out.println("Digite o peso:");
                                double pesoPasseio = sc.nextDouble();
                                cVeiculos.cadastrar(new VeiculoPasseio(placaPasseio, modeloPasseio, anoFabricacaoPasseio, pesoPasseio));
                                break;
                            case 4:
                                System.out.println("Digite a placa:");
                                sc.nextLine();
                                String placaUtilitarios = sc.nextLine();
                                System.out.println("Digite o modelo:");
                                String modeloUtilitarios = sc.nextLine();
                                System.out.println("Digite o ano de fabricação:");
                                int anoFabricacaoUtilitarios = sc.nextInt();
                                System.out.println("Digite o peso:");
                                double pesoUtilitarios = sc.nextDouble();
                                cVeiculos.cadastrar(new VeiculoUtilitarios(placaUtilitarios, modeloUtilitarios, anoFabricacaoUtilitarios, pesoUtilitarios));
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 5:
                    cVeiculos.listarVeiculos();
                    break;
                case 6:
                    System.out.println("Digite a Placa do veículo que deseja buscar:");
                    String placa = sc.nextLine();
                    Veiculo f1 = cVeiculos.getByPlaca(placa);
                    System.out.println(f1 == null ? "Veiculo não cadastrado." : "Resultado da busca:\n"+f1.getPlaca() +" - "+f1.getModelo()+" | "+f1.getAnoFabricacao()+" | Livre: "+f1.isLivre());
                    break;
                case 7:
                    while (x != 0){
                        System.out.println("Digite o tipo de frete a ser cadastrado:");
                        System.out.println("1-Frete Passageiros");
                        System.out.println("2-Frete Carga");
                        System.out.println("0-Escolher outra funcionalidade.");
                        x = sc.nextInt();
                        switch (x) {
                            case 0:
                                x = 0;
                                break;
                            case 1:
                                cVeiculos.listarVeiculosLivres();
                                System.out.println("Digite a placa do veiculo de passageiros livre:");
                                sc.nextLine();
                                String placa1 = sc.nextLine();
                                cadastroFuncionarios.listarMotoristasLivres();
                                System.out.println("Digite o CPF do motorista com CNH 'D' e Livre:");
                                String cpf1 = sc.nextLine();
                                System.out.println("Digite a data de inicio do frete: \nxxxx-xx-xx");
                                String dataInicio1 = sc.nextLine();
                                System.out.println("Digite a data de termino do frete: \nxxxx-xx-xx");
                                String dataTermino1 = sc.nextLine();
                                System.out.println("Digite a distancia:");
                                double distancia1 = sc.nextDouble();
                                try {
                                    cFretamentos.cadastrar(new FretamentoVeiculoPassageiros((cFretamentos.getNumeroFretes()+1),((VeiculoPassageiros)cVeiculos.getByPlaca(placa1)),(FuncionarioMotorista)cadastroFuncionarios.getByCPF(cpf1), validaData(dataInicio1), validaData(dataTermino1), distancia1));
                                } catch (Exception e) {
                                    System.out.println("Erro. Condutor deve estar devidamente habilitado para conduzir este veiculo.\nTente novamente.");
                                    x = -1;
                                }

                                break;
                            case 2:
                                cVeiculos.listarVeiculosLivres();
                                System.out.println("Digite a placa do veiculo de Carga livre:");
                                sc.nextLine();
                                String placa2 = sc.nextLine();
                                cadastroFuncionarios.listarMotoristasLivres();
                                System.out.println("Digite o CPF do motorista com CNH Adequado e Livre:");
                                String cpf2 = sc.nextLine();
                                System.out.println("Digite a data de inicio do frete: \nxxxx-xx-xx");
                                String dataInicio2 = sc.nextLine();
                                System.out.println("Digite a data de termino do frete: \nxxxx-xx-xx");
                                String dataTermino2 = sc.nextLine();
                                System.out.println("Digite a distancia:");
                                double distancia2 = sc.nextDouble();
                                System.out.println("Carga perigosa? \nSim-Nao");
                                sc.nextLine();
                                String is_cargaPerigosa = sc.nextLine();
                                try {
                                    cFretamentos.cadastrar(new FretamentoVeiculoCarga((cFretamentos.getNumeroFretes()+1),(VeiculoCargas)cVeiculos.getByPlaca(placa2),(FuncionarioMotorista)cadastroFuncionarios.getByCPF(cpf2),validaData(dataInicio2),validaData(dataTermino2), distancia2, validaIsCargaPerigosa(is_cargaPerigosa)));
                                } catch (Exception e) {
                                    System.out.println("ERRO!. Condutor deve estar devidamente habilitado para conduzir este veiculo.Tente novamente.\n");
                                    x = -1;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 8:
                    cadastroFuncionarios.listarMotoristasLivres();
                    break;
                case 9:
                    cVeiculos.listarVeiculosLivres();
                    break;
                case 10:
                    cFretamentos.listarHistoricoFretamentos();
                    break;
                case 11:
                    cFretamentos.cincoMaisLucrativos();
                    break;
                default:
                    break;
            }
        }
    }

    public static void mostraMenu(CadastroFuncionarios cadastroFuncionarios,CadastroVeiculos cVeiculos,CadastroFretamentos cFretamentos){
        int escolha = -1;
        Scanner sc = new Scanner(System.in);
        while (escolha != 0){
            System.out.println("\nEscolha uma das funcionalidades:");
            System.out.println("1-Cadastrar funcionário");
            System.out.println("2-Listar funcionários em ordem alfabética");
            System.out.println("3-Buscar funcionário por CPF");
            System.out.println("4-Cadastrar veículo");
            System.out.println("5-Listar veículos em ordem de ano de fabricação");
            System.out.println("6-Buscar veículo por placa");
            System.out.println("7-Cadastrar fretamento de veículo");
            System.out.println("8-Listar motoristas Livres");
            System.out.println("9-Listar veículos livres");
            System.out.println("10-Listar histórico de fretamentos");
            System.out.println("11-Listar top 5 veículos mais lucrativos");
            System.out.println("0-Finalizar programa.");
            escolha = sc.nextInt();
            solicitaEscolhas(cadastroFuncionarios,cVeiculos,cFretamentos,escolha);
        }
    }

    public static int validaLotacaoMaxima(int lotacaoMaxima){
        if ((lotacaoMaxima == 15) || (lotacaoMaxima == 26) || (lotacaoMaxima == 46)){
            return lotacaoMaxima;
        } else{
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Erro de digitação. Lotação máxima deve ser de 15, 26 ou 46.\nTente novamente:");
                System.out.println("Digite a lotação Máxima do veiculo: \n15-26-46");
                lotacaoMaxima = sc.nextInt();
                if ((lotacaoMaxima == 15) || (lotacaoMaxima == 26) || (lotacaoMaxima == 46)){
                    return lotacaoMaxima;
                }
            }
        }
    }


    public static boolean validaIsCargaPerigosa(String escolha){
        if (escolha.equalsIgnoreCase("sim")){
            return true;
        } else if(escolha.equalsIgnoreCase("nao")){
            return false;
        } else{
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Erro de digitação. Digite apenas Sim ou Nao!.\nTente novamente:");
                System.out.println("Carga perigosa? \nSim-Nao");
                String is_CargaPerigosa = sc.nextLine();
                if (is_CargaPerigosa.equalsIgnoreCase("sim")){
                    return true;
                } else if(is_CargaPerigosa.equalsIgnoreCase("nao")){
                    return false;
                }
            }
        }

    }
    public static boolean validaCursoPassageiros(String escolha){
        if (escolha.equalsIgnoreCase("sim")){
            return true;
        } else if(escolha.equalsIgnoreCase("nao")){
            return false;
        } else{
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Erro de digitação. Digite apenas Sim ou Nao.\nTente novamente:");
                System.out.println("Possui curso de transporte de Passageiros? \nSim-Nao");
                String cursoPassageiros = sc.nextLine();
                if (cursoPassageiros.equalsIgnoreCase("sim")){
                    return true;
                } else if(cursoPassageiros.equalsIgnoreCase("nao")){
                    return false;
                }
            }
        }

    }

    public static boolean validaCursoCargaPerigosa(String escolha){
        if (escolha.equalsIgnoreCase("sim")){
            return true;
        } else if(escolha.equalsIgnoreCase("nao")){
            return false;
        } else{
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Erro de digitação. Digite apenas Sim ou Nao.\nTente novamente:");
                System.out.println("Possui curso de carga perigosa? \nSim-Nao");
                String cargaPerigosa = sc.nextLine();
                if (cargaPerigosa.equalsIgnoreCase("sim")){
                    return true;
                } else if(cargaPerigosa.equalsIgnoreCase("nao")){
                    return false;
                }
            }
        }

    }
    public static boolean validaUnidadeAcoplada(String escolha){
        if (escolha.equalsIgnoreCase("sim")){
            return true;
        } else if(escolha.equalsIgnoreCase("nao")){
            return false;
        } else{
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Erro de digitação. Digite apenas Sim ou Nao.\nTente novamente:");
                System.out.println("Possui unidade acoplada? \nSim-Nao");
                String unidadeAcoplada = sc.nextLine();
                if (unidadeAcoplada.equalsIgnoreCase("sim")){
                    return true;
                } else if(unidadeAcoplada.equalsIgnoreCase("nao")){
                    return false;
                }
            }
        }
    }

    public static String validaCNH(String categoriaCNH){
        if ((categoriaCNH.equalsIgnoreCase("b"))
                || (categoriaCNH.equalsIgnoreCase("c"))
                || (categoriaCNH.equalsIgnoreCase("d"))
                || (categoriaCNH.equalsIgnoreCase("e"))){
            return categoriaCNH;
        } else {
            while(true){
                Scanner sc = new Scanner(System.in);
                System.out.println("Categoria de CNH inválida. Digite uma categria entre B,C,D ou E:");
                String escolha = sc.nextLine();
                if (escolha.equalsIgnoreCase("b") || escolha.equalsIgnoreCase("c") ||escolha.equalsIgnoreCase("d") ||escolha.equalsIgnoreCase("e")){
                    return escolha;
                }
            }
        }
    }

    public static LocalDate validaData(String data){
        try {
            return LocalDate.parse(data);
        } catch (DateTimeParseException e) {
            while(true){
                Scanner sc = new Scanner(System.in);
                System.out.println("Erro!Formato de data inválido.\nDigite utilizando o padrão ANO-MÊS-DIA \nxxxx-xx-xx):");
                String newData = sc.nextLine();
                if (matches(newData)){
                    return LocalDate.parse(newData);
                }

            }
        }
    }

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^\\d{4}-\\d{2}-\\d{2}$");

    public static boolean matches(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }


}