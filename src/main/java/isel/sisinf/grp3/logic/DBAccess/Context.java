package isel.sisinf.grp3.logic.DBAccess;

public interface Context extends AutoCloseable {

    void beginTransaction();

    void commit();

    void flush();

    //.......
}
