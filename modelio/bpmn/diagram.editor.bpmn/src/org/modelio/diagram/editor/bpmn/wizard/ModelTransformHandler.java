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

package org.modelio.diagram.editor.bpmn.wizard;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("33d9392e-f29f-47cd-8fba-52a90e902a3d")
public class ModelTransformHandler {
    @objid ("aa3c3fac-a2f6-4ef9-a6f5-0e11d344dbea")
    @Execute
    public void execute(@Named ("transformerindex") String transformerindex, @Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection, IProjectService projectService, IEclipseContext context) {
        IModelTransformer transformer = TransformerRegistry.getInstance(context).getTransformer(Integer.parseInt(transformerindex));
        
        EditPart ep = SelectionHelper.getFirst(selection, EditPart.class);
        AbstractDiagram diagram = ((GmModel) ep.getModel()).getDiagram().getRelatedElement();
        
        // Get the modeling session and open a transaction
        ICoreSession modelingSession = projectService.getSession();
        ITransactionSupport transactionManager = modelingSession.getTransactionSupport();
        try (ITransaction transaction = transactionManager.createTransaction("Model transformation")) {
            transformer.transform(diagram, selection);
        
            // Commit the transaction.
            transaction.commit();
        } catch (Exception e) {
            DiagramElements.LOG.debug(e);
        }
    }

    @objid ("55dc70fc-42c8-4005-a3fc-4b77a14bd149")
    @CanExecute
    public boolean canExecute(@Named ("transformerindex") String transformerindex, @Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection, IEclipseContext context) {
        // Needs to be a modifiable MObject
        for (MObject elementToBeTransmuted : SelectionHelper.toList(selection, MObject.class)) {
            if (!elementToBeTransmuted.isModifiable()) {
                return false;
            }
        }
        
        EditPart ep = SelectionHelper.getFirst(selection, EditPart.class);
        if (ep == null) {
            return false;
        }
        
        AbstractDiagram diagram = ((GmModel) ep.getModel()).getDiagram().getRelatedElement();
        IModelTransformer transformer = TransformerRegistry.getInstance(context).getTransformer(Integer.parseInt(transformerindex));
        return transformer != null && transformer.canExecute(diagram, selection);
    }

}
