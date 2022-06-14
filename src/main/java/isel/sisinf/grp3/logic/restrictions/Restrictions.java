package isel.sisinf.grp3.logic.restrictions;

import isel.sisinf.grp3.model.Gps;
import isel.sisinf.grp3.model.GreenZone;
import isel.sisinf.grp3.model.Vehicle;
import isel.sisinf.grp3.model.client.Client;
import isel.sisinf.grp3.model.client.InstitutionalClient;
import isel.sisinf.grp3.model.client.PrivateClient;

import java.util.Objects;

/**
 * Upper restrictions class that contains all the sub restrictions classes that correspond to each table's restrictions logic.
 */
public class Restrictions {

    /**
     * ClientRestrictions child class of Restrictions IRestrictions<Client> inherits it's methods</Client>
     */
    public static class ClientRestrictions implements IRestrictions<Client> {

        /**
         * Client table restrictions logic.
         */
        @Override
        public Boolean checkRestrictions(Client entity) throws RestrictionException {
            int phone = Integer.parseInt(entity.getPhone());
            if (!(phone >= 0 && phone <= 999999999)) {
                throw new RestrictionException("Invalid phone");
            }

            int nif = Integer.parseInt(entity.getNif());
            if (!(nif >= 0 && nif <= 999999999)) {
                throw new RestrictionException("Invalid nif");
            }

            int ref = Integer.parseInt(entity.getReference().getNif());
            if (!(ref >= 0 && ref <= 999999999) || ref == nif) {
                throw new RestrictionException("Invalid ref");
            }

            PrivateClient privateClient = entity.getPrivateClient();
            InstitutionalClient institutionalClient = entity.getInstitutionalClient();
            if (privateClient != null && institutionalClient != null) {
                throw new RestrictionException("Illegal client type");
            }
            return true;
        }
    }

    /**
     * PrivateClientRestrictions child class of Restrictions IRestrictions<Client> inherits it's methods</Client>
     */
    public static class PrivateClientRestrictions implements IPrivateClientRestrictions {

        /**
         * Private client table restrictions logic.
         */
        @Override
        public Boolean checkRestrictions(PrivateClient entity) throws RestrictionException {
            int nif = Integer.parseInt(entity.getNif());
            if (!(nif >= 0 && nif <= 999999999)) {
                throw new RestrictionException("Invalid nif");
            }
            int cc = Integer.parseInt(entity.getCC());
            if (!(cc >= 0 && cc <= 99999999)) {
                throw new RestrictionException("Invalid cc");
            }
            return true;
        }

    }

    /**
     * InstitutionalClientRestrictions child class of Restrictions IRestrictions<Client> inherits it's methods</Client>
     */
    public static class InstitutionalClientRestrictions implements IInstitutionalClientRestriction {

        /**
         * Institutional client table restrictions logic.
         */
        @Override
        public Boolean checkRestrictions(InstitutionalClient entity) throws RestrictionException {
            int nif = Integer.parseInt(entity.getNif());
            if (!(nif >= 0 && nif <= 999999999)) {
                throw new RestrictionException("Invalid nif");
            }
            return true;
        }

    }

    /**
     * VehicleRestrictions child class of Restrictions IRestrictions<Client> inherits it's methods</Client>
     */
    public static class VehicleRestrictions implements IVehicleRestrictions {

        /**
         * Vehicle table restrictions logic.
         */
        @Override
        public Boolean checkRestrictions(Vehicle entity) throws RestrictionException {
            int phone = Integer.parseInt(entity.getDriversPhone());
            if (!(phone >= 0 && phone <= 999999999)) {
                throw new RestrictionException("Invalid number");
            }
            int nif = Integer.parseInt(entity.getClient().getClientId());
            if (!(nif >= 0 && nif <= 999999999)) {
                throw new RestrictionException("Invalid nif");
            }
            return true;
        }
    }

    /**
     * GpsRestrictions child class of Restrictions IRestrictions<Client> inherits it's methods</Client>
     */
    public static class GpsRestrictions implements IGpsRestrictions {

        /**
         * Gps table restrictions logic.
         */
        @Override
        public Boolean checkRestrictions(Gps entity) throws RestrictionException { //do with enum class
            String status = entity.getStatus();
            if (Objects.equals(status, "Activo") || Objects.equals(status, "PausaDeAlarmes") || Objects.equals(status, "Inactivo")) {
                throw new RestrictionException("Invalid status");
            }
            return true;
        }
    }

    /**
     * GreenZoneRestrictions child class of Restrictions IRestrictions<Client> inherits it's methods</Client>
     */
    public static class GreenZoneRestrictions implements IGreenZoneRestrictions {

        /**
         * GreenZone table restrictions logic.
         */
        @Override
        public Boolean checkRestrictions(GreenZone entity) throws RestrictionException {
            return true;
        }
    }
}
