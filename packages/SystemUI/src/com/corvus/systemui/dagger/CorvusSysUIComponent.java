package com.corvus.systemui.dagger;

import com.android.systemui.dagger.DefaultComponentBinder;
import com.android.systemui.dagger.DependencyProvider;
import com.android.systemui.dagger.SysUISingleton;
import com.android.systemui.dagger.SystemUIBinder;
import com.android.systemui.dagger.SysUIComponent;
import com.android.systemui.dagger.SystemUIModule;

import com.corvus.systemui.keyguard.CorvusKeyguardSliceProvider;
import com.corvus.systemui.smartspace.KeyguardSmartspaceController;
import com.corvus.systemui.columbus.ColumbusModule;

import dagger.Subcomponent;

@SysUISingleton
@Subcomponent(modules = {
        ColumbusModule.class,
        DefaultComponentBinder.class,
        DependencyProvider.class,
        CorvusSystemUIBinder.class,
        SystemUIModule.class,
        CorvusSystemUIModule.class})
public interface CorvusSysUIComponent extends SysUIComponent {
    @SysUISingleton
    @Subcomponent.Builder
    interface Builder extends SysUIComponent.Builder {
        CorvusSysUIComponent build();
    }

    /**
     * Member injection into the supplied argument.
     */
    void inject(CorvusKeyguardSliceProvider keyguardSliceProvider);

    @SysUISingleton
    KeyguardSmartspaceController createKeyguardSmartspaceController();
}
