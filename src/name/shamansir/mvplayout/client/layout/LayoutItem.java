package name.shamansir.mvplayout.client.layout;

import name.shamansir.mvplayout.client.id.L;
import name.shamansir.mvplayout.client.id.O;
import name.shamansir.mvplayout.lib.ui.IsOutlet;
import name.shamansir.mvplayout.lib.ui.state.LayoutWithState;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.ui.widget.Outlet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

public class LayoutItem extends LayoutWithState {

    @UiTemplate("LayoutItem.ui.xml")
    interface LayoutItemUiBinder extends UiBinder<Widget, LayoutItem> { }
    private static LayoutItemUiBinder uiBinder = GWT.create(LayoutItemUiBinder.class);
    
    @UiField Outlet placeA;
    @UiField Outlet placeB;
    @UiField Outlet placeC;   
    @UiField Outlet status;
    
    public LayoutItem() {
        super(L.ITEM, new Place[] { O.A, O.B, O.C }, O.STATUS);

        initWidget(uiBinder.createAndBindUi(this));     
    }

    @Override
    protected IsOutlet prepareOutlet(Place place)
            throws IndexOutOfBoundsException {
        if (place.equals(O.A)) return placeA;
        if (place.equals(O.B)) return placeB;
        if (place.equals(O.C)) return placeC;
        if (place.equals(O.STATUS)) return status;
        throw new IndexOutOfBoundsException("No widget position at place " + place + " for layout " + id());
    }

    @Override
    public void prepare(State state) {
        switch (state) {
            case NO_DATA: case LOADING_DATA: case NO_MATCHES: {
                placeA.setVisible(false);
                placeB.setVisible(false);
                placeC.setVisible(false);
                status.setVisible(true);
            } break;
            case HAS_DATA: {
                status.setVisible(false);                
                placeA.setVisible(true);
                placeB.setVisible(true);
                placeC.setVisible(true);                
            }
        }        
    }
}
