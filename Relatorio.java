import java.io.*;

public class Relatorio {
    private Subestacao[] subestacoes;

    public Relatorio(Subestacao[] subestacoes) {
        this.subestacoes = subestacoes;
    }

    public void gerarRelatorio(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Matriz de Consumo por Subestação");
            writer.newLine();
            gerarMatrizConsumo(writer);
            writer.newLine();

            gerarMaiorConsumo(writer);
            writer.newLine();

            gerarMenorConsumo(writer);
            writer.newLine();

            gerarTotalAnual(writer);
            writer.newLine();

            gerarMediaMensal(writer);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gerarMatrizConsumo(BufferedWriter writer) throws IOException {
        String[] MESES = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
        writer.write( "meses " + " jan Fev Mar Abr Mai Jun Jul Ago Set Out Nov Dez".replaceAll("\s", "\t\t"));
        writer.newLine();

        for (Subestacao s : subestacoes) {
            writer.write(s.getNome());
            if(s.getNome().length() < 8)
                writer.write("\t");
            writer.write("\t");
            for (int consumo : s.getConsumosMensais()) {
                writer.write(consumo + "\t");
                if(consumo < 999)
                    writer.write("\t");
            }
            writer.newLine();
        }
    }

    private void gerarMaiorConsumo(BufferedWriter writer) throws IOException {
        int maiorConsumo = 0;
        String subestacaoMaior = "";
        String mesMaior = "";
        String[] MESES = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};

        for (Subestacao s : subestacoes) {
            int[] consumos = s.getConsumosMensais();
            for (int i = 0; i < consumos.length; i++) {
                if (consumos[i] > maiorConsumo) {
                    maiorConsumo = consumos[i];
                    subestacaoMaior = s.getNome();
                    mesMaior = MESES[i];
                }
            }
        }
        writer.write("Subestação com maior consumo mensal: " + subestacaoMaior + " - " + mesMaior);
    }

    private void gerarMenorConsumo(BufferedWriter writer) throws IOException {
        int menorConsumo = Integer.MAX_VALUE;
        String subestacaoMenor = "";
        String mesMenor = "";
        String[] MESES = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};

        for (Subestacao s : subestacoes) {
            int[] consumos = s.getConsumosMensais();
            for (int i = 0; i < consumos.length; i++) {
                if (consumos[i] < menorConsumo && consumos[i] > 0) {
                    menorConsumo = consumos[i];
                    subestacaoMenor = s.getNome();
                    mesMenor = MESES[i];
                }
            }
        }
        writer.write("Subestação com menor consumo mensal: " + subestacaoMenor + " - " + mesMenor);
    }

    private void gerarTotalAnual(BufferedWriter writer) throws IOException {
        int total = 0;
        for (Subestacao s : subestacoes) {
            total += s.totalAnual();
        }
        writer.write("Total geral de consumo: " + total);
    }

    private void gerarMediaMensal(BufferedWriter writer) throws IOException {
        writer.write("Média de consumo mensal por subestação");
        writer.newLine();
        for (Subestacao s : subestacoes) {
            writer.write(s.getNome() + ": " + s.mediaMensal());
            writer.newLine();
        }
    }
}
