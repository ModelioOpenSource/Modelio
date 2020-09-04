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

package org.modelio.diagram.editor.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.RootEditPart;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.editor.handlers.print.PrintSetupDialog;

/**
 * Handler for the com.modeliosoft.modelio.diagram.editor.printCommandId Command. Opens the PrintDialog and does some
 * Layer juggling to avoid printing the grid Layer while still printing the background image.
 * 
 * @author fpoyer
 */
@objid ("65bba199-33f7-11e2-95fe-001ec947c8cc")
public class PrintHandler {
    @objid ("65bba19b-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        if (! (part.getObject() instanceof AbstractDiagramEditor)) {
            return null;
        }
        AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
        print(editor.getRootEditPart());
        return null;
    }

    /**
     * Prints the currently shown diagram.
     * @param rootEditPart the root EditPart of the diagram to print.
     */
    @objid ("65bba1a1-33f7-11e2-95fe-001ec947c8cc")
    void print(RootEditPart rootEditPart) {
        /*int style = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getStyle();
        Shell shell = new Shell((style & SWT.MIRRORED) != 0 ? SWT.RIGHT_TO_LEFT : SWT.NONE);
        PrintDialog dialog = new PrintDialog(shell, SWT.NULL);
        PrinterData data = dialog.open();
        
        if (data != null) {
            LayerManager lm = (LayerManager) rootEditPart;
        
            // Temporarily add the background layer to the "printable layers" set so that it is present in the saved image
            IFigure backgroundLayer = lm.getLayer("BACKGROUND_LAYER");
            Layer printableLayers = (Layer) lm.getLayer(LayerConstants.PRINTABLE_LAYERS);
            printableLayers.add(backgroundLayer, "BACKGROUND_LAYER", 0);
        
            PrintGraphicalViewerOperation operation = new PrintGraphicalViewerOperation(new Printer(data),
                                                                                        (GraphicalViewer) rootEditPart.getViewer());
        
            // here you can set the Print Mode
            //operation.setPrintMode(PrintFigureOperation.TILE);
            operation.setPrintMode(PrintFigureOperation.FIT_PAGE);
        
            operation.run("Printing diagram");
        
            // Restore the background layer to its initial placement
            printableLayers.remove(backgroundLayer);
            Layer scalableLayers = (Layer) lm.getLayer(LayerConstants.SCALABLE_LAYERS);
            scalableLayers.add(backgroundLayer, "BACKGROUND_LAYER", 0);
        }*/
        PrintSetupDialog dialog =
                new PrintSetupDialog(rootEditPart);
        dialog.open();
    }

}
