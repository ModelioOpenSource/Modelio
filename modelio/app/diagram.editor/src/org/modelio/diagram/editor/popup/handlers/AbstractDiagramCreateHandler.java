/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.popup.handlers;

import java.util.Map;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.elements.common.root.ScalableFreeformRootEditPart2;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specialization of the base abstract class for all creation handlers provided by diagram editors.
 * 
 * @author fpoyer
 */
@objid ("6688abc7-33f7-11e2-95fe-001ec947c8cc")
public abstract class AbstractDiagramCreateHandler extends AbstractCreateHandler {
    @objid ("032e8fcf-5e26-11e2-a8be-00137282c51b")
    @Inject
    private IMModelServices modelService;

    @objid ("09f968ec-80cc-4a36-8a72-a4e954c40e15")
    private CreateRequest targetRequest;

    /**
     * Called by {@link com.modeliosoft.modelio.diagram.editor.createpopup.contribs.CreationContributionItem} on the handler of a
     * creation command to filter displayed items.
     * 
     * @param context an evaluation context with the command creation parameters.
     * @return true to display the item, false to hide it.
     */
    @objid ("668b0e1c-33f7-11e2-95fe-001ec947c8cc")
    public boolean isToFilter(final Map<String, String> context) {
        this.metaclass = context.get("metaclass");
        this.dependency = context.get("dependency");
        this.stereotype = context.get("stereotype");
        return shouldEnable();
    }

    @objid ("668b0e26-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected MObject create(MObject selectedElement) throws ExecutionException {
        // Base behavior: build a CreateRequest and send it to the selected
        // EditPart to get a Command, then execute said Command in the
        // CommandStack.
        EditPart selectedEditPart = getSelectedEditPart();
        if (selectedEditPart != null) {
        
            try {
                updateTargetRequest();
            } catch (IllegalArgumentException | ElementNotUniqueException e) {
                throw new ExecutionException("Cannot create element.", e);
            }
        
            CreateRequest createRequest = getTargetRequest();
            EditPart targetEditPart = findTargetEditPart(selectedEditPart);
        
            if (targetEditPart != null) {
                Command command = targetEditPart.getCommand(createRequest);
                if (command != null && command.canExecute()) {
                    targetEditPart.getViewer().getEditDomain().getCommandStack().execute(command);
                }
            }
        }
        
        this.targetRequest = null;
        return null;
    }

