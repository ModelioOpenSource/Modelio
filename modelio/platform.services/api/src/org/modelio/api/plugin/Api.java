/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.plugin;

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
@objid ("de111772-0dbc-467e-aaa3-3dc71c63df90")
public class Api implements BundleActivator {
    /**
     * ID of the "api" plugin.
     */
    @objid ("293ac7a6-47ef-4b27-8d52-c9daa79b3125")
    public static final String PLUGIN_ID = "org.modelio.api";

    @objid ("4a436e87-d828-4a2c-b061-f2c9864060b0")
    private static BundleContext context;

    /**
     * Api plugin translation service.
     */
    @objid ("cb2b9dd0-4850-489e-84a6-cbc81c0fe307")
    public static BundledMessages I18N = null;

    /**
     * Api plugin logger.
     */
    @objid ("747c1922-71d6-4f6c-afe3-c3aff26d1665")
    public static PluginLogger LOG = null;

    @objid ("40ecd469-1f69-4ca4-b757-d7d75570692e")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("api"));
    }

    @objid ("5bf50587-d1b3-4eea-819a-515d6ceb7e77")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    /**
     * @return the bundle context
     */
    @objid ("442b4a88-329c-455e-af12-5c820957bfe1")
    public static BundleContext getContext() {
        return context;
    }

}
