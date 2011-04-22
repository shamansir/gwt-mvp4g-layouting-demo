package name.shamansir.mvplayout.client.ui.pages.news.view;

import name.shamansir.mvplayout.client.ui.pages.user.presenter.ListPresenter.IListView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class ListView extends Composite implements IListView {

	@UiTemplate("ListView.ui.xml")	
	interface ListViewUiBinder extends UiBinder<Widget, ListView> { }	
	private static ListViewUiBinder uiBinder = GWT.create(ListViewUiBinder.class);	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
