/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.util.corvus;

import static android.os.UserHandle.USER_SYSTEM;

import android.app.UiModeManager;
import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.util.Log;

public class ThemesUtils {

    public static final String TAG = "ThemesUtils";

    public static final String[] ADAPTIVE_ICON_SHAPE = {
            "com.android.theme.icon.teardrop",
            "com.android.theme.icon.squircle",
            "com.android.theme.icon.roundedrect",
            "com.android.theme.icon.cylinder",
            "com.android.theme.icon.hexagon",
            "com.android.theme.icon.roundedhexagon",
            "com.android.theme.icon.pebble",
            "com.android.theme.icon.tapered_rect",
            "com.android.theme.icon.vessel",
    };

    public static final String[] PITCH_BLACK = {
            "com.android.theme.pitchblack.system",
            "com.android.theme.pitchblack.systemui",
    };

    public static final String[] SOLARIZED_DARK = {
            "com.android.theme.solarizeddark.system",
            "com.android.theme.solarizeddark.systemui",
    };

    public static final String[] DARK_GREY = {
            "com.android.theme.darkgrey.system",
            "com.android.theme.darkgrey.systemui",
    };

    public static final String[] MATERIAL_OCEAN = {
            "com.android.theme.materialocean.system",
            "com.android.theme.materialocean.systemui",
    };

    public static final String[] STATUSBAR_ICONS = {
            "com.android.theme.icon_pack.filled.android",
            "com.android.theme.icon_pack.rounded.android",
            "com.android.theme.icon_pack.circular.android",
            "com.android.theme.icon_pack.kai.android",
            "com.android.theme.icon_pack.sam.android",
            "com.android.theme.icon_pack.victor.android",
    };

    public static final String[] CHOCO_X = {
            "com.android.theme.chocox.system",
            "com.android.theme.chocox.systemui",
    };

    public static final String[] BAKED_GREEN = {
            "com.android.theme.bakedgreen.system",
            "com.android.theme.bakedgreen.systemui",
    };

    public static final String[] CORVUS_CLEAR = {
            "com.android.theme.corvusclear.system",
            "com.android.theme.corvusclear.systemui",
    };

    public static final String[] NAVBAR_STYLES = {
            "com.android.theme.navbar.asus",
            "com.android.theme.navbar.oneplus",
            "com.android.theme.navbar.oneui",
            "com.android.theme.navbar.tecno",
    };

    public static final String[] QS_HEADER_THEMES = {
            "com.android.systemui.qsheader.grey",
            "com.android.systemui.qsheader.lightgrey",
            "com.android.systemui.qsheader.accent", 
            "com.android.systemui.qsheader.transparent",
    };

    public static final String[] SWITCH_STYLE = {
        "com.android.system.switch.md2", // 1
        "com.android.system.switch.oneplus", // 2
        "com.android.system.switch.telegram", // 3
        "com.android.system.switch.narrow", // 4
        "com.android.system.switch.contained", // 5
        "com.android.system.switch.retro", // 6
        "com.android.system.switch.stockish", // 7
    };

    // QS Tile Styles
    public static final String[] QS_TILE_THEMES = {
            "com.android.systemui.qstile.default", // 1
            "com.android.systemui.qstile.square", // 2
            "com.android.systemui.qstile.squircletrim", // 3
            "com.android.systemui.qstile.diamond", // 4
            "com.android.systemui.qstile.star", // 5
            "com.android.systemui.qstile.gear", // 6
            "com.android.systemui.qstile.badge",// 7
            "com.android.systemui.qstile.badgetwo", // 8
            "com.android.systemui.qstile.circletrim", // 9
            "com.android.systemui.qstile.dualtonecircletrim", // 10
            "com.android.systemui.qstile.cookie", // 11
            "com.android.systemui.qstile.circleoutline", // 12
            "com.android.systemui.qstile.wavey", // 13
            "com.android.systemui.qstile.ninja", // 14
            "com.android.systemui.qstile.dottedcircle", // 15
            "com.android.systemui.qstile.attemptmountain", // 16
            "com.android.systemui.qstile.inktober", // 17
            "com.android.systemui.qstile.neonlike", // 18
            "com.android.systemui.qstile.triangles", // 19
            "com.bootleggers.qstile.oos", // 20
            "com.bootleggers.qstile.divided", // 21
            "com.bootleggers.qstile.cosmos", // 22
    };

    // Switches qs tile style to user selected.
    public static void updateNewTileStyle(IOverlayManager om, int userId, int qsTileStyle) {
        if (qsTileStyle == 0) {
            stockNewTileStyle(om, userId);
        } else {
            try {
                om.setEnabled(QS_TILE_THEMES[qsTileStyle],
                        true, userId);
            } catch (RemoteException e) {
                Log.w(TAG, "Can't change qs tile style", e);
            }
        }
    }

    // Switches qs tile style back to stock.
    public static void stockNewTileStyle(IOverlayManager om, int userId) {
        // skip index 0
        for (int i = 1; i < QS_TILE_THEMES.length; i++) {
            String qstiletheme = QS_TILE_THEMES[i];
            try {
                om.setEnabled(qstiletheme,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    public static final String[] STATUSBAR_HEIGHT = {
            "com.gnonymous.gvisualmod.sbh_m", // 1
            "com.gnonymous.gvisualmod.sbh_l", // 2
            "com.gnonymous.gvisualmod.sbh_xl", // 3
    };

    public static final String[] UI_RADIUS = {
            "com.gnonymous.gvisualmod.urm_r", // 1
            "com.gnonymous.gvisualmod.urm_m", // 2
            "com.gnonymous.gvisualmod.urm_l", // 3
    };
}
