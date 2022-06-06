package isel.sisinf.grp3.logic.repos;

public interface DataMapper<T, Tid>
{
    T Create(T entity);
    T Update(T entity);
    T Delete(T entity);
}
