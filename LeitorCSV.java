import java.io.*;

public class LeitorCSV {
    private Subestacao[] subestacoes;
    private final String[] MESES = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public LeitorCSV(Subestacao[] subestacoes) {
        this.subestacoes = subestacoes;
    }

    public void lerArquivo(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;
            boolean isFirstLine = true; // Verificar se é a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Ignorar o cabeçalho
                    continue;
                }

                String[] partes = linha.split(",");

                // Verifica se a linha possui ao menos 3 elementos (mes, subestacao, consumo)
                if (partes.length < 3) {
                    System.out.println("Linha inválida: " + linha);
                    continue;
                }

                String mes = partes[0].trim(); // Nome do mês
                String subestacaoNome = partes[1].trim();
                int consumo;

                try {
                    consumo = Integer.parseInt(partes[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Valor de consumo inválido na linha: " + linha);
                    continue;
                }

                int mesIndex = getMesIndex(mes);
                Subestacao subestacao = getSubestacao(subestacaoNome);

                if (mesIndex != -1 && subestacao != null) {
                    subestacao.adicionarConsumo(mesIndex, consumo);
                } else {
                    if (mesIndex == -1) {
                        System.out.println("Mês inválido: " + mes);
                    }
                    if (subestacao == null) {
                        System.out.println("Subestação não encontrada: " + subestacaoNome);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int getMesIndex(String mes) {
        for (int i = 0; i < MESES.length; i++) {
            if (MESES[i].equalsIgnoreCase(mes)) {
                return i;
            }
        }
        return -1; // Mês não encontrado
    }

    private Subestacao getSubestacao(String nome) {
        for (Subestacao s : subestacoes) {
            if (s.getNome().equalsIgnoreCase(nome)) {
                return s;
            }
        }
        return null; // Subestação não encontrada
    }
}
