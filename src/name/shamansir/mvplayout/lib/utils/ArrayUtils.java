/**
 * 
 */
package name.shamansir.mvplayout.lib.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class ArrayUtils {
    
    @SuppressWarnings("unchecked")
    public static <T> T[] concat(T[] first, T[] second) {
        List<T> list = new ArrayList<T>();
        list.addAll(Arrays.asList(first));
        list.addAll(Arrays.asList(second));
        return (T[])list.toArray();
    }
    
    /* public static <T> T[] add(T[] array, T item) {
        return concat(array, new T[] { item } );
    } */
    
}
