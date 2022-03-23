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
package org.modelio.diagram.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.elements.common.ghostlink.GhostLinkEditPart;
import org.modelio.diagram.elements.common.ghostnode.GhostNodeEditPart;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.factory.DelegatingEditPartFactory;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.factories.common.DiagramElementsEditPartFactory;
import org.modelio.diagram.elements.factories.common.DiagramElementsGmNodeFactory;
import org.modelio.diagram.elements.factories.drawing.DrawingEditPartFactory;
import org.modelio.diagram.elements.factories.generic.GenericEditPartFactory;
import org.modelio.diagram.elements.factories.generic.GenericGmFactory;

/**
 * Implementation of {@link EditPartFactory} delegating all requests to cascaded factories in the following order :
 * <ol>
 * <li>Ghost elements</li>
 * <li>Drawings</li>
 * <li>The main factory</li>
 * <li>Secondary factories registered as extensions in the {@link DiagramFactoryRegistry}</li>
 * <li>The {@link DiagramElementsGmNodeFactory}</li>
 * <li>The {@link GenericGmFactory}</li>
 * </ol>
 */
@objid ("44970a5f-236c-4ea0-8476-b48a25ac0b66")
public class StandardEditPartFactory implements EditPartFactory {
    @objid ("dac5efd9-4eaf-41c7-bcf0-9a7b1ea11749")
    private final DrawingEditPartFactory drawingEditPartFactory;

    @objid ("4201416d-d06f-4088-9b00-09efb0ac6a6f")
    private final EditPartFactory mainFactory;

    @objid ("5414056a-1839-498d-a73c-8f9baaefef8d")
    private final DelegatingEditPartFactory secondaryFactories;

    @objid ("28e9f673-0546-4d74-a462-09a11f8aab3c")
    private final EditPartFactory diagramElementsFactory;

    @objid ("fe99db0e-fd74-4b6b-b761-d0b35ce4fca4")
    private final EditPartFactory genericFactory;

    /**
     * Instantiate the factory.
     * @param factoryId identifier of the main factory.
     */
    @objid ("ba6ea29a-5b54-4e1f-96c2-be423f6fac92")
    public  StandardEditPartFactory(String factoryId) {
        this.mainFactory = DiagramFactoryRegistry.getInstance().getEditPartFactory(factoryId);
        this.secondaryFactories = new DelegatingEditPartFactory(DiagramFactoryRegistry.getInstance().getExtensions(factoryId));
        this.diagramElementsFactory = new DiagramElementsEditPartFactory();
        this.drawingEditPartFactory = new DrawingEditPartFactory();
        this.genericFactory = new GenericEditPartFactory();
        
    }

    @objid ("76579b95-e9ca-4560-8274-c1d15b7cea1e")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart;
        
        // 1 - Special handling of "dead" elements.
        if (model instanceof GmModel) {
            GmModel gmModel = (GmModel) model;
            if (gmModel.getRelatedElement() == null || gmModel.getRelatedElement().isDeleted() || gmModel.getRelatedElement().isShell()) {
                if (gmModel instanceof GmNodeModel) {
                    editPart = new GhostNodeEditPart();
                    editPart.setModel(gmModel);
                    return editPart;
                } else {
                    editPart = new GhostLinkEditPart();
                    editPart.setModel(gmModel);
                    return editPart;
                }
            }
        }
        
        // 2 - Drawings
        editPart = this.drawingEditPartFactory.createEditPart(context, model);
        if (editPart != null) {
            return editPart;
        }
        
        // 3 - Main factory
        editPart = this.mainFactory.createEditPart(context, model);
        if (editPart != null) {
            return editPart;
        }
        
        // 4 - Secondary factories
        editPart = this.secondaryFactories.createEditPart(context, model);
        if (editPart != null) {
            return editPart;
        }
        
        // 5 - Common elements
        editPart = this.diagramElementsFactory.createEditPart(context, model);
        if (editPart != null) {
            return editPart;
        }
        
        // 6 - Generic factory
        editPart = this.genericFactory.createEditPart(context, model);
        if (editPart != null) {
            return editPart;
        }
        
        throw new IllegalArgumentException(model + " is not supported.");
        
    }

    @objid ("620c2085-e879-4e0c-a387-23aaced18ed2")
    public DelegatingEditPartFactory getSecondaryFactories() {
        return this.secondaryFactories;
    }

}
