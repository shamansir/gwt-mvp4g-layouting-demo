package name.shamansir.mvplayout.client.page.company.view;

import name.shamansir.mvplayout.client.page.company.presenter.CompanyListPresenter.Display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class CompanyListView extends Composite implements Display {

	@UiTemplate("CompanyListView.ui.xml")	
	interface ListViewUiBinder extends UiBinder<Widget, CompanyListView> { }	
	private static ListViewUiBinder uiBinder = GWT.create(ListViewUiBinder.class);	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
