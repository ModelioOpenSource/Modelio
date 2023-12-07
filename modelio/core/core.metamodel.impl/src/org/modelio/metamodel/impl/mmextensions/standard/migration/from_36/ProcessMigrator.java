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
package org.modelio.metamodel.impl.mmextensions.standard.migration.from_36;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.model.spi.mm.IMofSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmDependency;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * Migrates BPMN Processes from Modelio 3.6 to Modelio 3.7 metamodel 2.1.0 .
 * @author cma
 * @since 3.7
 */
@objid ("d4acf3a6-f5ae-4bbd-8086-8b241c46bc0c")
class ProcessMigrator {
    @objid ("7eeddaed-9d56-43df-a999-3a437574a758")
    private static final Pattern defaultPoolName = Pattern.compile("Pool\\s*[0-9]*");

    @objid ("497cac35-8dae-4624-addd-95077252e592")
    private MofSmDependency laneSetDep;

    @objid ("a7b90bfa-a993-4abc-b7a8-464c9ca72ca5")
    private final MM mm;

    @objid ("306b4933-6c98-4cae-ba0c-59a9148ad43b")
    private final IMofSession mofSession;

    @objid ("72710a26-967f-463b-99e6-c905619fbaee")
    public  ProcessMigrator(IMofSession mofSession, MM mm) {
        this.mofSession = mofSession;
        this.mm = mm;
        this.laneSetDep = (MofSmDependency) this.mm.processMclass.getDependency("LaneSet");
        
    }

    @objid ("0251d0f7-fbbb-4963-8c11-dc191bb72a59")
    public void runOnProcess(MofSmObjectImpl process) throws MetaclassNotFoundException {
        if (!process.mGet(this.laneSetDep).isEmpty()) {
            // The process has LaneSets, migrate it
            migrateProcess(process);
        } else {
            // Transmute all BpmnProcessCollaborationDiagram to BpmnProcessDesignDiagram
            transmuteAll(process.getDep("Product"), this.mm.bpmnProcessCollaborationDiagramMC, this.mm.bpmnProcessDesignDiagramMC);
        }
        
    }

