/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.linkeditor.ext.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Singleton that represents the LinkViewer plugin.
 * <p>
 */
@objid ("0df8ddcb-31c8-4b83-8f4d-88b0668abdc3")
public final class LinkEditorOrg implements BundleActivator {
    /**
     * ID of this plugin.
     */
    @objid ("a9759aa6-5c65-446c-95cb-c30a42bc63d0")
    public static final String PLUGIN_ID = "org.modelio.linkeditor.ext";

    @objid ("e84d14d6-2ec4-49ed-bf71-fc2c4076038b")
    public static BundleContext context;

    /**
     * A configured Logger for this plugin.
     */
    @objid ("228bd1d5-ff87-4af8-9be3-f1b5b1a2ef51")
    public static PluginLogger LOG = null;

    /**
     * A configured I18n'd Message provider for this plugin.
     */
    @objid ("4e47665d-d27c-4407-9d1e-99c3ec34fbf1")
    public static BundledMessages I18N = null;

    @objid ("78fe5b97-56ea-40dd-96c6-b177c2345002")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        LinkEditorOrg.context = bundleContext;
        
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LinkEditorOrg.LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), LinkEditorOrg.PLUGIN_ID));
        LinkEditorOrg.I18N = new BundledMessages(LinkEditorOrg.LOG, ResourceBundle.getBundle("linkeditororg"));
    }

    @objid ("01bd39fb-2eff-4e29-bcb1-294204b2ced4")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        LinkEditorOrg.context = null;
    }

    @objid ("a050465c-2cfb-458f-b584-e0fefc102ad1")
    public static BundleContext getContext() {
        return LinkEditorOrg.context;
    }

}
