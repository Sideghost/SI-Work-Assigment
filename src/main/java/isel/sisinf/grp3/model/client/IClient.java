package isel.sisinf.grp3.model.client;

import java.util.Set;

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

    String getReference(); // TODO: 07/06/2022 devera ser set de clientes ou set de referencias?

    void setReference(String reference);

}
