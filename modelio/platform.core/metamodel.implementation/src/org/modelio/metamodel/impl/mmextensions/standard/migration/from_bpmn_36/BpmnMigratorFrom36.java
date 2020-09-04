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

package org.modelio.metamodel.impl.mmextensions.standard.migration.from_bpmn_36;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMofSession;
import org.modelio.vcore.model.spi.mm.MetamodelChangeDescriptor;
import org.modelio.vcore.model.spi.mm.MofMigrationException;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel.MofBuilder;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmDependency;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * <p>
 * BPMN metamodel Migrator from Modelio 3.6 to 3.7 .
 * </p>
 * <p/>
 * <ul>
 * <li># BpmnProcess.Participant : cardinality 0..1 --&gt; *
 * <li># BpmnProcess.LaneSet : cardinality * --&gt; 0..1
 * </ul>
 * In Modelio 3.6 a &quot;Pool&quot; was a LaneSet containing one Lane. <br/>
 * In Modelio 3.7 a &quot;Pool&quot; is a BpmnParticipant referencing a BpmnProcess. <br/>
 * The 3.6 &quot;Pool&quot; must be deleted and its content moved into the BpmnProcess itself. <br/>
 * For Modelio 3.6 BpmnProcess containing many &quot;Pools&quot;:
 * <ul>
 * <li>must be split in as many process as "Pools"</li>
 * <li>one BpmnCollaboration must be created for the 3.6 process</li>
 * <li>BpmnParticipant must be created for each "Pool" owned by the BpmnCollaboration</li>
 * <li>the diagram must be moved to the BpmnBehavior and changed to a collaboration diagram</li>
 * </ul>
 * 
 * @author cma
 * @since 3.7
 */
@objid ("8408e8e1-e80d-4702-8a91-bc3a1617f171")
public class BpmnMigratorFrom36 implements IMofRepositoryMigrator {
    @objid ("2439017b-7f35-470f-8684-2070c3c8f37f")
    private final MetamodelVersionDescriptor sourceMetamodel;

    @objid ("50338110-c2f4-4129-bcc4-1e7344eb60b8")
    private final MetamodelVersionDescriptor targetMetamodel;

    @objid ("28cb2a1a-8c5e-4932-8caa-faf348d44c53")
    private static final Pattern collabDummyName = Pattern.compile("(Collaboration\\s*[0-9]*)|locals");

    @objid ("b5c331d0-30d2-4476-8624-1948d32cf9d1")
    private MM mm;

    @objid ("323c3e28-d377-46f7-b8ab-c85d608e8d7b")
    public BpmnMigratorFrom36(MetamodelVersionDescriptor sourceMetamodel, MetamodelVersionDescriptor targetMetamodel) {
        this.sourceMetamodel = sourceMetamodel;
        this.targetMetamodel = targetMetamodel;
    }

    @objid ("8fedbe21-5c38-4a9e-9028-e28d820f8bfd")
    @Override
    public MetamodelChangeDescriptor getMetamodelChanges() {
        return new MetamodelChangeDescriptor();
    }

    @objid ("6b6b08e1-e369-452f-9c14-5fbdc2033236")
    @Override
    public MetamodelVersionDescriptor getSourceMetamodel() {
        return this.sourceMetamodel;
    }

    @objid ("352387b1-d982-4aa5-a27b-095e0740c96f")
    @Override
    public MetamodelVersionDescriptor getTargetMetamodel() {
        return this.targetMetamodel;
    }

    /**
     * Modify the metamodel so that it can read the source repository.
     * @param metamodel the metamodel at the final state
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on fatal failure preventing migration
     */
    @objid ("a6fe329b-0fd3-4664-8cff-8cafbf3643b3")
    @Override
    public void prepareMetamodel(MofMetamodel metamodel) throws MofMigrationException {
        this.mm = new MM(metamodel);
        
        // Merge Modelio 3.6 BPMN metamodel into the current one.
        try (MofBuilder b = metamodel.builder().setTemporary(true);) {
            // Restore BpmnProcess.LaneSet cardinality to '*'
            MofSmDependency laneSetDep = (MofSmDependency) this.mm.processMclass.getDependency("LaneSet");
            laneSetDep.setCardinality(0, -1);
        
            // Make BpmnProcessCollaborationDiagram concrete again
            this.mm.bpmnProcessCollaborationDiagramMC.setAbstract(false);
        }
    }

    @objid ("6adf9533-e227-431f-8243-b1aed06deed6")
    @Override
    public void run(IModelioProgress monitor, IMofSession mofSession) throws MofMigrationException {
        SubProgress mon = SubProgress.convert(monitor, 5);
        
        try {
        
            new BpmnLanePartitionMigrator(mofSession).run(mon.newChild(1));
            
            final Collection<MofSmObjectImpl> existingBpmn = new ArrayList<>(mofSession.findByClass(this.mm.bpmnBehaviorMClass, false));
            mon.worked(1);
            mon.setWorkRemaining(existingBpmn.size() + 1);
        
            for (MofSmObjectImpl bpmnBehavior : existingBpmn) {
                migrateBpmnBehavior(mofSession, bpmnBehavior);
        
                mon.worked(1);
            }
        
            new DataAssociationFixer(mofSession, this.mm).run();
            mon.worked(1);
        
        } catch (MetaclassNotFoundException e) {
            throw new MofMigrationException(e.getLocalizedMessage(), e);
        }
    }

