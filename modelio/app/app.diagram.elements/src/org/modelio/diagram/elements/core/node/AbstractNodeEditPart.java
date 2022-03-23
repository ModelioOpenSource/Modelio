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
package org.modelio.diagram.elements.core.node;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.ui.env.IModelioEnvService;
import org.modelio.diagram.elements.core.commands.FitToMinSizeCommand;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.anchors.INodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.RectangleNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.policies.DefaultDeleteNodeEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultRefreshFromModelEditPolicy;
import org.modelio.diagram.elements.core.policies.DelegatingDirectEditionEditPolicy;
import org.modelio.diagram.elements.core.policies.LayoutChildrenNodeConnectionsHelper;
import org.modelio.diagram.elements.core.policies.LayoutConnectionsCompositeNodeLayoutEditPolicyDecorator;
import org.modelio.diagram.elements.core.policies.ReadOnlyHoverFeedbackEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.requests.NavigationRequest;
import org.modelio.diagram.elements.factories.StandardEditPartFactory;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.FillMode;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.ui.swt.imagesselector.ImageSelectionDialog;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit parts for {@link GmNodeModel nodes} representing {@link MObject Elements}.
 */
@objid ("8090cd05-1dec-11e2-8cad-001ec947c8cc")
public abstract class AbstractNodeEditPart extends AbstractGraphicalEditPart implements IAnchorModelProvider, PropertyChangeListener {
    @objid ("9219e2f1-1e83-11e2-8cad-001ec947c8cc")
    private RepresentationMode initialRepMode = null;

    /**
     * Change bounds request property to specify the previous {@link #getTrimmedBounds()}.
     * 
     * @since 3.4.1
     */
    @objid ("7f1477d0-c5d3-4392-bc89-0da32fceab9d")
    public static final Object REQPROP_OLD_TRIMMED_BOUNDS = "old_trimmed_bounds";

    /**
     * Change bounds request property to specify requested {@link #getTrimmedBounds()}.
     * 
     * @since 3.4.1
     */
    @objid ("7703cc3b-32fa-4cc1-8b6a-20a2e0b43733")
    public static final Object REQPROP_TRIMMED_BOUNDS = "new_trimmed_bounds";

    @objid ("8090cd09-1dec-11e2-8cad-001ec947c8cc")
    private IDragTrackerProvider dragTrackerProvider;

    /**
     * Constructor.
     */
    @objid ("8090cd0b-1dec-11e2-8cad-001ec947c8cc")
    public  AbstractNodeEditPart() {
        super();
        this.dragTrackerProvider = new DefaultDragTrackerProvider(this);
        
    }

    @objid ("8090cd0e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void activate() {
        super.activate();
        getModel().addPropertyChangeListener(this);
        
    }

    /**
     * Changes the custom image on the element.
     * @param gmModel the selected gmModel
     * @param imageUrl URL to the new image
     */
    @objid ("9fa5a25a-f5f4-4953-b10f-731dbf90caad")
    public void changeCustomImage(final GmNodeModel gmModel, URL imageUrl) {
        final MObject obElement = gmModel.getRelatedElement();
        try {
            TagType tagType = gmModel.getDiagram().getModelManager().getModelServices().getTagType("ModelerModule", ".*", "userDiagramImage", obElement.getMClass());
            if (tagType == null)
                return;
        
            if (obElement instanceof ModelElement) {
                ModelElement el = (ModelElement) obElement;
                if (imageUrl != null) {
                    el.putTagValue(tagType, imageUrl.toString());
                    gmModel.obElementsUpdated();
                }
            }
        
        } catch (ElementNotUniqueException e) {
            DiagramElements.LOG.error(e);
        }
        
    }

    /**
     * Create a serializable anchor model from the given anchor.
     * @param anchor a figure anchor
     * @return an anchor model.
     */
    @objid ("8090cd11-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object createAnchorModel(final ConnectionAnchor anchor) {
        return getNodeAnchorProvider().createAnchorModel(anchor);
    }

    @objid ("8090cd1b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        super.deactivate();
        getModel().removePropertyChangeListener(this);
        
    }

