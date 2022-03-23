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
package org.modelio.editors.richnote.gui.creation.file;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor.CreationMode;
import org.modelio.editors.richnote.gui.creation.mimetype.MimeTypeContentProvider;
import org.modelio.editors.richnote.gui.creation.mimetype.MimeTypeLabelProvider;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.ui.CoreFontRegistry;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIFont;
import org.modelio.platform.ui.UIImages;

@objid ("b71563b1-0173-48c1-b937-b65bbd6c5c7e")
class FilePanelUI {
    @objid ("a9dbcdd5-79e8-401e-8def-87efb0c070b9")
    private static final Point DEFAULT_INDENT = new Point(25, 0);

    @objid ("2a8acd50-d0ec-47dc-9397-bc3c2a834b5d")
    private FilePanelController controller;

    @objid ("aae320d2-709b-417e-aca7-f4b6c4677439")
    private Group embeddedGroup;

    @objid ("2ac33017-72c4-4a46-b84b-99bf5be28e06")
    private StructuredViewer embeddedMimeTypeViewer;

    @objid ("fada4d72-60fa-4f40-99a2-db5f8dfdb627")
    private Text externalContentText;

    @objid ("b5b08ef3-f73f-48ed-99e9-dfd92f4a73eb")
    private Group externalGroup;

    @objid ("f2e6afe4-75e8-451e-a925-ce77de49f303")
    private Text importContentText;

    @objid ("03533f25-0720-484f-a542-77b42e646e3b")
    private Group importGroup;

    @objid ("80fe582c-0276-41eb-a9d9-566f7e6a7917")
    private TableComboViewer importMimeTypeViewer;

    @objid ("88205c22-e504-4548-8b4b-5bc8177a841c")
    private Button isEmbeddedButton;

    @objid ("c4167356-48f6-496f-af32-97c7c714f0f5")
    private Button isExternalButton;

    @objid ("eac58420-428e-4ad8-b3ad-97128c40f6d8")
    private Button isImportButton;

    @objid ("5d419857-eeeb-4898-a9f6-c8f5ea4b8c25")
    Composite top = null;

    @objid ("d793403d-022c-447d-aad4-e6d4a321bbc3")
    private Label errorLabel;

    @objid ("1c187075-c33b-4ba2-8122-087e3eacc82e")
    public  FilePanelUI(FilePanelController controller) {
        this.controller = controller;
    }

    @objid ("80e07170-778c-4598-bd21-4000158863e9")
    public Control createUI(Composite parent) {
        this.top = new Composite(parent, SWT.NONE);
        this.top.setLayout(new GridLayout(3, false));
        
        Label modeLabel = new Label(this.top, SWT.WRAP);
        modeLabel.setText(EditorsRichNote.I18N.getString("FileWizardPage.Mode.label"));
        modeLabel.setForeground(UIColor.LABEL_TIP_FG);
        modeLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        
        Group userChoiceGroup = createChoiceGroup(top);
        userChoiceGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        
        
        // Embedded document: group to hide/show according to the button's selection
        this.embeddedGroup = new Group(top, SWT.NONE);
        this.embeddedGroup.setText(EditorsRichNote.I18N.getString("FileWizardPage.Parameters.label"));
        this.embeddedGroup.setLayout(new GridLayout(3, false));
        this.embeddedGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(3, 1).create());
        
        // Document MIME type
        createLabel(this.embeddedGroup, EditorsRichNote.I18N.getString("FileWizardPage.MimeType.label"));
        this.embeddedMimeTypeViewer = createMimeTypeViewer(this.embeddedGroup, EditorsRichNote.I18N.getString("FileWizardPage.MimeType.tooltip"));
        
        // Import
        
