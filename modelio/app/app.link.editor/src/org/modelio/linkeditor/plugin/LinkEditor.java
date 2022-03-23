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
package org.modelio.linkeditor.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Singleton that represents the LinkViewer plugin.
 * <p>
 */
@objid ("1b55bf2b-5e33-11e2-b81d-002564c97630")
public final class LinkEditor implements BundleActivator {
    /**
     * ID of this plugin.
     */
    @objid ("1b55bf2f-5e33-11e2-b81d-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.app.link.editor";

    @objid ("5fc2eb53-32a6-4c97-ae71-d0208c5755d4")
    public static BundleContext context;

    /**
     * A configured Logger for this plugin.
     */
    @objid ("1b55bf35-5e33-11e2-b81d-002564c97630")
    public static PluginLogger LOG = null;

    /**
     * A configured I18n'd Message provider for this plugin.
     */
    @objid ("1b55bf37-5e33-11e2-b81d-002564c97630")
    public static BundledMessages I18N = null;

    @objid ("1b55bf40-5e33-11e2-b81d-002564c97630")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        LinkEditor.context = bundleContext;
        
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LinkEditor.LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), LinkEditor.PLUGIN_ID));
        LinkEditor.I18N = new BundledMessages(LinkEditor.LOG, ResourceBundle.getBundle("linkeditor"));
        
    }

    @objid ("1b55bf44-5e33-11e2-b81d-002564c97630")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        LinkEditor.context = null;
    }

    @objid ("1b55bf48-5e33-11e2-b81d-002564c97630")
    public static BundleContext getContext() {
        return LinkEditor.context;
    }

    @objid ("17b15535-dc1d-4ec5-acbb-4427e3d18d60")
    public  LinkEditor() {
        
    }

}
