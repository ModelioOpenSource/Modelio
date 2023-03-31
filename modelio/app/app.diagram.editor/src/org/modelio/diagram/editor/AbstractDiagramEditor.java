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
package org.modelio.diagram.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.IPresentationEngine;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.IPartListener;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerPreferences;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.modelio.api.modelio.picking.IPickingProvider;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.editor.plugin.DiagramEditorsManager;
import org.modelio.diagram.editor.plugin.IDiagramConfigurer;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.plugin.ModuleDiagramCustomizer;
import org.modelio.diagram.editor.plugin.ToolRegistry;
import org.modelio.diagram.editor.tools.PanSelectionTool;
import org.modelio.diagram.editor.tools.PickingSelectionTool;
import org.modelio.diagram.editor.widgets.draw2d.DeferredUpdateManagerWithWatchDog;
import org.modelio.diagram.editor.widgets.gef.ModelElementDropTargetListener;
import org.modelio.diagram.editor.widgets.gef.OutlinePage;
import org.modelio.diagram.editor.widgets.swt.FlyoutPaletteComposite2;
import org.modelio.diagram.editor.widgets.swt.FlyoutPaletteComposite2.FlyoutPreferences;
import org.modelio.diagram.editor.widgets.swt.SidePanelsContainerPanel;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.IDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.IDynamicStyler;
import org.modelio.diagram.elements.common.abstractdiagram.InfraDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.root.ScalableFreeformRootEditPart2;
import org.modelio.diagram.elements.core.link.ConnectionRoutingServices;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.factories.StandardEditPartFactory;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.core.picking.IPickingSession;
import org.modelio.platform.core.project.ICurrentProjectService;
import org.modelio.platform.mda.infra.service.IModuleService;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IModelChangeSupport;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Graphical Editor for Diagrams.
 */
@objid ("656f5647-33f7-11e2-95fe-001ec947c8cc")
@SuppressWarnings ("restriction")
public abstract class AbstractDiagramEditor implements IDiagramEditor {
    /**
     * Extension point ID for diagram editor lateral panels.
     * <p>
     * On Modelio 3.7 the only usage is for the symbol view.
     * 
     * @since 3.7
     */
    @objid ("bd0aba9b-8733-481b-998e-89ac30a4ebc9")
    private static final String DIAGRAM_EDITOR_FLYOUT_EXENSION_ID = "org.modelio.app.diagram.editor.flyout";

    @objid ("cbd8fb7c-c3b2-4bd5-b98b-8c136aebe6bb")
    private static final String DROPPOLICYEXTENSION_ID = "org.modelio.app.diagram.editor.droppolicy.extensions";

    @objid ("ca2fb77c-e418-46e4-a412-604cb01e9f83")
    private static final String PALETTE_LOCATION = "com.modeliosoft.modelio.diagram.editor.palettedock";

    @objid ("4e7baaed-a64b-45a7-9a78-d577084c6e4b")
    private static final String PALETTE_SIZE = "com.modeliosoft.modelio.diagram.editor.palettesize";

    @objid ("617557b4-3db2-4efa-a742-84f5437d9159")
    private static final String PALETTE_STATE = "com.modeliosoft.modelio.diagram.editor.palettestate";

    @objid ("6ced2780-3682-4d61-a6af-97179bb2cd18")
    private static final String SYMBOL_FLYOUT_LOCATION = "com.modeliosoft.modelio.diagram.editor.flyout.symbol.dock";

    @objid ("b8029364-94ca-4493-b2dc-fdb4265e4583")
    private static final String SYMBOL_FLYOUT_SIZE = "com.modeliosoft.modelio.diagram.editor.flyout.symbol.size";

    @objid ("e449f9c9-6767-40eb-ae18-03b7a426c59b")
    private static final String SYMBOL_FLYOUT_STATE = "com.modeliosoft.modelio.diagram.editor.flyout.symbol.state";

    @objid ("0afdbf2c-7844-42cd-9d03-9d3238d67724")
    private static final double[] ZOOM_LEVELS = { 0.25, 0.50, 0.75, 1.0, 1.25, 1.5, 1.75, 2.0, 2.25, 2.5, 2.75, 3.0,
                                                    3.25, 3.5, 3.75, 4.0 };

    @objid ("fc65ea0b-4fe3-4622-9cee-299a73dc7b78")
    @Inject
    private EContextService contextService;

    @objid ("84cc5b0b-335c-4ce4-bbb7-a78572bf1b5f")
    private GraphicalViewer graphicalViewer;

    @objid ("cb96adb5-d85b-4bd6-8513-111566f3ad23")
    @Inject
    private EMenuService menuService;

    @objid ("2a4d3fec-70f0-4708-979d-91a0ec921347")
    @Inject
    private MPart part;

    @objid ("9c72f52b-6d2c-4668-99ea-597054f44c84")
    private IPartListener partListener;

    @objid ("7d3834df-22f8-4697-891a-e79a3565435c")
    @Inject
    private EPartService partService;

    @objid ("f671b0be-edab-42f5-8176-191e7198bb9d")
    private final RootEditPart rootEditPart = new ScalableFreeformRootEditPart2();

    @objid ("081706ac-ce8c-4a8e-9e55-935fa792bba3")
    @Inject
    @Optional
    private ESelectionService selectionService;

    @objid ("32d47ff5-88d6-4155-a5b9-007844de74d7")
    private EditDomain editDomain;

    @objid ("88da4d34-9487-403b-8a5f-79e4bd9b52e0")
    private static Image modifiableImage;

