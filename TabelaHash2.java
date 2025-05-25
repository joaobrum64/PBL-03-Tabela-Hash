public class TabelaHash2 extends TabelaHashAbs {
    public TabelaHash2(int tamanho) {
        super(tamanho);
    }

    @Override
    protected int hash(String chave) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash ^= (chave.charAt(i) << (i % 5));
        }
        return Math.abs(hash) % tamanho;
    }
}
