package org.exoplatform.addons.gamification.test;

import org.exoplatform.addons.gamification.entities.domain.effective.GamificationActionsHistory;
import org.exoplatform.addons.gamification.service.dto.configuration.RuleDTO;
import org.exoplatform.addons.gamification.service.effective.*;
import org.exoplatform.addons.gamification.storage.dao.GamificationHistoryDAO;
import org.exoplatform.commons.persistence.impl.EntityManagerService;
import org.exoplatform.commons.testing.BaseExoContainerTestSuite;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.social.core.manager.ActivityManager;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.core.manager.RelationshipManager;
import org.exoplatform.social.core.space.spi.SpaceService;
import org.junit.Test;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")

public class GamificationServiceMock extends BaseExoContainerTestSuite {




    @Test
   public GamificationActionsHistory findLatestActionHistoryBySocialId(String userSocialId) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Test
    public List<GamificationActionsHistory> findActionHistoryByDateBySocialId(Date date, String socialId)throws Exception {
        throw new UnsupportedOperationException();
    }

    @Test
    public int bluidCurrentUserRank(String socialId, Date date, String domain) throws Exception {
        throw new UnsupportedOperationException();
    }
    @Test
    public long findUserReputationBySocialId(String socialId)throws Exception {
        throw new UnsupportedOperationException();
    }
    @Test
    public List<ProfileReputation> buildDomainScoreByUserId(String socialId)throws Exception {
        throw new UnsupportedOperationException();
    }
    @Test
    public void saveActionHistory(GamificationActionsHistory aHistory) throws Exception {
        throw new UnsupportedOperationException();

    }
    @Test
    public List<StandardLeaderboard>  filter(LeaderboardFilter filter, boolean isGlobalContext) throws Exception{
        throw new UnsupportedOperationException();

    }
    @Test
    public List<PiechartLeaderboard> buildStatsByUser(String userSocialId) throws Exception {
        throw new UnsupportedOperationException();

    }

    @Test
    public long findUserReputationScoreBetweenDate(String userSocialId, Date fromDate, Date toDate)throws Exception {
        throw new UnsupportedOperationException();

    }
    @Test
    public long findUserReputationScoreByMonth(String userSocialId, Date currentMonth) throws Exception {
        throw new UnsupportedOperationException();

    }
    @Test
    public long findUserReputationScoreByDomainBetweenDate(String userSocialId, String domain, Date fromDate, Date toDate) throws Exception {
        throw new UnsupportedOperationException();

    }
    @Test
    public List<StandardLeaderboard>  findAllLeaderboardBetweenDate(Date fromDate, Date toDate) throws Exception{
        throw new UnsupportedOperationException();

    }
    @Test
    public List<GamificationActionsHistory> findActionsHistoryByReceiverId(String userId, boolean isGlobalContext, int loadCapacity) throws Exception{
        throw new UnsupportedOperationException();

    }
    @Test

    public GamificationActionsHistory build(RuleDTO ruleDto, String actor, String receiver, String objectId) {
        throw new UnsupportedOperationException();
    }
    @Test
    public long computeTotalScore() throws Exception {
        throw new UnsupportedOperationException();
    }
}