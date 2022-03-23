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
package org.modelio.diagram.elements.umlcommon.externdocument;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.diagram.elements.common.linkednode.CreateLinkedNodeCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.editors.richnote.helper.AddEmbeddedDocumentHelper;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IModelFactoryService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmNodeModel} creation command that:
 * <ul>
 * <li>creates and initialize the MObject if asked.
 * <li>creates the {@link GmNodeModel} and unmask it.
 * </ul>
 * according to the provided {@link ModelioCreationContext}.
 */
@objid ("814d269e-1dec-11e2-8cad-001ec947c8cc")
public class CreateExternDocumentCommand extends CreateLinkedNodeCommand {
    /**
     * @param context Modelio creation context
     */
    @objid ("814d26a0-1dec-11e2-8cad-001ec947c8cc")
    public  CreateExternDocumentCommand(final ModelioCreationContext context) {
        super(context);
    }

    @objid ("814d26a5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected MObject createElement(final IModelFactoryService modelFactory, IElementNamer elementNamer) {
        Shell parentShell = Display.getDefault().getActiveShell();
        IGmDiagram diagram = this.sourceNode.getDiagram();
        IMModelServices modelServices = diagram.getModelManager().getModelServices();
        
        try {
            return AddEmbeddedDocumentHelper.execute(parentShell, (ModelElement) this.parentElement, modelServices);
        } catch (IOException | IllegalArgumentException e) {
            DiagramElements.LOG.error(e);
            MessageDialog.openError(parentShell, "Error", e.getLocalizedMessage());
        }
        return null;
    }

    @objid ("7e1c4897-9ffa-4b56-89c5-d1e3f1e8ecb4")
    @Override
    public boolean canExecute() {
        return super.canExecute() && AddEmbeddedDocumentHelper.canExecute((ModelElement) this.parentElement);
    }

}
