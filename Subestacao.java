public class Subestacao {
    private String nome;
    private int[] consumosMensais;

    public Subestacao(String nome) {
        this.nome = nome;
        this.consumosMensais = new int[12]; // Um para cada mês
    }

    public String getNome() {
        return nome;
    }

    public int[] getConsumosMensais() {
        return consumosMensais;
    }

    public void adicionarConsumo(int mes, int consumo) {
        consumosMensais[mes] += consumo;
    }

    public int totalAnual() {
        int total = 0;
        for (int consumo : consumosMensais) {
            total += consumo;
        }
        return total;
    }

    public double mediaMensal() {
        return (double) totalAnual() / 12;
    }
}
