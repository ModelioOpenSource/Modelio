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

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.draw2d.PrintFigureOperation;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MItem;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.print.PrintGraphicalViewerOperation;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Composite;
import org.modelio.linkeditor.gef.background.BackgroundEditPart;
import org.modelio.linkeditor.gef.edge.EdgeEditPart;
import org.modelio.linkeditor.gef.tools.PanSelectionTool;
import org.modelio.linkeditor.gef.tools.PickingSelectionTool;
import org.modelio.linkeditor.panel.ILinkEditorConfiguration.Orientation;
import org.modelio.linkeditor.panel.model.BackgroundModel;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.core.picking.IPickingSession;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.UIThreadRunner;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Link editor panel controller.
 * <p>
 * Must be instantiated with E4 injection factory.
 */
@objid ("230a80cc-6c13-4025-aa0b-17d2baa26dae")
class LinkEditorPanelController {
    @objid ("1c56266f-8001-45a4-89a1-c88084ffc39b")
    private boolean editMode;

    @objid ("3f643ca3-7f3e-40af-8fe0-60fcf686040b")
    private final IModelioNavigationService navigationService;

    @objid ("913df9dc-ab5b-430d-8ef6-7cf6b9945f2f")
    private final IProjectService projectService;

    @objid ("0eccb3d9-e22d-4f18-9ac6-560b7732d118")
    private final EMenuService menuService;

    @objid ("f5e03fdd-296e-40c5-b725-00fb89ef5d32")
    private LinkEditorPanelUi ui;

    /**
     * The configuration data (read-only) of the FLE.
     * The controller does not manage the configuration data (this is carried out by the ILinkEditor (panel provider) and cannot modify it.
     */
    @objid ("06af9fa7-51de-495d-afa0-6c7998fe89d3")
    private final ILinkEditorConfiguration config;

    @objid ("fcf9c77d-1ac2-430a-80ca-157974c19613")
    private final BackgroundModel backgroundModel = new BackgroundModel();

    @objid ("7a3826b3-93d7-48d3-88ef-3cb9ae90d908")
    private final IEclipseContext eclipseContext;

    @objid ("5b08379a-8dd2-4367-b096-87c3ff5feeb6")
    private final ESelectionService selectionService;

    @objid ("7e8618f7-7d11-4daa-8071-73b1bc317eb6")
    private final MItem editModeToobarButton;

    @objid ("e10d573c-b307-4f90-a496-1a4887676421")
    private IStructuredSelection deferredAppSelection;

    @objid ("e96e7950-2c5a-4703-8f06-8eeb8d0e7d81")
    private AtomicBoolean refreshScheduled = new AtomicBoolean(false);

    @objid ("f7ce722c-6527-4414-a51f-410d1dc1836c")
    LinkEditorPanelUi createUi(Composite parent) {
        this.ui = new LinkEditorPanelUi(parent, this);
        this.ui.setDoubleClickListener(() -> setEditMode(!isEditMode()));
        onModelChanged();
        return this.ui;
    }

    @objid ("41ecd139-1ce5-4adb-9a4a-53110edceba1")
    void setInput(MObject mObj) {
        this.backgroundModel.setInput(mObj);
        refreshView();
        
    }

    @objid ("ff79023c-828c-48c1-b38e-7944f6d3d42f")
    IModelioNavigationService getNavigationService() {
        return this.navigationService;
    }

    @objid ("a7d87b32-1608-46bb-8466-15716b84bb40")
    IProjectService getProjectService() {
        return this.projectService;
    }

    @objid ("c484783b-99fe-4dac-ab02-1db92e5123f0")
    EMenuService getMenuService() {
        return this.menuService;
    }

    @objid ("05bc28d3-3cf3-45ea-92bd-f148db429451")
    void startPicking(IPickingSession session) {
        this.ui.getComposite().getDisplay().asyncExec(() -> this.ui.setActiveTool(new PickingSelectionTool(session)));
    }

    @objid ("f31fa1d2-8055-44b7-a8c3-2545cda47ded")
    void stopPicking(IPickingSession session) {
        this.ui.getComposite().getDisplay().asyncExec(() -> this.ui.setActiveTool(new PanSelectionTool(this.navigationService)));
    }

    /**
     * Called by the panel when the model has been modified (IModelChangeListener event)
     */
    @objid ("3669763f-b282-4bab-ad12-22884f20891c")
    void onModelChanged() {
        this.backgroundModel.rebuild();
        refreshView();
        
    }

    @objid ("00f6eb1b-9fe3-4f16-bdef-7b3b5e253931")
    public double getZoomLevel() {
        ZoomManager zm = ((ScalableFreeformRootEditPart) (this.ui.getGraphicalViewer().getRootEditPart())).getZoomManager();
        return zm.getZoom();
    }

    @objid ("2da14e93-802c-44f2-a109-a4670a2f6412")
    public void setZoomLevel(double level) {
        ZoomManager zm = ((ScalableFreeformRootEditPart) (this.ui.getGraphicalViewer().getRootEditPart())).getZoomManager();
        zm.setZoom(level);
        
    }

    @objid ("25e01036-44e3-4104-83f6-a2cfc0a88f0f")
    public void print(PrinterData data) {
        PrintGraphicalViewerOperation operation = new PrintGraphicalViewerOperation(new Printer(data), this.ui.getGraphicalViewer());
        // set the print mode to TILE
        operation.setPrintMode(PrintFigureOperation.TILE);
        // run the print operation
        operation.run("Printing diagram");
        
    }

