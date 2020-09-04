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

package org.modelio.diagram.elements.common.label.modelelement;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;

/**
 * Migrator that converts {@link GmDefaultFlatHeader} to {@link GmDefaultModelElementLabel}.
 * @author cmarin
 */
@objid ("431b54e4-676a-471a-a934-8c8c5bdfbe9e")
public class GmDefaultFlatHeaderMigrator implements IPersistentMigrator {
    @objid ("c14a898d-55b6-11e2-877f-002564c97630")
    @Override
    public IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
            case 0: {
                // Directly instantiate a GmDefaultModelElementLabel,
                // it can read GmDefaultFlatHeader datas
                return new GmDefaultModelElementLabel();
            }
            default: {
                return null;
            }
        }
    }

    @objid ("c14a8996-55b6-11e2-877f-002564c97630")
    @Override
    public IPersistent migrate(final IPersistent instanceToMigrate) {
        if (instanceToMigrate instanceof GmDefaultModelElementLabel) {
            // 'instanceToMigrate' is already a  GmDefaultModelElementLabel migrated from GmDefaultFlatHeader V0,
            // instantiated by createInstanceOfMajorVersion(0)
            GmDefaultModelElementLabel migratedLabel = (GmDefaultModelElementLabel) instanceToMigrate;
            Object ld = migratedLabel.getLayoutData();
            if (ld instanceof Rectangle) {
                // Set height as unlimited because the label may now be wrapped
                ((Rectangle) ld).setHeight(-1);
            }
        
            return instanceToMigrate;
        }
        return null;
    }

}
