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
package org.modelio.diagram.editor.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.common.abstractdiagram.ImageBuilder;

@objid ("65c52b24-33f7-11e2-95fe-001ec947c8cc")
public class SaveImageHandler {
    @objid ("65c52b2d-33f7-11e2-95fe-001ec947c8cc")
    private static final int[] filterFormats = { SWT.IMAGE_PNG, SWT.IMAGE_BMP, SWT.IMAGE_JPEG, SWT.IMAGE_GIF };

    @objid ("65c52b32-33f7-11e2-95fe-001ec947c8cc")
    static int initialFilterIndex = 0; // PNG
    

    @objid ("c2ce5a68-3896-11e2-95fe-001ec947c8cc")
    private static final String[] filterExtensions = { "*.png", "*.bmp", "*.jpg", "*.gif" };

    @objid ("c2d7e3d3-3896-11e2-95fe-001ec947c8cc")
    private static final String[] filterNames = { "PNG", "BMP", "JPEG", "GIF" };

    @objid ("c2e16d38-3896-11e2-95fe-001ec947c8cc")
    static String initialFilterPath = System.getenv("USERPROFILE");

    @objid ("65c78d49-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        if (!(part.getObject() instanceof AbstractDiagramEditor))
            return null;
        
        AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
        
        SaveInfo saveInfo = getSaveInfo(part.getLabel());
        if (saveInfo != null) {
            saveAsImage(editor.getRootEditPart(), saveInfo);
        }
        return null;
    }

    @objid ("65c78d59-33f7-11e2-95fe-001ec947c8cc")
    private void saveAsImage(RootEditPart rootEditPart, SaveInfo saveInfo) {
        ImageBuilder imageBuilder = new ImageBuilder(saveInfo.format);
        Image img = imageBuilder.makeImage(rootEditPart);
        
        if (img != null) {
            ImageLoader imgLoader = new ImageLoader();
            imgLoader.data = new ImageData[] { img.getImageData() };
            imgLoader.save(saveInfo.location, saveInfo.format);
            img.dispose();
        }
        
    }

    @objid ("65c78d5d-33f7-11e2-95fe-001ec947c8cc")
    private SaveInfo getSaveInfo(String diagramName) {
        FileDialog dlg = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
        dlg.setOverwrite(true);
        dlg.setText(DiagramEditor.I18N.getString("Gui.SaveAsImageDialog.Title"));
        dlg.setFilterExtensions(filterExtensions);
        dlg.setFilterNames(filterNames);
        dlg.setFilterIndex(initialFilterIndex);
        dlg.setFilterPath(initialFilterPath);
        dlg.setFileName(diagramName + filterExtensions[initialFilterIndex].substring(1));
        dlg.setOverwrite(true);
        
        String saveLocation = dlg.open();
        
        if (saveLocation == null)
            return null;
        
        int filterIndex = dlg.getFilterIndex();
        saveLocation = normalizeFilename(saveLocation, filterExtensions[filterIndex].substring(1));
        
        // for next usage...
        initialFilterIndex = filterIndex;
        initialFilterPath = dlg.getFilterPath();
        return new SaveInfo(saveLocation, filterFormats[filterIndex]);
    }

    @objid ("65c78d61-33f7-11e2-95fe-001ec947c8cc")
    private String normalizeFilename(String saveLocation, String fileExtension) {
        if (saveLocation.endsWith(fileExtension))
            return saveLocation;
        else
            return saveLocation + fileExtension;
        
    }

    @objid ("7a7b7b93-5e25-11e2-a8be-00137282c51b")
    @CanExecute
    public boolean canExecute() {
        // Nothing to do
        return true;
    }

    @objid ("65c78d66-33f7-11e2-95fe-001ec947c8cc")
    class SaveInfo {
        @objid ("65c78d68-33f7-11e2-95fe-001ec947c8cc")
        public int format;

        @objid ("c2ed58fa-3896-11e2-95fe-001ec947c8cc")
        public String location;

        @objid ("65c78d69-33f7-11e2-95fe-001ec947c8cc")
        public  SaveInfo(String location, int format) {
            this.location = location;
            this.format = format;
            
        }

    }

}
