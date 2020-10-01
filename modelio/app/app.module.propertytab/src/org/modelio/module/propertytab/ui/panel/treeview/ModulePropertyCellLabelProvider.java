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

package org.modelio.module.propertytab.ui.panel.treeview;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.modelio.module.propertytab.model.ModuleProperty;
import org.modelio.module.propertytab.ui.panel.treeview.editingsupport.checkbox.CheckboxLabelProvider;
import org.modelio.module.propertytab.ui.panel.treeview.editingsupport.element.ElementLabelProvider;
import org.modelio.module.propertytab.ui.panel.treeview.editingsupport.empty.EmptyLabelProvider;
import org.modelio.module.propertytab.ui.panel.treeview.editingsupport.text.TextLabelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("c898c7d2-1eba-11e2-9382-bc305ba4815c")
public class ModulePropertyCellLabelProvider extends ColumnLabelProvider {
    @objid ("c898eee0-1eba-11e2-9382-bc305ba4815c")
    private Map<Class<?>, ColumnLabelProvider> providers = new HashMap<>();

    @objid ("c89915f2-1eba-11e2-9382-bc305ba4815c")
    private EmptyLabelProvider emptyProvider;

    @objid ("c89915f3-1eba-11e2-9382-bc305ba4815c")
    public ModulePropertyCellLabelProvider() {
        this.providers.put(Boolean.class, new CheckboxLabelProvider());
        this.providers.put(String.class, new TextLabelProvider());
        this.providers.put(Integer.class, new TextLabelProvider());
        this.providers.put(Enum.class, new TextLabelProvider());
        this.providers.put(MObject.class, new ElementLabelProvider());
        this.emptyProvider = new EmptyLabelProvider();
    }

    @objid ("c89915f5-1eba-11e2-9382-bc305ba4815c")
    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        
        if (element instanceof ModuleProperty) {
            Class<?> stype = ((ModuleProperty) element).getType();
            ColumnLabelProvider provider = this.providers.get(stype);
            if (provider != null) {
                provider.update(cell);
            } else if (stype.equals(String[].class)) {
                this.providers.get(Enum.class).update(cell);
            }
        } else {
            this.emptyProvider.update(cell);
        }
    }

    @objid ("c8993d02-1eba-11e2-9382-bc305ba4815c")
    @Override
    public String getToolTipText(Object element) {
        return super.getToolTipText(element);
    }

}
