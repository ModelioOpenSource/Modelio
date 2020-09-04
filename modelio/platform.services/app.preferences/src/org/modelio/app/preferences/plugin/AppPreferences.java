/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.app.preferences.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("18370aee-24da-487b-8550-c427b037cbfb")
public class AppPreferences implements BundleActivator {
    @objid ("2b563c7b-39c9-4f56-ae80-26b51df04daa")
    public static final String PLUGIN_ID = "org.modelio.app.preferences";

    @objid ("0350569d-1b4a-46b3-8788-44f0c12e581b")
    private static BundleContext context;

    @objid ("595add25-7aec-4b43-8cf2-df20500f2716")
    public static BundledMessages I18N;

    @objid ("2efb3b10-5132-48b9-892e-8f41fff63520")
    public static PluginLogger LOG;

    @objid ("e72544f8-6af1-40b0-8977-6d11dd15af56")
    private static ScopedPreferenceStore PREFERENCES;

    @objid ("1de155af-68e3-4742-80c8-4eabc781bc47")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("apppreferences"));
        PREFERENCES = new ScopedPreferenceStore(InstanceScope.INSTANCE, AppPreferences.PLUGIN_ID);
    }

    @objid ("7a24619f-7a86-455d-9eda-11dc6d38d17e")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        if (PREFERENCES.needsSaving()) {
            PREFERENCES.save();
        }
            
        context = null;
    }

    @objid ("f3f93b2d-1cef-4665-8018-d25211104fe0")
    public static BundleContext getContext() {
        return context;
    }

    @objid ("5c9a947c-1067-4c23-b46f-eb23d25759f4")
    public static IPersistentPreferenceStore getPreferences() {
        return PREFERENCES;
    }

}
