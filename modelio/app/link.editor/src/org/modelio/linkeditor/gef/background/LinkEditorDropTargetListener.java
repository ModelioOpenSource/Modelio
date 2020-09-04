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

package org.modelio.linkeditor.gef.background;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("1ba1eb1f-5e33-11e2-b81d-002564c97630")
public class LinkEditorDropTargetListener extends AbstractTransferDropTargetListener {
    @objid ("1ba1eb22-5e33-11e2-b81d-002564c97630")
    public static final String DROPPED_ELEMENTS = "dropped elements";

    @objid ("d4c6aa24-5efd-11e2-a8be-00137282c51b")
    private IProjectService projectService;

    @objid ("1ba1eb24-5e33-11e2-b81d-002564c97630")
    public LinkEditorDropTargetListener(final EditPartViewer viewer, IProjectService projectService) {
        super(viewer, ModelElementTransfer.getInstance());
        this.setEnablementDeterminedByCommand(true);
        this.projectService = projectService;
    }

    @objid ("1ba1eb2a-5e33-11e2-b81d-002564c97630")
    public LinkEditorDropTargetListener(final EditPartViewer viewer, final Transfer xfer) {
        super(viewer, xfer);
    }

    @objid ("1ba1eb32-5e33-11e2-b81d-002564c97630")
    @Override
    protected CreateRequest createTargetRequest() {
        return new CreateRequest(RequestConstants.REQ_CREATE);
    }

    @objid ("1ba1eb39-5e33-11e2-b81d-002564c97630")
    @Override
    protected void updateTargetRequest() {
        this.getTargetRequest().setLocation(this.getDropLocation());
        this.setRequestDroppedElements(this.getCurrentEvent());
    }

    /**
     * Get the elements dragged from the same instance of Modelio. Uses {@link LocalSelectionTransfer}.
     * @see LocalSelectionTransfer
     * @return the dragged elements.
     */
    @objid ("1ba1eb3c-5e33-11e2-b81d-002564c97630")
    private MObject[] getLocalDraggedElements() {
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

    @objid ("1ba1eb43-5e33-11e2-b81d-002564c97630")
    @Override
    protected CreateRequest getTargetRequest() {
        return (CreateRequest) super.getTargetRequest();
    }

    /**
     * Updates the ModelElementDropRequest dragged elements.
     * @param event the event to get the infos from.
     */
    @objid ("1ba1eb4a-5e33-11e2-b81d-002564c97630")
    @SuppressWarnings("unchecked")
    private void setRequestDroppedElements(final DropTargetEvent event) {
        if (ModelElementTransfer.getInstance().isSupportedType(event.currentDataType)) {
            ICoreSession session = this.projectService.getSession();
        
            MObject[] droppedElements = null;
            MRef[] refs = (MRef[]) event.data;
            if (refs != null) {
                droppedElements = new MObject[refs.length];
                for (int i = 0; i < droppedElements.length; i++) {
                    droppedElements[i] = session.getModel().findByRef(refs[i], IModel.NODELETED);
                }
            }
        
            if (droppedElements == null) {
                droppedElements = this.getLocalDraggedElements();
            }
            if (droppedElements != null) {
                this.getTargetRequest().getExtendedData().put(LinkEditorDropTargetListener.DROPPED_ELEMENTS, droppedElements);
            }
        
        }
    }

}
