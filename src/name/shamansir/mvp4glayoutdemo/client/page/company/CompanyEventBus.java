package name.shamansir.mvp4glayoutdemo.client.page.company;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

import name.shamansir.mvp4glayout.client.mvp.ChildEventBus;
import name.shamansir.mvp4glayout.client.ui.structure.Place;

import name.shamansir.mvp4glayoutdemo.client.page.company.history.CompanyHistoryConverter;
import name.shamansir.mvp4glayoutdemo.client.page.company.presenter.CompanyInfoPresenter;
import name.shamansir.mvp4glayoutdemo.client.page.company.presenter.CompanyListPresenter;
import name.shamansir.mvp4glayoutdemo.client.page.company.view.CompanyListView;

@Events(module = CompanyModule.class, startView = CompanyListView.class)
public interface CompanyEventBus extends ChildEventBus {
    
    // navigation events    
    
    @Event(name = "list", navigationEvent = true,
           handlers = CompanyListPresenter.class,
           historyConverter = CompanyHistoryConverter.class)
    public void companies();
    
    @Event(name = "show", navigationEvent = true,
           handlers = CompanyInfoPresenter.class,
           historyConverter = CompanyHistoryConverter.class)
    public void show(int cid);
    
    // projecting events, portlets
    
    @Event(handlers = CompanyListPresenter.class, calledMethod = "plug")
    public void plugCompaniesList(Place where);
    
    @Event(handlers = CompanyInfoPresenter.class, calledMethod = "plug")
    public void plugCompanyShow(Place where);    

}
