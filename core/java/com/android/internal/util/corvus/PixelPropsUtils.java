/*
 * Copyright (C) 2020 The Pixel Experience Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.util.corvus;

import android.os.Build;
import android.util.Log;

import java.util.Arrays;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PixelPropsUtils {

    private static final String TAG = PixelPropsUtils.class.getSimpleName();
    private static final boolean DEBUG = false;

    private static final Map<String, Object> propsToChange;
    private static final Map<String, Object> propsToChangePixelXL;
    private static final Map<String, Object> propsToChangePixel3XL;

    private static final String[] packagesToChange = {
	"com.android.vending",
        "com.breel.wallpapers20",
        "com.google.android.apps.customization.pixel",
        "com.google.android.apps.fitness",
        "com.google.android.apps.recorder",
        "com.google.android.apps.subscriptions.red",
        "com.google.android.apps.tachyon",
        "com.google.android.apps.turboadapter",
        "com.google.android.apps.wallpaper.pixel",
        "com.google.android.as",
        "com.google.android.dialer",
        "com.google.android.gms.location.history",
        "com.google.android.inputmethod.latin",
        "com.google.android.soundpicker",
        "com.google.pixel.dynamicwallpapers",
        "com.google.pixel.livewallpaper",
        "com.google.android.apps.safetyhub",
        "com.google.android.apps.turbo",
        "com.google.android.apps.wallpaper",
        "com.google.android.apps.maps",
        "com.google.android.gms",
        "com.google.android.apps.nexuslauncher"
    };

    private static final String[] packagesToChangePixelXL = {
            "com.google.android.apps.photos"
    };

    private static final String[] packagesToChangePixel3XL = {
        "com.google.android.googlequicksearchbox"
    };

    private static final Map<String, Object> propsToChangeROG1;
    private static final String[] packagesToChangeROG1 = {
            "com.dts.freefireth",
            "com.dts.freefiremax",
            "com.madfingergames.legends"
    };

    private static final Map<String, Object> propsToChangeXP5;
    private static final String[] packagesToChangeXP5 = {
            "com.activision.callofduty.shooter",
            "com.tencent.tmgp.kr.codm",
            "com.garena.game.codm",
            "com.vng.codmvn"
    };

    private static final Map<String, Object> propsToChangeOP8P;
    private static final String[] packagesToChangeOP8P = {
            "com.tencent.ig",
            "com.pubg.imobile",
            "com.pubg.krmobile",
            "com.pubg.newstate",
            "com.vng.pubgmobile",
            "com.rekoo.pubgm",
            "com.tencent.tmgp.pubgmhd",
            "com.riotgames.league.wildrift",
            "com.riotgames.league.wildrifttw",
            "com.riotgames.league.wildriftvn",
            "com.netease.lztgglobal",
            "com.epicgames.fortnite",
            "com.epicgames.portal"
    };

    static {
        propsToChange = new HashMap<>();
        propsToChange.put("BRAND", "google");
        propsToChange.put("MANUFACTURER", "Google");
        propsToChange.put("DEVICE", "walleye");
        propsToChange.put("PRODUCT", "walleye");
        propsToChange.put("MODEL", "Pixel 2");
        propsToChange.put("FINGERPRINT", "google/walleye/walleye:8.1.0/OPM1.171019.011/4448085:user/release-keys");
	propsToChangePixelXL = new HashMap<>();
        propsToChangePixelXL.put("BRAND", "google");
        propsToChangePixelXL.put("MANUFACTURER", "Google");
        propsToChangePixelXL.put("DEVICE", "marlin");
        propsToChangePixelXL.put("PRODUCT", "marlin");
        propsToChangePixelXL.put("MODEL", "Pixel XL");
        propsToChangePixelXL.put("FINGERPRINT", "google/marlin/marlin:10/QP1A.191005.007.A3/5972272:user/release-keys");
	propsToChangePixel3XL = new HashMap<>();
        propsToChangePixel3XL.put("BRAND", "google");
        propsToChangePixel3XL.put("MANUFACTURER", "Google");
        propsToChangePixel3XL.put("DEVICE", "crosshatch");
        propsToChangePixel3XL.put("PRODUCT", "crosshatch");
        propsToChangePixel3XL.put("MODEL", "Pixel 3 XL");
        propsToChangePixel3XL.put("FINGERPRINT", "google/crosshatch/crosshatch:11/RQ3A.210605.005/7349499:user/release-keys");
    propsToChangeROG1 = new HashMap<>();
        propsToChangeROG1.put("MODEL", "ASUS_Z01QD");
        propsToChangeROG1.put("MANUFACTURER", "asus");
    propsToChangeXP5 = new HashMap<>();
        propsToChangeXP5.put("MODEL", "SO-52A");
    propsToChangeOP8P = new HashMap<>();
        propsToChangeOP8P.put("MODEL", "IN2020");
        propsToChangeOP8P.put("MANUFACTURER", "OnePlus");        
    }

    public static void setProps(String packageName) {
        if (packageName == null) {
            return;
        }
        if (Arrays.asList(packagesToChange).contains(packageName)) {
            if (DEBUG) {
                Log.d(TAG, "Defining props for: " + packageName);
            }
            for (Map.Entry<String, Object> prop : propsToChange.entrySet()) {
                String key = prop.getKey();
                Object value = prop.getValue();
                setPropValue(key, value);
            }
        }
	if (Arrays.asList(packagesToChangePixelXL).contains(packageName)){
            if (DEBUG){
                Log.d(TAG, "Defining props for: " + packageName);
            }
            for (Map.Entry<String, Object> prop : propsToChangePixelXL.entrySet()) {
                String key = prop.getKey();
                Object value = prop.getValue();
                setPropValue(key, value);
            }
        }
	if (Arrays.asList(packagesToChangePixel3XL).contains(packageName)) {
            if (DEBUG) {
                Log.d(TAG, "Defining props for: " + packageName);
            }
            for (Map.Entry<String, Object> prop : propsToChangePixel3XL.entrySet()) {
                String key = prop.getKey();
                Object value = prop.getValue();
                setPropValue(key, value);
            }
        }
        // Set proper indexing fingerprint
    if (packageName.equals("com.google.android.settings.intelligence")) {
            setPropValue("FINGERPRINT", Build.DATE);
        }
            } else {
                if (Arrays.asList(packagesToChangeROG1).contains(packageName)) {
                    if (DEBUG) Log.d(TAG, "Defining props for: " + packageName);
                    for (Map.Entry<String, Object> prop : propsToChangeROG1.entrySet()) {
                        String key = prop.getKey();
                        Object value = prop.getValue();
                        setPropValue(key, value);
                    }
            } else if (Arrays.asList(packagesToChangeXP5).contains(packageName)) {
                if (DEBUG) Log.d(TAG, "Defining props for: " + packageName);
                    for (Map.Entry<String, Object> prop : propsToChangeXP5.entrySet()) {
                        String key = prop.getKey();
                        Object value = prop.getValue();
                        setPropValue(key, value);
                }
            } else if (Arrays.asList(packagesToChangeOP8P).contains(packageName)) {
                if (DEBUG) Log.d(TAG, "Defining props for: " + packageName);
                    for (Map.Entry<String, Object> prop : propsToChangeOP8P.entrySet()) {
                        String key = prop.getKey();
                        Object value = prop.getValue();
                        setPropValue(key, value);
                    }
            }        
        }

    private static void setPropValue(String key, Object value) {
        try {
            if (DEBUG) {
                Log.d(TAG, "Defining prop " + key + " to " + value.toString());
            }
            Field field = Build.class.getDeclaredField(key);
            field.setAccessible(true);
            field.set(null, value);
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Log.e(TAG, "Failed to set prop " + key, e);
        }
    }

}
