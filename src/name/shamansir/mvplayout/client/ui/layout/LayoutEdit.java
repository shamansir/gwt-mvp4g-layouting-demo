package name.shamansir.mvplayout.client.ui.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

import name.shamansir.mvplayout.client.ui.IsOutlet;
import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState;
import name.shamansir.mvplayout.client.ui.widget.Outlet;

public class LayoutEdit extends LayoutWithState {

    @UiTemplate("LayoutEdit.ui.xml")
    interface LayoutEditUiBinder extends UiBinder<Widget, LayoutEdit> { }
    private static LayoutEditUiBinder uiBinder = GWT.create(LayoutEditUiBinder.class);
    
    @UiField Outlet placeA;
    @UiField Outlet placeB;
    @UiField Outlet placeC;
    @UiField Outlet placeD;
    @UiField Outlet status;
    
	public LayoutEdit() {
		super(LayoutId.EDIT, new Place[] { Place.A, Place.B, Place.C, Place.D, Place.STATUS } );

        initWidget(uiBinder.createAndBindUi(this));		
	}

	@Override
	protected IsOutlet prepareOutlet(Place place)
			throws IndexOutOfBoundsException {
        if (place.equals(Place.A)) return placeA;
        if (place.equals(Place.B)) return placeB;
        if (place.equals(Place.C)) return placeC;
        if (place.equals(Place.D)) return placeD;
        if (place.equals(Place.STATUS)) return status;
        throw new IndexOutOfBoundsException("No widget position at place " + place + " for layout " + id());
	}

    @Override
    public void prepare(State state) {
        switch (state) {
            case NO_DATA: case LOADING_DATA: case NO_MATCHES: {
                placeA.setVisible(false);
                placeB.setVisible(false);
                placeC.setVisible(false);
                placeD.setVisible(false);
                status.setVisible(true);
            } break;
            case HAS_DATA: {
                status.setVisible(false);                
                placeA.setVisible(true);
                placeB.setVisible(true);
                placeC.setVisible(true);
                placeD.setVisible(true);                
            }
        }        
    }
}
