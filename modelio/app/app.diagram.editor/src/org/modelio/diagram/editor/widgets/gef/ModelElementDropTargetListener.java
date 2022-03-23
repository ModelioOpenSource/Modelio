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
package org.modelio.diagram.editor.widgets.gef;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Drop listener that supports {@link ModelElementTransfer} and {@link LocalSelectionTransfer}.
 * <p>
 * Use static constructors to get an instance for both types.
 */
@objid ("6664e8a8-33f7-11e2-95fe-001ec947c8cc")
public class ModelElementDropTargetListener extends AbstractTransferDropTargetListener {
    @objid ("6664e8a9-33f7-11e2-95fe-001ec947c8cc")
    private ModelElementDropRequest request;

    @objid ("6664e8aa-33f7-11e2-95fe-001ec947c8cc")
    private final IModel coreSession;

    @objid ("6664e8af-33f7-11e2-95fe-001ec947c8cc")
    protected  ModelElementDropTargetListener(EditPartViewer viewer, IModel coreSession, Transfer xfer) {
        super(viewer, xfer);
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
        //System.out.println("updateTargetRequest() for "+getTransfer().getClass().getSimpleName());
        ((ModelElementDropRequest) getTargetRequest()).setLocation(getDropLocation());
        setRequestDroppedElements(getCurrentEvent());
        
    }

    @objid ("6664e8bb-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void handleDragOver() {
        //System.out.println("begin handleDragOver() for "+getTransfer().getClass().getSimpleName()+" ep="+getTargetEditPart());
        
        getCurrentEvent().detail = DND.DROP_COPY;
        // setRequestDroppedElements(getCurrentEvent());
        super.handleDragOver();
        
        //System.out.println(" end handleDragOver() for "+getTransfer().getClass().getSimpleName()+" ep="+getTargetEditPart());
        
    }

    /**
     * Updates the ModelElementDropRequest dragged elements.
     * @param event the event to get the infos from.
     */
    @objid ("6664e8c1-33f7-11e2-95fe-001ec947c8cc")
    private void setRequestDroppedElements(DropTargetEvent event) {
        MObject[] droppedElements = null;
        
        if (ModelElementTransfer.getInstance().isSupportedType(event.currentDataType)) {
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
        
        } else if (LocalSelectionTransfer.getTransfer().isSupportedType(event.currentDataType)) {
            droppedElements = getLocalDraggedElements();
        }
        
        if (droppedElements != null) {
        
            final boolean DEBUG = false;
            if (DEBUG) {
                System.out.println( getTransfer().getClass().getSimpleName() + " Drop at (" + event.x + ", " + event.y + "):");
                for ( MObject element : droppedElements ) {
                    System.out.println( "  - " + element );
                }
            }
        
            ModelElementDropRequest req = (ModelElementDropRequest) getTargetRequest();
            req.setDroppedElements(droppedElements);
        }
        
    }

    /**
     * Get the elements dragged from the same instance of Modelio. Uses {@link LocalSelectionTransfer}.
     * @return the dragged elements.
     */
    @objid ("6664e8c5-33f7-11e2-95fe-001ec947c8cc")
    private static MObject[] getLocalDraggedElements() {
        ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
        List<MObject> selectedElements = SelectionHelper.toList(selection, MObject.class);
        return selectedElements.toArray(new MObject[0]);
    }

    /**
     * Get an instance for {@link ModelElementTransfer}.
     * @param viewer an EditPartViewer
     * @param coreSession the Interface used to access the model
     * @return a {@link ModelElementDropTargetListener}
     * @since 4.0
     */
    @objid ("3f78e4a6-db06-47e4-8ec9-8e4c7eaec21f")
    public static ModelElementDropTargetListener forModelElementTransfer(EditPartViewer viewer, IModel coreSession) {
        return new ModelElementDropTargetListener(viewer, coreSession, ModelElementTransfer.getInstance());
    }

    /**
     * Get an instance for {@link LocalSelectionTransfer}.
     * @param viewer an EditPartViewer
     * @param coreSession the Interface used to access the model
     * @return a {@link ModelElementDropTargetListener}
     * @since 4.0
     */
    @objid ("0caa444f-538c-4453-967a-2b7df782d351")
    public static ModelElementDropTargetListener forLocalSelectionTransfer(EditPartViewer viewer, IModel coreSession) {
        return new ModelElementDropTargetListener(viewer, coreSession, LocalSelectionTransfer.getTransfer());
    }

}
