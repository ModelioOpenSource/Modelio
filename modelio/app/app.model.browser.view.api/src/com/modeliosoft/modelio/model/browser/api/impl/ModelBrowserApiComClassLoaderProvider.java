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
package com.modeliosoft.modelio.model.browser.api.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.modeliosoft.modelio.model.browser.api.cp.IModelBrowserConfigurationPoint;
import org.modelio.platform.mda.infra.service.IModuleClassLoaderProvider;

/**
 * Class loader provider for Jython script engine and modules.
 * <p>
 * Gives browser.view.api.com class loader.
 * </p>
 */
@objid ("1ac13fb5-8366-4dcc-8fdf-a4231862d4f9")
public class ModelBrowserApiComClassLoaderProvider implements IModuleClassLoaderProvider {
    @objid ("09274adf-cf59-4f7f-9157-937e2236aff1")
    @Override
    public ClassLoader getClassLoader() {
        return IModelBrowserConfigurationPoint.class.getClassLoader();
    }

}
