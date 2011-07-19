package name.shamansir.mvplayout.client.page.company.layout;

import name.shamansir.mvplayout.client.id.O;
import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.company.CompanyEventBus;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.widget.Layout;

public class CompanyLayoutBuilder extends LayoutBuilder<CompanyEventBus> {

	@Override
	protected boolean layout(Portal view, Layout layout, State state,
	                         CompanyEventBus eventBus) {
        switch (P.by(view)) {
            case COMPANY_LIST: {
                eventBus.plugCompaniesList(O.A);
            } return true;
            case COMPANY_SHOW: {
                eventBus.plugCompanyShow(O.A);
            } return true;
        }
        return false;
	}

}
