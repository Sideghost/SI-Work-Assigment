package isel.sisinf.grp3.logic.repos;

public interface Repository<T,TCol,TK> {
    T findByKey(TK key);
    TCol find(String jpql, Object... params);
}

