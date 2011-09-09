package name.shamansir.mvp4glayout.client.ui.widget;

import name.shamansir.mvp4glayout.client.mvp.IsPortletView;
import name.shamansir.mvp4glayout.client.ui.Pluggable;
import name.shamansir.mvp4glayout.client.ui.structure.Place;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class Portlet extends Composite implements IsPortletView {
    
    private Plug plug;
    private Plugs plugs;
    private Place place;    
    
    @UiConstructor
    public Portlet() {
    	super();
    }
    
    @Override
    protected void initWidget(Widget widget) {
        if (!(widget instanceof Plug)) 
            throw new IllegalStateException("Plug instance must be a root of Portlet view");
        plugs = new Plugs();
        plug = (Plug)widget;
        plugs.add(plug);
        plugs.setContainer(this);        
    	//super.initWidget(plug);
    }
    
    @Override
    public Pluggable getMainView() { return plug; }
    
    @Override
    public Plugs getPlugs() { return plugs; }
    
    @Override
    public void setPlace(Place place) { this.place = place; }
    
    @Override
    public Place getPlace() { return place; }    
    
    @Override 
    public Widget asWidget() { return plug; }

}
