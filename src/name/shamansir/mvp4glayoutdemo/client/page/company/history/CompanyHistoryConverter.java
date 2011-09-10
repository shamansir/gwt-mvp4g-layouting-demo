package name.shamansir.mvp4glayoutdemo.client.page.company.history;

import com.mvp4g.client.annotation.History;

import name.shamansir.mvp4glayout.client.mvp.PortalsHistoryConverter;
import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.Portal.PortalUrl;

import name.shamansir.mvp4glayoutdemo.client.id.G;
import name.shamansir.mvp4glayoutdemo.client.id.P;
import name.shamansir.mvp4glayoutdemo.client.page.company.CompanyEventBus;

@History
public class CompanyHistoryConverter extends PortalsHistoryConverter<CompanyEventBus> {

    protected CompanyHistoryConverter() { super(G.COMPANY); }

    @Override
    protected void convertFromUrl(PortalUrl url, Portal view,
            CompanyEventBus eventBus) {
    	
    	switch (P.by(view)) {
    		case COMPANY_LIST: eventBus.companies(); break;
    		case COMPANY_SHOW: eventBus.show(
    		        (url.param(0) != null) && !url.param(0).isEmpty()
                    ? Integer.parseInt(url.param(0)) : -1); break;
    	}
    	
    }
    
    public String onCompanies() {
        return "";
    }
    
    public String onShow(int cid) {
        return (cid != -1) 
                ? url.parameters(String.valueOf(cid)) : "";
    }
    

}
