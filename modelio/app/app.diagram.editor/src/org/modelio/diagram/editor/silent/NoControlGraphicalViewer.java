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

package org.modelio.diagram.editor.silent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.ui.parts.GraphicalViewerImpl;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

@objid ("66995c40-33f7-11e2-95fe-001ec947c8cc")
final class NoControlGraphicalViewer extends GraphicalViewerImpl {
    @objid ("20ff061b-db71-43a3-b69c-d4f60f4a5d27")
    private LocalResourceManager resources;

    @objid ("66995c41-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public Control createControl(final Composite composite) {
        if (getRootEditPart() != null) {
            getRootEditPart().activate();
        }
        getLightweightSystem().setControl(null);
        return null;
    }

    @objid ("66995c48-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected LightweightSystem createLightweightSystem() {
        return new LightweightSystemNoCanvas();
    }

    /**
     * Overridden to force activation of the new root edit part despite the fact we have no Control.
     */
    @objid ("66995c4d-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void setRootEditPart(final RootEditPart editpart) {
        super.setRootEditPart(editpart);
        editpart.activate();
    }

    @objid ("e95d4808-7690-452a-8a59-c1e17c027a39")
    @Override
    public void setContents(EditPart editpart) {
        super.setContents(editpart);
        
        // https://mantis.softeam.com/view.php?id=12765 Some elements doesn't appears in the migrated diagrams generated in the documentation
        //
        // Workaround pb with org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramLayout.layout(IFigure) :
        // If validation is done on each revalidate() call AbstractDiagramLayout.layout() does not work: the preferred size cannot be computed because
        // child figures have not been added yet.
        ((SynchronousUpdateManager) getLightweightSystem().getUpdateManager()).setSyncValidationEnabled(true);
    }

    /**
     * Remove the 'control' assert from the super class.
     */
    @objid ("dc3339a2-a713-4e7b-94a7-2bb0a552a8b6")
    @Override
    public ResourceManager getResourceManager() {
        if (this.resources != null) {
            return this.resources;
        }
        this.resources = new LocalResourceManager(JFaceResources.getResources());
        return this.resources;
    }

    /**
     * Called if and when the viewer is disposed.
     * 
     * @param e the dispose event
     */
    @objid ("181313f7-fec1-44a9-9a16-47e3a1df6670")
    @Override
    protected void handleDispose(DisposeEvent e) {
        super.handleDispose(e);
        if (this.resources != null) {
            this.resources.dispose();
        }
    }

}
