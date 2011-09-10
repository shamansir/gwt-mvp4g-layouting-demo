package name.shamansir.mvp4glayoutdemo.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import name.shamansir.mvp4glayoutdemo.client.service.CompanyService;
import name.shamansir.mvp4glayoutdemo.client.service.exception.ItemNotFoundException;
import name.shamansir.mvp4glayoutdemo.client.service.exception.NoMatchesException;
import name.shamansir.mvp4glayoutdemo.shared.dao.Company;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CompanyServiceImpl extends RemoteServiceServlet implements
    	CompanyService {
    
    final Map<Integer, Company> companies = new HashMap<Integer, Company>();

    public CompanyServiceImpl() {
        int c1id = companies.size();
        Company companyOne = new Company(c1id);
        companyOne.title = "Google";
        companies.put(c1id, companyOne);
        
        int c2id = companies.size();
        Company companyTwo = new Company(c2id);
        companyTwo.title = "Apple";
        companies.put(c2id, companyTwo);
        
        int c3id = companies.size();
        Company companyThree = new Company(c3id);
        companyThree.title = "Microsoft";
        companies.put(c3id, companyThree);        
    }
    
    @Override
    public Set<Company> getCompanies(String filter) throws NoMatchesException {
        // TODO: apply filter        
    	return new HashSet<Company>(companies.values());
    }

    @Override
    public Company getCompany(int cid) throws ItemNotFoundException {
        if (!companies.containsKey(cid)) throw new ItemNotFoundException(cid);
        return companies.get(cid);
    }

    @Override
    public int saveCompany(Company company) {
    	throw new UnsupportedOperationException();
    }

}
