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

package org.modelio.editors.richnote.gui.creation;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor.CreationMode;
import org.modelio.editors.richnote.gui.creation.mimetype.MimeTypeContentProvider;
import org.modelio.editors.richnote.gui.creation.mimetype.MimeTypeLabelProvider;
import org.modelio.editors.richnote.gui.creation.model.ModelPanel;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.ui.UIColor;
import org.modelio.ui.UIImages;
import org.modelio.ui.dialog.ModelioDialog;
import org.modelio.ui.panel.IPanelListener;

/**
 * This dialog is used to create embedded documents.
 */
@objid ("da611dce-19f4-408e-8ace-fd016f30217a")
public class CreateEmbeddedDocumentDialog extends ModelioDialog {
    @objid ("32e1ed03-d01e-4673-8411-9ba448248457")
    private Text importContentText;

    @objid ("68c71ac5-5472-4369-b305-5005c9ed73dc")
    private TableComboViewer mimeTypeViewer;

    @objid ("9b50c976-6596-4d45-8893-b2d988edaf0d")
    private Label errorLabel;

    @objid ("d0863377-9ac7-4b9f-bbbd-412fea0cc647")
    private RichNoteDescriptor data;

    @objid ("a876ab80-020e-4078-8ae6-f5d2173d4646")
    private Label urlLabel;

    @objid ("03df4534-70f1-461e-a30e-835cbf75ce8f")
    private Button openFileSelectionDialog;

    /**
     * C'tor.
     * 
     * @param parentShell the parent SWT shell
     * @param data the dialogs date model.
     */
    @objid ("82894722-42c4-47a7-ac3b-2f874ddd2121")
    public CreateEmbeddedDocumentDialog(Shell parentShell, RichNoteDescriptor data) {
        super(parentShell);
        this.data = data;
        data.setCreationMode(CreationMode.EMBEDDED);
    }

