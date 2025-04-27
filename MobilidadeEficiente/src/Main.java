
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sistema s = new Sistema();
        int op;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gerenciar Linhas");
            System.out.println("2. Gerenciar Ônibus");
            System.out.println("3. Gerenciar Viagens");
            System.out.println("4. Sair");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());
            switch(op) {
                case 1: menuLinhas(s, sc); break;
                case 2: menuOnibus(s, sc); break;
                case 3: menuViagens(s, sc); break;
                case 4: System.out.println("Encerrando..."); break;
                default: System.out.println("Inválido!");
            }
        } while(op!=4);
    }

    static void menuLinhas(Sistema s, Scanner sc) {
        int op;
        do {
            System.out.println("\n-- GERENCIAR LINHAS --");
            System.out.println("1. Cadastrar Linha");
            System.out.println("2. Listar Linhas");
            System.out.println("3. Alterar Linha");
            System.out.println("4. Remover Linha");
            System.out.println("5. Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());
            switch(op) {
                case 1:
                    System.out.print("Código: ");   String c = sc.nextLine();
                    System.out.print("Nome: ");     String n = sc.nextLine();
                    Linha l = new Linha(c,n);
                    System.out.print("Quantas paradas? "); int q = Integer.parseInt(sc.nextLine());
                    for(int i=0;i<q;i++){
                        System.out.print("Parada "+(i+1)+": ");
                        l.adicionarParada(sc.nextLine(), i);
                    }
                    s.cadastrarLinha(l);
                    break;
                case 2: s.listarLinhas(); break;
                case 3:
                    System.out.print("Código da linha a alterar: ");
                    s.alterarLinha(sc.nextLine(), sc);
                    break;
                case 4:
                    System.out.print("Código da linha a remover: ");
                    s.removerLinha(sc.nextLine());
                    break;
                case 5: break;
                default: System.out.println("Inválido!");
            }
        } while(op!=5);
    }

    static void menuOnibus(Sistema s, Scanner sc) {
        int op;
        do {
            System.out.println("\n-- GERENCIAR ÔNIBUS --");
            System.out.println("1. Cadastrar Ônibus");
            System.out.println("2. Listar Ônibus");
            System.out.println("3. Alterar Ônibus");
            System.out.println("4. Remover Ônibus");
            System.out.println("5. Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());
            switch(op) {
                case 1:
                    System.out.print("Placa: "); String p = sc.nextLine();
                    System.out.print("Capacidade Máx.: ");
                    int cap = Integer.parseInt(sc.nextLine());
                    System.out.print("Código Municipal: ");
                    String cm = sc.nextLine();
                    s.cadastrarOnibus(new Onibus(p,cap,cm));
                    break;
                case 2: s.listarOnibus(); break;
                case 3:
                    System.out.print("Código Municipal a alterar: ");
                    s.alterarOnibus(sc.nextLine(), sc);
                    break;
                case 4:
                    System.out.print("Código Municipal a remover: ");
                    s.removerOnibus(sc.nextLine());
                    break;
                case 5: break;
                default: System.out.println("Inválido!");
            }
        } while(op!=5);
    }

    static void menuViagens(Sistema s, Scanner sc) {
        int op;
        do {
            System.out.println("\n-- GERENCIAR VIAGENS --");
            System.out.println("1. Iniciar Viagem (Simulação)");
            System.out.println("2. Listar Viagens");
            System.out.println("3. Alterar Viagem");
            System.out.println("4. Remover Viagem");
            System.out.println("5. Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());
            switch(op) {
                case 1: s.iniciarViagem(sc); break;
                case 2: s.listarViagens();    break;
                case 3:
                    System.out.print("Data da viagem a alterar (dd/mm/aa): ");
                    String d = sc.nextLine();
                    System.out.print("Hora da viagem a alterar (hh:mm): ");
                    String h = sc.nextLine();
                    s.alterarViagem(d, h, sc);
                    break;
                case 4:
                    System.out.print("Data da viagem a remover (dd/mm/aa): ");
                    String rd = sc.nextLine();
                    System.out.print("Hora da viagem a remover (hh:mm): ");
                    String rh = sc.nextLine();
                    s.removerViagem(rd, rh);
                    break;
                case 5: break;
                default: System.out.println("Inválido!");
            }
        } while(op!=5);
    }
}
