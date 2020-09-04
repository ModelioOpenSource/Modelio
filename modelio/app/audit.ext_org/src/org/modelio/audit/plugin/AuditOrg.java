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

package org.modelio.audit.plugin;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This class represents the unique instance of the plugin
 */
@objid ("719fc34c-3b22-4cf8-9b9a-5a4a42edc10a")
public class AuditOrg implements BundleActivator {
    @objid ("167318aa-a2f4-4893-a089-8a0046731e28")
    public static final String PLUGIN_ID = "org.modelio.audit.ext";

    @objid ("73518d2d-4b9b-44e0-a634-132f4685b8a7")
    private static BundleContext context;

    @objid ("33ef5797-c798-41d5-860a-e80af7ff8ff7")
    public static PluginLogger LOG = null;

    @objid ("01e661b0-aaa8-4d3c-9dba-3bc74f758076")
    public static BundledMessages I18N = null;

    @objid ("7014b988-c143-4ab4-945e-7fb399168ecb")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        AuditOrg.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        AuditOrg.LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), AuditOrg.PLUGIN_ID));
        AuditOrg.I18N = new BundledMessages(AuditOrg.LOG, ResourceBundle.getBundle("audit"));
    }

    @objid ("1f013582-a663-4579-8dc9-e5b6108bf94b")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        AuditOrg.context = null;
    }

    @objid ("19c4a6ec-0441-49a8-9303-641fbc6ffabc")
    public static BundleContext getContext() {
        return AuditOrg.context;
    }

    @objid ("ed64e326-26f0-4123-8ed7-90d9734c7b9f")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(AuditOrg.PLUGIN_ID, path);
    }

    /**
     * Get the path of a file bundled with the plugin.
     * 
     * @param relPath the path relative to the plugin.
     * @return the found file
     * @throws java.io.IOException if the file is not found or cannot be copied on the file system, should never occur.
     */
    @objid ("8c6f17e5-bed1-491f-b315-9dcc6ea3ffd2")
    public static File getBundleFile(final String relPath) throws IOException {
        try {
            URL url = FileLocator.find(AuditOrg.context.getBundle(), new Path(relPath), null);
            URL fileUrl = FileLocator.toFileURL(url);
            return new File(URIUtil.toURI(fileUrl));
        } catch (IOException | URISyntaxException | RuntimeException e) {
            throw new IOException("'" + relPath + "' not found in plugin.", e);
        }
    }

}
