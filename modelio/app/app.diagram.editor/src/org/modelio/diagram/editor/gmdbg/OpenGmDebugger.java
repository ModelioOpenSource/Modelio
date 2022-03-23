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
package org.modelio.diagram.editor.gmdbg;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.RootEditPart;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.core.model.GmModel;

@objid ("ba9a312c-a9bd-4e9f-9b37-563117abc52c")
public class OpenGmDebugger {
    @objid ("8cd5cda7-2312-4f60-9e1a-0eb98456bb34")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) final MPart part, IEclipseContext context) {
        if (!(part.getObject() instanceof AbstractDiagramEditor))
            return;
        AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
        GmDebugger debugger = GmDebugger.getInstance();
        ContextInjectionFactory.inject(debugger, context);
        RootEditPart r = editor.getRootEditPart();
        debugger.setInput2((AbstractDiagramEditPart) r.getContents(), (GmModel)editor.getEditorInput().getGmDiagram());
        
    }

}
