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

package org.modelio.diagram.elements.umlcommon.dependency;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.umlcommon.usage.GmUsage;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.Usage;

/**
 * Migrator for {@link GmDependency}.
 * <p>
 * Changes a {@link GmDependency} referencing a usage to a {@link GmUsage}.
 */
@objid ("71b4d9cb-f098-49da-a8cb-425e415967d6")
public class GmDependencyMigrator implements IPersistentMigrator {
    @objid ("1251ac73-3051-4934-8b61-ad9a609eca7b")
    @Override
    public IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
        case 0: {
            return new GmDependency();
        }
        default: {
            return null;
        }
        }
    }

    @objid ("f019e11a-22f9-4d64-af3e-f4d35612ec42")
    @Override
    public IPersistent migrate(final IPersistent instanceToMigrate) {
        if (instanceToMigrate instanceof GmDependency && ((GmDependency) instanceToMigrate).getRelatedElement() instanceof Usage) {
            return migrateFromV0((GmDependency) instanceToMigrate);
        }
        return null;
    }

    /**
     * Replace {@link GmDependency} referencing a {@link Usage} with a {@link GmUsage}.
     */
    @objid ("ececa223-4630-43a8-a806-e699be56aa1d")
    private IPersistent migrateFromV0(final GmDependency oldDependency) {
        GmUsage newDependency = new GmUsage(oldDependency.getDiagram(), (Usage) oldDependency.getRelatedElement(), oldDependency.getRepresentedRef());
        
        oldDependency.getFrom().addStartingLink(newDependency);
        oldDependency.getTo().addEndingLink(newDependency);
        
        newDependency.setLayoutData(oldDependency.getLayoutData());
        
        for (IGmLink link : oldDependency.getStartingLinks()) {
            oldDependency.removeStartingLink(link);
            newDependency.addStartingLink(link);
        }
        for (IGmLink link : oldDependency.getEndingLinks()) {
            oldDependency.removeEndingLink(link);
            newDependency.addEndingLink(link);
        }
        
        newDependency.getPersistedStyle().setCascadedStyle(oldDependency.getPersistedStyle().getCascadedStyle());
        for (StyleKey key : oldDependency.getPersistedStyle().getLocalKeys()) {
            newDependency.getPersistedStyle().setProperty(key, oldDependency.getPersistedStyle().getProperty(key));
        }
        
        oldDependency.delete();
        return newDependency;
    }

}
