/**
 * 
 */
package name.shamansir.mvplayout.client.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.utils</dd>
 * </dl>
 *
 * <code>StringUtils</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Apr 29, 2011 12:08:20 AM 
 *
 */
public class StringUtils {

    public static String join(Collection<String> source, String delim) {
        if (source == null || source.isEmpty()) return "";
        Iterator<String> iter = source.iterator();
        StringBuilder builder = new StringBuilder(iter.next());
        while (iter.hasNext()) {
            builder.append(delim).append(iter.next());
        }
        return builder.toString();
    }
    
    public static String join(String[] source, String delim) {
        return join(source, delim);
    }    
    
}