    @objid ("668b0e2f-33f7-11e2-95fe-001ec947c8cc")
    protected CreateRequest createTargetRequest() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setSize(new Dimension(-1, -1));
        return createRequest;
    }

    /**
     * Find the edit part that will accept the {@link #getTargetRequest()} and that relates the {@link #getSelectedElement()}.
     * <p>
     * Will parse child edit parts of the given edit part.
     * 
     * @param fromEditPart The edit part to start the lookup from.
     * @return the found edit part or <code>null</code>.
     */
    @objid ("668b0e33-33f7-11e2-95fe-001ec947c8cc")
    protected EditPart findTargetEditPart(final EditPart fromEditPart) {
        EditPart target = fromEditPart.getTargetEditPart(getTargetRequest());
        if (target != null) {
            if (relateSelectedElement(target)) {
                return target;
            } else {
                return null;
            }
        }
        
        for (Object child : fromEditPart.getChildren()) {
            target = findTargetEditPart((EditPart) child);
            if (target != null && relateSelectedElement(target)) {
                return target;
            }
        }
        return null;
    }

    /**
     * Get the currently selected EditPart.
     * 
     * @return the currently selected EditPart.
     */
    @objid ("668d707c-33f7-11e2-95fe-001ec947c8cc")
    protected EditPart getSelectedEditPart() {
        if (this.selection instanceof IStructuredSelection) {
            if (((IStructuredSelection) this.selection).getFirstElement() instanceof IAdaptable) {
                IAdaptable adapter = (IAdaptable) ((IStructuredSelection) this.selection).getFirstElement();
                return adapter.getAdapter(EditPart.class);
            }
        }
        // else
        return null;
    }

    /**
     * Lazily creates and returns the request used when communicating with the target editpart.
     * 
     * @return the target request
     */
    @objid ("668d7081-33f7-11e2-95fe-001ec947c8cc")
    protected CreateRequest getTargetRequest() {
        if (this.targetRequest == null) {
            this.targetRequest = createTargetRequest();
        }
        return this.targetRequest;
    }

    @objid ("668d7086-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void postCommit(MObject element) {
        // In most case, nothing to do in postCommit.
    }

    /**
     * @throws ElementNotUniqueException
     * 
     * @throws java.lang.IllegalArgumentException if the requested metaclass cannot be found.
     */
    @objid ("668d708a-33f7-11e2-95fe-001ec947c8cc")
    protected void updateTargetRequest() throws ElementNotUniqueException, IllegalArgumentException {
        EditPart selectedEditPart = getSelectedEditPart();
        if (selectedEditPart != null) {
            this.targetRequest = null;
            CreateRequest createRequest = getTargetRequest();
            Point creationLocation;
            if (selectedEditPart instanceof GraphicalEditPart) {
                IFigure figure = ((GraphicalEditPart) selectedEditPart).getFigure();
                Rectangle bounds;
                if (figure instanceof HandleBounds) {
                    bounds = ((HandleBounds) figure).getHandleBounds();
                } else {
                    bounds = figure.getBounds();
                }
                Point creationLocationTip = ((ScalableFreeformRootEditPart2) selectedEditPart.getRoot()).getCreationLocationTip();
                Point.SINGLETON.setLocation(creationLocationTip);
                figure.translateToRelative(Point.SINGLETON);
                if (figure.containsPoint(Point.SINGLETON)) {
                    creationLocation = creationLocationTip;
                } else {
                    Point.SINGLETON.setLocation(bounds.getCenter());
                    figure.translateToAbsolute(Point.SINGLETON);
                    creationLocation = Point.SINGLETON.getCopy();
                }
            } else {
                ScalableFreeformRootEditPart2 root = (ScalableFreeformRootEditPart2) selectedEditPart.getRoot();
                creationLocation = root.getCreationLocationTip();
            }
            createRequest.setLocation(creationLocation);
        
            Stereotype iStereotype = null;
            if (this.stereotype != null) {
                iStereotype = this.modelService.getStereotype(".*", this.stereotype, this.metaclass);
            }
        
            MMetamodel mm = this.modelService.getMetamodel();
            MClass mClass = mm.getMClass(this.metaclass);
            MDependency dep = ((GmModel) selectedEditPart.getModel()).getRelatedElement().getMClass().getDependency(this.dependency);
        
            createRequest.setFactory(new ModelioCreationContext(mClass, dep, iStereotype));
        } else {
            this.targetRequest = null;
        }
    }

    @objid ("668d708f-33f7-11e2-95fe-001ec947c8cc")
    private boolean relateSelectedElement(final EditPart a) {
        MObject el = ((GmModel) a.getModel()).getRelatedElement();
        if (el instanceof AbstractDiagram) {
            el = ((AbstractDiagram) el).getOrigin();
        }
        return (el != null && el.equals(getSelectedElement()));
    }

    /**
     * Tells whether the handler can execute on the selected edit part.
     * @return
     */
    @objid ("668d7095-33f7-11e2-95fe-001ec947c8cc")
    private boolean shouldEnable() {
        EditPart selectedEditPart = getSelectedEditPart();
        if (selectedEditPart != null) {
            try {
                updateTargetRequest();
                CreateRequest r = getTargetRequest();
        
                EditPart targetEditPart = findTargetEditPart(selectedEditPart);
                if (targetEditPart != null) {
                    Command command = targetEditPart.getCommand(r);
                    return (command != null && command.canExecute());
                }
            } catch (IllegalArgumentException e) {
                return true;
            } catch (ElementNotUniqueException e) {
                return true;
            }
        }
        return false;
    }

}
