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

package org.modelio.diagram.elements.umlcommon.diagramholder;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.umlcommon.diagramholder.v0._GmDiagramHolder;
import org.modelio.diagram.elements.umlcommon.diagramview.GmDiagramView;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Migrator class for {@link GmDiagramHolder}.
 */
@objid ("11773b83-c8b1-459b-a6ef-29280e893e12")
@SuppressWarnings ("deprecation")
public class GmDiagramHolderMigrator implements IPersistentMigrator {
    @objid ("9742e298-838d-41f9-a5fa-4614d4536324")
    @Override
    public IPersistent createInstanceOfMajorVersion(int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
        case 0: {
            return new _GmDiagramHolder();
        }
        default: {
            return null;
        }
        }
    }

    @objid ("09cb4022-72bb-4dce-8704-205c7cc25489")
    @Override
    public IPersistent migrate(IPersistent instanceToMigrate) {
        if (instanceToMigrate.getMajorVersion() == 0) {
            return migrateFromV0((_GmDiagramHolder) instanceToMigrate);
        }
        return null;
    }

    /**
     * Replace a {@link _GmDiagramHolder} with its owned {@link GmDiagramView}.
     */
    @objid ("da2b7de0-25b0-4f0b-bbee-17f45c77856c")
    private IPersistent migrateFromV0(_GmDiagramHolder instanceToMigrate) {
        List<GmNodeModel> children = instanceToMigrate.getChildren();
        if (children.size() != 1) {
            return null;
        }
        GmNodeModel gmDiagramView = children.get(0);
        
        instanceToMigrate.removeChild(gmDiagramView);
        
        gmDiagramView.setLayoutData(instanceToMigrate.getLayoutData());
        gmDiagramView.setRoleInComposition(instanceToMigrate.getRoleInComposition());
        
        for (IGmLink link : instanceToMigrate.getStartingLinks()) {
            instanceToMigrate.removeStartingLink(link);
            gmDiagramView.addStartingLink(link);
        }
        for (IGmLink link : instanceToMigrate.getEndingLinks()) {
            instanceToMigrate.removeEndingLink(link);
            gmDiagramView.addEndingLink(link);
        }
        
        gmDiagramView.getPersistedStyle().setCascadedStyle(instanceToMigrate.getPersistedStyle().getCascadedStyle());
        for (StyleKey key : instanceToMigrate.getPersistedStyle().getLocalKeys()) {
            gmDiagramView.getDisplayedStyle().setProperty(key, instanceToMigrate.getDisplayedStyle().getProperty(key));
        }
        
        instanceToMigrate.delete();
        return gmDiagramView;
    }

}
