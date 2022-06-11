package isel.sisinf.grp3.logic.repos.registers;

import isel.sisinf.grp3.logic.repos.IRepository;
import isel.sisinf.grp3.model.registors.ProcessedRegisters;

import java.util.Collection;

public interface IProcessedRegistersRepository extends IRepository<ProcessedRegisters, Collection<ProcessedRegisters>, Long/*tipo da chave*/> {
}
