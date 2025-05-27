/**
 * Classe abstrata que implementa o funcionamento básico de uma tabela hash
 */
public abstract class TabelaHashAbs {
    // Tamanho da tabela hash (número de posições)
    protected int tamanho;

    // Array de listas encadeadas que forma a tabela hash (tratamento de colisão por encadeamento separado)
    protected LinkedList[] tabela;

    // Contador de colisões ocorridas durante as inserções
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
     * Método abstrato que define a função de hash a ser implementada nas subclasses
     * @param chave valor a ser transformado em índice da tabela
     * @return índice calculado pela função hash (deve estar entre 0 e tamanho-1)
     */
    protected abstract int funcaoHash(String chave);

    /**
     * Insere uma chave na tabela hash.
     * Antes da inserção, verifica se o fator de carga ultrapassou 0.75 para redimensionar a tabela.
     * @param chave valor a ser inserido
     */
    public void inserir(String chave) {
        if (getFatorCarga() > 0.75) {
            redimensionar();
        }

        int indice = funcaoHash(chave);

        // Se a lista na posição já contém elementos, isso significa uma colisão
        if (!tabela[indice].estaVazia()) colisoes++;

        tabela[indice].adicionar(chave);
    }

    /**
     * Verifica se uma chave existe na tabela hash
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
     * Retorna o número total de colisões ocorridas na tabela hash
     * @return total de colisões
     */
    public int getColisoes() {
        return colisoes;
    }

    /**
     * Calcula o fator de carga da tabela hash (número de elementos dividido pelo tamanho da tabela)
     * @return fator de carga (valor entre 0 e maior que 1)
     */
    public double getFatorCarga() {
        int totalElementos = 0;
        for (LinkedList lista : tabela) {
            totalElementos += lista.tamanho();
        }
        return (double) totalElementos / tamanho;
    }

    /**
     * Redimensiona a tabela hash quando o fator de carga ultrapassa 0.75.
     * O tamanho da tabela é aumentado em 25% (novo tamanho = tamanho + tamanho/4).
     * Depois, todos os elementos são remapeados para a nova tabela (rehashing).
     */
    private void redimensionar() {
        int novoTamanho = tamanho + tamanho / 4;  // Aumenta o tamanho da tabela em 25%
        LinkedList[] antigaTabela = tabela;        // Guarda referência para a tabela antiga

        // Inicializa nova tabela com tamanho aumentado
        tabela = new LinkedList[novoTamanho];
        for (int i = 0; i < novoTamanho; i++) {
            tabela[i] = new LinkedList();
        }

        // Atualiza o tamanho e zera a contagem de colisões
        tamanho = novoTamanho;
        colisoes = 0;

        // Reinsere todos os elementos da tabela antiga na nova tabela (rehashing)
        for (int i = 0; i < antigaTabela.length; i++) {
            Node atual = antigaTabela[i].getPrimeiroNo();
            while (atual != null) {
                int novoIndice = funcaoHash(atual.valor);  // Recalcula índice para o novo tamanho
                if (!tabela[novoIndice].estaVazia()) colisoes++;
                tabela[novoIndice].adicionar(atual.valor);
                atual = atual.proximo;
            }
        }
    }
}
