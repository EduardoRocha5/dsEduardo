
import java.util.ArrayList;

class Linha {

    private String codigo;
    private String nome;
    private ArrayList<String> paradas;

    public Linha(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.paradas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<String> getParadas() {
        return paradas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarParada(String parada, int idx) {
        if (idx >= 0 && idx <= paradas.size()) {
            paradas.add(idx, parada);
        } else {
            paradas.add(parada);
        }
    }

    public boolean removerParada(String parada) {
        return paradas.remove(parada);
    }

    @Override
    public String toString() {
        // formato descritivo para salvar no arquivo
        return "Código: " + codigo + "\n"
                + "Nome:   " + nome + "\n"
                + "Paradas: " + String.join(", ", paradas) + "\n";
    }
}
