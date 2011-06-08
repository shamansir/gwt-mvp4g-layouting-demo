package name.shamansir.mvplayout.client.ui.layout;

import name.shamansir.mvplayout.client.ui.IsOutlet;
import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.widget.Layout;

public class LayoutItem extends Layout {

	public LayoutItem() {
		super(LayoutId.ITEM, new Place[] { Place.A, Place.B, Place.C } );
	}

	@Override
	protected IsOutlet prepareOutlet(Place place)
			throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

}
