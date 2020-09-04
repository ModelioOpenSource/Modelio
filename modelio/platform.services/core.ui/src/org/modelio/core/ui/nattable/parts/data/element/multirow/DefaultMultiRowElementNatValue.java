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

package org.modelio.core.ui.nattable.parts.data.element.multirow;

import java.security.InvalidParameterException;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of {@link IMultiRowElementNatValue}.
 */
@objid ("384cbafb-623d-4bdc-9438-aafba0481900")
public class DefaultMultiRowElementNatValue extends DefaultNatValue implements IMultiRowElementNatValue {
    @objid ("bb468433-02c8-408c-a6e5-5bd0d9d2ce31")
    private String tagSuffix;

    @objid ("36b9bc6b-5c10-498b-942f-3aab26556bbe")
    protected List<Class<? extends MObject>> allowedClasses = null;

    @objid ("5c08db06-e3d9-4e95-966d-45ede9612242")
    protected IMObjectFilter elementFilter = null;

    @objid ("2cbe31f7-0c77-4741-9e46-f57c66e78524")
    private List<MObject> allValues;

    @objid ("44a73c40-6c65-472e-83c2-06aaa55db025")
    @Override
    public List<MObject> getAllValues() {
        return this.allValues;
    }

    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     * @param allowedClasses list of the accepted metaclasses for the value.
     */
    @objid ("123e9c20-5a9e-40e7-b55f-fbe52d9f5ff1")
    public DefaultMultiRowElementNatValue(MObject value, List<MObject> allValues, boolean acceptNullValue, List<Class<? extends MObject>> allowedClasses, String tagSuffix) {
        super(value, acceptNullValue);
        this.allowedClasses = allowedClasses;
        this.allValues = allValues;
        this.tagSuffix = tagSuffix;
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("db36e7a7-2db8-4f27-8de5-cd5e1ce1b79b")
    public DefaultMultiRowElementNatValue(DefaultMultiRowElementNatValue anotherInstance) {
        super(anotherInstance);
        this.allowedClasses = anotherInstance.allowedClasses;
        this.elementFilter = anotherInstance.elementFilter;
        this.allValues = anotherInstance.allValues;
        this.tagSuffix = anotherInstance.tagSuffix;
    }

    @objid ("7a26b8b9-9a6c-4b0a-8d36-68643ca0799d")
    @Override
    public List<Class<? extends MObject>> getAllowedClasses() {
        return this.allowedClasses;
    }

    @objid ("67f5a2c9-7806-45a9-a3cc-fa37420cc9be")
    @Override
    public IMObjectFilter getElementFilter() {
        return this.elementFilter;
    }

    @objid ("b1192c9a-433f-4217-a98a-f9e846fe5b8e")
    @Override
    public MObject getValue() {
        return (MObject) super.getValue();
    }

    @objid ("8ff35238-b7a5-47d9-b430-76128a6992a1")
    @Override
    public void setElementFilter(IMObjectFilter elementFilter) {
        this.elementFilter = elementFilter;
    }

    @objid ("3265ea25-2792-4e1e-9cd2-ed89a6a4adbd")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof MObject)) {
            throw new InvalidParameterException("Value must be a MObject.");
        }
        super.setValue(value);
    }

    @objid ("28733a66-5dd1-4649-a623-0f328f05b26b")
    @Override
    public String toString() {
        return String.format("%s [value=%s]", getClass().getSimpleName(), getValue());
    }

    @objid ("54b204b1-6ad3-4928-aa72-72dfa69ff6f5")
    @Override
    public String getTagSuffix() {
        return this.tagSuffix;
    }

}
