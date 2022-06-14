package isel.sisinf.grp3.logic.restrictions;


/**
 * This class is used only to represent an exception that is used only when one of the restrictions isn´t verified.
 * @see Exception
 */
public class RestrictionException extends Exception {
    public RestrictionException(String message) {
        super(message);
    }
}