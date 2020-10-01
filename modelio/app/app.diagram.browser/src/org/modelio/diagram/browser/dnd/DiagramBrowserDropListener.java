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

package org.modelio.diagram.browser.dnd;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;
import org.modelio.diagram.browser.model.core.DiagramRef;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("4ff4ece3-9128-48d7-8f58-c4cbd08196a3")
public class DiagramBrowserDropListener extends ViewerDropAdapter {
    @objid ("d5eec4c5-cb16-469e-afb1-778ead7c2a27")
    private ICoreSession session;

    @objid ("1b8208fd-d7ff-4750-9036-df0893f30c47")
    private final TreeViewer viewer;

    @objid ("ce5c0117-b1db-45fc-9c5e-fd670b91c7d5")
    public DiagramBrowserDropListener(TreeViewer viewer, ICoreSession session) {
        super(viewer);
        this.viewer = viewer;
        this.session = session;
    }

    @objid ("28afdfb5-c670-46dd-9408-00d1d83fafe9")
    @Override
    public boolean performDrop(final Object data) {
        if (data instanceof IStructuredSelection) {
        
            IStructuredSelection selection = (IStructuredSelection) data;
            List<?> draggedObjects = selection.toList();
            perfromDrop(draggedObjects, getCurrentTarget());
        
            return true;
        }
        return false;
    }

    @objid ("a6a19a4c-629a-459c-a8a5-06722e3c6648")
    private void perfromDrop(List<?> draggedObjects, Object currentTarget) {
        DiagramSet targetSet = (DiagramSet) currentTarget;
        
        for (Object source : draggedObjects) {
            if (source instanceof AbstractDiagram) {
                if (!targetSet.getReferencedDiagram().contains(source)) {
                    try (ITransaction tr = this.session.getTransactionSupport().createTransaction("CREATE DiagraRef")) {
                        targetSet.getReferencedDiagram().add((AbstractDiagram) source);
                        tr.commit();
                        this.viewer.refresh();
                    }
                }
            } else if (source instanceof DiagramSet) {
                DiagramSet sourceSet = (DiagramSet) source;
                if (!isInSameComposition(sourceSet, targetSet)) {
                    try (ITransaction tr = this.session.getTransactionSupport().createTransaction("CREATE DiagraRef")) {
                        targetSet.getSub().add(sourceSet);
                        tr.commit();
                        this.viewer.refresh();
                    }
                }
            } else if (source instanceof DiagramRef) {
                DiagramRef diagramRef = (DiagramRef) source;
        
                try (ITransaction tr = this.session.getTransactionSupport().createTransaction("CREATE DiagraRef")) {
                    
                    diagramRef.getReferenceOwner().getReferencedDiagram().remove(diagramRef.getReferencedDiagram());
                    targetSet.getReferencedDiagram().add(diagramRef.getReferencedDiagram());
                    tr.commit();
                    this.viewer.refresh();
                }
        
            }
        }
    }

    @objid ("1e0e48c4-2b22-4090-a82d-3bbdb5dc3e21")
    private boolean isInSameComposition(DiagramSet sourceSet, DiagramSet targetSet) {
        MObject var = targetSet;
        while (var != null) {
            var = var.getCompositionOwner();
            if (sourceSet.equals(var))
                return true;
        }
        return false;
    }

    @objid ("3b1fb7d0-88ac-474a-a641-293e311f406a")
    @Override
    public boolean validateDrop(final Object target, int operation, TransferData transferType) {
        if (LocalSelectionTransfer.getTransfer().isSupportedType(transferType)) {
            ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
            if (selection instanceof IStructuredSelection) {
                List<?> draggedObjects = ((IStructuredSelection) selection).toList();
                return isValidDrop(draggedObjects, target);
            }
        }
        return false;
    }

    @objid ("cbab46cc-de9c-4659-bac3-e791a91fd426")
    private boolean isValidDrop(List<?> draggedObjects, Object target) {
        if (target instanceof DiagramSet) {
            for (Object dg : draggedObjects) {
                if (dg instanceof AbstractDiagram || dg instanceof DiagramSet || dg instanceof DiagramRef) {
                    return true;
                }
        
            }
        }
        return false;
    }

}
