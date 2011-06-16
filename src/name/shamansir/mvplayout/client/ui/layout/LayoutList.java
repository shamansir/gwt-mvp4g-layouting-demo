package name.shamansir.mvplayout.client.ui.layout;

import name.shamansir.mvplayout.client.ui.IsOutlet;
import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.widget.Layout;
import name.shamansir.mvplayout.client.ui.widget.Outlet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

public class LayoutList extends Layout {
	
	@UiTemplate("LayoutList.ui.xml")
	interface LayoutListUiBinder extends UiBinder<Widget, LayoutList> { }
	private static LayoutListUiBinder uiBinder = GWT.create(LayoutListUiBinder.class);
	
	@UiField Outlet placeA;
	@UiField Outlet placeB;
	@UiField Outlet placeC;

	public LayoutList() {
		super(LayoutId.LIST, new Place[] { Place.A, Place.B, Place.C } );
		
		initWidget(uiBinder.createAndBindUi(this));		
	}

	@Override
	protected IsOutlet prepareOutlet(Place place)
			throws IndexOutOfBoundsException {
		if (place.equals(Place.A)) return placeA;
		if (place.equals(Place.B)) return placeB;
		if (place.equals(Place.C)) return placeC;		
		throw new IndexOutOfBoundsException("No widget position at place " + place + " for layout " + id());
	}

}
