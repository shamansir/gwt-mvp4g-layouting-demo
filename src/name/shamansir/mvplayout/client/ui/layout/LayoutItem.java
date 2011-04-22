package name.shamansir.mvplayout.client.ui.layout;

import com.google.gwt.user.client.ui.HasWidgets;

import name.shamansir.mvplayout.client.ui.Layout;
import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;

public class LayoutItem extends Layout {

	public LayoutItem() {
		super(LayoutId.ITEM, new Place[] { Place.A, Place.B, Place.C } );
	}

	@Override
	protected HasWidgets preparePanel(Place place)
			throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

}
