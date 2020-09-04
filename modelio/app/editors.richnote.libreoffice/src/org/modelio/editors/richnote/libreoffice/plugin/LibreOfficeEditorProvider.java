/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.editors.richnote.libreoffice.plugin;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.editors.richnote.api.IRichNoteDiffMerger;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.api.RichNoteFormatRegistry;
import org.modelio.editors.richnote.editor.AbstractRichNoteEditorProvider;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.editors.richnote.libreoffice.editor.IEditedDocumentViewer;
import org.modelio.editors.richnote.libreoffice.editor.LibreOfficeEditor;
import org.modelio.editors.richnote.libreoffice.preferences.PreferenceConstants;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.utils.log.writers.PluginLogger;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rich note editor provider for LibreOffice.
 */
@objid ("9bc3beba-de2b-4b20-a791-5b6bcfe16c50")
public class LibreOfficeEditorProvider extends AbstractRichNoteEditorProvider {
    @objid ("b2936b6e-b322-45c4-8e8f-36a69103e853")
    private Boolean usable = null;

    @objid ("38ba9e66-6a6f-4817-a553-ff9e2f6ffc69")
    private IPropertyChangeListener preflistener;

    @objid ("d3661b35-4c72-4304-93d1-6d58977ff957")
    @Override
    public void createEmptyFile(final MObject target, RichNoteFormat format, IRichNoteFileRepository richNoteRepository) throws IOException {
        try {
            Class<? extends IEditedDocumentViewer> cl = LibreOfficeLoader.getDocumentViewerClass();
            IEditedDocumentViewer viewer = cl.newInstance();
        
            Shell sh = new Shell(Display.getCurrent());
            viewer.setFileManager(richNoteRepository);
            viewer.createPartControl(sh);
        
            // Let UI handle all pending events
            while (Display.getCurrent().readAndDispatch()) {
                /* noop */}
        
            viewer.createDocument((Document) target, format);
            viewer.close();
        
            sh.dispose();
        } catch (InstantiationException e) {
            throw new IOException(e);
        } catch (IllegalAccessException e) {
            throw new IOException(e);
        }
    }

    @objid ("9a8e1420-0cb0-4341-bdbd-f9cb0429a361")
    @Override
    public IRichNoteDiffMerger getDiffMerge() {
        return null;
    }

    @objid ("25338b9d-e3a9-463a-85a4-fb2a5c88b628")
    @Override
    public String getEditorId(final MObject target) {
        return LibreOfficeEditor.EDITOR_ID;
    }

    @objid ("b96afb51-6a38-406b-b52f-c987a570f8a5")
    @Override
    public boolean isUsable() {
        if (this.preflistener == null) {
            // install a preference change listener in the case LibreOffice path changes
            this.preflistener = event -> {
                if (event.getProperty().equals(PreferenceConstants.P_OOOPATH)) {
                    resetProvider();
                }
            };
        
            LibreOfficeEditors.PREFERENCES.addPropertyChangeListener(this.preflistener);
        }
        
        if (this.usable == null) {
            this.usable = computeUsable();
        }
        return this.usable;
    }

    /**
     * Rest usable state and notifies the format registry to update.
     */
    @objid ("84e8f1e2-7a4b-4107-887d-4d6d2e7a878a")
    void resetProvider() {
        this.usable = null;
        RichNoteFormatRegistry.getInstance().reset();
    }

    @objid ("62470fd6-3e61-43e0-bc64-e65c8b6a3659")
    private boolean computeUsable() {
        final PluginLogger log = LibreOfficeEditors.LOG;
        try {
            ClassLoader classLoader = LibreOfficeLoader.getClassLoader();
            if (classLoader == null) {
                return false;
            }
        
            // Load and run static initializer that loads the native library
            Class.forName("com.sun.star.lib.connections.pipe.PipeConnection", true, classLoader);
            
            return true;
        
        } catch (IOException e) {
            log.error("Failed loading LibreOffice/OpenOffice classes: %s", FileUtils.getLocalizedMessage(e));
            log.error(e);
        
            MessageDialog.openError(null, "Failed loading LibreOffice/OpenOffice", FileUtils.getLocalizedMessage(e));
            return false;
        } catch (ClassNotFoundException e) {
            log.warning("Failed finding LibreOffice/OpenOffice classes:");
            log.warning(e);
            return false;
        } catch (UnsatisfiedLinkError e) {
            // java.lang.UnsatisfiedLinkError: C:\Program Files (x86)\LibreOffice 3.5\URE\bin\jpipe.dll: Can't load IA 32-bit .dll on a AMD 64-bit platform
            // see : https://mantis.softeam.com/view.php?id=13227 Since Libre Office now supports 64-bits architecture, so should the Modelio's Rich Note feature...
            // see : https://mantis.softeam.com/view.php?id=11666 Open/Libre Office Rich Notes are not available with Modelio 64-bit under Windows...
            if (LibreOfficeLoader.isWindows() && System.getProperty("os.arch", "").contains("64")) {
                log.warning("LibreOffice 32bits is not supported on Windows 64 bits platforms, install the 64bits version.");
                log.warning(e.getMessage());
            } else {
                log.warning("Failed loading LibreOffice/OpenOffice classes:");
                log.warning(e);
            }
            return false;
        } catch (LinkageError e) {
            log.warning("Failed loading LibreOffice/OpenOffice classes:");
            log.warning(e);
            return false;
        }
    }

}
