import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            //funcionalidades:
            
            Aeroporto[] aero = new Aeroporto[100];//quantidade de aeroportos 
            Scanner ler = new Scanner(System.in);
            String s, no, c;
            String opcao ="";
            int n = 0, cv;  
            String inicial, f;          
            
            aero[0] = new Aeroporto("BSB", "Juscelino", "Brasília");
            aero[1] = new Aeroporto("CNF", "Confins","Belo Horizonte");
            // aero[2] = new Aeroporto("GIG","Rio de Janeiro");
            // aero[3] = new Aeroporto("GRU","São Paulo");
            // aero[4] = new Aeroporto("SSA","Salvador");
            aero[0].addVoo(new Voo("CNF", 107));
            // aero[1].addVoo(new Voo(4, 214));
            // aero[1].addVoo(new Voo(2, 555));
            // aero[1].addVoo(new Voo(3, 101));
            // System.out.println(aero[0]);            
            
            for(;;) {
                System.out.println("\nSolicite uma das opções abaixo: ");
                System.out.println("\nCadastramento de um novo aeroporto(CA)");
                System.out.println("Cadastramento de um Vôo(CV)");
                System.out.println("Remoção de um vôo(RV)");
                System.out.println("Listagem de Vôos(LV)");
                System.out.println("Sair(S)");
                System.out.println("");
                opcao = ler.nextLine();
                switch(opcao.toUpperCase()){
                    
                    //1-Cadastramento de um novo aeroporto
                    case "CA":
                        System.out.println("Digite a sigla do aeroporto (Ex: CNF): ");
                        s = ler.nextLine();
                        System.out.println("Digite o nome do aeroporto (Confins): ");
                        no = ler.nextLine();
                        System.out.println("Digite a cidade do aeroporto (Belo Horizonte): ");
                        c = ler.nextLine();
                        aero[n] = new Aeroporto(s, no, c);
                        System.out.println(aero[n]);
                        n++;
                        System.out.println("\n");
                        continue;

                    //2-Cadastramento de um vôo com um determinado número entre dois aeroportos identificados pelos seus códigos;
                    case "CV":
                        System.out.println("Digite o índice do aeroporto inicial: ");
                        inicial = ler.nextLine();
                        System.out.println("Digite o índice do aeroporto final: ");
                        f = ler.nextLine();
                        System.out.println("Digite o código do vôo: ");
                        cv = Integer.parseInt(ler.nextLine());
                        for(int i = 0; i<aero.length; i++) {
                            if(aero[i].getCod() == inicial) {
                                System.err.println("Aeroporto inexistente!");
                                break;
                            }
                            if(aero[i] == null) {
                                System.err.println("Aeroporto inexistente!");
                                break;
                            }
                            
                            aero[i].addVoo(new Voo(f, cv));
                        }
                        System.out.println("\n");
                        continue;

                    //3-Remoção de um vôo indicado pelo número;
                    case "RV":
                        System.out.println("Digite o código do vôo para remoção: ");
                        cv = Integer.parseInt(ler.nextLine());
                        for(int i = 0; i<aero.length; i++) {
                            
                            if(aero[i] == null) {
                                System.err.println("Vôo inexistente!");
                                break;
                            }
                            try {
                                aero[i].removeVooIndicado(aero[i].getVooById(cv));
                                System.out.println("\n Vôo removido!");
                                break;                           
                            } catch(Exception e) { e.printStackTrace(); }                      
                        }
                        System.out.println("\n");
                        continue;

                    //4-Listagem na tela de todos os vôos (número e nome da cidade destino) que saem de um determinado aeroporto.
                    case "LV":
                        System.out.println("Digite a sigla do aeroporto para ver os seus vôos:");
                        s = ler.nextLine().toUpperCase();
                        for(int i = 0; i<aero.length; i++) {
                            if(aero[i] == null) {
                                System.err.println("Aeroporto inexistente");
                            }
                            
                            if(aero[i].getSigla().equals(s)) {
                                // for(int index = 0; index< aero[i].getVoos().getQuantidade(); index++) {
                                //     try {
                                //     Voo v = (Voo) aero[i].getVoos().getElemento(index);
                                //     System.out.println(aero[v.getIndiceAeroDist()].getCidade()+""+v.getCodVoo());
                                //     break;
                                //     } catch(Exception e) {}
                                // }
                                
                                System.out.println(aero[i]);
                                break;
                            }
                        }
                        System.out.println("\n");
                        continue;
                    
                    case "S":
                        ler.close();
                        System.out.println("Programa encerrado!");
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}