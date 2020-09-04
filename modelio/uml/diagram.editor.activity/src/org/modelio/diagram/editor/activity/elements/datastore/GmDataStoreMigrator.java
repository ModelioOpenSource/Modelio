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

package org.modelio.diagram.editor.activity.elements.datastore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.elements.datastore.v0._GmDataStore;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Migrator class for GmDataStore.
 * 
 * @author fpoyer
 */
@objid ("2a2a7cee-55b6-11e2-877f-002564c97630")
public class GmDataStoreMigrator implements IPersistentMigrator {
    @objid ("2a2a7cf2-55b6-11e2-877f-002564c97630")
    @Override
    public IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
        case 0: {
            return new _GmDataStore();
        }
        default: {
            return null;
        }
        }
    }

    @objid ("2a2c037b-55b6-11e2-877f-002564c97630")
    @Override
    public IPersistent migrate(final IPersistent instanceToMigrate) {
        if (instanceToMigrate.getMajorVersion() == 0) {
            return migrateFromV0((_GmDataStore) instanceToMigrate);
        }
        return null;
    }

    @objid ("2a2c0386-55b6-11e2-877f-002564c97630")
    private IPersistent migrateFromV0(final _GmDataStore oldDataStore) {
        GmDataStore newDataStore = new GmDataStore(oldDataStore);
        
        newDataStore.setLayoutData(oldDataStore.getLayoutData());
        
        newDataStore.setRoleInComposition(oldDataStore.getRoleInComposition());
        
        GmDataStorePrimaryNode newPrimaryNode = (GmDataStorePrimaryNode) newDataStore.getMainNode();
        for (IGmLink link : oldDataStore.getStartingLinks()) {
            oldDataStore.removeStartingLink(link);
            newPrimaryNode.addStartingLink(link);
        }
        for (IGmLink link : oldDataStore.getEndingLinks()) {
            oldDataStore.removeEndingLink(link);
            newPrimaryNode.addEndingLink(link);
        }
        
        newDataStore.getPersistedStyle().setCascadedStyle(oldDataStore.getPersistedStyle().getCascadedStyle());
        for (StyleKey key : oldDataStore.getPersistedStyle().getLocalKeys()) {
            newDataStore.getDisplayedStyle().setProperty(key, oldDataStore.getDisplayedStyle().getProperty(key));
        }
        
        oldDataStore.delete();
        return newDataStore;
    }

}
