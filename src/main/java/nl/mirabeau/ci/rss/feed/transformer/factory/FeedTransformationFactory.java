package nl.mirabeau.ci.rss.feed.transformer.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nl.mirabeau.ci.rss.feed.domain.enumeration.Vendor;
import nl.mirabeau.ci.rss.feed.exception.UnknownVendorException;
import nl.mirabeau.ci.rss.feed.transformer.FeedTransformer;

/**
 * FeedTransformationFactory
 * 
 * @author awulder
 * 
 */
@Component
public class FeedTransformationFactory {

   @Autowired
   @Qualifier("hudsonFeedTransformer")
   private FeedTransformer hudsonFeedTransformer;

   @Autowired
   @Qualifier("teamCityFeedTransformer")
   private FeedTransformer teamCityFeedTransformer;


   public FeedTransformer getFeedTransformer(Vendor vendor) throws UnknownVendorException {
      if (vendor == null) {
         throw new IllegalArgumentException("vendor may not be null");
      }

      switch (vendor) {
         case HUDSON:
            return hudsonFeedTransformer;
         case TEAM_CITY:
            return teamCityFeedTransformer;
         default:
            throw new UnknownVendorException("vendor [" + vendor + "] is not recognized by the system");
      }
   }


   public void setHudsonFeedTransformer(FeedTransformer hudsonFeedTransformer) {
      this.hudsonFeedTransformer = hudsonFeedTransformer;
   }


   public void setTeamCityFeedTransformer(FeedTransformer teamCityFeedTransformer) {
      this.teamCityFeedTransformer = teamCityFeedTransformer;
   }

}
