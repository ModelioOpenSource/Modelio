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
package org.modelio.model.browser.view.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.gproject.core.IGProject;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.model.browser.view.BrowserView;
import org.modelio.model.browser.view.IElementNameEditor;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.core.metamodel.MetamodelExtensionPoint;
import org.modelio.platform.core.picking.IPickingSession;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.model.ui.swt.cell.ElementNameCellModifier;
import org.modelio.platform.model.ui.swt.dnd.MObjectViewerDragProvider;
import org.modelio.platform.model.ui.swt.dnd.MObjectViewerDropListener;
import org.modelio.platform.model.ui.swt.edition.EditorActivationStrategy;
import org.modelio.platform.model.ui.swt.images.ElementDecoratedStyledLabelProvider;
import org.modelio.platform.model.ui.swt.images.IModelioElementLabelProvider;
import org.modelio.platform.model.ui.swt.labelprovider.BrowserLabelProvider;
import org.modelio.platform.ui.gef.SharedCursors2;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;

/**
 * Provides reusable browser panels
 */
@objid ("27fb4d4e-d01b-11e1-9020-002564c97630")
public class ModelBrowserPanelProvider implements IPanelProvider, IElementNameEditor {
    @objid ("1fc49986-1de3-11e2-bcbe-002564c97630")
    private EditorActivationStrategy activationSupport;

    @objid ("72459257-4540-11e2-aeb7-002564c97630")
    private BrowserLabelProvider baseProvider;

    @objid ("57ccc2d6-d023-11e1-9020-002564c97630")
    private BrowserContentProvider contentProvider;

    @objid ("bee44d7c-9a45-485c-8c1e-cffcb3010cbd")
    private MObjectViewerDragProvider dragListener;

    @objid ("5940c59f-a6da-49d2-b425-7929cbe064e3")
    private MObjectViewerDropListener dropListener;

    @objid ("1fc49985-1de3-11e2-bcbe-002564c97630")
    private ICoreSession modelingSession;

    @objid ("5cd1f2a3-7fdc-4a10-acf1-4f511275c23e")
    private ElementNameCellModifier nameModifier;

    @objid ("345ab100-bede-11e1-b430-001ec947c8cc")
    private TreeViewer treeViewer;

    @objid ("2c2102be-2d53-405d-845c-bc412f05c9c5")
    @Inject
    @Optional
    private EContextService contextService;

    @objid ("cd691306-66ed-4485-98fc-950abbd7bee4")
    private final MetamodelExtensionPoint<ITreeContentProvider> contentProviderExtensions = new MetamodelExtensionPoint<>(BrowserView.CONTENTPROVIDER_EXTENSION_POINT_ID);

    @objid ("2b24c0dc-03c7-4eca-b9bb-1257c354e803")
    private final MetamodelExtensionPoint<IModelioElementLabelProvider> labelProviderExtensions = new MetamodelExtensionPoint<>(BrowserView.LABEL_PROVIDER_EXTENSION_POINT_ID);

    @objid ("9041c545-00c4-4e0b-a486-a42bd1b2e73f")
    @Inject
    @Optional
    private EMenuService menuService;

    @objid ("5323daf7-ce29-436c-b972-509a6a4398c1")
    @Inject
    @Optional
    private ESelectionService selectionService;

    @objid ("84ff6e34-7866-4a0d-8b2e-daee22d4ad53")
    @Inject
    @Optional
    private IActivationService activationService;

    @objid ("41feb58f-bc4b-4562-9f3d-4f925541588a")
    private BrowserPickingManager pickingManager;

    @objid ("23ed581b-c8b7-43bd-a5d5-b3d7de6e4cdf")
    @Inject
    @Optional
    private EPartService partService;

    @objid ("0749e97a-43e1-450d-b89d-d17b8032cc1b")
    @Inject
    @Optional
    private MPart mpart;

