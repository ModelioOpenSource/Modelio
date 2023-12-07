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
package org.modelio.bpmn.diagram.editor.contributor;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.editor.plugin.DiagramEditorsManager;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.ModelManager;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.gproject.MigrationFailedException;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGProject;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.platform.project.services.IFragmentMigrationContributor;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.model.spi.mm.IMigrationReporter;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Adds a BPMN diagram migration to the metamodel migration of model fragments.
 */
@objid ("8e9bdb8c-affd-4c96-b243-4d60927c0fce")
public class BpmnMmMigrationContributor implements IFragmentMigrationContributor {
    @objid ("834758b3-4e48-40e5-bb4f-e9caeaed1198")
    private static final Version VERSION = new Version (3,7,0);

    @objid ("d3299225-430d-4517-a6cb-31072fb4f0b3")
    @Override
    public Version getTargetModelioVersion() {
        return VERSION;
    }

    @objid ("d73a0a67-3d04-4821-8b2d-b2f289ee0f1e")
    @Override
    public void contributeMigration(IModelioProgress monitor, IMigrationReporter reporter, IGProject gproject, IGModelFragment f, MetamodelVersionDescriptor fromVersion, IEclipseContext eclipseContext) throws MigrationFailedException {
        if (!fromVersion.contains(StandardMetamodel.NAME, new Version(0, 0, 0), new Version(2, 1, 0))) {
            // No migration needed
            return;
        }
        
        SubProgress mon = SubProgress.convert(monitor, 6);
        
        ICoreSession coreSession = gproject.getSession();
        IStandardModelFactory factory = MTools.get(coreSession).getModelFactory(IStandardModelFactory.class);
        
        // Force the style manager to load now
        DiagramStyles.getStyleManager().reloadStylesIn(gproject.getPfs().getProjectPath().resolve(DiagramStyles.PROJECT_STYLE_SUBDIR));
        
        migrateTo2_1_0(reporter, eclipseContext, mon, coreSession, f, factory);
        
    }

    /**
     * Migration from Modelio 3.6 to Modelio 3.7.
     */
    @objid ("b095972f-9777-4ff9-b137-6bcff398ec16")
    private void migrateTo2_1_0(IMigrationReporter reporter, IEclipseContext eclipseContext, SubProgress mon, ICoreSession coreSession, IGModelFragment f, IStandardModelFactory factory) {
        Collection<MObject> created = new HashSet<>();
        IRepository repo = f.getRepository();
        SmMetamodel mm = coreSession.getMetamodel();
        String fragName = f.getId();
        @SuppressWarnings ("resource")
        PrintWriter logger = reporter.getLogger();
        
        String transactionName = "Migration to Modelio 3.7: Create missing BPMN diagrams";
        try (ITransaction t = coreSession.getTransactionSupport().createTransaction(transactionName)) {
            t.disableUndo();
        
            logger.format("Begin %s...%n", transactionName);
        
            // Create BpmnProcessDesignDiagrams for migration
            for (MObject obj : findByClass(repo, mm, BpmnProcess.class)) {
                BpmnProcess p = (BpmnProcess) obj;
                if (p.getProduct(BpmnProcessDesignDiagram.class).isEmpty() || isUnmaskedInAnotherProcessDiagram(p)) {
                    if (p.isModifiable()) {
                        BpmnProcessDesignDiagram pdd = factory.createBpmnProcessDesignDiagram();
                        pdd.setName(p.getName());
                        p.getProduct().add(0, pdd);
                        created.add(pdd);
                        logger.format("  Created %s into %s.%n", pdd, p);
                    } else {
                        logger.format("Cannot create BpmnProcessDesignDiagram into read only %s: %s%n", p, p.getStatus());
                    }
                }
            }
            mon.worked(1);
        
            // Create BpmnSubProcessDiagram for migration
            for (BpmnSubProcess sp : findByClass(repo, mm, BpmnSubProcess.class)) {
                if (sp.getProduct(BpmnSubProcessDiagram.class).isEmpty() || isUnmaskedInAnyProcessDiagram(sp)) {
                    if (sp.isModifiable()) {
                        BpmnSubProcessDiagram spdd = factory.createBpmnSubProcessDiagram();
                        spdd.setName(sp.getName());
                        sp.getProduct().add(0, spdd);
                        created.add(spdd);
                        logger.format("  Created %s into %s.%n", spdd, sp);
                    } else {
                        logger.format("Cannot create BpmnSubProcessDiagram into read only %s: %s%n", sp, sp.getStatus());
                    }
                }
            }
            mon.worked(1);
        
            t.commit();
        }
        
        // Migrate the Gm model
        int i = 1;
        IModelManager manager = new ModelManager(eclipseContext);
        for (BpmnCollaborationDiagram cd : findByClass(repo, mm, BpmnCollaborationDiagram.class)) {
            if (!created.contains(cd)) {
                migrateGmModel(cd, manager, reporter, this::migrateCollaborationGmModel);
                advance(mon, fragName, i++);
            }
        }
        
        for (BpmnProcessDesignDiagram pdd : findByClass(repo, mm, BpmnProcessDesignDiagram.class)) {
            if (!created.contains(pdd)) {
                migrateGmModel(pdd, manager, reporter, this::migrateProcessGmModel);
                advance(mon, fragName, i++);
            }
        }
        
        for (BpmnSubProcessDiagram spdd : findByClass(repo, mm, BpmnSubProcessDiagram.class)) {
            if (!created.contains(spdd)) {
                migrateGmModel(spdd, manager, reporter, this::migrateSubProcessGmModel);
                advance(mon, fragName, i++);
            }
        }
        
    }

