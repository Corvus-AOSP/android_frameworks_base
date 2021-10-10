/*
 * Copyright (C) 2021 CorvusOS
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

package com.android.systemui.qs.tiles;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.service.quicksettings.Tile;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.internal.logging.MetricsLogger;

import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.internal.util.corvus.CorvusUtils;
import com.android.systemui.qs.QSHost;
import com.android.systemui.plugins.qs.QSTile.BooleanState;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.dagger.qualifiers.Background;
import com.android.systemui.dagger.qualifiers.Main;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.R;

import javax.inject.Inject;

public class CorvusZenTile extends QSTileImpl<BooleanState> {

    private final Icon mIcon = ResourceIcon.get(R.drawable.ic_qs_corvuszen);

    @Inject
    public CorvusZenTile(QSHost host,
            @Background Looper backgroundLooper,
            @Main Handler mainHandler,
            FalsingManager falsingManager,
            MetricsLogger metricsLogger,
            StatusBarStateController statusBarStateController,
            ActivityStarter activityStarter,
            QSLogger qsLogger) {
        super(host, backgroundLooper, mainHandler, falsingManager, metricsLogger,
                statusBarStateController, activityStarter, qsLogger);
    }

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.CORVUS;
    }

    @Override
    public void handleClick(@Nullable View view) {
        mHost.collapsePanels();
        if (CorvusUtils.isPackageInstalled(mContext, "com.corvus.corvuszen")) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("com.corvus.corvuszen", 
            "com.corvus.corvuszen.MainActivity");
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, mContext.getString(
                    R.string.quick_settings_corvus_zen_unavailable), Toast.LENGTH_LONG).show();
        }
        refreshState();
    }

    @Override
    public Intent getLongClickIntent() {
        return null;
    }

    @Override
    public CharSequence getTileLabel() {
        return mContext.getString(R.string.quick_settings_corvus_zen);
    }

    @Override
    public void handleUpdateState(BooleanState state, Object arg) {
        state.icon = mIcon;
        state.label = mContext.getString(R.string.quick_settings_corvus_zen);
        state.contentDescription = mContext.getString(
                R.string.quick_settings_corvus_zen);
        state.state = Tile.STATE_INACTIVE;
    }

    @Override
    public BooleanState newTileState() {
        BooleanState state = new BooleanState();
        state.handlesLongClick = false;
        return state;
    }

    @Override
    public void handleSetListening(boolean listening) {
    }
}
