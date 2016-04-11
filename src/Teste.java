import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class Teste {
    public static void main(String[] args) throws IOException {
        Integer[] rota = new Integer[]{0, 1, 2, 3, 4, 5};
        int len = rota.length;
        for (int i = 0; i < len; i++) {
            System.out.print("i=" + i + "\n");
            ArrayList<String> jin = new ArrayList<>();
            for (int j = wrap(i + 2, len); j != wrap(len + i - 1, len); j = wrap(j + 1, len)) {
                jin.add(Integer.toString(j));
                Integer[] p1 = SubArray(rota, wrap(j + 1, len), i);
                Integer[] p2 = Reverso(SubArray(rota, i + 1, wrap(j, len)));
                Integer[] nr = Join(p1, p2);
                System.out.println("\tNR(" + i + ", " + j + ") = " + Arrays.toString(nr));
            }
            System.out.print("\tj in {" + String.join(", ", jin) + "}");
            System.out.print("\n-------------------\n");
        }
    }

    public static <T> T[] Join(T[] array1, T[] array2) {
        T[] retorno = (T[]) Array.newInstance(array1.getClass().getComponentType(), array1.length + array2.length);
        System.arraycopy(array1, 0, retorno, 0, array1.length);
        System.arraycopy(array2, 0, retorno, array1.length, array2.length);
        return retorno;
    }

    public static int wrap(int _i, int _len) {
        return (_len + _i) % _len;
    }

    public static <T> void Swap(T[] array, int troca, int por) {
        troca = Teste.wrap(troca, array.length);
        por = Teste.wrap(por, array.length);
        Collections.swap(Arrays.asList(array), troca, por);
    }

    // array(1,2,3,4,5,6)
    // ini= 4, fim =1
    // resultado = array(5,6,1,2)
    public static <T> T[] SubArray(T[] array, int ini, int tamanho) {
        ArrayList<T> retorno = new ArrayList<>();
        for (int c = ini; c < tamanho + ini; c++)
            retorno.add(array[wrap(c, array.length)]);
        T[] retorno2 = (T[]) Array.newInstance(array.getClass().getComponentType(), retorno.size());
        System.arraycopy(retorno.toArray(), 0, retorno2, 0, retorno.size());
        return retorno2;
    }

    public static <T> T[] Reverso(T[] array) {
        T[] retorno = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        for (int c = array.length - 1, i = 0; c >= 0; c--, i++)
            retorno[i] = array[c];
        return retorno;
    }
}
