// -------------------------------------------------------------------------------------
// Classe Onibus: representa um ônibus com atributos encapsulados, capacidade máxima e
// quantidade atual de passageiros.  
// -------------------------------------------------------------------------------------
class Onibus {
    private String placa;
    private int capacidadeMaxima;
    private String codigoMunicipal;

    public Onibus(String placa, int cap, String codMun) {
        this.placa = placa;
        this.capacidadeMaxima  = cap;
        this.codigoMunicipal   = codMun;
    }
    public String getPlaca() { return placa; }
    public int    getCapacidadeMaxima() { return capacidadeMaxima; }
    public String getCodigoMunicipal()  { return codigoMunicipal; }

    public void setPlaca(String p) { this.placa = p; }
    public void setCapacidadeMaxima(int c) { this.capacidadeMaxima = c; }
    public void setCodigoMunicipal(String c) { this.codigoMunicipal = c; }

    @Override
    public String toString() {
        return  "Placa:             " + placa + "\n" +
                "Capacidade Máxima: " + capacidadeMaxima + "\n" +
                "Código Municipal: " + codigoMunicipal + "\n";
    }
}