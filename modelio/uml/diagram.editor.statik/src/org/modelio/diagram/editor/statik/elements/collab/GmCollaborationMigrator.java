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

package org.modelio.diagram.editor.statik.elements.collab;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.collab.v0._GmCollaboration;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Migrator class for GmCollaboration
 * 
 * @author fpoyer
 */
@objid ("345b5312-55b7-11e2-877f-002564c97630")
public class GmCollaborationMigrator implements IPersistentMigrator {
    /**
     * Instantiate a version of the {@link IPersistent} as it was when its major version was the given parameter. The returned instance can then be used to read the serialisation string corresponding to the version without risk.
     * 
     * @param majorVersionToInstantiate the major version of the instance requested.
     * @return an instance of IPersistent at the requested version.
     */
    @objid ("345b5316-55b7-11e2-877f-002564c97630")
    @Override
    public IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
        case 0: {
            return new _GmCollaboration();
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
    @objid ("345b531f-55b7-11e2-877f-002564c97630")
    @Override
    public IPersistent migrate(final IPersistent instanceToMigrate) {
        if (instanceToMigrate.getMajorVersion() == 0) {
            return migrateFromV0((_GmCollaboration) instanceToMigrate);
        }
        return null;
    }

    @objid ("345b532b-55b7-11e2-877f-002564c97630")
    private IPersistent migrateFromV0(final _GmCollaboration oldCollaboration) {
        GmCollaboration newCollaboration = new GmCollaboration(oldCollaboration);
        
        newCollaboration.setLayoutData(oldCollaboration.getLayoutData());
        
        newCollaboration.setRoleInComposition(oldCollaboration.getRoleInComposition());
        
        GmCollaborationPrimaryNode newPrimaryNode = (GmCollaborationPrimaryNode) newCollaboration.getMainNode();
        for (IGmLink link : oldCollaboration.getStartingLinks()) {
            oldCollaboration.removeStartingLink(link);
            newPrimaryNode.addStartingLink(link);
        }
        for (IGmLink link : oldCollaboration.getEndingLinks()) {
            oldCollaboration.removeEndingLink(link);
            newPrimaryNode.addEndingLink(link);
        }
        
        newCollaboration.getPersistedStyle().setCascadedStyle(oldCollaboration.getPersistedStyle().getCascadedStyle());
        for (StyleKey key : oldCollaboration.getPersistedStyle().getLocalKeys()) {
            newCollaboration.getDisplayedStyle().setProperty(key, oldCollaboration.getDisplayedStyle().getProperty(key));
        }
        
        oldCollaboration.delete();
        return newCollaboration;
    }

}
