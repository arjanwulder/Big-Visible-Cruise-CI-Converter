package nl.mirabeau.ci.rss.feed.transformer.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nl.mirabeau.ci.rss.feed.domain.enumeration.Vendor;
import nl.mirabeau.ci.rss.feed.exception.UnknownVendorException;
import nl.mirabeau.ci.rss.feed.transformer.builder.LinkBuilder;

/**
 * LinkBuilderFactory
 * 
 * @author awulder
 * 
 */
@Component
public class LinkBuilderFactory {

   @Autowired
   @Qualifier("hudsonLinkBuilder")
   private LinkBuilder hudsonLinkBuilder;

   @Autowired
   @Qualifier("teamCityLinkBuilder")
   private LinkBuilder teamCityLinkBuilder;


   public LinkBuilder getLinkBuilder(Vendor vendor) throws UnknownVendorException {
      if (vendor == null) {
         throw new IllegalArgumentException("vendor may not be null");
      }

      switch (vendor) {
         case HUDSON:
            return hudsonLinkBuilder;
         case TEAM_CITY:
            return teamCityLinkBuilder;
         default:
            throw new UnknownVendorException("vendor [" + vendor + "] is not recognized by the system");
      }
   }
}
