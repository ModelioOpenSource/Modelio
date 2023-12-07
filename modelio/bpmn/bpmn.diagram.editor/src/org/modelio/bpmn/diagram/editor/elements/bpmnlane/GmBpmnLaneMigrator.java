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
package org.modelio.bpmn.diagram.editor.elements.bpmnlane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.hibridcontainer.GmBodyHybridContainer;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.v0.GmBpmnLaneV0;
import org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer.GmBpmnLaneSetContainer;
import org.modelio.bpmn.diagram.editor.elements.participant.GmBpmnParticipantPortContainer;
import org.modelio.bpmn.diagram.editor.elements.participant.GmBpmnParticipantPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.workflow.GmWorkflow;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.diagram.persistence.PersistenceException;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class manages "major" migrations for {@link GmBpmnLane} instances.
 * <p>
 * V0 corresponds to the Modelio 3.6 -> 3.7 changes, where Pools (aka BpmnLanes directly under processes) should be replaced with Participants.
 * </p>
 */
@objid ("d887caa0-745a-424d-84a9-4ed61a3a6c3c")
public class GmBpmnLaneMigrator implements IPersistentMigrator {
    @objid ("83ef5387-4ffa-4a05-a07c-c555ed6f747d")
    @Override
    public IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate) {
        switch (majorVersionToInstantiate) {
        case 0: {
            return new GmBpmnLaneV0();
        }
        default: {
            return null;
        }
        }
        
    }

    @objid ("c91ee51d-b89d-4f17-917e-faf25007da3f")
    @Override
    public IPersistent migrate(final IPersistent instanceToMigrate) {
        if (instanceToMigrate.getMajorVersion() == 0) {
            return migrateFromV0((GmBpmnLaneV0) instanceToMigrate);
        }
        return null;
    }

    /**
     * In this migration, we have three cases :
     * <ul>
     * <li>{@link GmBpmnLaneV0} representing a {@link BpmnLane} are handled by {@link #migrateLaneToLane(GmBpmnLaneV0)}</li>
     * <li>{@link GmBpmnLaneV0} representing a {@link BpmnParticipant} or {@link NullPointerException} are handled by {@link #migrateLaneToParticipant(GmBpmnLaneV0)}</li>
     * </ul>
     * @param oldGmLane the gm being migrated.
     * @return the migrated gm, replacing the given one.
     * @throws PersistenceException if the lane do not represents a {@link BpmnLane} nor a {@link BpmnParticipant}.
     */
    @objid ("ce1604e0-77e7-43ad-aec1-9c1e5417dec4")
    private IPersistent migrateFromV0(final GmBpmnLaneV0 oldGmLane) throws PersistenceException {
        MObject elt = oldGmLane.getRepresentedElement();
        if (elt instanceof BpmnLane) {
            return migrateLaneToLane(oldGmLane);
        } else if (elt instanceof BpmnParticipant) {
            return migrateLaneToParticipant(oldGmLane);
        } else if (elt == null) {
            // Migrate shell Lanes as a participant
            return migrateLaneToParticipant(oldGmLane);
        } else {
            // Should never happen, maybe
            throw new PersistenceException("Unable to migrate " + oldGmLane);
        }
        
    }

    /**
     * Convert a {@link GmBpmnLaneV0} to a {@link GmBpmnParticipantPortContainer}, keeping its layout and contents.
     */
    @objid ("261366ff-20b8-4204-93f8-f9d67f0662c2")
    private GmBpmnParticipantPortContainer migrateLaneToParticipant(GmBpmnLaneV0 oldGmLane) {
        IGmDiagram diagram = oldGmLane.getDiagram();
        
        if (BpmnProcessDesignDiagram.class.isAssignableFrom(diagram.getRelatedMClass().getJavaInterface())) {
            DiagramEditorBpmn.LOG.debug("GmBpmnLaneMigrator-01: Pruning %s from %s because it is for collaboration diagrams.", oldGmLane, diagram);
            return null;
        }
        
        BpmnParticipant participant = (BpmnParticipant) oldGmLane.getRepresentedElement();
        
        Object oldLayoutData = oldGmLane.getLayoutData();
        if (oldLayoutData instanceof Rectangle) {
            Rectangle oldRectangle = (Rectangle) oldLayoutData;
            // Use default values from 3.6.1
            if (oldRectangle.width == -1) {
                oldRectangle.width = 700;
            }
            if (oldRectangle.height == -1) {
                oldRectangle.height = 200;
            }
        }
        
        GmBpmnParticipantPortContainer newGmParticipant = new GmBpmnParticipantPortContainer(diagram, participant, oldGmLane.getRepresentedRef());
        newGmParticipant.setLayoutData(oldLayoutData);
        GmBpmnParticipantPrimaryNode newGmParticipantPrimary = newGmParticipant.getMainNode();
        
        // Restore contents
        GmBodyHybridContainer oldBody = oldGmLane.getBody();
        migrateOwnedNodes(oldBody, newGmParticipantPrimary);
        
        Rectangle layoutData = (oldLayoutData instanceof Rectangle ? ((Rectangle) oldLayoutData).getCopy() : new Rectangle()).setLocation(0, 0);
        migrateOwnedLanes(oldBody, newGmParticipantPrimary, layoutData);
        
        // Keep cascaded style
        newGmParticipant.getPersistedStyle().setCascadedStyle(oldGmLane.getPersistedStyle().getCascadedStyle());
        
        // Keep local style changes, converting style keys
        for (StyleKey oldKey : oldGmLane.getPersistedStyle().getLocalKeys()) {
            Object oldValue = oldGmLane.getPersistedStyle().getProperty(oldKey);
            StyleKey newKey = newGmParticipant.getStyleKey(oldKey.getMetakey());
            newGmParticipant.getPersistedStyle().setProperty(newKey, oldValue);
        }
        
        // Migration is done, delete old gm
        oldGmLane.delete();
        fixMissingLanes(newGmParticipantPrimary, layoutData);
        return newGmParticipant;
    }

    /**
     * Convert a {@link GmBpmnLaneV0} to a {@link GmBpmnLane}, keeping its layout and contents.
     */
    @objid ("cff375ca-c2ff-4bf9-9e9e-95826d133d80")
    private GmBpmnLane migrateLaneToLane(final GmBpmnLaneV0 oldGmLane) {
        GmBpmnLane newGmLane = new GmBpmnLane(oldGmLane);
        
        // Old layout contraint was kind of the width instead of the height, reset it !
        // Keep it for the moment, it will be fixed by the container migration;
        newGmLane.setLayoutData(oldGmLane.getLayoutData());
        
        // Keep role
        newGmLane.setRoleInComposition(oldGmLane.getRoleInComposition());
        
        // Keep starting links
        for (IGmLink link : oldGmLane.getStartingLinks()) {
            oldGmLane.removeStartingLink(link);
            newGmLane.addStartingLink(link);
        }
        
        // Keep ending links
        for (IGmLink link : oldGmLane.getEndingLinks()) {
            oldGmLane.removeEndingLink(link);
            newGmLane.addEndingLink(link);
        }
        
        // Keep cascaded style
        newGmLane.getPersistedStyle().setCascadedStyle(oldGmLane.getPersistedStyle().getCascadedStyle());
        
        // Keep local style changes
        for (StyleKey key : oldGmLane.getPersistedStyle().getLocalKeys()) {
            newGmLane.getPersistedStyle().setProperty(key, oldGmLane.getPersistedStyle().getProperty(key));
        }
        
        // Migration is done, delete old gm
        oldGmLane.delete();
        return newGmLane;
    }

    @objid ("48ad0dbf-3f73-4ab2-8504-32815d288d56")
    private void migrateOwnedNodes(GmBodyHybridContainer oldBody, GmBpmnParticipantPrimaryNode newGm) {
        // Process simple nodes in the old Gm
        for (GmNodeModel ownedNode : oldBody.getChildren(GmBodyHybridContainer.OWNED_NODE)) {
            GmCompositeNode compositeFor = newGm.getCompositeFor(getRepresentedMetaclass(ownedNode));
            if (compositeFor != null) {
                oldBody.removeChild(ownedNode);
        
                // Avoid duplicated GMs when a process is referenced by several participants
                for (GmModel existingGm : compositeFor.getDiagram().getAllGMRepresenting(ownedNode.getRepresentedRef())) {
                    if (!Objects.equals(ownedNode, existingGm)) {
                        existingGm.delete();
                    }
                }
        
                compositeFor.addChild(ownedNode);
            } else {
                // There is probably no diagram for this process, delete the gm
                ownedNode.delete();
            }
        }
        
    }

    @objid ("6f4b4023-6355-4db8-9f3d-9c17790faca6")
    private void migrateOwnedLanes(GmBodyHybridContainer oldBody, GmBpmnParticipantPrimaryNode newGm, Rectangle newProcessLayout) {
        // Process lanes in the old Gm
        for (GmNodeModel ownedNode : oldBody.getChildren(GmBodyHybridContainer.SUB_LANE)) {
            MObject representedElement = ownedNode.getRepresentedElement();
            GmCompositeNode compositeFor = representedElement != null ? newGm.getCompositeFor(representedElement.getClass()) : null;
            if (compositeFor != null) {
                // Delete soon-to-be-replaced automatically unmasked container
                for (GmNodeModel node : compositeFor.getChildren(GmWorkflow.OWNED_LANE)) {
                    node.delete();
                }
        
                if (representedElement instanceof BpmnLaneSet && representedElement.isShell()) {
                    // LaneSet is shell, move its contents into the workflow itself
                    List<GmBpmnLane> toDelete = new ArrayList<>();
                    List<GmBpmnLane> lanes = ((GmBpmnLaneSetContainer) ownedNode).getLanes();
                    for (GmBpmnLane lane : lanes) {
                        if (lane.getRelatedElement().isShell()) {
                            toDelete.add(lane);
                        }
                    }
        
                    if (toDelete.size() == lanes.size()) {
                        for (GmBpmnLane lane : toDelete) {
                            for (GmNodeModel child : lane.getBody().getChildren()) {
                                lane.getBody().removeChild(child);
                                compositeFor.addChild(child);
                            }
                            lane.delete();
                        }
                    }
                    ownedNode.delete();
                } else {
                    // Old layout data was an Integer, replace it with a proper Rectangle
                    ownedNode.setLayoutData(newProcessLayout.getCopy());
        
                    // Add migrated container
                    oldBody.removeChild(ownedNode);
        
                    // Avoid duplicated GMs when a process is referenced by several participants
                    for (GmModel existingGm : compositeFor.getDiagram().getAllGMRepresenting(ownedNode.getRepresentedRef())) {
                        if (!Objects.equals(ownedNode, existingGm)) {
                            existingGm.delete();
                        }
                    }
        
                    compositeFor.addChild(ownedNode);
        
                    // Old GmBpmnLane layout data was the width instead of the eight, and was interpreted as a weight.
                    // translate all of this to height.
                    fixLaneLayoutData((GmBpmnLaneSetContainer) ownedNode, newProcessLayout.getCopy().setLocation(0, 0));
                }
            } else {
                // There is probably no diagram for this process, delete the gm
                ownedNode.delete();
            }
        }
        
    }

    /**
     * Old GmBpmnLane layout data was buggily the width instead of the height, and was interpreted as a weight.
     * Interpret all of them as a weight and translate to height.
     * @param gmLaneContainer the laneset container
     * @param containerBounds the bounds of the container
     */
    @objid ("c318de5b-4b10-41f9-9ba1-90857756dee9")
    protected void fixLaneLayoutData(GmBpmnLaneSetContainer gmLaneContainer, Rectangle containerBounds) {
        int total = 0;
        for (GmBpmnLane gmBpmnLane : gmLaneContainer.getLanes()) {
            final Object layoutData = gmBpmnLane.getLayoutData();
            if (layoutData instanceof Integer) {
                // Some layout data are Rectangle, with inconsistent data, particularly on last lane, don't ask me why...
                int oldWeight = (int) layoutData;
                if (oldWeight > 0) {
                    total += oldWeight;
                }
            }
        }
        
        for (GmBpmnLane gmBpmnLane : gmLaneContainer.getLanes()) {
            final Object layoutData = gmBpmnLane.getLayoutData();
            if (layoutData instanceof Integer) {
                int oldWeight = (int) layoutData;
                if (oldWeight > 0) {
                    int newWeight = oldWeight * containerBounds.height / total;
                    gmBpmnLane.setLayoutData(newWeight);
                }
            } else {
                // reset ugly data
                DiagramEditorBpmn.LOG.debug("GmBpmnLaneMigrator-02: Replace %s in %s layout data by -1.", getClass().getSimpleName(), gmBpmnLane, gmLaneContainer);
                gmBpmnLane.setLayoutData(-1);
            }
        }
        
    }

    @objid ("a948f0c7-6578-4fc0-9e97-8b06cfc535ec")
    private void fixMissingLanes(GmBpmnParticipantPrimaryNode newGmParticipantPrimary, Rectangle layoutData) {
        BpmnParticipant participant = newGmParticipantPrimary.getRelatedElement();
        if (participant == null || participant.isShell()) {
            return;
        }
        BpmnProcess process = participant.getProcess();
        BpmnLaneSet laneSet = process.getLaneSet();
        if (laneSet == null) {
            return;
        }
        
        if (laneSet.getLane().isEmpty()) {
            // No lanes in container
            return;
        }
        
        IGmDiagram diagram = newGmParticipantPrimary.getDiagram();
        if (!diagram.getAllGMRepresenting(new MRef(laneSet)).isEmpty()) {
            // Container already unmasked
            return;
        }
        
        GmCompositeNode workflow = newGmParticipantPrimary.getCompositeFor(laneSet.getClass());
        
        if (workflow == null) {
            DiagramEditorBpmn.LOG.warning("GmBpmnLaneMigrator-03: %s in %s has no workflow.", newGmParticipantPrimary, newGmParticipantPrimary.getDiagram());
            DiagramEditorBpmn.LOG.debug  ("GmBpmnLaneMigrator-04:   %s.getBody() = %s .", newGmParticipantPrimary, newGmParticipantPrimary.getBody());
            return ;
        }
        
        GmBpmnLaneSetContainer newContainer = new GmBpmnLaneSetContainer(diagram, laneSet, new MRef(laneSet));
        
        // Unmask first lane
        BpmnLane firstLane = laneSet.getLane().get(0);
        GmBpmnLane firstLaneGm = new GmBpmnLane(diagram, firstLane, new MRef(firstLane));
        newContainer.addChild(firstLaneGm);
        
        GmNodeModel wrongLaneSet = workflow.getFirstChild(GmWorkflow.OWNED_LANE);
        if (wrongLaneSet != null) {
            // Set new container's layout data
            Rectangle oldLayoutData = (Rectangle) wrongLaneSet.getLayoutData();
            oldLayoutData.x -= 20;
            oldLayoutData.width += 20;
            newContainer.setLayoutData(oldLayoutData.getCopy());
        
            firstLaneGm.setLayoutData(oldLayoutData.height);
        
            // Make the wrong lane set a child of the first lane
            workflow.removeChild(wrongLaneSet);
            firstLaneGm.getBody().addChild(wrongLaneSet);
        } else {
            newContainer.setLayoutData(layoutData);
        
            firstLaneGm.setLayoutData(layoutData.height);
        
            // Process simple nodes in the old Gm
            for (GmNodeModel ownedNode : workflow.getChildren(GmBodyHybridContainer.OWNED_NODE)) {
                GmCompositeNode compositeFor = firstLaneGm.getCompositeFor(getRepresentedMetaclass(ownedNode));
                if (compositeFor != null) {
                    workflow.removeChild(ownedNode);
        
                    // Avoid duplicated GMs when a process is referenced by several participants
                    for (GmModel existingGm : compositeFor.getDiagram().getAllGMRepresenting(ownedNode.getRepresentedRef())) {
                        if (!Objects.equals(ownedNode, existingGm)) {
                            existingGm.delete();
                        }
                    }
        
                    compositeFor.addChild(ownedNode);
                } else {
                    // There is probably no diagram for this process, delete the gm
                    ownedNode.delete();
                }
            }
        }
        
        workflow.addChild(newContainer);
        
    }

    @objid ("2c35565f-aa83-4c87-a397-a43cc7d0a074")
    private static Class<? extends MObject> getRepresentedMetaclass(GmNodeModel ownedNode) {
        MObject relatedElement = ownedNode.getRelatedElement();
        if (relatedElement != null) {
            return relatedElement.getClass();
        }
        return ownedNode.getDiagram().getModelManager().getMetamodel().getMClass(ownedNode.getRepresentedRef().mc).getJavaInterface();
    }

}
