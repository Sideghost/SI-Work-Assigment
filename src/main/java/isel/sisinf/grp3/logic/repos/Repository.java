package isel.sisinf.grp3.logic.repos;

/**
 * todo
 * @param <T>
 * @param <TCol>
 * @param <TK>
 */
public interface Repository<T, TCol, TK> {
    T findByKey(TK key);

    TCol find(String jpql, Object... params);
}

