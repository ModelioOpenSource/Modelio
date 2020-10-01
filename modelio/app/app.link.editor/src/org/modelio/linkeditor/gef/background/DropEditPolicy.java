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

package org.modelio.linkeditor.gef.background;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.linkeditor.LinkTypeDescriptor;
import org.modelio.linkeditor.panel.ILinkEditorConfiguration;
import org.modelio.linkeditor.panel.ILinkEditorFilter;
import org.modelio.linkeditor.panel.model.BackgroundModel;
import org.modelio.linkeditor.panel.model.GraphNode;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.platform.mda.infra.service.IModuleService;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Drop Edit Policy used on the background of the LinkEditor to handle creation by D&D.
 */
@objid ("1b91417c-5e33-11e2-b81d-002564c97630")
public class DropEditPolicy extends XYLayoutEditPolicy {
    @objid ("468251a6-ac0e-41c8-a678-0db1b5f1d719")
    private final XYAnchor dummyXYAnchor = new XYAnchor(new Point(10, 10));

    @objid ("4cf07c7c-4dea-4d75-9d31-0f674b269345")
    private PolylineConnection connectionFeedback;

    @objid ("dfc3cde0-8c18-4224-aabe-39ed067f332f")
    private final ChopboxAnchor dummyChopBoxAnchor = new ChopboxAnchor(null);

    @objid ("1b914193-5e33-11e2-b81d-002564c97630")
    @Override
    protected Command getCreateCommand(final CreateRequest request) {
        BackgroundEditPart ep = (BackgroundEditPart) getHost();
        
        // Extracting the dropped elements
        MObject[] droppedElements = (MObject[]) request.getExtendedData().get(LinkEditorDropTargetListener.DROPPED_ELEMENTS);
        
        if (droppedElements != null && droppedElements.length > 0 && ep.isEditMode() && getCenterNode() != null) {
            // determine whether it is "to" or "from"
            boolean isFrom = getSide(request);
        
            Set<LinkTypeDescriptor> candidates = collectTypes(getCenterNode().getData(), droppedElements, isFrom);
            return new CreateLinkCommand(getCenterNode().getData(), droppedElements, isFrom, candidates, ep.getContext().get(IModuleService.class).getMdaExpert());
        }
        return UnexecutableCommand.INSTANCE;
    }

    @objid ("1b9141b1-5e33-11e2-b81d-002564c97630")
    @Override
    protected EditPolicy createChildEditPolicy(final EditPart child) {
        return null;
    }

    @objid ("1b93a2da-5e33-11e2-b81d-002564c97630")
    @Override
    protected void showLayoutTargetFeedback(final Request request) {
        // Only CreateRequest are handled
        if (!(request instanceof CreateRequest)) {
            return;
        }
        CreateRequest createRequest = (CreateRequest) request;
        GraphNode centerNode = getCenterNode();
        GraphicalEditPart centerNodeEditPart = (GraphicalEditPart) getHost().getViewer().getEditPartRegistry().get(centerNode);
        IFigure centerNodeFigure = centerNodeEditPart.getFigure();
        this.dummyChopBoxAnchor.setOwner(centerNodeFigure);
        // update the XY anchor position.
        this.dummyXYAnchor.setLocation(createRequest.getLocation());
        
        // determine wether it is "to" or "from"
        boolean isFrom = getSide(createRequest);
        
        if (this.connectionFeedback == null) {
            // add the "link" feedback.
            this.connectionFeedback = new PolylineConnection();
            this.connectionFeedback.setVisible(true);
            this.connectionFeedback.setOpaque(true);
        
            getFeedbackLayer().add(this.connectionFeedback);
        }
        if (isFrom) {
            this.connectionFeedback.setSourceAnchor(this.dummyXYAnchor);
            this.connectionFeedback.setTargetAnchor(this.dummyChopBoxAnchor);
        } else {
            this.connectionFeedback.setSourceAnchor(this.dummyChopBoxAnchor);
            this.connectionFeedback.setTargetAnchor(this.dummyXYAnchor);
        }
        getFeedbackLayer().remove(this.connectionFeedback);
        getFeedbackLayer().add(this.connectionFeedback);
        
        super.showLayoutTargetFeedback(request);
    }

