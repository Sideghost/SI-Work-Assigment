package isel.sisinf.grp3.logic.restrictions;

import isel.sisinf.grp3.model.Gps;
import isel.sisinf.grp3.model.Vehicle;
import isel.sisinf.grp3.model.client.Client;
import isel.sisinf.grp3.model.client.InstitutionalClient;
import isel.sisinf.grp3.model.client.PrivateClient;

import java.util.Objects;

/**
 * todo
 */
public class Restrictions {

    protected class ClientRestrictions implements IRestrictions<Client> {
        @Override
        public Boolean checkRestrictions(Client entity) throws IllegalArgumentException {
            int phone = Integer.parseInt(entity.getPhone());
            if (!(phone > 0 && phone < 999999999)) {
                throw new IllegalArgumentException("Invalid phone");
            }

            int nif = Integer.parseInt(entity.getNif());
            if (!(nif > 0 && nif < 999999999)) {
                throw new IllegalArgumentException("Invalid nif");
            }

            int ref = Integer.parseInt(entity.getReference().getNif());
            if (!(ref > 0 && ref < 999999999) || ref == nif) {
                throw new IllegalArgumentException("Invalid ref");
            }

            PrivateClient privateClient = entity.getPrivateClient();
            InstitutionalClient institutionalClient = entity.getInstitutionalClient();
            if (privateClient != null && institutionalClient != null) {
                throw new IllegalStateException("Illegal client type");
            }
            return true;
        }

    }

    protected class PrivateClientRestrictions implements IPrivateClientRestrictions {

        @Override
        public Boolean checkRestrictions(PrivateClient entity) throws IllegalArgumentException {
            int nif = Integer.parseInt(entity.getNif());
            if (!(nif > 0 && nif < 999999999)) {
                throw new IllegalArgumentException("Invalid nif");
            }
            int cc = Integer.parseInt(entity.getCC());
            if (!(cc > 0 && cc < 99999999)) {
                throw new IllegalArgumentException("Invalid cc");
            }
            return true;
        }

    }

    protected class InstitutionalClientRestrictions implements IInstitutionalClientRestriction {

        @Override
        public Boolean checkRestrictions(InstitutionalClient entity) throws IllegalArgumentException {
            int nif = Integer.parseInt(entity.getNif());
            if (!(nif > 0 && nif < 999999999)) {
                throw new IllegalArgumentException("Invalid nif");
            }
            return true;
        }

    }


    protected class VehicleRestrictions implements IVehicleRestrictions {

        @Override
        public Boolean checkRestrictions(Vehicle entity) throws IllegalArgumentException {
            int phone = Integer.parseInt(entity.getDriversPhone());
            if (!(phone > 0 && phone < 999999999)) {
                throw new IllegalArgumentException("Invalid number");
            }
            int nif = Integer.parseInt(entity.getClient().getClientId());
            if (!(nif > 0 && nif < 999999999)) {
                throw new IllegalArgumentException("Invalid nif");
            }
            return true;
        }


    }

    protected class GpsRestrictions implements IGpsRestrictions {

        @Override
        public Boolean checkRestrictions(Gps entity) throws IllegalArgumentException { //do with enum class
            String status = entity.getStatus();
            if (Objects.equals(status, "Activo") || Objects.equals(status, "PausaDeAlarmes") || Objects.equals(status, "Inactivo")) {
                throw new IllegalArgumentException("Invalid status");
            }
            return true;
        }
    }
}
