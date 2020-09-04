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

package org.modelio.diagram.editor.usecase.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.plugin.AbstractDiagramConfigurer;
import org.modelio.diagram.editor.usecase.editor.UseCaseDiagramEditor;

/**
 * Use case diagrams palette configurer.
 */
@objid ("5e2eb8cb-55b7-11e2-877f-002564c97630")
public class UseCaseDiagramConfigurer extends AbstractDiagramConfigurer {
    @objid ("7c19dea7-5eff-11e2-b9cc-001ec947c8cc")
    @Override
    public String getContributionURI() {
        return UseCaseDiagramEditor.ID;
    }

}
