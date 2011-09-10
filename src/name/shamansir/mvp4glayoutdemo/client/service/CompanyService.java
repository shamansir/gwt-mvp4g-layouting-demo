package name.shamansir.mvp4glayoutdemo.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import name.shamansir.mvp4glayout.client.exception.ItemNotFoundException;
import name.shamansir.mvp4glayout.client.exception.NoMatchesException;

import name.shamansir.mvp4glayoutdemo.shared.dao.Company;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("companies")
public interface CompanyService extends RemoteService {
    Set<Company> getCompanies(String filter) throws NoMatchesException;
    Company getCompany(int cid) throws ItemNotFoundException;
    int saveCompany(Company company); 
}
