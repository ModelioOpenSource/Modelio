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
package com.modeliosoft.modelio.model.browser.api.cp;

import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.modeliosoft.modelio.platform.expertises.core.cp.IConfigurationPoint;

/**
 * The IModelBrowserConfigurationPoint provides the following browser customization features:
 * <ol>
 * <li>provide a content filter</li>
 * <li>provide a label provider</li>
 * <li>provide a content sorter</li>
 * </ol>
 */
@objid ("4f097ef6-d38a-4cca-99fc-250c426fcd92")
public interface IModelBrowserConfigurationPoint extends IConfigurationPoint {
    /**
     * @return a Properties map containing the Model options to be used when this configuration is active.
     */
    @objid ("da1ff432-f233-4673-b6bf-064d15ddfdf2")
    Properties getOptions();

    @objid ("ad6d1bbb-79fb-490f-9522-39712d03b69d")
    IDynamicModelFilter getModelFilter();

    @objid ("7ad2fcc6-d9e4-4715-9bd3-71170b1cc7b2")
    IDynamicLabelProviderRegistry getLabelProvider();
}

