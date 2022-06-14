package isel.sisinf.grp3.logic.repos;

/**
 * Interface that has the signature of all the methods for a Data mapper.
 * Separates the in-memory objects from the database and its responsible for the transfer between the two.
 * @param <T>
 * @param <Tid>
 */
public interface IDataMapper<T, Tid> {
    T Create(T entity);

    T Update(T entity);

    T Delete(T entity);
}
