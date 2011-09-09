/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.layout;

import name.shamansir.mvp4glayout.client.ui.IsOutlet;
import name.shamansir.mvp4glayout.client.ui.structure.Place;
import name.shamansir.mvp4glayout.client.ui.widget.Layout;
import name.shamansir.mvp4glayout.client.ui.widget.Outlet;
import name.shamansir.mvp4glayoutdemo.client.id.L;
import name.shamansir.mvp4glayoutdemo.client.id.O;

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
