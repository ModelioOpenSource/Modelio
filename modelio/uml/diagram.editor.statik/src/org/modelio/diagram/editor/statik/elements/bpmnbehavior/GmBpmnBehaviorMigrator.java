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

package org.modelio.diagram.editor.statik.elements.bpmnbehavior;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.bpmncollaboration.GmBpmnCollaboration;
import org.modelio.diagram.editor.statik.elements.bpmnprocess.GmBpmnProcess;
import org.modelio.diagram.editor.statik.elements.bpmnsharedefinition.GmBpmnSharedDefinitions;
import org.modelio.diagram.editor.statik.elements.packaze.GmPackage;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.diagram.persistence.PersistenceException;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class manages "major" migrations for {@link GmBpmnBehavior} instances.
 * <p>
 * V0 corresponds to the Modelio 3.6 -> 3.7 changes, where Pools (aka BpmnLanes directly under processes) should be replaced with Participants.
 * </p>
 */
@objid ("d3b2fb1a-1d2d-4793-9208-be2583affd31")
public class GmBpmnBehaviorMigrator implements IPersistentMigrator {
    @objid ("956879da-eadd-4d63-aada-bf6853f59679")
    @Override
    public IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
        case 0: {
            return new GmBpmnBehavior();
        }
        default: {
            return null;
        }
        }
    }

    @objid ("70db2a2f-5ccf-48d9-9378-9f142c187d89")
    @Override
    public IPersistent migrate(final IPersistent instanceToMigrate) {
        return migrateFromV0((GmBpmnBehavior) instanceToMigrate);
    }

    /**
     * In this migration, we have three cases :
     * <ul>
     * <li>{@link GmBpmnBehavior} representing a {@link BpmnCollaboration} are handled by {@link #migrateToCollaboration(GmBpmnBehavior)}</li>
     * <li>{@link GmBpmnBehavior} representing a {@link BpmnProcess} are handled by {@link #migrateToProcess(GmBpmnBehavior)}</li>
     * <li>{@link GmBpmnBehavior} representing a {@link BpmnSharedDefinitions} are handled by {@link #migrateToSharedDefinitions(GmBpmnBehavior)}</li>
     * <li>{@link GmBpmnBehavior} representing a {@link Package} are handled by {@link #migrateToPackage(GmBpmnBehavior)}</li>
     * </ul>
     * 
     * @param oldGmBehavior the gm being migrated.
     * @return the migrated gm, replacing the given one.
     * @throws org.modelio.diagram.persistence.PersistenceException if the lane do not represents a {@link BpmnLane} nor a {@link BpmnParticipant}.
     */
    @objid ("e285d561-b5e1-4a0f-ab41-4c7765cde828")
    private IPersistent migrateFromV0(final GmBpmnBehavior oldGmBehavior) throws PersistenceException {
        MObject elt = oldGmBehavior.getRepresentedElement();
        if (elt instanceof BpmnCollaboration) {
            return migrateToCollaboration(oldGmBehavior);
        } else if (elt instanceof BpmnProcess) {
            return migrateToProcess(oldGmBehavior);
        } else if (elt instanceof BpmnSharedDefinitions) {
            return migrateToSharedDefinitions(oldGmBehavior);
        } else if (elt instanceof Package) {
            return migrateToPackage(oldGmBehavior);
        } else {
            // Should never happen, return the deprecated Gm
            return oldGmBehavior;
        }
    }

    /**
     * Convert a {@link GmBpmnBehavior} to a {@link GmBpmnCollaboration}, keeping its layout and contents.
     */
    @objid ("291439ab-e0c1-4153-921d-57e73c4d45c2")
    private GmBpmnCollaboration migrateToCollaboration(GmBpmnBehavior oldGmBehavior) {
        IGmDiagram diagram = oldGmBehavior.getDiagram();
        BpmnCollaboration elt = (BpmnCollaboration) oldGmBehavior.getRepresentedElement();
        
        GmBpmnCollaboration newGmCollaboration = new GmBpmnCollaboration(diagram, elt, oldGmBehavior.getRepresentedRef());
        newGmCollaboration.setLayoutData(oldGmBehavior.getLayoutData());
        
        migrateStyle(oldGmBehavior, newGmCollaboration);
        
        // Migration is done, delete old gm
        oldGmBehavior.delete();
        return newGmCollaboration;
    }

    /**
     * Convert a {@link GmBpmnBehavior} to a {@link GmPackage}, keeping its layout and contents.
     */
    @objid ("bc35bc1a-7e2a-48ff-abbe-814952f28be3")
    private GmPackage migrateToPackage(GmBpmnBehavior oldGmBehavior) {
        IGmDiagram diagram = oldGmBehavior.getDiagram();
        Package elt = (Package) oldGmBehavior.getRepresentedElement();
        
        GmPackage newGmPackage = new GmPackage(diagram, elt, oldGmBehavior.getRepresentedRef());
        newGmPackage.setLayoutData(oldGmBehavior.getLayoutData());
        
        migrateStyle(oldGmBehavior, newGmPackage);
        
        newGmPackage.getPersistedStyle().setProperty(newGmPackage.getStyleKey(MetaKey.REPMODE), RepresentationMode.SIMPLE);
        
        // Migration is done, delete old gm
        oldGmBehavior.delete();
        return newGmPackage;
    }

    /**
     * Convert a {@link GmBpmnBehavior} to a {@link GmBpmnProcess}, keeping its layout and contents.
     */
    @objid ("01167ef0-40cd-42c3-ba51-bd4e7cc95aff")
    private GmBpmnProcess migrateToProcess(GmBpmnBehavior oldGmBehavior) {
        IGmDiagram diagram = oldGmBehavior.getDiagram();
        BpmnProcess elt = (BpmnProcess) oldGmBehavior.getRepresentedElement();
        
        GmBpmnProcess newGmProcess = new GmBpmnProcess(diagram, elt, oldGmBehavior.getRepresentedRef());
        newGmProcess.setLayoutData(oldGmBehavior.getLayoutData());
        
        migrateStyle(oldGmBehavior, newGmProcess);
        
        // Migration is done, delete old gm
        oldGmBehavior.delete();
        return newGmProcess;
    }

    /**
     * Convert a {@link GmBpmnBehavior} to a {@link GmBpmnSharedDefinitions}, keeping its layout and contents.
     */
    @objid ("c6a3bb98-0858-48c2-baa5-08e6f6b0681f")
    private GmBpmnSharedDefinitions migrateToSharedDefinitions(GmBpmnBehavior oldGmBehavior) {
        IGmDiagram diagram = oldGmBehavior.getDiagram();
        BpmnSharedDefinitions elt = (BpmnSharedDefinitions) oldGmBehavior.getRepresentedElement();
        
        GmBpmnSharedDefinitions newGmSharedDefinitions = new GmBpmnSharedDefinitions(diagram, elt, oldGmBehavior.getRepresentedRef());
        newGmSharedDefinitions.setLayoutData(oldGmBehavior.getLayoutData());
        
        migrateStyle(oldGmBehavior, newGmSharedDefinitions);
        
        // Migration is done, delete old gm
        oldGmBehavior.delete();
        return newGmSharedDefinitions;
    }

    @objid ("2146e43c-c6bf-4254-864c-a4e8cbd93c02")
    private void migrateStyle(GmBpmnBehavior oldGm, GmNodeModel newGm) {
        // Keep cascaded style
        newGm.getPersistedStyle().setCascadedStyle(oldGm.getPersistedStyle().getCascadedStyle());
        
        // Keep local style changes, converting style keys
        for (StyleKey oldKey : oldGm.getPersistedStyle().getLocalKeys()) {
            Object oldValue = oldGm.getPersistedStyle().getProperty(oldKey);
            StyleKey newKey = newGm.getStyleKey(oldKey.getMetakey());
            newGm.getPersistedStyle().setProperty(newKey, oldValue);
        }
    }

}
