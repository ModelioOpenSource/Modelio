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

package org.modelio.uml.statikdiagram.editor.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Singleton that represents the diagram plugin.
 */
@objid ("36ee8120-55b7-11e2-877f-002564c97630")
public class DiagramEditorStatik implements BundleActivator {
    /**
     * The plug-in ID
     */
    @objid ("36ee8125-55b7-11e2-877f-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.uml.statikdiagram.editor";

    /**
     * Logger.
     */
    @objid ("6578fa08-5bd5-11e2-9e33-00137282c51b")
    public static PluginLogger LOG = null;

    /**
     * Message translator.
     */
    @objid ("6578fa0a-5bd5-11e2-9e33-00137282c51b")
    public static BundledMessages I18N;

    @objid ("6578fa0c-5bd5-11e2-9e33-00137282c51b")
    private static BundleContext context;

    @objid ("6578fa0d-5bd5-11e2-9e33-00137282c51b")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagrameditorstatik"));
    }

    @objid ("657b5c62-5bd5-11e2-9e33-00137282c51b")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("657b5c66-5bd5-11e2-9e33-00137282c51b")
    public static BundleContext getContext() {
        return context;
    }

}
