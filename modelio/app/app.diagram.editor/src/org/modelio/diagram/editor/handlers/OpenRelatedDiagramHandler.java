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
package org.modelio.diagram.editor.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.project.services.IProjectService;

@objid ("65b21878-33f7-11e2-95fe-001ec947c8cc")
public class OpenRelatedDiagramHandler {
    @objid ("65b47a78-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public void execute(@Named("org.modelio.app.ui.command.parameter.related_diagram") String related_diagram_id, IProjectService projectService, IActivationService activationService) {
        final AbstractDiagram related_diagram = projectService.getSession().getModel().findById(AbstractDiagram.class, related_diagram_id);
        if (related_diagram != null) {
            activationService.activateMObject(related_diagram);
        }
        
    }

}
