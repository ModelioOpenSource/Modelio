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

package org.modelio.module.propertytab.ui.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.propertytab.ui.panel.treeview.TreeContentPanel;

@objid ("c88c1da0-1eba-11e2-9382-bc305ba4815c")
public class PropertyPanelFactory {
    @objid ("c88c1da1-1eba-11e2-9382-bc305ba4815c")
    public static IModulePropertyTreePanel createStandardPanel(Composite parent, ModelElement element) {
        return new TreeContentPanel(parent, element);
    }

}
