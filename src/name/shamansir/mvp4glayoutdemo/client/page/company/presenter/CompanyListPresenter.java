package name.shamansir.mvp4glayoutdemo.client.page.company.presenter;

import java.util.Set;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

import name.shamansir.mvp4glayout.client.SafeCallback;
import name.shamansir.mvp4glayout.client.mvp.IsPortletView;
import name.shamansir.mvp4glayout.client.mvp.PortletPresenter;
import name.shamansir.mvp4glayout.client.ui.Portal.UrlBuilder;

import name.shamansir.mvp4glayoutdemo.client.page.company.CompanyEventBus;
import name.shamansir.mvp4glayoutdemo.client.page.company.view.CompanyListView;
import name.shamansir.mvp4glayoutdemo.client.service.CompanyServiceAsync;
import name.shamansir.mvp4glayoutdemo.shared.dao.Company;

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
