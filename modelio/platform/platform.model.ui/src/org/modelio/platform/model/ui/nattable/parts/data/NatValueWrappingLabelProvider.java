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
package org.modelio.platform.model.ui.nattable.parts.data;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

/**
 * Styled label provider for {@link INatValue} wrapping another label provider for the value.
 * 
 * @author cma
 * @since Valkyrie 3.8
 */
@objid ("20726e9b-0de9-497e-a0e1-d724044d00d0")
public class NatValueWrappingLabelProvider extends LabelProvider implements IStyledLabelProvider {
    @objid ("4b7516e6-9086-49da-9943-e02ecfbdb9d3")
    private final ILabelProvider labelProvider;

    @objid ("64b4a482-106a-4680-8396-ce7a006fec07")
    private final IStyledLabelProvider styledLabelProvider;

    @objid ("5930f286-8199-4291-bc39-e893d91d022a")
    public  NatValueWrappingLabelProvider(ILabelProvider labelProvider) {
        super();
        this.labelProvider = Objects.requireNonNull(labelProvider);
        if (labelProvider instanceof IStyledLabelProvider) {
            this.styledLabelProvider = (IStyledLabelProvider) labelProvider;
        } else {
            this.styledLabelProvider = null;
        }
        
    }

    @objid ("4a95c936-23d0-4bdc-9b1e-d2afe3be704d")
    @Override
    public Image getImage(Object initialElement) {
        Object element = INatValue.getValue(initialElement);
        return this.labelProvider.getImage(element);
    }

    @objid ("df7cb550-6a86-4948-8abc-7034f79668e8")
    @Override
    public String getText(Object initialElement) {
        Object element = INatValue.getValue(initialElement);
        return this.labelProvider.getText(element);
    }

    @objid ("d3d3c80d-e882-484f-9c85-95b01549001d")
    @Override
    public StyledString getStyledText(Object initialElement) {
        if (this.styledLabelProvider==null) {
            return new StyledString(getText(initialElement));
        }
        
        Object element = INatValue.getValue(initialElement);
        return this.styledLabelProvider.getStyledText(element);
    }

}
