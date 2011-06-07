package name.shamansir.mvplayout.client.ui.layout;

import name.shamansir.mvplayout.client.ui.IsOutlet;
import name.shamansir.mvplayout.client.ui.Outlet;
import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

public class LayoutList extends LayoutWithState {
	
	@UiTemplate("LayoutList.ui.xml")
	interface LayoutListUiBinder extends UiBinder<Widget, LayoutList> { }
	private static LayoutListUiBinder uiBinder = GWT.create(LayoutListUiBinder.class);
	
	@UiField Outlet placeA;
	@UiField Outlet placeB;
	@UiField Outlet placeC;
	@UiField Outlet status;

	public LayoutList() {
		super(LayoutId.LIST, new Place[] { Place.A, Place.B, Place.C, Place.STATUS } );
		
		initWidget(uiBinder.createAndBindUi(this));		
	}

	@Override
	public void prepare(State state) {
		switch (state) {
		
		   case LOADING_DATA: 
		   case NO_DATA: 
		   case NO_MATCHES: {
			   placeA.setVisible(false);
			   status.setVisible(true);
		   } break;
		
		   case HAS_DATA: {
			   status.setVisible(false);			   
			   placeA.setVisible(true);			   
		   } break;
				   
		}		
	}

	@Override
	protected IsOutlet prepareOutlet(Place place)
			throws IndexOutOfBoundsException {
		if (place.equals(Place.A)) return placeA;
		if (place.equals(Place.B)) return placeB;
		if (place.equals(Place.C)) return placeC;
		if (place.equals(Place.STATUS)) return status;		
		throw new IndexOutOfBoundsException("No widget position at place " + place + " for layout " + id());
	}

}
