package nl.mirabeau.ci.rss.feed.common;

import nl.mirabeau.ci.rss.feed.domain.enumeration.Vendor;
import nl.mirabeau.ci.rss.feed.exception.UnknownVendorException;

public final class VendorUtils {

    private VendorUtils() {
    }

    public static Vendor getVendorAsEnum(String vendorAsString) throws UnknownVendorException {
        if (Vendor.HUDSON.toString().equalsIgnoreCase(vendorAsString)) {
            return Vendor.HUDSON;
        }
        if (Vendor.TEAM_CITY.toString().equalsIgnoreCase(vendorAsString)) {
            return Vendor.TEAM_CITY;
        }
        throw new UnknownVendorException("Vendor with name [" + vendorAsString + "] is not recognized");
    }

}
