import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Teste2 extends Teste {

    public static void main(String[] args) {
        Integer[] rota = new Integer[]{0, 1, 2, 3, 4, 5};
        ArrayList<Integer[]> novasRotas = new ArrayList<>();
        int len = rota.length;

        for (int i = 0; i < len; i++) {
            Integer[] movimentacao = SubArray(rota, i, len);
            ArrayList<Integer[]> rotas = new ArrayList<>();
            rotas.add(movimentacao.clone());
            rotas.add(movimentacao.clone()); Swap(rotas.get(1), 3, 4);
            rotas.add(movimentacao.clone()); Swap(rotas.get(2), 1, 2);
            rotas.add(movimentacao.clone()); Swap(rotas.get(3), 1, 2); Swap(rotas.get(3), 3, 4);
            rotas.add(movimentacao.clone()); Swap(rotas.get(4), 1, 3); Swap(rotas.get(4), 2, 4);
            rotas.add(movimentacao.clone()); Swap(rotas.get(5), 1, 3); Swap(rotas.get(5), 2, 4); Swap(rotas.get(5), 3, 4);
            rotas.add(movimentacao.clone()); Swap(rotas.get(6), 1, 4); Swap(rotas.get(6), 2, 3); Swap(rotas.get(6), 3, 4);
            rotas.add(movimentacao.clone()); Swap(rotas.get(7), 1, 4); Swap(rotas.get(7), 2, 3);
        }
    }
}
