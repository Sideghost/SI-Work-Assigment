package isel.sisinf.grp3.model.client;

/**
 * todo
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

    String getReference(); // TODO: 07/06/2022 devera ser set de clientes ou set de referencias? como e que e pra fazer esta merda

    void setReference(String reference);

}
