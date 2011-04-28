package name.shamansir.mvplayout.client.ui.layout;

import com.google.gwt.user.client.ui.HasWidgets;

import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState;

public class LayoutList extends LayoutWithState {

	public LayoutList() {
		super(LayoutId.EDIT, new Place[] { Place.A, Place.B, Place.C, Place.STATUS } );
	}

	@Override
	public void prepare(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected HasWidgets preparePanel(Place place)
			throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

}
