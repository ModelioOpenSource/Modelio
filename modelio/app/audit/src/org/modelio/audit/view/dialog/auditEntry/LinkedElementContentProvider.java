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

package org.modelio.audit.view.dialog.auditEntry;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * This class provide the element to display in the linked element list.
 */
@objid ("46cc4e03-8480-48e0-9730-4f6ebd249c5a")
public class LinkedElementContentProvider implements IStructuredContentProvider {
    @objid ("a7c87233-f82d-4887-9bef-0b02abf9cdb4")
    @Override
    public void dispose() {
        // Empty
    }

    @objid ("db03a942-7bac-4782-afd6-eaf275488814")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Empty
    }

    /**
     * Return the elements that must be displayed in the linked list.
     */
    @objid ("4ca4930c-4e5f-4e53-a7e6-01991a4a68a5")
    @Override
    public Object[] getElements(Object inputElement) {
        IAuditEntry entry = (IAuditEntry) inputElement;
        
        ArrayList<Object> linkedElements = new ArrayList<>();
        for (Object o : entry.getLinkedObjects()) {
            if (o instanceof Element) {
                linkedElements.add(o);
            }
        }
        return linkedElements.toArray();
    }

}
