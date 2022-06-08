package isel.sisinf.grp3.logic;

import isel.sisinf.grp3.model.client.Client;
import isel.sisinf.grp3.model.client.PrivateClient;

import java.util.Objects;

/**
 * todo
 */
public class ClientOptions {

    /**
     *
     * @param client
     * @param newName
     * @param newAddress
     * @param newPhone
     * @param newStatus
     */
    void update(PrivateClient client, String newName, String newAddress, String newPhone, String newStatus) {
        Boolean status = client.getClient().getStatus();
        if(newStatus != null && !(newAddress.trim()).isEmpty()) {
            switch (newStatus) {
                case "1": status = true;
                case "0": status = false;
            }
        }
    }

    /**
     *
     * @param privateClient
     */
    void remove(PrivateClient privateClient) {
        privateClient.getClient().setStatus(false);
    }

    /**
     *
     * @param NIF
     * @param name
     * @param address
     * @param phone
     * @param CC
     * @param reference
     */
    void insert(String NIF, String name, String address, String phone, String CC, String reference) {
        // TODO: 06/06/2022 fazer
        Client newClient = new Client();
        PrivateClient newPrivateClient = new PrivateClient();
        newPrivateClient.setCc(CC);
        newPrivateClient.setNif(NIF);
        newPrivateClient.setNif(NIF);
        newClient.setPrivateClient(newPrivateClient);
        newClient.setName(name);
        newClient.setPhone(phone);
        newClient.setAddress(address);
        newClient.setReference(Objects.requireNonNullElse(reference, ""));
    }

}
