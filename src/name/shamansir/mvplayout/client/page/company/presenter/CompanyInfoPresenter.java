package name.shamansir.mvplayout.client.page.company.presenter;

import name.shamansir.mvplayout.client.page.company.CompanyEventBus;
import name.shamansir.mvplayout.client.page.company.view.widget.CompanyWidget;
import name.shamansir.mvplayout.lib.mvp.PortletPresenter;

import com.mvp4g.client.annotation.Presenter;

@Presenter(view = CompanyWidget.class)
public class CompanyInfoPresenter extends PortletPresenter<CompanyWidget, CompanyEventBus> {

    public void onShow(int nid) {
        
    }
    
}
