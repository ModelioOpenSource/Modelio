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

package org.modelio.diagram.api.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.api.modelio.diagram.tools.IDiagramTool;

@objid ("486e4a7f-fc56-48d2-b8dc-6b8b4c2df4f3")
public class BoxToolEntry extends ToolEntry {
    /**
     * Constructor for BoxToolEntry.
     * 
     * @param label the label
     * @param shortDesc the description
     * @param factory the CreationFactory
     * @param iconSmall the small icon
     * @param iconLarge the large icon
     */
    @objid ("91f9faf9-4304-41c9-9ace-b25965090fc6")
    public BoxToolEntry(final String label, final String shortDesc, final CreationFactory factory, final ImageDescriptor iconSmall, final ImageDescriptor iconLarge, final IDiagramTool handler) {
        super(label, shortDesc, iconSmall, iconLarge, BoxTool.class);
        
        this.setToolProperty(CreationTool.PROPERTY_CREATION_FACTORY, factory);
        this.setToolProperty(BoxTool.PROPERTY_HANDLER, handler);
    }

}
