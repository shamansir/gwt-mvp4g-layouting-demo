package name.shamansir.mvplayout.server;

import java.util.HashSet;
import java.util.Set;

import name.shamansir.mvplayout.client.exception.ItemNotFoundException;
import name.shamansir.mvplayout.client.exception.NoMatchesException;
import name.shamansir.mvplayout.client.service.CompaniesService;
import name.shamansir.mvplayout.shared.dao.Company;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CompaniesServiceImpl extends RemoteServiceServlet implements
		CompaniesService {

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
