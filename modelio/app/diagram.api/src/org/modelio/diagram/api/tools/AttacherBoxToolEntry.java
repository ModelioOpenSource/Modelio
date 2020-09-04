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
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.api.modelio.diagram.tools.IDiagramTool;

@objid ("6d2b1616-f815-4774-b572-abf001d2ae7c")
public class AttacherBoxToolEntry extends ConnectionCreationToolEntry {
    /**
     * Constructor for LinkToolEntry.
     * 
     * @param label the label
     * @param shortDesc the description
     * @param factory the CreationFactory
     * @param iconSmall the small icon
     * @param iconLarge the large icon
     */
    @objid ("d73f9ac3-b810-4bb7-bb86-b1d9ee98478d")
    public AttacherBoxToolEntry(final String label, final String shortDesc, final CreationFactory factory, final ImageDescriptor iconSmall, final ImageDescriptor iconLarge, final IDiagramTool handler) {
        super(label, shortDesc, factory, iconSmall, iconLarge);
        setToolClass(AttachedBoxTool.class);
        this.setToolProperty(LinkTool.PROPERTY_HANDLER, handler);
        
        // Return to default selection tool after finished
        setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
    }

}