    /**
     * Makes this view editable. <code>modelingSession</code> is mandatory otherwise edition cannot be supported. To deactivate edition, call <code>activateEdition(null)</code>
     * @param newModelingSession the current edited modeling session.
     */
    @objid ("1fc49987-1de3-11e2-bcbe-002564c97630")
    public void activateEdition(ICoreSession newModelingSession) {
        this.modelingSession = newModelingSession;
        if (newModelingSession != null) {
            for (MMetamodelFragment fragment : this.modelingSession.getMetamodel().getFragments()) {
                ITreeContentProvider subContentProvider = this.contentProviderExtensions.get(fragment);
                if (subContentProvider != null) {
                    this.contentProvider.registerExtension(fragment, subContentProvider);
                }
        
                IModelioElementLabelProvider subLabelProvider = this.labelProviderExtensions.get(fragment);
                if (subLabelProvider != null) {
                    this.baseProvider.registerExtension(fragment.getName(), subLabelProvider);
                }
            }
        }
        
        if (this.treeViewer != null) {
            this.nameModifier = new ElementNameCellModifier();
            this.treeViewer.setCellModifier(this.nameModifier);
        
            if (this.dragListener == null) {
                this.dragListener = new MObjectViewerDragProvider(this.treeViewer);
                this.treeViewer.addDragSupport(DND.DROP_MOVE | DND.DROP_COPY, new Transfer[] { ModelElementTransfer.getInstance() }, this.dragListener);
            }
        
            if (this.dropListener == null) {
                this.dropListener = new MObjectViewerDropListener(this.treeViewer);
                this.treeViewer.addDropSupport(DND.DROP_MOVE | DND.DROP_COPY, new Transfer[] { ModelElementTransfer.getInstance() }, this.dropListener);
            }
        
            this.treeViewer.refresh(true);
        }
        
    }

    /**
     * Called to create the view and initialize it.
     * @return the browser tree viewer.
     */
    @objid ("57ccc2d7-d023-11e1-9020-002564c97630")
    @Override
    public TreeViewer createPanel(Composite parent) {
        this.treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        this.treeViewer.setUseHashlookup(true);
        
        configureTreeviewer();
        
        initEditor();
        
        this.treeViewer.setInput(null);
        return this.treeViewer;
    }

    @objid ("c2a435c5-2910-4cf3-b726-ec2f40e42623")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("a501c525-0679-447f-a363-4f381df2656d")
    @Override
    public String getHelpTopic() {
        return null;
    }

    /**
     * Get the current element displayed by the view.
     * @return the model element whose content is listed in the model tree. May be null.
     */
    @objid ("57ccc2e3-d023-11e1-9020-002564c97630")
    @Override
    public Object getInput() {
        return this.treeViewer.getInput();
    }

    @objid ("57ccc2ee-d023-11e1-9020-002564c97630")
    public List<Object> getLocalRoots() {
        return new ArrayList<>(this.contentProvider.getLocalRoots());
    }

    @objid ("57ccc2de-d023-11e1-9020-002564c97630")
    @Override
    public Tree getPanel() {
        return (Tree) this.treeViewer.getControl();
    }

    @objid ("36b61724-7ca1-4fa6-9e92-1e77111502b0")
    @Override
    public boolean isRelevantFor(Object obj) {
        return true;
    }

    @objid ("72459262-4540-11e2-aeb7-002564c97630")
    public boolean isShowModuleFragments() {
        return this.contentProvider.isShowModuleFragments();
    }

    @objid ("72459269-4540-11e2-aeb7-002564c97630")
    public boolean isShowProjects() {
        return this.contentProvider.isShowProjects();
    }

    /**
     * Set the current element displayed by the view.
     * @param input the model element whose content is listed in the model tree panel. May be null.
     */
    @objid ("57ccc2e9-d023-11e1-9020-002564c97630")
    @Override
    public void setInput(Object input) {
        this.treeViewer.setInput(input);
        if (input == null) {
            this.contentProvider.setLocalRoots(Collections.emptyList());
        } else if (input instanceof IGProject) {
            activateEdition(((IGProject) input).getSession());
        }
        
    }

    @objid ("002b00ca-78e3-107d-a016-001ec947cd2a")
    public void setLabelProvider(IBaseLabelProvider labelProvider) {
        this.treeViewer.setLabelProvider(labelProvider);
    }

    @objid ("57cf2428-d023-11e1-9020-002564c97630")
    public void setLocalRoots(List<Object> localRoots) {
        this.contentProvider.setLocalRoots(localRoots);
    }

