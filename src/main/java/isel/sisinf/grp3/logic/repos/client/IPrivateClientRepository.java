package isel.sisinf.grp3.logic.repos.client;

import isel.sisinf.grp3.logic.repos.IRepository;
import isel.sisinf.grp3.model.client.PrivateClient;

import java.util.Collection;

public interface IPrivateClientRepository extends IRepository<PrivateClient, Collection<PrivateClient>, String/*key type*/> {
}
