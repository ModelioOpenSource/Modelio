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

package org.modelio.vcore.smkernel.meta.mof;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface implemented by MOF model objects.
 * @author cma
 * @since 3.6
 */
@objid ("1b011f89-2793-4731-ab29-b1b35861b14d")
public interface MMofObject extends MObject {
    /**
     * Get an attribute value.
     * 
     * @param attName the attribute name.
     * @return attribute value.
     * @throws java.lang.IllegalArgumentException if the attribute does not exist on the metaclass.
     */
    @objid ("1d52ea2c-b566-48a9-9db4-60856a09702e")
    Object getAtt(String attName) throws IllegalArgumentException;

    /**
     * Set an attribute value
     * 
     * @param attName the attribute name
     * @param value the value to set.
     */
    @objid ("73070c03-1614-4c93-9a0e-7ad3a2909815")
    void setAttVal(String attName, Object value);

    /**
     * Get a dependency content.
     * <p>
     * The returned list reflects the model : modifying the list
     * modifies the model.
     * 
     * @param depName the dependency name
     * @return the dependency content.
     */
    @objid ("7ff4e2b1-3c6d-4311-a54d-1f22806bd25d")
    List<MofSmObjectImpl> getDep(String depName);

}
