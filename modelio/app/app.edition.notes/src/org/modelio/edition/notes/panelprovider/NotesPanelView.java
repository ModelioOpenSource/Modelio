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
package org.modelio.edition.notes.panelprovider;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.edition.notes.panelprovider.actions.CopyAction;
import org.modelio.edition.notes.panelprovider.actions.CutAction;
import org.modelio.edition.notes.panelprovider.actions.HtmlConvertAction;
import org.modelio.edition.notes.panelprovider.actions.JyExecAction;
import org.modelio.edition.notes.panelprovider.actions.PasteAction;
import org.modelio.edition.notes.panelprovider.actions.RemoveAnnotationAction;
import org.modelio.edition.notes.panelprovider.data.NoteViewContentPanel;
import org.modelio.edition.notes.panelprovider.tree.NoteViewTreeContentProvider;
import org.modelio.edition.notes.panelprovider.tree.NoteViewTreeLabelProvider;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.model.ui.swt.dnd.MObjectViewerDragProvider;
import org.modelio.platform.model.ui.swt.images.ElementDecoratedStyledLabelProvider;
import org.modelio.platform.ui.panel.IPanelProvider;

/**
 * The notes panel composite features:
 * <ul>
 * <li>a toolbar providing commands like add/remove notes and constraints or
 * switch layout</li>
 * <li>a tree listing the notes and constraints</li>
 * <li>an edition area where notes and constraints text can be edited</li>
 * </ul>
 * This class implements only a view managed by the NotesPanelController
 * 
 * @author phv
 */
@objid ("e6590e8f-03d3-4d35-bed4-320ca6af581c")
public class NotesPanelView {
    /**
     * Minimum and maxim H/W ratio
     */
    @objid ("23d2b682-b03b-44cf-af4a-05e28fb03783")
    private static final float HWMIN = (float) 0.6;

    @objid ("4a4160d9-b25f-4467-8be6-74a7001450de")
    private static final float HWMAX = (float) 0.65;

    @objid ("0b4f05cd-df52-4624-8f04-3df159fc14e5")
    private String lastSelectedItemKey;

    @objid ("bea7a20e-3d98-45a1-992e-ad88afa0c52e")
    private final NotesPanelController controller;

    @objid ("e9ff8c42-2eda-4cfa-8ced-6fa01e9c7f3c")
    private IPanelProvider data;

    @objid ("988f4387-1e54-480f-b6d6-4f326a9f61b8")
    private Composite parentComposite;

    @objid ("e78db39a-39f9-46ba-aba7-ebc001bb42e5")
    private SashForm shform;

    @objid ("3e9a94a5-df52-400e-a0c5-80a7d4bb271d")
    private TreeViewer tree;

    @objid ("01771f47-3073-4525-a5eb-ccf470a38359")
    private Composite top;

    @objid ("f2e44a9f-76dc-4c14-b9bb-cb0e45697781")
    private ControlListener layoutChangeListener;

    @objid ("5817cf85-23ea-4f8e-90a7-16aaa3abc6e6")
    private DragSourceListener dragListener;

    @objid ("dc02aab4-f6c0-4685-ac33-85307c70bd56")
    private NotesPanelToolbar toolBar;

    /**
     * C'tor
     * @param controller the controller
     */
    @objid ("066e8175-289d-4376-9f2b-b91b7d07c642")
    public  NotesPanelView(NotesPanelController controller) {
        this.controller = controller;
    }

