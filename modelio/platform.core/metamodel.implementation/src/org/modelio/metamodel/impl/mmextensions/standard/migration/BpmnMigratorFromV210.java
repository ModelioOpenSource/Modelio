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

package org.modelio.metamodel.impl.mmextensions.standard.migration;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.impl.mmextensions.standard.migration.from_bpmn_36.BpmnLanePartitionMigrator;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMofSession;
import org.modelio.vcore.model.spi.mm.MetamodelChangeDescriptor;
import org.modelio.vcore.model.spi.mm.MofMigrationException;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel.MofBuilder;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmDependency;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * <p>
 * BPMN metamodel Migrator from Modelio 3.7 v2.1.0 to 3.7 v2.2.0 .
 * </p>
 * Metamodel Standard 2.2.0 : BPMN metamodel changes to remove "BpmnBehavior" that no user want to see.
 * <ul>
 * <li>- BpmnBehavior (existing instances are transmuted to BpmnSharedElements or Package)
 * <li>+ BpmnSharedDefinitions
 * <li># BpmnRootElement renamed BpmnSharedElement
 * <li># BpmnProcess ^ Behavior (from UML) instead of BpmnBehavior
 * <li># BpmnCollaboration ^ Behavior (from UML) instead of BpmnBehavior
 * <li># BpmnMessage ^ SharedElement (was BpmnBaseElement)
 * <li>- BpmnProcess.Caller : BpmnCallActivity, now duplicate of Behavior.Caller
 * <li># BpmnProcess and BpmnCollaboration are now CMS nodes
 * </ul>
 * 
 * @author cma
 * @since 3.7
 */
@objid ("dd84c109-cae7-42db-867e-e8b7ff587192")
class BpmnMigratorFromV210 implements IMofRepositoryMigrator {
    @objid ("3da843fa-e651-4280-9cbe-ae8cfb8c69b0")
    private static final String MC_BPMN_BEHAVIOR = "Standard.BpmnBehavior";

    @objid ("dcbcf16b-7a67-42c8-9175-89b45f451636")
    private static final String MC_BPMN_COLLABO = "Standard.BpmnCollaboration";

    @objid ("e2c09f03-bcd3-4f4c-96b1-8a3a8aec6bc9")
    private static final String MC_BPMN_MESSAGE = "Standard.BpmnMessage";

    @objid ("aa1eef7d-0948-4bef-a0a5-f0df717cbaa0")
    private static final String MC_BPMN_PARTICIPANT = "Standard.BpmnParticipant";

    @objid ("714f8af8-69a4-4c7a-bd28-3f327a65c607")
    private static final String MC_BPMN_PROCESS = "Standard.BpmnProcess";

    @objid ("89e4e897-8146-410e-b783-5e30002a05b8")
    private static final String DEP_COLLABORATION_OWNER_EMULATED = "OwnerNameSpaceV220";

    @objid ("1ac09688-3f15-42d7-8102-a95dd5c7d323")
    private static final String DEP_UMLBEHAVIOR_OWNER_EMULATED = BpmnMigratorFromV210.DEP_COLLABORATION_OWNER_EMULATED;

    @objid ("30ef18df-5888-4d19-b7f3-d03752df8441")
    private SmClass bpmnBehaviorMClass;

    @objid ("de005e37-80e8-4089-bb2e-592c86ce5d8b")
    private MofSmClass bpmnCallActivityMC;

    @objid ("e459c099-e8aa-4136-bfba-5755f2aea80d")
    private MofSmClass bpmnCollaborationDiagramMC;

    @objid ("d2727f43-32e2-4e80-bb5f-551a0be36af6")
    private MofSmClass bpmnDefinitionsMC;

    @objid ("23f6ce89-f214-4065-b3da-0b9740ec8322")
    private MofSmClass bpmnProcessDesignDiagramMC;

    @objid ("42c93e9f-fe12-4116-8c67-ef04c94e3d0d")
     MofSmClass bpmnProcessMC;

    @objid ("ed80c829-8bbc-41f4-99a7-029c4b7a95cd")
     MofSmClass collaboMclass;

    @objid ("d730dc85-1e72-4a0b-90ac-cfdfd24cd758")
    private MofSmClass messageMClass;

    @objid ("7cac8901-1006-40d8-8db5-18c604c6793d")
    private MofSmClass participantMC;

