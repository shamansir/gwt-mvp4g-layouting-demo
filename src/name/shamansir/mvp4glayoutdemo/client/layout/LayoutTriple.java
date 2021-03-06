/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

import name.shamansir.mvp4glayout.client.ui.IsOutlet;
import name.shamansir.mvp4glayout.client.ui.structure.Place;
import name.shamansir.mvp4glayout.client.ui.widget.Layout;
import name.shamansir.mvp4glayout.client.ui.widget.Outlet;

import name.shamansir.mvp4glayoutdemo.client.id.L;
import name.shamansir.mvp4glayoutdemo.client.id.O;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class LayoutTriple extends Layout {
    
    @UiTemplate("LayoutTriple.ui.xml")
    interface LayoutDoubleUiBinder extends UiBinder<Widget, LayoutTriple> { }
    private static LayoutDoubleUiBinder uiBinder = GWT.create(LayoutDoubleUiBinder.class);
    
    @UiField Outlet placeA;
    @UiField Outlet placeB;
    @UiField Outlet placeC;
    
    public LayoutTriple() {
        super(L.TRIPLE, new Place[] { O.A, O.B, O.C });

        initWidget(uiBinder.createAndBindUi(this));     
    }

    @Override
    protected IsOutlet prepareOutlet(Place place)
            throws IndexOutOfBoundsException {
        if (place.equals(O.A)) return placeA;
        if (place.equals(O.B)) return placeB;
        if (place.equals(O.C)) return placeC;
        throw new IndexOutOfBoundsException("No widget position at place " + place + " for layout " + id());
    }    

}
