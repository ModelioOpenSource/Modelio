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

package org.modelio.diagram.browser.model.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.diagram.browser.plugin.DiagramBrowser;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;

/**
 * Element name modifier.
 */
@objid ("0035747e-0d4f-10c6-842f-001ec947cd2a")
public class DefaultLabelEditor implements ICellModifier {
    @objid ("009609ce-1f1a-10c7-842f-001ec947cd2a")
    private final TreeViewer view;

    @objid ("cd362d18-54c7-11e2-ae63-002564c97630")
    private ICoreSession iCoreSession;

    @objid ("00359d96-0d4f-10c6-842f-001ec947cd2a")
    public DefaultLabelEditor(TreeViewer view, ICoreSession iCoreSession) {
        super();
        this.view = view;
        this.iCoreSession = iCoreSession;
    }

    @objid ("0035bcc2-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public boolean canModify(Object object, String property) {
        TreeItem[] selection = this.view.getTree().getSelection();
        return (selection.length == 1 && isEditable(object));
    }

    @objid ("00361e1a-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object getValue(Object object, String property) {
        if (object instanceof DiagramSet) {
            return ((DiagramSet) object).getName();
        }
        
        if (object instanceof DiagramRef) {
            return ((DiagramRef) object).getReferencedDiagram().getName();
        }
        
        if (object instanceof AbstractDiagram) {
            return ((AbstractDiagram) object).getName();
        }
        
        DiagramBrowser.LOG.error("DefaultLabelEditor: unknown type '%s'", object.getClass().getName());
        return null;
    }

    @objid ("003685b2-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void modify(Object object, String property, Object value) {
        // Note that it is possible for an SWT Item to be passed instead of the model element.
        Object data = (object instanceof TreeItem) ? ((Item) object).getData() : object;
        
        // modify the element's property here
        Object currentValue = getValue(data, property);
        
        if (!value.equals(currentValue)) {
            if (this.iCoreSession != null) {
                final ITransactionSupport transactionManager = this.iCoreSession.getTransactionSupport();
        
                try (ITransaction transaction = transactionManager.createTransaction("Rename")) {
                    if (data instanceof DiagramSet) {
                        ((DiagramSet) data).setName((String) value);
                    }
        
                    if (data instanceof DiagramRef) {
                        ((DiagramRef) data).getReferencedDiagram().setName((String) value);
                    }
                    if (data instanceof AbstractDiagram) {
                        ((AbstractDiagram) data).setName((String) value);
                    }
        
                    transaction.commit();
                }
            }
        }
    }

    /**
     * Check if the object can theoretically be edited based on its nature. The diagram browser tree shows only three types of node:
     * DiagramSet, DiagramRef, VirtualFolder. Only the name of DiagramSet and DiagramRef can be edited.
     * 
     * @return true if the object nature allows its edition, false otherwise.
     */
    @objid ("0036fd6c-0d4f-10c6-842f-001ec947cd2a")
    private boolean isEditable(Object object) {
        return (object instanceof DiagramSet) || (object instanceof DiagramRef || (object instanceof AbstractDiagram));
    }

}
