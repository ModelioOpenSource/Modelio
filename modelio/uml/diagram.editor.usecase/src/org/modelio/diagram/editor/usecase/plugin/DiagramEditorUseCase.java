/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.usecase.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("5e9060ba-55b7-11e2-877f-002564c97630")
public final class DiagramEditorUseCase implements BundleActivator {
    @objid ("5e9060c0-55b7-11e2-877f-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.diagram.editor.usecase";

    @objid ("9f60735d-ea8c-4705-9ac5-cb75258aa2f3")
    private static BundleContext context;

    @objid ("7b8d6cd5-5eff-11e2-b9cc-001ec947c8cc")
    public static PluginLogger LOG = null;

    @objid ("7b8d6cd7-5eff-11e2-b9cc-001ec947c8cc")
    public static BundledMessages I18N;

    @objid ("7b8d6ced-5eff-11e2-b9cc-001ec947c8cc")
    private static UseCaseSharedImages imageRegistry = new UseCaseSharedImages();

    @objid ("5e91e761-55b7-11e2-877f-002564c97630")
    public static UseCaseSharedImages getImageRegistry() {
        return imageRegistry;
    }

    @objid ("7b8d6ce6-5eff-11e2-b9cc-001ec947c8cc")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    @objid ("a1fca68c-17ab-4908-a925-faaa58f65fee")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagrameditorusecase"));
    }

    @objid ("e9f8dfa4-b29e-4dd7-b88d-048c71e9c4cb")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("2a1ac89c-89ae-41e1-b844-37f85a4ff8c8")
    public static BundleContext getContext() {
        return context;
    }

}