    @objid ("305e54cf-9fda-4ed8-9b26-3db2328ba04a")
    private static Image notModifiableImage;

    @objid ("f7b0e5a9-c872-473e-ae33-b62050bd2eb2")
    private PaletteRoot paletteRoot;

    @objid ("b56c7ca5-2cc8-4389-a0db-c4460853cb50")
    private PaletteViewerProvider provider;

    /**
     * The selection synchronizer object, which can be used to sync the selection of 2 or more EditPartViewers.
     */
    @objid ("4ba20193-e69a-4943-9ec9-d72ef7b73c5e")
    private final SelectionSynchronizer synchronizer = new SelectionSynchronizer();

    @objid ("faad5e29-1cb9-4a73-bb62-fa1c3a66b770")
    @Inject
    private IDiagramConfigurerRegistry configurerRegistry;

    @objid ("dd9a4a49-a40c-46c2-a2db-b864f7c4a310")
    private final Collection<IPanelProvider> flyoutPanels = new ArrayList<>();

    @objid ("729499d6-c016-4206-8f12-867a95dd9a45")
    private DiagramEditorInput input;

    @objid ("d75b49eb-d5b5-4bb0-ba02-62014e889dde")
    private ModelChangeController modelChangeController;

    @objid ("f1a73c22-dd65-4904-a91d-87531055c9f1")
    private IsModifiableIndicator modifIndicator = new IsModifiableIndicator();

    @objid ("5e7a01c6-2b01-4c87-b1e5-371856abaeae")
    @Inject
    private IModuleService moduleService;

    @objid ("569296b9-13e3-41d8-92b0-cb308fdfcc10")
    @Inject
    private ICurrentProjectService projectService;

    @objid ("c9a76d06-eb16-4a02-88f5-ae4d48617016")
    private SidePanelsContainerPanel sashContainer;

    @objid ("9a433b9c-436f-4c2c-8115-e810f7654627")
    FlyoutPaletteComposite2 splitter;

    @objid ("b6687038-cd75-4b98-85ef-942b439f7a43")
    @Inject
    private ToolRegistry toolRegistry;

    /**
     * Constructor. Registers the editor as {@link IPickingProvider}.
     */
    @objid ("15c3abf7-f412-4250-b7ce-5390776065de")
    public  AbstractDiagramEditor() {
        super();
    }

    @objid ("159b1cbf-9d86-457e-a7b9-95f85f0f5c3f")
    @PreDestroy
    public void dispose(final DiagramEditorsManager manager) {
        try {
            // Unregister as model change listener
            if (getModelingSession() != null) {
                final IModelChangeSupport modelChangeSupport = getModelingSession().getModelChangeSupport();
                if (modelChangeSupport != null) {
                    modelChangeSupport.removeStatusChangeListener(this.modelChangeController);
                    modelChangeSupport.removeModelChangeListener(this.modelChangeController);
                }
            }
        
            // Remove the part listener
            if (this.partListener != null) {
                this.partService.removePartListener(this.partListener);
                this.partListener = null;
            }
        
            // Dispose the editor input
            if (this.input != null) {
                try {
                    manager.remove(this.input.getDiagram());
                    this.input.dispose();
                } finally {
                    this.input = null;
                }
            }
        
            // empty the palette
            if (this.editDomain != null) {
                this.editDomain.setActiveTool(null);
        
                final PaletteViewer paletteViewer = this.editDomain.getPaletteViewer();
                if (paletteViewer != null) {
                    paletteViewer.setContents(null);
                }
            }
        
            this.paletteRoot = null;
        
            // Dispose the modification indicator
            if (this.modifIndicator != null) {
                this.modifIndicator.dispose();
                this.modifIndicator = null;
            }
        } catch (RuntimeException | LinkageError | AssertionError | VirtualMachineError e) {
            // Trap errors to make sure Eclipse MPart management is not broken
            DiagramEditor.LOG.error("Unable to properly close %s diagram editor: %s", this, e.toString());
            DiagramEditor.LOG.error(e);
        }
        
    }

    /**
     * Notifies the editor that the handle opened on it has been closed. Depending on its nature, the editor might decide to delete itself, release some resources, or ignore this event altogether.
     */
    @objid ("afa645a8-36e9-4b29-befb-35bf484e873b")
    @Override
    public void disposeHandle() {
        // Nothing to do: this editor doesn't care about handles on it.
    }

    /**
     * Returns the adapter for the specified key.
     * <P>
     * <EM>IMPORTANT</EM> certain requests, such as the property sheet, may be made before or after {@link #createPartControl(Composite)} is called. The order is unspecified by the Workbench.
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    @objid ("42aa9234-878c-44e1-aa52-a8e4e49173bb")
    @SuppressWarnings ("unchecked")
    @Override
    public <T> T getAdapter(final Class<T> type) {
        if (type == ZoomManager.class) {
            return (T) ((ScalableFreeformRootEditPart2) getGraphicalViewer().getRootEditPart()).getZoomManager();
        }
        
        if (type == IContentOutlinePage.class) {
            return (T) new OutlinePage(this.graphicalViewer, this.synchronizer);
        }
        if (type == GraphicalViewer.class) {
            return (T) getGraphicalViewer();
        }
        if (type == CommandStack.class) {
            return (T) this.editDomain.getCommandStack();
        }
        if (type == EditPart.class && getGraphicalViewer() != null) {
            return (T) getGraphicalViewer().getRootEditPart();
        }
        if (type == IFigure.class && getGraphicalViewer() != null) {
            return (T) ((GraphicalEditPart) getGraphicalViewer().getRootEditPart()).getFigure();
        }
        return null;
    }

    @objid ("365b38e3-f58e-4c3b-a512-03aef1ecbf3c")
    @Override
    public final DiagramEditorInput getEditorInput() {
        return this.input;
    }

    /**
     * Returns the graphical viewer.
     * @return the graphical viewer
     */
    @objid ("defd22b5-d3fa-46d8-9105-e9aae99739a1")
    public GraphicalViewer getGraphicalViewer() {
        return this.graphicalViewer;
    }

