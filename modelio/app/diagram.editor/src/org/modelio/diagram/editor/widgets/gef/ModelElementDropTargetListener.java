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

package org.modelio.diagram.editor.widgets.gef;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("6664e8a8-33f7-11e2-95fe-001ec947c8cc")
public class ModelElementDropTargetListener extends AbstractTransferDropTargetListener {
    @objid ("6664e8a9-33f7-11e2-95fe-001ec947c8cc")
    private ModelElementDropRequest request;

    @objid ("6664e8aa-33f7-11e2-95fe-001ec947c8cc")
    private IModel coreSession;

    @objid ("6664e8ab-33f7-11e2-95fe-001ec947c8cc")
    ModelElementDropTargetListener(EditPartViewer viewer, Transfer xfer) {
        super(viewer, xfer);
    }

    @objid ("6664e8af-33f7-11e2-95fe-001ec947c8cc")
    public ModelElementDropTargetListener(EditPartViewer viewer, IModel coreSession) {
        super(viewer, ModelElementTransfer.getInstance());
        this.coreSession = coreSession;
        setEnablementDeterminedByCommand(true);
    }

    @objid ("6664e8b3-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected Request createTargetRequest() {
        ModelElementDropRequest req = new ModelElementDropRequest();
        req.setLocation(getDropLocation());
        return req;
    }

    @objid ("6664e8b8-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void updateTargetRequest() {
        ((ModelElementDropRequest) getTargetRequest()).setLocation(getDropLocation());
        setRequestDroppedElements(getCurrentEvent());
    }

    @objid ("6664e8bb-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void handleDragOver() {
        getCurrentEvent().detail = DND.DROP_COPY;
        // setRequestDroppedElements(getCurrentEvent());
        super.handleDragOver();
    }

    @objid ("6664e8be-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void handleDrop() {
        // DropTargetEvent event = getCurrentEvent();
        
        // Point dropPoint = Display.getCurrent().map(null, dropZone, event.x, event.y);
        
        super.handleDrop();
    }

    /**
     * Updates the ModelElementDropRequest dragged elements.
     * @param event the event to get the infos from.
     */
    @objid ("6664e8c1-33f7-11e2-95fe-001ec947c8cc")
    private void setRequestDroppedElements(DropTargetEvent event) {
        if (ModelElementTransfer.getInstance().isSupportedType(event.currentDataType)) {
            MObject[] droppedElements = null;
            MRef[] refs = (MRef[]) event.data;
            if (refs != null) {
                droppedElements = new MObject[refs.length];
                for (int i = 0; i < droppedElements.length; i++) {
                    droppedElements[i] = this.coreSession.findByRef(refs[i]);
                }
            }
        
            if (droppedElements == null) {
                droppedElements = getLocalDraggedElements();
            }
        
            if (droppedElements != null) {
        
                // for ( MObject element : droppedElements )
                // System.out.println( element + " Drop at (" + event.x + ", " + event.y + ")");
        
                ModelElementDropRequest req = (ModelElementDropRequest) getTargetRequest();
                req.setDroppedElements(droppedElements);
            }
            // else {
            // System.out.println("setRequestDroppedElements(DropTargetEvent event) : event.data == null");
            // }
        }
    }

    /**
     * Get the elements dragged from the same instance of Modelio. Uses {@link LocalSelectionTransfer}.
     * @see LocalSelectionTransfer
     * @return the dragged elements.
     */
    @objid ("6664e8c5-33f7-11e2-95fe-001ec947c8cc")
    private static MObject[] getLocalDraggedElements() {
        List<MObject> selectedElements = new ArrayList<>();
        
        ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            for (Iterator<?> i = structuredSelection.iterator(); i.hasNext();) {
                Object o = i.next();
                if (o instanceof IAdaptable) {
                    IAdaptable adapter = (IAdaptable) o;
                    MObject element = adapter.getAdapter(MObject.class);
                    if (element != null) {
                        selectedElements.add(element);
                    }
                } else if (o instanceof MObject) {
                    selectedElements.add((MObject) o);
                }
            }
        }
        return selectedElements.toArray(new MObject[0]);
    }

}
