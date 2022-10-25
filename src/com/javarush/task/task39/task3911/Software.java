package com.javarush.task.task39.task3911;

import java.util.*;
import java.util.stream.Collectors;

public class Software {
    private int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        if (rollbackVersion == currentVersion || !versionHistoryMap.containsKey(rollbackVersion)) {
            return false;
        }
        versionHistoryMap = versionHistoryMap.entrySet().stream()
                .filter(mapEntry -> mapEntry.getKey() <= rollbackVersion)
                .peek(mapEntry -> setNewVersion(rollbackVersion, mapEntry))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return true;
    }

    private void setNewVersion(int rollbackVersion, Map.Entry<Integer, String> mapEntry) {
        if (mapEntry.getKey() == rollbackVersion)
            currentVersion = rollbackVersion;
    }

}
