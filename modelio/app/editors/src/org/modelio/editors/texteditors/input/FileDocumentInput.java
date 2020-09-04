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

package org.modelio.editors.texteditors.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.modelio.editors.plugin.TextEditors;
import org.modelio.editors.service.IEditorListener;

/**
 * {@link IDocumentInput} implementation based on a {@link File}.
 */
@objid ("7b5333e0-2a77-11e2-9fb9-bc305ba4815c")
public class FileDocumentInput extends AbstractInput implements IDocumentInput {
    @objid ("7b5333e1-2a77-11e2-9fb9-bc305ba4815c")
    private long cachedModificationStamp;

    @objid ("7b5333e9-2a77-11e2-9fb9-bc305ba4815c")
    private static final int DEFAULT_FILE_SIZE = 15 * 1024;

    @objid ("ab520472-2a77-11e2-9fb9-bc305ba4815c")
    private static final String DEFAULT_CHARSET_NAME = "UTF-8"; // $NON-NLS-1$

    @objid ("cfa4aa8b-6b95-42e6-a023-c9d0b517dd6c")
    private String charsetName;

    @objid ("7b5333e2-2a77-11e2-9fb9-bc305ba4815c")
    private File file;

    @objid ("7b5333e3-2a77-11e2-9fb9-bc305ba4815c")
    private IDocument document;

    @objid ("c1ea9a7e-2e5d-11e2-a8ff-bc305ba4815c")
    private IEditorListener saveListener;

    @objid ("1e380aee-e4b5-4e30-b13d-63c872a3d7bd")
    private DocumentListener docListener;

    /**
     * @param file the file to edit.
     */
    @objid ("7b5333eb-2a77-11e2-9fb9-bc305ba4815c")
    public FileDocumentInput(File file) {
        this.file = file;
        this.charsetName = DEFAULT_CHARSET_NAME;
    }

    @objid ("7b5333ee-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public File getFile() {
        return this.file;
    }

    @objid ("7b533401-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IStatus save() {
        if (this.file.exists()) {
            if (this.file.lastModified() != this.cachedModificationStamp) {
                String[] tab = { TextEditors.I18N.getString("FileDocumentInput.choice.OverwriteDiskContent"),
                        TextEditors.I18N.getString("FileDocumentInput.choice.ReloadFromDisk"),
                        TextEditors.I18N.getString("FileDocumentInput.choice.Cancel") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                MessageDialog dialog = new MessageDialog(null,
                        TextEditors.I18N.getString("FileDocumentInput.OutOfSyncFile.title"), null, //$NON-NLS-1$
                        TextEditors.I18N.getString("FileDocumentInput.OutOfSyncFile.message"),
                        MessageDialog.INFORMATION, tab, 0); //$NON-NLS-1$
                int choice = dialog.open();
                switch (choice) {
                case 0: // overwrite
                    doSave();
                    break;
                case 1: // reload from disk
                    doLoad();
                    break;
                default:
                case 2: // cancel
                    break;
                }
                return null;
            }
        }
        
        doSave();
        return null;
    }

    @objid ("7b533405-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IDocument getDocument(IDocument inputDocument) {
        if (this.document == null) {
            if (inputDocument == null) {
                this.document = new Document();
            } else {
                this.document = inputDocument;
            }
        
            doLoad();
        }
        return this.document;
    }

    @objid ("7b53340a-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void doSave() {
        try{
            byte[] utf8Bytes = this.document.get().getBytes(this.charsetName);
            this.file.createNewFile();
            try (OutputStream out = new FileOutputStream(this.file)){
        
                out.write(utf8Bytes);
                out.close();
                setDirty(false);
                this.cachedModificationStamp = this.file.lastModified();
            }
        } catch (IOException e) {
            TextEditors.LOG.error(e);
        }
        
        if(this.saveListener != null){
            this.saveListener.documentSaved(this.file);
        }
    }

    @objid ("7b53340c-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void doLoad() {
        try (
                InputStream contentStream = new FileInputStream(this.file);
                Reader in = new BufferedReader(new InputStreamReader(contentStream, this.charsetName), DEFAULT_FILE_SIZE)){
            StringBuffer buffer = new StringBuffer(DEFAULT_FILE_SIZE);
            char[] readBuffer = new char[2048];
            int n = in.read(readBuffer);
            while (n > 0) {
                buffer.append(readBuffer, 0, n);
                n = in.read(readBuffer);
            }
        
            // Avoid set dirty on reload.
            if (this.docListener != null) {
                this.document.removeDocumentListener(this.docListener);
            }
        
            this.document.set(buffer.toString());
            this.cachedModificationStamp = this.file.lastModified();
        
            if (this.docListener == null) {
                this.docListener = new DocumentListener();
            }
        
            this.document.addDocumentListener(this.docListener);
        
            setDirty(false);
        } catch (IOException x) {
            TextEditors.LOG.error(x);
        }
    }

    @objid ("c1ea9a83-2e5d-11e2-a8ff-bc305ba4815c")
    @Override
    public void setSaveListener(IEditorListener saveListener) {
        this.saveListener = saveListener;
    }

    @objid ("c1ea9a86-2e5d-11e2-a8ff-bc305ba4815c")
    @Override
    public void dispose() {
        if(this.saveListener != null){
            this.saveListener.editorClosed();
        }
    }

    @objid ("43af09fc-98e9-4d24-8c4d-969499cf9bd3")
    @Override
    public String getCharsetName() {
        return this.charsetName;
    }

    /**
     * Change current charset and reload the file.
     */
    @objid ("d9cb5402-b45e-4bf0-a542-01af378caa7d")
    @Override
    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
        doLoad();
    }

    @objid ("108d1992-f6be-4702-b2e2-5bc167f3eaf3")
    private final class DocumentListener implements IDocumentListener {
        @objid ("f48b5239-68fa-4ca0-a3d7-59a99691f1bb")
        public DocumentListener() {
            // none
        }

        @objid ("03037d5c-8c8c-46a9-a258-425880322299")
        @Override
        public void documentChanged(DocumentEvent event) {
            setDirty(true);
        }

        @objid ("bdf4a4b7-a995-4bc1-93ac-6cd160de16aa")
        @Override
        public void documentAboutToBeChanged(DocumentEvent event) {
            // ignore
        }

    }

}
