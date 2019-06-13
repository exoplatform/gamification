package org.exoplatform.addons.gamification.service;
import com.sun.istack.Nullable;
import org.exoplatform.commons.testing.BaseExoTestCase;
import org.apache.commons.lang3.StringUtils;
import org.exoplatform.addons.gamification.GamificationUtils;
import org.exoplatform.addons.gamification.entities.domain.effective.GamificationActionsHistory;
import org.exoplatform.addons.gamification.service.effective.GamificationService;
import org.exoplatform.addons.gamification.storage.dao.GamificationHistoryDAO;
import org.exoplatform.addons.gamification.test.AbstractServiceTest;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.addons.gamification.service.dto.configuration.RuleDTO;
import org.junit.Before;
import org.exoplatform.social.core.identity.model.Identity;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TestGamificationService extends AbstractServiceTest {
    /*Gamification domain such us (Social, Knowledge, Teamwork ,Knowledge....)*/
    private static final String GAMIFICATION_DOMAIN     ="TeamWork";
    private static final String RULE_NAME      ="Update a new Task";
    /*Receiver */
    private static final String TEST_USER_RECEIVER = "55";
    /*Sender */
    private static final String TEST_USER_SENDER = "1";
    /*Link to the activity stream */
    private static final String TEST_LINK_ACTIVITY = "/activity?id=245590";

    private static final String TEST_GLOBAL_SCORE = "245590";




    List<RuleDTO> ruleDTOList = new ArrayList<RuleDTO>();
    GamificationService gamificationService;
    GamificationHistoryDAO gamificationHistoryDAO;



    @Before
    public void setUp() throws Exception {

        super.setUp();

        gamificationService = CommonsUtils.getService(GamificationService.class);

        // Needed for exchange points between users
        Identity testUserReceiver = new Identity(TEST_USER_RECEIVER);
        Identity testUserSender = new Identity(TEST_USER_SENDER);


    }
    public GamificationActionsHistory build(RuleDTO ruleDto, String testUserSender, String testUserReceiver, String objectId) throws Exception{

        org.exoplatform.social.core.identity.model.Identity actorIdentity = identityManager.getIdentity(testUserSender, false);

        GamificationActionsHistory aHistory = null;
        // check if the current user is not a bot
        actorIdentity = identityManager.getIdentity(actorIdentity.getId(), false);
        if (actorIdentity == null || StringUtils.isBlank(( ((org.exoplatform.social.core.identity.model.Identity) actorIdentity).getRemoteId())) ){
            fail("Actor {} has earned some points but doesn't have a social identity");
            return null;
        }
        if (actorIdentity.isDeleted()) {
            fail("Actor {} has earned some points but is marked as deleted");
            return null;
        }
        if (!actorIdentity.isEnable()) {
            fail("Actor {} has earned some points but is marked as disabled");
            return null;

        }
        String actorRemoteId = actorIdentity.getRemoteId();
        if (GamificationUtils.isBlackListed(actorRemoteId)) {
            fail("Actor {} has earned some points but is marked as blacklisted");
            return null;
        }

        // Buil only an entry when a rule enable and exist
        if (ruleDto != null) {
            aHistory = new GamificationActionsHistory();
            aHistory.setActionScore(ruleDto.getScore());
            try {
                aHistory.setGlobalScore(computeTotalScore(testUserSender) + ruleDto.getScore());
            } catch (Exception e) {
                e.printStackTrace();
            }
            aHistory.setDate(new Date());
            aHistory.setUserSocialId(testUserSender);

            aHistory.setActionTitle(RULE_NAME);
            aHistory.setDomain(GAMIFICATION_DOMAIN);
            aHistory.setReceiver(testUserReceiver);
            aHistory.setObjectId(TEST_LINK_ACTIVITY);
            // Set update metadata
            aHistory.setLastModifiedDate(new Date());
            aHistory.setLastModifiedBy("Gamification Inner Process");
            // Set create metadata
            aHistory.setCreatedBy("Gamification Inner Process");

        }
        return aHistory;



    }

    public void testSaveActionHistory() {
        GamificationActionsHistory aHistory = null;

        try {

            if (aHistory != null) {
                gamificationService.saveActionHistory(aHistory);
            }


        } catch (Exception e) {
        fail("Error to save an actionHistory entry ",e);
        }

    }


    //Needed for the computed score

    public long computeTotalScore(String actorIdentity) throws Exception{
        return gamificationHistoryDAO.computeTotalScore(actorIdentity);
    }



    public void testAddActivityOnUserStream() throws Exception {
        Identity maryIdentity = identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, "mary", false);
    }




    public GamificationActionsHistory FindLatestActionHistoryBySocialId(String actorIdentity) {
        List<GamificationActionsHistory>  aHistory = null;

        try {




                aHistory=   gamificationHistoryDAO.findActionsHistoryByUserId(actorIdentity);
                return (aHistory != null && !aHistory.isEmpty()) ? aHistory.get(0) : null;



        }
        catch (Exception e) {
            fail("Error to List an actionHistory associated to userId ",e);
        }
        return null;


    }


    public void testFindLatestActionHistoryBySocialId() {
        List<GamificationActionsHistory> aHistory = null;

        try {

            if (aHistory != null) {
                gamificationService.findLatestActionHistoryBySocialId(TEST_USER_RECEIVER);
            }


        } catch (Exception e) {
            fail("Error to save an actionHistory entry ",e);
        }

    }

    public void testFindUserReputationBySocialId() {
    }

    public void testBuildDomainScoreByUserId() {
    }

    public void testFindActionsHistoryByReceiverId() {
    }

    public void testComputeTotalScore() {
    }

}