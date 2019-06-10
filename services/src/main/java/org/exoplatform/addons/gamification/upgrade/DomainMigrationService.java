package org.exoplatform.addons.gamification.upgrade;


import org.exoplatform.addons.gamification.entities.domain.effective.GamificationActionsHistory;
import org.exoplatform.addons.gamification.service.configuration.BadgeService;
import org.exoplatform.addons.gamification.service.configuration.DomainService;
import org.exoplatform.addons.gamification.service.configuration.RuleService;
import org.exoplatform.addons.gamification.service.dto.configuration.BadgeDTO;
import org.exoplatform.addons.gamification.service.dto.configuration.DomainDTO;
import org.exoplatform.addons.gamification.service.dto.configuration.RuleDTO;
import org.exoplatform.addons.gamification.service.mapper.DomainMapper;
import org.exoplatform.addons.gamification.storage.dao.GamificationHistoryDAO;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.picocontainer.Startable;

import java.util.List;

public class DomainMigrationService implements Startable {

    private static final Log LOG = ExoLogger.getLogger(DomainMigrationService.class);



    protected RuleService ruleService;
    protected DomainService domainService;
    protected BadgeService badgeService;
    protected GamificationHistoryDAO gamificationHistoryDAO;
    protected DomainMapper domainMapper;

    public DomainMigrationService(RuleService ruleService, DomainService domainService, BadgeService badgeService, GamificationHistoryDAO gamificationHistoryDAO, DomainMapper domainMapper) {

        this.ruleService = ruleService;
        this.domainService = domainService;
        this.badgeService = badgeService;
        this.gamificationHistoryDAO = gamificationHistoryDAO;
        this.domainMapper = domainMapper;
    }

    @Override
    public void start() {
        long startTime = System.currentTimeMillis();
        boolean entitiesToMigrate = false;
        try {
            LOG.info("=== Start migration of Rules");
            List<String> domains = ruleService.getDomainListFromRules();
            for(String title : domains){
                DomainDTO domainDTO = domainService.findDomainByTitle(title);
                if(domainDTO!=null){
                   List<RuleDTO> rules = ruleService.getAllRulesByDomain(title);
                   if(rules != null && rules.size()>0){
                       for(RuleDTO rule : rules){
                           if (rule.getDomainDTO()==null){
                               if(!entitiesToMigrate){ entitiesToMigrate = true; }
                               rule.setDomainDTO(domainDTO);
                               ruleService.updateRule(rule);
                           }
                       }
                    }
                }
            }

            List<RuleDTO> rules = ruleService.getAllRulesWithNullDomain();
            if(rules != null && rules.size()>0){
                entitiesToMigrate = true;
                for(RuleDTO rule : rules){
                    DomainDTO domainDTO = domainService.findDomainByTitle(rule.getArea());
                    if (domainDTO!=null){
                        rule.setDomainDTO(domainDTO);
                        ruleService.updateRule(rule);
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            if(entitiesToMigrate){
                LOG.info("=== Migration of Rules is done in " + (endTime - startTime) + " ms");
            }else{
                LOG.info("=== No rules to migrate");
            }

        } catch (Exception e) {
            LOG.error("Error when migration Rules ", e);
        }

        try {
            LOG.info("=== Start migration of Badges");
            startTime = System.currentTimeMillis();
            entitiesToMigrate = false;
            List<String> domains = badgeService.getDomainListFromBadges();
            for(String title : domains){
                DomainDTO domainDTO = domainService.findDomainByTitle(title);
                if(domainDTO!=null){
                    List<BadgeDTO> badges = badgeService.findBadgesByDomain(title);
                    if(badges != null && badges.size()>0){
                        for(BadgeDTO badge : badges){
                            if (badge.getDomainDTO()==null){
                                if(!entitiesToMigrate){ entitiesToMigrate = true; }
                                badge.setDomainDTO(domainDTO);
                                badgeService.updateBadge(badge);
                            }
                        }
                    }
                }
            }

            List<BadgeDTO> badges = badgeService.getAllBadgesWithNullDomain();
            if(badges != null && badges.size()>0){
                entitiesToMigrate = true;
                for(BadgeDTO badge : badges){
                    DomainDTO domainDTO = domainService.findDomainByTitle(badge.getDomain());
                    if (domainDTO!=null){
                        badge.setDomainDTO(domainDTO);
                        badgeService.updateBadge(badge);
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            if(entitiesToMigrate){
                LOG.info("=== Migration of Badges is done in " + (endTime - startTime) + " ms");
            }else{
                LOG.info("=== No badges to migrate");
            }

        } catch (Exception e) {
            LOG.error("Error when migration Rules ", e);
        }


        try {
            LOG.info("=== Start migration of History");
            startTime = System.currentTimeMillis();
            entitiesToMigrate = false;
            List<String>  domains = gamificationHistoryDAO.getDomainList();
            for(String title : domains){
                DomainDTO domainDTO = domainService.findDomainByTitle(title);
                if(domainDTO!=null){
                    List<GamificationActionsHistory> points = gamificationHistoryDAO.getAllPointsByDomain(title);
                    if(points != null && points.size()>0){
                        for(GamificationActionsHistory point : points){
                            if (point.getDomainEntity()==null){
                                point.setDomainEntity(domainMapper.domainDTOToDomain(domainDTO));
                                gamificationHistoryDAO.update(point);
                            }
                        }
                    }
                }
            }

            List<GamificationActionsHistory> points = gamificationHistoryDAO.getAllPointsWithNullDomain();
            if(points != null && points.size()>0){
                for(GamificationActionsHistory point : points){
                    DomainDTO domainDTO = domainService.findDomainByTitle(point.getDomain());
                    if (domainDTO!=null){
                        point.setDomainEntity(domainMapper.domainDTOToDomain(domainDTO));
                        gamificationHistoryDAO.update(point);
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            if(entitiesToMigrate){
            LOG.info("=== Migration of Points is done in " + (endTime - startTime) + " ms");
            }else{
                LOG.info("=== No Points to migrate");
            }
        } catch (Exception e) {
            LOG.error("Error when migration Points ", e);
        }

    }

    @Override
    public void stop() {

    }

    }