    /**
     * Returns an object which is an instance of the given class associated with this object. Returns <code>null</code> if no such object can be found.
     * <p>
     * Extends {@link AbstractGraphicalEditPart#getAdapter(Class)} to support {@link MObject}, {@link IGmObject}, {@link GmModel} and their subclasses.
     * @see IAdaptable#getAdapter(Class)
     * @param adapter the adapter class to look up
     * @return a object castable to the given class, or <code>null</code> if this object does not have an adapter for the given class
     */
    @objid ("8090cd1e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getAdapter(Class adapter) {
        final GmNodeModel gmModel = getModel();
        
        // Support IGmObject, GmModel and its subclasses
        if (adapter.isInstance(gmModel)) {
            return gmModel;
        }
        
        // Support ObElement & subclasses
        if (gmModel != null) {
            final MObject obElement = gmModel.getRelatedElement();
            if (adapter.isInstance(obElement)) {
                return obElement;
            }
        }
        return super.getAdapter(adapter);
    }

    /**
     * Open a dialog to select an image from an imageLibrary.
     * @return the selected image. Might be <code>null</code>.
     */
    @objid ("70b5762d-fcb2-459d-8651-ee0f1660b428")
    public URL getCustomImageUrl() {
        IModelioEnvService context = getModel().getDiagram().getModelManager().getService(IModelioEnvService.class);
        ImageSelectionDialog imageSelctionDialog = new ImageSelectionDialog((Display.getCurrent().getActiveShell()), context.getImageLibrary(), context.getImagesCache());
        
        if (imageSelctionDialog.open() == IDialogConstants.OK_ID) {
            return imageSelctionDialog.getSelectedItem();
        }
        return null;
    }

    @objid ("8090cd26-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public DragTracker getDragTracker(Request request) {
        return this.dragTrackerProvider.getDragTracker(request);
    }

    @objid ("8f781d53-cf90-4d4d-aebe-9b8ad9888406")
    public List<EditPolicy> getInstalledPolicies() {
        List<EditPolicy> policies = new ArrayList<>();
        
        EditPolicyIterator it = getEditPolicyIterator();
        
        while (it.hasNext()) {
            policies.add(it.next());
        }
        return policies;
    }

    @objid ("4116726d-26e0-4426-8ee3-bb023f68b5c2")
    @Override
    public GmNodeModel getModel() {
        return (GmNodeModel) super.getModel();
    }

    /**
     * <p>
     * This method must return a policy handling the kind of request passed as parameter <em>IF (and only if)</em> it has specific geometric needs. This policy will be installed by the EditPart containing this node with the DRAG_ROLE role.
     * </p>
     * <p>
     * If this node has no specific needs, it can return <code>null</code> which indicates to the parent that the default behavior should be used.
     * </p>
     * @param requestType the type of request the returned policy must handle.
     * @return a policy able to handle the passed type of request or <code>null</code>.
     */
    @objid ("8090cd30-1dec-11e2-8cad-001ec947c8cc")
    public SelectionEditPolicy getPreferredDragRolePolicy(String requestType) {
        assert (RequestConstants.REQ_SELECTION.equals(requestType) ||
                RequestConstants.REQ_MOVE.equals(requestType) || RequestConstants.REQ_RESIZE.equals(requestType)) : "AbstractNodeEditPart#getPreferredDragRolePolicy: Unhandled request type";
        return null;
    }

    @objid ("80932f32-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return getNodeAnchorProvider().getSourceConnectionAnchor(this, connection);
    }

    @objid ("80932f3c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return getNodeAnchorProvider().getSourceConnectionAnchor(this, request);
    }

    @objid ("80932f46-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connectionPart) {
        return getNodeAnchorProvider().getTargetConnectionAnchor(this, connectionPart);
    }

    @objid ("80932f51-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return getNodeAnchorProvider().getTargetConnectionAnchor(this, request);
    }

    /**
     * Get the bounds that may be used by the parent container layouter.
     * <p>
     * Returns usually the figure {@link IFigure#getBounds()}. May be redefined by subclasses to return a different (smaller) rectangle that may make the layout have a better look.
     * @return the figure trimmed bounds.
     * @since 3.4.1
     */
    @objid ("d9999449-f04b-480b-9d8c-95d45027a9f7")
    public Rectangle getTrimmedBounds() {
        return getFigure().getBounds();
    }

