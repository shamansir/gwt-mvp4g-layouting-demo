package name.shamansir.mvplayout.client.page.company.presenter;

import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.company.CompanyEventBus;
import name.shamansir.mvplayout.client.page.company.view.widget.CompanyPortlet;
import name.shamansir.mvplayout.client.service.CompanyServiceAsync;
import name.shamansir.mvplayout.lib.mvp.PortletPresenter;
import name.shamansir.mvplayout.lib.utils.SafeCallback;
import name.shamansir.mvplayout.shared.dao.Company;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

@Presenter(view = CompanyPortlet.class)
public class CompanyInfoPresenter extends PortletPresenter<CompanyPortlet, CompanyEventBus> {

    @Inject CompanyServiceAsync service;    
    
    public void onShow(final int cid) {
        service.getCompany(cid, new SafeCallback<Company>(eventBus) {

            @Override
            public void onSuccess(Company company) {
                view.load(company, url.from(P.COMPANY_SHOW, String.valueOf(cid)));
            }
        }); 
    }
    
}
