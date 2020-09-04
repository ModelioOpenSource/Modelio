/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.app.ui.plugin;

import java.net.Authenticator;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.net.proxy.IProxyService;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.modelio.app.preferences.LogLevelUpdater;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Bundle activator.
 * <p>
 * Also initializes the log level from preferences.
 */
@objid ("002e1cc4-d6b6-1ff2-a7f4-001ec947cd2a")
public class AppUi implements BundleActivator {
    /**
     * The 'app.ui' plugin identifier.
     */
    @objid ("002e2426-d6b6-1ff2-a7f4-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.app.ui";

    @objid ("002e255c-d6b6-1ff2-a7f4-001ec947cd2a")
    private static BundleContext context;

    /**
     * Translated messages service.
     */
    @objid ("002e2b1a-d6b6-1ff2-a7f4-001ec947cd2a")
    public static BundledMessages I18N;

    /**
     * The plugin logger
     */
    @objid ("002e2c46-d6b6-1ff2-a7f4-001ec947cd2a")
    public static PluginLogger LOG;

    /**
     * Access to preferences store.
     */
    @objid ("1abc42ec-f7a8-4a41-bf0c-381ec5f7e41e")
    private static ScopedPreferenceStore PREFERENCES;

    /**
     * @return the bundle context.
     */
    @objid ("002e276e-d6b6-1ff2-a7f4-001ec947cd2a")
    public static BundleContext getContext() {
        return AppUi.context;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path
     * 
     * @param path the path
     * @return the image descriptor
     */
    @objid ("b8c640b3-af95-486d-ac31-6c927ce87c16")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(AppUi.PLUGIN_ID, path);
    }

    /**
     * Access to preferences store.
     * 
     * @return the {@link AppUi#PLUGIN_ID} plugin preferences store.
     */
    @objid ("6a5ec005-b829-45f9-8879-45b31bca9a4c")
    public static IPersistentPreferenceStore getPreferences() {
        return PREFERENCES;
    }

    @objid ("002e225a-d6b6-1ff2-a7f4-001ec947cd2a")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        AppUi.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        AppUi.LOG = new PluginLogger(service.getLogger((String)null));
        AppUi.I18N = new BundledMessages(AppUi.LOG, ResourceBundle.getBundle("appui"));
        AppUi.PREFERENCES = new ScopedPreferenceStore(InstanceScope.INSTANCE, AppUi.PLUGIN_ID);
        
        initializeLogLevel();
        
        // Remove Eclipse 3.x URLConnection authenticator
        overrideAuthenticator(bundleContext);
    }

    @objid ("002e26e2-d6b6-1ff2-a7f4-001ec947cd2a")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        AppUi.context = null;
    }

    @objid ("eefce8a5-0669-45c7-ac22-4213c958dbb9")
    private void initializeLogLevel() {
        LogLevelUpdater.setLogLevelFromPreferences();
    }

    /**
     * Remove the authenticator set by Eclipse.
     * <p>
     * org.eclipse.ui.internal.net.auth.NetAuthenticator uses the Eclipse 3.x workbench to open an authentication dialog.
     * <p>
     * We don't need it and it does not work because we don't start the Eclipse 3 workbench.
     * @see org.eclipse.ui.internal.net.auth.NetAuthenticator
     * 
     * @param bundleContext the bundle context.
     * 
     * 
     * @see java.net.Authenticator 
     * @see org.eclipse.ui.internal.net.auth.NetAuthenticator 
     */
    @objid ("8f41fc06-4e70-4b23-9ee9-7ce29bef04ed")
    private void overrideAuthenticator(BundleContext bundleContext) {
        // force org.eclipse.core.net Eclipse plugin to load first
        ServiceReference<IProxyService> sr = bundleContext.getServiceReference(IProxyService.class);
        @SuppressWarnings ("unused")
        IProxyService svc = bundleContext.getService(sr);
        bundleContext.ungetService(sr);
        
        // remove its ugly authenticator
        Authenticator.setDefault(null);
    }

}
