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
package com.modeliosoft.modelio.model.browser.api.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
@objid ("b06f6ec6-9146-4ea2-9801-4fea088bef68")
public class ModelBrowserApiCom extends AbstractUIPlugin {
    /**
     * The plug-in ID
     */
    @objid ("477d9eba-61d6-44d5-80a7-b219736a84cc")
    public static final String PLUGIN_ID = "com.modeliosoft.modelio.app.model.browser.view.api"; // $NON-NLS-1$
    

    @objid ("d0cb19cf-ab00-48db-a5a0-a39f7454f9f7")
    public static BundledMessages I18N;

    @objid ("13085c11-19c0-4dc9-8da9-bf2275e2de68")
    private static BundleContext context;

    @objid ("5e96d755-c9fa-4a7b-9728-b7aabebd87f1")
    public static PluginLogger LOG;

    @objid ("ef86d0c9-42e7-4678-9837-955f77623310")
    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        ModelBrowserApiCom.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        ModelBrowserApiCom.LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), ModelBrowserApiCom.PLUGIN_ID));
        ModelBrowserApiCom.I18N = new BundledMessages(ModelBrowserApiCom.LOG, ResourceBundle.getBundle("modelbrowserapi"));
        
    }

    @objid ("b86e64a8-5a1e-4675-ac7b-0fef4daaf31f")
    @Override
    public void stop(final BundleContext bundleContext) throws Exception {
        ModelBrowserApiCom.context = null;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * @param path the path
     * @return the image descriptor
     */
    @objid ("0f1723ad-9669-4ea1-8551-e1541295f62f")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(ModelBrowserApiCom.PLUGIN_ID, path);
    }

    @objid ("83b242f7-2c4d-4818-aa07-de3dcd2b0f33")
    public static BundleContext getContext() {
        return ModelBrowserApiCom.context;
    }

}
