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

package org.modelio.patterns.plugin;

import java.nio.file.Path;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.gproject.gproject.GProject;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("daeade25-ba16-4ae6-9bee-c85ca836bc78")
public class Patterns implements BundleActivator {
    @objid ("bec0d7bb-9220-4a03-82fe-256cea51c785")
    public static final String PLUGIN_ID = "org.modelio.patterns";

    @objid ("ab1f2479-371f-4d57-80b8-fd4c60f249d4")
    private static final String PATTERN_SUBDIR = "patterns";

    @objid ("ef1e060b-e206-475d-bd5a-9d7f96d045f9")
    public static BundledMessages I18N;

    @objid ("52842c32-0886-4876-9fb1-774938382dd4")
    public static PluginLogger LOG;

    @objid ("66b50181-6ebf-44f3-a726-15b588b58d28")
    private static BundleContext context;

    @objid ("665ff740-b912-485b-83fe-0a294a774d77")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        Patterns.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        Patterns.LOG = new PluginLogger(service.getLogger((String)null));
        Patterns.I18N = new BundledMessages(Patterns.LOG, ResourceBundle.getBundle("patterns"));
    }

    @objid ("7201dcca-c4e9-4553-90c4-3542a6589977")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        Patterns.context = null;
    }

    @objid ("134df6b1-1ac2-487e-9e4b-0ace09cc6366")
    public static BundleContext getContext() {
        return Patterns.context;
    }

    /**
     * Returns an image descriptor for the image file in the plug-in relative path.
     * 
     * @param path a path relative to this plugin.
     * @return the image descriptor.
     */
    @objid ("45374a21-8347-4e6a-b3b9-8a5bb97b59aa")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(Patterns.PLUGIN_ID, path);
    }

    /**
     * @param openedProject a project
     * @return the directory where project patterns are stored.
     */
    @objid ("3c15529a-aded-4757-83da-099f5c8b059c")
    public static Path getProjectPatternsDirectory(GProject openedProject) {
        return openedProject.getProjectFileStructure().getProjectDataConfigPath().resolve(Patterns.PATTERN_SUBDIR);
    }

}