        // Group to hide/show according to the button's selection
        this.importGroup = new Group(top, SWT.NONE);
        this.importGroup.setText(EditorsRichNote.I18N.getString("FileWizardPage.Parameters.label"));
        this.importGroup.setLayout(new GridLayout(3, false));
        this.importGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(3, 1).create());
        
        // Document MIME type
        createLabel(this.importGroup, EditorsRichNote.I18N.getString("FileWizardPage.MimeType.label"));
        this.importMimeTypeViewer = createMimeTypeViewer(this.importGroup, EditorsRichNote.I18N.getString("FileWizardPage.MimeType.tooltip"));
        
        // File/url chooser
        createLabel(this.importGroup, EditorsRichNote.I18N.getString("FileWizardPage.ImportContent.label"));
        this.importContentText = createContentText(this.importGroup, EditorsRichNote.I18N.getString("FileWizardPage.ImportContent.tooltip"));
        createContentChooserButton(this.importGroup, EditorsRichNote.I18N.getString("FileWizardPage.ImportContentDialog.title"), EditorsRichNote.I18N.getString("FileWizardPage.ImportContent.tooltip"), this.importMimeTypeViewer);
        
        // Group to hide/show according to the button's selection
        this.externalGroup = new Group(top, SWT.NONE);
        this.externalGroup.setText(EditorsRichNote.I18N.getString("FileWizardPage.Parameters.label"));
        this.externalGroup.setLayout(new GridLayout(3, false));
        this.externalGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(3, 1).create());
        
        // File/url chooser
        createLabel(this.externalGroup, EditorsRichNote.I18N.getString("FileWizardPage.ReferencedContent.label"));
        this.externalContentText = createContentText(this.externalGroup, EditorsRichNote.I18N.getString("FileWizardPage.ReferencedContent.tooltip"));
        createContentChooserButton(this.externalGroup, EditorsRichNote.I18N.getString("FileWizardPage.ReferencedContentDialog.title"), EditorsRichNote.I18N.getString("FileWizardPage.ReferencedContent.tooltip"), null);
        
        
        this.errorLabel = new Label(top, SWT.NONE);
        this.errorLabel.setForeground(UIColor.RED);
        this.errorLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        return this.top;
    }

    @objid ("db781fb0-925c-4fe3-b11c-ad37d35e3d16")
    public void update(RichNoteDescriptor data) {
        if (data != null) {
            switch (data.getCreationMode()) {
            case EMBEDDED:
                // Init visible widgets
                this.isEmbeddedButton.setSelection(true);
                this.embeddedMimeTypeViewer.setInput(data.getTargetElement());
                this.embeddedMimeTypeViewer.setSelection(data.getChosenMimeType() != null ? new StructuredSelection(data.getChosenMimeType()) : new StructuredSelection());
        
                // Hide/show groups
                this.embeddedGroup.setVisible(true);
                ((GridData) this.embeddedGroup.getLayoutData()).exclude = false;
                this.externalGroup.setVisible(false);
                ((GridData) this.externalGroup.getLayoutData()).exclude = true;
                this.importGroup.setVisible(false);
                ((GridData) this.importGroup.getLayoutData()).exclude = true;
        
                break;
            case EXTERNAL:
                // Init widgets
                this.isExternalButton.setSelection(true);
                this.externalContentText.setText(data.getPath());
        
                // Hide/show groups
                this.embeddedGroup.setVisible(false);
                ((GridData) this.embeddedGroup.getLayoutData()).exclude = true;
                this.externalGroup.setVisible(true);
                ((GridData) this.externalGroup.getLayoutData()).exclude = false;
                this.importGroup.setVisible(false);
                ((GridData) this.importGroup.getLayoutData()).exclude = true;
        
                break;
            case IMPORT:
                // Init visible widgets
                this.isImportButton.setSelection(true);
                this.importMimeTypeViewer.setInput(data.getTargetElement());
                this.importMimeTypeViewer.setSelection(data.getChosenMimeType() != null ? new StructuredSelection(data.getChosenMimeType()) : new StructuredSelection());
                this.importContentText.setText(data.getPath());
        
                // Hide/show groups
                this.embeddedGroup.setVisible(false);
                ((GridData) this.embeddedGroup.getLayoutData()).exclude = true;
                this.externalGroup.setVisible(false);
                ((GridData) this.externalGroup.getLayoutData()).exclude = true;
                this.importGroup.setVisible(true);
                ((GridData) this.importGroup.getLayoutData()).exclude = false;
        
                break;
            case UNDEFINED:
            default:
                this.embeddedGroup.setVisible(false);
                ((GridData) this.embeddedGroup.getLayoutData()).exclude = true;
                this.externalGroup.setVisible(false);
                ((GridData) this.externalGroup.getLayoutData()).exclude = true;
                this.importGroup.setVisible(false);
                ((GridData) this.importGroup.getLayoutData()).exclude = true;
        
                break;
            }
        
            this.top.layout(true);
        }
        
    }

    @objid ("9809e1e6-e89f-450b-86b3-efc28aeac18e")
    public void dispose() {
        this.top.dispose();
    }

    @objid ("d2c21cd0-0941-47b6-a2a2-be1803d79465")
    private void createEmbeddedWidgets(Composite parent) {
        
    }

    @objid ("a171f639-9214-452f-9fee-2caba63cb5c8")
    private void createExternalWidgets(Composite parent) {
        
    }

    @objid ("fa4a898f-7000-4ef2-9ba3-b411ae8b2150")
    private void createImportWidgets(Composite parent) {
        
    }

    @objid ("f5f8aed7-da1d-48cf-89c8-b2be34aabfdd")
    private Button createContentChooserButton(Composite parent, String chooserDialogTitle, String tooltip, StructuredViewer mimeTypeViewer) {
        Button b = new Button(parent, SWT.PUSH);
        b.setToolTipText(tooltip);
        b.setImage(UIImages.FILECHOOSE);
        b.setLayoutData(GridDataFactory.fillDefaults().create());
        b.addListener(SWT.Selection, l -> {
            FileDialog fd = new FileDialog(parent.getShell(), SWT.OPEN);
            fd.setText(chooserDialogTitle);
        
            if (mimeTypeViewer != null) {
                RichNoteFormat format = SelectionHelper.getFirst(mimeTypeViewer.getSelection(), RichNoteFormat.class);
        
                Collection<String> formatExtensions = format.getFileExtensions();
                String[] filterExtensions = new String[formatExtensions.size()];
                int i = 0;
                for (String formatExtension : formatExtensions) {
                    filterExtensions[i] = "*." + formatExtension;
                }
                fd.setFilterExtensions(filterExtensions);
            }
        
            String selected = fd.open();
            if (selected != null) {
                FilePanelUI.this.controller.onContentChanged(selected, true);
            }
        });
        return null;
    }

    @objid ("53e65aaf-3818-4444-93bf-09f8a4b9500c")
    private Text createContentText(Composite parent, String tooltip) {
        Text t = new Text(parent, SWT.SINGLE | SWT.BORDER);
        t.setToolTipText(tooltip);
        t.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        t.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                FilePanelUI.this.controller.onContentChanged(t.getText(), true);
            }
        });
        
        t.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                FilePanelUI.this.controller.onContentChanged(t.getText(), false);
                
            }
        });
        return t;
    }

    @objid ("b3fd9ff7-0a28-4b79-9d57-f0bccec0d022")
    private Label createLabel(Composite parent, String label) {
        Label l = new Label(parent, SWT.WRAP);
        l.setText(label);
        l.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        return l;
    }

    @objid ("802d42c6-9bf5-4e48-9747-51b467d008a7")
    private TableComboViewer createMimeTypeViewer(Composite parent, String tooltip) {
        TableComboViewer viewer = new TableComboViewer(parent, SWT.BORDER | SWT.READ_ONLY);
        viewer.getControl().setToolTipText(tooltip);
        viewer.setContentProvider(new MimeTypeContentProvider());
        viewer.setLabelProvider(new MimeTypeLabelProvider());
        viewer.setComparator(new ViewerComparator());
        viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().span(3, 1).create());
        viewer.addSelectionChangedListener(e -> {
            this.controller.onMimeTypeSelected(SelectionHelper.getFirst(viewer.getSelection(), RichNoteFormat.class));
        });
        return viewer;
    }

    @objid ("fff16da7-6b5f-4f4b-b657-8973eae18e24")
    void setErrorIndication(String errorText) {
        if (errorText != null) {
            this.errorLabel.setText(errorText);
            
        } else {
            // no error
            this.errorLabel.setText("");
        }
        
    }

    @objid ("019a10a9-cc1f-485a-a0a1-41f5fd3f8a14")
    private Group createChoiceGroup(Composite parent) {
        Font tipFont = CoreFontRegistry.getModifiedFont(parent.getFont(), SWT.ITALIC, UIFont.SMALL_SIZE);
        
        Group userChoiceGroup = new Group(parent, SWT.NONE);
        userChoiceGroup.setLayout(new GridLayout());
        
        // Create the choice radio buttons and their help
        // Embedded
        this.isEmbeddedButton = new Button(userChoiceGroup, SWT.RADIO);
        this.isEmbeddedButton.setText(EditorsRichNote.I18N.getString("FileWizardPage.Embedded.label"));
        this.isEmbeddedButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        this.isEmbeddedButton.addListener(SWT.Selection, e -> {
            this.controller.onSetCreationMode(CreationMode.EMBEDDED);
        });
        
        Label isEmbeddedLabel = new Label(userChoiceGroup, SWT.WRAP);
        isEmbeddedLabel.setText(EditorsRichNote.I18N.getString("FileWizardPage.Embedded.tooltip"));
        isEmbeddedLabel.setForeground(UIColor.LABEL_TIP_FG);
        isEmbeddedLabel.setFont(tipFont);
        isEmbeddedLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(FilePanelUI.DEFAULT_INDENT).span(3, 1).create());
        
        // Import
        this.isImportButton = new Button(userChoiceGroup, SWT.RADIO);
        this.isImportButton.setText(EditorsRichNote.I18N.getString("FileWizardPage.Import.label"));
        this.isImportButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        this.isImportButton.addListener(SWT.Selection, e -> {
            this.controller.onSetCreationMode(CreationMode.IMPORT);
        });
        
        Label isImportLabel = new Label(userChoiceGroup, SWT.WRAP);
        isImportLabel.setText(EditorsRichNote.I18N.getString("FileWizardPage.Import.tooltip"));
        isImportLabel.setForeground(UIColor.LABEL_TIP_FG);
        isImportLabel.setFont(tipFont);
        isImportLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(FilePanelUI.DEFAULT_INDENT).span(3, 1).create());
        
        // External
        this.isExternalButton = new Button(userChoiceGroup, SWT.RADIO);
        this.isExternalButton.setText(EditorsRichNote.I18N.getString("FileWizardPage.External.label"));
        this.isExternalButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        this.isExternalButton.addListener(SWT.Selection, e -> {
            this.controller.onSetCreationMode(CreationMode.EXTERNAL);
        });
        
        Label isExternalLabel = new Label(userChoiceGroup, SWT.WRAP);
        isExternalLabel.setText(EditorsRichNote.I18N.getString("FileWizardPage.External.tooltip"));
        isExternalLabel.setForeground(UIColor.LABEL_TIP_FG);
        isExternalLabel.setFont(tipFont);
        isExternalLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(FilePanelUI.DEFAULT_INDENT).span(3, 1).create());
        return userChoiceGroup;
    }

}
