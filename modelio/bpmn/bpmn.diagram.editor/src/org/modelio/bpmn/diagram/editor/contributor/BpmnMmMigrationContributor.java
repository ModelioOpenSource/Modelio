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

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.widgets.Display;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.editor.plugin.DiagramEditorsManager;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.ModelManager;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.fragment.migration.MigrationFailedException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
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
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Adds a BPMN diagram migration to the metamodel migration of model fragments.
 */
@objid ("8e9bdb8c-affd-4c96-b243-4d60927c0fce")
public class BpmnMmMigrationContributor implements IFragmentMigrationContributor {
    @objid ("d73a0a67-3d04-4821-8b2d-b2f289ee0f1e")
    @Override
    public void contributeMigration(IModelioProgress monitor, IMigrationReporter reporter, GProject gproject, IProjectFragment f, MetamodelVersionDescriptor fromVersion, IEclipseContext eclipseContext) throws MigrationFailedException {
        if (!fromVersion.contains(StandardMetamodel.NAME, new Version(0, 0, 0), new Version(2, 1, 0))) {
            // No migration needed
            return;
        }
        
        SubProgress mon = SubProgress.convert(monitor, 6);
        
        ICoreSession coreSession = gproject.getSession();
        IStandardModelFactory factory = MTools.get(coreSession).getModelFactory(IStandardModelFactory.class);
        
        // Force the style manager to load now
        DiagramStyles.getStyleManager().reloadStylesIn(gproject.getProjectFileStructure().getProjectPath().resolve(DiagramStyles.PROJECT_STYLE_SUBDIR));
        
        try (ITransaction t = coreSession.getTransactionSupport().createTransaction("BPMN diagrams migration")) {
            migrateTo2_1_0(reporter, eclipseContext, mon, coreSession, f, factory);
        
            t.commit();
        }
    }

    /**
     * Migration from Modelio 3.6 to Modelio 3.7.
     */
    @objid ("b095972f-9777-4ff9-b137-6bcff398ec16")
    private void migrateTo2_1_0(IMigrationReporter reporter, IEclipseContext eclipseContext, SubProgress mon, ICoreSession coreSession, IProjectFragment f, IStandardModelFactory factory) {
        Collection<MObject> created = new HashSet<>();
        IRepository repo = f.getRepository();
        SmMetamodel mm = coreSession.getMetamodel();
        String fragName = f.getId();
        
        // Create BpmnProcessDesignDiagrams for migration
        for (MObject obj : findByClass(repo, mm, BpmnProcess.class)) {
            BpmnProcess p = (BpmnProcess) obj;
            if (p.getProduct(BpmnProcessDesignDiagram.class).isEmpty() || isInProcessDiagram(p)) {
                if (p.isModifiable()) {
                    BpmnProcessDesignDiagram pdd = factory.createBpmnProcessDesignDiagram();
                    pdd.setName(p.getName());
                    p.getProduct().add(0, pdd);
                    created.add(pdd);
                } else {
                    reporter.getLogger().format("Cannot create BpmnProcessDesignDiagram into read only %s: %s", p, p.getStatus());
                }
            }
        }
        mon.worked(1);
        
        // Create BpmnSubProcessDiagram for migration
        for (BpmnSubProcess sp : findByClass(repo, mm, BpmnSubProcess.class)) {
            if (sp.getProduct(BpmnSubProcessDiagram.class).isEmpty() || isInProcessDiagram(sp)) {
                if (sp.isModifiable()) {
                    BpmnSubProcessDiagram spdd = factory.createBpmnSubProcessDiagram();
                    spdd.setName(sp.getName());
                    sp.getProduct().add(0, spdd);
                    created.add(spdd);
                } else {
                    reporter.getLogger().format("Cannot create BpmnSubProcessDiagram into read only %s: %s", sp, sp.getStatus());
                }
            }
        }
        mon.worked(1);
        
        // Migrate the Gm model
        Display.getDefault().syncExec(() -> {
            int i = 0;
            IModelManager manager = new ModelManager(eclipseContext);
            for (BpmnCollaborationDiagram cd : findByClass(repo, mm, BpmnCollaborationDiagram.class)) {
                if (!created.contains(cd)) {
                    migrateGmModel(cd, manager, reporter);
                    advance(mon, fragName, i++);
                }
            }
        
            for (BpmnProcessDesignDiagram pdd : findByClass(repo, mm, BpmnProcessDesignDiagram.class)) {
                if (!created.contains(pdd)) {
                    migrateGmModel(pdd, manager, reporter);
                    advance(mon, fragName, i++);
                }
            }
        
            for (BpmnSubProcessDiagram spdd : findByClass(repo, mm, BpmnSubProcessDiagram.class)) {
                if (!created.contains(spdd)) {
                    migrateGmModel(spdd, manager, reporter);
                    advance(mon, fragName, i++);
                }
            }
        });
    }

    /**
     * Open the given diagram to force the GM model migration.
     */
    @objid ("3373e65e-9ec6-401f-9d0a-01917e107983")
    private void migrateGmModel(AbstractDiagram diagram, IModelManager modelManager, IMigrationReporter reporter) {
        reporter.getLogger().format("Migrating %s BPMN diagram in %s...%n", diagram, diagram.getOrigin());
        
        try (DiagramHandle dh = DiagramHandle.create(diagram, modelManager, modelManager.getService(IProjectService.class), modelManager.getService(DiagramEditorsManager.class))) {
            dh.save();
            dh.close();
        } catch (LinkageError | RuntimeException e) {
            // Log, report and continue.
            String msg = DiagramEditorBpmn.I18N.getMessage(
                    "BpmnMmMigrationContributor.diagramFailed",
                    diagram.getName(),
                    diagram.getUuid(),
                    e.toString());
        
            reporter.getLogger().println(msg);
            e.printStackTrace(reporter.getLogger());
        
            reporter.getResultReporter().println(msg);
        }
    }

    /**
     * @param elt a BPMN process
     * @return <code>true</code> if the process is part of at least one {@link BpmnProcessDesignDiagram} (that it does not own) or {@link BpmnProcessCollaborationDiagram}.
     */
    @objid ("c49290ef-c068-4cad-8336-e3476f454d4c")
    private boolean isInProcessDiagram(BpmnProcess elt) {
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
            if (isInProcessDiagram(participant)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param elt a BPMN participant
     * @return <code>true</code> if the process is part of at least one {@link BpmnProcessDesignDiagram} (not owned by its referenced process) or {@link BpmnProcessCollaborationDiagram}.
     */
    @objid ("d1fbe9f7-b5fd-4060-beef-561b31505feb")
    private boolean isInProcessDiagram(BpmnParticipant elt) {
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
    private boolean isInProcessDiagram(BpmnSubProcess elt) {
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
    }

    @objid ("8f814e65-c66e-4397-ba8c-37fea88c97b1")
    @SuppressWarnings ("unchecked")
    private <T extends MObject> Collection<T> findByClass(IRepository repo, SmMetamodel mm, Class<T> interf) {
        SmClass mc = mm.getMClass(interf);
        // Ugly cast (c)(r)(tm)
        return (Collection<T>) repo.findByClass(mc, true);
    }

}
