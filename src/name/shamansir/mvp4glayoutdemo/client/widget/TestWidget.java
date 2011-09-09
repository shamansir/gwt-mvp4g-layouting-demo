/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.widget;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class TestWidget extends FlowPanel {

    @UiConstructor
    public TestWidget() {
        super();
        add(new Label("I am test widget"));
    }
    
}
