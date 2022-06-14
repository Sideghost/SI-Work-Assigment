package isel.sisinf.grp3.logic.repos;

import isel.sisinf.grp3.model.AllAlarm;

import java.util.Collection;

public interface IAllAlarmRepository extends IRepository<AllAlarm, Collection<AllAlarm>, Long> {
    Collection<AllAlarm> findAll();
}
