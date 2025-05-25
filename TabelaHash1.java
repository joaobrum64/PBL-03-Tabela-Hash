public class TabelaHash1 extends TabelaHashAbs {
    public TabelaHash1(int tamanho) {
        super(tamanho);
    }

    @Override
    protected int hash(String chave) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash = (31 * hash + chave.charAt(i)) % tamanho;
        }
        return Math.abs(hash);
    }
}