    @objid ("a8e79481-30f3-4341-a05c-639523897755")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        addDefaultButtons(parent);
    }

    @objid ("f38bfb4b-75b6-4854-9438-67036c7157c2")
    @Override
    protected Point getInitialSize() {
        return new Point(600, 550);
    }

    @objid ("13e1716a-da52-4202-ab6b-61b17da56aab")
    @Override
    public void init() {
        setTitle(EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.title"));
        setMessage(EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.message"));
        validatePage();
    }

    @objid ("1f9f33e5-c4ac-40c4-999e-2f31a4546153")
    @Override
    public Control createContentArea(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(1, true));
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        ModelPanel documentModelPanel = new ModelPanel();
        Composite model = (Composite) documentModelPanel.createPanel(container);
        model.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        documentModelPanel.setInput(this.data);
        
        documentModelPanel.addListener(new IPanelListener() {
        
            @Override
            public void dataChanged(Object changedData, boolean isValidate) {
                validatePage();
            }
        });
        
        Group fileGroup = createFileGroup(container);
        fileGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        return container;
    }

    @objid ("a1b1abba-d31f-43ae-83a6-9bc7db44387c")
    private Group createFileGroup(Composite parent) {
        Group top = new Group(parent, SWT.NONE);
        top.setText(EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.contents.label"));
        top.setLayout(new GridLayout(3, false));
        
        // Document MIME type selection
        Label mimeTypeLabel = new Label(top, SWT.WRAP);
        mimeTypeLabel.setText(EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.contents.mimetype.label"));
        mimeTypeLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        
        this.mimeTypeViewer = new TableComboViewer(top, SWT.BORDER | SWT.READ_ONLY);
        this.mimeTypeViewer.getControl().setToolTipText(EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.contents.mimetype.tooltip"));
        this.mimeTypeViewer.setContentProvider(new MimeTypeContentProvider());
        this.mimeTypeViewer.setLabelProvider(new MimeTypeLabelProvider());
        this.mimeTypeViewer.setComparator(new ViewerComparator());
        this.mimeTypeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().span(3, 1).create());
        this.mimeTypeViewer.addSelectionChangedListener(e -> {
            onMimeTypeSelected(SelectionHelper.getFirst(this.mimeTypeViewer.getSelection(), RichNoteFormat.class));
        });
        
        // Import
        
        // Check box to choose to provide an inital contents
        Button importCheckbox = new Button(top, SWT.CHECK);
        importCheckbox.setText(EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.contents.importoption.label"));
        importCheckbox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        importCheckbox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                CreateEmbeddedDocumentDialog.this.onInitializeContentsChange(((Button) e.widget).getSelection());
            }
        });
        
        // File/url chooser
        this.urlLabel = new Label(top, SWT.WRAP);
        this.urlLabel.setText(EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.contents.file.label"));
        this.urlLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        
        this.importContentText = new Text(top, SWT.SINGLE | SWT.BORDER);
        this.importContentText.setToolTipText(EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.contents.file.tooltip"));
        this.importContentText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        this.importContentText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                CreateEmbeddedDocumentDialog.this.onContentChanged(CreateEmbeddedDocumentDialog.this.importContentText.getText(), true);
            }
        });
        
        this.importContentText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                CreateEmbeddedDocumentDialog.this.onContentChanged(CreateEmbeddedDocumentDialog.this.importContentText.getText(), false);
        
            }
        });
        
        this.openFileSelectionDialog = new Button(top, SWT.PUSH);
        this.openFileSelectionDialog.setImage(UIImages.FILECHOOSE);
        this.openFileSelectionDialog.setLayoutData(GridDataFactory.fillDefaults().create());
        this.openFileSelectionDialog.addListener(SWT.Selection, l -> {
            FileDialog fd = new FileDialog(parent.getShell(), SWT.OPEN);
            fd.setText(EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.contents.file.selector.title"));
        
            if (this.mimeTypeViewer != null) {
                RichNoteFormat format = SelectionHelper.getFirst(this.mimeTypeViewer.getSelection(), RichNoteFormat.class);
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
                onContentChanged(selected, true);
            }
        });
        
        this.errorLabel = new Label(top, SWT.NONE);
        this.errorLabel.setForeground(UIColor.RED);
        this.errorLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        
        // Init field values
        importCheckbox.setSelection(this.data.getCreationMode() == CreationMode.IMPORT);
        onInitializeContentsChange(this.data.getCreationMode() == CreationMode.IMPORT);
        
        this.mimeTypeViewer.setInput(this.data.getTargetElement());
        this.mimeTypeViewer.setSelection(this.data.getChosenMimeType() != null ? new StructuredSelection(this.data.getChosenMimeType()) : new StructuredSelection());
        this.importContentText.setText(this.data.getPath());
        return top;
    }

    @objid ("cc6262f6-9db5-4954-a10b-2c21906ba39a")
    protected void onInitializeContentsChange(boolean b) {
        this.urlLabel.setVisible(b);
        this.importContentText.setVisible(b);
        this.openFileSelectionDialog.setVisible(b);
        this.data.setCreationMode(b ? CreationMode.IMPORT : CreationMode.EMBEDDED);
        validatePage();
    }

    @objid ("ef28c2d4-a6d0-4f69-819e-dd35e0ff1e38")
    protected void onContentChanged(String value, boolean isValidate) {
        // Make sure there is a change
        if (Objects.equals(this.data.getPath(), value)) {
            return;
        }
        
        this.data.setPath(value);
        if (isValidate) {
            this.importContentText.setText(value);
        }
        
        validatePage();
    }

    @objid ("8207dc9e-a57d-4ff5-8d87-493602919975")
    private void validatePage() {
        Button b = this.getButton(IDialogConstants.OK_ID);
        
        if (b != null) {
            b.setEnabled(isPageComplete(this.data));
        }
    }

    @objid ("6735b4ea-598d-4671-a961-5b235d610bf6")
    private void onMimeTypeSelected(RichNoteFormat selectedMimeType) {
        // Make sure there is a change
        if (Objects.equals(this.data.getChosenMimeType(), selectedMimeType)) {
            return;
        }
        
        this.data.setMimeType(selectedMimeType);
        validatePage();
    }

    @objid ("54aab377-bdb9-476c-95e2-a5d645d0f60c")
    private boolean isPageComplete(RichNoteDescriptor data) {
        if (data != null) {
            String diagnostic = diagnoseData(data);
            onShowError(diagnostic);
            return (diagnostic == null);
        } else {
            return false;
        }
    }

    @objid ("06ba36ae-fb1f-437e-acec-77cce0b9a391")
    private void onShowError(String errorText) {
        if (errorText != null) {
            this.errorLabel.setText(errorText);
        } else {
            // no error
            this.errorLabel.setText("");
        }
    }

    /**
     * @param data the dialog's data model.
     * @return <code>null>/code> if there is no diagnosed problem. Otherwise return a (I18n) string indicating the error.
     */
    @objid ("d1c6d953-1e62-4552-9c7f-ff38488e2285")
    private String diagnoseData(RichNoteDescriptor data) {
        if (data.getDocumentType() == null) {
            return EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.Category.isMandatory");
        }
        
        // Check mime type is valid
        if (data.getChosenMimeType() == null) {
            return EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.MimeType.isMandatory");
        }
        
        // Check path is valid if import mode
        if (data.getCreationMode() == CreationMode.IMPORT) {
            String reference = data.getPath();
        
            // File reference is mandatory in IMPORT mode
            if (reference == null || reference.isEmpty()) {
                return EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.FileReference.isMandatory");
            }
        
            URI uri;
            try {
                uri = new URI(reference);
                // IF an URI could be parsed it must be a "file:" URI which is the only supported form here.
                if ("file".equals(uri.getScheme())) {
                    reference = uri.toString().substring(7);
                } else if (uri.getScheme() != null) {
                    return EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.URINotAllowed");
                }
            } catch (URISyntaxException e) {
                // Invalid URI, assume it's a local file...
            }
        
            // Local file case
            try {
                Path path = Paths.get(reference);
                RichNoteFormat mimeType = data.getChosenMimeType();
        
                // The uri has no scheme, assume it's a local file...
                if (!Files.isRegularFile(path)) {
                    if (Files.isDirectory(path)) {
                        return EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.DirectoryNotAllowed");
                    } else {
                        return EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.NotARegularFile");
                    }
                } else if (!Files.isReadable(path)) {
                    return EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.NotReadableFile");
                } else if (mimeType != null) {
                    if (data.getCreationMode() == CreationMode.EMBEDDED || data.getCreationMode() == CreationMode.IMPORT) {
                        for (String extension : mimeType.getFileExtensions()) {
                            if (reference.endsWith("." + extension)) {
                                return null;
                            }
                        }
                        return EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.InvalidContentForMimeType");
                    }
                }
                return null;
            } catch (InvalidPathException e2) {
                // Invalid path too
                return EditorsRichNote.I18N.getString("CreateEmbeddedDocumentDialog.NotARegularFile");
            }
        }
        return null;
    }

    @objid ("3e53bdf1-ad4f-4b29-ad57-5fdc013dd6ff")
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX);
        newShell.setMinimumSize(getInitialSize());
    }

}
