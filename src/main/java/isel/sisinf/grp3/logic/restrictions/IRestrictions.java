package isel.sisinf.grp3.logic.restrictions;

public interface IRestrictions<T> {
    Boolean checkRestrictions(T entity) throws IllegalArgumentException;
}
