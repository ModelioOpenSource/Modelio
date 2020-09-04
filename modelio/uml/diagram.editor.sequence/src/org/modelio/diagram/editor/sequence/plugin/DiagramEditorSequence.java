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

package org.modelio.diagram.editor.sequence.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("d9a239fa-55b6-11e2-877f-002564c97630")
public class DiagramEditorSequence implements BundleActivator {
    @objid ("d9a23a00-55b6-11e2-877f-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.diagram.editor.sequence";

    @objid ("ebfe29cf-5790-4901-8ac1-eceee522c366")
    public static PluginLogger LOG = null;

    @objid ("eadb661a-5753-4663-86dc-7072068976cc")
    public static BundledMessages I18N;

    @objid ("9c75af87-91e5-4e3b-90fb-8d85b8a47aae")
    private static BundleContext context;

    @objid ("7b8d6cda-5eff-11e2-b9cc-001ec947c8cc")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagrameditorsequence"));
    }

    @objid ("7b8d6cde-5eff-11e2-b9cc-001ec947c8cc")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("7b8d6ce2-5eff-11e2-b9cc-001ec947c8cc")
    public static BundleContext getContext() {
        return context;
    }

}
