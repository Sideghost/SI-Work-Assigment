package isel.sisinf.grp3.logic.repos;

/**
 * Interface of a generic Repository.
 * @param <T> Type of Stored Class.
 * @param <TCol> Collection of Stored type.
 * @param <TK> Key type.
 */
public interface IRepository<T, TCol, TK> {
    T findByKey(TK key);

    TCol find(String jpql, Object... params);
}

