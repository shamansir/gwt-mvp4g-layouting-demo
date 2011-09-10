package name.shamansir.mvp4glayoutdemo.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

import name.shamansir.mvp4glayoutdemo.shared.dao.Company;

/**
 * The async counterpart of <code>UserService</code>.
 */
public interface CompanyServiceAsync {
    void getCompanies(String filter, AsyncCallback<Set<Company>> callback);
    void getCompany(int cid, AsyncCallback<Company> callback);
    void saveCompany(Company company, AsyncCallback<Integer> callback);
}
