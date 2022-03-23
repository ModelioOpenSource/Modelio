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
package org.modelio.linkeditor.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GraphicsSource;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.Tool;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.modelio.linkeditor.gef.LinkEditorCommandStack;
import org.modelio.linkeditor.gef.LinkEditorEditPartFactory;
import org.modelio.linkeditor.gef.background.BackgroundEditPart;
import org.modelio.linkeditor.gef.background.LinkEditorDropTargetListener;
import org.modelio.linkeditor.gef.node.NodeEditPart;
import org.modelio.linkeditor.gef.tools.PanSelectionTool;
import org.modelio.linkeditor.panel.model.BackgroundModel;
import org.modelio.linkeditor.panel.model.GraphNode;
import org.modelio.linkeditor.view.ILinkEditorView;
import org.modelio.platform.ui.UIImages;

@objid ("6c6ca579-ff3a-473c-b763-a78ba0e7bdb2")
class LinkEditorPanelUi {
    @objid ("b7f1960a-90f9-40a1-b035-acd4bdf10496")
    private final LinkEditorPanelController controller;

    @objid ("ffcf9a7f-65f1-4933-ade4-c7cf802613f4")
    private final GraphicalViewer graphicalViewer;

    @objid ("84847763-faca-4b8f-b9a8-f218204cac3d")
    private final EditDomain editDomain = new EditDomain();

    @objid ("98e7067f-70ec-463d-b179-a8d929be5b3c")
    private final RootEditPart rootEditPart = new ScalableFreeformRootEditPart();

    @objid ("7a0778fa-49ff-402a-8506-960df51bd3a9")
    private SelectionSynchronizer synchronizer;

    @objid ("fdc1b4ee-babe-442c-a829-cb52534ba0c6")
    private final Composite composite;

    @objid ("964786b4-6e46-4e29-8671-e2b55a3e2024")
    private Runnable doubleClickListener;

    @objid ("142396ef-0d8a-4855-9f09-eb82af0eee60")
    private ISelectionChangedListener viewerSelectionListener;

    @objid ("07b83df0-febf-4dc8-a061-bbdc63818c26")
     LinkEditorPanelUi(Composite parent, LinkEditorPanelController controller) {
        this.controller = controller;
        
        this.composite = new Composite(parent, SWT.NONE);
        GridLayout gl = new GridLayout(1, true);
        gl.horizontalSpacing = 0;
        gl.verticalSpacing = 0;
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        this.composite.setLayout(gl);
        
        // Add the GEF viewer on top
        this.graphicalViewer = createGraphicalViewer(this.composite, controller.getEclipseContext());
        
        // Register viewer into the domain
        this.editDomain.addViewer(this.graphicalViewer);
        
        // Set layout data for the viewer
        GridData viewerLayoutData = new GridData();
        viewerLayoutData.grabExcessHorizontalSpace = true;
        viewerLayoutData.grabExcessVerticalSpace = true;
        viewerLayoutData.horizontalAlignment = SWT.FILL;
        viewerLayoutData.verticalAlignment = SWT.FILL;
        this.graphicalViewer.getControl().setLayoutData(viewerLayoutData);
        
        Font systemFont = this.composite.getDisplay().getSystemFont();
        org.eclipse.swt.graphics.Rectangle iconSize = UIImages.PLACEHOLDER.getBounds();
        GraphNode.WIDTH = GraphNode.MARGIN_WIDTH + iconSize.width + GraphNode.MARGIN_WIDTH + FigureUtilities.getStringExtents("abcdefghijklmnop...", systemFont).width() + GraphNode.MARGIN_WIDTH;
        GraphNode.HEIGHT = GraphNode.MARGIN_HEIGHT + Math.max(iconSize.height, systemFont.getFontData()[0].getHeight()) + GraphNode.MARGIN_HEIGHT + 2;
        
    }

    @objid ("f551b973-c020-46b9-a640-6df2f17415ed")
    Composite getComposite() {
        return this.composite;
    }