    @objid ("1b93a2e1-5e33-11e2-b81d-002564c97630")
    @Override
    public void deactivate() {
        super.deactivate();
        if (this.connectionFeedback != null) {
            getFeedbackLayer().remove(this.connectionFeedback);
            this.connectionFeedback = null;
        }
    }

    /**
     * Return true if the drop creates a link starting from center node.
     * 
     * @param request the creation request.
     * @return true if the drop should create a link that is originated from centerNode, false for a link that should target centerNode.
     */
    @objid ("1b93a2e4-5e33-11e2-b81d-002564c97630")
    private boolean getSide(final CreateRequest request) {
        BackgroundEditPart ep = (BackgroundEditPart) getHost();
        
        GraphNode centerNode = getCenterNode();
        boolean vertical = ep.isVerticalLayout();
        
        if (vertical) {
            if (request.getLocation().y < (centerNode.y + (centerNode.height / 2))) {
                // drop above the centerNode => centerNode outgoing link
                return true;
            } else {
                // drop below the centerNode => centerNode incoming link
                return false;
            }
        } else {
            if (request.getLocation().x < (centerNode.x + (centerNode.width / 2))) {
                // drop on the left of centerNode => centerNode incoming link
                return false;
            } else {
                // drop on the right of centerNode => centerNode outgoing link
                return true;
            }
        }
    }

    @objid ("1b93a2eb-5e33-11e2-b81d-002564c97630")
    @Override
    protected void eraseLayoutTargetFeedback(final Request request) {
        if (this.connectionFeedback != null) {
            getFeedbackLayer().remove(this.connectionFeedback);
            this.connectionFeedback = null;
        }
        super.eraseLayoutTargetFeedback(request);
    }

    /**
     * @return the node at the center of the link editor.
     */
    @objid ("1b93a30e-5e33-11e2-b81d-002564c97630")
    private GraphNode getCenterNode() {
        GraphNode centerNode = ((BackgroundModel) getHost().getModel()).getCenter();
        return centerNode;
    }

    /**
     * Returns the possible link types for creation.
     * 
     * @return a list of LinkTypeDescriptor
     */
    @objid ("b21b5287-7aa7-4642-9ce4-f1a12656e688")
    private Set<LinkTypeDescriptor> collectTypes(MObject refElement, MObject[] droppedElements, boolean isFrom) {
        BackgroundEditPart ep = (BackgroundEditPart) getHost();
        ILinkEditorConfiguration linkEditorConfiguration = ep.getModel().getConfiguration();
        IModuleService moduleService = ep.getContext().get(IModuleService.class);
        
        IMdaExpert mdaExpert = moduleService.getMdaExpert();
        
        // Compute the set of link candidate types
        Set<LinkTypeDescriptor> ret = new TreeSet<>();
        
        ILinkEditorFilter configurationFilter = linkEditorConfiguration.getLinkFilter();
        List<MClass> enabledLinkMetaclasses = getEnabledLinkMetaclasses(refElement.getMClass().getMetamodel(), configurationFilter);
        
        // Check dropped elements compatibility with the enabled link meta-classes, removing non applicable meta-classes.
        for (MObject droppedElement : droppedElements) {
            for (MClass mc : new ArrayList<>(enabledLinkMetaclasses)) {
                if (!mc.getMetamodel().getMExpert().canLink(mc, isFrom ? refElement : droppedElement, isFrom ? droppedElement : refElement)) {
                    enabledLinkMetaclasses.remove(mc);
                } else {
                    String name = mc.getQualifiedName();
                    if (name.equals(Association.MQNAME) || name.equals(Link.MQNAME) || name.equals(Connector.MQNAME)) {
                        enabledLinkMetaclasses.remove(mc);
                    }
                }
            }
        }
        
        MModelServices mmService = new MModelServices(CoreSession.getSession(refElement));
        MMetamodel metamodel = mmService.getMetamodel();
        
        for (MClass mc : enabledLinkMetaclasses) {
            if (mc.getQualifiedName().equals(BpmnMessageFlow.MQNAME)) {
                continue;
            }
        
            // Naked metaclass ?
            if (configurationFilter.accept(mc, null)) {
                ret.add(new LinkTypeDescriptor(mc, null));
            }
        
            // Stereotyped metaclasses ?
            List<Stereotype> enabledStereotypes = getEnabledStereotypes(mmService, moduleService, mc);
            for (Stereotype s : filterValidStereotypes(mdaExpert, mc, filterStereotypes(configurationFilter, mc, enabledStereotypes), refElement, droppedElements, isFrom)) {
                ret.add(new LinkTypeDescriptor(metamodel.getMClass(s.getBaseClassName()), s));
            }
        
        }
        return ret;
    }