    @objid ("505289b2-773f-44b6-9289-6db555558045")
    private MofSmClass umlBehaviorMC;

    @objid ("aa6b1ce1-0390-455e-80ef-ec375f5034f6")
     MofSmClass umlPackageMC;

    @objid ("8c1b87f4-6904-4ec0-91d1-70ca76c20d50")
    private MofSmDependency laneSetDep;

    @objid ("323cb4ec-f826-48b4-b0f4-16414462f3d5")
    private MDependency participantDep;

    @objid ("4bdf5679-eb6f-498e-ad99-1fcc353eb911")
    private final MetamodelVersionDescriptor sourceMetamodel;

    @objid ("2f0c4061-3c91-4b53-b1f9-3e00bb997945")
    private final MetamodelVersionDescriptor targetMetamodel;

    @objid ("cc875a77-53bc-48ea-829f-0ab2d706012c")
    private static final Pattern collabDummyName = Pattern.compile("(Collaboration(\\s*[0-9]*))|locals");

    @objid ("45c4422c-49a2-466f-b1cc-eb15364737ec")
    private static final Pattern processDummyName = Pattern.compile("(Process(\\s*[0-9]*))");

    @objid ("5879ec25-fef9-41ce-a9c0-6eab55a3418a")
    public BpmnMigratorFromV210(MetamodelVersionDescriptor sourceMetamodel, MetamodelVersionDescriptor targetMetamodel) {
        this.sourceMetamodel = sourceMetamodel;
        this.targetMetamodel = targetMetamodel;
    }

    @objid ("925c4c1e-1c41-4bf4-aebc-b94abcd37c1c")
    @Override
    public MetamodelChangeDescriptor getMetamodelChanges() {
        return new MetamodelChangeDescriptor()
                                .classRemoved(StandardMetamodel.NAME, "BpmnBehavior")
                                .addClass(StandardMetamodel.NAME, "BpmnSharedDefinitions")
                                .newCmsNode(StandardMetamodel.NAME, "BpmnProcess")
                                .newCmsNode(StandardMetamodel.NAME, "BpmnCollaboration");
    }

    @objid ("df2bff8d-3f83-455a-bd54-e12caeb49250")
    @Override
    public MetamodelVersionDescriptor getSourceMetamodel() {
        return this.sourceMetamodel;
    }

    @objid ("733ab787-7f6b-4840-b852-f140d98b8032")
    @Override
    public MetamodelVersionDescriptor getTargetMetamodel() {
        return this.targetMetamodel;
    }

