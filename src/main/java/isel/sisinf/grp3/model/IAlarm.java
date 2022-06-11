package isel.sisinf.grp3.model;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public interface IAlarm {

    void setAlarmId(Long id);
    Long getAlarmId();

    void setGpsId(Long id);
    Long getGpsId();

    void setTimeStamp(DateTime timeStamp);
    DateTime getTimestamp();

}
