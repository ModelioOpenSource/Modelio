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

package org.modelio.diagram.editor.bpmn.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.editor.bpmn.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagramStyleKeys;
import org.modelio.diagram.elements.core.model.IGmDiagram;

/**
 * BPMN-specific 'show grid' handler, using the {@link GmBpmnDiagramStyleKeys#VIEWGRID} stylekey instead of the default one from {@link GmAbstractDiagramStyleKeys}.
 */
@objid ("6fe22bff-5525-4ec3-80a9-9c1788ca5889")
public class BpmnGridHandler {
    @objid ("6dc94a91-85b2-4d25-90ac-eb38143be75c")
    @Execute
    public Object execute(@Named (IServiceConstants.ACTIVE_PART) final MPart part) {
        if (!(part.getObject() instanceof AbstractDiagramEditor)) {
            return null;
        }
        
        AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
        
        IGmDiagram diagram = editor.getEditorInput().getGmDiagram();
        diagram.getDisplayedStyle().setProperty(GmBpmnDiagramStyleKeys.VIEWGRID, !diagram.getDisplayedStyle().getBoolean(GmBpmnDiagramStyleKeys.VIEWGRID));
        return null;
    }

}
