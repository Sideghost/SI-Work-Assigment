package isel.sisinf.grp3.logic.repos.client;

import isel.sisinf.grp3.logic.repos.IRepository;
import isel.sisinf.grp3.model.client.Client;

import java.util.Collection;

/**
 * todo
 */
public interface IClientRepository extends IRepository<Client, Collection<Client>, String/*key type*/> {
}
