package isel.sisinf.grp3.model.client;

/**
 * Interface Client that has all the signatures of the setters and getters needed for
 * the "Cliente" table present in the DB.
 */
public interface IClient {
    String getClientId();

    void setClientId(String clientId);

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    String getPhone();

    void setPhone(String phone);

    PrivateClient getReference();

    void setReference(PrivateClient reference);

}
