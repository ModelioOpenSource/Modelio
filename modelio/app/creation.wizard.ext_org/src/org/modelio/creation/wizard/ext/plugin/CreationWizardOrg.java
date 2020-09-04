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
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This class represents the diagram.editor plugin.
 */
@objid ("2c569cf3-094b-41ed-9604-7f5ee4bf4598")
public class CreationWizardOrg implements BundleActivator {
    /**
     * ID of the DiagramEditor plugin.
     */
    @objid ("4f351a6f-bacb-4059-ade0-42ab15acf1cc")
    public static final String PLUGIN_ID = "org.modelio.creation.wizard";

    @objid ("7d3ec4a6-0ace-4922-8ecd-91648abbbb6a")
    private static BundleContext context;

    @objid ("f52f3210-c857-4c9e-8777-676873c43feb")
    public static BundledMessages I18N = null;

    @objid ("ecdfe620-b0cf-4698-a96d-2a63f5a6f323")
    public static PluginLogger LOG = null;

    @objid ("9b42b844-bab2-4593-84ca-b546772c6b43")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        CreationWizardOrg.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        CreationWizardOrg.LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), CreationWizardOrg.PLUGIN_ID));
        CreationWizardOrg.I18N = new BundledMessages(CreationWizardOrg.LOG, ResourceBundle.getBundle("creationwizard"));
    }

    @objid ("4ca385b4-7507-4069-8166-59c7a72dfa5b")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        CreationWizardOrg.context = null;
    }

    @objid ("2abc02d4-a13a-44c5-8219-78a3d61f7882")
    public static BundleContext getContext() {
        return CreationWizardOrg.context;
    }

}
