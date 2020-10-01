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

package org.modelio.model.browser.view.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.project.services.IProjectService;

@objid ("aa15dfe8-63e8-4b55-934d-16be0ae4f3c9")
public class OpenRelatedDiagramHandler {
    @objid ("f07f3f65-5c0c-4a2e-8cdf-1d4a3f775789")
    @Execute
    public void execute(@Named("org.modelio.app.ui.command.parameter.related_diagram") String related_diagram_id, IProjectService projectService, IActivationService activationService) {
        final AbstractDiagram related_diagram = projectService.getSession().getModel().findById(AbstractDiagram.class, related_diagram_id);
        if (related_diagram != null) {
            activationService.activateMObject(related_diagram);
        }
    }

}
