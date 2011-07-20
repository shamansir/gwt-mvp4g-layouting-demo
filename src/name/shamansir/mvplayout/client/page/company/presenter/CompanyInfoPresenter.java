package name.shamansir.mvplayout.client.page.company.presenter;

import name.shamansir.mvplayout.client.page.company.CompanyEventBus;
import name.shamansir.mvplayout.client.page.company.view.widget.CompanyPortlet;
import name.shamansir.mvplayout.lib.mvp.PortletPresenter;

import com.mvp4g.client.annotation.Presenter;

@Presenter(view = CompanyPortlet.class)
public class CompanyInfoPresenter extends PortletPresenter<CompanyPortlet, CompanyEventBus> {

    public void onShow(int nid) {
        
    }
    
}
