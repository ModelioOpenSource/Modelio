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

package org.modelio.diagram.elements.umlcommon.diagramholder;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Fake GM needed for migration purpose.
 * 
 * @since 3.8
 * @see GmDiagramHolderMigrator
 */
@objid ("98430753-38ca-4940-b434-5336f8309914")
public class GmDiagramHolder implements IPersistent {
    @objid ("a1637445-a98b-4ceb-a0fd-3e267df4555e")
    private static final int MAJOR_VERSION = 1;

    @objid ("465bc024-d303-4373-8f55-6b50bcd1802b")
    @Override
    public void read(IDiagramReader in) {
        // do nothing
    }

    @objid ("2982a9a9-0d30-4237-a77b-1f963fe63ab2")
    @Override
    public void write(IDiagramWriter out) {
        // do nothing
    }

    @objid ("bd6d190a-1c82-410f-b30d-f977c483888d")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("1ad3caa4-7e55-4dc9-b5df-9cecf35b434b")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
