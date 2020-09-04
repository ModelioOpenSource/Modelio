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

package org.modelio.core.ui.nattable.parts.data.element.choice;

import java.security.InvalidParameterException;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of {@link IElementChoiceNatValue}.
 */
@objid ("da91ed40-f780-4ad9-9ad2-f73937d76cc2")
public class DefaultElementChoiceNatValue extends DefaultElementNatValue implements IElementChoiceNatValue {
    @objid ("aa40ba62-56e8-481e-9d08-dcc74c126ed7")
    private List<? extends MObject> valueChoices;

    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     * @param allowedClasses list of the accepted metaclasses for the value.
     * @param valueChoices list of values to choose from.
     */
    @objid ("3cd12dc5-d738-4754-8607-52d015745120")
    public DefaultElementChoiceNatValue(MObject value, boolean acceptNullValue, List<Class<? extends MObject>> allowedClasses, List<? extends MObject> valueChoices) {
        super(value, acceptNullValue, allowedClasses);
        if (acceptNullValue) {
            valueChoices.add(0, null);
        }
        this.valueChoices = valueChoices;
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("595b3952-a7fe-4bf0-9af9-337b79d9ffa5")
    public DefaultElementChoiceNatValue(DefaultElementChoiceNatValue anotherInstance) {
        super(anotherInstance);
        this.valueChoices = anotherInstance.valueChoices;
    }

    @objid ("83dc44e6-a6da-4bcf-8f84-b0bafa1ddaeb")
    @Override
    public MObject getValue() {
        return super.getValue();
    }

    @objid ("40f2c652-8ec6-43c2-a3cf-54fddf50b000")
    @Override
    public List<? extends MObject> getValueChoices() {
        return this.valueChoices;
    }

    @objid ("b64539cb-b95f-4ab2-a158-09c5a52d642f")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof MObject)) {
            throw new InvalidParameterException("Value must be a MObject.");
        }
        super.setValue(value);
    }

}
