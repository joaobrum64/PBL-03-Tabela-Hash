public abstract class TabelaHashAbs {
    protected int tamanho;
    protected LinkedList[] tabela;
    protected int colisoes;

    public TabelaHashAbs(int tamanho) {
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList();
        }
        colisoes = 0;
    }

    protected abstract int hash(String chave);

    public void put(String chave) {
        int indice = hash(chave);
        if (!tabela[indice].estaVazia()) colisoes++;
        tabela[indice].add(chave);
    }

    public boolean contains(String chave) {
        int indice = hash(chave);
        return tabela[indice].contains(chave);
    }

    public int[] contValoresPosicao() {
        int[] cont = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            cont[i] = tabela[i].size();
        }
        return cont;
    }

    public int getColisoes() {
        return colisoes;
    }
}
