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
package org.modelio.core.modelshield.internal;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.modelshield.ModelShield;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelError;

/**
 * Error report of a {@link ModelShield} check.
 */
@objid ("002804cc-0000-0008-0000-000000000000")
public class ErrorReport implements IErrorReport {
    @objid ("0071ef08-8f78-1f4b-b2b8-001ec947cd2a")
    private final List<IModelError> entries;

    @objid ("00280b20-0000-0003-0000-000000000000")
    public  ErrorReport() {
        this.entries = new ArrayList<>();
    }

    @objid ("002806e4-0000-0212-0000-000000000000")
    @Override
    public void addEntry(final IModelError anEntry) {
        this.entries.add(anEntry);
    }

    @objid ("002804f8-0000-06a7-0000-000000000000")
    @Override
    public List<IModelError> getEntries() {
        return this.entries;
    }

    @objid ("00515932-524f-1036-812a-001ec947cd2a")
    @Override
    public boolean isFailed() {
        return this.entries.size() > 0;
    }

}
