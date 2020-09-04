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

package org.modelio.diagram.editor.bpmn.elements.workflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;

@objid ("d8392586-24bc-4a6b-8e22-e97235b5620c")
public interface IWorkflowProvider {
    @objid ("33c44e53-13fb-4ec3-9cf9-c632d7138536")
    GmWorkflow getWorkflow();

    @objid ("43abf95f-42b6-486e-ae95-2639c8ad031c")
    IGmDiagram getDiagram();

}