    @objid ("aa8b3a26-16e1-11e2-aa0d-002564c97630")
    public void setSelection(List<?> elements) {
        final IStructuredSelection treeSelection = new StructuredSelection(elements);
        this.treeViewer.setSelection(treeSelection);
        
        // Check each asked element is displayed in the browser.
        // For each element not displayed in the browser, look for a parent element to select instead.
        List<?> requestedSel = elements;
        List<Object> obtainedSel = SelectionHelper.toList(this.treeViewer.getStructuredSelection(), Object.class);
        int nbtry = 0; // shield against infinite loops
        while (!obtainedSel.containsAll(requestedSel) && nbtry++ < 10) {
            List<Object> req2 = new ArrayList<>(requestedSel.size());
            for (Object mObject : requestedSel) {
                if (obtainedSel.contains(mObject)) {
                    req2.add(mObject);
                } else {
                    Object parent = ((ITreeContentProvider) this.treeViewer.getContentProvider()).getParent(mObject);
                    if (parent == null && mObject instanceof MObject) {
                        parent = ((MObject) mObject).getCompositionOwner();
                    }
                    if (parent != null) {
                        req2.add(parent);
                    }
                }
            }
        
            this.treeViewer.setSelection(new StructuredSelection(req2));
            requestedSel = req2;
            obtainedSel = SelectionHelper.toList(this.treeViewer.getStructuredSelection(), Object.class);
        }
        
        if (this.partService != null) {
            // Make the browser view active to trigger its "selection provider"
            this.partService.showPart(this.mpart, PartState.ACTIVATE);
        }
        
    }

    @objid ("72459266-4540-11e2-aeb7-002564c97630")
    public void setShowModuleFragments(boolean showModuleFragments) {
        this.contentProvider.setShowModuleFragments(showModuleFragments);
        this.treeViewer.refresh(true);
        
    }