    /**
     * Get the diagram's {@link MPart}.
     * @return the eclipse part displaying the diagram.
     */
    @objid ("4a025d78-9396-4c09-9300-947cabb6bbcb")
    public MPart getPart() {
        return this.part;
    }

    /**
     * Return the root edit part of this editor.
     * @return the root edit part of this editor.
     */
    @objid ("23af25bb-115b-41fa-9aba-8d08ba22e371")
    @Override
    public RootEditPart getRootEditPart() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.rootEditPart;
    }

    /**
     * Intended for specialization. Called after postConstruct
     */
    @objid ("8c8bbc5f-9fdd-47cd-a6fa-3c47c6006bd8")
    public void initialize() {
        // Empty
    }

    /**
     * @return true if the {@link #dispose(DiagramEditorsManager)} has been called or {@link #postConstruct(Composite, DiagramEditorInput)} has not yet been called.
     * @since 3.7
     */
    @objid ("895b786c-5ecd-4241-b541-6efe91075db7")
    public final boolean isDisposed() {
        return this.partListener == null;
    }

    /**
     * Make the {@link PickingSelectionTool} active when a picking session starts.
     * @param session the picking session.
     */
    @objid ("db0fd136-fd76-421c-bd86-648ca6f148b3")
    @Inject
    @Optional
    public void onPickingStart(@EventTopic (ModelioEventTopics.PICKING_START) final IPickingSession session) {
        Display.getDefault().asyncExec(() -> this.editDomain.setActiveTool(new PickingSelectionTool(session)));
    }

    /**
     * Make the {@link PanSelectionTool} active when a picking session ends.
     * @param session the picking session.
     */
    @objid ("254c7d48-b2d3-41fe-910f-2413833b4eed")
    @Inject
    @Optional
    public void onPickingStop(@EventTopic (ModelioEventTopics.PICKING_STOP) final IPickingSession session) {
        Display.getDefault().asyncExec(() -> this.editDomain.setActiveTool(new PanSelectionTool()));
    }

    @objid ("a81962dc-ac08-4e93-b3af-cc887a6dd829")
    @PostConstruct
    public final void postConstruct(final Composite composite, final DiagramEditorInput diagramEditorInput) {
        if (diagramEditorInput != null) {
            this.input = diagramEditorInput;
        
            // First create an edit domain instance
            this.editDomain = new EditDomain();
        
            this.paletteRoot = new PaletteRoot();
        
            // CreatePartControl
            createPartControl(composite);
            this.graphicalViewer.setEditDomain(this.editDomain);
        
            // Use applicable configurer to configure the diagram
            configureDiagram();
        
            if (DiagramEditor.LOG.isDebugEnabled()) {
                DiagramEditor.LOG.debug("AbstractDiagramEditor: Dumping %s diagram palette:", diagramEditorInput.getDiagram());
                for (Object child : this.paletteRoot.getChildren()) {
                    if (child instanceof PaletteGroup) {
                        PaletteGroup pg = (PaletteGroup) child;
                        DiagramEditor.LOG.debug("- "+pg.getLabel() + " - " + pg);
                        DiagramEditor.LOG.debug("    small icon=" + pg.getSmallIcon());
                        DiagramEditor.LOG.debug("    small icon=" + pg.getLargeIcon());
                        for (Object child2 : pg.getChildren()) {
                            PaletteEntry pe2 = (PaletteEntry) child2;
                            DiagramEditor.LOG.debug("  - "+pe2.getLabel() + " - " + pe2);
                            DiagramEditor.LOG.debug("      small icon=" + pe2.getSmallIcon());
                            DiagramEditor.LOG.debug("      large icon=" + pe2.getLargeIcon());
                        }
                    }
                }
            }
        
            // Configure the edit domain
            this.editDomain.setPaletteRoot(this.paletteRoot);
        
            // Configure the edit domain
            // Set the active and default tool
            final SelectionTool selectionTool = new PanSelectionTool();
            this.editDomain.setActiveTool(selectionTool);
            this.editDomain.setDefaultTool(selectionTool);
            this.editDomain.addViewer(this.graphicalViewer);
        
            // Register as a model and status change listener
            final IModelChangeSupport modelChangeSupport = getModelingSession().getModelChangeSupport();
            this.modelChangeController = new ModelChangeController(this);
            modelChangeSupport.addModelChangeListener(this.modelChangeController);
            modelChangeSupport.addStatusChangeListener(this.modelChangeController);
        
            // Plug our own command stack that is bound to the Modelio
            // transaction manager
            this.editDomain.setCommandStack(new DiagramCommandStack(getModelingSession(), this.input.getGmDiagram()));
        
            showPalette(this.input.getGmDiagram().isUserEditable());
        
            initialize();
        
            // Part listening to detect visible/hidden part
            this.partListener = new IPartListener() {
        
                @Override
                public void partActivated(final MPart activatedPart) {
                    if (activatedPart.equals(AbstractDiagramEditor.this.part)) {
                        // System.out.printf(" => partActivated %s - %s\n",
                        // activatedPart.getLabel(),
                        // activatedPart.getElementId());
                        AbstractDiagramEditor.this.modifIndicator.update(activatedPart, AbstractDiagramEditor.this.input.getDiagram());
                    }
                }
        
                @Override
                public void partBroughtToTop(final MPart topPart) {
                    // noop
                }
        
                @Override
                public void partDeactivated(final MPart deactivatedPart) {
                    // noop
                }
        
                @Override
                public void partHidden(final MPart hiddenPart) {
                    if (hiddenPart.equals(AbstractDiagramEditor.this.part)) {
                        final IGmDiagram gmDiagram = AbstractDiagramEditor.this.getEditorInput().getGmDiagram();
                        if (gmDiagram != null) {
                            gmDiagram.enableRefresh(false);
                        }
        
                    }
                }
        
                @Override
                public void partVisible(final MPart visiblePart) {
                    if (visiblePart.equals(AbstractDiagramEditor.this.part)) {
                        // System.out.printf(" => partVisible %s\n",
                        // part.getLabel());
                        final IGmDiagram gmDiagram = AbstractDiagramEditor.this.getEditorInput().getGmDiagram();
                        if (gmDiagram != null) {
                            gmDiagram.enableRefresh(true);
                            AbstractDiagramEditor.this.modifIndicator.update(visiblePart, AbstractDiagramEditor.this.input.getDiagram());
                        }
                    }
                }
        
            };
            this.partService.addPartListener(this.partListener);
        
            // Force a complete refresh now that edit parts are finally
            // listening to
            // events that might
            // be sent by the model (e.g.: links that have changed source and/or
            // target while diagram
            // was closed).
            this.input.getGmDiagram().refreshAllFromObModel();
        
        } else {
            MessageDialog.openError(composite.getShell(), DiagramEditor.I18N.getMessage("InvalidDiagram.title"),
                    DiagramEditor.I18N.getMessage("InvalidDiagram.message"));
        }
        
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    @objid ("0bb1a659-6ba9-43d9-bb8e-aacc5e39f391")
    @Focus
    public void setFocus() {
        final GraphicalViewer viewer = getGraphicalViewer();
        if (viewer != null) {
            viewer.getControl().setFocus();
        }
        
    }

    /**
     * Hide/show the diagram's palette.
     * @param onOff <code>true</code> to display the palette, <code>false</code> otherwise.
     */
    @objid ("48f48e41-34e8-46c8-8d75-aba77e3ba3ed")
    public void showPalette(final boolean onOff) {
        this.splitter.setState(onOff ? FlyoutPaletteComposite2.STATE_PINNED_OPEN : FlyoutPaletteComposite2.STATE_HIDDEN);
    }

    @objid ("233d5b5e-3345-46a0-9356-5955164b17c6")
    @Override
    public String toString() {
        try {
            return String.format("%s [part label='%s', input=%s]", getClass().getSimpleName(), this.part.getLabel(), this.input);
        } catch (RuntimeException | Error e) {
            return getClass().getSimpleName();
        }
        
    }

    @objid ("b674cc29-2966-44bd-abdd-8be03fbdff4e")
    @Inject
    @Optional
    void onNavigateElement(@EventTopic (ModelioEventTopics.NAVIGATE_ELEMENT) final MObject element) {
        final AbstractDiagram diagram = this.input.getDiagram();
        if (element.equals(diagram)) {
            Display.getDefault().asyncExec(() -> AbstractDiagramEditor.this.partService.activate(AbstractDiagramEditor.this.part));
        }
        
    }

    @objid ("874260af-45ae-432f-bf18-526e19c0853e")
    @Inject
    @Optional
    void onNavigateElement(@EventTopic (ModelioEventTopics.NAVIGATE_ELEMENT) final List<MObject> elements) {
        if (elements.size() == 1) {
            onNavigateElement(elements.get(0));
        }
        
    }

    @objid ("1450de0f-6bd3-4e5d-ab43-25d8bf2840d6")
    protected void configureGraphicalViewer(final GraphicalViewer viewer) {
        viewer.getControl().addFocusListener(new FocusListener() {
            private ArrayList<String> activeContexts = new ArrayList<>();
        
            @Override
            public void focusGained(final FocusEvent e) {
                // Restore previously deactivated contexts
                for (final String contextId : this.activeContexts) {
                    AbstractDiagramEditor.this.contextService.activateContext(contextId);
                }
                this.activeContexts = new ArrayList<>();
            }
        
            @Override
            public void focusLost(final FocusEvent e) {
                // We must deactivate the active contexts when losing focus, to
                // avoid the editor's shortcuts to be triggered when editing
                // elsewhere...
        
                // Store those contexts for further reactivation
                final Collection<String> activeContextIds = AbstractDiagramEditor.this.contextService.getActiveContextIds();
                if (activeContextIds != null) {
                    this.activeContexts = new ArrayList<>(activeContextIds);
                    for (final String contextId : this.activeContexts) {
                        AbstractDiagramEditor.this.contextService.deactivateContext(contextId);
                    }
                }
            }
        });
        
        viewer.getControl().setBackground(ColorConstants.listBackground);
        
        // Set the root edit part
        viewer.setRootEditPart(this.rootEditPart);
        viewer.setEditPartFactory(createEditPartFactory());
        
        // D&D support
        IModel imodel = this.projectService.getSession().getModel();
        viewer.addDropTargetListener(ModelElementDropTargetListener.forLocalSelectionTransfer(viewer, imodel));
        viewer.addDropTargetListener(ModelElementDropTargetListener.forModelElementTransfer(viewer, imodel));
        
        // Configure zoom levels
        final ZoomManager zoomManager = ((ScalableFreeformRootEditPart2) viewer.getRootEditPart()).getZoomManager();
        zoomManager.setZoomLevels(AbstractDiagramEditor.ZOOM_LEVELS);
        
        // Scroll-wheel Zoom
        viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), MouseWheelZoomHandler.SINGLETON);
        
        // Add the contextual menu
        final String popupId = getPopupId();
        if (popupId != null) {
            this.menuService.registerContextMenu(viewer.getControl(), popupId);
        }
        
        // Add handler to move elements with arrow keys
        final MoveKeyHandler kh = new MoveKeyHandler(viewer);
        kh.setParent(viewer.getKeyHandler());
        viewer.setKeyHandler(kh);
        
    }

    /**
     * Creates the GraphicalViewer on the specified <code>Composite</code>.
     * @param parent the parent composite
     */
    @objid ("52976cc9-9bbc-48a0-a74b-307caff0bd62")
    protected GraphicalViewer createGraphicalViewer(final Composite parent) {
        final ScrollingGraphicalViewer viewer = new ScrollingGraphicalViewer() {
            @Override
            protected LightweightSystem createLightweightSystem() {
                final LightweightSystem ls = super.createLightweightSystem();
                ls.setUpdateManager(new DeferredUpdateManagerWithWatchDog());
                return ls;
            }
        };
        viewer.createControl(parent);
        
        configureGraphicalViewer(viewer);
        hookGraphicalViewer(viewer);
        initializeGraphicalViewer(viewer);
        return viewer;
    }

    @objid ("b9dc6bce-d5af-4198-893f-b4be65a47e4e")
    protected void createPartControl(final Composite parent) {
        this.sashContainer = new SidePanelsContainerPanel(parent);
        
        this.splitter = new FlyoutPaletteComposite2(this.sashContainer.getMainPanel(),
                SWT.NONE, getPaletteViewerProvider(),
                getPalettePreferences());
        
        this.graphicalViewer = createGraphicalViewer(this.splitter);
        
        this.splitter.setGraphicalControl(getGraphicalControl());
        
        for (final IConfigurationElement configurationElement : new ExtensionPointContributionManager(AbstractDiagramEditor.DIAGRAM_EDITOR_FLYOUT_EXENSION_ID).getExtensions("panel")) {
            try {
                final IPanelProvider panelProv = (IPanelProvider) configurationElement.createExecutableExtension("class");
                this.sashContainer.addFlyout(panelProv, "Symbol", null);
                this.flyoutPanels.add(panelProv);
                this.selectionService.addPostSelectionListener((p, selection) -> panelProv.setInput(selection));
            } catch (CoreException | ClassCastException e) {
                DiagramEditor.LOG.error(e);
            }
        }
        
    }

    /**
     * @return the graphical viewer's control
     */
    @objid ("c5a623a6-260a-4fa5-a1bc-bee010298cf2")
    protected Control getGraphicalControl() {
        return getGraphicalViewer().getControl();
    }

    /**
     * Get the modeling session.
     * @return The modeling session.
     */
    @objid ("2a2f72a2-0cbf-4b52-9d34-40bc9422593d")
    protected final ICoreSession getModelingSession() {
        return this.projectService.getSession();
    }

    @objid ("f6f85175-756f-45c3-9d34-dc58a006b8d0")
    protected FlyoutPreferences getPalettePreferences() {
        return new EditorFlyoutPreferences(AbstractDiagramEditor.PALETTE_LOCATION, AbstractDiagramEditor.PALETTE_SIZE, AbstractDiagramEditor.PALETTE_STATE);
    }

    @objid ("578311aa-5234-47ea-9a08-3a3af1991cb0")
    protected abstract String getPopupId();

    /**
     * Hooks the GraphicalViewer to the rest of the Editor. By default, the viewer is added to the SelectionSynchronizer, which can be used to keep 2 or more EditPartViewers in sync. The viewer is also registered as the ISelectionProvider for the Editor's
     * PartSite.
     */
    @objid ("f4580294-57b0-4760-9658-2bcf0aff7fc0")
    protected void hookGraphicalViewer(final GraphicalViewer viewer) {
        this.synchronizer.addViewer(viewer);
        
        viewer.addSelectionChangedListener(event -> {
            this.selectionService.setSelection(event.getSelection());
            for (final IPanelProvider p : this.flyoutPanels) {
                p.setInput(event.getSelection());
            }
        });
        
    }

    @objid ("4ab61e21-a719-49f7-a91f-fd0309ecaa22")
    protected final void initializeGraphicalViewer(final GraphicalViewer viewer) {
        this.splitter.hookDropTargetListener(viewer);
        
        viewer.setProperty(ConnectionRoutingServices.ID, initializeConnectionRoutingServices());
        
        // Set the viewer content
        if (getEditorInput() != null) {
            final IGmDiagram gmDiagram = getEditorInput().getGmDiagram();
            viewer.setContents(gmDiagram);
            viewer.setProperty(DiagramElementDropEditPolicy.DROP_EXTENSIONS, loadDropExtensions());
        
        }
        
    }

    /**
     * Initialize connection routing services:
     * <ul>
     * <li>routers</li>
     * <li>connection helper factory</li>
     * </ul>
     */
    @objid ("f39f9fa8-b6c7-4e9f-9403-0ead0ca3848d")
    protected ConnectionRoutingServices initializeConnectionRoutingServices() {
        return ConnectionRoutingServices.builder()
                .withAutoOrthogonalDefaults()
                .build();
        
    }

    /**
     * Set the palette root of this editor.
     * @param value the palette root for this diagram.
     */
    @objid ("fc3be786-4ede-45ab-89a3-c3015e1dbb56")
    private void changePaletteContents(final PaletteRoot replacementPalette) {
        // We are not supposed to replace the palette root that has been
        // initialized in the C'tor, only its
        // contents shall be changed
        
        // Set the new palette contents
        if (replacementPalette != null) {
            this.paletteRoot.setChildren(replacementPalette.getChildren());
        }
        
    }

    /**
     * Configure the diagram using its dedicated configurer ({@link IDiagramConfigurer}).
     * <p>
     * The chosen configurer is the configurer for the metaclass possibly overridden by configurers brought in by the diagram's first stereotype.
     */
    @objid ("53dae48f-fa16-437e-a988-a56f680ee2d0")
    private void configureDiagram() {
        final AbstractDiagram editedDiagram = this.input.getDiagram();
        
        // Ensure figures are layouted before beginning firing notifications in the Gm model (mantis 0013360)
        ((GraphicalEditPart) getGraphicalViewer().getRootEditPart()).getFigure().getUpdateManager().performValidation();
        
        final List<String> stereotypes = editedDiagram.getExtension().stream()
                // Ignore shell stereotypes
                .filter(Stereotype::isValid)
                // Ignore orphan stereotypes
                .filter(stereotype -> stereotype.getModule() != null)
                // Sort stereotypes according to the "started modules" order
                .sorted((stereotype1, stereotype2) -> Integer.compare(getModulePriority(stereotype1.getModule()), getModulePriority(stereotype2.getModule())))
                .map(s -> s.getName())
                .collect(Collectors.toList());
        
        if (stereotypes.isEmpty()) {
            // Use metaclass configurer
            final IDiagramConfigurer diagramConfigurer = this.configurerRegistry.getConfigurer(editedDiagram.getMClass().getName());
            if (diagramConfigurer != null) {
                changePaletteContents(diagramConfigurer.initPalette(this, this.toolRegistry));
                this.input.getGmDiagram().setDynamicStyler(diagramConfigurer.getDynamicStyler());
            }
        } else {
            // Use configurer from metaclass and stereotype
            IDiagramConfigurer current = null;
            for (final IDiagramConfigurer configurer : this.configurerRegistry.getConfigurers(editedDiagram.getMClass().getName(), stereotypes)) {
                if (current == null || !(configurer instanceof ModuleDiagramCustomizer)) {
                    current = configurer;
                } else {
                    // Chain the odule customizers
                    ModuleDiagramCustomizer mdc = (ModuleDiagramCustomizer) configurer;
                    current = new ModuleDiagramCustomizer(current, mdc.getModuleCustomizer(), mdc.getDynamicStyler());
                }
        
                // Only the first configurer declaring a dynamic styler is taken into account
                IDynamicStyler dynamicStyler = current.getDynamicStyler();
                if (dynamicStyler != null && this.input.getGmDiagram().getDynamicStyler() == null) {
                    this.input.getGmDiagram().setDynamicStyler(dynamicStyler);
                }
            }
            if (current != null) {
                changePaletteContents(current.initPalette(this, this.toolRegistry));
            }
        }
        
    }

    @objid ("413b80f2-9075-4ffe-a195-fd108e45df76")
    private ToolEntry getFirstToolEntry(final PaletteRoot value) {
        for (final Object child : value.getChildren()) {
            if (child instanceof PaletteContainer) {
                for (final Object child2 : ((PaletteContainer) child).getChildren()) {
                    if (child2 instanceof ToolEntry) {
                        return (ToolEntry) child2;
                    }
                }
            } else if (child instanceof ToolEntry) {
                return (ToolEntry) child;
            }
        }
        return null;
    }

    /**
     * Compute a priority for a module component, according to its position in the "stated modules" list.
     */
    @objid ("f926ecb6-3d83-418f-9549-00540697b1cb")
    private int getModulePriority(final ModuleComponent moduleComponent) {
        if (this.moduleService != null) {
            final List<IRTModule> startedModules = this.moduleService.getStartedModules();
            for (int i = 0; i < startedModules.size(); i++) {
                final IRTModule startedModule = startedModules.get(i);
                if (Objects.equals(moduleComponent, startedModule.getModel())) {
                    return i;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Returns the palette viewer provider that is used to create palettes for the view and the flyout. Creates one if it doesn't already exist.
     * @see #createPaletteViewerProvider()
     * @return the PaletteViewerProvider that can be used to create PaletteViewers for this editor
     */
    @objid ("abe78536-b635-463a-9292-d189d52f43c6")
    private final PaletteViewerProvider getPaletteViewerProvider() {
        if (this.provider == null) {
            this.provider = new PaletteViewerProvider(this.editDomain) {
                @Override
                public PaletteViewer createPaletteViewer(final Composite parent) {
                    final PaletteViewer pViewer = new PaletteViewer();
                    pViewer.getPaletteViewerPreferences().setLayoutSetting(PaletteViewerPreferences.LAYOUT_ICONS);
                    pViewer.createControl(parent);
                    configurePaletteViewer(pViewer);
                    hookPaletteViewer(pViewer);
                    return pViewer;
                }
            };
        }
        return this.provider;
    }

    @objid ("f7c321c3-64cc-4416-b6ca-84a77c01a595")
    private List<IDiagramElementDropEditPolicyExtension> loadDropExtensions() {
        final List<IDiagramElementDropEditPolicyExtension> ret = new ArrayList<>();
        // Infra contribution is ALWAYS registered
        ret.add(new InfraDiagramElementDropEditPolicyExtension());
        
        for (final IConfigurationElement dropExtensionElement : new ExtensionPointContributionManager(AbstractDiagramEditor.DROPPOLICYEXTENSION_ID).getExtensions("droppolicyextension")) {
            for (final IConfigurationElement scope : dropExtensionElement.getChildren("scope")) {
                final String editorId = scope.getAttribute("editorId");
                if (editorId.equals(getPart().getElementId())) {
                    // TODO handle metaclass & stereotype
                    try {
                        final Object o = dropExtensionElement.createExecutableExtension("class");
                        if (o instanceof IDiagramElementDropEditPolicyExtension) {
                            ret.add((IDiagramElementDropEditPolicyExtension) o);
                        }
                    } catch (final CoreException e1) {
                        DiagramEditor.LOG.error(e1);
                    }
                }
            }
        }
        
        if (this.input.getGmDiagram().canUnmaskGenericElements()) {
            ret.add(new AbstractDiagramElementDropEditPolicyExtension() {
                @Override
                public boolean isToBeAddedToHierarchy(final IGmDiagram context, final Deque<MObject> hierarchy, final MObject candidate) {
                    return hierarchy.isEmpty();
                }
        
                @Override
                public boolean canUnmask(final DiagramElementDropEditPolicy dropPolicy, final MObject candidate) {
                    return true;
                }
        
                @Override
                public MObject getParentInGraphicalHierarchy(final IGmDiagram context, final MObject element) {
                    return null;
                }
            });
        }
        return ret;
    }

    @objid ("7983fcbd-42ca-4daa-a226-4cc332cc72be")
    protected final StandardEditPartFactory createEditPartFactory() {
        return new StandardEditPartFactory(this.input.getGmDiagram().getFactoryIdentifier());
    }

    /**
     * Palette preferences manager
     */
    @objid ("55833e5e-f67b-4645-8906-0303d9ceaf1a")
    private static class EditorFlyoutPreferences implements FlyoutPreferences {
        @objid ("b750f45b-6dd9-47fe-a376-1dd83614247d")
        private final String locationKey;

        @objid ("30d32154-459f-4f4d-b697-f970f36c60e2")
        private final String sizeKey;

        @objid ("2f758001-e0ea-436a-853e-ee7309b63d29")
        private final String stateKey;

        @objid ("53004142-b4fc-4c4d-a018-02bad01bcc50")
         EditorFlyoutPreferences(final String locationKey, final String sizeKey, final String stateKey) {
            this.locationKey = locationKey;
            this.sizeKey = sizeKey;
            this.stateKey = stateKey;
            
        }

        @objid ("0346cac5-3805-4115-a405-6905bddb5339")
        @Override
        public int getDockLocation() {
            return getDiagramPreferences().getInt(
                    this.locationKey,
                    PositionConstants.WEST);
            
        }

        @objid ("6865e36c-7d4d-4af1-a27c-09f81abe26b2")
        @Override
        public int getPaletteState() {
            return getDiagramPreferences().getInt(
                    this.stateKey,
                    FlyoutPaletteComposite.STATE_PINNED_OPEN);
            
        }

        @objid ("4202f687-f4c8-4e29-bab9-979a555fdba5")
        @Override
        public int getPaletteWidth() {
            return getDiagramPreferences().getInt(
                    this.sizeKey,
                    FlyoutPaletteComposite2.DEFAULT_PALETTE_SIZE);
            
        }

        @objid ("207fafec-3a75-4fc9-a943-bceaffe6ceba")
        @Override
        public void setDockLocation(final int location) {
            if (location == PositionConstants.WEST || location == PositionConstants.EAST) {
                getDiagramPreferences().putInt(
                        this.locationKey,
                        location);
            }
            
        }

        @objid ("ba8c4ceb-0a44-4e87-90fa-0c1b04eab7cc")
        @Override
        public void setPaletteState(final int state) {
            getDiagramPreferences().putInt(this.stateKey, state);
        }

        @objid ("45661c5c-a782-41c4-a7f7-8ea4d7ae1e6f")
        @Override
        public void setPaletteWidth(final int width) {
            getDiagramPreferences().putInt(this.sizeKey, width);
        }

        @objid ("d4762564-6dca-4b1d-860f-a89d989c09e2")
        private IEclipsePreferences getDiagramPreferences() {
            return InstanceScope.INSTANCE.getNode(DiagramEditor.PLUGIN_ID);
        }

    }

    /**
     * This class is in charge of decorating the tab folder icon with a read-only decoration when the diagram being displayed is not editable. The class 'caches' the diagram decorated image and dispose it in its dispose() method.
     */
    @objid ("736e0ba7-983a-48d1-b4b0-bec3371dda03")
    private static final class IsModifiableIndicator {
        @objid ("f660dfe1-c709-4414-9476-d2551afb5c1d")
        private Image decoratedIcon = null;

        @objid ("8cd35c74-3d0a-480f-85b1-380ac0c6288e")
        public  IsModifiableIndicator() {
            // Nothing specific to do here.
        }

        @objid ("20ac098f-d20a-434e-a255-c15ebea408c5")
        protected void update(final MPart part, final AbstractDiagram diagram) {
            if (diagram != null) {
                if (diagram.isModifiable()) {
                    setIcon(part, ElementImageService.getIcon(diagram));
                } else {
                    setIcon(part, getReadOnlylDecoratedIcon(diagram));
                }
            }
            
        }

        @objid ("b283c52a-5211-41a2-8504-1bf5d304f8ae")
        public void dispose() {
            if (this.decoratedIcon != null) {
                this.decoratedIcon.dispose();
                this.decoratedIcon = null;
            }
            
        }

        @objid ("e55e207f-9cd9-4a2d-a607-0fd225311619")
        private Image getReadOnlylDecoratedIcon(final AbstractDiagram diagram) {
            if (this.decoratedIcon == null) {
                final Image baseImage = ElementImageService.getIcon(diagram);
                final ImageDescriptor overlay = AbstractUIPlugin.imageDescriptorFromPlugin(DiagramEditor.PLUGIN_ID, "icons/readonly_overlay.png");
            
                this.decoratedIcon = new DecorationOverlayIcon(baseImage, overlay, IDecoration.TOP_LEFT).createImage();
            }
            return this.decoratedIcon;
        }

        /**
         * Set an icon for the part.
         */
        @objid ("da5c7ef6-145f-41fd-a3c0-913295a78249")
        private void setIcon(final MPart part, final Image icon) {
            part.getTransientData().put(IPresentationEngine.OVERRIDE_ICON_IMAGE_KEY, icon);
        }

    }

    /**
     * This model and status changes listener refreshes the diagram editor when events occur.
     */
    @objid ("95d977fb-13fc-4c08-bdad-8cf70ce6e1c4")
    private static final class ModelChangeController implements IModelChangeListener, IStatusChangeListener {
        /**
         * The list of stereotype names extending the current diagram input.
         * <p>
         * Needed to configure the diagram's {@link IDiagramConfigurer}.
         * </p>
         * 
         * @see #configureDiagram()
         */
        @objid ("bffa6222-84ed-41ee-8511-812364d01177")
        private List<String> referenceStereotypes = Collections.emptyList();

        @objid ("f7c733f5-8d1a-4461-a9e2-5dd2835750b6")
        private final AbstractDiagramEditor editor;

        @objid ("57bf5df2-61d4-42b4-a455-3da9ff5ceacb")
        public  ModelChangeController(final AbstractDiagramEditor editor) {
            this.editor = editor;
        }

        @objid ("11288bfc-3216-43fd-9258-709e33173139")
        private List<String> getDiagramStereotypes(final AbstractDiagram editedDiagram) {
            final List<String> stereotypes = new ArrayList<>();
            for (final Stereotype stereotype : editedDiagram.getExtension()) {
                stereotypes.add(stereotype.getName());
            }
            return stereotypes;
        }

        /**
         * Invoked when the model has changed.
         * <p>
         * <ul>
         * <li>Refresh the diagram title</li>
         * <li>Refresh the 'read-only' indicator in th eeditor tab</li>
         * <li>Close the diagram editor if deleted from the model.</li>
         * <li>Refresh the palette (configurers) if needed</li>
         * </ul>
         * <p>
         */
        @objid ("b03c079e-7124-44bf-ada6-05faa721a7dc")
        @Override
        public void modelChanged(final IModelChangeEvent event) {
            final AbstractDiagram diagram = this.editor.getEditorInput().getDiagram();
            
            // Re enter the UI thread
            swtAsyncExec(() -> {
                // Some elements were deleted: check for validity of the diagram.
                if (diagram == null || !diagram.isValid()) {
                    // The diagram in no longer valid, close the editor
                    // use the PartService to close this editor
                    this.editor.partService.hidePart(this.editor.getPart(), true);
                    return;
                }
            
                if (this.editor.isDisposed()) {
                    return;
                }
            
                // At this point, we know that diagram is still valid,
                // update the editor's title.
                this.editor.getPart().setLabel(diagram.getName());
                this.editor.modifIndicator.update(this.editor.getPart(), diagram);
            
                // Refresh the configurers if the stereotype list has
                // changed
                final List<String> newStereotypes = getDiagramStereotypes(diagram);
                if (stereotypesChanged(newStereotypes)) {
                    this.editor.configureDiagram();
                    this.referenceStereotypes = newStereotypes;
                }
            
            });
            
        }

        @objid ("ea32a405-82c5-43fc-a2f6-395caeb326b3")
        @Override
        public void statusChanged(final IStatusChangeEvent event) {
            final AbstractDiagram diagram = this.editor.getEditorInput().getDiagram();
            
            // Re enter the UI thread
            swtAsyncExec(() -> {
                if (this.editor.isDisposed()) {
                    return;
                }
            
                // Some elements were deleted: check for validity of the diagram.
                if (diagram == null) {
                    this.editor.showPalette(false);
                } else if (!diagram.isValid()) {
                    // The diagram in no longer valid, close the editor
                    // use the PartService to close this editor
                    // Re enter the UI thread
                    this.editor.partService.hidePart(this.editor.getPart(), true);
                } else {
                    this.editor.modifIndicator.update(this.editor.getPart(), diagram);
                    this.editor.showPalette(diagram.isModifiable());
                }
            });
            
        }

        @objid ("52f15bf9-523e-463d-b4bb-bcb6bed6b8c6")
        private boolean stereotypesChanged(final List<String> newStereotypes) {
            return !this.referenceStereotypes.equals(newStereotypes);
        }

        /**
         * Execute the runnable asynchronously in the SWT thread unless no SWT control for the editor exist.
         * @param r the code to execute in the SWT thread.
         */
        @objid ("9595b16a-d5da-4d4b-ac39-f0daa0c16fb7")
        private void swtAsyncExec(final Runnable r) {
            java.util.Optional.ofNullable(this.editor.getGraphicalViewer())
                    .map(GraphicalViewer::getControl)
                    .map(Control::getDisplay)
                    .ifPresent(d -> d.asyncExec(r));
            
        }

    }

}