    @objid ("3a239098-56a1-41f2-a272-156e0422edb4")
    public void setInput(BackgroundModel model) {
        this.graphicalViewer.setContents(model);
        
        if (model.getCenter() != null) {
            this.graphicalViewer.setSelection(new StructuredSelection(this.graphicalViewer.getEditPartRegistry().get(model.getCenter())));
        }
        
    }

    /**
     * Creates the GraphicalViewer on the specified <code>Composite</code>.
     * @param ctx
     * @param parent the parent composite
     */
    @objid ("e62beecb-bd97-45fa-b0fd-fb15849ba901")
    private GraphicalViewer createGraphicalViewer(final Composite parent, IEclipseContext ctx) {
        // Need to use a hacked ScrollingGraphicalViewer to circumvent SWT/GEF bug 137786 (see
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=137786 )
        final GraphicalViewer viewer = new HackedScrollingGraphicalViewer();
        viewer.createControl(parent);
        
        configureGraphicalViewer(viewer, ctx);
        hookGraphicalViewer(viewer);
        initializeGraphicalViewer(viewer);
        
        viewer.addDropTargetListener(new LinkEditorDropTargetListener(viewer, this.controller.getProjectService()));
        
        FocusListener focusListener = new FocusListener() {
        
            @Override
            public void focusLost(FocusEvent e) {
                NodeEditPart.hasFocus = false;
            }
        
            @Override
            public void focusGained(FocusEvent e) {
                NodeEditPart.hasFocus = true;
            }
        };
        viewer.getControl().addFocusListener(focusListener);
        return viewer;
    }

    /**
     * Called to configure the graphical viewer before it receives its contents. This is where the root editpart should be configured.
     */
    @objid ("1ba6ade8-5e33-11e2-b81d-002564c97630")
    private void configureGraphicalViewer(final GraphicalViewer viewer, IEclipseContext ctx) {
        viewer.getControl().setBackground(ColorConstants.listBackground);
        
        // Set the root edit part
        viewer.setRootEditPart(this.rootEditPart);
        viewer.setEditPartFactory(new LinkEditorEditPartFactory(ctx));
        
        // Configure the edit domain
        // Set the active and default tool
        final SelectionTool selectionTool = new PanSelectionTool(this.controller.getNavigationService());
        this.editDomain.setActiveTool(selectionTool);
        this.editDomain.setDefaultTool(selectionTool);
        
        viewer.setEditDomain(this.editDomain);
        
        // Plug our own command stack that is bound to the Modelio transaction
        // manager
        this.editDomain.setCommandStack(new LinkEditorCommandStack(() -> this.controller.getProjectService().getSession()));
        
        // Configure zoom levels: 32 levels in a geometric progression reason sqrt(sqrt(2)) ~ 1.18
        int nZoomLevels = 32;
        double zoomLevels[] = new double[nZoomLevels];
        double progression = Math.sqrt(Math.sqrt(2.0));
        
        zoomLevels[nZoomLevels / 2] = 1.0;
        
        // Zoomin levels
        for (int i = nZoomLevels / 2 + 1; i < nZoomLevels; i++) {
            zoomLevels[i] = zoomLevels[i - 1] * progression;
        }
        
        // Zoom out levels
        for (int i = nZoomLevels / 2 - 1; i >= 0; i--) {
            zoomLevels[i] = zoomLevels[i + 1] / progression;
        }
        
        ZoomManager zoomManager = ((ScalableFreeformRootEditPart) viewer.getRootEditPart()).getZoomManager();
        zoomManager.setZoomLevels(zoomLevels);
        
        // Scroll-wheel Zoom
        viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), MouseWheelZoomHandler.SINGLETON);
        
        // Add the contextual menu
        this.controller.getMenuService().registerContextMenu(viewer.getControl(), ILinkEditorView.POPUPID);
        
    }

    /**
     * Hooks the GraphicalViewer to the rest of the Editor.
     * <p>
     * By default, the viewer is added to the SelectionSynchronizer, which can be used to keep 2 or more EditPartViewers in sync.
     * @param viewer the GraphicalViewer
     */
    @objid ("5a77fee9-006e-4ca7-86f1-c94c1f4b0d24")
    protected void hookGraphicalViewer(GraphicalViewer viewer) {
        this.synchronizer = new SelectionSynchronizer();
        this.synchronizer.addViewer(viewer);
        
        this.viewerSelectionListener = event -> this.controller.onEditorSelectionChanged(event.getSelection());
        viewer.addSelectionChangedListener(this.viewerSelectionListener);
        
    }

    /**
     * Set the contents of the GraphicalViewer after it has been created.
     * @see #createGraphicalViewer(Composite)
     * @param viewer the GraphicalViewer
     */
    @objid ("6bbe997a-9b71-4528-8a30-02e2b4565c9e")
    private void initializeGraphicalViewer(GraphicalViewer viewer) {
        // Set the viewer content
        BackgroundModel backgroundModel = new BackgroundModel();
        viewer.setContents(backgroundModel);
        
    }

    @objid ("efd5cf63-00bc-49cd-9844-7b4019ae6ab1")
    public void setActiveTool(Tool tool) {
        this.editDomain.setActiveTool(tool);
    }

    @objid ("415afa2d-de87-4293-b1da-b073cf826878")
    GraphicalViewer getGraphicalViewer() {
        return this.graphicalViewer;
    }

    @objid ("662e7fd6-fc9c-4c10-9c4e-d932def436a1")
    public void refresh() {
        this.graphicalViewer.getRootEditPart().getContents().refresh();
        this.graphicalViewer.getRootEditPart().refresh();
        
    }

    @objid ("e5aa3a30-1833-4fbc-9511-330f24f99942")
    void setDoubleClickListener(Runnable listener) {
        this.doubleClickListener = listener;
        getGraphicalViewer().setProperty(BackgroundEditPart.VIEWERPROP_SWITCH_EDIT_MODE, this.doubleClickListener);
        
    }

    /**
     * XXX Hack: we need to specialize the {@link GraphicsSource} to avoid calling the {@link Canvas#update()} method in the {@link GraphicsSource#getGraphics(Rectangle)} method.
     * <p>
     * This hack is needed to avoid SWT/GEF bug 137786 (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=137786 ) where drag over feedback (ie the drag feedback provided by the drag source) and drag under feedback (ie the feedback provided by the drop
     * target) interfere with each other, causing flickering and more importantly ugly graphical artifacts.
     * <p>
     * In order to be able to specialize the GraphicsSource, we need to specialize the {@link LightweightSystem}, and to do that we need in turn to specialize the GraphicalViewer.
     */
    @objid ("f974cc4c-1262-4c99-87cb-fdd3f9a942a5")
    private static class HackedScrollingGraphicalViewer extends ScrollingGraphicalViewer {
        @objid ("6d7d59d8-2771-4682-ad4f-19c3c07aa3a9")
        @Override
        protected LightweightSystem createLightweightSystem() {
            return new LightweightSystem() {
                            @Override
                            public void setControl(final Canvas c) {
                                super.setControl(c);
                                getUpdateManager().setGraphicsSource(new GraphicsSource() {
                                    @Override
                                    public Graphics getGraphics(Rectangle r) {
                                        c.redraw(r.x, r.y, r.width, r.height, false);
                                        // The actual hack is the following code
                                        // line: in original GEF code a call is
                                        // made to the #update() method of the c Canvas.
                                        // But calling #update() at this point
                                        // causes SWT to redraw the drag over
                                        // feedback which in turn causes GEF to
                                        // redraw the drag under feedback etc.
                                        // The final result is flickering
                                        // (because of constant erase and
                                        // redraw) and graphical artifacts.
                                        // Commenting this line however seems to
                                        // have no side effect (so far).
                                        // c.update();
                                        return null;
                                    }
            
                                    @Override
                                    public void flushGraphics(Rectangle region) {
                                        // Nothing to do.
                                    }
                                });
                            }
                        };
        }

    }

}
