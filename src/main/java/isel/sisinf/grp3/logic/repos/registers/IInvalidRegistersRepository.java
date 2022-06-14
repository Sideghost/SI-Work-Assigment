package isel.sisinf.grp3.logic.repos.registers;

import isel.sisinf.grp3.logic.repos.IRepository;
import isel.sisinf.grp3.model.registors.InvalidRegisters;

import java.util.Collection;


public interface IInvalidRegistersRepository extends IRepository<InvalidRegisters, Collection<InvalidRegisters>, Long/*tipo da chave*/> {
}
