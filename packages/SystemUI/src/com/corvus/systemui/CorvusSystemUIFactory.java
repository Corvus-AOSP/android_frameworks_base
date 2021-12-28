package com.corvus.systemui;

import android.content.Context;

import com.corvus.systemui.dagger.CorvusGlobalRootComponent;
import com.corvus.systemui.dagger.DaggerCorvusGlobalRootComponent;

import com.android.systemui.SystemUIFactory;
import com.android.systemui.dagger.GlobalRootComponent;

public class CorvusSystemUIFactory extends SystemUIFactory {
    @Override
    protected GlobalRootComponent buildGlobalRootComponent(Context context) {
        return DaggerCorvusGlobalRootComponent.builder()
                .context(context)
                .build();
    }
}
