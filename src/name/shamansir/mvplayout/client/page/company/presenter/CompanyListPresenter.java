package name.shamansir.mvplayout.client.page.company.presenter;

import java.util.Set;

import name.shamansir.mvplayout.client.page.company.CompanyEventBus;
import name.shamansir.mvplayout.client.page.company.view.CompanyListView;
import name.shamansir.mvplayout.client.service.CompanyServiceAsync;
import name.shamansir.mvplayout.lib.mvp.IsPortletView;
import name.shamansir.mvplayout.lib.mvp.PortletPresenter;
import name.shamansir.mvplayout.lib.ui.Portal.UrlBuilder;
import name.shamansir.mvplayout.lib.utils.SafeCallback;
import name.shamansir.mvplayout.shared.dao.Company;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

@Presenter(view = CompanyListView.class)
public class CompanyListPresenter extends PortletPresenter<CompanyListPresenter.Display, CompanyEventBus> {

    @Inject CompanyServiceAsync service;
    
    public interface Display extends IsPortletView {
        public void showCompanies(Set<Company> companies, UrlBuilder url);
    }
    
    public void onCompanies() {
        service.getCompanies("", new SafeCallback<Set<Company>>(eventBus) {

            @Override
            public void onSuccess(Set<Company> companies) {
                view.showCompanies(companies, url);
            }
            
        });
    }    
    
}
