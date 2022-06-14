package isel.sisinf.grp3.logic.repos.client;

import isel.sisinf.grp3.logic.repos.IRepository;
import isel.sisinf.grp3.model.client.Client;

import java.util.Collection;

/**
 * Repository of Clients present in DB
 */
public interface IClientRepository extends IRepository<Client, Collection<Client>, String/*key type*/> {

    Integer getClientVehicles(String key);
}
