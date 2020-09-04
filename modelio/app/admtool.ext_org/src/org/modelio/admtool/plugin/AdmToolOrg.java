/* 
 * Copyright 2013-2020 Modeliosoft - www.modeliosoft.com 
 * 
 * All information contained herein is, and remains the property of Modeliosoft.
 * The intellectual and technical concepts contained herein are proprietary 
 * to Modeliosoft and may be covered by French and Foreign Patents, patents
 * in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Modeliosoft.
 * 
 */

package org.modelio.admtool.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("29b05a0a-f645-4f97-aa75-2624b89fa356")
public class AdmToolOrg extends AbstractUIPlugin {
    @objid ("cc5dd3c4-0200-41d2-ad76-44fca7d2488e")
    public static final String PLUGIN_ID = "org.modelio.admtool.ext"; // $NON-NLS-1$

    @objid ("e015a7d3-b5fe-4416-a62e-7e6a9785b7a8")
    public static PluginLogger LOG;

    @objid ("3bc7524f-432d-4742-a851-c5e950bdd032")
    public static BundledMessages I18N;

    @objid ("46a68bf5-8548-4e02-885f-1b8f7424764e")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        AdmToolOrg.LOG = new PluginLogger(service.getLogger((String) null));
        AdmToolOrg.I18N = new BundledMessages(AdmToolOrg.LOG, ResourceBundle.getBundle("admtoolorg"));
    }

    @objid ("be471244-cbaa-4e4f-843b-9b5fdef7f867")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        // Nothing to do
    }

}
