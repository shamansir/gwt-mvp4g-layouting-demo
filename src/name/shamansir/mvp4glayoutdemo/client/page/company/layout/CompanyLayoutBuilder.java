package name.shamansir.mvp4glayoutdemo.client.page.company.layout;

import name.shamansir.mvp4glayout.client.ui.LayoutBuilder;
import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.state.State;
import name.shamansir.mvp4glayout.client.ui.widget.Layout;
import name.shamansir.mvp4glayoutdemo.client.id.O;
import name.shamansir.mvp4glayoutdemo.client.id.P;
import name.shamansir.mvp4glayoutdemo.client.page.company.CompanyEventBus;

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
