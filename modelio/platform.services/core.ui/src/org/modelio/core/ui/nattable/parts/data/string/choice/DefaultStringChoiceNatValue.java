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

package org.modelio.core.ui.nattable.parts.data.string.choice;

import java.security.InvalidParameterException;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link IStringChoiceNatValue}.
 */
@objid ("5a81d50e-7f01-4e9f-a6fb-cf72c700ae86")
public class DefaultStringChoiceNatValue extends DefaultNatValue implements IStringChoiceNatValue {
    @objid ("6f215e39-ea91-41e3-ae9d-5cbf492f13ce")
    private boolean editable;

    @objid ("2d21ab8c-5486-4df5-952c-6ee0e9405674")
    private List<String> valueChoices;

    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     * @param valueChoices list of values to choose from.
     * @param editable whether or not the value choices can be extended with other values.
     */
    @objid ("a362cc98-ecf0-4ec0-985e-82327b341098")
    public DefaultStringChoiceNatValue(String value, boolean acceptNullValue, List<String> valueChoices, boolean editable) {
        super(value, acceptNullValue);
        if (acceptNullValue) {
            valueChoices.add(0, "");
        }
        this.valueChoices = valueChoices;
        this.editable = editable;
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("756fbc9c-fab9-49bb-bd0f-a47da55a69c8")
    public DefaultStringChoiceNatValue(DefaultStringChoiceNatValue anotherInstance) {
        super(anotherInstance);
        this.valueChoices = anotherInstance.valueChoices;
    }

    @objid ("310545c5-d008-4f2d-9a25-7efe56f0a948")
    @Override
    public String getValue() {
        return (String) super.getValue();
    }

    @objid ("9c7dff7e-ccff-4f83-9b53-890699ceb856")
    @Override
    public List<String> getValueChoices() {
        return this.valueChoices;
    }

    @objid ("b70a751c-c51c-4156-b551-3b31e7c8dc04")
    @Override
    public boolean isEditable() {
        return this.editable;
    }

    @objid ("4e8c60c0-e478-474a-8ea7-9759d8c77315")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof String)) {
            throw new InvalidParameterException("Value must be a String.");
        }
        super.setValue(value);
    }

}