    /**
     * Modify the metamodel so that it can read the source repository.
     * @param metamodel the metamodel at the final state
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on fatal failure preventing migration
     */
    @objid ("d36f4cec-b792-4a7c-a092-e8d5f64cff15")
    @Override
    public void prepareMetamodel(MofMetamodel metamodel) throws MofMigrationException {
        // Get pointers to used metaclasses
        this.bpmnProcessMC = (MofSmClass) requireMClass(metamodel, BpmnMigratorFromV210.MC_BPMN_PROCESS);
        this.collaboMclass = (MofSmClass) requireMClass(metamodel, BpmnMigratorFromV210.MC_BPMN_COLLABO);
        this.participantMC = (MofSmClass) requireMClass(metamodel, BpmnMigratorFromV210.MC_BPMN_PARTICIPANT);
        this.umlBehaviorMC = (MofSmClass) requireMClass(metamodel, Behavior.MQNAME);
        this.bpmnDefinitionsMC = (MofSmClass) requireMClass(metamodel, BpmnSharedDefinitions.MQNAME);
        this.umlPackageMC = (MofSmClass) requireMClass(metamodel, org.modelio.metamodel.uml.statik.Package.MQNAME);
        this.messageMClass = (MofSmClass) requireMClass(metamodel, BpmnMigratorFromV210.MC_BPMN_MESSAGE);
        
        this.bpmnProcessDesignDiagramMC = (MofSmClass) requireMClass(metamodel, "Standard.BpmnProcessDesignDiagram");
        this.bpmnCollaborationDiagramMC = (MofSmClass) requireMClass(metamodel, "Standard.BpmnCollaborationDiagram");
        
        MofSmClass CallBehaviorActionMc = requireMClass(metamodel, CallBehaviorAction.MQNAME);
        
        this.participantDep = this.bpmnProcessMC.getDependency("Participant");
        
        assert (this.bpmnProcessMC.getDependency("LaneSet") != null);
        assert (this.participantDep != null);
        
        // Merge BPMN metamodel 2.1.0 into the current one.
        try (MofBuilder b = metamodel.builder().setTemporary(true);) {
        
            this.bpmnBehaviorMClass = metamodel.getMClass(BpmnMigratorFromV210.MC_BPMN_BEHAVIOR);
            if (this.bpmnBehaviorMClass == null) {
                this.bpmnBehaviorMClass = b.createClass("BpmnBehavior", StandardMetamodel.NAME, true)
                        .setVersion(new Version(2, 1, 0))
                        .setParent(this.umlBehaviorMC)
                        .build();
        
                b.createDep("RootElement")
                        .setSource((MofSmClass) this.bpmnBehaviorMClass)
                        .setTarget(BpmnSharedElement.MQNAME)
                        .setCardinality(0, -1)
                        .setComposition()
                        .setOpposite("Owner")
                        .build();
            }
        
            // Restore BpmnCollaboration and BpmnProcess inheritance toward BpmnRootElement (renamed BpmnSharedElement) abstract metaclass.
            SmClass rootElementMc = requireMClass(metamodel, BpmnSharedElement.MQNAME);
            this.collaboMclass.setParent(rootElementMc);
            this.bpmnProcessMC.setParent(rootElementMc);
        
            // Emulate composition opposites on BpmnProcess and BpmnCollaboration toward that exist on UML Behavior
            MofSmClass NameSpaceMC = requireMClass(metamodel, NameSpace.MQNAME);
            MofSmClass OperationMC = requireMClass(metamodel, Operation.MQNAME);
        
            for (MofSmClass cls : new MofSmClass[] { this.collaboMclass, this.bpmnProcessMC }) {
                b.createDep(BpmnMigratorFromV210.DEP_COLLABORATION_OWNER_EMULATED)
                        .setSource(cls)
                        .setTarget(NameSpaceMC)
                        .setCardinality(0, 1)
                        .setOpposite("OwnedBehavior") // asymetric
                        .build();
        
                b.createDep("OwnerOperationV220")
                        .setSource(cls)
                        .setTarget(OperationMC)
                        .setCardinality(0, 1)
                        .setOpposite("OwnedBehavior") // asymetric
                        .build();
            }
        
            // "Behavior.Owner : NameSpace" overrides "BpmnRootElement.Owner : BpmnBehavior" on BpmnCollaboration and BpmnProcess
            // so that on migration the "Owner" dependency content is mixed.
            // Then, trying to delete BpmnCollaboration remove them from either Namespace.OwnedBehavior or BpmnBehavior.RootElement but not both.
            // Rename the new dependency to avoid collision. As this dependency is not persisted this will have no impact on save.
            MofSmDependency behaviorOwnerDep = (MofSmDependency) this.umlBehaviorMC.getDependency("Owner");
            behaviorOwnerDep.rename(BpmnMigratorFromV210.DEP_UMLBEHAVIOR_OWNER_EMULATED /* "Owner_V220" */);
        
            // Restore "BpmnCallActivity.CalledProcess : BpmnProcess", opposite = 'BpmnProcess.Caller : BpmnCallActivity'
            // that clashes with 'Behavior.Caller : CallBehaviorAction', opposite of 'CallBehaviorAction.Called : Behavior'
            //
            // Some of these dependenciues may not exist or their opposite may be wrong:
            // - 'BpmnProcess.Caller : BpmnCallActivity' : may be broken, rename 'Caller_V210'
            // - 'Behavior.Caller : CallBehaviorAction' : may be broken
            // - 'CallBehaviorAction.Called : Behavior' : exist for sure, opposite may be wrong
            // - 'BpmnCallActivity.CalledProcess : BpmnProcess' : may not exist, opposite may be broken, rename opposite 'Caller_V210'
            this.bpmnCallActivityMC = requireMClass(metamodel, BpmnCallActivity.MQNAME);
        
            // 'BpmnProcess.Caller : BpmnCallActivity' : may be broken, rename 'Caller_V210'
            MofSmDependency processCallerDep = b.createDep("Caller").setSource(this.bpmnProcessMC).setTarget(this.bpmnCallActivityMC).setCardinality(0, -1).setTemporary(true).getOrCreate();
            processCallerDep.rename("Caller_V210");
        
            // 'Behavior.Caller : CallBehaviorAction' : may be broken
            MofSmDependency behaviorCallerDep = b.createDep("Caller").setSource(this.umlBehaviorMC).setTarget(CallBehaviorActionMc).setCardinality(0, -1).setTemporary(false).getOrCreate();
        
            // 'CallBehaviorAction.Called : Behavior' : exist for sure, opposite may be wrong
            MofSmDependency callBehaviorCalledDep = (MofSmDependency) CallBehaviorActionMc.getDependency("Called");
            assert (callBehaviorCalledDep != null);
        
            // 'BpmnCallActivity.CalledProcess : BpmnProcess' : may not exist, opposite may be broken, rename opposite 'Caller_V210'
            MofSmDependency callActivityCalledProcessDep = b.createDep("CalledProcess").setSource(this.bpmnCallActivityMC).setTarget(this.bpmnProcessMC).setCardinality(0, 1).addFlag(SmDirective.SMCDPARTOF).setTemporary(true).getOrCreate();
        
            // Enforce all opposites are ok
            processCallerDep.setSymetric(callActivityCalledProcessDep);
            callActivityCalledProcessDep.setSymetric(processCallerDep);
        
            behaviorCallerDep.setSymetric(callBehaviorCalledDep);
            callBehaviorCalledDep.setSymetric(behaviorCallerDep);
        }
        
        assert (this.bpmnBehaviorMClass != null);
        assert (this.bpmnBehaviorMClass.getDependency("RootElement") != null);
        
        // - 'BpmnProcess.Caller : BpmnCallActivity' : may be broken, rename 'Caller_V210'
        // - 'Behavior.Caller : CallBehaviorAction' : may be broken
        // - 'CallBehaviorAction.Called : Behavior' : exist for sure, opposite may be wrong
        // - 'BpmnCallActivity.CalledProcess : BpmnProcess' : may not exist, opposite may be broken, rename opposite 'Caller_V210'
        assert (assertDependencyConsistent(this.bpmnProcessMC, "Caller_V210", this.bpmnCallActivityMC, "CalledProcess"));
        assert (assertDependencyConsistent(this.bpmnCallActivityMC, "CalledProcess", this.bpmnProcessMC, "Caller_V210"));
        
        assert (assertDependencyConsistent(this.umlBehaviorMC, "Caller", CallBehaviorActionMc, "Called"));
        assert (assertDependencyConsistent(CallBehaviorActionMc, "Called", this.umlBehaviorMC, "Caller"));
    }

