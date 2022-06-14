package isel.sisinf.grp3.logic.restrictions;

/**
 * Interface IRestrictions has the signature that all the tables restrictions classes are going to implement.
 * @param <T>
 */
public interface IRestrictions<T> {
    Boolean checkRestrictions(T entity) throws IllegalArgumentException, RestrictionException;
}
