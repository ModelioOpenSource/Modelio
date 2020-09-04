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

package org.modelio.core.ui.nattable.parts.data.texticon;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;
import org.eclipse.swt.graphics.Image;

/**
 * Data type converter for a Text+Icon cell.
 * <p>
 * Create a new {@link TextIcon} instance for display, using a
 * {@link ILabelProvider} to define the icon and text associated with each
 * object.
 * </p>
 */
@objid ("399bec45-9190-4e24-9d40-32a9d464c363")
public class TextIconConverter extends DisplayConverter {
    @objid ("a3a328f9-ee72-49f1-b53f-c6ce4f0865e3")
    private final ILabelProvider labelProvider;

    /**
     * Create a new TextIconConverter.
     * 
     * @param labelProvider the label provider to use when converting to display.
     */
    @objid ("c4b569c8-6868-419a-9c13-3e2c3d69e3ab")
    public TextIconConverter(ILabelProvider labelProvider) {
        this.labelProvider = Objects.requireNonNull(labelProvider);
    }

    /**
     * Returns a {@link TextIcon} instance built from the canonical value.
     */
    @objid ("23b499a6-2223-4cde-8a2d-73e872b08777")
    @Override
    public Object canonicalToDisplayValue(Object canonicalValue) {
        if (canonicalValue instanceof TextIcon) {
            return canonicalValue;
        }
        final Image icon = this.labelProvider.getImage(canonicalValue);
        final String text = this.labelProvider.getText(canonicalValue);
        return new TextIcon(text, icon);
    }

    /**
     * Returns an unchanged displayValue.
     */
    @objid ("9bf55c75-f58e-4e03-b1d3-dae39b89c3f1")
    @Override
    public Object displayToCanonicalValue(Object displayValue) {
        return displayValue;
    }

}
