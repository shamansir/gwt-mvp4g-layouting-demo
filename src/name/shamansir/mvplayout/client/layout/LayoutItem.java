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

public class LayoutItem extends Layout {

    @UiTemplate("LayoutItem.ui.xml")
    interface LayoutItemUiBinder extends UiBinder<Widget, LayoutItem> { }
    private static LayoutItemUiBinder uiBinder = GWT.create(LayoutItemUiBinder.class);
    
    @UiField Outlet placeA;
    @UiField Outlet placeB;
    @UiField Outlet placeC;
    
    public LayoutItem() {
        super(L.ITEM, new Place[] { O.A, O.B, O.C });

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

