package isel.sisinf.grp3.logic.repos.client;

import isel.sisinf.grp3.logic.repos.IRepository;
import isel.sisinf.grp3.model.client.InstitutionalClient;

import java.util.Collection;

public interface IInstitutionalClientRepository extends IRepository<InstitutionalClient, Collection<InstitutionalClient>, String/*key type*/> {
}
