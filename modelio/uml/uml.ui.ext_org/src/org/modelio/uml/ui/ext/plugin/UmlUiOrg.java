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

package org.modelio.uml.ui.ext.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
@objid ("e636196d-f1e1-49f7-ad0f-28a76eab5632")
public class UmlUiOrg extends AbstractUIPlugin {
    /**
     * The plug-in ID
     */
    @objid ("4edbb350-b88b-4e81-86b4-22c284e905d6")
    public static final String PLUGIN_ID = "org.modelio.uml.ui.ext"; // $NON-NLS-1$

    @objid ("3ac7b048-2c40-480e-8753-bf17d80da1c9")
    private static BundleContext context;

    /**
     * The plug-in's i18n bundle.
     */
    @objid ("1176104b-9180-4b85-ab34-d581bbee51ca")
    public static BundledMessages I18N;

    /**
     * The plug-in's logger.
     */
    @objid ("c3d4d04a-7ce5-4b80-9a96-c2eec34c0a36")
    public static PluginLogger LOG;

    @objid ("bc5fd725-1b73-4bd6-a456-295deda8ee7e")
    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        UmlUiOrg.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        UmlUiOrg.LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), UmlUiOrg.PLUGIN_ID));
        UmlUiOrg.I18N = new BundledMessages(UmlUiOrg.LOG, ResourceBundle.getBundle("umlui"));
    }

    @objid ("e16fdd25-671a-4922-ac9f-ebf130b3e51f")
    @Override
    public void stop(final BundleContext bundleContext) throws Exception {
        UmlUiOrg.context = null;
    }

    /**
     * @return the bundle's execution context within the Framework.
     */
    @objid ("3f9e4c50-a2c3-4a5a-960c-ed451c359328")
    public static BundleContext getContext() {
        return UmlUiOrg.context;
    }

}
