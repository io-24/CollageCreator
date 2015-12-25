package org.collage.creator;

import collage.Application;
import collage.ConfigProperty;
import collage.controller.ImageFactory;
import collage.controller.ImageProperty;
import collage.entity.Image;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import twitter4j.TwitterException;

import java.util.List;


@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class FunctionalTest {
    @Autowired
    private ImageFactory imageFactory;

    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void checkUserName() {
        try {
            Assert.assertTrue(imageFactory.check("byte") != null);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkImageList(){
        ImageProperty imageProperty = (ImageProperty) applicationContext.getBean(ConfigProperty.PROP[0]);
        try {
            Assert.assertTrue(imageFactory.getUserImages("byte",300,300,imageProperty).size() > 0);

        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

}