    /**
     * Open the given diagram to force the GM model migration.
     */
    @objid ("3373e65e-9ec6-401f-9d0a-01917e107983")
    private void migrateGmModel(AbstractDiagram diagram, IModelManager modelManager, IMigrationReporter reporter, BiConsumer<DiagramHandle, PrintWriter> migrator) {
        @SuppressWarnings ("resource")
        PrintWriter logger = reporter.getLogger();
        
        logger.format("  Migrating %s BPMN diagram in %s to Modelio %s...%n", diagram, diagram.getOrigin(), VERSION);
        
        Display.getDefault().syncExec(() -> {
            ITransactionSupport transactionSupport = modelManager.getModelingSession().getTransactionSupport();
            try (ITransaction t = transactionSupport.createTransaction(String.format("Migrate '%s' BPMN diagram to %s", diagram, VERSION))) {
                t.disableUndo();
                try (DiagramHandle dh = DiagramHandle.create(diagram, modelManager, modelManager.getService(IProjectService.class), modelManager.getService(DiagramEditorsManager.class))) {
                    migrator.accept(dh, logger);
                    dh.save();
                    dh.close();
                    t.commit();
                } catch (LinkageError | RuntimeException e) {
                    // Log, report and continue.
                    String msg = DiagramEditorBpmn.I18N.getMessage(
                            "BpmnMmMigrationContributor.diagramFailed",
                            diagram.getName(),
                            diagram.getUuid(),
                            e.toString());
        
                    logger.println(msg);
                    e.printStackTrace(logger);
        
                    reporter.getResultReporter().println(msg);
                }
            }
        });
        
    }

    @objid ("051d1888-45d5-4222-90b3-dd1bbfd578e2")
    private void migrateProcessGmModel(DiagramHandle dh, PrintWriter logger) {
        if (false) {
            // Aborted try to fix CAFAT 3.6 diagrams.
            // This code could have been usefull if original 3.6 diagrams were cloned
            // to Collaboration and ProcessDesign diagrams.
            BpmnProcess proc = (BpmnProcess) dh.getDiagram().getOrigin();
            if (proc == null)
                return;
        
            for (BpmnParticipant participant : proc.getParticipant()) {
                BpmnCollaboration collaboration = participant.getContainer();
                for (IDiagramGraphic dg : dh.getDiagramGraphics(collaboration)) {
                    logger.format("    Masking '%s' collaboration graphic from %d.%n", dg, dh.getDiagram());
                    dg.mask();
                }
        
                for (IDiagramGraphic collabDg : dh.getDiagramGraphics(participant)) {
                    logger.format("    Masking '%s' participant from %s.%n", collabDg, dh.getDiagram());
                    collabDg.mask();
                }
            }
        
            maskElementsOutsideProcess(proc, dh, logger, dh.getDiagramNode().getNodes());
        }
        
    }

    @objid ("5a3359a9-3786-4814-bb09-96931fa32dd2")
    @Deprecated
    private void maskElementsOutsideProcess(BpmnProcess proc, DiagramHandle dh, PrintWriter logger, List<IDiagramNode> nodes) {
        for (IDiagramNode diagramNode : nodes) {
            MObject representedElement = diagramNode.getElement();
            if ( representedElement instanceof BpmnFlowElement) {
                BpmnFlowElement flowEl = (BpmnFlowElement) representedElement;
                BpmnProcess flowOwner = getProcessOwner(flowEl);
                if (! Objects.equals (flowOwner, proc)) {
                    logger.format("   Masking %s from %s because it is owned by %s instead of %s." , diagramNode, dh.getDiagram(), flowOwner, proc);
                    diagramNode.mask();
                }
            } else if ( representedElement instanceof BpmnLane) {
                BpmnLane lane = (BpmnLane) representedElement;
                BpmnLaneSet laneSet = lane.getLaneSet();
                BpmnProcess laneProcess = laneSet != null ? laneSet.getProcess(): null;
                if (! Objects.equals (laneProcess, proc)) {
                    logger.format("   Masking %s from %s because it is owned by %s instead of %s." , diagramNode, dh.getDiagram(), laneProcess, proc);
                    diagramNode.mask();
                }
            } else if ( representedElement instanceof BpmnParticipant) {
                logger.format("   Masking %s because it is for collaboration diagrams." , diagramNode);
                diagramNode.mask();
            } else {
                maskElementsOutsideProcess(proc, dh, logger, diagramNode.getNodes());
            }
        }
        
    }

