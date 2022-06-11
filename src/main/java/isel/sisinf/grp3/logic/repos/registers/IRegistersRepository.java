package isel.sisinf.grp3.logic.repos.registers;

import isel.sisinf.grp3.logic.repos.IRepository;
import isel.sisinf.grp3.model.registors.Registers;

import java.util.Collection;

public interface IRegistersRepository extends IRepository<Registers, Collection<Registers>, Long/*why*/> {
}
