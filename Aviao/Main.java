import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            //funcionalidades:
            
            Aeroporto[] aero = new Aeroporto[100];//quantidade de aeroportos 
            Scanner ler = new Scanner(System.in);
            String s, c;
            String opcao ="";
            int n = 0, inicial, f, cv;            
            
            aero[0] = new Aeroporto("BSB","Brasília");
            aero[1] = new Aeroporto("CNF","Belo Horizonte");
            // aero[2] = new Aeroporto("GIG","Rio de Janeiro");
            // aero[3] = new Aeroporto("GRU","São Paulo");
            // aero[4] = new Aeroporto("SSA","Salvador");
            aero[0].addVoo(new Voo(4, 107));
            // aero[1].addVoo(new Voo(4, 214));
            // aero[1].addVoo(new Voo(2, 555));
            // aero[1].addVoo(new Voo(3, 101));
            // System.out.println(aero[0]);            
            
            for(;;) {
                System.out.println("Solicite uma das opções abaixo: ");
                System.out.println("Cadastramento de um novo aeroporto(CA)");
                System.out.println("Cadastramento de um Vôo(CV)");
                System.out.println("Remoção de um vôo(RV)");
                System.out.println("Listagem de Vôos(LV)");
                System.out.println("Sair(S)");
                opcao = ler.nextLine();
                switch(opcao.toUpperCase()){
                    //1-Cadastramento de um novo aeroporto
                    case "CA":
                        System.out.println("Digite a sigla do aeroporto:(Ex: BSB)");
                        s = ler.nextLine();
                        System.out.println("Digite a cidade do aeroporto:");
                        c = ler.nextLine();
                        aero[n] = new Aeroporto(s, c);
                        System.out.println(aero[n]);
                        n++;
                        System.out.println("\n");
                        continue;
                    //2-Cadastramento de um vôo com um determinado número entre dois aeroportos identificados pelos seus códigos;
                    case "CV":
                        System.out.println("Digite o índice do aeroporto inicial:");
                        inicial = Integer.parseInt(ler.nextLine());
                        System.out.println("Digite o índice do aeroporto final");
                        f = Integer.parseInt(ler.nextLine());
                        System.out.println("Digite o código do vôo:");
                        cv = Integer.parseInt(ler.nextLine());
                        if(aero[inicial] == null) {
                            System.err.println("Aeroporto inexistente");
                            break;
                        }
                        if(aero[f] == null) {
                            System.err.println("Aeroporto inexistente");
                            break;
                        }
                        aero[inicial].addVoo(new Voo(f, cv));
                        System.out.println("\n");
                        continue;
                    //3-Remoção de um vôo indicado pelo número;
                    case "RV":
                        System.out.println("Digite o código do vôo para remoção:");
                        cv = Integer.parseInt(ler.nextLine());
                        for (int i = 0; i < aero.length; i++) {
                            Voo v;
                            if (aero[i] == null) {
                                System.err.println("Vôo inexistente");
                                break;
                            }
                            v = new Voo(i, cv); // Correção: usar o índice do aeroporto atual (i)
                            if (aero[i].temVoo(v)) {
                                aero[i].removeVooIndicado(v);
                                break;
                            }                            
                        }
                        System.out.println("\n");
                        continue;
                    //4-Listagem na tela de todos os vôos (número e nome da cidade destino) que saem de um determinado aeroporto.
                    case "LV":
                        System.out.println("Digite a sigla do aeroporto para ver os seus vôos:");
                        s = ler.nextLine();
                        for(int i = 0; i<aero.length; i++) {
                            if(aero[i] == null) {
                                System.err.println("Aeroporto inexistente");
                            }
                            if(aero[i].getSigla().equals(s)) {
                                System.out.println(aero[i]);
                                break;
                            }
                        }
                        System.out.println("\n");
                        continue;
                    
                    case "S":
                        ler.close();
                        System.out.println("Programa encerrado");
                        System.exit(0);
                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}