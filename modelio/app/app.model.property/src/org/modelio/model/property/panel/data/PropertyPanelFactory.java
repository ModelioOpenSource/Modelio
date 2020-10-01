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

package org.modelio.model.property.panel.data;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.model.property.panel.data.standard.StandardPropertyPanel;

/**
 * This factory returns a PropertyPanel used to display the "properties" part of
 * the property view, depending on the element metaclass.
 */
@objid ("8fa7c846-c068-11e1-8c0a-002564c97630")
public class PropertyPanelFactory {
    /**
     * Create a panel displaying standard UML properties.
     * 
     * @param parent the graphical parent.
     * @param element the element to display in the panel.
     * @return a new instance of IPropertyPanel.
     */
    @objid ("8fa7c848-c068-11e1-8c0a-002564c97630")
    public static IPropertyPanel createStandardPanel(Composite parent, Element element) {
        return new StandardPropertyPanel(parent, element);
    }

}
