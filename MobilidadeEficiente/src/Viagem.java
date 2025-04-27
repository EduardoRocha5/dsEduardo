import java.util.List;
import java.util.Random;

// -------------------------------------------------------------------------------------
// Classe Viagem: representa uma viagem, utilizando um ônibus e uma linha, e simula,
// por parada, a entrada e saída de passageiros, respeitando as restrições:
// - Na primeira parada, somente entram passageiros.
// - Em paradas intermediárias, primeiro saem passageiros (não excedendo a quantidade a bordo),
//   depois entram novos passageiros, respeitando a capacidade máxima.
// - Na última parada, todos os passageiros desembarcam, deixando o ônibus vazio.
// Além disso, os dados da viagem (data e hora) são armazenados como Strings.
// -------------------------------------------------------------------------------------
class Viagem {
    private String data;    // dd/mm/aa
    private String hora;    // hh:mm
    private Linha linha;
    private Onibus onibus;
    private int passageiros = 0;

    public Viagem(String data, String hora, Linha l, Onibus o) {
        this.data  = data;
        this.hora  = hora;
        this.linha = l;
        this.onibus= o;
    }
    public String getData() { return data; }
    public String getHora() { return hora; }
    public Linha  getLinha(){ return linha; }
    public Onibus getOnibus(){ return onibus; }

    public void setData(String d) { this.data = d; }
    public void setHora(String h) { this.hora = h; }
    public void setLinha(Linha l) { this.linha = l; }
    public void setOnibus(Onibus o){ this.onibus = o; }

    private int embarcar() {
        Random r = new Random();
        int disponiveis = onibus.getCapacidadeMaxima() - passageiros;
        if (disponiveis <= 0) return 0;
        return r.nextInt(disponiveis) + 1;
    }
    private int desembarcar() {
        Random r = new Random();
        if (passageiros <= 0) return 0;
        return r.nextInt(passageiros + 1);
    }

    public String simularViagem() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== Simulação: ")
          .append(data).append(" ").append(hora)
          .append(" | Linha ").append(linha.getCodigo())
          .append(" | Ônibus ").append(onibus.getCodigoMunicipal())
          .append(" ===\n");

        List<String> stops = linha.getParadas();
        for (int i = 0; i < stops.size(); i++) {
            String nomeParada = stops.get(i);   
            sb.append("\nParada ").append(i+1).append(": ").append(nomeParada).append("\n");

            if (i == 0) {
                int e = embarcar(); passageiros += e;
                sb.append("  Embarcaram:   ").append(e).append("\n");
            }
            else if (i < stops.size()-1) {
                int e = embarcar(), d = desembarcar();
                passageiros += e - d;
                sb.append("  Embarcaram:   ").append(e).append("\n")
                  .append("  Desembarcaram:").append(d).append("\n");
            }
            else {
                sb.append("  Desembarcaram:").append(passageiros).append(" (todos)\n");
                passageiros = 0;
            }
            sb.append("  Total no ônibus: ").append(passageiros).append("\n"); 
        }
        sb.append("=== Fim da Simulação ===\n");

        // imprime no console
        System.out.print(sb);
        return sb.toString();
    }

    @Override
    public String toString() {
        return  "Data:  " + data + "\n" +
                "Hora:  " + hora + "\n" +
                "Linha: [Código: " + linha.getCodigo() +
                        ", Nome: " + linha.getNome() + "]\n" +
                "Ônibus: [Placa: " + onibus.getPlaca() +
                         ", Capacidade: " + onibus.getCapacidadeMaxima() +
                         ", Cod.Municipal: " + onibus.getCodigoMunicipal() + "]\n";
    }
}