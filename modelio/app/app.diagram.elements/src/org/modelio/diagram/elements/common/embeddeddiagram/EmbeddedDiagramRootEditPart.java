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
package org.modelio.diagram.elements.common.embeddeddiagram;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.ViewportLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.image.ImageEditPart;
import org.modelio.diagram.elements.common.root.ScalableFreeformRootEditPart2;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.factory.DelegatingEditPartFactory;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultDeleteNodeEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.policies.ReadOnlyHoverFeedbackEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.requests.NavigationRequest;
import org.modelio.diagram.elements.factories.StandardEditPartFactory;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("5aa80088-dd25-4436-9ded-e025cf30d546")
public class EmbeddedDiagramRootEditPart extends ScalableFreeformRootEditPart2 implements PropertyChangeListener {
    @objid ("1ffe1e05-5214-406e-b18f-23fce894d272")
    private final PropertyChangeListener diagramListener = evt -> refreshChildren();

    @objid ("c150cf2e-7dc0-4991-8a02-ec934a800a40")
    private final EditPartViewer embeddedViewer;

    @objid ("166ffc8f-2c7d-4e4e-a83d-589290b511c0")
    private GmEmbeddedDiagram model;

    @objid ("1e4a518b-f2fe-40a8-9da9-8a9694d28064")
    public  EmbeddedDiagramRootEditPart(EditPart parent, Object model) {
        super();
        
        setModel(model);
        setParent(parent);
        this.embeddedViewer = new EmbeddedEditPartViewer(this);
        
        // Ensure max zoom level is 1.0
        getLocalZoomManager().setZoomLevels(new double[] { 0.1, 0.2, 0.3, 0.5, 0.7, 0.8, 0.9, 1.0 });
        
    }

    @objid ("9a3380fe-7046-421e-a205-7acd113c0a3b")
    @Override
    public void activate() {
        super.activate();
        
        // Load the viewed diagram
        final IGmDiagram viewedDiagram = getModel().getViewedDiagramModel(true);
        if (viewedDiagram != null) {
            // Ensure the listener is registered once and only once.
            viewedDiagram.removePropertyChangeListener(this.diagramListener);
            viewedDiagram.addPropertyChangeListener(this.diagramListener);
        
            // Make sure embedded diagram register their factories as "secondary"
            EditPartFactory editPartFactory = getViewer().getEditPartFactory();
            if (editPartFactory instanceof StandardEditPartFactory) {
                StandardEditPartFactory currentFactory = (StandardEditPartFactory) editPartFactory;
                DelegatingEditPartFactory secondaryFactories = currentFactory.getSecondaryFactories();
        
                String embeddedFactoryIdentifier = viewedDiagram.getFactoryIdentifier();
                secondaryFactories.registerFactory(DiagramFactoryRegistry.getInstance().getEditPartFactory(embeddedFactoryIdentifier));
        
                for (String embeddedFactoryExtension : DiagramFactoryRegistry.getInstance().getExtensions(embeddedFactoryIdentifier)) {
                    secondaryFactories.registerFactory(DiagramFactoryRegistry.getInstance().getEditPartFactory(embeddedFactoryExtension));
                }
            }
        
            setContents(editPartFactory.createEditPart(this, viewedDiagram));
        } else {
            // Loading failed, probably diagram cycle
            // Display an image or icon instead
            ImageEditPart editpart = new ImageEditPart();
            editpart.setModel(getModel());
            editpart.setImageProvider(this::getDiagramLoadFailedImage);
            setContents(editpart);
            getContentPane().setLayoutManager(new StackLayout());
        }
        
        getModel().addPropertyChangeListener(this);
        
    }

    @objid ("a9eda506-5942-4c6d-b08b-065fb6da63b8")
    @Override
    public void deactivate() {
        getModel().removePropertyChangeListener(this);
        
        final IGmDiagram viewedDiagram = getModel().getViewedDiagramModel(false);
        if (viewedDiagram != null) {
            viewedDiagram.removePropertyChangeListener(this.diagramListener);
            viewedDiagram.dispose();
        }
        
        super.deactivate();
        
    }

    @objid ("bf7730e0-0a92-4a18-9983-43ce582a94b7")
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
     * Get the zoom manager for the embedded diagram.
     * @return the local zoom manager.
     */
    @objid ("e5c93b60-67c1-4f11-a0f3-46686b3a99a9")
    public EmbeddedZoomManager getLocalZoomManager() {
        return (EmbeddedZoomManager) super.getZoomManager();
    }

    /**
     * Embedded diagram edit part DO have a real model.
     */
    @objid ("8277ca94-f52f-42b0-86b8-ce2544ac5143")
    @Override
    public GmEmbeddedDiagram getModel() {
        return this.model;
    }

