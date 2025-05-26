/**
 * Classe abstrata que implementa o funcionamento básico de uma tabela hash
 */
public abstract class TabelaHashAbs {
    // Tamanho da tabela hash
    protected int tamanho;
    // Array de listas encadeadas que forma a tabela hash
    protected LinkedList[] tabela;
    // Contador de colisões ocorridas
    protected int colisoes;

    /**
     * Construtor - Inicializa a tabela hash com o tamanho especificado
     * @param tamanho número de posições na tabela
     */
    public TabelaHashAbs(int tamanho) {
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList();
        }
        colisoes = 0;
    }

    /**
     * Método abstrato que define a função de hash
     * @param chave valor a ser transformado em índice
     * @return índice calculado pela função hash
     */
    protected abstract int funcaoHash(String chave);

    /**
     * Insere uma chave na tabela hash
     * @param chave valor a ser inserido
     */
    public void inserir(String chave) {
        int indice = funcaoHash(chave);
        if (!tabela[indice].estaVazia()) colisoes++;
        tabela[indice].adicionar(chave);
    }

    /**
     * Verifica se uma chave existe na tabela
     * @param chave valor a ser procurado
     * @return verdadeiro se a chave for encontrada, falso caso contrário
     */
    public boolean contem(String chave) {
        int indice = funcaoHash(chave);
        return tabela[indice].contem(chave);
    }

    /**
     * Conta quantos elementos existem em cada posição da tabela
     * @return array com a contagem de elementos por posição
     */
    public int[] contarElementosPorPosicao() {
        int[] contagem = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            contagem[i] = tabela[i].tamanho();
        }
        return contagem;
    }

    /**
     * Retorna o número de colisões ocorridas
     * @return total de colisões
     */
    public int getColisoes() {
        return colisoes;
    }

    /**
     * Calcula o fator de carga da tabela hash
     * @return fator de carga
     */
    public double getFatorCarga() {
        int totalElementos = 0;
        for (LinkedList lista : tabela) {
            totalElementos += lista.tamanho();
        }
        return (double) totalElementos / tamanho;
    }
}
