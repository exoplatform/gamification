package org.exoplatform.gamification.service.setting.zone.impl;

import org.exoplatform.commons.api.settings.SettingService;
import org.exoplatform.commons.api.settings.SettingValue;
import org.exoplatform.commons.api.settings.data.Context;
import org.exoplatform.commons.api.settings.data.Scope;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.gamification.service.setting.zone.ZonePlugin;
import org.exoplatform.gamification.service.setting.zone.model.ZoneConfig;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.picocontainer.Startable;

import java.util.HashMap;
import java.util.Map;

public class ZonePluginImpl implements Startable, ZonePlugin {

    private static final Log LOG = ExoLogger.getLogger(ZonePluginImpl.class);

    private static final String GAMIFICATION_PREFERENCES = "gamification:";

    private final Map<String, ZoneConfig> zoneMap;

    public ZonePluginImpl() {
        this.zoneMap = new HashMap<String, ZoneConfig>();
    }

    @Override
    public void addPlugin(ZoneConfig zone) {
        zoneMap.put(zone.getZoneName(), zone);
    }

    @Override
    public boolean remove(ZoneConfig zone) {
        return false;
    }

    @Override
    public void start() {

        for (ZoneConfig zone : zoneMap.values()) {
            CommonsUtils.getService(SettingService.class).set(Context.GLOBAL, Scope.GLOBAL.id(null), (GAMIFICATION_PREFERENCES + zone.getZoneName()), SettingValue.create(zone.getZoneName()));

        }

    }

    @Override
    public void stop() {

    }
}
