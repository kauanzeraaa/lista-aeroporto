import java.util.Scanner;

public class Run {
    public static void runner() throws Exception{
        Aeroporto[] aero = new Aeroporto[100];
        Scanner ler = new Scanner(System.in);
        String s, no, c;
        String opcao ="";
        int n = 5, cv;  
        String inicial, f;          
        
        aero[0] = new Aeroporto("BSB", "Juscelino", "Brasília");
        aero[1] = new Aeroporto("CNF", "Confins","Belo Horizonte");
        aero[2] = new Aeroporto("GIG", "Tom Jobim", "Rio de Janeiro");
        aero[3] = new Aeroporto("GRU", "Guarulhos", "São Paulo");
        aero[4] = new Aeroporto("SSA", "Salvador Airport", "Salvador");

        aero[0].addVoo(new Voo("SSA", 107));
        aero[1].addVoo(new Voo("SSA", 214));
        aero[1].addVoo(new Voo("GIG", 555));
        aero[1].addVoo(new Voo("GRU", 101));
        aero[2].addVoo(new Voo("CNF", 554));
        aero[2].addVoo(new Voo("GRU", 900));
        aero[3].addVoo(new Voo("BSB", 500));
        aero[3].addVoo(new Voo("GIG", 890));
        aero[3].addVoo(new Voo("CNF", 102));
        aero[4].addVoo(new Voo("CNF", 215));

        // aero[1].addVoo(new Voo(4, 214));
        // aero[1].addVoo(new Voo(2, 555));
        // aero[1].addVoo(new Voo(3, 101));
        // System.out.println(aero[0]);            
        
        for(;;) {
            System.out.println("\n--- CADASTRAMENTO DE AEROPORTOS E VOOS ---");
            System.out.println("\n• Cadastramento de um novo aeroporto (CA)");
            System.out.println("• Cadastramento de um Vôo (CV)");
            System.out.println("• Remoção de um vôo (RV)");
            System.out.println("• Listagem de Vôos (LV)");
            System.out.println("• Sair (S)");
            System.out.print("\nSolicite uma das opções abaixo a partir da sigla: ");
            opcao = ler.nextLine();
            switch(opcao.toUpperCase()){
                
                //1-Cadastramento de um novo aeroporto
                case "CA":
                    System.out.print("\nDigite o código do aeroporto (Ex: CNF): ");
                    s = ler.nextLine().toUpperCase();
                    System.out.print("\nDigite o nome do aeroporto (Ex: Confins): ");
                    no = ler.nextLine();
                    System.out.print("\nDigite a cidade do aeroporto (Ex: Belo Horizonte): ");
                    c = ler.nextLine();
                    int indexRepCod = 0, indexRepNome = 0;
                    for(int i = 0; i < aero.length; i++) {
                        if(aero[i] == null)
                            break;

                        if(aero[i].getCod().equals(s)) {
                           indexRepCod++;
                           System.err.println("\nCódigo do aeroporto já existe");
                            break;
                        }
                        if(aero[i].getNome().equals(no))  {
                            System.err.println("\nNome do aeroporto já existe");
                            indexRepNome++;
                            break;
                        }
                    }
                    if(indexRepCod == 0 && indexRepNome ==0) {
                        aero[n] = new Aeroporto(s, no, c);
                        System.out.println("Aeroporto adicionado!");
                        n++;
                    }
                    continue;

                //2-Cadastramento de um vôo com um determinado número entre dois aeroportos identificados pelos seus códigos;
                case "CV":
                    System.out.print("\nDigite o código do aeroporto inicial: ");
                    inicial = ler.nextLine().toUpperCase();
                    System.out.print("\nDigite o código do aeroporto final: ");
                    f = ler.nextLine().toUpperCase();
                    System.out.print("\nDigite o código do vôo: ");
                    cv = Integer.parseInt(ler.nextLine());
                    int indexI = -1, indexF = -1;
                    for(int i = 0; i<aero.length; i++) {
                        if(aero[i] ==null) {
                            if(indexI == -1 && indexF == -1) {
                                System.err.println("\nAeroportos inválidos");
                                break;
                            }
                            if(indexI == -1) {
                                System.err.println("\nAeroporto de saída inválido");
                                break;
                            }
                            if(indexF == -1)
                                System.err.println("\nAeroporto de chegada inválido");
                            break;
                        }
                        if(aero[i].getCod().equals(inicial)) {
                            indexI = i;
                        }
                         
                        if(aero[i].getCod().equals(f)) {
                            indexF = i;
                        }

                    }                     
                    boolean temRep = false;  
                    if(indexI != -1 && indexF != -1) {
                        for(int i = 0; i < aero.length; i++) {
                            if(aero[i] == null)
                                break;
                            for(int x = 0; x < aero[i].getVoos().getQuantidade(); x++) {
                                Voo v = (Voo)aero[i].getVoos().getElemento(x);
                                if((v.getCodVoo() == cv)) {
                                    System.err.println("\nCódigo de vôo já existente!");
                                    temRep = true;
                                    break;
                                }
                            }
                            if(temRep == true)
                                break;
                        }
                        if(!temRep) {
                            Voo voozinho = new Voo(f, cv);
                            if (!aero[indexI].temVoo(voozinho)) {
                                aero[indexI].addVoo(voozinho);
                                System.out.println("\nVôo inserido");
                            }
                            else {
                                System.out.println("\nCódigo de voo já existente");
                            }
                        }
                    }
                    continue;

                //3-Remoção de um vôo indicado pelo número;
                case "RV":
                    System.out.print("\nDigite o código do vôo para remoção: ");
                    cv = Integer.parseInt(ler.nextLine());
                    for(int i = 0; i<aero.length; i++) {
                        
                        if(aero[i] == null) {
                            System.err.println("\nVôo inexistente!");
                            break;
                        }
                        try {
                            aero[i].removeVooIndicado(aero[i].getVooById(cv));
                            System.out.println("\nVôo removido!");
                            break;                           
                        } catch(Exception e) { }                      
                    }
                    continue;

                // 4-Listagem na tela de todos os vôos (número e nome da cidade destino) que saem de um determinado aeroporto.
                case "LV":
                    System.out.print("\nDigite a sigla do aeroporto: ");
                    s = ler.nextLine().toUpperCase();
                    String resp = "";
                    int index = -1;
                    for(int i = 0; i<aero.length; i++) {
                        if(aero[i] == null) {
                            System.err.println("\nVoo inexistente!");
                            break;
                        }
                        
                        if(aero[i].getCod().equals(s)) {
                            index = i;
                            break;                               
                        }
                    }
                    if(index >-1) {
                        resp = "\nCódigo: " + aero[index].getCod()+" - Nome: "+aero[index].getNome()+
                        " - Cidade: "+aero[index].getCidade()+"; Vôos: [";
                        for(int l = 0; l < aero[index].getVoos().getQuantidade(); l++) {
                            Voo v = (Voo)aero[index].getVoos().getElemento(l);
                            
                            resp+= " Número do Vôo: "+v.getCodVoo();
                            for(int x = 0; x < aero.length; x++) {
                                if (aero[x].getCod().equals(v.getIndiceAeroDist())) {
                                    resp += ", Cidade destino: " + aero[x].getCidade();
                                    break;
                                }
                            }
                            if(l != 0 || l != aero[index].getVoos().getQuantidade() - 1)
                                resp += " | ";
                        }
                        resp += " ]";
                        System.out.println(resp);
                    }
                    continue;
                
                case "S":
                    ler.close();
                    System.out.println("\nPrograma encerrado!");
                    System.exit(0);
                default:
                    System.err.println("\nDigite uma das siglas!");
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
            }
        }
    }
}
