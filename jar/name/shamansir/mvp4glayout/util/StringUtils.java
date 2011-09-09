/**
 * 
 */
package name.shamansir.mvp4glayout.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvp4glayout.client.utils</dd>
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
        final StringBuffer buffer = new StringBuffer();
        final Iterator<String> iter = source.iterator();
        while (iter.hasNext()) {
            final String item = iter.next();
            if (item != null) {
                buffer.append(item);
                if (iter.hasNext()) {
                    buffer.append(delim);
                }
            }
        }
        return buffer.toString();
    }
    
    public static String join(String[] source, String delim) {
        return join(Arrays.asList(source), delim);
    }
    
    // someId expected in format LIKE_THIS, like Enum.name() and constants do
    public static String toCSS(String constant) {
        return constant.toLowerCase().replace('_', '-');
    }
    
}
