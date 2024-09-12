public class Main {
    public static void main(String[] args) {
        Subestacao[] subestacoes = {
                new Subestacao("Planalto"),
                new Subestacao("Aurora"),
                new Subestacao("Litoral"),
                new Subestacao("Horizonte"),
                new Subestacao("Progresso")
        };

        LeitorCSV leitorCSV = new LeitorCSV(subestacoes);
        leitorCSV.lerArquivo("consumos_20.csv");

        Relatorio relatorio = new Relatorio(subestacoes);
        relatorio.gerarRelatorio("resultados.txt");
    }
}
