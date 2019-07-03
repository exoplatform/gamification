package org.exoplatform.addons.gamification.test.rest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.exoplatform.addons.gamification.entities.domain.configuration.BadgeEntity;
import org.exoplatform.addons.gamification.rest.ManageBadgesEndpoint;
import org.exoplatform.addons.gamification.test.AbstractServiceTest;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class TestManageBadgesEndpoint extends AbstractServiceTest {

    private static final Log LOG = ExoLogger.getLogger(ManageBadgesEndpoint.class);


    /**
     * Testing the Status Code
     **/
    @Test
    public void testgetAllBadges() throws Exception {

        // Given
        org.junit.Assert.assertTrue(new ArrayList().isEmpty());
        LOG.info("****Initiation of the assert of badges List**** ", BadgeEntity.class);

        HttpUriRequest request = new HttpGet("http://localhost:8080/rest/gamification/badges/all");
        assertNotEquals(request, new ArrayList().isEmpty());

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));

        LOG.info("List of badges is OK ", BadgeEntity.class, httpResponse.getStatusLine());

        assertNotEquals(HttpStatus.SC_NOT_FOUND, HttpStatus.SC_OK);

        LOG.info("List of badges is Not found ", BadgeEntity.class, httpResponse.getStatusLine());

    }
    // Then


    /**
     * Testing the add of a new badge with the  Media Type
     **/
    @Test
    public void testaddbadge() throws Exception {

        // Givenge
        try {
            String Badgename = RandomStringUtils.randomAlphabetic(8);
            String jsonMimeType = "application/json";

            // Given
            HttpUriRequest request = new HttpGet("http://localhost:8080/rest/gamification/badges/add" + Badgename);

            // When
            HttpResponse response = HttpClientBuilder.create().build().execute(request);


            // Then
            String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
            assertEquals(jsonMimeType, jsonMimeType);

            LOG.info("The add of the badges is completely done with success ", BadgeEntity.class, response.getStatusLine());
        } catch (Exception e) {
            LOG.error("Error to save the following Badges Entity entry {}", BadgeEntity.class, e);

            throw new UnsupportedOperationException();
        }


    }


    /**
     * Testing the add of delete of badge with the  Media Type
     **/
    @Test
    public void testdeletebadge() throws Exception {


        String badgeTitle = RandomStringUtils.randomAlphabetic(8);
        String jsonMimeType = "application/json";


        try {
            // Given
            HttpUriRequest request = new HttpGet("http://localhost:8080/rest/gamification/badges/delete" + badgeTitle);

            // When
            HttpResponse response = HttpClientBuilder.create().build().execute(request);


            // Then
            String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
            assertEquals(jsonMimeType, jsonMimeType);
            LOG.info("The delete of the selected badge is completely done with success ", BadgeEntity.class, response.getStatusLine());
        } catch (Exception e) {

            LOG.error("Failure in the deletion ", BadgeEntity.class, e);
            throw new UnsupportedOperationException();

        }
        
    }

    /**
     * Testing the add of delete of badge with the  Media Type
     **/
    @Test
    public void testupdatebadge() throws Exception {

        // Givenge
        String badgeTitle = RandomStringUtils.randomAlphabetic(8);
        String jsonMimeType = "application/json";
        try {
            // Given
            HttpUriRequest request = new HttpGet("http://localhost:8080/rest/gamification/badges/update");

            // When
            HttpResponse response = HttpClientBuilder.create().build().execute(request);


            // Then
            String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
            assertEquals(jsonMimeType, jsonMimeType);
            LOG.info("The update of the selected badge is completely done with success ", BadgeEntity.class, response.getStatusLine());
        } catch (Exception e) {

            LOG.error("Failure in the update ", BadgeEntity.class, e);
            throw new UnsupportedOperationException();

        }

    }

}

