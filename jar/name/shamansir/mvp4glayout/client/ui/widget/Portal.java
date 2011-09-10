package name.shamansir.mvp4glayout.client.ui.widget;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import name.shamansir.mvp4glayout.client.mvp.IsPortalView;

public abstract class Portal extends Composite implements IsPortalView {
    
    private Plugs plugs;
    
    @UiConstructor
    public Portal() {
    	super();
    }
    
    @Override
    protected void initWidget(Widget widget) {
        if (!(widget instanceof Plugs)) 
            throw new IllegalStateException("Plugs instance must be a root of Portal view");
        plugs = (Plugs)widget; 
        plugs.setContainer(this);
    }	
    
    public Plugs getPlugs() { return plugs; }

}
