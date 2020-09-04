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

package org.modelio.diagram.editor.statik.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.packaze.v0._GmPackage;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Migrator class for GmPackage.
 * 
 * @author fpoyer
 */
@objid ("362082c2-55b7-11e2-877f-002564c97630")
public class GmPackageMigrator implements IPersistentMigrator {
    /**
     * Instantiate a version of the {@link IPersistent} as it was when its major version was the given parameter. The returned instance can then be used to read the serialisation string corresponding to the version without risk.
     * 
     * @param majorVersionToInstantiate the major version of the instance requested.
     * @return an instance of IPersistent at the requested version.
     */
    @objid ("362082c6-55b7-11e2-877f-002564c97630")
    @Override
    public IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
        case 0: {
            return new _GmPackage();
        }
        default: {
            return null;
        }
        }
    }

    /**
     * Returns an instance of IPersistent with the most recent major version, using as much information from the given IPersistent as possible.
     * 
     * @param instanceToMigrate an instance of a previous major version to be used as source of information.
     * @return an instance of IPersistent with the most recent major version based on the given instance.
     */
    @objid ("362082cf-55b7-11e2-877f-002564c97630")
    @Override
    public IPersistent migrate(final IPersistent instanceToMigrate) {
        if (instanceToMigrate.getMajorVersion() == 0) {
            return migrateFromV0((_GmPackage) instanceToMigrate);
        }
        return null;
    }

    @objid ("362082db-55b7-11e2-877f-002564c97630")
    private IPersistent migrateFromV0(final _GmPackage oldPackage) {
        GmPackage newPackage = new GmPackage(oldPackage);
        
        newPackage.setLayoutData(oldPackage.getLayoutData());
        
        newPackage.setRoleInComposition(oldPackage.getRoleInComposition());
        
        GmPackagePrimaryNode newPrimaryNode = (GmPackagePrimaryNode) newPackage.getMainNode();
        for (IGmLink link : oldPackage.getStartingLinks()) {
            oldPackage.removeStartingLink(link);
            newPrimaryNode.addStartingLink(link);
        }
        for (IGmLink link : oldPackage.getEndingLinks()) {
            oldPackage.removeEndingLink(link);
            newPrimaryNode.addEndingLink(link);
        }
        
        newPackage.getPersistedStyle().setCascadedStyle(oldPackage.getPersistedStyle().getCascadedStyle());
        for (StyleKey key : oldPackage.getPersistedStyle().getLocalKeys()) {
            newPackage.getDisplayedStyle().setProperty(key, oldPackage.getDisplayedStyle().getProperty(key));
        }
        
        oldPackage.delete();
        return newPackage;
    }

}
