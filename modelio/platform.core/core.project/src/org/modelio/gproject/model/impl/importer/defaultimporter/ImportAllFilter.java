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

package org.modelio.gproject.model.impl.importer.defaultimporter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.model.impl.importer.core.IImportFilter;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Filter that accepts all.
 */
@objid ("007a1caa-d3aa-108f-8d81-001ec947cd2a")
public class ImportAllFilter implements IImportFilter {
    @objid ("007a2f1a-d3aa-108f-8d81-001ec947cd2a")
    @Override
    public boolean select(final SmObjectImpl anObject) {
        return true;
    }

}