    /**
     * @return <code>true</code> if the diagram contains at least one element of the given metaclass.
     */
    @objid ("3f636c1e-9b99-4d69-94c6-be603aab765e")
    private boolean contains(MofSmObjectImpl diagram, SmClass mc) {
        for (MofSmObjectImpl represented : diagram.getDep("Represented")) {
            if (represented.getMClass() == mc) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get FlowElement referenced by the Lane and all its sub lanes hierarchy.
     * @param lane a BPMN Lane
     * @return all referenced flow elements
     */
    @objid ("380ffb4f-07ed-4788-9b58-377b2af8acac")
    private List<MofSmObjectImpl> getAllLaneNodes(MofSmObjectImpl lane) {
        final List<MofSmObjectImpl> laneFlowElementRefs = new ArrayList<>(lane.getDep("FlowElementRef"));
        
        for (MofSmObjectImpl subLaneSet : lane.getDep("ChildLaneSet")) {
            for (MofSmObjectImpl childLane : subLaneSet.getDep("Lane")) {
                laneFlowElementRefs.addAll(getAllLaneNodes(childLane));
            }
        }
        return laneFlowElementRefs;
    }

    /**
     * Get a MDependency content then empty it.
     * @param obj the dep source
     * @param depName the dep name
     * @return the dependency content.
     */
    @objid ("c81903f6-4b6b-4ef8-91b8-b72620df27cd")
    private static List<MofSmObjectImpl> getAndClearDep(MofSmObjectImpl obj, String depName) {
        List<MofSmObjectImpl> depAccess = obj.getDep(depName);
        List<MofSmObjectImpl> ret = new ArrayList<>(depAccess);
        depAccess.clear();
        return ret;
    }

    /**
     * Get a BpmnLaneSet different than the given one.
     * <p>
     * Create a new BpmnLaneSet if no satisfying one found.
     * @param aProcess a BPMN Process
     * @param origRootPools BpmnLaneSets to avoid
     * @return the found or created BpmnLaneSet
     */
    @objid ("581702bd-ff2c-4b2f-ad2c-d785c1dcd636")
    private MofSmObjectImpl getAnotherLaneSet(MofSmObjectImpl aProcess, List<MofSmObjectImpl> origRootPools) {
        assert (this.mm.processMclass.isInstance(aProcess)) : aProcess;
        //assert (badLaneSet == null || this.mm.bpmnLaneSetMC.isInstance(badLaneSet)) : badLaneSet;
        
        List<MofSmObjectImpl> processLaneSets = aProcess.getDep("LaneSet");
        for (MofSmObjectImpl laneSet : processLaneSets) {
            if (!origRootPools.contains(laneSet)) {
                return laneSet;
            }
        }
        
        // Recreate a LaneSet
        MofSmObjectImpl processLaneSet = this.mofSession.createObject(this.mm.bpmnLaneSetMC, "");
        processLaneSets.add(processLaneSet);
        return processLaneSet;
    }

    @objid ("9351f204-732f-4a66-a440-884e5a483e68")
    private MofSmObjectImpl getOrCreateCollaboration(MofSmObjectImpl process, MofSmObjectImpl bpmnBehavior) {
        final List<MofSmObjectImpl> bpmnBehaviorRoots = bpmnBehavior.getDep("RootElement");
        
        PrintWriter logger = this.mofSession.getReport().getLogger();
        
        for (MofSmObjectImpl rootEl : bpmnBehaviorRoots) {
            if (rootEl.getMClass().equals(this.mm.bpmnCollaboMC)) {
                logger.format("  Use existing %s for %s .%n", rootEl, process);
                return rootEl;
            }
        }
        
        MofSmObjectImpl collaboration = this.mofSession.createObject(this.mm.bpmnCollaboMC, bpmnBehavior.getName());
        bpmnBehaviorRoots.add(collaboration);
        logger.format("  Create %s in %s for %s .%n", collaboration, collaboration.getSingleDep("Owner"), process);
        return collaboration;
    }

    @objid ("9dcc8c5f-4a4f-4c55-8eac-7607c915acc2")
    private boolean laneSetHasMessageFlows(MofSmObjectImpl laneSet) {
        if (!laneSet.getDep("IncomingFlow").isEmpty() || !laneSet.getDep("OutgoingFlow").isEmpty()) {
            return true;
        }
        
        for (MofSmObjectImpl lane : laneSet.getDep("Lane")) {
            if (!lane.getDep("IncomingFlow").isEmpty() || !lane.getDep("OutgoingFlow").isEmpty()) {
                return true;
            }
        
            for (MofSmObjectImpl childLaneSet : lane.getDep("ChildLaneSet")) {
                if (laneSetHasMessageFlows(childLaneSet)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Move child LaneSet into the owning Process.
     * @param collaboration the BPMN Collaboration. If not null, a Participant for the Process should be created.
     * @param logger the log reporter
     * @param origPool the pool to move, is a LaneSet
     * @param process the BPMN Process
     */
    @objid ("568fd07f-0dbc-49de-b857-a3e0a2bf72cf")
    private void mergePoolIntoProcess(final PrintWriter logger, MofSmObjectImpl origPool, MofSmObjectImpl process) {
        final List<MofSmObjectImpl> debugProcessNodes = new ArrayList<>(process.getDep("FlowElement"));
        final List<MofSmObjectImpl> poolLanes = getAndClearDep(origPool, "Lane");
        for (MofSmObjectImpl poolLane : poolLanes) {
            logger.format("  Merge %s content into %s without BPMN collaboration...\n", poolLane, process);
        
            // debug
            List<MofSmObjectImpl> oldLaneNodes = new ArrayList<>(poolLane.getDep("FlowElementRef"));
        
            // Merge Process name and Pool name
            final String poolLaneName = poolLane.getName();
            if (!Objects.equals(poolLaneName, process.getName()) && !poolHasDefaultName(poolLane)) {
                String newName = process.getName() + " - " + poolLaneName;
                logger.format("    Rename '%s' to '%s' \n", process, newName);
                process.setName(newName);
            }
        
            final MofSmObjectImpl laneRepresentedElement = poolLane.getSingleDep("PartitionElement");
            final List<MofSmObjectImpl> processLaneSets = process.getDep("LaneSet");
        
            // Move child Lane into the original Process
            if (laneRepresentedElement == null) {
                // Move child LaneSet into the new Process
                final List<MofSmObjectImpl> laneSubLaneSet = getAndClearDep(poolLane, "ChildLaneSet");
                processLaneSets.addAll(laneSubLaneSet);
                poolLane.getDep("FlowElementRef").clear(); // this dep is {isToDelete}
            } else {
                // Keep pool lane in any LaneSet of the new Process
                // Get first LaneSet that is not origPool
                MofSmObjectImpl processLaneSet = null;
                for (MofSmObjectImpl laneSet : processLaneSets) {
                    if (!laneSet.equals(origPool)) {
                        processLaneSet = laneSet;
                        break;
                    }
                }
        
                if (processLaneSet == null) {
                    // Recreate a LaneSet
                    processLaneSet = this.mofSession.createObject(this.mm.bpmnLaneSetMC, "");
                    processLaneSets.add(processLaneSet);
                }
        
                // Keep pool lane in the new Process
                processLaneSet.getDep("Lane").add(poolLane);
            }
            assert (oldLaneNodes.stream().allMatch(o -> o.isValid())) : "Lost BPMN nodes: "+oldLaneNodes.stream().filter(o -> !o.isValid()).collect(Collectors.toList());
        }
        
        // Delete the old Pool
        assert(origPool.getDep("Lane").isEmpty()) : String.format("%s.Lane still contains %s", origPool, origPool.getDep("Lane"));
        
        origPool.delete();
        
        assert (debugProcessNodes.stream().allMatch(o -> o.isValid())) : "Lost BPMN nodes: "+debugProcessNodes.stream().filter(o -> !o.isValid()).collect(Collectors.toList());
        
    }

    /**
     * Move child LaneSet into the owning Process.
     * @param logger the log reporter
     * @param origPool the pool to move, is a LaneSet
     * @param process the BPMN Process
     * @param origRootPools the original list of Pools, must be a snapshot or immutable
     * @param collaboration the BPMN Collaboration, not null. a Participant for the Process should be created.
     */
    @objid ("5a68e0ae-fb6c-42eb-800d-3090f27cb601")
    private void mergePoolIntoProcessAndCollabo(final PrintWriter logger, MofSmObjectImpl origPool, MofSmObjectImpl process, List<MofSmObjectImpl> origRootPools, MofSmObjectImpl collaboration) {
        if (collaboration == null) {
            mergePoolIntoProcess(logger, origPool, process);
            return;
        }
        
        final List<MofSmObjectImpl> debugProcessNodes = new ArrayList<>(process.getDep("FlowElement"));
        final List<MofSmObjectImpl> poolLanes = getAndClearDep(origPool, "Lane");
        for (MofSmObjectImpl poolLane : poolLanes) {
            logger.format("  Merge %s content into %s with %s ...\n", poolLane, process, collaboration);
        
            // debug
            List<MofSmObjectImpl> oldLaneNodes = new ArrayList<>(poolLane.getDep("FlowElementRef"));
        
            // Merge Process name and Pool name
            final String poolLaneName = poolLane.getName();
            if (!Objects.equals(poolLaneName, process.getName()) && !poolHasDefaultName(poolLane)) {
                String newName = process.getName() + " - " + poolLaneName;
                logger.format("    Rename '%s' to '%s' \n", process, newName);
                process.setName(newName);
            }
        
            final MofSmObjectImpl laneRepresentedElement = poolLane.getSingleDep("PartitionElement");
            final List<MofSmObjectImpl> processLaneSets = process.getDep("LaneSet");
        
            // Look for an existing Participant for the Process in the Collaboration
            MofSmObjectImpl participant = null;
            MofSmObjectImpl laneRepresented = poolLane.getSingleDep("PartitionElement");
        
            for (MofSmObjectImpl collParticipant : collaboration.getDep("Participants")) {
                if (collParticipant.getDep("Process").contains(process)) {
                    participant = collParticipant;
                }
            }
        
            if (participant == null) {
                // Move child Lane into the original Process
                if (laneRepresentedElement == null) {
                    // Move child LaneSet into the new Process
                    final List<MofSmObjectImpl> laneSubLaneSet = getAndClearDep(poolLane, "ChildLaneSet");
                    processLaneSets.addAll(laneSubLaneSet);
                    poolLane.getDep("FlowElementRef").clear(); // this dep is {isToDelete}
                } else {
                    // Get first LaneSet that is not origPool, or create new
                    MofSmObjectImpl processLaneSet = getAnotherLaneSet(process, origRootPools);
                    moveLaneContentToNewLaneInLaneSet(processLaneSet, poolLane);
                }
        
                // Transmute the Lane into a Participant
                participant = this.mofSession.transmute(poolLane, this.mm.participantMC);
                poolLane = null; // Don't use 'lane' anymore, it is invalid
        
                collaboration.getDep("Participants").add(participant);
                participant.getDep("Process").add(process);
            } else {
                if (laneRepresentedElement == null) {
                    // Move child LaneSet into the new Process
                    final List<MofSmObjectImpl> laneSubLaneSet = getAndClearDep(poolLane, "ChildLaneSet");
                    processLaneSets.addAll(laneSubLaneSet);
                    poolLane.getDep("FlowElementRef").clear(); // this dep is {isToDelete}
                    poolLane.delete();
                } else {
                    // Get first LaneSet that is not origPool, or create new
                    MofSmObjectImpl processLaneSet = getAnotherLaneSet(process, origRootPools);
        
                    // Keep pool lane in the new Process
                    processLaneSet.getDep("Lane").add(poolLane);
                }
            }
        
            migrateLaneRepresentedtoParticipant(laneRepresented, participant);
        
            assert (oldLaneNodes.stream().allMatch(o -> o.isValid())) : "Lost BPMN nodes: "+oldLaneNodes.stream().filter(o -> !o.isValid()).collect(Collectors.toList());
        }
        
        // Delete the old Pool
        assert(origPool.getDep("Lane").isEmpty()) : String.format("%s.Lane still contains %s", origPool, origPool.getDep("Lane"));
        
        origPool.delete();
        
        assert (debugProcessNodes.stream().allMatch(o -> o.isValid())) : "Lost BPMN nodes: "+debugProcessNodes.stream().filter(o -> !o.isValid()).collect(Collectors.toList());
        
    }

    @objid ("5aac3efc-0c5b-4e77-a6cb-18f308cc07e2")
    private void migrateLaneRepresentedtoParticipant(MofSmObjectImpl laneRepresented, MofSmObjectImpl participant) {
        if (laneRepresented == null) {
            // noop
        } else if (this.mm.umlClassifierMC.isInstance(laneRepresented)) {
            List<MofSmObjectImpl> onlyType = participant.getDep("Type");
            if (onlyType.isEmpty()) {
                onlyType.add(laneRepresented);
            }
        } else if (this.mm.umlPackageMC.isInstance(laneRepresented)) {
            List<MofSmObjectImpl> onlyType = participant.getDep("PackageRef");
            if (onlyType.isEmpty()) {
                onlyType.add(laneRepresented);
            }
        }
        
    }

    @objid ("9da81589-b64d-4e21-88fb-cc1c5cbdc22b")
    @SuppressWarnings ("resource")
    private void migrateProcess(MofSmObjectImpl process) throws MetaclassNotFoundException {
        final PrintWriter logger = this.mofSession.getReport().getLogger();
        
        // Get owner BPMN Behavior
        MofSmObjectImpl bpmnBehavior = process.getSingleDep("Owner");
        if (bpmnBehavior == null) {
            // orphan BpmnProcess !
            logger.format("WARN:  %s is orphan.\n", process);
        
            bpmnBehavior = this.mofSession.createObject("Standard.BpmnBehavior", process.getName());
        }
        
        logger.format("\nMigrating %s process to 2.1.0 ...\n", process);
        
        LaneSetCleaner.deleteProcessEmptyLaneSets(process, logger);
        
        // If the process has only pools, no nodes at root,
        // keep the first pool as a Lane container and convert other pools to Process and Participant.
        // CMA 17/10/2023 : keep the parent lane, to avoid breaking the content layout.
        // If the Process has Pools and nodes on root, convert all pools to Process and Participant.
        List<MofSmObjectImpl> origRootPools = new ArrayList<>(process.getDep("LaneSet"));
        
        boolean origHasActiveNodes = processHasRootNodes(process);
        boolean origHasMessageFlows = processHasMessageFlows(process);
        int nbPoolsToMerge = origHasActiveNodes ? 0 : 1;
        
        logger.format(" - %s \n", origHasActiveNodes ? "it has nodes at root" : "no active nodes at root");
        logger.format(" - %s \n", origHasMessageFlows ? "it has messages flows" : "no message flow");
        logger.format(" - it contains %d pools \n", origRootPools.size());
        
        if (origRootPools.size() == 0) {
            // nothing to do with pools
            logger.format(" %s has no pool, keep as is.\n", process);
        
            // Transmute all BpmnProcessCollaborationDiagram to BpmnProcessDesignDiagram
            final List<MofSmObjectImpl> diagrams = new ArrayList<>(process.getDep("Product"));
            transmuteAll(diagrams, this.mm.bpmnProcessCollaborationDiagramMC, this.mm.bpmnProcessDesignDiagramMC);
        } else if (origRootPools.size() == 1
                && !origHasActiveNodes
                && !origHasMessageFlows
                /*&& origRootPools.get(0).getDep("Lane").stream()
                        .allMatch(l -> l.getDep("ChildLaneSet").isEmpty())*/) {
            // CMA 17/10/2023 : disabled last condition to keep the previously mandatory parent lane, to avoid breaking the content layout.
            // Special case : 1 Pool with 1 Lane (or 1 Pool with x Lanes with no sub LaneSet)
            // keep lanes as is
            logger.format(" %s has only one pool /*with no sub laneset*/, keep as is.\n", process);
        
            // Transmute all BpmnProcessCollaborationDiagram to BpmnProcessDesignDiagram
            final List<MofSmObjectImpl> diagrams = new ArrayList<>(process.getDep("Product"));
            transmuteAll(diagrams, this.mm.bpmnProcessCollaborationDiagramMC, this.mm.bpmnProcessDesignDiagramMC);
        
        } else if (origRootPools.size() > nbPoolsToMerge) {
            // Split pools to Processes
            logger.format(" Splitting %s%s in %d more processes...\n",
                    process,
                    origHasActiveNodes ? " with nodes outside BpmnLanes": "",
                    origRootPools.size() - nbPoolsToMerge);
        
            MofSmObjectImpl collaboration = getOrCreateCollaboration(process, bpmnBehavior);
        
            int nbMergedPools = 0;
            for (MofSmObjectImpl origPool : origRootPools) {
                // Keep the first Pool in the existing process
                if (nbMergedPools < nbPoolsToMerge) {
                    // First Pool, Move child LaneSet into the original Process
                    nbMergedPools++;
                    mergePoolIntoProcessAndCollabo(logger, origPool, process, origRootPools, collaboration);
                } else {
                    // We have to move the Pool content to a new Process.
                    movePoolContentToNewProcess(logger, origPool, process, bpmnBehavior, collaboration);
                }
            }
        
            // Migrates owned diagrams.
            new OwnedDiagramsMigrator(this.mm).run(this.mofSession, process, collaboration);
        
        } else {
            // Remove first level of BpmnLaneSet
            MofSmObjectImpl collaboration = null;
            if (origHasMessageFlows) {
                collaboration = getOrCreateCollaboration(process, bpmnBehavior);
                logger.format(" Removing %s first level of BpmnLaneSet...\n", process);
            } else {
                logger.format(" Removing %s first level of BpmnLaneSet...\n", process);
            }
        
            for (MofSmObjectImpl origPool : origRootPools) {
                // Keep the first Pool in the existing process
                mergePoolIntoProcessAndCollabo(logger, origPool, process, origRootPools, collaboration);
            }
        
            // Transmute BpmnProcessCollaborationDiagram
            for (MofSmObjectImpl diagram : new ArrayList<>(process.getDep("Product"))) {
                if (diagram.getMClass() == this.mm.bpmnProcessCollaborationDiagramMC) {
                    if (origHasMessageFlows && collaboration != null && (contains(diagram, this.mm.bpmnMessageFlowMC) || contains(diagram, this.mm.participantMC))) {
                        // Duplicate the diagram as a process diagram, the original one will be transmuted to a BpmnCollaborationDiagram.
                        // org.modelio.bpmn.diagram.editor.contributor.BpmnMmMigrationContributor will clean the diagram later.
                        if (false) {
                            MofSmObjectImpl clonedDiagram = cloneDiagram(diagram, process, this.mm.bpmnProcessDesignDiagramMC);
                        }
        
                        // Move the original diagram from the process into the collaboration
                        // Transmute it to a BpmnCollaborationDiagram
                        process.getDep("Product").remove(diagram);
                        collaboration.getDep("Product").add(diagram);
                        this.mofSession.transmute(diagram, this.mm.bpmnCollaborationDiagramMC);
        
                    } else {
                        // To a BpmnProcessDesignDiagram
                        this.mofSession.transmute(diagram, this.mm.bpmnProcessDesignDiagramMC);
                    }
                }
            }
        }
        
        logger.format("-- end of %s migration.\n\n", process);
        
    }

    /**
     * Clone the given diagram with a new metaclass.
     * <p>
     * The returned diagram is orphan and must be attached to somebody with:
     * <pre><code>process.getDep("Product").add(clonedDiagram);</code></pre>
     * @param diagram the original diagram
     * @param clonedDiagramMetaclass the clone metaclass
     * @return the cloned diagram
     */
    @objid ("392e00d7-c87e-44e6-aee6-1450c510a82e")
    private MofSmObjectImpl cloneDiagram(MofSmObjectImpl diagram, MofSmObjectImpl target, MClass clonedDiagramMetaclass) {
        MofSmObjectImpl clonedDiagram = this.mofSession.createObject(clonedDiagramMetaclass);
        String originalName = diagram.getName();
        clonedDiagram.setAttVal("UiDataVersion", diagram.getAtt("UiDataVersion"));
        clonedDiagram.setAttVal("UiData", diagram.getAtt("UiData"));
        clonedDiagram.getDep("Represented").addAll(diagram.getDep("Represented")); // represented elements list
        clonedDiagram.getDep("ReferencingSet").addAll(diagram.getDep("ReferencingSet")); // diagram folder
        
        target.getDep("Product").add(clonedDiagram);
        
        try {
            String ownerName = diagram.getSingleDep("Origin").getName();
            String newName = originalName.replace(ownerName, target.getName());
            if (newName.equals(originalName)) {
                newName = target.getName();
            }
            clonedDiagram.setAttVal("Name", newName);
        } catch (RuntimeException e) {
            Log.warning(e);
            clonedDiagram.setAttVal("Name", target.getName());
        }
        return clonedDiagram;
    }

    /**
     * Move lane content into a new lane under the process or recreate the lane if it represents an element.
     * <p>
     * When this method returns 'lane' is empty.
     * @param targetProcess the Process to move things into
     * @param lane the Pool/Lane to migrate
     */
    @objid ("2d364ff8-acaf-4d7e-9655-ab05c4519d17")
    private void moveLaneContentToNewLaneInLaneSet(MofSmObjectImpl targetLaneSet, MofSmObjectImpl lane) {
        final MofSmObjectImpl laneRepresentedElement = lane.getSingleDep("PartitionElement");
        /*final List<MofSmObjectImpl> processLaneSets = targetProcess.getDep("LaneSet");
        // Recreate a LaneSet and a Lane in the target Process
        MofSmObjectImpl processLaneSet;
        if (processLaneSets.isEmpty()) {
            processLaneSet = this.mofSession.createObject(this.mm.bpmnLaneSetMC, "");
            processLaneSets.add(processLaneSet);
        } else {
            processLaneSet = processLaneSets.get(0);
        }*/
        
        // Use pool name or "" if pool name is dummy
        final String newLaneName = poolHasDefaultName(lane) ? "" : lane.getName();
        
        MofSmObjectImpl newLane = this.mofSession.createObject(this.mm.bpmnLaneMC, newLaneName);
        targetLaneSet.getDep("Lane").add(newLane);
        
        // Reference lane nodes into the new Lane
        final List<MofSmObjectImpl> laneNodes = getAndClearDep(lane, "FlowElementRef");
        newLane.getDep("PartitionElement").add(laneRepresentedElement);
        newLane.getDep("FlowElementRef").addAll(laneNodes);
        
        // Move child LaneSet into the new Lane
        final List<MofSmObjectImpl> laneSubLaneSet = getAndClearDep(lane, "ChildLaneSet");
        newLane.getDep("ChildLaneSet").addAll(laneSubLaneSet);
        
        assert (lane.getDep("FlowElementRef").isEmpty()) : String.format("%s.FlowElementRef still contains %s", lane, lane.getDep("FlowElementRef"));
        assert (laneSubLaneSet.isEmpty() || laneSubLaneSet.get(0).getSingleDep("ParentLane") == newLane) : String.format("%s.parent = %s instead of %s", laneSubLaneSet.get(0), laneSubLaneSet.get(0).getDep("ParentLane"), newLane);
        assert (newLane.getDep("LaneSet").contains(targetLaneSet)) : String.format("%s.LaneSet contains %s instead of %s", newLane, newLane.getDep("LaneSet"), targetLaneSet);
        
    }

    /**
     * Move the Pool content to a new Process.
     * @param logger the logger
     * @param origPool the Pool (BpmnLaneSet) to migrate
     * @param process the origin BPMN Process
     * @param bpmnBehavior the BPMN container
     * @param collaboration the BPMN Collaboration to use
     */
    @objid ("1ca1a424-6004-45c3-b25a-d6dc4163d765")
    private void movePoolContentToNewProcess(final PrintWriter logger, MofSmObjectImpl origPool, MofSmObjectImpl process, MofSmObjectImpl bpmnBehavior, MofSmObjectImpl collaboration) {
        ArrayList<MofSmObjectImpl> processDiagrams = new ArrayList<>(process.getDep("Product"));
        processDiagrams.removeIf(diagram -> diagram.getMClass() != this.mm.bpmnProcessCollaborationDiagramMC);
        
        final List<MofSmObjectImpl> poolOnlyLane = getAndClearDep(origPool, "Lane");
        
        // We have to move the Pool content to a new Process.
        List<MofSmObjectImpl> oldProcessElements = process.getDep("FlowElement");
        
        // Move the lanes content into the new process
        for (MofSmObjectImpl lane : poolOnlyLane) {
            MofSmObjectImpl laneRepresentedElement = lane.getSingleDep("PartitionElement");
        
            // The pool name is in the Lane
            final String newProcessName;
            if ((lane.getName().isEmpty() || poolHasDefaultName(lane)) && laneRepresentedElement != null) {
                newProcessName = laneRepresentedElement.getName();
            } else {
                newProcessName = lane.getName();
            }
        
            // Create the new process
            MofSmObjectImpl newProcess = this.mofSession.createObject(this.mm.processMclass, newProcessName);
            logger.format("   Moving all nodes inside (%s) to new (%s)...%n", lane, newProcess);
        
            // clone the process diagram
            if (false) {
                for (MofSmObjectImpl processDiag : processDiagrams) {
                    // Duplicate the diagram as a process diagram, the original one will be transmuted to a BpmnCollaborationDiagram.
                    // org.modelio.bpmn.diagram.editor.contributor.BpmnMmMigrationContributor will clean the diagram later.
                    MofSmObjectImpl clonedDiagram = cloneDiagram(processDiag, newProcess, this.mm.bpmnProcessDesignDiagramMC);
                }
            }
        
            final List<MofSmObjectImpl> allLaneNodes = getAllLaneNodes(lane);
        
            // Remove or copy the lane
            if (laneRepresentedElement == null) {
                // Move lane nodes into the new process
                // Move child LaneSet into the new Process
                final List<MofSmObjectImpl> processLaneSet = newProcess.getDep("LaneSet");
                final List<MofSmObjectImpl> laneSubLaneSet = getAndClearDep(lane, "ChildLaneSet");
                processLaneSet.addAll(laneSubLaneSet);
            } else {
                // Get first LaneSet  or create new
                MofSmObjectImpl processLaneSet = getAnotherLaneSet(newProcess, null);
        
                moveLaneContentToNewLaneInLaneSet(processLaneSet, lane);
            }
        
            // Transmute the Lane into a Participant
            MofSmObjectImpl participant = this.mofSession.transmute(lane, this.mm.participantMC);
            lane = null; // Don't use 'lane' anymore, it is invalid
        
            // Attach everybody
            newProcess.getDep("DiagramElement").addAll(participant.getDep("DiagramElement"));
            collaboration.getDep("Participants").add(participant);
            participant.getDep("Process").add(newProcess);
            bpmnBehavior.getDep("RootElement").add(newProcess);
            logger.format("    Created %s under %s.\n", newProcess, bpmnBehavior);
        
            // Lane.RepresentedElement ==> Participant.Type if compatible
            if (laneRepresentedElement != null && this.mm.umlClassifierMC.isInstance(laneRepresentedElement)) {
                participant.getDep("Type").add(laneRepresentedElement);
            }
        
            // Move lane nodes into the new process
            List<MofSmObjectImpl> newProcessElements = newProcess.getDep("FlowElement");
            for (MofSmObjectImpl el : allLaneNodes) {
                if (el.getDep("SubProcess").isEmpty()) {
                    if (el.getMClass().hasBase(this.mm.bpmnActivityMC)) {
                        // BpmnBoundaryEvents are not attached to the BpmnLane
                        for (MofSmObjectImpl boundaryEvent : el.getDep("BoundaryEventRef")) {
                            oldProcessElements.remove(boundaryEvent);
                            newProcessElements.add(boundaryEvent);
                        }
                    }
                    oldProcessElements.remove(el);
                    newProcessElements.add(el);
                } else {
                    // Fix 3.6 model bug : nodes moved from Lane to sub process could still reference the Lane
                    el.getDep("Lane").clear();
                }
            }
        
            // Move sequence flows relating new process nodes to the new process
            moveSequenceFlows(logger, oldProcessElements, newProcess, newProcessElements);
        
        }
        
        origPool.delete();
        
    }

    /**
     * Move sequence flows relating new process nodes to the new process.
     */
    @objid ("58634247-0b99-4f23-a619-5d87ff9f8c1b")
    private void moveSequenceFlows(final PrintWriter logger, List<MofSmObjectImpl> oldProcessElements, MofSmObjectImpl newProcess, List<MofSmObjectImpl> newProcessElements) {
        for (MofSmObjectImpl el : new ArrayList<>(oldProcessElements)) {
            if (el.getMClass().hasBase(this.mm.bpmnSequenceFlowMC)) {
                MofSmObjectImpl source = el.getSingleDep("SourceRef");
                MofSmObjectImpl target = el.getSingleDep("TargetRef");
                MofSmObjectImpl sourceContainer = source.getSingleDep("Container");
                MofSmObjectImpl targetContainer = target.getSingleDep("Container");
                MofSmObjectImpl flowContainer = el.getSingleDep("Container");
                if (newProcess.equals(sourceContainer) &&
                        newProcess.equals(targetContainer)) {
                    oldProcessElements.remove(el);
                    newProcessElements.add(el);
                    logger.format("       move %s [%s --> %s] from %s to %s \n", el, source, target, flowContainer, newProcess);
                } else if (!Objects.equals(sourceContainer, targetContainer)) {
                    logger.format("      WARN : %s relates elements from different containers:\n\tsource:%s from %s\n\ttarget:%s from %s.\n",
                            el, source, sourceContainer, target, targetContainer);
                }
            }
        }
        
    }

    @objid ("242f4d31-00f6-4d81-8fc0-4eeb9ac2cf7e")
    private static boolean poolHasDefaultName(MofSmObjectImpl lane) {
        return ProcessMigrator.defaultPoolName.matcher(lane.getName()).matches();
    }

    /**
     * Tells whether BpmnMessageFlows go from or to one of the BPMN Process nodes.
     * @param process a BPMN Process
     * @return true if the Process / sub Process has message flows
     */
    @objid ("70add332-01f3-470c-9d7a-4fb66e14f24e")
    private boolean processHasMessageFlows(MofSmObjectImpl process) {
        if (this.mm.processMclass.isInstance(process)) {
            for (MofSmObjectImpl laneSet : process.getDep(this.laneSetDep.getName())) {
                if (laneSetHasMessageFlows(laneSet)) {
                    return true;
                }
            }
        }
        for (MofSmObjectImpl flowEl : process.getDep("FlowElement")) {
            if (this.mm.bpmnSubProcessMC.isInstance(flowEl)) {
                if (processHasMessageFlows(flowEl)) {
                    return true;
                }
            }
            if (!flowEl.getDep("IncomingFlow").isEmpty() || !flowEl.getDep("OutgoingFlow").isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tells whether a BPMN Process has BPMN nodes at root, eg not in a Lane.
     * @param process a BPMN Process
     * @return whether the BPMN Process has BPMN nodes at root.
     */
    @objid ("b1d0d7a1-a97e-4701-84e5-24a58aed195f")
    private boolean processHasRootNodes(MofSmObjectImpl process) {
        for (MObject flowElement : process.mGet(this.mm.flowElementDep)) {
            final MClass flowElementMc = flowElement.getMClass();
            if ((flowElementMc.hasBase(this.mm.bpmnItemAwareElementMC) || flowElementMc.hasBase(this.mm.bpmnFlowNodeMC))
                    && !flowElementMc.hasBase(this.mm.bpmnBoundaryEventMC)) {
                if (((MofSmObjectImpl) flowElement).getDep("Lane").isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Transmute all elements in the list whose metaclass is exactly the given one.
     * @param dep the elements to transmute
     * @param srcClass the metaclass the element must have to be transmuted
     * @param targetClass the target metaclass
     */
    @objid ("2dbfd2cd-4725-4ecb-84cc-cd70ed4b5148")
    private void transmuteAll(List<MofSmObjectImpl> dep, SmClass srcClass, MofSmClass targetClass) {
        for (MofSmObjectImpl obj : new ArrayList<>(dep)) {
            if (obj.getMClass() == srcClass) {
                this.mofSession.transmute(obj, targetClass);
            }
        }
        
    }

}
