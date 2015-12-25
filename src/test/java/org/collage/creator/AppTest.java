package org.collage.creator;

import collage.Application;
import collage.CheckSiteStatusController;
import collage.ConfigProperty;
import collage.controller.ImageFactory;
import collage.controller.ImageProperty;
import collage.controller.collage.ImageCreator;
import collage.entity.Image;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.apache.log4j.Logger;
import twitter4j.TwitterException;

import java.util.List;

@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {

    @Autowired
    private ImageFactory imageFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ImageCreator imageCreator;

    @Autowired
    private CheckSiteStatusController checkSiteStatusController;

    private final static Logger logger = Logger.getLogger(AppTest.class);

    @Test
    public void checkBackOf() {

        String url = "http://localhost:8089/status503";
        Assert.assertFalse(checkSiteStatusController.checkSiteStatus(url));
    }

    @Test
    public void checkRandomImageProperties(){
        ImageProperty imageProperty = (ImageProperty) applicationContext.getBean(ConfigProperty.PROP[0]);
        int width = 100;
        int height = 100;
        try {
            List<Image> images = imageFactory.getUserImages("byteUA", 100, 100, imageProperty);
            for (Image img : images){
                Assert.assertTrue(img.getX() >= 0 && img.getX() <= width);
                Assert.assertTrue(img.getY() >= 0 && img.getY() <= height);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
