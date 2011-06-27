/**
 * 
 */
package name.shamansir.mvplayout.lib.utils;

import java.util.Arrays;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class ArrayUtils {
    
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
    
    /* public static <T> T[] add(T[] array, T item) {
        return concat(array, new T[] { item } );
    } */
    
}
