package sample.controllers;

import java.util.prefs.Preferences;

public class SettingsViewController {
    private final Preferences preferences;

    private final String UP = "UP";
    private final String DOWN = "DOWN";
    private final String LEFT = "LEFT";
    private final String RIGHT = "RIGHT";

    public SettingsViewController() {
        preferences = Preferences.userRoot().node(SettingsViewController.class.getName());
    }
}
