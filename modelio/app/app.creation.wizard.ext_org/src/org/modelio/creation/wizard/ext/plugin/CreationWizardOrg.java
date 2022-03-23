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
package org.modelio.creation.wizard.ext.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This class represents the diagram.editor plugin.
 */
@objid ("52113835-dafa-4b01-957c-10f90539b158")
public class CreationWizardOrg implements BundleActivator {
    /**
     * ID of the DiagramEditor plugin.
     */
    @objid ("c5dffeac-d376-41c2-b237-6e34e9ff4aec")
    public static final String PLUGIN_ID = "org.modelio.app.creation.wizard";

    @objid ("d4c81074-1080-4f06-9f8c-b5abac8d9bd9")
    private static BundleContext context;

    @objid ("fb3429b3-b13d-4f16-a189-09a4ced4809c")
    public static BundledMessages I18N = null;

    @objid ("2568661a-ceb8-4f29-85a2-2e60aecbbaed")
    public static PluginLogger LOG = null;

    @objid ("1426684e-3ea2-444e-8365-30a397658d39")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        CreationWizardOrg.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        CreationWizardOrg.LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), CreationWizardOrg.PLUGIN_ID));
        CreationWizardOrg.I18N = new BundledMessages(CreationWizardOrg.LOG, ResourceBundle.getBundle("creationwizard"));
        
    }

    @objid ("4706ab6c-aef4-4dfd-a9d2-85101d8b5d43")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        CreationWizardOrg.context = null;
    }

    @objid ("c18b3abc-6d49-4542-ad28-e9dddfee3f11")
    public static BundleContext getContext() {
        return CreationWizardOrg.context;
    }

}
