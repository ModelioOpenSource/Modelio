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

package org.modelio.diagram.editor.activity.elements.objectnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.elements.objectnode.v0._GmObjectNode;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Migrator for GmObjectNode
 * 
 * @author fpoyer
 */
@objid ("2ad6e97d-55b6-11e2-877f-002564c97630")
public class GmObjectNodeMigrator implements IPersistentMigrator {
    /**
     * Instantiate a version of the {@link IPersistent} as it was when its major version was the given parameter. The returned instance can then be used to read the serialisation string corresponding to the version without risk.
     * 
     * @param majorVersionToInstantiate the major version of the instance requested.
     * @return an instance of IPersistent at the requested version.
     */
    @objid ("2ad6e981-55b6-11e2-877f-002564c97630")
    @Override
    public IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
        case 0: {
            return new _GmObjectNode();
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
    @objid ("2ad6e98a-55b6-11e2-877f-002564c97630")
    @Override
    public IPersistent migrate(final IPersistent instanceToMigrate) {
        if (instanceToMigrate.getMajorVersion() == 0) {
            return migrateFromV0((_GmObjectNode) instanceToMigrate);
        }
        return null;
    }

    @objid ("2ad6e996-55b6-11e2-877f-002564c97630")
    private IPersistent migrateFromV0(final _GmObjectNode oldObjectNode) {
        GmObjectNode newObjectNode = new GmObjectNode(oldObjectNode);
        
        newObjectNode.setLayoutData(oldObjectNode.getLayoutData());
        
        newObjectNode.setRoleInComposition(oldObjectNode.getRoleInComposition());
        
        GmObjectNodePrimaryNode newPrimaryNode = (GmObjectNodePrimaryNode) newObjectNode.getMainNode();
        for (IGmLink link : oldObjectNode.getStartingLinks()) {
            oldObjectNode.removeStartingLink(link);
            newPrimaryNode.addStartingLink(link);
        }
        for (IGmLink link : oldObjectNode.getEndingLinks()) {
            oldObjectNode.removeEndingLink(link);
            newPrimaryNode.addEndingLink(link);
        }
        
        newObjectNode.getPersistedStyle().setCascadedStyle(oldObjectNode.getPersistedStyle().getCascadedStyle());
        for (StyleKey key : oldObjectNode.getPersistedStyle().getLocalKeys()) {
            newObjectNode.getDisplayedStyle().setProperty(key, oldObjectNode.getDisplayedStyle().getProperty(key));
        }
        
        oldObjectNode.delete();
        return newObjectNode;
    }

}
