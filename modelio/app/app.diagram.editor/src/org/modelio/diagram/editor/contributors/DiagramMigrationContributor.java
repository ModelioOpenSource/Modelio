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
package org.modelio.diagram.editor.contributors;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.dg.IDiagramDrawingsLayer;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.ModelManager;
import org.modelio.gproject.MigrationFailedException;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGProject;
import org.modelio.metamodel.InfrastructureMetamodel;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.project.services.IFragmentMigrationContributor;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.mm.IMigrationReporter;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Adds a diagram migration to the metamodel migration of model fragments.
 */
@objid ("dc4c0dd5-bf5a-4d3a-b1da-d72bb5dff20b")
public class DiagramMigrationContributor implements IFragmentMigrationContributor {
    @objid ("2baad223-20a4-412a-9590-4b86c202bba2")
    private static final Version VERSION = new Version(5,4,0);

    @objid ("c3ece170-37e3-4b5d-b6f5-176d3645f015")
    @Override
    public void contributeMigration(IModelioProgress monitor, IMigrationReporter reporter, IGProject gproject, IGModelFragment f, MetamodelVersionDescriptor fromVersion, IEclipseContext eclipseContext) throws MigrationFailedException {
        if (!fromVersion.contains(InfrastructureMetamodel.NAME, new Version(0, 0, 0), new Version(2, 1, 04))) {
            // No migration needed
            return;
        }
        ICoreSession coreSession = gproject.getSession();
        
        migrateTo2_1_04(reporter, eclipseContext, monitor, coreSession, f);
        
    }

    @objid ("bd45852f-5721-4f58-882b-b0abda4a93ca")
    @Override
    public Version getTargetModelioVersion() {
        return VERSION;
    }

    /**
     * Migration from Modelio 5.3.1 to Modelio 5.4.0
     */
    @objid ("2ec3f935-e005-4b57-9f3d-5147ba96a5d7")
    @SuppressWarnings ("resource")
    private void migrateTo2_1_04(IMigrationReporter reporter, IEclipseContext eclipseContext, IModelioProgress monitor, ICoreSession coreSession, IGModelFragment f) {
        // Find, open and save all diagrams to populate new JsStructure property
        IRepository repo = f.getRepository();
        SmMetamodel mm = coreSession.getMetamodel();
        Display display = Display.getDefault();
        display.syncExec(() -> {
            Collection<AbstractDiagram> allDiagrams = findByClass(repo, mm, AbstractDiagram.class);
            int nbDiags = allDiagrams.size();
            int i = 0;
            SubProgress mon = SubProgress.convert(monitor, nbDiags * 2);
            for (AbstractDiagram diagram : allDiagrams) {
                mon.subTask(DiagramEditor.I18N.getMessage("DiagramMigrationContributor.diagram",  ++i , nbDiags, VERSION));
        
                DiagramEditor.LOG.debug("DiagramMigrationContributor: Migrate %s to Modelio %s", diagram, VERSION) ;
                try (ITransaction t = coreSession.getTransactionSupport().createTransaction(
                        String.format("DiagramMigrationContributor: Migrate %s to Modelio %s", diagram, VERSION))) {
        
                    // Spare memory once commit() done
                    t.disableUndo();
        
                    IModelManager manager = new ModelManager(eclipseContext);
                    migrateGmModel(diagram, manager, reporter);
                    mon.worked(1);
        
                    while (display.readAndDispatch()); // We are running in the SWT thread and freezing GUI
        
                    t.commit();
                    mon.worked(1);
                } catch (RuntimeException e) {
                    reporter.getLogger().format("ERROR Migrating %s diagram : %s%n", diagram, e.getMessage());
                    e.printStackTrace(reporter.getLogger());
                }
        
                while (display.readAndDispatch()); // We are running in the SWT thread and freezing GUI
            }
        
        });
        
    }

    @objid ("a2e11bff-806a-419f-b45f-4969efdb3fed")
    private void migrateGmModel(AbstractDiagram diagram, IModelManager modelManager, IMigrationReporter reporter) {
        // Create a temporary drawing to force diagram UiData update
        reporter.getLogger().format("Migrating %s diagram in %s...%n", diagram, diagram.getOrigin());
        try (IDiagramHandle handle = Modelio.getInstance().getDiagramService().getDiagramHandle(diagram)) {
            handle.setBatchMode(true);
            handle.refreshDynamicDecoration();
            handle.getCreationFactory().createDrawingNote(handle.getDiagramNode().getDrawingsLayer(IDiagramDrawingsLayer.TOP), "Migration", "Migration", 0, 0, 100, 50);
            handle.save();
        }
        
        try (IDiagramHandle handle = Modelio.getInstance().getDiagramService().getDiagramHandle(diagram)) {
            handle.setBatchMode(true);
            handle.mask(handle.getDrawingGraphic("Migration"));
            handle.save();
        }
        
    }

    @objid ("3802ec1b-78d3-4d9a-bd60-a5fe6918c245")
    @SuppressWarnings ("unchecked")
    private static <T extends MObject> Collection<T> findByClass(IRepository repo, SmMetamodel mm, Class<T> interf) {
        SmClass mc = mm.getMClass(interf);
        return (Collection<T>) repo.findByClass(mc, true);
    }

}
