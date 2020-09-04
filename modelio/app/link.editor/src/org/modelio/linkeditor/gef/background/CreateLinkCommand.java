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

package org.modelio.linkeditor.gef.background;

import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.linkeditor.LinkTypeDescriptor;
import org.modelio.linkeditor.gef.background.typeselection.LinkTypeSelectionDialog;
import org.modelio.linkeditor.gef.background.typeselection.TypeSelectionModel;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command used by the DropEditPolicy to create a link.
 */
@objid ("1b8ee04d-5e33-11e2-b81d-002564c97630")
public class CreateLinkCommand extends Command {
    @objid ("d5156865-1b43-4315-ac54-86487f3b435f")
    private boolean isFrom;

    @objid ("484532e6-b893-4d3f-8683-ebc2d118d361")
    private TypeSelectionModel typeModel;

    @objid ("b24121dd-a4ee-4367-8106-b1e610e2e5ad")
    private MObject refElement;

    @objid ("8d1381f6-690c-41da-b512-6685e7d5ee0d")
    private MObject[] droppedElements;

    @objid ("27f8b6ff-c727-4f2f-97e3-7e2c27547941")
    private IMdaExpert mdaExpert;

    /**
     * C'tor. the type(s) of the link to create, can be any mix of {@link AssociationEnd}.class, {@link ElementImport} .class, {@link Generalization}.class, {@link Dependency}.class and Stereotype(s) that can be applied to a dependency;
     * @param refElement the center element in the link editor.
     * @param droppedElements the dropped elements.
     * @param isFrom whether the refElement should be a source or a target for the new link.
     * @param candidates links descriptor available for creation.
     * @param mdaExpert the module expert for stereotypes.
     */
    @objid ("e6088f98-5efd-11e2-a8be-00137282c51b")
    public CreateLinkCommand(final MObject refElement, final MObject[] droppedElements, boolean isFrom, Set<LinkTypeDescriptor> candidates, IMdaExpert mdaExpert) {
        this.refElement = refElement;
        this.droppedElements = droppedElements;
        this.isFrom = isFrom;
        this.typeModel = new TypeSelectionModel(refElement, droppedElements, isFrom, candidates);
        this.mdaExpert = mdaExpert;
    }

    @objid ("e6088fa3-5efd-11e2-a8be-00137282c51b")
    @Override
    public boolean canExecute() {
        // The create link command is executable if
        // - there is at least one possible link type (note that mixed droppedELements types will produce a 'no available link' TypeSelectionModel)
        // - 'refElement' is modifiable when creating a direct link (isFrom == true)
        // - all dropped elements are modifiable when creating a reverse link (isFrom == false)
        if (this.typeModel.isEmpty()) {
            return false;
        }
        
        if (this.isFrom && !this.refElement.isModifiable()) {
            return false;
        }
        
        if (!this.isFrom) {
            for (MObject o : this.droppedElements) {
                if (!o.isModifiable()) {
                    return false;
                }
            }
        }
        return true;
    }

    @objid ("e60af1f3-5efd-11e2-a8be-00137282c51b")
    @Override
    public void execute() {
        MTools mTools = MTools.get(this.refElement);
        
        IElementNamer namer = mTools.getNamer();
        IModelFactory factory = mTools.getModelFactories();
        
        // Not really expected to occur but safer...
        if (this.typeModel.isEmpty()) {
            // No usable link type, abort.
            return;
        }
        
        // One or more possible links, prompt the user for his choice.
        LinkTypeDescriptor typeToUse = promptUser(this.typeModel);
        
        if (typeToUse == null) {
            // User aborted the operation
            return;
        }
        
        // Proceed to the creation of the links
        MClass mc = typeToUse.getMClass();
        Stereotype st = typeToUse.getStereotype();
        
        for (MObject droppedElement : this.droppedElements) {
            ModelElement createdLink = (ModelElement) factory.createElement(mc);
            if (st != null) {
                createdLink.getExtension().add(st);
            }
        
            MObject source, target;
            if (this.isFrom) {
                source = this.refElement;
                target = droppedElement;
            } else {
                source = droppedElement;
                target = this.refElement;
            }
        
            MExpert mExpert = mc.getMetamodel().getMExpert();
            mExpert.setSource(createdLink, null, source);
            mExpert.setTarget(createdLink, null, target);
        
            if (createdLink.getCompositionOwner() == null) {
                // FIXME Hack for BPMN (will be fixed some day...)
                if (createdLink instanceof BpmnMessageFlow) {
                    if (source instanceof BpmnParticipant) {
                        BpmnCollaboration collab = ((BpmnParticipant) source).getContainer();
                        ((BpmnMessageFlow) createdLink).setCollaboration(collab);
                    } else if (target instanceof BpmnParticipant) {
                        BpmnCollaboration collab = ((BpmnParticipant) target).getContainer();
                        ((BpmnMessageFlow) createdLink).setCollaboration(collab);
                    } else {
                        // FIXME no valid parent found for the flow, model shield is going to forbid the creation.
                    }
                } else {
                    // Generic determination
                    // In most cases, the source of the link is also its composition owner
                    // When its not, try finding a valid owner in the composition tree before model shield is triggered
                    MObject potentialOwner = source.getCompositionOwner();
                    boolean isOrphan = true;
                    while (isOrphan && potentialOwner != null) {
                        MDependency compositionDep = mExpert.getDefaultCompositionDep(potentialOwner, createdLink);
                        if (compositionDep != null) {
                            // Append new instance of said dependency
                            potentialOwner.mGet(compositionDep).add(createdLink);
                            isOrphan = false;
                        } else {
                            potentialOwner = potentialOwner.getCompositionOwner();
                        }
                    }
                }
            }
        
            createdLink.setName(namer.getUniqueName(createdLink));
        
            if (createdLink instanceof MethodologicalLink && st != null) {
                // When a methodological link is not 'multiple', only keep the last link instance having the 'st' stereotype.
                if (!this.mdaExpert.isMultiple(st)) {
                    for (MethodologicalLink link : ((ModelElement) source).getDependsOnDependency(MethodologicalLink.class)) {
                        if (createdLink != link && link.isStereotyped(st)) {
                            link.delete();
                        }
                    }
                }
            }
        }
    }

    /**
     * Prompt the user for choosing a link type among the possible ones. The dialog is not popped when only one link type is possible.
     */
    @objid ("4ec0a599-0a05-4eef-b398-433ae44a4c37")
    private LinkTypeDescriptor promptUser(TypeSelectionModel model) {
        // Dumb case, only one link type is available => return it as the user choice
        if (model.getTypes().size() == 1) {
            return model.getTypes().iterator().next();
        }
        
        // Pop the selection dialog to get user's choice
        LinkTypeSelectionDialog popup = new LinkTypeSelectionDialog(Display.getDefault().getActiveShell(), model);
        popup.setBlockOnOpen(true);
        if (popup.open() == IDialogConstants.OK_ID) {
            return model.getSelectedType();
        } else {
            // User cancelled.
            return null;
        }
    }

}
