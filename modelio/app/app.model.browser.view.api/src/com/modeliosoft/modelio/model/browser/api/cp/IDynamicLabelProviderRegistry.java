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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.swt.images.IModelioElementLabelProvider;

@objid ("ec6d8587-d586-4f4b-851c-273fc8f35c32")
public interface IDynamicLabelProviderRegistry {
    @objid ("98c78eb3-2bc1-4396-9d0d-a7f9922a5ec7")
    IModelioElementLabelProvider getLabelProviderFor(Object o);
}

