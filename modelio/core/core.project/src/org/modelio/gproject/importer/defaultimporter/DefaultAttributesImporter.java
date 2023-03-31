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
package org.modelio.gproject.importer.defaultimporter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.importer.core.IAttributesImporter;
import org.modelio.gproject.importer.core.IObjectFinder;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Default {@link IAttributesImporter} implementation.
 */
@objid ("007b0412-d3aa-108f-8d81-001ec947cd2a")
public class DefaultAttributesImporter implements IAttributesImporter {
    @objid ("45fbdd09-649c-49d7-b718-89e9fbc15e4e")
    private final IObjectFinder objectFinder;

    @objid ("cdc775da-8e61-45b8-b860-5f9e82d0cb54")
    public  DefaultAttributesImporter(IObjectFinder objectFinder) {
        this.objectFinder = objectFinder;
    }

    @objid ("007b14de-d3aa-108f-8d81-001ec947cd2a")
    @Override
    public void importAttributes(final SmObjectImpl refObject, SmObjectImpl localObject) {
        SmClass cls = (SmClass) localObject.getMClass();
        
        for (SmAttribute attr : cls.getAllAttDef()) {
            if (attr.getName().equals("Uuid")) {
                // Ignore the Uuid
            } else {
                SmAttribute localAttr = this.objectFinder.getSameAttribute(attr);
                if (localAttr != null) {
                    Object oldVal = localObject.getAttVal(localAttr);
                    Object newVal = refObject.getAttVal(attr);
                    if (oldVal != newVal) {
                        localObject.setAttVal(localAttr, newVal);
                    }
                }
            }
        }
        
    }

}
