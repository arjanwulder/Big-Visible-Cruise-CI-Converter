package nl.mirabeau.ci.rss.feed.common;

import junit.framework.Assert;
import nl.mirabeau.ci.rss.feed.domain.enumeration.Vendor;
import nl.mirabeau.ci.rss.feed.exception.UnknownVendorException;
import org.junit.Test;

public class VendorUtilsTest {

    @Test(expected = UnknownVendorException.class)
    public void mustThrowUnknownVendorExceptionWhenVendorIsUnknown() throws UnknownVendorException {
        // Act
        VendorUtils.getVendorAsEnum("unknown vendor");
    }


    @Test
    public void mustReturnVendorEnumWhenHudsonIsRecognizedByTheSystem() throws UnknownVendorException {
        // Act
        Vendor vendorAsEnum = VendorUtils.getVendorAsEnum("hudson");

        // Assert
        Assert.assertNotNull(vendorAsEnum);
        Assert.assertEquals(Vendor.HUDSON, vendorAsEnum);
    }


    @Test
    public void mustReturnVendorEnumWhenTeamCityIsRecognizedByTheSystem() throws UnknownVendorException {
        // Act
        Vendor vendorAsEnum = VendorUtils.getVendorAsEnum("team_city");

        // Assert
        Assert.assertNotNull(vendorAsEnum);
        Assert.assertEquals(Vendor.TEAM_CITY, vendorAsEnum);
    }

}
