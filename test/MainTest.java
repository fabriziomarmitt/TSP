import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void TestaWrap() {
        String[] array = new String[]{"a", "b", "c"};
        int indice = Teste.wrap(0, array.length);
        Assert.assertEquals(indice, 0);

        indice = Teste.wrap(2, array.length);
        Assert.assertEquals(indice, 2);

        indice = Teste.wrap(3, array.length);
        Assert.assertEquals(indice, 0);

        indice = Teste.wrap(5, array.length);
        Assert.assertEquals(indice, 2);
    }

    @Test
    public void TestaSwap() {
        String[] array = new String[]{"a", "b", "c"};
        Teste.Swap(array, 0, 1);
        Assert.assertArrayEquals(new String[]{"b", "a", "c"}, array);

        array = new String[]{"a", "b", "c"};
        Teste2.Swap(array, 0, 2);
        Assert.assertArrayEquals(new String[]{"c", "b", "a"}, array);

        array = new String[]{"a", "b", "c"};
        Teste2.Swap(array, 0, 3);
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, array);

        array = new String[]{"a", "b", "c"};
        Teste2.Swap(array, -1, 0);
        Assert.assertArrayEquals(new String[]{"c", "b", "a"}, array);
    }

    @Test
    public void TestaSubArray() {
        String[] array = new String[]{"a", "b", "c", "d", "e"};

        String[] subarray = Teste.SubArray(array, 0, 3);
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, subarray);

        subarray = Teste.SubArray(array, 0, 5);
        Assert.assertArrayEquals(new String[]{"a", "b", "c", "d", "e"}, subarray);

        subarray = Teste.SubArray(array, 3, 5);
        Assert.assertArrayEquals(new String[]{"d", "e", "a", "b", "c"}, subarray);
    }
}
