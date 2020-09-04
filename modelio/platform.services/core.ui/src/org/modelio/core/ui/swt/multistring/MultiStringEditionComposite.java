/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.core.ui.swt.multistring;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.modelio.ui.UIImages;

@objid ("406c5a50-374f-48bc-a054-766dc0bc9cad")
public class MultiStringEditionComposite extends Composite {
    @objid ("8dcf2028-c068-11e1-8c0a-002564c97630")
    private int size = 0;

    @objid ("5728b7a0-30b6-4e84-97e3-983ba4979610")
    private List<String> content = null;

    @objid ("8dcd99b9-c068-11e1-8c0a-002564c97630")
    private Button addParameterButton = null;

    @objid ("8dcd99ba-c068-11e1-8c0a-002564c97630")
    private Button removeParameterButton = null;

    @objid ("8dcd99bd-c068-11e1-8c0a-002564c97630")
    private StringAdapterModifier stringAdapterModifier = null;

    @objid ("8dcd99b5-c068-11e1-8c0a-002564c97630")
    private TableViewer contentTable = null;

    @objid ("8dcd99be-c068-11e1-8c0a-002564c97630")
    private Text addStringText = null;

    @objid ("8dcf2025-c068-11e1-8c0a-002564c97630")
    private StringTextListener stringTextListener = null;

    @objid ("8dcf2029-c068-11e1-8c0a-002564c97630")
    private ContentTableListener contentTableListener = null;

    @objid ("8dcf202c-c068-11e1-8c0a-002564c97630")
    private Button moveUpParameterButton = null;

    @objid ("8dcf202d-c068-11e1-8c0a-002564c97630")
    private Button moveDownParameterButton = null;

    @objid ("8dcf202e-c068-11e1-8c0a-002564c97630")
    private AddButtonListener addButtonListener = null;

    @objid ("8dcf202f-c068-11e1-8c0a-002564c97630")
    private MoveUpButtonListener moveUpButtonListener = null;

    @objid ("8dcf2030-c068-11e1-8c0a-002564c97630")
    private MoveDownButtonListener moveDownButtonListener = null;

    @objid ("8dcf2031-c068-11e1-8c0a-002564c97630")
    private RemoveButtonListener removeButtonListener = null;

    @objid ("73a58bbc-5028-4be4-bffb-6f109633d365")
    public List<String> getContent() {
        return this.content;
    }

    @objid ("c0f94828-4984-4425-82cd-e4a6856feeb5")
    public MultiStringEditionComposite(Composite parent, int style, int size) {
        super(parent, style);
        
        this.size = size;
        this.content = new ArrayList<>();
        
        this.setLayout(new GridLayout(2, false));
        final GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        layoutData.minimumHeight = 300;
        layoutData.minimumWidth = 400;
        this.setLayoutData(layoutData);
        this.setFont(parent.getFont());
        
        createListField(this);
        createListButtons(this);
        createAddStringField(this);
        
        initListeners();
    }

    @objid ("8dd22d81-c068-11e1-8c0a-002564c97630")
    public void initContent(List<String> values) {
        this.content = new ArrayList<>();
        this.content.addAll(values);
        refresh();
    }

