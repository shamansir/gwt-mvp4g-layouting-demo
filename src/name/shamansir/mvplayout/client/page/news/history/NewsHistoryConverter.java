package name.shamansir.mvplayout.client.page.news.history;

import name.shamansir.mvplayout.client.id.G;
import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.news.NewsEventBus;
import name.shamansir.mvplayout.lib.mvp.PortalsHistoryConverter;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.Portal.PortalUrl;

import com.mvp4g.client.annotation.History;

@History
public class NewsHistoryConverter extends PortalsHistoryConverter<NewsEventBus> {

	protected NewsHistoryConverter() { super(G.NEWS); }

	@Override
	protected void convertFromUrl(PortalUrl url, Portal view,
	        NewsEventBus eventBus) {
		
		switch (P.by(view)) {
			case NEWS_LIST: eventBus.news(); break;
			case NEWS_SHOW: eventBus.show(
			        (url.param(0) != null) && !url.param(0).isEmpty()
                    ? Integer.parseInt(url.param(0)) : -1); break;
			case NEWS_EDIT: eventBus.edit(
			        (url.param(0) != null) && !url.param(0).isEmpty()
			        ? Integer.parseInt(url.param(0)) : -1); break;
		}
		
	}
	
	public String onNews() {
	    return "";
	}
	
	public String onShow(int nid) {
        return (nid != -1) 
                ? url.parameters(String.valueOf(nid)) : "";
	}
	
    public String onEdit(int nid) {
        return (nid != -1) 
                ? url.parameters(String.valueOf(nid)) : "";
    }	

}
