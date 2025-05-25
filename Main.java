import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final int TAMANHO_TABELA = 32;
        final String ARQUIVO = "female_names.txt";
        String[] nomes = new String[5000];

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            int i = 0;
            while ((linha = br.readLine()) != null && i < 5000) {
                nomes[i++] = linha.trim();
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
            return;
        }

        TabelaHashAbs tabela1 = new TabelaHash1(TAMANHO_TABELA);
        TabelaHashAbs tabela2 = new TabelaHash2(TAMANHO_TABELA);

        long inicio1 = System.nanoTime();
        for (String nome : nomes) {
            tabela1.put(nome);
        }
        long fim1 = System.nanoTime();

        long inicio2 = System.nanoTime();
        for (String nome : nomes) {
            tabela2.put(nome);
        }
        long fim2 = System.nanoTime();

        long buscaInicio1 = System.nanoTime();
        for (String nome : nomes) {
            tabela1.contains(nome);
        }
        long buscaFim1 = System.nanoTime();

        long buscaInicio2 = System.nanoTime();
        for (String nome : nomes) {
            tabela2.contains(nome);
        }
        long buscaFim2 = System.nanoTime();

        System.out.println("=== RELATÓRIO COMPARATIVO ===");

        System.out.println("\nTabelaHash1 (função hash polinomial base 31):");
        System.out.println("Colisões: " + tabela1.getColisoes());
        System.out.println("Tempo de inserção: " + (fim1 - inicio1) / 1_000_000.0 + " ms");
        System.out.println("Tempo de busca: " + (buscaFim1 - buscaInicio1) / 1_000_000.0 + " ms");
        exibirDistribuicao(tabela1);

        System.out.println("\nTabelaHash2 (função hash com mistura de bits):");
        System.out.println("Colisões: " + tabela2.getColisoes());
        System.out.println("Tempo de inserção: " + (fim2 - inicio2) / 1_000_000.0 + " ms");
        System.out.println("Tempo de busca: " + (buscaFim2 - buscaInicio2) / 1_000_000.0 + " ms");
        exibirDistribuicao(tabela2);
    }

    public static void exibirDistribuicao(TabelaHashAbs tabela) {
        int[] dist = tabela.contValoresPosicao();
        System.out.println("Distribuição de chaves por posição:");
        for (int i = 0; i < dist.length; i++) {
            System.out.printf("Posição %2d: %d elementos%n", i, dist[i]);
        }
    }
}
