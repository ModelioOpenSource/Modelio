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
package org.modelio.uml.statediagram.editor.plugin;

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
@objid ("f5b348fa-55b6-11e2-877f-002564c97630")
public final class DiagramEditorState implements BundleActivator {
    /**
     * The plug-in ID
     */
    @objid ("f5b348ff-55b6-11e2-877f-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.uml.statediagram.editor";

    @objid ("fe94b5fe-5a5b-11e2-9e33-00137282c51b")
    public static PluginLogger LOG = null;

    @objid ("fe94b5ff-5a5b-11e2-9e33-00137282c51b")
    public static BundledMessages I18N;

    @objid ("fe94b600-5a5b-11e2-9e33-00137282c51b")
    private static BundleContext context;

    @objid ("fe94b601-5a5b-11e2-9e33-00137282c51b")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagrameditorstate"));
        
    }

    @objid ("fe94b605-5a5b-11e2-9e33-00137282c51b")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("fe94b609-5a5b-11e2-9e33-00137282c51b")
    public static BundleContext getContext() {
        return context;
    }

}
