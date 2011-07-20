/**
 * 
 */
package name.shamansir.mvplayout.client.page.company.view.widget;

import name.shamansir.mvplayout.lib.ui.MakesLink;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.widget.Plug;
import name.shamansir.mvplayout.lib.ui.widget.Portlet;
import name.shamansir.mvplayout.shared.dao.Company;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class CompanyPortlet extends Portlet {
    
    private final Plug plug;
    private final Panel wrapper;
    
    public CompanyPortlet() {
        this.wrapper = new HorizontalPanel();
        this.plug = Plug.of("company-widget", wrapper);
    }
    
    public void load(Company company, MakesLink showUrl) {
        wrapper.clear();
        wrapper.add(new Label(company.title));
        wrapper.add(new Anchor("show", Portal.URL_PREFIX + showUrl.makeLink()));
    }

    @Override
    public void createView() {
        initWidget(plug);
    }

}
