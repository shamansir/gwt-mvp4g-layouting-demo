package name.shamansir.mvplayout.client.ui.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState;

public class LayoutList extends LayoutWithState {
	
	@UiTemplate("LayoutList.ui.xml")
	interface LayoutListUiBinder extends UiBinder<Widget, LayoutList> { }
	private static LayoutListUiBinder uiBinder = GWT.create(LayoutListUiBinder.class);
	
	@UiField HasWidgets placeA;
	@UiField HasWidgets placeB;
	@UiField HasWidgets placeC;
	@UiField HasWidgets status;

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
			   nav.setVisible(false);
			   main.setVisible(false);
			   status.setVisible(true);
		   } break;
		
		   case HAS_DATA: {
			   status.setVisible(false);			   
			   nav.setVisible(true);
			   main.setVisible(true);			   
		   } break;
				   
		}		
	}

	@Override
	protected HasWidgets preparePanel(Place place)
			throws IndexOutOfBoundsException {
		if (place.equals(Place.A)) return placeA;
		if (place.equals(Place.B)) return placeB;
		if (place.equals(Place.C)) return placeC;
		if (place.equals(Place.STATUS)) return status;		
		throw new IndexOutOfBoundsException("No widget position at place " + place + " for layout " + id());
	}

}
