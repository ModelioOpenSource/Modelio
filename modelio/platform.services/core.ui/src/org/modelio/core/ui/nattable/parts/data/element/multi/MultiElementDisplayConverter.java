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

package org.modelio.core.ui.nattable.parts.data.element.multi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.nattable.parts.data.texticon.TextIcon;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Data type converter for a Multi-Element editor.
 * <p>
 * Assumes that the data value is stored as a {@link List} of {@link MObject}.
 * </p>
 */
@objid ("36ef90bc-6353-4364-8277-f48987844bca")
public class MultiElementDisplayConverter extends DisplayConverter {
    @objid ("1e690bfb-5d1e-4969-aaf7-d54002a85686")
    private ILabelProvider labelProvider;

    /**
     * Returns a {@link TextIcon} list built from the canonical value.
     */
    @objid ("461efc62-e47f-4e02-9be7-cdde4b2e01d2")
    @Override
    public List<TextIcon> canonicalToDisplayValue(Object canonicalValue) {
        List<TextIcon> ret = new ArrayList<>();
        if (canonicalValue instanceof TextIcon) {
            ret.add((TextIcon) canonicalValue);
        } else if (canonicalValue instanceof Collection) {
            Collection<?> collection = (Collection<?>) canonicalValue;
            for (Iterator<?> iterator = collection.iterator(); iterator.hasNext();) {
                Object value = iterator.next();
                TextIcon textIcon = buildTextIcon(value);
                ret.add(textIcon);
            }
        } else {
            ret.add(buildTextIcon(canonicalValue));
        }
        return ret;
    }

    /**
     * Returns an unchanged displayValue.
     */
    @objid ("92bfb9ae-d42b-4290-a5fe-276cd55318e6")
    @Override
    public Object displayToCanonicalValue(Object displayValue) {
        return displayValue;
    }

    /**
     * Create a new MultiElementDisplayConverter.
     * @param labelProvider the label provider to use when converting to display.
     */
    @objid ("cc2242af-4b79-4ee8-8094-c789a01b7b26")
    public MultiElementDisplayConverter(ILabelProvider labelProvider) {
        this.labelProvider = Objects.requireNonNull(labelProvider);
    }

    @objid ("b229fbea-2c1e-46d9-b61e-c66f774af2fe")
    private TextIcon buildTextIcon(Object canonicalValue) {
        Image icon = this.labelProvider.getImage(canonicalValue);
        String text = this.labelProvider.getText(canonicalValue);
        return new TextIcon(text, icon);
    }

}
