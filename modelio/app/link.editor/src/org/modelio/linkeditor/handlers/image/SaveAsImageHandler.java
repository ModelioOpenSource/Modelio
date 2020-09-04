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

package org.modelio.linkeditor.handlers.image;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.modelio.linkeditor.panel.ILinkEditor;
import org.modelio.linkeditor.plugin.LinkEditor;
import org.modelio.linkeditor.view.ILinkEditorView;

/**
 * @author fpoyer
 */
@objid ("1b477738-5e33-11e2-b81d-002564c97630")
public class SaveAsImageHandler {
    @objid ("1b47773a-5e33-11e2-b81d-002564c97630")
    private static final String[] filterExtensions = { ".png", ".bmp", ".jpg", ".gif" };

    @objid ("1b47773e-5e33-11e2-b81d-002564c97630")
    private static final String[] filterNames = { "PNG", "BMP", "JPEG", "GIF" };

    @objid ("1b477742-5e33-11e2-b81d-002564c97630")
    private static final int[] filterFormats = { SWT.IMAGE_PNG, SWT.IMAGE_BMP, SWT.IMAGE_JPEG, SWT.IMAGE_GIF };

    @objid ("1b477746-5e33-11e2-b81d-002564c97630")
     static String initialFilterPath = System.getenv("USERPROFILE");

    @objid ("1b477747-5e33-11e2-b81d-002564c97630")
     static int initialFilterIndex = 0; // PNG

    @objid ("1b477749-5e33-11e2-b81d-002564c97630")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        if (!(part.getObject() instanceof ILinkEditorView)) {
            return null;
        }
        
        ILinkEditor editor = ((ILinkEditorView) part.getObject()).getLinkEditor();
        SaveInfo saveInfo = getSaveInfo();
        if (saveInfo != null) {
        
            Image img = editor.getImage();
            if (img != null) {
                ImageLoader imgLoader = new ImageLoader();
                imgLoader.data = new ImageData[] { img.getImageData() };
                imgLoader.save(saveInfo.location, saveInfo.format);
                img.dispose();
            }
        }
        return null;
    }

    @objid ("1b49d84f-5e33-11e2-b81d-002564c97630")
    private SaveInfo getSaveInfo() {
        FileDialog dlg = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
        dlg.setOverwrite(true);
        dlg.setText(LinkEditor.I18N.getString("Gui.SaveAsImageDialog.Title"));
        dlg.setFilterExtensions(filterExtensions);
        dlg.setFilterNames(filterNames);
        dlg.setFilterIndex(initialFilterIndex);
        dlg.setFilterPath(initialFilterPath);
        dlg.setFileName("*" + filterExtensions[initialFilterIndex]);
        dlg.setOverwrite(true);
        
        String saveLocation = dlg.open();
        
        if (saveLocation == null)
            return null;
        
        int filterIndex = dlg.getFilterIndex();
        saveLocation = normalizeFilename(saveLocation, filterExtensions[filterIndex]);
        
        // for next usage...
        initialFilterIndex = filterIndex;
        initialFilterPath = dlg.getFilterPath();
        return new SaveInfo(saveLocation, filterFormats[filterIndex]);
    }

    @objid ("1b49d853-5e33-11e2-b81d-002564c97630")
    private String normalizeFilename(final String saveLocation, final String fileExtension) {
        if (saveLocation.endsWith(fileExtension))
            return saveLocation;
        else
            return saveLocation + fileExtension;
    }

    @objid ("1b49d85a-5e33-11e2-b81d-002564c97630")
    private static class SaveInfo {
        @objid ("1b49d85b-5e33-11e2-b81d-002564c97630")
        public String location;

        @objid ("1b49d85c-5e33-11e2-b81d-002564c97630")
        public int format;

        @objid ("1b49d85d-5e33-11e2-b81d-002564c97630")
        public SaveInfo(final String location, final int format) {
            this.location = location;
            this.format = format;
        }

    }

}
