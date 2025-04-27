
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Sistema {
    private ArrayList<Linha>  linhas  = new ArrayList<>();
    private ArrayList<Onibus> onibus  = new ArrayList<>();
    private ArrayList<Viagem> viagens = new ArrayList<>();

    public List<Linha>  getLinhas()  { return linhas; }
    public List<Onibus> getOnibus()  { return onibus; }
    public List<Viagem> getViagens() { return viagens; }

    // regrava TODO em dados.txt com seções descritivas :contentReference[oaicite:0]{index=0}
    public void salvarDados() {
        try (FileWriter fw = new FileWriter("dados.txt")) {
            fw.write("=== LINHAS ===\n");
            for (Linha l : linhas) fw.write(l.toString() + "\n");
            fw.write("=== ÔNIBUS ===\n");
            for (Onibus o : onibus) fw.write(o.toString() + "\n");
            fw.write("=== VIAGENS ===\n");
            for (Viagem v : viagens) fw.write(v.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    // --- CRUD Linhas ---
    public void cadastrarLinha(Linha l) { linhas.add(l); salvarDados(); }
    public void listarLinhas() {
        System.out.println("\n--- Linhas Cadastradas ---");
        for (Linha l : linhas) System.out.print(l);
    }
    public void alterarLinha(String codigo, Scanner sc) {
        for (Linha l : linhas) {
            if (l.getCodigo().equals(codigo)) {
                System.out.print("Novo nome: ");
                l.setNome(sc.nextLine());
                System.out.print("Deseja alterar paradas? (s/n): ");
                if (sc.nextLine().equalsIgnoreCase("s")) {
                    l.getParadas().clear();
                    System.out.print("Quantas paradas? ");
                    int q = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < q; i++) {
                        System.out.print("Parada " + (i+1) + ": ");
                        l.adicionarParada(sc.nextLine(), i);
                    }
                }
                salvarDados();
                return;
            }
        }
        System.out.println("Linha não encontrada!");
    }
    public void removerLinha(String codigo) {
        if (linhas.removeIf(l -> l.getCodigo().equals(codigo))) salvarDados();
        else System.out.println("Linha não encontrada!");
    }

    // --- CRUD Ônibus ---
    public void cadastrarOnibus(Onibus o) { onibus.add(o); salvarDados(); }
    public void listarOnibus() {
        System.out.println("\n--- Ônibus Cadastrados ---");
        for (Onibus o : onibus) System.out.print(o);
    }
    public void alterarOnibus(String codMun, Scanner sc) {
        for (Onibus o : onibus) {
            if (o.getCodigoMunicipal().equals(codMun)) {
                System.out.print("Nova placa: ");
                o.setPlaca(sc.nextLine());
                System.out.print("Nova capacidade máxima: ");
                o.setCapacidadeMaxima(Integer.parseInt(sc.nextLine()));
                System.out.print("Novo código municipal: ");
                o.setCodigoMunicipal(sc.nextLine());
                salvarDados();
                return;
            }
        }
        System.out.println("Ônibus não encontrado!");
    }
    public void removerOnibus(String codMun) {
        if (onibus.removeIf(o -> o.getCodigoMunicipal().equals(codMun))) salvarDados();
        else System.out.println("Ônibus não encontrado!");
    }

    // --- CRUD Viagens ---
    public void cadastrarViagem(Viagem v) { viagens.add(v); salvarDados(); }
    public void listarViagens() {
        System.out.println("\n--- Viagens Cadastradas ---");
        for (Viagem v : viagens) System.out.print(v);
    }
    public void alterarViagem(String data, String hora, Scanner sc) {
        for (Viagem v : viagens) {
            if (v.getData().equals(data) && v.getHora().equals(hora)) {
                System.out.print("Nova data (dd/mm/aa): ");
                v.setData(sc.nextLine());
                System.out.print("Nova hora (hh:mm): ");
                v.setHora(sc.nextLine());
                System.out.print("Novo código da linha: ");
                String cl = sc.nextLine();
                Linha l = linhas.stream()
                                .filter(x->x.getCodigo().equals(cl))
                                .findFirst().orElse(null);
                if (l!=null) v.setLinha(l);
                System.out.print("Novo código municipal do ônibus: ");
                String co = sc.nextLine();
                Onibus o = onibus.stream()
                                 .filter(x->x.getCodigoMunicipal().equals(co))
                                 .findFirst().orElse(null);
                if (o!=null) v.setOnibus(o);
                salvarDados();
                return;
            }
        }
        System.out.println("Viagem não encontrada!");
    }
    public void removerViagem(String data, String hora) {
        if (viagens.removeIf(v->v.getData().equals(data)&&v.getHora().equals(hora)))
            salvarDados();
        else System.out.println("Viagem não encontrada!");
    }

    // Iniciar simulação e cadastrar
    public void iniciarViagem(Scanner sc) {
        System.out.print("Data (dd/mm/aa): ");
        String d = sc.nextLine();
        System.out.print("Hora (hh:mm): ");
        String h = sc.nextLine();

        System.out.print("Código da Linha: ");
        String cl = sc.nextLine();
            Linha l = linhas.stream()
                        .filter(x->x.getCodigo().equals(cl))
                        .findFirst().orElse(null);
        if (l==null) { System.out.println("Linha não existe!"); return; }

        System.out.print("Código Municipal do Ônibus: ");
        String co = sc.nextLine();
        Onibus o = onibus.stream()
                         .filter(x->x.getCodigoMunicipal().equals(co))
                         .findFirst().orElse(null);
        if (o==null) { System.out.println("Ônibus não existe!"); return; }

        Viagem v = new Viagem(d, h, l, o);
        viagens.add(v);
        salvarDados();
        v.simularViagem();
    }
}

