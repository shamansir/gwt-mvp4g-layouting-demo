package name.shamansir.mvplayout.client.page.company.history;

import name.shamansir.mvplayout.client.id.G;
import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.company.CompanyEventBus;
import name.shamansir.mvplayout.lib.mvp.PortalsHistoryConverter;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.Portal.PortalUrl;

import com.mvp4g.client.annotation.History;

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
