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
package org.modelio.diagram.elements.common.abstractdiagram;

import java.beans.PropertyChangeEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gef.tools.MarqueeDragTracker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidConstants;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidFinishCreationEditPolicy;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IPostLoadAction;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.LayoutConnectionsConstrainedLayoutEditPolicyDecorator;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.layer.DrawingLayerEditPart;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.vbasic.files.FileUtils;

/**
 * Default Edit part for GmDiagram.
 */
@objid ("7e038885-1dec-11e2-8cad-001ec947c8cc")
public class AbstractDiagramEditPart extends AbstractNodeEditPart {
    /**
     * Role used to name policy that delegates to the drawing layer
     */
    @objid ("0d5a1d42-40b4-4062-a313-07c924a95a05")
    protected static final String DRAWINGLAYER_ROLE = "DRAWINGLAYER_ROLE";

    /**
     * ID of the layer pane where drawing layers are put.
     */
    @objid ("9eeff616-9943-42e1-8193-1a4fa86532d1")
    public static final String DRAWING_LAYER = "LAYER_PANE_DRAWING";

    /**
     * Fill image transparency property key.
     */
    @objid ("90df2c9c-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_FILL_ALPHA = "AbstractDiagramEditPart.FillAlpha";

    /**
     * Diagram fill color property key.
     */
    @objid ("90df2c90-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_FILL_COLOR = "AbstractDiagramEditPart.FillColor";

    /**
     * Fill image property key.
     * <p>
     * This may be a file path or an URL. Relative file paths may be supported if {@link #refreshFromStyle(IFigure, IStyle)} uses {@link #resolvePath(String)}.
     */
    @objid ("90df2c96-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_FILL_IMAGE = "AbstractDiagramEditPart.FillImage";

    /**
     * Fill tile size property key.
     */
    @objid ("90df2ca2-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_FILL_TILE_SIZE = "AbstractDiagramEditPart.FillTileSize";

    /**
     * Grid transparency property key.
     */
    @objid ("90df2c8a-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_GRID_ALPHA = "AbstractDiagramEditPart.GridAlpha";

    /**
     * Grid color property key.
     */
    @objid ("90df2c84-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_GRID_COLOR = "AbstractDiagramEditPart.GridColor";

    /**
     * Default constructor.
     */
    @objid ("7e05eaeb-1dec-11e2-8cad-001ec947c8cc")
    public  AbstractDiagramEditPart() {
        // Nothing to do yet.
    }

    /**
     * Run post loading actions once activated.
     * <p>
     * {@inheritDoc}
     * @since redefined since 3.7
     */
    @objid ("c6b03019-287b-428a-8155-267aac9c0e5c")
    @Override
    public void activate() {
        super.activate();
        runPostLoadActions();
        
    }

    /**
     * In order to provide snap to grid facility the AbstractEditPart must be adaptable to a SnapToHelper. Here we adapt to a SnapToGrid only if the style defines SNAPTOGRID = true
     */
    @objid ("7e05eaf1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getAdapter(Class type) {
        if (type == SnapToHelper.class) {
            return new SnapEditPartAdapter(this).getSnapToHelper();
        }
        return super.getAdapter(type);
    }

    /**
     * Diagrams are usually not selected, in the case it happens return the rectangle selection tool.
     */
    @objid ("2d3b2d14-b845-408c-a6eb-62997a2c36a7")
    @Override
    public DragTracker getDragTracker(Request request) {
        return new MarqueeDragTracker(); 
    }

    @objid ("7e05eaf8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        if (request instanceof ReconnectRequest) {
            final Point p = ((ReconnectRequest) request).getLocation();
            return new XYAnchor(p);
        } else if (request instanceof DropRequest) {
            final Point p = ((DropRequest) request).getLocation();
            return new XYAnchor(p);
        }
        throw new IllegalArgumentException(request + " not handled.");
        
    }

    /**
     * Embedded diagrams are not selectable.
     * <p>
     * {@inheritDoc}
     */
    @objid ("4187275a-5b1c-4476-84f6-b948135a4e44")
    @Override
    public boolean isSelectable() {
        IGmDiagram diag = (IGmDiagram) getModel();
        if (diag != null && diag.getDiagramOwner() != null) {
            return false;
        }
        return super.isSelectable();
    }

    @objid ("67085f02-7375-4a44-8551-22dd1c7c6e22")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case GmAbstractDiagram.PROP_UIDATA_VERSION:
            updatePreviewData();
            break;
        case GmAbstractDiagram.PROP_DIAGRAM_LOAD_END:
            // When the diagram reloads, make sure there are no pending load actions
            if (isActive()) {
                runPostLoadActions();
            }
        }
        
        super.propertyChange(evt);
        
    }

    /**
     * Redefined to add {@link DrawingLayerEditPart} layers in the drawing layer pane.
     * <p/>
     * Subclasses willing to redefine this method must redefine {@link #doAddChildVisual(EditPart, int)} instead, so that they won't be annoyed by drawing layers.
     */
    @objid ("80a29b4a-33d5-4b9f-813d-bb17f88ff7d5")
    @Override
    protected final void addChildVisual(EditPart childEditPart, int index) {
        if (childEditPart instanceof DrawingLayerEditPart) {
            // Add all drawing layers to the drawing layer pane, except the background layer
            // that stays in this layer.
            if (index != 0) {
                // childEditPart is a Foreground layer
                getDrawingLayerPane().add(((DrawingLayerEditPart) childEditPart).getFigure());
            } else {
                // index==0: childEditPart is the background drawing layer
                super.addChildVisual(childEditPart, index);
            }
        } else {
            doAddChildVisual(childEditPart, index);
        }
        
    }

    @objid ("7e05eb0c-1dec-11e2-8cad-001ec947c8cc")
    protected static final PrecisionDimension convertMmToInch(PrecisionDimension d) {
        if (d == null) {
            return null;
        }
        final double factor = 25.4; // one inch is 25,4 mm
        return new PrecisionDimension(d.preciseWidth() / factor, d.preciseHeight() / factor);
    }

    /**
     * Creates the layer pane where drawing layers are put.
     * <p>
     * The layer pane is put on top of the {@link LayerConstants#PRINTABLE_LAYERS} layer.
     * @return the drawings layer pane.
     */
    @objid ("a7c40bc5-f7d4-42c6-b604-273fc07d900b")
    protected LayeredPane createDrawingLayerPane() {
        // Ensure the diagram layers already exist
        getFigure();
        
        // Create the drawing layer on top of the diagram layer.
        FreeformLayeredPane drawLayerPane = new FreeformLayeredPane();
        LayeredPane pane = (LayeredPane) getLayer(LayerConstants.PRINTABLE_LAYERS);
        pane.add(drawLayerPane, DRAWING_LAYER);
        return drawLayerPane;
    }

    @objid ("7e05eb01-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Override the default drop policy with a specific one that can also handle links.
        installEditPolicy(ModelElementDropRequest.TYPE, new DiagramElementDropEditPolicy());
        
        // Remove the default DIRECT_EDIT policy: we don't want the diagram
        // background to delegate direct edit requests.
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
        
        // For lollipops (provided interface, required interface)
        final LinkToVoidFinishCreationEditPolicy linkTovoidEditPolicy = new LinkToVoidFinishCreationEditPolicy();
        installEditPolicy(LinkToVoidConstants.REQ_LINKTOVOID_END, linkTovoidEditPolicy);
        
        // For constraints "body"
        installEditPolicy(Constraint.MNAME, new ConstraintFinalizationEditPolicy());
        
        // To redirect to drawing layers
        installEditPolicy(DRAWINGLAYER_ROLE, new AskDrawingLayerEditPolicy());
        
        // Snap to Geometry feedback
        installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
        
    }

    /**
     * Creates an {@link AbstractDiagramFigure}.
     */
    @objid ("28b5e133-9225-4e57-8dd6-36c7e81046f3")
    @Override
    protected IFigure createFigure() {
        Figure diagramFigure = new AbstractDiagramFigure();
        IStyle style = ((GmAbstractObject) getModel()).getDisplayedStyle();
        
        // Set style independent properties
        
        // Set style dependent properties
        refreshFromStyle(diagramFigure, style);
        return diagramFigure;
    }

    /**
     * Encapsulate layout edit policies into {@link #createLayoutPolicyDecorator(EditPolicy)}.
     * <p>
     * May be redefined by sub classes.
     * @param layoutPolicy the layout edit policy. expected to be a {@link ConstrainedLayoutEditPolicy} by default implementation.
     * @return the created policy.
     * @since 5.1.0
     */
    @objid ("af05e012-1761-4576-8a5b-8bd9a32c21a4")
    @Override
    protected EditPolicy createLayoutPolicyDecorator(EditPolicy layoutPolicy) {
        if(false && layoutPolicy instanceof ConstrainedLayoutEditPolicy)
            return new LayoutConnectionsConstrainedLayoutEditPolicyDecorator((ConstrainedLayoutEditPolicy) layoutPolicy);
        else
            return  layoutPolicy;
        
    }

    /**
     * Hook for sub classes to redefine {@link #addChildVisual(EditPart, int)}.
     * <p>
     * By default calls {@link AbstractGraphicalEditPart#addChildVisual(EditPart, int)}
     */
    @objid ("e13d5a6e-f507-4d36-834c-e75a3cbe351d")
    protected void doAddChildVisual(EditPart childEditPart, int index) {
        super.addChildVisual(childEditPart, index);
    }

    /**
     * Hook for sub classes to redefine {@link #removeChildVisual(EditPart)}.
     * <p>
     * By default calls {@link AbstractGraphicalEditPart#removeChildVisual(EditPart)}
     */
    @objid ("18a001e2-2d25-40da-9873-644e28ddd622")
    protected void doRemoveChildVisual(EditPart childEditPart) {
        super.removeChildVisual(childEditPart);
    }

    /**
     * Get the layer pane where drawing layers are put.
     * @return the drawings layer pane.
     */
    @objid ("2b7373ed-b77c-4cd3-bbae-8887dba3c516")
    protected final LayeredPane getDrawingLayerPane() {
        LayeredPane pane = (LayeredPane) getLayer(DRAWING_LAYER);
        if (pane == null) {
            pane = createDrawingLayerPane();
        }
        return pane;
    }

    @objid ("3cb77c77-2b4d-47c8-834a-ae8b5344a64e")
    @Override
    protected List<Object> getModelChildren() {
        IGmDiagram d = (IGmDiagram) getModel();
        // Add background layer first
        // default children next
        // and finish with foreground layers.
        
        final List<?> modelChildren = super.getModelChildren();
        final Collection<IGmDrawingLayer> drawingLayers = d.getDrawingLayers();
        
        ArrayList<Object> ret = new ArrayList<>(modelChildren.size() + drawingLayers.size() + 1);
        
        if (d.getBackgroundDrawingLayer() != null) {
            ret.add(d.getBackgroundDrawingLayer());
        }
        
        ret.addAll(modelChildren);
        ret.addAll(drawingLayers);
        return ret;
    }

    /**
     * Refresh the figure from the given style.
     * <p>
     * Often called in {@link #createFigure()} and after a style change.
     * @param aFigure The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("7e05eb04-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        AbstractDiagramFigure diagramFigure = (AbstractDiagramFigure) aFigure;
        
        boolean viewGrid = style.getBoolean(GmAbstractDiagramStyleKeys.VIEWGRID);
        boolean snapToGrid = style.getBoolean(GmAbstractDiagramStyleKeys.SNAPTOGRID);
        boolean snapToGeom = style.getBoolean(GmAbstractDiagramStyleKeys.SNAPTOGEOMETRY);
        Color gridColor = style.getColor(GmAbstractDiagramStyleKeys.GRIDCOLOR);
        int gridAlpha = style.getInteger(GmAbstractDiagramStyleKeys.GRIDALPHA);
        int gridSpacing = style.getInteger(GmAbstractDiagramStyleKeys.GRIDSPACING);
        
        Color fillColor = style.getColor(GmAbstractDiagramStyleKeys.FILLCOLOR);
        String fillImage = style.getProperty(GmAbstractDiagramStyleKeys.FILLIMAGE);
        int fillAlpha = style.getInteger(GmAbstractDiagramStyleKeys.FILLALPHA);
        
        // Process the page size property
        // TODO: in the future this parsing might become the responsibility of the property view,
        // ie the property view would propose a 'Dimension' editor returning the proper 'in pixel' dimension value...
        String pageSize = (String) style.getProperty(GmAbstractDiagramStyleKeys.PAGE_SIZE);
        Dimension pixelPageSize = PageSizeParser.parseInPixels(pageSize);
        
        //
        EditPartViewer v = getRoot().getViewer();
        v.setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, Boolean.valueOf(viewGrid));
        v.setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, Boolean.valueOf(snapToGrid));
        v.setProperty(SnapToGrid.PROPERTY_GRID_SPACING, new Dimension(gridSpacing, gridSpacing));
        v.setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, Boolean.valueOf(snapToGeom));
        v.setProperty(AbstractDiagramEditPart.PROPERTY_GRID_COLOR, gridColor);
        v.setProperty(AbstractDiagramEditPart.PROPERTY_GRID_ALPHA, Integer.valueOf(gridAlpha));
        v.setProperty(AbstractDiagramEditPart.PROPERTY_FILL_COLOR, fillColor);
        v.setProperty(AbstractDiagramEditPart.PROPERTY_FILL_IMAGE, resolvePath(fillImage));
        v.setProperty(AbstractDiagramEditPart.PROPERTY_FILL_ALPHA, Integer.valueOf(fillAlpha));
        
        //
        diagramFigure.showPageBoundaries(style.getBoolean(GmAbstractDiagramStyleKeys.SHOW_PAGES));
        
        //
        v.setProperty(AbstractDiagramEditPart.PROPERTY_FILL_TILE_SIZE, pixelPageSize);
        diagramFigure.setPageBoundaries(pixelPageSize);
        
    }

    /**
     * Redefined to remove DrawingLayerEditPart layers from the drawing layer pane. <br/>
     * Subclasses must redefine {@link #doRemoveChildVisual(EditPart)}.
     */
    @objid ("78c57828-b40b-489b-b4eb-11e103e5eae4")
    @Override
    protected final void removeChildVisual(EditPart childEditPart) {
        if (childEditPart instanceof DrawingLayerEditPart) {
            final IFigure childFigure = ((GraphicalEditPart) childEditPart).getFigure();
            childFigure.getParent().remove(childFigure);
        } else {
            doRemoveChildVisual(childEditPart);
        }
        
    }

    /**
     * Resolve the given path against the project directory if it is a relative file path or a relative URL.
     * <p>
     * Returns the given string in all other cases.
     * @param aPath a path
     * @return the resolved path.
     */
    @objid ("e44af8a3-2dc8-403f-b750-4c8c9bacad07")
    protected final String resolvePath(String aPath) {
        if (aPath == null || aPath.isEmpty()) {
            return null;
        }
        
        String path = aPath;
        
        // Try to transform a file: URL to a file path
        try {
            URL url = new URL(path);
            if (url.getProtocol().isEmpty() || url.getProtocol().equals("file")) {
                path = url.getPath();
            } else {
                // it is an URL, return string unmodified
                return aPath;
            }
        } catch (@SuppressWarnings ("unused") MalformedURLException e1) {
            // ignore and next try
        }
        
        // handle path as a file path
        try {
            Path p = Paths.get(path);
            if (!Files.isRegularFile(p)) {
                // try to resolve against project directory
        
                p = getModel().getDiagram().getModelManager().getProjectPath().resolve(p);
                return p.toUri().toString();
            }
        } catch (@SuppressWarnings ("unused") InvalidPathException e) {
            // ignore and return string unmodified
        }
        return path;
    }

    /**
     * Run post loading actions.
     * @see GmAbstractDiagram#addPostLoadAction(IPostLoadAction)
     * @since 3.7
     */
    @objid ("63960f32-4c68-40b8-b32a-1c350c22fb7a")
    protected final void runPostLoadActions() {
        GmAbstractDiagram gm = (GmAbstractDiagram) getModel();
        List<IPostLoadAction> postLoadActions = gm.getPostLoadActions();
        
        if (!postLoadActions.isEmpty()) {
            final EditPartViewer viewer = getViewer();
            for (IPostLoadAction action : postLoadActions) {
                action.run(viewer);
            }
            postLoadActions.clear();
        }
        
        // Fire a property change event
        gm.firePropertyChange(GmAbstractDiagram.PROP_POSTLOADACTIONS_END);
        
    }

    /**
     * Update the 'previewData' for the diagram. Currently preview data is a UUBase64Compressor compressed string of the PNG image of the diagram.
     */
    @objid ("31e74d9f-a04e-4fe8-bb64-7b9653b19bfb")
    private void updatePreviewData() {
        ImageBuilder ib = new ImageBuilder();
        Image img = ib.makeImage(getRoot());
        if (img != null) {
            try {
                ImageLoader imgLoader = new ImageLoader();
                imgLoader.data = new ImageData[] { img.getImageData() };
        
                try (ByteArrayOutputStream bos = new ByteArrayOutputStream(img.getImageData().data.length)) {
                imgLoader.save(bos, SWT.IMAGE_PNG);
        
                StringBuilder pngPreviewString = new StringBuilder("data:image/png;base64,");
                pngPreviewString.append(Base64.getEncoder().encodeToString(bos.toByteArray()));
        
                bos.flush();
                bos.close();
        
                AbstractDiagram diagram = (AbstractDiagram) this.getModel().getRelatedElement();
                if (diagram != null) {
                    diagram.setPreviewData(pngPreviewString.toString());
                }
        
                img.dispose();
                }
            } catch (IOException e) {
                DiagramElements.LOG.warning("Failed saving %s preview: %s", getModel().getRelatedElement(), FileUtils.getLocalizedMessage(e));
                DiagramElements.LOG.warning(e);
            }
        
        }
        
    }

    /**
     * Edit policy that redirects request to the drawing layers
     * 
     * @author cmarin
     */
    @objid ("4d6b7bcf-d62e-4126-a0b9-8879a9f841ce")
    protected static class AskDrawingLayerEditPolicy extends AbstractEditPolicy {
        @objid ("2e8345e9-2a0a-491b-9b04-3992e964db70")
        public  AskDrawingLayerEditPolicy() {
            super();
        }

        @objid ("e6becfb4-2ff2-4371-9167-f2861abb8295")
        @Override
        public EditPart getTargetEditPart(Request request) {
            List<EditPart> l = getHost().getChildren();
            ListIterator<EditPart> it = l.listIterator(l.size());
            while (it.hasPrevious()) {
                EditPart o = it.previous();
                if (o instanceof DrawingLayerEditPart) {
                    EditPart target = o.getTargetEditPart(request);
                    if (target != null) {
                        return target;
                    }
                }
            }
            return null;
        }

    }

    @objid ("1a51575e-54c9-474b-bf54-6bd815cfc4bb")
    protected static class PageSizeParser {
        @objid ("5f0d4879-1973-4525-9088-aaca0d535ca2")
        private static final Pattern NOT_A_NUMBER = Pattern.compile("[^0-9\\.]");

        @objid ("ba08ef2e-7e82-4e11-be5b-9cb6c43eea40")
        private static final Pattern PAGE_FORMAT = Pattern.compile("(\\d+\\.?\\d*.*)(x)(\\d+\\.?\\d*.*)", Pattern.CASE_INSENSITIVE);

        /**
         * No instance
         */
        @objid ("5de944f5-5469-476b-ac8b-221c0f58de35")
        private  PageSizeParser() {
            
        }

        /**
         * Parse the page size and returns it as inches
         * @param value the page size string
         * @return the page size in inches
         */
        @objid ("41476101-a1f6-415b-9a8a-53441b700e00")
        private static final PrecisionDimension parsePageSize(String value) {
            if (value == null || value.isEmpty()) {
                return null;
            }
            
            final float oneInch = 25.4f; // mm
            String s = value.replace(" ", "");
            
            // This might be replaced by a lookup table in the future ?
            if ("A0H".equals(s)) {
                return convertMmToInch(new PrecisionDimension(1189, 841));
            }
            if ("A0V".equals(s)) {
                return convertMmToInch(new PrecisionDimension(841, 1189));
            }
            if ("A1H".equals(s)) {
                return convertMmToInch(new PrecisionDimension(841, 594));
            }
            if ("A1V".equals(s)) {
                return convertMmToInch(new PrecisionDimension(594, 841));
            }
            if ("A2H".equals(s)) {
                return convertMmToInch(new PrecisionDimension(594, 420));
            }
            if ("A2V".equals(s)) {
                return convertMmToInch(new PrecisionDimension(420, 594));
            }
            if ("A3H".equals(s)) {
                return convertMmToInch(new PrecisionDimension(420, 297));
            }
            if ("A3V".equals(s)) {
                return convertMmToInch(new PrecisionDimension(297, 420));
            }
            if ("A4H".equals(s)) {
                return convertMmToInch(new PrecisionDimension(297, 210));
            }
            if ("A4V".equals(s)) {
                return convertMmToInch(new PrecisionDimension(210, 297));
            }
            if ("A5H".equals(s)) {
                return convertMmToInch(new PrecisionDimension(210, 148));
            }
            if ("A5V".equals(s)) {
                return convertMmToInch(new PrecisionDimension(148, 210));
            }
            
            // try to parse
            Pattern whR = PAGE_FORMAT;
            
            Matcher whM = whR.matcher(s);
            
            if (whM.matches()) {
                String widthString = whM.group(1);
                String heightString = whM.group(3);
                float width;
                float height;
            
                if (widthString.endsWith("\"")) {
                    // '"' character means inches
            
                    width = Float.parseFloat(NOT_A_NUMBER.matcher(widthString).replaceAll(""));
                } else {
                    // convert mm to inches
                    width = Float.parseFloat(NOT_A_NUMBER.matcher(widthString).replaceAll("")) / oneInch; // 1 inch = 25,4 mm
                }
                if (heightString.endsWith("\"")) {
                    // '"' character means inches
            
                    height = Float.parseFloat(NOT_A_NUMBER.matcher(heightString).replaceAll(""));
                } else {
                    // convert mm to inches
                    height = Float.parseFloat(NOT_A_NUMBER.matcher(heightString).replaceAll("")) / oneInch; // 1 inch = 25,4 mm
                }
                return new PrecisionDimension(width, height);
            }
            return null;
        }

        /**
         * Convert a dimension from inches to pixel
         * @return the dimension in pixels
         */
        @objid ("7e05eb15-1dec-11e2-8cad-001ec947c8cc")
        private static final PrecisionDimension convertToPixel(PrecisionDimension d) {
            org.eclipse.swt.graphics.Point dpi = Display.getCurrent().getDPI();
            return new PrecisionDimension(d.preciseWidth() * dpi.x, d.preciseHeight() * dpi.y);
        }

        @objid ("5ca26440-c219-4cdc-8b1f-c1c965666dad")
        public static Dimension parseInPixels(String pageSize) {
            PrecisionDimension inchPageSize = parsePageSize(pageSize);
            if (inchPageSize != null) {
                return convertToPixel(inchPageSize);
            }
            return null;
        }

    }

}
