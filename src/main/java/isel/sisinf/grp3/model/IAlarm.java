package isel.sisinf.grp3.model;

//import java.time.LocalDate;

public interface IAlarm {

    Long getAlarmId();

    void setAlarmId(Long id);

    Long getGpsId();

    void setGpsId(Long id);

    void setTimeStamp(Timestamp timeStamp);

    Timestamp getTimestamp();

}
