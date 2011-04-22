package name.shamansir.mvplayout.client.ui.layout;

import com.google.gwt.user.client.ui.HasWidgets;

import name.shamansir.mvplayout.client.ui.layout.base.Layout;
import name.shamansir.mvplayout.client.ui.layout.base.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.layout.base.Layouts.Place;

public class LayoutEdit extends Layout {

	public LayoutEdit() {
		super(LayoutId.EDIT, new Place[] { Place.A, Place.B, Place.C, Place.D } );
	}

	@Override
	protected HasWidgets preparePanel(Place place)
			throws IndexOutOfBoundsException {
		return null;
	}
}
