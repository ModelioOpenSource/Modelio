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

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor.CreationMode;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.platform.ui.panel.IPanelListener;

@objid ("f1d849d7-8bff-42a7-9adc-6c096199fa0d")
public class FileWizardPage extends WizardPage {
    @objid ("7591e3cc-d27f-464f-ad41-238f18df0ebd")
    private FilePanel panel;

    @objid ("a1cd62c7-c438-4633-9a8f-c836ce559ff4")
    private AtomicReference<CompletableFuture<?>> work = new AtomicReference<>();

    @objid ("4352751f-57e5-45c2-b36b-a7e87d69796d")
    public  FileWizardPage(RichNoteDescriptor data) {
        super(EditorsRichNote.I18N.getString("FileWizardPage.page"), EditorsRichNote.I18N.getString("FileWizardPage.title"), null);
        this.panel = new FilePanel();
        this.panel.setInput(data);
        this.panel.addListener(new IPanelListener() {
            @Override
            public void dataChanged(Object o, boolean isValidate) {
                RichNoteDescriptor changedData = (RichNoteDescriptor) o;
                setPageComplete(isPageComplete(changedData));
            }
        });
        
        setPageComplete(isPageComplete(data));
        
    }

    @objid ("c7a66281-292f-4ff3-8d6e-162468388669")
    @Override
    public void createControl(Composite parent) {
        Control aPanel = this.panel.createPanel(parent);
        setControl(aPanel);
        
    }

    @objid ("e5a95e9b-48a7-4417-acfb-d9c91b563c76")
    @Override
    public void dispose() {
        super.dispose();
        this.panel.dispose();
        
    }

    @objid ("73c497ea-f804-423e-a2e4-709e70787570")
    @Override
    public String getDescription() {
        return EditorsRichNote.I18N.getString("FileWizardPage.description");
    }

    @objid ("5b6d9f59-9e64-467d-888e-0cdbb2a3a933")
    public String getHelpTopic() {
        return EditorsRichNote.I18N.getString("FileWizardPage.helpid");
    }

    @objid ("51df55e4-0c23-4a01-8567-066c1ea65598")
    private boolean isPageComplete(RichNoteDescriptor data) {
        if (data == null) {
            return false;
        }
        
        switch (data.getCreationMode()) {
        case EMBEDDED:
            // Embedded docs must have a mime type
            boolean isValidMimeType = data.getChosenMimeType() != null;
            return isValidMimeType;
        case IMPORT:
            // Imported docs must have a mime type and a path
            isValidMimeType = data.getChosenMimeType() != null;
            String diagnostic = isValidReference(data);
            this.panel.setErrorIndication(diagnostic);
            return isValidMimeType && diagnostic == null;
        case EXTERNAL:
            // External docs must have a referenced path
            diagnostic = isValidReference(data);
            this.panel.setErrorIndication(diagnostic);
            return diagnostic == null;
        case UNDEFINED:
        default:
            return false;
        }
        
    }

    @objid ("fbe9e024-9333-4bff-a1e4-d406cea3abdc")
    private String isValidReference(RichNoteDescriptor data) {
        String reference = data.getPath();
        if (reference == null || reference.isEmpty()) {
            return "";
        }
        
        URI uri;
        try {
            uri = new URI(reference);
            if ("file".equals(uri.getScheme())) {
                reference = uri.toString().substring(7);
            } else if (uri.getScheme() != null) {
                if (data.getCreationMode() == CreationMode.EXTERNAL) {
                    // Prepare the worker
                    CompletableFuture<String> newWorker = CompletableFuture.supplyAsync(() -> {
                        try {
                            return checkConnection(uri);
                        } catch (Exception e) {
                            throw new java.util.concurrent.CompletionException(e);
                        }
                    });
        
                    // Cancel current preview if any
                    CompletableFuture<?> oldWorker = this.work.getAndSet(newWorker);
                    if (oldWorker != null) {
                        oldWorker.cancel(true);
                    }
        
                    // Launch asynchronous execution
                    newWorker.whenCompleteAsync((result, ex) -> {
                        if (ex == null) {
                            // Update the preview browser
                            this.panel.setErrorIndication(result);
                            setPageComplete(result == null);
                        } else if (ex instanceof CancellationException) {
                            // Preview was canceled, do nothing
                            return;
                        } else {
                            // Something went wrong, report the error to the user
                            this.panel.setErrorIndication(ex.toString());
                            setPageComplete(false);
                        }
        
                    }, runnable -> this.panel.getPanel().getDisplay().asyncExec(runnable));
                    return EditorsRichNote.I18N.getString("FileWizardPage.CheckInProgress");
                } else {
                    return EditorsRichNote.I18N.getString("FileWizardPage.URINotAllowed");
                }
            }
        } catch (URISyntaxException e) {
            // Invalid URI, assume it's a local file...
        }
        
        try {
            Path path = Paths.get(reference);
            RichNoteFormat mimeType = data.getChosenMimeType();
        
            // The uri has no scheme, assume it's a local file...
            if (!Files.isRegularFile(path)) {
                if (Files.isDirectory(path)) {
                    return EditorsRichNote.I18N.getString("FileWizardPage.DirectoryNotAllowed");
                } else {
                    return EditorsRichNote.I18N.getString("FileWizardPage.NotARegularFile");
                }
            } else if (!Files.isReadable(path)) {
                return EditorsRichNote.I18N.getString("FileWizardPage.NotReadableFile");
            } else if (mimeType != null) {
                if (data.getCreationMode() == CreationMode.EMBEDDED || data.getCreationMode() == CreationMode.IMPORT) {
                    for (String extension : mimeType.getFileExtensions()) {
                        if (reference.endsWith("." + extension)) {
                            return null;
                        }
                    }
                    return EditorsRichNote.I18N.getString("FileWizardPage.InvalidContentForMimeType");
                }
            }
            return null;
        } catch (InvalidPathException e2) {
            // Invalid path too
            return EditorsRichNote.I18N.getString("FileWizardPage.NotARegularFile");
        }
        
    }

    @objid ("900e73ea-9653-40aa-a982-d8aab803a83b")
    private String checkConnection(URI uri) {
        try {
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("HEAD");
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                return null;
            } else {
                return EditorsRichNote.I18N.getString("FileWizardPage.UnreachableURI");
            }
        } catch (Exception e) {
            return EditorsRichNote.I18N.getString("FileWizardPage.UnreachableURI");
        }
        
    }

}
