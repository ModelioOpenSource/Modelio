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

package org.modelio.diagram.editor.activity.elements.centralbuffer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.elements.centralbuffer.v0._GmCentralBuffer;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Migrator class for GmCentralBuffer.
 * 
 * @author fpoyer
 */
@objid ("29e5d2eb-55b6-11e2-877f-002564c97630")
public class GmCentralBufferMigrator implements IPersistentMigrator {
    /**
     * Instantiate a version of the {@link IPersistent} as it was when its major version was the given parameter. The returned instance can then be used to read the serialisation string corresponding to the version without risk.
     * @param majorVersionToInstantiate the major version of the instance requested.
     * @return an instance of IPersistent at the requested version.
     */
    @objid ("29e5d2ef-55b6-11e2-877f-002564c97630")
    @Override
    public IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
        case 0: {
            return new _GmCentralBuffer();
        }
        default: {
            return null;
        }
        }
    }

    /**
     * Returns an instance of IPersistent with the most recent major version, using as much information from the given IPersistent as possible.
     * @param instanceToMigrate an instance of a previous major version to be used as source of information.
     * @return an instance of IPersistent with the most recent major version based on the given instance.
     */
    @objid ("29e5d2f8-55b6-11e2-877f-002564c97630")
    @Override
    public IPersistent migrate(final IPersistent instanceToMigrate) {
        if (instanceToMigrate.getMajorVersion() == 0) {
            return migrateFromV0((_GmCentralBuffer) instanceToMigrate);
        }
        return null;
    }

    @objid ("29e75963-55b6-11e2-877f-002564c97630")
    private IPersistent migrateFromV0(final _GmCentralBuffer oldCentralBuffer) {
        GmCentralBuffer newCentralBuffer = new GmCentralBuffer(oldCentralBuffer);
        
        newCentralBuffer.setLayoutData(oldCentralBuffer.getLayoutData());
        
        newCentralBuffer.setRoleInComposition(oldCentralBuffer.getRoleInComposition());
        
        GmCentralBufferPrimaryNode newPrimaryNode = (GmCentralBufferPrimaryNode) newCentralBuffer.getMainNode();
        for (IGmLink link : oldCentralBuffer.getStartingLinks()) {
            oldCentralBuffer.removeStartingLink(link);
            newPrimaryNode.addStartingLink(link);
        }
        for (IGmLink link : oldCentralBuffer.getEndingLinks()) {
            oldCentralBuffer.removeEndingLink(link);
            newPrimaryNode.addEndingLink(link);
        }
        
        newCentralBuffer.getPersistedStyle().setCascadedStyle(oldCentralBuffer.getPersistedStyle().getCascadedStyle());
        for (StyleKey key : oldCentralBuffer.getPersistedStyle().getLocalKeys()) {
            newCentralBuffer.getDisplayedStyle().setProperty(key, oldCentralBuffer.getDisplayedStyle().getProperty(key));
        }
        
        oldCentralBuffer.delete();
        return newCentralBuffer;
    }

}
