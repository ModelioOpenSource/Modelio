/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.core.ui.nattable.parts.data.texticon;

import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link ITextIconNatValue}.
 */
@objid ("1c8b095c-d60b-41de-a8d7-cbbf2ca5928a")
public class DefaultTextIconNatValue extends DefaultNatValue implements ITextIconNatValue {
    /**
     * Creates a new instance.
     * @param value the text.
     * @param image the icon.
     */
    @objid ("bd76a36c-242c-41be-aa7c-91fad2afbdc8")
    public DefaultTextIconNatValue(String text, Image icon) {
        super(new TextIcon(text, icon), false);
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * @param anotherInstance the instance to copy.
     */
    @objid ("69b5b162-bce9-4339-9940-769f6b1fc84f")
    public DefaultTextIconNatValue(DefaultTextIconNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("32e82788-5656-4522-b0c3-7aa4a8f21631")
    @Override
    public TextIcon getValue() {
        return (TextIcon) super.getValue();
    }

    @objid ("56f756c1-d822-4d8f-8590-e320d2ae51e3")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof TextIcon)) {
            throw new InvalidParameterException("Value must be a TextIcon.");
        }
        super.setValue(value);
    }

}