    /**
     * Build the view GUI.
     * @param parent the parent SWt Composite
     * @return the created GUI
     */
    @objid ("a9ce8eff-250a-43d0-99a8-54397d22c5df")
    public Composite createContents(Composite parent) {
        this.parentComposite = parent;
        
        // Top level container: a Composite
        this.top = new Composite(parent, SWT.NONE);
        final GridLayout gl = new GridLayout(1, true);
        gl.marginBottom = gl.marginTop = gl.marginHeight = 0;
        gl.marginLeft = gl.marginRight = gl.marginWidth = 0;
        gl.horizontalSpacing = gl.verticalSpacing = 0;
        this.top.setLayout(gl);
        
        // The tool bar
        this.toolBar = new NotesPanelToolbar(this.controller);
        this.toolBar.createPanel(this.top);
        ((Composite) this.toolBar.getPanel()).setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, false));
        
        // The Shash form and its two areas
        this.shform = new SashForm(this.top, SWT.HORIZONTAL);
        this.shform.setLayout(new FillLayout());
        this.shform.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.tree = createNotesTree(this.shform);
        this.data = createNoteEditArea(this.shform);
        
        this.shform.setWeights(new int[] { 30, 70 });
        
        this.dragListener = new MObjectViewerDragProvider(getTreeViewer());
        this.tree.addDragSupport(DND.DROP_MOVE | DND.DROP_COPY, new Transfer[] { ModelElementTransfer.getInstance() },
                this.dragListener);
        
        // the tree popup menu
        initPopupMenu(this.tree);
        return this.top;
    }

    /**
     * Get the top level container of the view.
     * @return the top level container of the view.
     */
    @objid ("c4b5e473-444d-49c1-a826-5be9028ba4c4")
    public Composite getComposite() {
        return this.top;
    }

    /**
     * Set the layout to horizontal.
     */
    @objid ("17ca0035-2ab4-40fd-a03c-69bd5f074f4e")
    public void setHorizontalLayout() {
        this.shform.setOrientation(SWT.HORIZONTAL);
    }

    /**
     * Set the layout to vertical.
     */
    @objid ("fafc7965-dcc5-4a6d-ad16-1fd9a31cafac")
    public void setVerticalLayout() {
        this.shform.setOrientation(SWT.VERTICAL);
    }

    /**
     * Enable automatic horizontal/vertical layout change when resizing the
     * view.
     */
    @objid ("b2c44b06-0f61-4508-9092-198009fd1e25")
    public void enableAutoLayout() {
        this.layoutChangeListener = new LayoutChangeListener(this);
        this.parentComposite.addControlListener(this.layoutChangeListener);
        autoLayout();
        
    }

    /**
     * Disable automatic horizontal/vertical layout change when resizing the
     * view.
     */
    @objid ("d83d3274-6a25-4521-8c8b-25d3519a4391")
    public void disableAutoLayout() {
        if (this.layoutChangeListener != null) {
            this.parentComposite.removeControlListener(this.layoutChangeListener);
            this.layoutChangeListener = null;
        }
        
    }

    /**
     * Automatically choose horizontal or vertical layout depending on parent
     * composite size.
     */
    @objid ("2d9eabb7-be10-45a0-a6fe-a940ee6d26a5")
    private void autoLayout() {
        final float ratio = (float) this.parentComposite.getSize().y / (float) this.parentComposite.getSize().x;
        if (ratio < NotesPanelView.HWMIN) {
            setHorizontalLayout();
        } else if (ratio > NotesPanelView.HWMAX) {
            setVerticalLayout();
        }
        
    }

    /**
     * Set the edited model element.
     * @param e the element to edit.
     */
    @objid ("e5f119eb-12e4-4719-a6f6-4f6f573b240a")
    public void setInput(ModelElement e) {
        this.tree.setInput(e);
        this.toolBar.setInput(e);
        
    }

    /**
     * Set the selected annotation on the edited element.
     * @param select the annotation to select
     */
    @objid ("cc0bdef9-1f59-4609-bce7-0470e047471a")
    public void setSelected(ModelElement select) {
        if (select == null) {
            final ModelElement smartSelect = getSelectHint();
            this.data.setInput(smartSelect);
            this.toolBar.setInput(smartSelect);
            if (smartSelect != null) {
                this.tree.setSelection(new StructuredSelection(smartSelect), true);
            }
        } else {
            this.data.setInput(select);
            this.toolBar.setInput(select);
            this.tree.setSelection(new StructuredSelection(select), true);
            this.lastSelectedItemKey = getItemName(select);
        }
        
    }

    @objid ("6e6f92b9-da2c-47a8-bcc4-7bbdc6c4fa63")
    private IPanelProvider createNoteEditArea(Composite parent) {
        final NoteViewContentPanel panel = new NoteViewContentPanel(this.controller.getActivationService(),
                this.controller.getContextService());
        panel.createPanel(parent);
        return panel;
    }

    /**
     * Create the notes tree
     * @param parent @return
     */
    @objid ("eab700c2-7e45-4eea-a9ec-153e1a20b85b")
    private TreeViewer createNotesTree(Composite parent) {
        final TreeViewer viewer = new TreeViewer(parent, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
        viewer.setLabelProvider(new ElementDecoratedStyledLabelProvider(new NoteViewTreeLabelProvider(viewer), true, false));
        viewer.setContentProvider(new NoteViewTreeContentProvider());
        viewer.setAutoExpandLevel(3);
        
        viewer.addDoubleClickListener(event -> this.controller.onItemDoubleClick(event.getSelection()));
        
        viewer.addSelectionChangedListener(event -> {
            if (!event.getSelection().isEmpty()) {
                this.controller.onItemSelectionChange(event.getSelection());
            }
        });
        
        // Enable name edition
        viewer.setColumnProperties(new String[] { "name" });
        viewer.setCellEditors(new CellEditor[] { new TextCellEditor((Composite) viewer.getControl()) });
        viewer.setCellModifier(new ICellModifier() {
            @Override
            public void modify(Object element, String property, Object value) {
                if (element != null && element instanceof TableItem) {
                    final TableItem item = (TableItem) element;
        
                    NotesPanelView.this.controller.onNameChanged(item.getData(), (String) value);
                } else if (element != null && element instanceof TreeItem) {
                    final TreeItem item = (TreeItem) element;
        
                    NotesPanelView.this.controller.onNameChanged(item.getData(), (String) value);
                }
            }
        
            @Override
            public Object getValue(Object element, String property) {
                if (element instanceof ModelElement) {
                    return ((ModelElement) element).getName();
                } else {
                    return "";
                }
            }
        
            @Override
            public boolean canModify(Object element, String property) {
                if (element instanceof ModelElement) {
                    return ((ModelElement) element).isModifiable();
                } else {
                    return false;
                }
            }
        });
        
        viewer.getTree().addKeyListener(new KeyAdapter() {
        
            @Override
            public void keyReleased(KeyEvent e) {
                // CTRL-C, CTRL-V, CTRL-X
                if ((e.stateMask &= SWT.MOD1) != 0) {
                    switch (e.keyCode) {
                    case 'c':
                        if (NotesPanelView.this.controller.canCopy()) {
                            NotesPanelView.this.controller.onCopy();
                        }
                        return;
                    case 'x':
                        if (NotesPanelView.this.controller.canCut()) {
                            NotesPanelView.this.controller.onCut();
                        }
                        return;
                    case 'v':
                        if (NotesPanelView.this.controller.canPaste()) {
                            NotesPanelView.this.controller.onPaste();
                        }
                        return;
                    }
                } else {
                    // F2, DEL
                    switch (e.keyCode) {
                    case SWT.F2:
                        ModelElement editedDocument = SelectionHelper.getFirst(viewer.getSelection(), ModelElement.class);
                        if (editedDocument != null) {
                            // Edit selected element
                            viewer.editElement(editedDocument, 0);
                        }
                        break;
                    case SWT.DEL:
                        if (NotesPanelView.this.controller.canRemoveAnnotation()) {
                            NotesPanelView.this.controller.onRemoveAnnotation();
                        }
                        break;
                    default:
                        break;
                    }
                }
        
            }
        
        });
        return viewer;
    }

    /**
     * @return the selected annotations
     */
    @objid ("e2498705-db94-4cdc-9d15-f935b9057933")
    public List<ModelElement> getSelectedNotes() {
        final ISelection selection = this.tree.getSelection();
        if (selection instanceof IStructuredSelection) {
            return ((IStructuredSelection) selection).toList();
        }
        return Collections.emptyList();
    }

    /**
     * @return the annotations tree viewer.
     */
    @objid ("a1402d66-a56c-4e52-9b79-a9380a969afe")
    public TreeViewer getTreeViewer() {
        return this.tree;
    }

    /**
     * This method tries to guess the smartest possible item selection for the
     * current element.
     * @return the annotation to select.
     */
    @objid ("7667946d-9895-4a54-8074-2fa80a8365f9")
    private ModelElement getSelectHint() {
        final Object[] items = ((ITreeContentProvider) this.tree.getContentProvider())
                .getElements(this.tree.getInput());
        
        for (Object item : items) {
            final ModelElement me = (ModelElement) item;
            if (getItemName(me).equals(this.lastSelectedItemKey)) {
                return me;
            }
        }
        return items.length > 0 ? (ModelElement) items[0] : null;
    }

    @objid ("5c65b3bc-c02d-4c9a-b000-2b81e2f12f54")
    private String getItemName(ModelElement item) {
        final String mc = item.getMClass().getName();
        if (item instanceof Note) {
            return mc + ":" + ((Note) item).getModel().getName();
        } else if (item instanceof Constraint) {
            return mc + ":" + ((Constraint) item).getName();
        } else if (item instanceof Document) {
            return mc + ":" + ((Document) item).getType().getName();
        } else {
            return mc;
        }
        
    }

    /**
     * Dispose SWT widgets.
     */
    @objid ("36a20960-d1ad-48b4-be31-f38f526074e2")
    public void dispose() {
        this.toolBar.dispose();
        this.data.dispose();
        
    }

    @objid ("8faaa82a-4516-4f03-9870-8eb93bc1bc4a")
    public int getLayoutOrientation() {
        return this.shform.getOrientation();
    }

    @objid ("d81bc82b-4767-4c17-8379-a6be8df5bf60")
    private void initPopupMenu(TreeViewer treeViewer) {
        // initalize the context menu
        MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(IMenuManager manager) {
                manager.add(new CutAction(NotesPanelView.this.controller));
                manager.add(new CopyAction(NotesPanelView.this.controller));
                manager.add(new PasteAction(NotesPanelView.this.controller));
                manager.add(new Separator());
                manager.add(new JyExecAction(NotesPanelView.this.controller));
                manager.add(new HtmlConvertAction(NotesPanelView.this.controller));
                manager.add(new Separator());
                manager.add(new RemoveAnnotationAction(NotesPanelView.this.controller));
            }
        });
        Menu menu = menuMgr.createContextMenu(treeViewer.getTree());
        treeViewer.getTree().setMenu(menu);
        
    }

    @objid ("f949d79a-2c0d-4a73-bf12-b7ac9a2ee006")
    private static class LayoutChangeListener implements ControlListener {
        @objid ("22109f9c-5a8a-4a8f-bc97-d86502a702cd")
        private final NotesPanelView view;

        @objid ("30972a83-f688-4c9d-b028-1a29190d7537")
        @Override
        public void controlResized(ControlEvent theEvent) {
            changeLayout(theEvent);
        }

        @objid ("b0dded6d-aa66-40bc-ba1f-5aca1bee51e8")
        @Override
        public void controlMoved(ControlEvent theEvent) {
            // Nothing to do
        }

        /**
         * C'tor
         * @param view the notes panel view
         */
        @objid ("7fe32178-a8d0-44c6-9b82-6ff8fbcd88e0")
        public  LayoutChangeListener(final NotesPanelView view) {
            super();
            this.view = view;
            
        }

        @objid ("c242c610-ada3-48ea-a47a-64e6ebb45a07")
        private void changeLayout(ControlEvent theEvent) {
            final Composite comp = (Composite) theEvent.widget;
            comp.layout();
            this.view.autoLayout();
            
        }

    }

}
