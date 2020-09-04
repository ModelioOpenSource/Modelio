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

package org.modelio.diagram.elements.drawings.core.node;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.jface.viewers.StructuredSelection;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.diagram.elements.core.commands.FitToMinSizeCommand;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.DefaultDragTrackerProvider;
import org.modelio.diagram.elements.core.node.IDragTrackerProvider;
import org.modelio.diagram.elements.core.node.RectangleNodeAnchorProvider;
import org.modelio.diagram.elements.core.policies.DefaultDeleteNodeEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.policies.DelegatingDirectEditionEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.requests.NavigationRequest;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.FillMode;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Edit parts for {@link GmNodeDrawing nodes} representing no model element.
 */
@objid ("860c5822-d082-45c7-9e78-748e8dc0b980")
public abstract class NodeDrawingEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, IAnchorModelProvider {
    @objid ("eb185de6-2146-407e-aac8-71bfd207f880")
    private IDragTrackerProvider dragTrackerProvider;

    /**
     * Constructor.
     */
    @objid ("d454239c-f2cf-47a6-a654-326ceb099010")
    public NodeDrawingEditPart() {
        super();
        this.dragTrackerProvider = new DefaultDragTrackerProvider(this);
    }

    @objid ("5c381a89-6342-4d4d-94dc-6bbf484d4b53")
    @Override
    public void activate() {
        super.activate();
        getModel().addPropertyChangeListener(this);
    }

    /**
     * Create a serializable anchor model from the given anchor.
     * 
     * @param anchor a figure anchor
     * @return an anchor model.
     */
    @objid ("c9a3d66b-e1f4-4a09-bb19-71fa1946462e")
    @Override
    public Object createAnchorModel(final ConnectionAnchor anchor) {
        return RectangleNodeAnchorProvider.get().createAnchorModel(anchor);
    }

    @objid ("3893a36c-8df1-4e48-84af-eff6f9a1c235")
    @Override
    public void deactivate() {
        super.deactivate();
        getModel().removePropertyChangeListener(this);
    }

    /**
     * Returns an object which is an instance of the given class associated with this object. Returns <code>null</code>
     * if no such object can be found.
     * <p>
     * Extends {@link AbstractGraphicalEditPart#getAdapter(Class)} to support {@link IGmObject},
     * {@link GmDrawing} and their subclasses.
     * @see IAdaptable#getAdapter(Class)
     * 
     * @param adapter the adapter class to look up
     * @return a object castable to the given class, or <code>null</code> if this object does not have an adapter for
     * the given class
     */
    @objid ("05ccdb19-32a2-4209-9a52-ecdc90aea8ec")
    @Override
    public Object getAdapter(Class adapter) {
        final GmNodeDrawing model = getModel();
        
        // Support IGmObject, GmModel and its subclasses
        if (adapter.isInstance(model)) {
            return model;
        }
        
        // Supports MRef and MObject
        if (MRef.class.isAssignableFrom(adapter) ||
                MObject.class.isAssignableFrom(adapter)) {
            
            MRef ref = model.getHyperLink();
            if (ref != null) {
                if (adapter.isInstance(ref)) {
                    return ref;
                }
                
                MObject obj = model.getDiagram().getModelManager().getModelServices().findByRef(ref);
                if (obj != null && adapter.isInstance(obj)) {
                    return obj;
                }
            }
        }
        return super.getAdapter(adapter);
    }

    @objid ("52844b67-5f23-40b4-adbe-d2ed5d0a381e")
    @Override
    public GmNodeDrawing getModel() {
        return (GmNodeDrawing) super.getModel();
    }