    @objid ("19e55b6a-cb92-4f35-8714-626d958de77c")
    private void configureTreeviewer() {
        this.baseProvider = new BrowserLabelProvider();
        
        // Keep configuration before switching content provider
        if (this.contentProvider != null) {
            boolean showModuleFragments = this.contentProvider.isShowModuleFragments();
            List<Object> localRoots = this.contentProvider.getLocalRoots();
        
            this.contentProvider = new BrowserContentProvider();
            if (this.modelingSession != null) {
                for (MMetamodelFragment fragment : this.modelingSession.getMetamodel().getFragments()) {
                    ITreeContentProvider subContentProvider = this.contentProviderExtensions.get(fragment);
                    if (subContentProvider != null) {
                        this.contentProvider.registerExtension(fragment, subContentProvider);
                    }
        
                    IModelioElementLabelProvider subLabelProvider = this.labelProviderExtensions.get(fragment);
                    if (subLabelProvider != null) {
                        this.baseProvider.registerExtension(fragment.getName(), subLabelProvider);
                    }
                }
            }
        
            // Restore configuration
            this.contentProvider.setShowModuleFragments(showModuleFragments);
            this.contentProvider.setLocalRoots(localRoots);
        } else {
            this.contentProvider = new BrowserContentProvider();
        }
        
        this.treeViewer.setContentProvider(this.contentProvider);
        
        this.treeViewer.setLabelProvider(new ElementDecoratedStyledLabelProvider(this.baseProvider, true, true));
        
        // Add tooltip support
        ColumnViewerToolTipSupport.enableFor(this.treeViewer);
        
        // Add the contextual menu
        if (this.menuService != null) {
            this.menuService.registerContextMenu(this.treeViewer.getTree(), BrowserView.POPUP_ID);
        }
        
        // Add the selection provider
        this.treeViewer.addSelectionChangedListener(
                event -> onSelectionChanged(event.getSelection()));
        
        // Edit object on double click
        this.treeViewer.addDoubleClickListener(
                event -> onDoubleClickActivation(event.getSelection()));
        
        // Navigate to target on <ctrl>+<alt>+click
        this.treeViewer.getTree().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                final int keyFlags = SWT.CONTROL | SWT.ALT;
                if ((e.button == 1) && ((e.stateMask & keyFlags) == keyFlags)) {
                    final TreeItem item = ((Tree) e.widget).getItem(new Point(e.x, e.y));
                    onNavigateToTarget(item.getData());
                }
            }
        });
        
    }

    @objid ("1fc4998b-1de3-11e2-bcbe-002564c97630")
    private void initEditor() {
        // Define cell editor
        final TextCellEditor[] cellEditors = new TextCellEditor[1];
        final TextCellEditor editor = new TextCellEditor(this.treeViewer.getTree(), SWT.NONE) {
            private Collection<String> activeContexts;
        
            @SuppressWarnings ("synthetic-access")
            @Override
            public void activate() {
                // We must deactivate the active contexts during the edition, to
                // avoid the editor's shortcuts to be triggered when entering an
                // element's name...
                // Store those contexts for further reactivation
                Collection<String> activeContextIds = getContextService() != null ? getContextService().getActiveContextIds() : null;
                this.activeContexts = activeContextIds != null ? new ArrayList<>(activeContextIds) : new ArrayList<>();
                for (final String contextId : this.activeContexts) {
                    getContextService().deactivateContext(contextId);
                }
                ModelBrowserPanelProvider.this.contentProvider.isEditorActive = true;
        
                super.activate();
            }
        
            @SuppressWarnings ("synthetic-access")
            @Override
            public void deactivate() {
                if (this.activeContexts != null) {
                    // Restore previously deactivated contexts
                    for (final String contextId : this.activeContexts) {
                        if (getContextService() != null) {
                            getContextService().activateContext(contextId);
                        }
                    }
                    ModelBrowserPanelProvider.this.contentProvider.isEditorActive = false;
                    ModelBrowserPanelProvider.this.treeViewer.refresh(true);
        
                    this.activeContexts = null;
                }
        
                super.deactivate();
            }
        };
        
        editor.getControl().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                // Nothing to do.
            }
        
            @Override
            public void keyReleased(KeyEvent event) {
                if (((event.stateMask &= SWT.MOD1) != 0) && (event.keyCode == 'a')) {
                    final Object source = event.getSource();
                    if (source instanceof Text) {
                        final Text text = (Text) source;
                        text.selectAll();
                    }
                }
            }
        });
        cellEditors[0] = editor;
        this.treeViewer.setCellEditors(cellEditors);
        
        // Define column properties
        final String[] properties = new String[1];
        properties[0] = "name";
        this.treeViewer.setColumnProperties(properties);
        
        // Define editor activation strategy
        this.activationSupport = new EditorActivationStrategy(this.treeViewer);
        
        TreeViewerEditor.create(this.treeViewer, null, this.activationSupport, ColumnViewerEditor.TABBING_HORIZONTAL
                | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR | ColumnViewerEditor.TABBING_VERTICAL
                | ColumnViewerEditor.KEYBOARD_ACTIVATION);
        
    }

    @objid ("9ce5023e-92ec-4172-9125-1f7321fa2d8b")
    public EContextService getContextService() {
        return this.contextService;
    }

    @objid ("7835daf1-2647-42af-836a-30675232602d")
    public void setContextService(EContextService contextService) {
        this.contextService = contextService;
    }

    @objid ("ba201904-4cfe-46d4-8f4e-87bae29bb8df")
    public ITreeContentProvider getBrowserContentProvider(String fragmentName) {
        if (this.modelingSession != null) {
            for (ISmMetamodelFragment fragment : this.modelingSession.getMetamodel().getFragments()) {
                if (fragment.getName().equals(fragmentName)) {
                    return this.contentProviderExtensions.get(fragment);
                }
            }
        }
        return null;
    }

    @objid ("99e8cc75-ec07-49a3-999c-e07a8e0f9fdb")
    public IModelioElementLabelProvider getLabelContentProvider(String fragmentName) {
        for (ISmMetamodelFragment fragment : this.modelingSession.getMetamodel().getFragments()) {
            if (fragment.getName().equals(fragmentName)) {
                return this.labelProviderExtensions.get(fragment);
            }
        }
        return null;
    }

    /**
     * Called when the selection change in the tree viewer => propagates selection to application
     */
    @objid ("c0082f8b-c3a5-4836-b4f3-7b6d8db7c4c6")
    private void onSelectionChanged(ISelection selection) {
        if (this.selectionService != null) {
            this.selectionService.setSelection(selection);
        }
        
    }

    /**
     * Enter edition mode for 'elementToEdit'. Equivalent to F2 behavior on the element.
     */
    @objid ("987b0a10-d989-4914-a0d3-e33a83333da5")
    @Override
    public void edit(MObject elementToEdit) {
        if (this.treeViewer != null) {
            this.treeViewer.expandToLevel(elementToEdit, 0);
            this.treeViewer.editElement(elementToEdit, 0);
        }
        
    }

    /**
     * Called when the user double-click an element in the tree viewer => fire an 'activate Modelio event' to the application
     */
    @objid ("2bb1baf2-d4d7-4792-a890-4385531fa7c9")
    private void onDoubleClickActivation(ISelection selection) {
        if (this.activationService != null
                && SelectionHelper.containsOnly(selection, MObject.class)
                && (SelectionHelper.count(selection, MObject.class) == 1)) {
            final MObject selectedObject = SelectionHelper.getFirst(selection, MObject.class);
            this.activationService.activateMObject(selectedObject);
        }
        
    }

    @objid ("30f53473-4d1c-4d77-924a-d1336380e42b")
    private void onNavigateToTarget(final Object obj) {
        if (obj instanceof MObject) {
            final MObject mObj = (MObject) obj;
        
            // Compute 'smart' selection target
            final MObject target;
            MExpert expert = mObj.getMClass().getMetamodel().getMExpert();
            if (expert.isLink(mObj.getMClass())) {
                target = expert.getTarget(mObj);
            } else {
                if (mObj instanceof Attribute) {
                    target = ((Attribute) mObj).getType();
                } else if (mObj instanceof Parameter) {
                    target = ((Parameter) mObj).getType();
                } else if (mObj instanceof Instance) {
                    target = ((Instance) mObj).getBase();
                } else if (mObj instanceof AttributeLink) {
                    final AttributeLink attributeLink = (AttributeLink) mObj;
                    target = attributeLink.getBase();
                } else {
                    target = null;
                }
            }
        
            // Change selection in the view in display thread
            if (target != null) {
                Display.getDefault().asyncExec(() -> setSelection(Arrays.asList(target)));
            }
        }
        
    }

    @objid ("9f61fe2c-05ba-457b-802f-c6926cc192be")
    public void collapseAll() {
        if (this.treeViewer != null && !this.treeViewer.getTree().isDisposed()) {
            this.treeViewer.collapseAll();
        }
        
    }

    @objid ("f1151833-4e5e-4266-a973-3d2088422740")
    public void refresh() {
        if (this.treeViewer != null && !this.treeViewer.getTree().isDisposed()) {
            this.treeViewer.refresh(true);
        }
        
    }

    @objid ("be9b5597-f318-4e4d-b127-8e86188b98d5")
    @Inject
    @Optional
    void onPickingStart(@UIEventTopic (ModelioEventTopics.PICKING_START) final IPickingSession session) {
        if (this.pickingManager == null) {
            this.pickingManager = new BrowserPickingManager(this, session);
            this.pickingManager.beginPicking();
        }
        
    }

    @objid ("93e50294-3605-4f74-977e-83169f3a95dd")
    @Inject
    @Optional
    void onPickingStop(@UIEventTopic (ModelioEventTopics.PICKING_STOP) final IPickingSession session) {
        if (this.pickingManager != null) {
            this.pickingManager.endPicking();
            this.pickingManager = null;
        }
        
    }

    @objid ("e6118f56-0d6b-4200-9df0-a3e2a819420d")
    @Inject
    @Optional
    void onNavigateElement(@UIEventTopic (ModelioEventTopics.NAVIGATE_ELEMENT) final List<MObject> elements) {
        setSelection(elements);
    }

    @objid ("94cfdec0-91a7-4763-9fd4-a76bac34cc05")
    @Inject
    @Optional
    void onNavigateElement(@UIEventTopic (ModelioEventTopics.NAVIGATE_ELEMENT) final MObject element) {
        setSelection(Arrays.asList(element));
    }

    /**
     * This class handles picking. When picking starts it adds itself as mouse listener (mouse, drack and move) to the tree. When picking is done, it removes itself from mouse listening.
     */
    @objid ("4c0a8592-1fe3-4b0d-a4c0-0294cec53fca")
    private static class BrowserPickingManager implements MouseListener, MouseTrackListener, MouseMoveListener {
        @objid ("d19c989e-6b8e-4ddb-90e1-697789d05d2a")
        private ModelBrowserPanelProvider view;

        @objid ("d6fde0dc-54fc-4ba1-af1e-eb644e653828")
        private Cursor defaultCursor;

        @objid ("1a86b3b3-2a15-41ed-80cf-360d92702d77")
        private IPickingSession pickingSession;

        @objid ("4d09fad6-cfda-433b-bbc6-a70449cb3b49")
        private TreeItem[] pickingStartSelection;

        @objid ("733d45fd-d4f6-4256-8e29-5efa0bbe935b")
        public  BrowserPickingManager(ModelBrowserPanelProvider view, IPickingSession pickingSession) {
            this.view = view;
            this.pickingSession = pickingSession;
            this.defaultCursor = view.getPanel().getCursor();
            
        }

        @objid ("80e1c5d4-e957-4a37-bedb-573f101ea356")
        @Override
        public void mouseDoubleClick(MouseEvent e) {
            // Nothing to do
        }

        @objid ("4e1ac8ba-70f5-40bb-8867-1f9097b37440")
        @Override
        public void mouseDown(MouseEvent e) {
            // Nothing to do
        }

        @objid ("6817eacb-73ad-403c-a177-6ee44e567a54")
        private static Element getPickedElement(MouseEvent e) {
            Object source = e.getSource();
            
            if (source instanceof Tree) {
                Tree tree = (Tree) source;
                TreeItem item = tree.getItem(new Point(e.x, e.y));
            
                if (item != null) {
                    Object data = item.getData();
                    if (data instanceof Element) {
                        return (Element) data;
                    }
                }
            }
            return null;
        }

        @objid ("1814228f-7c6a-4383-90dc-65e2f690d4fb")
        @Override
        public void mouseUp(MouseEvent e) {
            if (e.button == 1) {
                Element selectedElement = BrowserPickingManager.getPickedElement(e);
                if (selectedElement != null && this.pickingSession.hover(selectedElement)) {
                    this.pickingSession.pick(selectedElement);
                }
            }
            
        }

        @objid ("15f3ab91-6f53-482b-a0b4-f2d88b3a2048")
        @Override
        public void mouseEnter(MouseEvent e) {
            this.view.getPanel().setCursor(SharedCursors2.CURSOR_PICKING);
        }

        @objid ("67b8de2b-bbd9-43b4-a296-ff91243e1909")
        @Override
        public void mouseExit(MouseEvent e) {
            this.view.getPanel().setCursor(this.defaultCursor);
        }

        @objid ("08813c61-0194-43ce-9076-4162d162f76a")
        @Override
        public void mouseHover(MouseEvent e) {
            // Nothing to do
        }

        @objid ("7b7ec4d4-0d05-4dac-82e9-58733c0ee9bc")
        public void beginPicking() {
            this.view.getPanel().addMouseListener(this);
            this.view.getPanel().addMouseTrackListener(this);
            this.view.getPanel().addMouseMoveListener(this);
            
            // Store the current selection to restore it when the picking ends
            this.pickingStartSelection = this.view.getPanel().getSelection();
            
        }

        @objid ("d7ba0c1a-ee8f-402d-b192-cb7c76e603ad")
        public void endPicking() {
            // Deactivate picking:
            this.view.getPanel().removeMouseListener(this);
            this.view.getPanel().removeMouseTrackListener(this);
            this.view.getPanel().removeMouseMoveListener(this);
            
            this.view.getPanel().setCursor(this.defaultCursor);
            
            // Restore old selection
            this.view.getPanel().setSelection(this.pickingStartSelection);
            this.pickingStartSelection = null;
            
        }

        @objid ("9bfe623c-c731-41cc-aa4a-102f1b4342c7")
        @Override
        public void mouseMove(MouseEvent e) {
            Element element = null;
            
            Object source = e.getSource();
            
            if (source instanceof Tree) {
                Tree tree = (Tree) source;
                TreeItem item = tree.getItem(new Point(e.x, e.y));
            
                if (item != null) {
                    Object data = item.getData();
                    if (data instanceof Element) {
                        element = (Element) data;
                    }
                }
            }
            
            if (element != null) {
                if (this.pickingSession.hover(element)) {
                    this.view.getPanel().setCursor(SharedCursors2.CURSOR_PICKING_YES);
                } else {
                    this.view.getPanel().setCursor(SharedCursors2.CURSOR_PICKING_NO);
                }
            } else {
                this.view.getPanel().setCursor(SharedCursors2.CURSOR_PICKING);
            }
            
        }

    }

}