    /**
     * Redefined to encapsulate :<ul>
     * <li>layout edit policies into {@link #createLayoutPolicyDecorator(EditPolicy)}.
     * <li>primary drag policies into {@link #createPrimaryDragPolicyDecorator(EditPolicy)}.
     * @see #createLayoutPolicyDecorator(EditPolicy)
     * @see #createPrimaryDragPolicyDecorator(EditPolicy)
     * @since 5.1.0
     */
    @objid ("e821f0c5-e958-4cd1-9790-5a14fc564ec2")
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (editPolicy == null) {
            // In this case remove all
            super.installEditPolicy(key, editPolicy);
        } else if (EditPolicy.LAYOUT_ROLE.equals(key)) {
            super.installEditPolicy(key, createLayoutPolicyDecorator(editPolicy));
        } else if (EditPolicy.PRIMARY_DRAG_ROLE.equals(key)) {
            super.installEditPolicy(key, createPrimaryDragPolicyDecorator(editPolicy));
        } else {
            super.installEditPolicy(key, editPolicy);
        }
        
    }

    /**
     * Overridden to add a specific behavior on <ul>
     * <li> {@link RequestConstants#REQ_DIRECT_EDIT DIRECT_EDIT} request: delegates to {@link #performDirectEditRequest(Request)}
     * <li> {@link RequestConstants#REQ_OPEN} : fires the Modelio activation service.
     * <li> {@link NavigationRequest#TYPE} : fires the Modelio navigation service
     * </ul>
     * the request if forwarded to all children edit parts until one understand it, and then said child edit part is asked to perform the request.
     */
    @objid ("80932f5b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performRequest(Request req) {
        if (NavigationRequest.TYPE.equals(req.getType())) {
            final GmNodeModel gm = getModel();
            final MObject relatedEl = gm.getRelatedElement();
        
            IModelioNavigationService service = gm.getDiagram().getModelManager().getNavigationService();
            service.fireNavigate(relatedEl);
        
            getViewer().setSelection(new StructuredSelection(this));
        } else if (RequestConstants.REQ_OPEN.equals(req.getType())) {
            final GmNodeModel gm = getModel();
            final MObject relatedEl = gm.getRelatedElement();
        
            IActivationService service = gm.getDiagram().getModelManager().getActivationService();
            service.activateMObject(relatedEl);
        
        } else if (RequestConstants.REQ_DIRECT_EDIT.equals(req.getType())) {
            performDirectEditRequest(req);
        }
        
        super.performRequest(req);
        
    }

    /**
     * Called by {@link #performRequest(Request)} for {@link RequestConstants#REQ_DIRECT_EDIT} requests.
     * <p>
     * Default implementation the request forwards to all children edit parts until one understand it,
     * and then said child edit part is asked to perform the request.
     * @param req the direct edit request
     * @since 5.1.0
     */
    @objid ("3831d415-8cdf-4db7-958a-1683c6ff4d40")
    protected void performDirectEditRequest(Request req) {
        if (req instanceof LocationRequest) {
            // Give the request to the child where the request is located
            final Point reqLocation = ((LocationRequest) req).getLocation();
        
            for (Object childEditPartObj : getChildren()) {
        
                final GraphicalEditPart childEditPart = (GraphicalEditPart) childEditPartObj;
                if (childEditPart.understandsRequest(req) &&
                        containsAbsolutePoint(childEditPart, reqLocation)) {
                    childEditPart.performRequest(req);
                }
            }
        } else {
            // Give the request to the first child that understand it
            for (Object childEditPartObj : getChildren()) {
                final EditPart childEditPart = (EditPart) childEditPartObj;
                if (childEditPart.understandsRequest(req)) {
                    childEditPart.performRequest(req);
                    return;
                }
            }
        }
        
    }

    @objid ("80932f62-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propName = evt.getPropertyName();
        
        switch (propName) {
        case IGmObject.PROPERTY_LINK_SOURCE:
            refreshSourceConnections();
            break;
        case IGmObject.PROPERTY_LINK_TARGET:
            refreshTargetConnections();
            break;
        case IGmObject.PROPERTY_CHILDREN:
            refreshChildren();
            break;
        case IGmObject.PROPERTY_LAYOUTDATA:
            refreshVisuals();
            break;
        case IGmObject.PROPERTY_STYLE:
            refreshFromStyle(getFigure(), (IStyle) evt.getNewValue());
            break;
        }
        
    }

    @objid ("809591cb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void removeNotify() {
        // If this node is becoming invisible, delete all links to and from it.
        if (!getModel().isVisible()) {
            for (Object obj : getSourceConnections()) {
                ConnectionEditPart connection = (ConnectionEditPart) obj;
                ((IGmObject) connection.getModel()).delete();
            }
            for (Object obj : getTargetConnections()) {
                ConnectionEditPart connection = (ConnectionEditPart) obj;
                ((IGmObject) connection.getModel()).delete();
            }
        }
        super.removeNotify();
        
    }

    /**
     * Select a custom image for the element from Modelio's image library.
     * @param gmModel the selected gmModel
     */
    @objid ("2d6b58ef-8a05-422e-9e4a-0d837d912af1")
    public void selectCustomImage(final GmNodeModel gmModel) {
        URL itemurl = getCustomImageUrl();
        changeCustomImage(gmModel, itemurl);
        
    }

    /**
     * Changes the current {@link IDragTrackerProvider} used by this edit part.
     * @param value the new {@link IDragTrackerProvider} to be used by this edit part.
     */
    @objid ("80932f66-1dec-11e2-8cad-001ec947c8cc")
    public void setDragTrackerProvider(IDragTrackerProvider value) {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        this.dragTrackerProvider = value;
        
    }

    @objid ("80932f6a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setModel(final Object model) {
        super.setModel(model);
        
        // Set the initial representation mode
        GmNodeModel gmAbstractObject = (GmNodeModel) model;
        this.initialRepMode = gmAbstractObject.getRepresentationMode();
        if (this.initialRepMode == null) {
            throw new IllegalStateException("No initial representation mode on" + gmAbstractObject);
        }
        
    }

    @objid ("ed1a83b6-6745-467c-8462-f838feb49769")
    @Override
    public String toString() {
        Rectangle bounds = this.figure != null ? this.figure.getBounds() : null;
        
        if (isActive()) {
            return String.format("%s [model=%s, bounds=%s]", getClass().getSimpleName(), getModel(), bounds);
        } else {
            return String.format("inactive %s [model=%s]", getClass().getSimpleName(), getModel());
        }
        
    }

    /**
     * Hook called by {@link #switchRepresentationMode()} on the new EditPart, after the switch has completed. Called only when representation switch is needed.
     * <p>
     * Does nothing by default, subclasses may redefine if needed.
     */
    @objid ("9e000841-55a0-4144-81cc-0adf89165200")
    protected void afterSwitchRepresentationMode() {
        // nothing by default
    }

    /**
     * Launches a {@link FitToMinSizeCommand} after a {@link RepresentationMode} switch.
     * @param newEditPart the new edit part being created.
     */
    @objid ("809591ba-1dec-11e2-8cad-001ec947c8cc")
    protected void autoSizeNode(final EditPart newEditPart) {
        // Look for an edit part in the parent hierarchy that understands resize requests.
        final ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        req.setEditParts(newEditPart);
        req.setSizeDelta(new Dimension(-1, -1));
        
        EditPart editPart = newEditPart;
        while (editPart != null && !editPart.understandsRequest(req)) {
            editPart = editPart.getParent();
            req.setEditParts(newEditPart);
        }
        
        if (editPart != null) {
            final GraphicalEditPart graphicEditPart = (GraphicalEditPart) editPart;
        
            // Force layout so that child figures on Port container have valid bounds needed by
            // XYLayoutEditPolicy.getConstraintFor(ChangeBoundsRequest , GraphicalEditPart ) .
            graphicEditPart.refresh();
            graphicEditPart.getFigure().getUpdateManager().performValidation();
        
            // Run fit to content to the found edit part.
            new FitToMinSizeCommand(graphicEditPart).execute();
        }
        
    }

    /**
     * Hook called by {@link #switchRepresentationMode()} when representation switch is needed, before the switch is done.
     * <p>
     * Does nothing by default, subclasses may redefine if needed.
     */
    @objid ("2ba07703-45aa-4c10-ade5-af8532147881")
    protected void beforeSwitchRepresentationMode() {
        // nothing by default
    }

    @objid ("80932f6f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DelegatingDirectEditionEditPolicy());
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new DefaultDeleteNodeEditPolicy());
        installEditPolicy(ModelElementDropRequest.TYPE, new DefaultElementDropEditPolicy());
        
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ReadOnlyHoverFeedbackEditPolicy());
        
        // Edit policy that refreshes the graphic model from model changes.
        // Deletes the graphic model when the model element is deleted.
        installEditPolicy(DefaultRefreshFromModelEditPolicy.ROLE, new DefaultRefreshFromModelEditPolicy());
        
    }

    @objid ("80932f72-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        return null;
    }

    /**
     * Encapsulate layout edit policies into another EditPolicy.
     * <p>
     * May be redefined by sub classes.
     * @param layoutPolicy the layout edit policy.
     * @return the created policy.
     * @since 5.1.0
     */
    @objid ("0a83a2cc-9ded-428a-90bc-a85fb5e4e61c")
    protected EditPolicy createLayoutPolicyDecorator(EditPolicy layoutPolicy) {
        if (getModel() instanceof GmCompositeNode && layoutPolicy instanceof LayoutEditPolicy) {
            return new LayoutConnectionsCompositeNodeLayoutEditPolicyDecorator( (LayoutEditPolicy) layoutPolicy);
        } else {
            return layoutPolicy;
        }
        
    }

    /**
     * Encapsulate primary drag edit policies into another EditPolicy.
     * <p>
     * May be redefined by sub classes.
     * @param dragPolicy the layout edit policy.
     * @return the created policy.
     * @since 5.1.0
     */
    @objid ("c950131d-522f-4537-9445-f03fa5ebac11")
    protected EditPolicy createPrimaryDragPolicyDecorator(EditPolicy dragPolicy) {
        return dragPolicy;
    }

    @objid ("8095918f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<?> getModelChildren() {
        final GmNodeModel gmNodeModel = getModel();
        
        // Only visible composite nodes have children
        if (gmNodeModel.isVisible() && gmNodeModel instanceof GmCompositeNode) {
            // Filter visible children
            return ((GmCompositeNode) gmNodeModel).getVisibleChildren();
        } else {
            return Collections.emptyList();
        }
        
    }

    @objid ("80959195-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<IGmLink> getModelSourceConnections() {
        return getModel().getStartingLinks();
    }

    /**
     * Convenience method to retrieve the model style.
     * @return the model style.
     */
    @objid ("8095919b-1dec-11e2-8cad-001ec947c8cc")
    protected IStyle getModelStyle() {
        return getModel().getDisplayedStyle();
    }

    @objid ("809591a0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<IGmLink> getModelTargetConnections() {
        return getModel().getEndingLinks();
    }

    /**
     * Get the service used to produce connection anchors from ConnectionEditPart of Request.
     * <p>
     * Default implementation returns {@link RectangleNodeAnchorProvider}.
     * May be redefined to use another service.
     * @return the service used to produce connection anchors from ConnectionEditPart of Request.
     * @since 5.0.2
     */
    @objid ("7128cf30-d618-42f1-abb0-22527bec8ec3")
    protected INodeAnchorProvider getNodeAnchorProvider() {
        return RectangleNodeAnchorProvider.get();
    }

    /**
     * Tells whether this node is owned directly or indirectly by a {@link ConnectionEditPart} (a link).
     * @return <i>true</i> if this node is owned by a link else <i>false</i>.
     */
    @objid ("63c6c6d3-3101-4af6-91a9-e40e1958eda0")
    protected final boolean isOwnedByConnection() {
        EditPart p = getParent();
        while (p != null) {
            if (p instanceof ConnectionEditPart) {
                return true;
            } else {
                p = p.getParent();
            }
        }
        return false;
    }

    /**
     * Tells whether this edit part has a related model element and whether it is valid: non shell, non deleted.
     * @return true if this edit part is related to a valid living modele element.
     */
    @objid ("fb1df387-e1fa-42a7-a2af-931230c9c671")
    protected boolean isRelatedElementValid() {
        final GmNodeModel gmModel = getModel();
        if (gmModel == null) {
            return false;
        }
        
        MObject relatedElement = gmModel.getRelatedElement();
        return (relatedElement != null
                && !relatedElement.isDeleted()
                && !relatedElement.isShell());
        
    }

    /**
     * Tells whether the representation mode needs to be switched.
     * @return true if the representation mode needs to be switched.
     */
    @objid ("c7362413-5291-4fab-9135-558992283ff1")
    protected boolean needsRepresentationModeSwitch() {
        final EditPart parentEditPart = getParent();
        if (parentEditPart == null) {
            return false;
        }
        final GmNodeModel gmModel = getModel();
        
        final RepresentationMode askedMode = gmModel.getRepresentationMode();
        return (askedMode != this.initialRepMode);
    }

    /**
     * Refresh the figure from the given style. This implementation updates pen and brush properties if applicable. StyleKey are looked up by MetaKey.
     * <p>
     * Often called in {@link #createFigure()} and after a style change.
     * @param aFigure The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("809591a6-1dec-11e2-8cad-001ec947c8cc")
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        final GmNodeModel gmModel = getModel();
        
        // Set pen properties where applicable
        if (aFigure instanceof IPenOptionsSupport) {
            final IPenOptionsSupport pen = (IPenOptionsSupport) aFigure;
            if (gmModel.getStyleKey(MetaKey.FONT) != null) {
                pen.setTextFont(style.getFont(gmModel.getStyleKey(MetaKey.FONT)));
            }
            if (gmModel.getStyleKey(MetaKey.TEXTCOLOR) != null) {
                pen.setTextColor(style.getColor(gmModel.getStyleKey(MetaKey.TEXTCOLOR)));
            }
            if (gmModel.getStyleKey(MetaKey.LINECOLOR) != null) {
                pen.setLineColor(style.getColor(gmModel.getStyleKey(MetaKey.LINECOLOR)));
            }
            if (gmModel.getStyleKey(MetaKey.LINEWIDTH) != null) {
                pen.setLineWidth(style.getInteger(gmModel.getStyleKey(MetaKey.LINEWIDTH)));
            }
            if (gmModel.getStyleKey(MetaKey.LINEPATTERN) != null) {
                LinePattern linePattern = style.getProperty(gmModel.getStyleKey(MetaKey.LINEPATTERN));
                pen.setLinePattern(linePattern);
            }
        }
        
        // Set brush properties where applicable
        if (aFigure instanceof IBrushOptionsSupport) {
            final IBrushOptionsSupport brush = (IBrushOptionsSupport) aFigure;
        
            if (gmModel.getStyleKey(MetaKey.FILLCOLOR) != null) {
                brush.setFillColor(style.getColor(gmModel.getStyleKey(MetaKey.FILLCOLOR)));
            }
        
            if (gmModel.getStyleKey(MetaKey.FILLMODE) != null) {
                switch ((FillMode) style.getProperty(gmModel.getStyleKey(MetaKey.FILLMODE))) {
                case SOLID:
                    brush.setUseGradient(false);
                    break;
                case TRANSPARENT:
                    brush.setFillColor(null);
                    break;
                case GRADIENT:
                    brush.setUseGradient(true);
                    break;
                default:
                    break;
                }
            }
        }
        
    }

    /**
     * Implementation of {@link #isSelectable()} that makes the node selectable only if one of its parent node is already selected.
     * <p>
     * To be called from {@link #isSelectable()}.
     * @return true if the parent node or this node is already selected else false.
     */
    @objid ("ed497043-f62d-4b0d-b68a-1324bf1dab76")
    protected final boolean selectableOnlyWithParent() {
        // Already selected, return true
        if (getViewer().getSelectedEditParts().contains(this)) {
            return true;
        }
        
        // Allow selection only if the composition parent was already selected
        EditPart parent = getParent();
        while (parent != null) {
            if (parent.isSelectable()) {
                return (parent.getSelected() != EditPart.SELECTED_NONE);
            }
            parent = parent.getParent();
        }
        return false;
    }

    /**
     * This convenience method tests if the representation must be switched from/to image stereotype mode and does the switch if needed.
     * <p>
     * It switches the representation by removing and adding again the gm element from its parent.<br>
     * Doing this makes the EditPart killing itself and call the {@link StandardEditPartFactory}.
     * @return true if the representation was swapped, false if it didn't change.
     */
    @objid ("809591ad-1dec-11e2-8cad-001ec947c8cc")
    protected final boolean switchRepresentationMode() {
        if (!needsRepresentationModeSwitch()) {
            // Fast exit
            return false;
        }
        
        Command layoutConnectionsCommand = LayoutChildrenNodeConnectionsHelper.forRequest(null)
        .addEditPart(this)
        .createCommands(new CompoundCommand());
        
        beforeSwitchRepresentationMode();
        
        final EditPart parentEditPart = getParent();
        final GmNodeModel gmModel = getModel();
        final GmCompositeNode parentNode = gmModel.getParentNode();
        final GmLink parentLink = gmModel.getParentLink();
        EditPart newEditPart = null;
        if (parentNode != null) {
            if (parentEditPart instanceof AbstractNodeEditPart) {
                // This will "delete" the current edit part.
                ((AbstractNodeEditPart) parentEditPart).removeChild(this);
                // This will invoke the ModelioEditPartFactory that will create another edit part.
                parentEditPart.refresh();
            } else {
                // Keep the index of the child avoids problems with positional read
                int index = parentNode.getChildIndex(gmModel);
                // This will "delete" the current edit part (AND modify the GM model).
                parentNode.removeChild(gmModel);
                // This will restore the GM model to its original state and invoke the ModelioEditPartFactory that will create another edit part.
                parentNode.addChild(gmModel, index);
            }
        
            newEditPart = (EditPart) parentEditPart.getViewer()
                    .getEditPartRegistry()
                    .get(gmModel);
        
            autoSizeNode(newEditPart);
        
        } else if (parentLink != null) {
            final IGmLocator constraint = parentLink.getLayoutContraint(gmModel);
        
            // This will "delete" the current edit part.
            parentLink.removeExtension(gmModel);
        
            // This will invoke the ModelioEditPartFactory that will
            // create another edit part.
            parentLink.addExtension(gmModel, gmModel.getRoleInComposition(), constraint);
        
            newEditPart = (EditPart) parentEditPart.getViewer()
                    .getEditPartRegistry()
                    .get(gmModel);
        }
        
        // Custom image mode selected
        if (gmModel.getRepresentationMode() == RepresentationMode.USER_IMAGE) {
            MObject obElement = gmModel.getRelatedElement();
            if (obElement instanceof ModelElement && !((ModelElement) obElement).isTagged("ModelerModule", ".*", "userDiagramImage")) {
                selectCustomImage(gmModel);
            }
            autoSizeNode(newEditPart);
        }
        
        if (newEditPart instanceof AbstractNodeEditPart) {
            ((AbstractNodeEditPart) newEditPart).afterSwitchRepresentationMode();
        }
        
        // since 5.1.0: layout connections
        if (layoutConnectionsCommand!= null && layoutConnectionsCommand.canExecute())
            layoutConnectionsCommand.execute();
        return true;
    }

    @objid ("6bcd4cb1-cd1a-4bd4-a405-c87899da69e6")
    @Override
    protected void unregisterVisuals() {
        if (getViewer() != null) {
            super.unregisterVisuals();
        }
        
    }

    /**
     * Tells whether the figure of the given edit part contains the given point.
     * @param editPart A graphic edit part
     * @param aPoint a point in absolute coordinates
     * @return <i>true</i> if the edit part figure contains the point, else <i>false</i>
     */
    @objid ("809591c0-1dec-11e2-8cad-001ec947c8cc")
    private boolean containsAbsolutePoint(GraphicalEditPart editPart, Point aPoint) {
        final IFigure fig = editPart.getFigure();
        final Point p = aPoint.getCopy();
        fig.translateToRelative(p);
        return fig.containsPoint(p);
    }

}
