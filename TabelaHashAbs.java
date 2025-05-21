public abstract class TabelaHashAbs {
    protected Node[] tabela;
    protected int tamanho;
    protected int colisoes;

    public TabelaHashAbs(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Node[tamanho];
        this.colisoes = 0;
    }

    protected abstract int funcaoHash(String chave);

    public void insert(String chave) {
        int indice = funcaoHash(chave);
        Node novo = new Node(chave);

        if (tabela[indice] == null) {
            tabela[indice] = novo;
        } else {
            colisoes++;
            Node atual = tabela[indice];
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
    }

    public boolean search(String chave) {
        int indice = funcaoHash(chave);
        Node atual = tabela[indice];
        while (atual != null) {
            if (atual.chave.equals(chave)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public int getColisoes() {
        return colisoes;
    }

    public int[] contValoresPosicao() {
        int[] distribuicao = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            int cont = 0;
            Node atual = tabela[i];
            while (atual != null) {
                cont++;
                atual = atual.proximo;
            }
            distribuicao[i] = cont;
        }
        return distribuicao;
    }
}