    @objid ("0af1cc3c-774f-4a41-8b87-760bfa75e514")
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[from " + getSourceMetamodel() + " to " + getTargetMetamodel() + "]";
    }

    /**
     * Get a MDependency content then empty it.
     * @param obj the dep source
     * @param depName the dep name
     * @return the dependency content.
     */
    @objid ("857a069a-f034-47c1-a07c-e3efe3b67171")
    private static List<MofSmObjectImpl> getAndClearDep(MofSmObjectImpl obj, String depName) {
        List<MofSmObjectImpl> depAccess = obj.getDep(depName);
        List<MofSmObjectImpl> ret = new ArrayList<>(depAccess);
        depAccess.clear();
        return ret;
    }

    /**
     * Transmute all elements in the list whose metaclass is exactly the given one.
     * @param mofSession a MOF session
     * @param dep the elements to transmute
     * @param srcClass the metaclass the element must have to be transmuted
     * @param targetClass the target metaclass
     */
    @objid ("b7f7d0bd-cd1d-4c6c-94c8-094cc2d1fa0b")
    static void transmuteAll(IMofSession mofSession, List<MofSmObjectImpl> dep, SmClass srcClass, MofSmClass targetClass) {
        for (MofSmObjectImpl obj : new ArrayList<>(dep)) {
            if (obj.getMClass() == srcClass) {
                mofSession.transmute(obj, targetClass);
            }
        }
    }

    @objid ("83ecd9a4-4003-4fa3-a406-91440696ef6c")
    private void migrateBpmnBehavior(IMofSession mofSession, MofSmObjectImpl bpmnBehavior) throws MetaclassNotFoundException {
        final List<MofSmObjectImpl> rootElements = new ArrayList<>(bpmnBehavior.getDep("RootElement"));
        
        
        // Delete all Collaborations Participants: they are not used and bother us
        for (MofSmObjectImpl rootEl : rootElements) {
            MClass rootMc = rootEl.getMClass();
            if (rootMc == this.mm.bpmnCollaboMC) {
                getAndClearDep(rootEl, "Participants").forEach(o -> o.delete());
        
                // If the BPMN Collaboration has no meaningful name, rename it to the BPMNBehavior name
                if (BpmnMigratorFrom36.collabDummyName.matcher(rootEl.getName()).matches()) {
                    rootEl.setName(bpmnBehavior.getName());
                }
        
                // Transmute all BpmnProcessCollaborationDiagram to BpmnCollaborationDiagram
                transmuteAll(mofSession, bpmnBehavior.getDep("Product"), this.mm.bpmnProcessCollaborationDiagramMC, this.mm.bpmnCollaborationDiagramMC);
            }
        }
        
        // Migrate each contained BPMN Process
        ProcessMigrator m = new ProcessMigrator(mofSession, this.mm);
        for (MofSmObjectImpl rootEl : rootElements) {
            if (rootEl.getMClass() == this.mm.processMclass) {
                // It is a process
                m.runOnProcess(rootEl);
            }
        }
        
        List<MofSmObjectImpl> collaborations = new ArrayList<>();
        List<MofSmObjectImpl> messages = new ArrayList<>();
        for (MofSmObjectImpl rootEl : new ArrayList<>(bpmnBehavior.getDep("RootElement"))) {
            if (rootEl.getMClass() == this.mm.bpmnMessageMC) {
                messages.add(rootEl);
            } else if (rootEl.getMClass() == this.mm.bpmnCollaboMC) {
                collaborations.add(rootEl);
            }
        }
        
        // Keep only one collaboration containing participants and messages
        MofSmObjectImpl firstCollab = null;
        for (MofSmObjectImpl rootEl : new ArrayList<>(collaborations)) {
            List<MofSmObjectImpl> participants = rootEl.getDep("Participants");
        
            // Delete empty Collaborations except the last one if there are messages
            if (participants.isEmpty() && (messages.isEmpty() || collaborations.size() > 1)) {
                mofSession.getReport().getLogger().format(" Deleting empty %s \n", rootEl);
        
                // Move diagrams on the BpmnBehavior
                List<MofSmObjectImpl> elDiagrams = rootEl.getDep("Product");
                for (MofSmObjectImpl diag : new ArrayList<>(elDiagrams)) {
                    elDiagrams.remove(diag);
                    bpmnBehavior.getDep("Product").add(diag);
                }
        
                collaborations.remove(rootEl);
                rootEl.delete();
            } else if (firstCollab == null) {
                // First collaboration having participants
                firstCollab = rootEl;
        
                if (!messages.isEmpty()) {
                    // Move messages into it
                    mofSession.getReport().getLogger().format(" Moving messages from %s into %s \n", bpmnBehavior, firstCollab);
                    bpmnBehavior.getDep("RootElement").removeAll(messages);
                    firstCollab.getDep("Messages").addAll(messages);
                    messages.clear();
                }
            } else {
                // Not the first collaboration having participants, move them
                mofSession.getReport().getLogger().format(" Moving participants from %s into %s \n", rootEl, firstCollab);
                firstCollab.getDep("Participants").addAll(participants);
                participants.clear();
            }
        }
        
        // Migrates owned diagrams.
        new OwnedDiagramsMigrator(this.mm).run(mofSession, bpmnBehavior, firstCollab);
    }

}
