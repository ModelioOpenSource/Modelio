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
package org.modelio.platform.model.ui.swt.dnd;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Drag provider implementation for a viewer containing MObjects.
 * @see ModelElementTransfer
 */
@objid ("bf67977d-f861-446d-9d2c-1d94c2d7cf07")
public class MObjectViewerDragProvider implements DragSourceListener {
    @objid ("282f7a14-37ce-46bf-be35-c54d044063f2")
    private Viewer viewer;

    @objid ("45e956ee-c0a5-4763-aed8-1c10d584d23d")
    public  MObjectViewerDragProvider(Viewer viewer) {
        this.viewer = viewer;
    }

    @objid ("b6c53d21-8869-40f9-8668-268139474fac")
    @Override
    public void dragStart(DragSourceEvent event) {
        ISelection selection = this.viewer.getSelection();
        LocalSelectionTransfer.getTransfer().setSelection(selection);
        
    }

    @objid ("1275dacc-1f03-4287-8699-97d093737f72")
    @Override
    public void dragSetData(DragSourceEvent event) {
        if (ModelElementTransfer.getInstance().isSupportedType(event.dataType)) {
            ArrayList<MObject> selectedElements = new ArrayList<>();
        
            ISelection selection = this.viewer.getSelection();
        
            if (selection instanceof IStructuredSelection) {
                List<Object> selectedObjects = ((IStructuredSelection) selection).toList();
                for (Object obj : selectedObjects) {
                    MObject mobj = convertToMObject(obj);
                    if (mobj != null) {
                        selectedElements.add(mobj);
                    } else {
                        // Invalid dragged object... cancel drag
                        event.doit = false;
                        return;
                    }
                }
        
                event.data = selectedElements.toArray(new Element[selectedElements.size()]);
            } else {
                event.doit = false;
            }
        }
        
    }

    /**
     * Convert a selected element to a {@link MObject}.
     * <p>
     * May be redefined by subclasses to support proxies, adapters, ...
     * @param dataModel a IStructuredSelection element.
     * @return a MObject or null.
     */
    @objid ("64540cb1-fcc7-476b-b91e-c7dc281343e4")
    protected MObject convertToMObject(Object dataModel) {
        if (dataModel instanceof MObject) {
            return (MObject) dataModel;
        }
        
        if (dataModel instanceof IAdaptable) {
            final MObject adapter = ((IAdaptable) dataModel).getAdapter(MObject.class);
            if (adapter != null) {
                return adapter;
            }
        }
        return null;
    }

    @objid ("3cee9d83-a992-4c81-9b8e-4cd6f29f212d")
    @Override
    public void dragFinished(DragSourceEvent event) {
        // Reset the local selection buffer
        LocalSelectionTransfer.getTransfer().setSelection(null);
        
    }

}
