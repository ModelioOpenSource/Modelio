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
package org.modelio.diagram.elements.core.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.ui.DefaultElementSelectionDialog;
import org.modelio.diagram.elements.core.ui.ElementPlaceolderData;
import org.modelio.diagram.elements.core.ui.ElementSelectionData;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmNodeModel} creation command that:
 * <ul>
 * <li>allow to select an existing {@link MObject}
 * <li>creates the {@link GmNodeModel} and unmask it.
 * <li>optionaly, it allow also to creates and initialize the {@link MObject} if asked.
 * </ul>
 * according to the provided {@link ModelioCreationContext}.
 */
@objid ("e6c44060-55cd-4bad-b572-b457c94a1904")
public class DefaultSelectElementCommand extends DefaultCreateElementCommand {
    @objid ("31e2124d-0375-4437-9d5c-8d140091b786")
    private IModelioNavigationService navigationService;

    /**
     * Creates a node creation command.
     * @param parentNode The parent node
     * @param context Details on the MObject and/or the node to create
     * @param constraint The initial constraint of the created node.
     */
    @objid ("eb9b0677-961e-4d07-83f1-623e1388f458")
    public  DefaultSelectElementCommand(GmCompositeNode parentNode, ModelioCreationContext context, Object constraint) {
        super(parentNode,context,constraint);
    }

    /**
     * Creates a node creation command.
     * @param parentElement The parent MObject of the MObject to create
     * @param parentNode The parent node
     * @param context Details on the MObject and/or the node to create
     * @param constraint The initial constraint of the created node.
     */
    @objid ("116fa471-5bc0-4ac3-b341-61729d3186e2")
    public  DefaultSelectElementCommand(MObject parentElement, GmCompositeNode parentNode, ModelioCreationContext context, Object constraint) {
        super(parentElement,parentNode,context,constraint);
    }

    @objid ("ccce0488-a6e4-4c74-b5ea-36f8ca00661e")
    @Override
    public void execute() {
        canExecute();
        final IGmDiagram diagram = this.parentNode.getDiagram();
        ElementSelectionData data = initSelectionDataModel();
        if ( this.parentNode.getDiagram() != null && this.parentNode.getDiagram().getModelManager() != null) {
            this.navigationService = this.parentNode.getDiagram().getModelManager().getNavigationService();
        }
        Display.getCurrent().syncExec(new Runnable() {
            @Override
            public void run() {
                DefaultElementSelectionDialog dialog = new DefaultElementSelectionDialog(Display.getCurrent().getActiveShell(), CoreSession.getSession(parentElement), navigationService);
                dialog.setInput(data);
                if(dialog.open() == IDialogConstants.OK_ID) {
                    MObject newElement = null;
                    if (data.getSelectedElement() instanceof ElementPlaceolderData) {
                        newElement = createElement(diagram);
                        ElementPlaceolderData newPlaceolder = (ElementPlaceolderData) data.getSelectedElement();
                        newElement.setName(newPlaceolder.getName());
                        if(newElement instanceof ModelElement) {
                            ((ModelElement)newElement).putNoteContent("ModelerModule", "Infrastructure.ModelElement", "description", newPlaceolder.getDescription());
                        }
                    } else {
                        newElement = (ModelElement) data.getSelectedElement();
                    }
        
                    beforeUnmask(newElement);
                    // Show the new element in the diagram (ie create its Gm )
                    GmNodeModel gm = diagram.unmask(parentNode, newElement,constraint);
        
                    mainLinkable = gm;
        
                    afterUnmask(newElement, gm);
        
                }
            }
        });
        
    }

    @objid ("74434934-54d1-477d-9a1b-4511e1225592")
    public ElementSelectionData initSelectionDataModel() {
        ElementSelectionData data = new ElementSelectionData(this.context.getMetaclass());
        data.setFilter(this.parentNode.getDiagram());
        
        
        if(!(this.parentNode  instanceof GmAbstractDiagram)) {
            data.setFilter(this.parentNode.getRelatedElement());
        }
        return data;
    }

}
