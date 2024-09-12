import java.io.*;

public class LeitorCSV {
    private Subestacao[] subestacoes;

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

                // Garante que partes[0] possui o formato esperado (algo como "01 Dezembro")
                String[] mesPartes = partes[0].split(" ");
                if (mesPartes.length < 2) {
                    System.out.println("Formato de mês inválido: " + partes[0]);
                    continue;
                }

                String mes = mesPartes[1];  // Pega o nome do mês (ex: "Dezembro")
                String subestacaoNome = partes[1];
                int consumo;

                try {
                    consumo = Integer.parseInt(partes[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Valor de consumo inválido na linha: " + linha);
                    continue;
                }

                int mesIndex = getMesIndex(mes);
                Subestacao subestacao = getSubestacao(subestacaoNome);

                if (mesIndex != -1 && subestacao != null) {
                    subestacao.adicionarConsumo(mesIndex, consumo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getMesIndex(String mes) {
        String[] MESES = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
        for (int i = 0; i < MESES.length; i++) {
            if (MESES[i].startsWith(mes.substring(0, 3))) { // Corrige para pegar os três primeiros caracteres do mês
                return i;
            }
        }
        return -1; // Mês não encontrado
    }

    private Subestacao getSubestacao(String nome) {
        for (Subestacao s : subestacoes) {
            if (s.getNome().equals(nome)) {
                return s;
            }
        }
        return null; // Subestação não encontrada
    }
}
