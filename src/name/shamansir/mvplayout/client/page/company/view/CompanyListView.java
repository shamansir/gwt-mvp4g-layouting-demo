package name.shamansir.mvplayout.client.page.company.view;

import java.util.Set;

import com.google.gwt.user.client.ui.VerticalPanel;

import name.shamansir.mvplayout.client.page.company.presenter.CompanyListPresenter.Display;
import name.shamansir.mvplayout.lib.ui.Portal.UrlBuilder;
import name.shamansir.mvplayout.lib.ui.widget.Plug;
import name.shamansir.mvplayout.lib.ui.widget.Portlet;
import name.shamansir.mvplayout.shared.dao.Company;

public final class CompanyListView extends Portlet implements Display {

	private final VerticalPanel panel = new VerticalPanel();
    
	@Override
	public void createView() {
		initWidget(Plug.of("company-list", panel));
	}

    @Override
    public void showCompanies(Set<Company> companies, UrlBuilder url) {
        // TODO Auto-generated method stub
    }

}
