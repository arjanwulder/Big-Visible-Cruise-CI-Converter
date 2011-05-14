package nl.mirabeau.ci.rss.feed.service;

import nl.mirabeau.ci.rss.feed.common.Validate;
import nl.mirabeau.ci.rss.feed.domain.Projects;
import nl.mirabeau.ci.rss.feed.domain.enumeration.Vendor;
import nl.mirabeau.ci.rss.feed.exception.UnknownVendorException;
import nl.mirabeau.ci.rss.feed.transformer.FeedTransformer;
import nl.mirabeau.ci.rss.feed.transformer.builder.LinkBuilder;
import nl.mirabeau.ci.rss.feed.transformer.factory.FeedTransformationFactory;
import nl.mirabeau.ci.rss.feed.transformer.factory.LinkBuilderFactory;
import nl.mirabeau.ci.rss.feed.common.VendorUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("transformService")
public class TransformServiceImpl implements TransformService {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private FeedTransformationFactory feedTransformationFactory;

    @Autowired
    private LinkBuilderFactory linkBuilderFactory;

    @Override
    public Projects transformFeed(String vendor, String view) {
        Validate.notNull(vendor);
        Validate.hasText(view);

        try {
            Vendor vendorAsEnum = VendorUtils.getVendorAsEnum(vendor);
            FeedTransformer feedTransformer = feedTransformationFactory.getFeedTransformer(vendorAsEnum);

            LinkBuilder linkBuilder = linkBuilderFactory.getLinkBuilder(vendorAsEnum);
            return feedTransformer.transform(linkBuilder.buildUrl(view));
        } catch (UnknownVendorException e) {
            logger.error(e);
        }

        return null;
    }

    @Override
    public Projects transformFeed(String vendor, String view, String parameters) {
        Validate.notNull(vendor);
        Validate.hasText(view);

        try {
            Vendor vendorAsEnum = VendorUtils.getVendorAsEnum(vendor);
            FeedTransformer feedTransformer = feedTransformationFactory.getFeedTransformer(vendorAsEnum);

            LinkBuilder linkBuilder = linkBuilderFactory.getLinkBuilder(vendorAsEnum);
            return feedTransformer.transform(linkBuilder.buildUrl(view, parameters));
        } catch (UnknownVendorException e) {
            logger.error(e);
        }

        return null;
    }

    public void setFeedTransformationFactory(FeedTransformationFactory feedTransformationFactory) {
        this.feedTransformationFactory = feedTransformationFactory;
    }

    public void setLinkBuilderFactory(LinkBuilderFactory linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

}
