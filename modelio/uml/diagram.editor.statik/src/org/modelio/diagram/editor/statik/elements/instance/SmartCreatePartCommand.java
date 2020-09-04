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

package org.modelio.diagram.editor.statik.elements.instance;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.UnmaskHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command used for smart unmask interactions: <br>
 * Creates a {@link BindableInstance} representing the dropped element.
 * 
 * @author cma
 */
@objid ("34383aaf-55b7-11e2-877f-002564c97630")
public class SmartCreatePartCommand extends Command {
    @objid ("34383ab1-55b7-11e2-877f-002564c97630")
    private MObject parentElement;

    @objid ("34383ab4-55b7-11e2-877f-002564c97630")
    private MObject toUnmask;

    @objid ("5b6084f3-5bd5-11e2-9e33-00137282c51b")
    private EditPart parentEditPart;

    @objid ("5b6084f6-5bd5-11e2-9e33-00137282c51b")
    private EditPartViewer viewer;

    @objid ("be074210-a4de-404a-8ebf-2e8840098f1c")
    private Point location;

    /**
     * @param dropLocation the location where the ObjectNode is to be unmasked.
     * @param toUnmask the element that the ObjectNode will represent.
     * @param parentEditPart the edit part handling the unmasking
     * @param parentElement the element that will own the new ObjectNode
     */
    @objid ("34383aba-55b7-11e2-877f-002564c97630")
    public SmartCreatePartCommand(final Point dropLocation, final MObject toUnmask, final EditPart parentEditPart, final MObject parentElement) {
        this.location = dropLocation;
        this.toUnmask = toUnmask;
        this.parentEditPart = parentEditPart;
        this.parentElement = parentElement;
        this.viewer = parentEditPart.getViewer();
    }

    @objid ("34383ac9-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final GmModel gmModel = (GmModel) this.parentEditPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        return (this.parentElement != null && this.parentElement.isValid() && this.parentElement.isModifiable());
    }

    @objid ("34383ace-55b7-11e2-877f-002564c97630")
    @Override
    public void execute() {
        GmModel gmModel = (GmModel) this.parentEditPart.getModel();
        IGmDiagram gmDiagram = gmModel.getDiagram();
        IModelManager modelManager = gmDiagram.getModelManager();
        IStandardModelFactory factory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the smart node
        BindableInstance instanceNode = factory.createBindableInstance();
        
        // Attach to parent
        MExpert expert = this.parentElement.getMClass().getMetamodel().getMExpert();
        MDependency effectiveDependency = expert.getDefaultCompositionDep(this.parentElement, instanceNode);
        
        if (effectiveDependency == null) {
            StringBuilder msg = new StringBuilder();
            msg.append("Cannot find a composition dependency to attach ");
            msg.append(instanceNode.toString());
            msg.append(" to ");
            msg.append(this.parentElement.toString());
            throw new IllegalStateException(msg.toString());
        }
        this.parentElement.mGet(effectiveDependency).add(instanceNode);
        
        // Attach to dropped element
        Collection<Port> ports = null;
        if (this.toUnmask instanceof Instance) {
            instanceNode.setRepresentedFeature((Instance) this.toUnmask);
        } else if (this.toUnmask instanceof Attribute) {
            instanceNode.setRepresentedFeature((Attribute) this.toUnmask);
        } else if (this.toUnmask instanceof AssociationEnd) {
            instanceNode.setRepresentedFeature((AssociationEnd) this.toUnmask);
        } else if (this.toUnmask instanceof Classifier) {
            instanceNode.setBase((NameSpace) this.toUnmask);
            ports = createPorts(instanceNode);
        } else if (this.toUnmask instanceof NameSpace) {
            instanceNode.setBase((NameSpace) this.toUnmask);
        } else if (this.toUnmask instanceof UmlModelElement) {
            instanceNode.setRepresentedFeature((UmlModelElement) this.toUnmask);
        }
        
        // Unmask the node
        unmaskElement(instanceNode);
        
        // Unmask the part ports too
        if (ports != null && ports.size() > 0) {
            // Force graphical validation of parent to avoid some nasty side effects
            ((GraphicalEditPart) this.parentEditPart).getFigure().getUpdateManager().performValidation();
        
            // Translate point to unmask port on the right
            this.location.translate(100, 10);
            Command cmd = UnmaskHelper.getUnmaskCommand(this.viewer, ports, this.location);
            if (cmd != null && cmd.canExecute()) {
                cmd.execute();
            }
        }
    }

    /**
     * Copy the Ports of the base class to the instance.
     * @param part the part where Ports are to be added.
     * @return the created ports.
     */
    @objid ("34383ad1-55b7-11e2-877f-002564c97630")
    private Collection<Port> createPorts(final BindableInstance part) {
        final Classifier type = (Classifier) part.getBase();
        final Collection<Port> ret = new ArrayList<>(type.getInternalStructure().size());
        
        for (BindableInstance typePart : type.getInternalStructure()) {
            if (typePart instanceof Port) {
                final Port partPort = (Port) MTools.getModelTool().cloneElement(typePart);
                partPort.setInternalOwner(null);
                partPort.setCluster(part);
                partPort.setRepresentedFeature(typePart);
        
                ret.add(partPort);
            }
        }
        return ret;
    }

    @objid ("34383ade-55b7-11e2-877f-002564c97630")
    private void unmaskElement(final MObject el) {
        final ModelioCreationContext gmCreationContext = new ModelioCreationContext(el);
        
        final CreateRequest creationRequest = new CreateRequest();
        creationRequest.setLocation(this.location);
        creationRequest.setSize(new Dimension(-1, -1));
        creationRequest.setFactory(gmCreationContext);
        
        final Command cmd = this.parentEditPart.getTargetEditPart(creationRequest)
                .getCommand(creationRequest);
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
        }
    }

}