    @objid ("8dd0a6f8-c068-11e1-8c0a-002564c97630")
    private void createAddStringField(Composite area) {
        this.addStringText = new Text(area, SWT.BORDER);
        if (this.size != -1) {
            this.addStringText.setEnabled(this.content.size() < this.size);
        }
        GridData gd_addStringText = new GridData(SWT.FILL, SWT.FILL, true, false);
        this.addStringText.setLayoutData(gd_addStringText);
        this.addStringText.forceFocus();
        // Prevent CR from closing the editor
        this.addStringText.addTraverseListener(new TraverseListener() {
            @Override
            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                    e.detail = SWT.TRAVERSE_NONE;
                }
            }
        });
        
        this.addParameterButton = new Button(area, SWT.PUSH);
        this.addParameterButton.setImage(UIImages.ADD);
        this.addParameterButton.setEnabled(false);
        GridData gd_addParameterButton = new GridData(SWT.LEFT, SWT.TOP, false, false);
        this.addParameterButton.setLayoutData(gd_addParameterButton);
    }

    @objid ("8dd22d65-c068-11e1-8c0a-002564c97630")
    private void createListField(Composite area) {
        this.contentTable = new TableViewer(area);
        
        String columnNames[] = {"Value"};
        
        TableColumn column0 = new TableColumn(this.contentTable.getTable(), SWT.LEFT, 0);
        column0.setWidth(150);
        
        this.contentTable.setColumnProperties(columnNames);
        
        this.contentTable.setContentProvider(new MultiStringContentProvider());
        this.contentTable.setLabelProvider(new MultiStringLabelProvider());
        
        initEditor();
        
        this.contentTable.setInput(this.content);
        GridData gd_contentTree = new GridData(SWT.FILL, SWT.FILL, true, true);
        this.contentTable.getControl().setLayoutData(gd_contentTree);
    }

    @objid ("8dd22d8a-c068-11e1-8c0a-002564c97630")
    private void initEditor() {
        // Define cell editor:
        TextCellEditor[] cellEditors = new TextCellEditor[1];
        cellEditors[0]  = new TextCellEditor(this.contentTable.getTable(), SWT.NONE);
        this.contentTable.setCellEditors(cellEditors);
        
        // Define ICellModifier:
        String[] properties = new String[1];
        properties[0] = "name";
        this.contentTable.setColumnProperties(properties);
        
        this.stringAdapterModifier = new StringAdapterModifier(this);
        this.contentTable.setCellModifier(this.stringAdapterModifier);
        this.contentTable.getTable().addKeyListener(this.stringAdapterModifier);
        
        // Define editor activation strategy:
        StringAdapterEditorActivationStrategy actSupport = new StringAdapterEditorActivationStrategy(this.contentTable);
        
        TableViewerEditor.create(this.contentTable, null, actSupport, ColumnViewerEditor.TABBING_HORIZONTAL
                | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
                | ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);
    }

    @objid ("8dd0a6fe-c068-11e1-8c0a-002564c97630")
    private void createListButtons(Composite area) {
        Composite buttonsComposite = new Composite(area, SWT.NONE);
        GridLayout gl_buttonComposite = new GridLayout(1, false);
        gl_buttonComposite.marginWidth = 0;
        gl_buttonComposite.marginHeight = 0;
        buttonsComposite.setLayout(gl_buttonComposite);
        GridData gd_buttonsComposite = new GridData(SWT.LEFT, SWT.TOP, false, false);
        buttonsComposite.setLayoutData(gd_buttonsComposite);
        
        this.moveUpParameterButton = new Button(buttonsComposite, SWT.PUSH);
        this.moveUpParameterButton.setImage(UIImages.UPARROW);
        this.moveUpParameterButton.setEnabled(false);
        GridData gd_moveUpParameterButton = new GridData(SWT.LEFT, SWT.TOP, false, false);
        this.moveUpParameterButton.setLayoutData(gd_moveUpParameterButton);
        
        this.moveDownParameterButton = new Button(buttonsComposite, SWT.PUSH);
        this.moveDownParameterButton.setImage(UIImages.DOWNARROW);
        this.moveDownParameterButton.setEnabled(false);
        GridData gd_moveDownParameterButton = new GridData(SWT.LEFT, SWT.TOP, false, false);
        this.moveDownParameterButton.setLayoutData(gd_moveDownParameterButton);
        
        this.removeParameterButton = new Button(buttonsComposite, SWT.PUSH);
        this.removeParameterButton.setImage(UIImages.DELETE);
        this.removeParameterButton.setEnabled(false);
        GridData gd_removeParameterButton = new GridData(SWT.LEFT, SWT.TOP, false, false);
        this.removeParameterButton.setLayoutData(gd_removeParameterButton);
    }

    @objid ("8dd22d8c-c068-11e1-8c0a-002564c97630")
    private void initListeners() {
        this.contentTableListener = new ContentTableListener(this);
        this.contentTable.addSelectionChangedListener(this.contentTableListener);
        this.contentTable.getTable().addKeyListener(this.contentTableListener);
        this.contentTable.getTable().addControlListener(this.contentTableListener);
        
        this.moveUpButtonListener = new MoveUpButtonListener(this);
        this.moveUpParameterButton.addSelectionListener(this.moveUpButtonListener);
        
        this.moveDownButtonListener = new MoveDownButtonListener(this);
        this.moveDownParameterButton.addSelectionListener(this.moveDownButtonListener);
        
        this.removeButtonListener = new RemoveButtonListener(this);
        this.removeParameterButton.addSelectionListener(this.removeButtonListener);
        
        this.addButtonListener = new AddButtonListener(this);
        this.addParameterButton.addSelectionListener(this.addButtonListener);
        
        this.stringTextListener = new StringTextListener(this);
        this.addStringText.addModifyListener(this.stringTextListener);
        this.addStringText.addKeyListener(this.stringTextListener);
    }

    @objid ("8dcf2050-c068-11e1-8c0a-002564c97630")
    public Button getAddParameterButton() {
        return this.addParameterButton;
    }

    @objid ("8dcf2054-c068-11e1-8c0a-002564c97630")
    public Text getAddStringText() {
        return this.addStringText;
    }

    @objid ("8dcf2058-c068-11e1-8c0a-002564c97630")
    public TableViewer getContentTable() {
        return this.contentTable;
    }

    @objid ("8dcf205c-c068-11e1-8c0a-002564c97630")
    public Button getMoveDownParameterButton() {
        return this.moveDownParameterButton;
    }

    @objid ("8dd0a6c5-c068-11e1-8c0a-002564c97630")
    public Button getMoveUpParameterButton() {
        return this.moveUpParameterButton;
    }

    @objid ("8dd0a6c9-c068-11e1-8c0a-002564c97630")
    public Button getRemoveParameterButton() {
        return this.removeParameterButton;
    }

    @objid ("8dd0a6cd-c068-11e1-8c0a-002564c97630")
    public List<String> getSelectedAdapters() {
        IStructuredSelection structuredSelection = (IStructuredSelection)this.contentTable.getSelection();
        return structuredSelection.toList();
    }

    @objid ("8dcf203e-c068-11e1-8c0a-002564c97630")
    public void addAdapter(String value) {
        this.content.add(value);
        refresh();
    }

    @objid ("8dd0a6e6-c068-11e1-8c0a-002564c97630")
    public void refresh() {
        this.contentTable.setInput(this.content);
        
        if (this.size != -1) {
            this.addStringText.setEnabled(this.content.size() < this.size);
        }
    }

    @objid ("8dd0a6e8-c068-11e1-8c0a-002564c97630")
    public void removeAdapters(List<String> values) {
        for (String value : values) {
            this.content.remove(value);
        }
        refresh();
    }

    @objid ("8dd22d68-c068-11e1-8c0a-002564c97630")
    private static int getIndexDown(String value, List<String> list) {
        int index = list.indexOf(value);
        
        if (index == -1) {
            return -1;
        }
        
        index++;
        
        if (index >= list.size()) {
            return -1;
        }
        return index;
    }

    @objid ("8dd22d70-c068-11e1-8c0a-002564c97630")
    private static int getIndexUp(String value, List<String> list) {
        int index = list.indexOf(value);
        
        if (index < 1) {
            return -1;
        }
        
        index--;
        return index;
    }

    @objid ("8dd0a6d7-c068-11e1-8c0a-002564c97630")
    public void moveDown(List<String> values) {
        // We first move down the Last element of the list; This way the positions of other
        // selected elements are not affected by the move of the current element.
        for (int i = values.size() - 1; i > -1; i--) {
            String value = values.get(i);
        
            // Retrieve the new index of the element
            int index = getIndexDown(value, this.content);
        
            if (index != -1) {
                // Move the element in the list
                this.content.remove(value);
                this.content.add(index, value);
            } else {
                break;
            }
        }
        
        refresh();
    }

    @objid ("8dd0a6dc-c068-11e1-8c0a-002564c97630")
    public void moveUp(List<String> values) {
        for (String value : values) {
            int index = getIndexUp(value, this.content);
        
            if (index != -1) {
                this.content.remove(value);
                this.content.add(index, value);
            } else {
                break;
            }
        }
        
        this.refresh();
    }

    @objid ("9454b7ad-f11d-4fd9-aa3a-5ab9b5a533f8")
    @Override
    public void dispose() {
        if (this.moveUpButtonListener != null) {
            this.moveUpParameterButton.removeSelectionListener(this.moveUpButtonListener);
            this.moveUpButtonListener = null;
        }
        
        if (this.moveDownButtonListener != null) {
            this.moveDownParameterButton.removeSelectionListener(this.moveDownButtonListener);
            this.moveDownButtonListener = null;
        }
        
        if (this.removeButtonListener != null) {
            this.removeParameterButton.removeSelectionListener(this.removeButtonListener);
            this.removeParameterButton = null;
        }
        
        if (this.addButtonListener != null) {
            this.addParameterButton.removeSelectionListener(this.addButtonListener);
            this.addButtonListener = null;
        }
        
        if (this.stringTextListener != null) {
            this.addStringText.removeModifyListener(this.stringTextListener);
            this.addStringText.removeKeyListener(this.stringTextListener);
            this.stringTextListener = null;
        }
        
        if (this.contentTableListener != null) {
            this.contentTable.removeSelectionChangedListener(this.contentTableListener);
            this.contentTable.getTable().removeKeyListener(this.contentTableListener);
            this.contentTable.getTable().removeControlListener(this.contentTableListener);
            this.contentTableListener = null;
        }
        
        super.dispose();
    }

}
