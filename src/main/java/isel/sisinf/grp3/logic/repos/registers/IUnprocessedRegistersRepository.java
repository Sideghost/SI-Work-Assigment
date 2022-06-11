package isel.sisinf.grp3.logic.repos.registers;

import isel.sisinf.grp3.logic.repos.IRepository;
import isel.sisinf.grp3.model.registors.UnprocessedRegisters;

import java.util.Collection;

public interface IUnprocessedRegistersRepository extends IRepository<UnprocessedRegisters, Collection<UnprocessedRegisters>, Long/*tipo da chave*/> {
}
