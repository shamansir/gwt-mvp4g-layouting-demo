package name.shamansir.mvplayout.client.page.company.view;

import java.util.Set;

import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.company.presenter.CompanyListPresenter.Display;
import name.shamansir.mvplayout.client.page.company.view.widget.CompanyPortlet;
import name.shamansir.mvplayout.lib.ui.Portal.UrlBuilder;
import name.shamansir.mvplayout.lib.ui.widget.Plug;
import name.shamansir.mvplayout.lib.ui.widget.Portlet;
import name.shamansir.mvplayout.shared.dao.Company;

import com.google.gwt.user.client.ui.VerticalPanel;

public final class CompanyListView extends Portlet implements Display {

	private final VerticalPanel panel = new VerticalPanel();
    
	@Override
	public void createView() {
		initWidget(Plug.of("company-list", panel));
	}

    @Override
    public void showCompanies(Set<Company> companies, UrlBuilder url) {
        panel.clear();
        for (Company company: companies) {
            CompanyPortlet portlet = new CompanyPortlet();
            portlet.load(company, 
                    url.from(P.COMPANY_SHOW, String.valueOf(company.getId())));
            portlet.createView();
            panel.add(portlet.getMainView());
        }
    }

}
