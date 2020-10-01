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

package org.modelio.platform.model.ui.nattable.parts.data.hybrid;

import java.security.InvalidParameterException;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.nattable.parts.data.DefaultNatValue;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of {@link IHybridNatValue}.
 */
@objid ("eccb2b58-ff47-4956-89d1-be59bcb242fe")
public class DefaultHybridNatValue extends DefaultNatValue implements IHybridNatValue {
    @objid ("9e8dc576-4409-4e73-b046-6b973f353ca7")
    private boolean acceptStringValue;

    @objid ("6cebc32d-1e86-4434-9d48-06da6ccf1f49")
    protected List<Class<? extends MObject>> allowedClasses = null;

    @objid ("21e3a7c2-efc6-4494-9b3a-334710d9c3c7")
    protected IMObjectFilter elementFilter = null;

    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     * @param allowedClasses list of the accepted metaclasses for the value.
     * @param acceptStringValue whether or not a String is a valid value for this field.
     */
    @objid ("6979470e-3302-4679-9623-b2b6a7ec7929")
    public DefaultHybridNatValue(Object value, boolean acceptNullValue, List<Class<? extends MObject>> allowedClasses, boolean acceptStringValue) {
        super(value, acceptNullValue);
        this.allowedClasses = allowedClasses;
        this.acceptStringValue = acceptStringValue;
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("50803805-813a-4877-b810-6646abcbfddf")
    public DefaultHybridNatValue(DefaultHybridNatValue anotherInstance) {
        super(anotherInstance);
        this.allowedClasses = anotherInstance.allowedClasses;
        this.elementFilter = anotherInstance.elementFilter;
    }

    @objid ("c077cdec-cb85-427d-a3e5-17ece1eacf35")
    @Override
    public boolean acceptStringValue() {
        return this.acceptStringValue;
    }

    @objid ("d92adf1b-e73e-464a-bb8e-8cc3b3dddc54")
    @Override
    public List<Class<? extends MObject>> getAllowedClasses() {
        return this.allowedClasses;
    }

    @objid ("8b3492a1-e52f-493b-894c-5f3681f6f4e4")
    @Override
    public IMObjectFilter getElementFilter() {
        return this.elementFilter;
    }

    @objid ("e1da31da-e604-4041-9201-ade6bfde1ee0")
    @Override
    public Object getValue() {
        return super.getValue();
    }

    @objid ("643e5db2-1ece-42db-a61d-d0fc8efa637b")
    @Override
    public void setElementFilter(IMObjectFilter elementFilter) {
        this.elementFilter = elementFilter;
    }

    @objid ("79a0e4f8-15fb-4b98-92b0-8ecfe9a98ae3")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof MObject) && !(value instanceof String)) {
            throw new InvalidParameterException("Value must be a MObject or a String.");
        }
        super.setValue(value);
    }

}
