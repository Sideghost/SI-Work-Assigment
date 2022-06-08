package isel.sisinf.grp3.logic.repos;

/**
 * todo
 */
public interface IDataMapper<T, Tid> {
    T Create(T entity);

    T Update(T entity);

    T Delete(T entity);
}
