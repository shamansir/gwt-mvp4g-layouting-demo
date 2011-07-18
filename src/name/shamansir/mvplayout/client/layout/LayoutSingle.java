/**
 * 
 */
package name.shamansir.mvplayout.client.layout;

import name.shamansir.mvplayout.client.id.L;
import name.shamansir.mvplayout.client.id.O;
import name.shamansir.mvplayout.lib.ui.IsOutlet;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.ui.widget.Layout;
import name.shamansir.mvplayout.lib.ui.widget.Outlet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class LayoutSingle extends Layout {
    
    @UiTemplate("LayoutSingle.ui.xml")
    interface LayoutSingleUiBinder extends UiBinder<Widget, LayoutSingle> { }
    private static LayoutSingleUiBinder uiBinder = GWT.create(LayoutSingleUiBinder.class);
    
    @UiField Outlet placeA;
    
    public LayoutSingle() {
        super(L.SINGLE, new Place[] { O.A });

        initWidget(uiBinder.createAndBindUi(this));     
    }

    @Override
    protected IsOutlet prepareOutlet(Place place)
            throws IndexOutOfBoundsException {
        if (place.equals(O.A)) return placeA;
        throw new IndexOutOfBoundsException("No widget position at place " + place + " for layout " + id());
    }    

}
