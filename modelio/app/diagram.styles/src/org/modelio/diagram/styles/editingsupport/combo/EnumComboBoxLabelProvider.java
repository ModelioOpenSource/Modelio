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

package org.modelio.diagram.styles.editingsupport.combo;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewItem.Choice;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.diagram.styles.viewer.StyleEditPanelUIData;

@objid ("859fd1d1-1926-11e2-92d2-001ec947c8cc")
public class EnumComboBoxLabelProvider extends ColumnLabelProvider {
    @objid ("644d7afa-ef06-4136-a5fb-348afff06035")
     ColumnViewer viewer;

    @objid ("859fd1d3-1926-11e2-92d2-001ec947c8cc")
    public EnumComboBoxLabelProvider(ColumnViewer viewer) {
        this.viewer = viewer;
    }

    @objid ("859fd1d6-1926-11e2-92d2-001ec947c8cc")
    @Override
    public String getText(Object element) {
        ISymbolViewItem item = (ISymbolViewItem) element;
        Object value = item.getValue(getEditedStyle());
        
        for (Choice choice : item.getPossibleValues()) {
            if (Objects.equals(choice.value, value)) {
                return choice.label;
            }
        }
        
        // fallback
        if (value != null) {
            return DiagramStyles.I18N.getString("$" + value.getClass().getSimpleName() + "." + value.toString());
        } else {
            return super.getText(element);
        }
    }

    @objid ("4d92187c-6b88-4c57-8e6f-1d398844090e")
    private IStyle getEditedStyle() {
        return ((StyleEditPanelUIData) this.viewer.getInput()).getStyleData();
    }

}