    @objid ("8906b954-7a6c-48e2-a45d-ce827b07eb0c")
    @Override
    public void run(IModelioProgress monitor, IMofSession mofSession) throws MofMigrationException {
        SubProgress mon = SubProgress.convert(monitor, 7);
        @SuppressWarnings ("resource")
        PrintWriter logger = mofSession.getReport().getLogger();
        
        fixBpmnCallActivities(mon.newChild(1), mofSession);
        
        new BpmnLanePartitionMigrator(mofSession).run(mon.newChild(1));
        
        
        final Collection<MofSmObjectImpl> existingBpmn = new ArrayList<>(mofSession.findByClass(this.bpmnBehaviorMClass, true));
        mon.worked(1);
        mon.setWorkRemaining(existingBpmn.size());
        
        for (MofSmObjectImpl bpmnBehavior : existingBpmn) {
            BehaviorContent content = new BehaviorContent(bpmnBehavior);
        
            if (content.ownedProcesses.size() == 1 && content.ownedCollaborations.size() == 1) {
                // 1 Process + 1 Collaboration : Move the collaboration under process and delete behavior
                content.ownedProcesses.get(0).getDep("DefinitionalCollaboration").addAll(content.ownedCollaborations);
                bpmnBehavior.getDep("RootElement").removeAll(content.ownedCollaborations);
                content.rootElements.removeAll(content.ownedCollaborations);
        
                fixRootElementsName(logger, bpmnBehavior, content.ownedProcesses);
                moveBehaviorContentToParent(mofSession, bpmnBehavior, content);
            } else if (content.ownedProcesses.size() > 1 && content.ownerPackage != null) {
                // More than 1 Process : Change BpmnBehavior to Package
                changeBpmnBehaviorToPackage(mofSession, bpmnBehavior, content);
            } else {
                // Other cases : Move all Collaborations and Processes to parent Namespace or Operation,
                // then delete BpmnBehavior
                fixRootElementsName(logger, bpmnBehavior, content.rootElements);
                moveBehaviorContentToParent(mofSession, bpmnBehavior, content);
            }
        
            mon.worked(1);
        }
        
        assert (checkMigration(mofSession, existingBpmn));
    }

