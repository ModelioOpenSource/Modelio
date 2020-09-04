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

package org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.content.v0;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.GmBpmnSubProcessPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.content.GmBpmnSubProcessContent;
import org.modelio.diagram.editor.bpmn.elements.diagrams.subprocess.GmBpmnSubProcessDiagram;
import org.modelio.diagram.editor.bpmn.elements.workflow.GmWorkflow;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class manages "major" migrations for {@link GmBpmnBodyFreeZone} instances.
 * <p>
 * V0 corresponds to the Modelio 3.6 -> 3.7 changes, where SubProcesses display their first diagram instead of a custom layout.
 * </p>
 */
@objid ("415da76b-1e04-4688-aaa2-02ef58269192")
public class GmBpmnBodyFreeZoneMigrator implements IPersistentMigrator {
    @objid ("66bc9444-6095-470b-aae7-7833baa9e349")
    @Override
    public IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
        case 0: {
            return new GmBpmnBodyFreeZone();
        }
        default: {
            return null;
        }
        }
    }

    @objid ("24b9b23e-f968-4da3-af94-9160ece4da55")
    @Override
    public IPersistent migrate(final IPersistent instanceToMigrate) {
        if (instanceToMigrate.getMajorVersion() == 1) {
            return migrateFromV0((GmBpmnBodyFreeZone) instanceToMigrate);
        }
        return null;
    }

    /**
     * In this migration, we have two cases :
     * <p>
     * First, simply replace the {@link GmBpmnBodyFreeZone} with a proper {@link GmBpmnSubProcessContent}. <br/>
     * All GMs previously unmasked in the {@link GmBpmnBodyFreeZone} are to be transfered into the new GM's sub process diagram.
     * </p>
     * <p>
     * Else, if the migration takes place in a sub process diagram "X" and the sub process being migrated is X's owner,<br/>
     * said sub process is masked and its contents transfered into the diagram itself, therefore avoiding potential infinite loops.
     * </p>
     * @param oldGmBody the gm being migrated.
     * @return the migrated gm, replacing the given one. Might be <code>null</code>.
     */
    @objid ("640e0b11-25c8-488c-a63d-ea6eef8a3624")
    private IPersistent migrateFromV0(final GmBpmnBodyFreeZone oldGmBody) {
        IGmDiagram diagram = oldGmBody.getDiagram();
        BpmnSubProcess subProcess = (BpmnSubProcess) diagram.getModelManager().resolveRef(oldGmBody.getRepresentedRef());
        if (Objects.equals(subProcess, diagram.getRelatedElement().getOrigin())) {
            migrateToDiagram(oldGmBody, (GmBpmnSubProcessDiagram) diagram);
            // This is a pure model transformation, not a simple GM replacement
            return null;
        } else {
            return migrateToSubProcessContent(oldGmBody, diagram, subProcess);
        }
    }

    /**
     * Transfer every gm owned by the {@link GmBpmnBodyFreeZone} to the diagram itself, keeping their layout constraints.
     */
    @objid ("9b2299c6-3c5a-407c-8dbb-e5b104f1ffcd")
    private void migrateToDiagram(final GmBpmnBodyFreeZone oldGmBody, GmBpmnSubProcessDiagram diagram) {
        // Refresh the diagram to make it create its workflow
        diagram.refreshFromObModel();
        
        GmWorkflow newBody = new GmWorkflow(diagram, diagram.getRepresentedRef());
        newBody.setRoleInComposition(GmBpmnSubProcessDiagram.ROLE_BODY);
        diagram.addChild(newBody);
        
        // Move children into the workflow
        for (GmNodeModel ownedNode : oldGmBody.getChildren()) {
            oldGmBody.removeChild(ownedNode);
            newBody.addChild(ownedNode);
        }
        
        // Migration is done, delete old gm
        oldGmBody.delete();
    }

    /**
     * Convert a {@link GmBpmnBodyFreeZone} to a {@link GmBpmnSubProcessContent}, keeping its layout and contents.
     */
    @objid ("2cd2489a-50fd-4531-bbb1-87c7cd91b846")
    private GmBpmnSubProcessContent migrateToSubProcessContent(final GmBpmnBodyFreeZone oldGmBody, IGmDiagram diagram, BpmnSubProcess subProcess) {
        // Get a sub process diagram
        BpmnSubProcessDiagram spd;
        List<BpmnSubProcessDiagram> processdiags = subProcess.getProduct(BpmnSubProcessDiagram.class);
        if (!processdiags.isEmpty()) {
            spd = processdiags.get(0);
        
            // Create new GM
            GmBpmnSubProcessContent newGmBody = new GmBpmnSubProcessContent(diagram, spd, new MRef(spd));
            newGmBody.setRoleInComposition(GmBpmnSubProcessPrimaryNode.ROLE_BODY);
        
            if (!oldGmBody.getChildren().isEmpty()) {
                newGmBody.setVisible(true);
            }
        
            // Move children to the new GM
            for (GmNodeModel ownedNode : oldGmBody.getChildren()) {
                oldGmBody.removeChild(ownedNode);
        
                GmCompositeNode compositeFor = newGmBody.getCompositeFor(ownedNode.getRepresentedElement().getClass());
                if (compositeFor != null) {
                    compositeFor.addChild(ownedNode);
                } else {
                    ownedNode.delete();
                }
            }
        
            // Migration is done, delete old gm
            oldGmBody.delete();
            return newGmBody;
        } else {
            // There is probably no diagram for this process, delete the gm
            oldGmBody.delete();
            return null;
        }
    }

}