    /**
     * Select the metamodel meta-classes that:
     * <ul>
     * <li>are representing a 'link'</li>
     * <li>pass the configuration filter on its meta-class criterion</li>
     * </ul>
     * 
     * @param metamodel the metamodel.
     * @param filter the selected filter in link editor.
     * @return link metaclasses that are enabled by the filter.
     */
    @objid ("18f07109-b311-4259-8d1b-a15281caa72b")
    private List<MClass> getEnabledLinkMetaclasses(MMetamodel metamodel, ILinkEditorFilter filter) {
        List<MClass> ret = new ArrayList<>();
        for (MClass mc : metamodel.getRegisteredMClasses()) {
            if (mc.isLinkMetaclass()) {
                if (filter == null || filter.isLinkTypeEnabled(mc)) {
                    ret.add(mc);
                }
            }
        }
        return ret;
    }

    /**
     * Filter the stereotypes using the {@link ILinkEditorConfiguration} filter
     * 
     * @param filter the selected filter in link editor.
     * @param mc a metaclass.
     * @param candidatesStereotypes stereotypes compatible with the given metaclass.
     * @return link stereotypes that are enabled by the filter for this metaclass.
     */
    @objid ("648503b0-5320-49d8-a215-0b5df8397a4a")
    private List<Stereotype> filterStereotypes(ILinkEditorFilter filter, MClass mc, List<Stereotype> candidatesStereotypes) {
        // Stereotyped metaclass ?
        for (Stereotype st : new ArrayList<>(candidatesStereotypes)) {
            if (!filter.accept(mc, st)) {
                candidatesStereotypes.remove(st);
            }
        }
        return candidatesStereotypes;
    }

    /**
     * Get all the visible stereotypes applicable on 'mc' that are provided by active modules.
     * 
     * @param mmService the model service to find stereotypes.
     * @param moduleService the module service to find active modules.
     * @param mc a metaclass.
     * @return link stereotypes that are provided by active modules for this metaclass.
     */
    @objid ("b64d0e86-2282-447b-a6c5-c93e79c1af60")
    private List<Stereotype> getEnabledStereotypes(MModelServices mmService, IModuleService moduleService, MClass mc) {
        List<Stereotype> results = new ArrayList<>();
        Set<String> activatedModules = moduleService.getStartedModules().stream()
                .map(rtModule -> rtModule.getModel())
                .map(module -> module.getName())
                .collect(Collectors.toSet());
        activatedModules.add("LocalModule");
        
        for (Stereotype st : mmService.findStereotypes(".*", ".*", mc)) {
            ModuleComponent module = st.getModule();
            if (st.isValid() && !st.isIsHidden() && module != null && activatedModules.contains(module.getName())) {
                results.add(st);
            }
        }
        return results;
    }

    /**
     * Filter stereotype using the MDA Expert
     * 
     * @param mdaExpert an mda expert.
     * @param mc a metaclass.
     * @param candidatesStereotypes stereotypes compatible with the given metaclass.
     * @param refElement the center element in the link editor.
     * @param droppedElements the dropped elements.
     * @param isFrom whether the refElement should be a source or a target for the new link.
     * @return link stereotypes that are accepted by this expert.
     */
    @objid ("babd1a72-9c22-4204-b538-2d4068f1d56c")
    private List<Stereotype> filterValidStereotypes(IMdaExpert mdaExpert, MClass mc, List<Stereotype> candidatesStereotypes, MObject refElement, MObject[] droppedElements, boolean isFrom) {
        for (MObject droppedElement : droppedElements) {
            for (Stereotype st : new ArrayList<>(candidatesStereotypes)) {
                if (!mdaExpert.canLink(st, mc, isFrom ? refElement : droppedElement, isFrom ? droppedElement : refElement)) {
                    candidatesStereotypes.remove(st);
                }
            }
        }
        return candidatesStereotypes;
    }

}