    @objid ("5d1452e8-89fc-434f-921a-21650d23a4b9")
    @Deprecated
    private BpmnProcess getProcessOwner(BpmnFlowElement flowEl) {
        BpmnSubProcess sub = flowEl.getSubProcess();
        if (sub != null)
            return getProcessOwner(sub);
        return flowEl.getContainer();
    }

    @objid ("1425568d-61af-43ce-a024-d5079d911e5f")
    @SuppressWarnings ("unused")
    private void migrateSubProcessGmModel(DiagramHandle dh, PrintWriter logger) {
        
    }

    @objid ("18460d3b-1eab-46f9-9ae8-df196ea121ca")
    @SuppressWarnings ("unused")
    private void migrateCollaborationGmModel(DiagramHandle dh, PrintWriter logger) {
        
    }

    /**
     * @param elt a BPMN process
     * @return <code>true</code> if the process is part of at least one {@link BpmnProcessDesignDiagram} (that it does not own)
     * or {@link BpmnProcessCollaborationDiagram}.
     */
    @objid ("c49290ef-c068-4cad-8336-e3476f454d4c")
    private static boolean isUnmaskedInAnotherProcessDiagram(BpmnProcess elt) {
        for (AbstractDiagram diagram : elt.getDiagramElement()) {
            if (diagram instanceof BpmnProcessDesignDiagram) {
                if (!Objects.equals(elt, diagram.getOrigin())) {
                    return true;
                }
            } else if (diagram instanceof BpmnProcessCollaborationDiagram) {
                return true;
            }
        }
        
        for (BpmnParticipant participant : elt.getParticipant()) {
            if (isUnmaskedInProcessDiagram(participant)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param elt a BPMN participant
     * @return <code>true</code> if the process is part of at least one {@link BpmnProcessDesignDiagram}
     * (not owned by its referenced process) or {@link BpmnProcessCollaborationDiagram}.
     */
    @objid ("d1fbe9f7-b5fd-4060-beef-561b31505feb")
    private static boolean isUnmaskedInProcessDiagram(BpmnParticipant elt) {
        for (AbstractDiagram diagram : elt.getDiagramElement()) {
            if (diagram instanceof BpmnProcessDesignDiagram) {
                if (!Objects.equals(elt.getProcess(), diagram.getOrigin())) {
                    return true;
                }
            } else if (diagram instanceof BpmnProcessCollaborationDiagram) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param elt a BPMN sub-process
     * @return <code>true</code> if the sub-process is part of at least one {@link BpmnProcessDesignDiagram} or {@link BpmnProcessCollaborationDiagram}.
     */
    @objid ("5d010fef-202f-4d12-931a-d1af6cf69c36")
    private static boolean isUnmaskedInAnyProcessDiagram(BpmnSubProcess elt) {
        for (AbstractDiagram diagram : elt.getDiagramElement()) {
            if (diagram instanceof BpmnProcessDesignDiagram) {
                return true;
            } else if (diagram instanceof BpmnProcessCollaborationDiagram) {
                return true;
            }
        }
        return false;
    }

    @objid ("ea7b17be-3f23-4caa-b591-0ad9ce67d0ef")
    private static void advance(SubProgress mon, String fragName, int i) {
        if (i % 7 == 0) {
            mon.subTask(DiagramEditorBpmn.I18N.getMessage("BpmnMmMigrationContributor.advance", fragName, i));
        }
        mon.worked(1);
        mon.setWorkRemaining(5);
        
        Display currentDisplay = Display.getCurrent();
        if (currentDisplay != null) {
            // We are running in the SWT thread and freezing GUI
            while (currentDisplay.readAndDispatch());
        }
        
    }

    @objid ("8f814e65-c66e-4397-ba8c-37fea88c97b1")
    @SuppressWarnings ("unchecked")
    private <T extends MObject> Collection<T> findByClass(IRepository repo, SmMetamodel mm, Class<T> interf) {
        SmClass mc = mm.getMClass(interf);
        // Ugly cast (c)(r)(tm)
        return (Collection<T>) repo.findByClass(mc, true);
    }

}
