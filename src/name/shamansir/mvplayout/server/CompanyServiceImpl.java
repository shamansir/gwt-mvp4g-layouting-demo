package name.shamansir.mvplayout.server;

import java.util.HashSet;
import java.util.Set;

import name.shamansir.mvplayout.client.service.CompanyService;
import name.shamansir.mvplayout.lib.exception.ItemNotFoundException;
import name.shamansir.mvplayout.lib.exception.NoMatchesException;
import name.shamansir.mvplayout.shared.dao.Company;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CompanyServiceImpl extends RemoteServiceServlet implements
		CompanyService {

	@Override
	public Set<Company> getCompanies(String filter) throws NoMatchesException {
		return new HashSet<Company>();
	}

	@Override
	public Company getCompany(int cid) throws ItemNotFoundException {
		return new Company(cid);
	}

	@Override
	public int saveCompany(Company company) {
		return -1;
	}

}
