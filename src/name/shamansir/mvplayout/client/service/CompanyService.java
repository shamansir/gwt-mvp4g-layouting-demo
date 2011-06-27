package name.shamansir.mvplayout.client.service;

import java.util.Set;

import name.shamansir.mvplayout.lib.exception.ItemNotFoundException;
import name.shamansir.mvplayout.lib.exception.NoMatchesException;
import name.shamansir.mvplayout.shared.dao.Company;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("companies")
public interface CompanyService extends RemoteService {
	Set<Company> getCompanies(String filter) throws NoMatchesException;
	Company getCompany(int cid) throws ItemNotFoundException;
	int saveCompany(Company company); 
}
