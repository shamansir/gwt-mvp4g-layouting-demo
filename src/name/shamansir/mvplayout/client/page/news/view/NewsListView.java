package name.shamansir.mvplayout.client.page.news.view;

import name.shamansir.mvplayout.client.page.news.presenter.NewsListPresenter.Display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class NewsListView extends Composite implements Display {

	@UiTemplate("NewsListView.ui.xml")	
	interface ListViewUiBinder extends UiBinder<Widget, NewsListView> { }	
	private static ListViewUiBinder uiBinder = GWT.create(ListViewUiBinder.class);	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
