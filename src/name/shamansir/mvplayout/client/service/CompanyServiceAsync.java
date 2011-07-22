package name.shamansir.mvplayout.client.service;

import java.util.Set;

import name.shamansir.mvplayout.shared.dao.Company;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>UserService</code>.
 */
public interface CompanyServiceAsync {
    void getCompanies(String filter, AsyncCallback<Set<Company>> callback);
    void getCompany(int cid, AsyncCallback<Company> callback);
    void saveCompany(Company company, AsyncCallback<Integer> callback);
}
