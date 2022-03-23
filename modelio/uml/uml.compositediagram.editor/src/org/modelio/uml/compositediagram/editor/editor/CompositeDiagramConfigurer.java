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
package org.modelio.uml.compositediagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.plugin.AbstractDiagramConfigurer;

/**
 * Composite diagram palette configurer.
 */
@objid ("8089b19a-55b6-11e2-877f-002564c97630")
public class CompositeDiagramConfigurer extends AbstractDiagramConfigurer {
    @objid ("27b884c0-a915-4484-b728-2d5d08bd20e1")
    @Override
    public String getContributionURI() {
        return CompositeDiagramEditor.ID;
    }

}