    @objid ("f8ba2837-37cc-457b-b6a7-14d1c0071fd9")
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[from " + getSourceMetamodel() + " to " + getTargetMetamodel() + "]";
    }

    @objid ("fa1e7dbc-54f8-432d-a608-5c6b078863a6")
    private boolean checkMigration(IMofSession mofSession, final Collection<MofSmObjectImpl> existingBpmn) {
        Collection<MObject> remainingBehavior = mofSession.getTargetRepository().findByClass(this.bpmnBehaviorMClass, false);
        
        if (remainingBehavior.stream().anyMatch(o -> o.isValid())) {
            Collection<MObject> remainingBehavior2 = mofSession.getTargetRepository().findByClass(this.bpmnBehaviorMClass, true);
        
            String msg = String.format("%s: some %s remain after migration:\n"
                    + "Initial:%s\n\n"
                    + "Remaining without subclasses:%s\n\n"
                    + "Remaining with subclasses:%s\n\n",
                    getClass().getSimpleName(),
                    this.bpmnBehaviorMClass,
                    existingBpmn.stream().map(o -> o.toString() + " " + o.getStatus()).collect(Collectors.joining("\n  -", "\n  -", "")),
                    remainingBehavior.stream().filter(MObject::isValid).map(o -> o.toString() + " " + o.getStatus()).collect(Collectors.joining("\n  -", "\n  -", "")),
                    remainingBehavior2.stream().filter(MObject::isValid).map(o -> o.toString() + " " + o.getStatus()).collect(Collectors.joining("\n  -", "\n  -", "")));
            throw new AssertionError(msg);
        }
        return true;
    }

    /**
     * Move all Collaborations and Processes to parent Namespace or Operation then delete BpmnBehavior .
     * @param mofSession the MOF session
     * @param bpmnBehavior the BPMN behavior to process
     */
    @objid ("b9832b89-b181-4234-9417-1f25e18f61cf")
    private void moveBehaviorContentToParent(final IMofSession mofSession, MofSmObjectImpl bpmnBehavior, BehaviorContent content) {
        final List<MofSmObjectImpl> rootElements = bpmnBehavior.getDep("RootElement");
        
        // Move all Collaborations and Processes to parent Namespace or Operation
        for (MofSmObjectImpl rootEl : content.rootElements) {
            if (rootEl.getMClass() == this.collaboMclass || rootEl.getMClass() == this.bpmnProcessMC) {
                // Move Collaboration or Process to parent Namespace or Operation
                mofSession.getReport().getLogger().format("    Moving %s from %s to %s...\n", rootEl, bpmnBehavior, content.owner);
        
                rootElements.remove(rootEl);
                // content.owner.getDep("OwnedBehavior").add(rootEl);
                rootEl.getDep(BpmnMigratorFromV210.DEP_COLLABORATION_OWNER_EMULATED).add(content.owner);
            }
        
            if (rootEl.getMClass() == this.messageMClass) {
                // Move Bpmn Messages under the first Collaboration
                for (MofSmObjectImpl collaboration : content.ownedCollaborations) {
                    mofSession.getReport().getLogger().format("    Moving %s from %s to %s...\n", rootEl, bpmnBehavior, collaboration);
                    rootElements.remove(rootEl);
                    collaboration.getDep("Messages").add(rootEl);
                    break;
                }
            }
        }
        
        // Move all owned diagrams under the first found BPMN Collaboration or the first found Process
        {
            MofSmObjectImpl b = bpmnBehavior;
            Stream.concat(content.ownedCollaborations.stream(), content.ownedProcesses.stream())
                    .findFirst()
                    .ifPresent(el -> el.getDep("Product").addAll(new ArrayList<>(b.getDep("Product"))));
        }
        
        fixBpmnBehaviorCallers(mofSession, bpmnBehavior, content);
        
        if (rootElements.isEmpty()) {
            // The BpmnBehavior now owns no root BPMN element
            if (bpmnBehavior.getCompositionChildren().isEmpty()) {
                mofSession.getReport().getLogger().format("   Deleting now empty %s...\n", bpmnBehavior);
                bpmnBehavior.delete();
            } else {
                // The BpmnBehavior still own tags, properties, notes
                if (true) {
                    mofSession.getReport().getLogger().format("   %s still own elements, they will be lost : %s\n", bpmnBehavior, bpmnBehavior.getCompositionChildren());
                    bpmnBehavior.delete();
                } else {
                    // Transmute BpmnBehavior to BpmnCollaboration
                    mofSession.getReport().getLogger().format("   %s still own elements, transmute it to BpmnCollaboration : %s\n", bpmnBehavior, bpmnBehavior.getCompositionChildren());
                    MofSmObjectImpl newCollabo = mofSession.transmute(bpmnBehavior, this.collaboMclass);
                    bpmnBehavior = null;
                    if (content.ownedProcesses.size() == 1) {
                        // Move created collaboration under the process
                        content.ownedProcesses.get(0).getDep("DefinitionalCollaboration").add(newCollabo);
                    }
                }
            }
        } else {
            // The BpmnBehavior still owns BPMN Artifacts, Resources ... transmute the BpmnBehavior to BpmnSharedDefinitions
            mofSession.getReport().getLogger().format("   %s still has roots, transmute it to BpmnSharedDefinitions : %s\n", bpmnBehavior, rootElements);
            mofSession.transmute(bpmnBehavior, this.bpmnDefinitionsMC);
        }
    }

    /**
     * Changes :
     * - b.Caller : CallBehaviorAction.Called and
     * - b.EffectOf : Transition.BehaviorEffect and
     * - b.BpmnCaller : BpmnCallActivity.CalledBehavior
     * 
     * So that they point to the contained Process or Collaboration.
     * @param bpmnBehavior the BPMN Behavior to migrate
     * @param content its initial content
     */
    @objid ("fc744eae-93f3-4f03-bf75-65abaf959e79")
    @SuppressWarnings ("resource")
    private void fixBpmnBehaviorCallers(IMofSession mofSession, MofSmObjectImpl bpmnBehavior, BehaviorContent content) {
        final PrintWriter logger = mofSession.getReport().getLogger();
        
        // Determine new target
        MofSmObjectImpl newTarget;
        if (content.ownedProcesses.size() == 1) {
            newTarget = content.ownedProcesses.get(0);
        } else if (content.ownedCollaborations.isEmpty()) {
            if (!content.ownedProcesses.isEmpty()) {
                newTarget = content.ownedProcesses.get(0);
            } else {
                if (bpmnBehavior.getDep("Caller").isEmpty() && bpmnBehavior.getDep("EffectOf").isEmpty() && bpmnBehavior.getDep("BpmnCaller").isEmpty()) {
                    return;
                }
        
                logger.format("   WARN %s: don't know where to put Callers of %s, its roots are %s.\n",
                        getClass().getSimpleName(), bpmnBehavior, content.rootElements);
                return;
            }
        } else {
            newTarget = content.ownedCollaborations.get(0);
        }
        
        // Move
        for (MofSmObjectImpl o : new ArrayList<>(bpmnBehavior.getDep("Caller"))) {
            logger.format("   Change %s target to %s\n", o, newTarget);
            o.getDep("Called").add(newTarget);
        }
        for (MofSmObjectImpl o : new ArrayList<>(bpmnBehavior.getDep("EffectOf"))) {
            logger.format("   Change %s target to %s\n", o, newTarget);
            o.getDep("BehaviorEffect").add(newTarget);
        }
        for (MofSmObjectImpl o : new ArrayList<>(bpmnBehavior.getDep("BpmnCaller"))) {
            logger.format("   Change %s target to %s\n", o, newTarget);
            o.getDep("CalledBehavior").add(newTarget);
        }
    }

    @objid ("4a90c5c2-310d-411b-994f-18314b7e1f9e")
    private void fixBpmnCallActivities(IModelioProgress monitor, IMofSession mofSession) {
        SubProgress mon = SubProgress.convert(monitor, 5);
        
        final Collection<MofSmObjectImpl> existingCalls = new ArrayList<>(mofSession.findByClass(this.bpmnCallActivityMC, true));
        mon.worked(1);
        mon.setWorkRemaining(existingCalls.size());
        
        for (MofSmObjectImpl call : existingCalls) {
            final List<MofSmObjectImpl> oldDep = call.getDep("CalledProcess");
            final List<MofSmObjectImpl> newDep = call.getDep("CalledBehavior");
            newDep.addAll(new ArrayList<>(oldDep));
            oldDep.clear();
        }
    }

    @objid ("6b8ed274-ca8c-4cf4-a134-0a32a1497526")
    private void fixRootElementsName(PrintWriter logger, MofSmObjectImpl bpmnBehavior, Collection<MofSmObjectImpl> rootEls) {
        for (MofSmObjectImpl rootEl : rootEls) {
            Pattern dummyNameRegex = null;
            if (rootEl.getMClass() == this.collaboMclass) {
                dummyNameRegex = BpmnMigratorFromV210.collabDummyName;
            } else if (rootEl.getMClass() == this.bpmnProcessMC) {
                dummyNameRegex = BpmnMigratorFromV210.processDummyName;
            }
        
            if (dummyNameRegex != null) {
                Matcher m = dummyNameRegex.matcher(rootEl.getName());
                if (m.matches()) {
                    String newName = bpmnBehavior.getName();
                    if (m.group(2) != null) {
                        newName += m.group(2);
                    }
        
                    logger.format("   Renaming %s under %s '%s'\n", rootEl, bpmnBehavior, newName);
                    rootEl.setName(newName);
                }
            }
        }
    }

    @objid ("2ecabdbc-a627-425d-bbab-4553a47ea4a5")
    private void changeBpmnBehaviorToPackage(IMofSession mofSession, MofSmObjectImpl bpmnBehavior, BehaviorContent content) {
        final String bpmnBehaviorStr = bpmnBehavior.toString();
        
        // Clear dep before transmutation
        bpmnBehavior.getDep("RootElement").clear();
        
        fixBpmnBehaviorCallers(mofSession, bpmnBehavior, content);
        
        // Transmute behavior into package
        content.ownerPackage.getDep("OwnedBehavior").remove(bpmnBehavior);
        MofSmObjectImpl newPackage = mofSession.transmute(bpmnBehavior, this.umlPackageMC);
        bpmnBehavior = null; // bpmnBehavior is not usable anymore
        content.ownerPackage.getDep("OwnedElement").add(newPackage);
        
        // Dispatch behavior content into new package
        
        // Move all Collaborations and Processes to parent Namespace or Operation
        for (MofSmObjectImpl rootEl : content.rootElements) {
            MClass rootCls = rootEl.getMClass();
            if (rootCls == this.collaboMclass || rootCls == this.bpmnProcessMC) {
                // Move Collaboration or Process to parent Namespace or Operation
                mofSession.getReport().getLogger().format("    Moving %s from %s to %s...\n", rootEl, bpmnBehaviorStr, newPackage);
        
                newPackage.getDep("OwnedBehavior").add(rootEl);
                // rootEl.getDep(BpmnMigratorFromV210.DEP_COLLABORATION_OWNER_EMULATED).add(newPackage);
            }
        
            if (rootCls == this.collaboMclass) {
                // Move Bpmn Messages under the Collaboration
                for (MofSmObjectImpl bpmnMsg : content.ownedOthers.stream()
                        .filter(elt -> elt.getMClass() == this.messageMClass)
                        .collect(Collectors.toList())) {
                    mofSession.getReport().getLogger().format("    Moving %s from %s to %s...\n", bpmnMsg, bpmnBehaviorStr, rootEl);
        
                    content.ownedOthers.remove(bpmnMsg);
                    rootEl.getDep("Messages").add(bpmnMsg);
                }
            }
        }
        
        // Move all owned BPMN diagrams under the first found BPMN Collaboration or the first found Process
        {
            List<MofSmObjectImpl> behaviorDiagrams = newPackage.getDep("Product")
                    .stream()
                    .filter(o -> this.bpmnProcessDesignDiagramMC.isInstance(o) || this.bpmnCollaborationDiagramMC.isInstance(o))
                    .collect(Collectors.toList());
        
            Stream.concat(content.ownedCollaborations.stream(), content.ownedProcesses.stream())
                    .findFirst()
                    .ifPresent(el -> {
                        el.getDep("Product").addAll(behaviorDiagrams);
                    });
        }
        
        if (!content.ownedOthers.isEmpty()) {
            // The BpmnBehavior owned BPMN Artifacts, Resources ... move them to a BpmnSharedDefinitions
            MofSmObjectImpl bpmnDefs = mofSession.createObject(this.bpmnDefinitionsMC, newPackage.getName());
            mofSession.getReport().getLogger().format("   %s still has roots, move them to a BpmnSharedDefinitions : %s\n", bpmnBehaviorStr, content.ownedOthers);
            bpmnDefs.getDep("RootElement").addAll(content.ownedOthers);
        
            newPackage.getDep("OwnedBehavior").add(bpmnDefs);
        }
    }

    @objid ("77d0d555-5d9f-4e10-b30d-c5c59d8e4339")
    @SuppressWarnings ("unchecked")
    private static <T extends SmClass> T requireMClass(MofMetamodel metamodel, String name) {
        return (T) Objects.requireNonNull(metamodel.getMClass(name), name);
    }

    @objid ("6e8a01a3-ade4-4fe4-973d-fe858a311de6")
    private static boolean assertDependencyConsistent(MofSmClass src, String name, MofSmClass target, String oppositeName) {
        MDependency dep = src.getDependency(name);
        assert (dep != null) : String.format("%s.%s : %s missing", src, name, target);
        assert (dep.getTarget() == target) : String.format("%s.%s : target should be %s.", src, dep, target);
        
        MDependency opposite = dep.getSymetric();
        assert (opposite != null) : String.format("%s.%s has no opposite.", src, dep);
        assert (opposite.getName().equals(oppositeName)) : String.format("%s.%s opposite of %s.%s name should be '%s'.", opposite.getSource(), opposite, src, dep, oppositeName);
        assert (opposite.getSource() == target) : String.format("%s.%s opposite of %s.%s source should be %s.", opposite.getSource(), opposite, src, dep, target);
        assert (opposite.getTarget() == src) : String.format("%s.%s opposite of %s.%s target should be %s.", opposite.getSource(), opposite, src, dep, src);
        return true;
    }

    /**
     * Parsed snapshot of the content of a BpmnBehavior at the moment it is instantiated.
     */
    @objid ("ffaf31ee-8373-4f5b-ad0e-51cdb0c7c9e3")
    private class BehaviorContent {
        @objid ("143fced7-29a3-4d6c-b3e9-0cdf95e66762")
        public final List<MofSmObjectImpl> rootElements;

        @objid ("22702cdf-4fc0-43f7-a108-ea46bca7167d")
        public final List<MofSmObjectImpl> ownedProcesses;

        @objid ("64ff73d2-5fc5-424f-a641-033e339e19f2")
        public final List<MofSmObjectImpl> ownedCollaborations;

        @objid ("74ef643f-1fbb-4a4b-99af-43b8955637de")
        public final MofSmObjectImpl owner;

        @objid ("b9f40892-87ed-4649-b02e-ed354f3e7eed")
        public final MofSmObjectImpl ownerPackage;

        /**
         * BpmnRootElements that are neither Process nor Collaboration.
         */
        @objid ("9143b35b-b89e-405b-8011-5cdbbd68d7a2")
        public final List<MofSmObjectImpl> ownedOthers;

        @objid ("52e59ca0-ec3e-49c1-9dbd-2ce2d11802e1")
        public BehaviorContent(MofSmObjectImpl bpmnBehavior) {
            this.rootElements = new ArrayList<>(bpmnBehavior.getDep("RootElement"));
            this.ownedProcesses = new ArrayList<>();
            this.ownedCollaborations = new ArrayList<>();
            this.ownedOthers = new ArrayList<>();
            this.owner = (MofSmObjectImpl) bpmnBehavior.getCompositionOwner();
            if (this.owner != null && BpmnMigratorFromV210.this.umlPackageMC.isInstance(this.owner)) {
                this.ownerPackage = this.owner;
            } else {
                this.ownerPackage = null;
            }
            
            for (MofSmObjectImpl o : this.rootElements) {
                MClass mc = o.getMClass();
                if (mc == BpmnMigratorFromV210.this.bpmnProcessMC) {
                    this.ownedProcesses.add(o);
                } else if (mc == BpmnMigratorFromV210.this.collaboMclass) {
                    this.ownedCollaborations.add(o);
                } else {
                    this.ownedOthers.add(o);
                }
            }
        }

    }

}