    /**
     * Get the root edit part of the parent EditPart.
     * @return the owner RootEditPart.
     */
    @objid ("2654b85b-27cf-441c-8d34-b7520aca414e")
    public RootEditPart getParentRoot() {
        EditPart parent = getParent();
        if (parent == null) {
            return null;
        }
        return parent.getRoot();
    }

    @objid ("c906ce80-3a7f-4f51-b27f-c878f9d90e6d")
    @Override
    public EditPartViewer getViewer() {
        return this.embeddedViewer;
    }

    /**
     * Get the zoom manager for the embedded diagram.
     * @return the local zoom manager.
     */
    @objid ("ebeb7434-8418-48a6-b1eb-8844c6850bec")
    @Override
    public ZoomManager getZoomManager() {
        return getLocalZoomManager();
    }

    @objid ("70b286bb-19bf-4044-a090-02484d85db51")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * Overridden to add a specific behavior on {@link RequestConstants#REQ_DIRECT_EDIT DIRECT_EDIT} request: the request if forwarded to all children edit parts until one understand it, and then said child edit part is asked to perform the request.
     */
    @objid ("c7a61fd5-eb85-4e6b-9b9d-2786c63f1ae6")
    @Override
    public void performRequest(Request req) {
        if (NavigationRequest.TYPE == req.getType()) {
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
        
        }
        super.performRequest(req);
        
    }

    @objid ("76748a0e-ecd0-4fde-8d8b-8bb430e7235d")
    @Override
    public void propertyChange(PropertyChangeEvent ev) {
        if (ev.getPropertyName().equals(IGmObject.PROPERTY_CHILDREN)) {
            refreshChildren();
        }
        
        if (GmEmbeddedDiagram.PROP_INNER_DIAGRAM.equals(ev.getPropertyName())) {
            final IGmDiagram oldViewedDiagram = (IGmDiagram) ev.getOldValue();
            final IGmDiagram newViewedDiagram = (IGmDiagram) ev.getNewValue();
            if (oldViewedDiagram != null) {
                oldViewedDiagram.removePropertyChangeListener(this.diagramListener);
            }
            if (newViewedDiagram != null) {
                newViewedDiagram.addPropertyChangeListener(this.diagramListener);
            }
        }
        
    }

    @objid ("5f3c2f06-8db5-4e45-a65c-4acd816fc5a7")
    @Override
    public void setModel(Object model) {
        this.model = (GmEmbeddedDiagram) model;
        super.setModel(model);
        
    }

    @objid ("44bacba2-592f-41d9-97d5-c646984524ea")
    @Override
    public void setViewer(EditPartViewer newViewer) {
        // The viewer is set on construction
        throw new UnsupportedOperationException();
        
    }

    /**
     * No background layer
     */
    @objid ("1fd17f40-d7d1-447e-a867-9a48898a13b4")
    @Override
    protected FreeformLayer createBackgroundLayer() {
        return null;
    }

    @objid ("2df152b9-5e5c-411d-9716-95a27e0a0e03")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new DefaultDeleteNodeEditPolicy());
        installEditPolicy(ModelElementDropRequest.TYPE, new DefaultElementDropEditPolicy());
        
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ReadOnlyHoverFeedbackEditPolicy());
        
        // installEditPolicy(TranslateChildrenOnResizeEditPolicy.class, new TranslateChildrenOnResizeEditPolicy());
        
    }

    @objid ("c5dbb896-6350-4ae7-b538-7c2738fce6fa")
    @Override
    protected void createLayers(LayeredPane layeredPane) {
        super.createLayers(layeredPane);
        
        // Remove the guides layer
        layeredPane.remove(layeredPane.getLayer(LayerConstants.GUIDE_LAYER));
        
    }

    /**
     * Creates a layered pane and the layers that should be scaled.
     * @return a new freeform layered pane containing the scalable layers
     */
    @objid ("c790bb2d-745e-488d-a220-28ed7106edb0")
    @Override
    protected ScalableFreeformLayeredPane createScaledLayers() {
        ScalableFreeformLayeredPane layers = super.createScaledLayers();
        layers.remove(layers.getLayer(LayerConstants.GRID_LAYER));
        
        // Auto fit to content
        if (false) {
        layers.addLayoutListener(new LayoutListener.Stub() {
            protected boolean scheduled;
        
            @Override
            public void postLayout(IFigure container) {
                if (!this.scheduled) {
                    this.scheduled = true;
                    layers.getUpdateManager().runWithUpdate(() -> {
                        this.scheduled = false;
                        fitToContent();
                    });
                }
            }
        });
        }
        return layers;
    }

    @objid ("d4ef4098-1f4d-4d5f-9ef4-b4ad1e46712c")
    protected void fitToContent() {
        if (isActive()) {
            EmbeddedZoomManager zm = getLocalZoomManager();
            zm.setCenterView(getModel().isViewToCenter());
            zm.fitToContent();
        }
        
    }

    @objid ("64ec8cec-e8a9-46bf-801f-b33cd67a29fc")
    @Override
    protected FreeformViewport createViewport() {
        EmbeddedDiagramViewport viewport = new EmbeddedDiagramViewport();
        viewport.setLayoutManager(new EmbeddedDiagramViewportLayout());
        
        // Auto fit to content
        viewport.addLayoutListener(new LayoutListener.Stub() {
            @Override
            public void postLayout(IFigure container) {
                fitToContent();
            }
        });
        return viewport;
    }

    /**
     * Responsible of creating a {@link ZoomManager} to be used by this {@link ScalableRootEditPart}.
     * @return A new {@link ZoomManager} bound to the given {@link ScalableFigure} and {@link Viewport}.
     * @since 3.10
     */
    @objid ("7bc7ef3b-4e9c-4966-bdd4-0835eebe013f")
    @Override
    protected ZoomManager createZoomManager(ScalableFigure scalableFigure, Viewport viewport) {
        return new EmbeddedZoomManager(scalableFigure, viewport);
    }

    @objid ("9918dcec-33cc-483f-96b4-e630ed1e2439")
    protected Image getDiagramLoadFailedImage() {
        AbstractDiagram element = getModel().getRelatedElement();
        if (element != null) {
            Image image = ElementImageService.getImage(element);
            if (image == null) {
                image = ElementImageService.getIcon(element);
            }
            return image;
        }
        Image image = MetamodelImageService.getImage(getModel().getRelatedMClass());
        if (image == null) {
            image = MetamodelImageService.getIcon(getModel().getRelatedMClass());
        }
        return image;
    }

    @objid ("ff57c6dc-1882-42f2-b058-0a074d4dd87b")
    @Override
    protected List<?> getModelChildren() {
        GmEmbeddedDiagram gmNodeModel = getModel();
        
        // Only visible composite nodes have children
        if (gmNodeModel.isVisible()) {
            // Filter visible children
            return ((GmCompositeNode) gmNodeModel).getVisibleChildren();
        } else {
            return Collections.emptyList();
        }
        
    }

    @objid ("217aac8d-ddb0-47f1-b985-ba0f597ff67c")
    @Override
    protected void refreshGridLayer() {
        
    }

    /**
     * Copy of package private org.eclipse.draw2d.FreeformViewport.FreeformViewportLayout to redefine {@link #calculateMinimumSize(IFigure, int, int)}.
     * 
     * @author cma
     * @since 3.7
     */
    @objid ("a3c52b52-f571-4ecc-9525-934f77ed4cfd")
    protected class EmbeddedDiagramViewportLayout extends ViewportLayout {
        @objid ("388bf837-ebd9-4875-8c69-5b5ad905c444")
        @Override
        protected Dimension calculatePreferredSize(IFigure parent, int wHint, int hHint) {
            IFigure contents = ((Viewport) parent).getContents();
            contents.validate();
            // wHint = Math.max(0, wHint);
            // hHint = Math.max(0, hHint);
            return ((FreeformFigure) contents).getFreeformExtent()
                    .getCopy()
                    .scale(1 / getZoomManager().getZoom())
                    .expand(parent.getInsets())
                    // .union(0, 0)
                    // .union(wHint - 1, hHint - 1)
                    .getSize();
            
        }

        @objid ("0a04fa34-c837-4426-9ec4-24a81f1612bd")
        @Override
        protected Dimension calculateMinimumSize(IFigure parent, int wHint, int hHint) {
            IFigure contents = ((Viewport) parent).getContents();
            contents.validate();
            return ((FreeformFigure) contents).getFreeformExtent()
                    .getCopy()
                    .scale(1 / getZoomManager().getZoom())
                    .expand(parent.getInsets())
                    .getSize();
            
        }

        @objid ("d8b6b2ea-147f-4f66-8c58-d12fa48c268e")
        @Override
        protected boolean isSensitiveHorizontally(IFigure parent) {
            return true;
        }

        @objid ("1572a9f7-1641-4f18-8642-6f86a76772c1")
        @Override
        protected boolean isSensitiveVertically(IFigure parent) {
            return true;
        }

        @objid ("3a2da749-9471-422e-a610-d0049df01af9")
        @Override
        public void layout(IFigure parent) {
            // Do nothing, contents updates itself.
        }

    }

}
