package isel.sisinf.grp3.logic.repos;

import isel.sisinf.grp3.model.registos.UnprocessedRegisters;

import java.util.Collection;

public interface IRegistersRepository extends IRepository<UnprocessedRegisters, Collection<UnprocessedRegisters>, Long/*why*/> {
    void eliminateInvalidRegisters();
}
