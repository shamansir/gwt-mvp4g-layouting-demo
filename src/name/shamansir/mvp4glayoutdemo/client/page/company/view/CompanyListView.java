package name.shamansir.mvp4glayoutdemo.client.page.company.view;

import java.util.Set;

import name.shamansir.mvp4glayout.client.ui.Portal.UrlBuilder;
import name.shamansir.mvp4glayout.client.ui.widget.Plug;
import name.shamansir.mvp4glayout.client.ui.widget.Portlet;
import name.shamansir.mvp4glayoutdemo.client.id.P;
import name.shamansir.mvp4glayoutdemo.client.page.company.presenter.CompanyListPresenter.Display;
import name.shamansir.mvp4glayoutdemo.client.page.company.view.widget.CompanyPortlet;
import name.shamansir.mvp4glayoutdemo.shared.dao.Company;

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
