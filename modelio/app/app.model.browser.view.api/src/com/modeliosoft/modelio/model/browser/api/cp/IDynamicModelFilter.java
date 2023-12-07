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

@objid ("321b524d-e836-4d88-8290-961f9acae978")
public interface IDynamicModelFilter {
    /**
     * Filter that accepts all elements.
     */
    @objid ("7e5f9b31-ef58-4667-9855-7e986365d113")
    public static final IDynamicModelFilter SELECTALL_FILTER = new IDynamicModelFilter() {
    		@Override
    		public boolean select(Object parentElement, Object element) {
    			return true;
    		}
    	};

    @objid ("20c09d25-95c9-48cd-ab06-c77856ff9e6e")
    boolean select(Object parentElement, Object element);
}