    @objid ("8714fcf4-6f27-4b0b-bd64-375e45195cf9")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return RectangleNodeAnchorProvider.get().getSourceConnectionAnchor(this, connection);
    }

    @objid ("987e69b4-12cb-45af-9e5c-e916dbb6390b")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return RectangleNodeAnchorProvider.get().getSourceConnectionAnchor(this, request);
    }

    @objid ("c559d767-15e4-4a10-930b-b460ff9a0a09")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connectionPart) {
        return RectangleNodeAnchorProvider.get().getTargetConnectionAnchor(this, connectionPart);
    }

    @objid ("a545a5f5-2808-4e61-ba41-33a5ad65043a")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return RectangleNodeAnchorProvider.get().getTargetConnectionAnchor(this, request);
    }

    /**
     * To be redefined if the node should not be resizeable.
     * 
     * @return <code>true</code> if the figure should be resizeable else <code>false</code>.
     */
    @objid ("0f435f58-46af-4b9c-b75a-4437e2c3b03e")
    public boolean isResizeable() {
        return true;
    }

    /**
     * Overridden to add a specific behavior on:<ul>
     * <li> {@link RequestConstants#REQ_DIRECT_EDIT DIRECT_EDIT} request: the
     * request if forwarded to all children edit parts until one understand it, and then said child edit part is asked
     * to perform the request.
     * <li> {@link NavigationRequest#TYPE} : navigate to the hyper-link if defined.
     * </ul>
     */
    @objid ("e8b8eff0-2bcd-491f-a1b6-1dabcee3f9c5")
    @Override
    public void performRequest(Request req) {
        if (RequestConstants.REQ_DIRECT_EDIT.equals(req.getType())) {
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
        } else if (NavigationRequest.TYPE == req.getType()) {
            final IGmDrawing gm = getModel();
            final MRef ref = gm.getHyperLink();
            if (ref != null) {
                final MObject relatedEl = gm.getDiagram().getModelManager().resolveRef(ref);
        
                if (relatedEl != null) {
                    IModelioNavigationService service = gm.getDiagram().getModelManager().getNavigationService();
                    service.fireNavigate(relatedEl);
        
                    getViewer().setSelection(new StructuredSelection(this));
                }
            }
        }
        super.performRequest(req);
    }

    @objid ("bdbdd1c5-02bb-4afc-b369-82bfcdc3e736")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName())
        {
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
        default:
            break;
        }
    }

    @objid ("9b32af8c-ec82-4ad8-91de-9b38ef662395")
    @Override
    public void removeNotify() {
        // If this node is becoming invisible, delete all links to and from it.
        /*
        if (!((GmNodeDrawing) getModel()).isVisible()) {
            for (Object obj : getSourceConnections()) {
                ConnectionEditPart connection = (ConnectionEditPart) obj;
                ((IGmObject) connection.getModel()).delete();
            }
            for (Object obj : getTargetConnections()) {
                ConnectionEditPart connection = (ConnectionEditPart) obj;
                ((IGmObject) connection.getModel()).delete();
            }
        }*/
        super.removeNotify();
    }

    /**
     * Changes the current {@link IDragTrackerProvider} used by this edit part.
     * 
     * @param value the new {@link IDragTrackerProvider} to be used by this edit part.
     */
    @objid ("63e477d3-574c-417e-b3e2-16a2fdbb4bb0")
    public void setDragTrackerProvider(IDragTrackerProvider value) {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        this.dragTrackerProvider = value;
    }

    @objid ("98a9df6e-bef2-409e-b737-dd858193bd4c")
    @Override
    protected void createEditPolicies() {
        // installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new GraphicBoxSelectionPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DelegatingDirectEditionEditPolicy());
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new DefaultDeleteNodeEditPolicy());
        installEditPolicy(ModelElementDropRequest.TYPE, new DefaultElementDropEditPolicy());
    }

    @objid ("9b362757-b131-44c8-8aa9-ba8cb83d06a7")
    @Override
    protected IFigure createFigure() {
        return null;
    }

    @objid ("cdb37655-9516-429e-82ef-11beaefe8efc")
    @Override
    protected List<?> getModelChildren() {
        return Collections.emptyList();
    }

    @objid ("3c4a24c3-fbbf-4c9b-9507-fbab23fdd923")
    @Override
    protected List<IGmDrawingLink> getModelSourceConnections() {
        return getModel().getStartingDrawingLinks();
    }

    /**
     * Convenience method to retrieve the model style.
     * 
     * @return the model style.
     */
    @objid ("3b360802-5569-4c7c-8557-885271bec8e8")
    protected IStyle getModelStyle() {
        return ((IGmObject) getModel()).getDisplayedStyle();
    }

    @objid ("02d3c09a-30e1-45b2-ae44-18a45479a51c")
    @Override
    protected List<IGmDrawingLink> getModelTargetConnections() {
        return getModel().getEndingDrawingLinks();
    }

    /**
     * Refresh the figure from the given style. This implementation updates pen and brush properties if applicable.
     * StyleKey are looked up by MetaKey.
     * <p>
     * Often called in {@link #createFigure()} and after a style change.
     * 
     * @param aFigure The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("f0345440-5dc4-43ec-ba89-8f9245311313")
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        final GmDrawing gmModel = getModel();
        
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
                final FillMode fillMode = (FillMode) style.getProperty(gmModel.getStyleKey(MetaKey.FILLMODE));
                switch (fillMode) {
                case GRADIENT:
                    brush.setUseGradient(true);
                    break;
                case SOLID:
                    brush.setUseGradient(false);
                    break;
                case TRANSPARENT:
                    brush.setFillColor(null);
                    break;
                default:
                    DiagramElements.LOG.warning(new IllegalArgumentException(fillMode.toString()));
                    break;
                }
            }
            
            if (gmModel.getStyleKey(MetaKey.FILLALPHA) != null) {
                brush.setFillAlpha(style.getInteger(gmModel.getStyleKey(MetaKey.FILLALPHA)));
            }
            
        }
    }

    @objid ("cf5cf4e7-d23d-4d46-9ce7-0e1a33d45c33")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        final IFigure fig = getFigure();
        final GmNodeDrawing model = getModel();
        
        fig.getParent().setConstraint(fig, model.getLayoutData());
    }

    @objid ("21a39b32-0d85-4f96-9873-ec12624b9d51")
    private void autoSizeNode(final EditPart newEditPart) {
        // Look for an edit part in the parent hierarchy that understands resize requests.
        final ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_RESIZE);
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
     * Tells whether the figure of the given edit part contains the given point.
     * 
     * @param editPart A graphic edit part
     * @param aPoint a point in absolute coordinates
     * @return <i>true</i> if the edit part figure contains the point, else <i>false</i>
     */
    @objid ("10eb50ed-f64b-4069-ad19-94259cf0ea53")
    private boolean containsAbsolutePoint(GraphicalEditPart editPart, Point aPoint) {
        final IFigure fig = editPart.getFigure();
        final Point p = aPoint.getCopy();
        fig.translateToRelative(p);
        return fig.containsPoint(p);
    }

    @objid ("1e343e37-8be2-4b8e-afaf-4c6f7e758d85")
    @Override
    public DragTracker getDragTracker(Request request) {
        return this.dragTrackerProvider.getDragTracker(request);
    }

}