    @objid ("3bdeafae-6731-4f54-882d-4c6ea9946487")
    public Image getImage() {
        return new ImageBuilder().makeImage(this.ui.getGraphicalViewer().getRootEditPart());
    }

    @objid ("90855fba-fdad-45c2-af4a-bc52b5aee68e")
    void setLayoutOrientation(Orientation orientation) {
        Orientation currentOrientation = (Orientation) this.ui.getGraphicalViewer().getProperty(BackgroundEditPart.LAYOUT_ORIENTATION);
        
        if (currentOrientation == orientation) {
            return;
        }
        
        switch (orientation) {
        case Horizontal:
        case Vertical:
            this.ui.getGraphicalViewer().setProperty(BackgroundEditPart.LAYOUT_ORIENTATION, orientation);
            break;
        case Auto:
            // if requested mode is auto ; use the config recommended orientation
            Orientation recommended = this.config.getLayoutOrientation();
            if (recommended == Orientation.Auto) {
                recommended = Orientation.Horizontal;
            }
            this.ui.getGraphicalViewer().setProperty(BackgroundEditPart.LAYOUT_ORIENTATION, recommended);
        }
        
    }

    @objid ("b4ddf7ff-17be-42e1-829d-098667f82cdd")
    public void setEditMode(boolean onOff) {
        if (onOff != this.editMode) {
            this.editMode = onOff;
            this.ui.getGraphicalViewer().setProperty(BackgroundEditPart.EDIT_MODE, onOff);
            this.editModeToobarButton.setSelected(onOff);
        
            if (!onOff) {
                // Reset the link editor selection to the current selection in other views.
                if (this.deferredAppSelection != null) {
                    onAppSelectionChange(this.deferredAppSelection);
                    this.deferredAppSelection = null;
                }
            }
        }
        
    }

    @objid ("bb682004-28e3-4b01-908b-d4cfec7dced3")
    public IEclipseContext getEclipseContext() {
        return this.eclipseContext;
    }

    /**
     * Called when the selection changes in the graphical viewer.
     */
    @objid ("7a41f43e-7660-4db2-a901-1844ce1d95e5")
    void onEditorSelectionChanged(ISelection selection) {
        if (!SelectionHelper.containsOnly(selection, BackgroundEditPart.class)) {
            this.selectionService.setSelection(selection);
        }
        
    }

    @objid ("f563bd6a-f199-44bf-b747-59c89bb9511b")
    @Inject
    public  LinkEditorPanelController(IEclipseContext eclipseContext, ILinkEditorConfiguration config, MPart e4Part, EMenuService menuService, EModelService e4ModelService, ESelectionService selectionService, IProjectService projectService, IModelioNavigationService navigationService) {
        this.eclipseContext = eclipseContext;
        this.config = config;
        this.menuService = menuService;
        this.selectionService = selectionService;
        this.projectService = projectService;
        this.navigationService = navigationService;
        this.editModeToobarButton = Objects.requireNonNull(((MItem) e4ModelService.find("org.modelio.linkeditor.handledtoolitem.PinEditor", e4Part.getToolbar())));
        
    }

    /**
     * Called (by the ILinkEditor panel provider) when the configuration of the FLE has been modified.
     */
    @objid ("cce9379c-8ba3-43b8-ad7e-80ef6a656664")
    public void onConfigurationChanged() {
        // Set the layout orientation
        setLayoutOrientation(this.config.getLayoutOrientation());
        
        // Update the tree model (config options like left or right depths might have changed)
        this.backgroundModel.setConfiguration(this.config);
        
        refreshView();
        
    }

    /**
     * Called on selection change (at the application level)
     * @see org.modelio.linkeditor.others.edge.EdgeEditPart
     * @see org.modelio.linkeditor.gef.edge.EdgeEditPart
     * @see org.modelio.linkeditor.gef.edge.EdgeEditPart 
     */
    @objid ("4a040d81-19de-4e2e-b69a-68933757377f")
    @Inject
    @Optional
    private void onAppSelectionChange(@Named (IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        if (SelectionHelper.contains(selection, EdgeEditPart.class)) {
            return;
        }
        
        MObject mObj = SelectionHelper.getFirst(selection, MObject.class);
        if (mObj != null && !Objects.equals(mObj, getInput())) {
            // Change the current viewed element if view is not pinned.
            if (isEditMode()) {
                this.deferredAppSelection = selection;
            } else {
                setInput(mObj);
            }
        }
        
    }

    @objid ("91bd34bd-f013-4a34-9543-45eaa8e24305")
    public MObject getInput() {
        return this.backgroundModel.getInput();
    }

    @objid ("c467ad7a-76f5-411f-b390-b4ff912a7fbb")
    public boolean isEditMode() {
        return this.editMode;
    }

    @objid ("33a1dafa-5b97-47bb-a342-e99e7442b392")
    private void refreshView() {
        if (this.ui==null || this.ui.getComposite().isDisposed())
            return;
        
        if (! this.refreshScheduled.compareAndSet(false, true))
            return;
        
        UIThreadRunner.asynExec(this.ui.getComposite(), () -> {
            this.refreshScheduled.set(false);
        
            if (this.ui != null) {
                // When input element dies, cancel edit mode
                MObject input = this.backgroundModel.getInput();
                if (isEditMode() && input != null && !input.isValid()) {
                    setEditMode(false);
                }
        
                this.ui.setInput(this.backgroundModel);
        
                // Notifies the view to display new content
                this.backgroundModel.fireContentChanged();
            }
        });
        
    }

}
