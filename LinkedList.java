public class LinkedList {
    private Node inicio;

    public LinkedList() {
        inicio = null;
    }

    public void add(String valor) {
        Node novo = new Node(valor);
        novo.proximo = inicio;
        inicio = novo;
    }

    public boolean contains(String valor) {
        Node atual = inicio;
        while (atual != null) {
            if (atual.valor.equals(valor)) return true;
            atual = atual.proximo;
        }
        return false;
    }

    public int size() {
        int cont = 0;
        Node atual = inicio;
        while (atual != null) {
            cont++;
            atual = atual.proximo;
        }
        return cont;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}
