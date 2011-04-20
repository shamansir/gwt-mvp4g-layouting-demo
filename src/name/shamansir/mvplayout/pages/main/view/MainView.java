package name.shamansir.mvplayout.pages.main.view;

import name.shamansir.mvplayout.pages.main.presenter.MainPresenter.IMainView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class MainView extends Composite implements IMainView {

	private static EurekaPageUiBinder uiBinder = GWT.create(EurekaPageUiBinder.class);

	interface EurekaPageUiBinder extends UiBinder<Widget, MainView> { }	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
