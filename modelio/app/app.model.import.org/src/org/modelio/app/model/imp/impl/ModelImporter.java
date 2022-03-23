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
package org.modelio.app.model.imp.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.model.imp.plugin.AppModelImportOrg;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.IllegalModelManipulationException;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Imports many elements from a project to the local project.
 */
@objid ("aa9a3209-13db-441b-8d4f-734320726209")
public class ModelImporter implements IRunnableWithProgress {
    @objid ("7bc82d82-de53-406d-b104-fe63ee22a812")
    private List<MObject> doneCopies;

    @objid ("768b7ef2-cc2b-46c9-8c3a-c717356426e6")
    private ModelImportDataModel importedModel;

    @objid ("e1cfbe0c-63df-4028-a4c0-17e5a24c7e23")
    private IRepository localRepository;

    @objid ("66b7978f-cb11-4277-94a3-416fa72a6819")
    private ICoreSession localSession;

    @objid ("8ea24f32-8787-4514-aad0-874545c54fb8")
    private List<MObject> localTargetElements;

    /**
     * Initialize the model importer
     * @param localSession the model to import elements into.
     * @param selection the Eclipse selection
     * @param importedModel the model elements to import. This list must not contain any {@link AbstractProject}.
     */
    @objid ("8ab86f97-c77b-488c-b773-15625fadb57c")
    public  ModelImporter(ICoreSession localSession, IStructuredSelection selection, ModelImportDataModel importedModel) {
        this.importedModel = importedModel;
        this.localSession = localSession;
        this.localTargetElements = SelectionHelper.toList(selection, MObject.class);
        
        this.localRepository = Optional.ofNullable(SelectionHelper.getFirst(selection, IProjectFragment.class))
                .map(f -> f.getRepository())
                .orElseGet(() -> localSession.getRepositorySupport().getRepository(this.localTargetElements.get(0)));
        
    }

    /**
     * @return the imported elements in the local model.
     */
    @objid ("c4813411-690f-410a-add7-75934c9f05b2")
    public List<MObject> getDoneCopies() {
        return this.doneCopies;
    }

    @objid ("fb8e79cd-7aa1-4291-ad9a-17d9d2b624b8")
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(AppModelImportOrg.I18N.getString("ImportModelDialog.ImportProgressMessage"), IProgressMonitor.UNKNOWN);
        this.doneCopies = new ArrayList<>();
        
        try (ITransaction transaction = this.localSession.getTransactionSupport().createTransaction("Import Model");) {
        
            // Dispatch the elements to import into lists by project
            Map<MObject, List<SmObjectImpl>> importMap = dispatchElementsToImport(this.importedModel.getElementsToImport());
        
            // Import by copy
            List<List<? extends MObject>> toCopy = new ArrayList<>();
            List<MObject> target = new ArrayList<>();
        
            for (Entry<MObject, List<SmObjectImpl>> entry : importMap.entrySet()) {
                target.add(entry.getKey());
                toCopy.add(entry.getValue());
            }
        
            if (!toCopy.isEmpty()) {
                List<List<? extends MObject>> result = MTools.getModelTool().copyElements(toCopy, target);
                for (List<? extends MObject> c : result) {
                    this.doneCopies.addAll(c);
                }
            }
        
            transaction.commit();
        } catch (IllegalModelManipulationException e) {
            // error already reported automatically
            throw new InvocationTargetException(e);
        } catch (RuntimeException e) {
            displayError(e);
            throw new InvocationTargetException(e);
        }
        
        monitor.done();
        
    }

    /**
     * Dispatch the elements to import into lists by project.
     */
    @objid ("b7a84aed-5e77-4f62-8e71-0fa912ec0e2c")
    private Map<MObject, List<SmObjectImpl>> dispatchElementsToImport(List<MObject> elementsToImport) throws IllegalArgumentException {
        Map<MObject, List<SmObjectImpl>> importMap = new HashMap<>();
        
        for (MObject elementToImport : elementsToImport) {
            MObject localTarget = getOrCreateTarget(elementToImport);
        
            importMap
                    .computeIfAbsent(localTarget, t -> new ArrayList<>())
                    .add((SmObjectImpl) elementToImport);
        
            AppModelImportOrg.LOG.info("%s: Will import %s under %s.", getClass().getSimpleName(), elementToImport, localTarget);
        
        }
        return importMap;
    }

    @objid ("6f29a48f-2550-4ab6-8714-fae2fa3ea0dc")
    private void displayError(final Exception e) {
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                MessageDialog.openError(Display.getDefault().getActiveShell(), "Error", e.getLocalizedMessage());
            }
        });
        
    }

    /**
     * Look for a composition child of 'into' with 'src' name and metaclass.
     * If found, return it.
     * In the other case create a new object with same name and metaclass and add it
     * to the default composition dependency of 'into.
     * @param into the element that will own the stub, non null.
     * @param src the element to copy
     * @return the created stub.
     */
    @objid ("9359ca9a-5a9a-4094-85b2-7f1bc7833d81")
    private MObject findSameChildOrCreateStub(MObject into, MObject src) {
        SmClass targetMClass = this.localSession.getMetamodel().getMClass(src.getMClass().getQualifiedName());
        
        for (MObject child : into.getCompositionChildren()) {
            if (child.getMClass() == targetMClass && Objects.equals(child.getName(), src.getName())) {
                return child;
            }
        }
        
        MObject targetEl;
        MTools mTools = MTools.get(this.localSession);
        
        MDependency compoDep = this.localSession.getMetamodel().getMExpert().getDefaultCompositionDep(into.getMClass(), targetMClass);
        if (compoDep == null) {
            throw new NullPointerException(String.format("No composition dependency to attach %s to %s.", targetMClass, into));
        }
        
        if (compoDep.getMaxCardinality() == 1) {
            // Single dependency, won't be able to add a second stub
            List<MObject> children = into.mGet(compoDep);
            if (!children.isEmpty()) {
                // Unable to add a second stub, use the existing element if any as target
                return children.get(0);
            }
        }
        
        targetEl = mTools.getModelFactories().createElement(targetMClass, into, compoDep);
        targetEl.setName(src.getName());
        mTools.getConfigurator().configure(targetEl, Collections.emptyMap());
        
        AppModelImportOrg.LOG.info("%s:  Created %s stub under %s.", getClass().getSimpleName(), targetEl, into);
        return targetEl;
    }

    @objid ("75329d62-0205-4347-a4ca-154daa922b01")
    private AbstractProject getOrCreateLocalProject(AbstractProject project) {
        SmClass mClass = this.localSession.getMetamodel().getMClass(project.getMClass().getQualifiedName());
        for (MObject found : this.localRepository.findByClass(mClass, false)) {
            if (found.isValid()) {
                return (AbstractProject) found;
            }
        }
        
        MObject localProject = this.localSession.getModel().getGenericFactory().create(mClass, this.localRepository);
        localProject.setName(project.getName());
        return (AbstractProject) localProject;
    }

    /**
     * Get or create the element in which the given element must be attached to.
     * @param sourceEl the element we want a composition parent
     * @return the element in which the given one must be placed.
     */
    @objid ("2dd94034-7ed3-44a1-90f5-0f236ca88aae")
    private MObject getOrCreateTarget(MObject sourceEl) {
        if (sourceEl instanceof AbstractProject) {
            throw new IllegalArgumentException();
        }
        
        MObject srcOwner = sourceEl.getCompositionOwner();
        if (srcOwner == null) {
            throw new IllegalArgumentException(String.format("%s is orphan.", sourceEl));
        }
        
        for (MObject potentialTarget : this.localTargetElements) {
            if (potentialTarget.getMClass().getQualifiedName().equals(srcOwner.getMClass().getQualifiedName())) {
                return potentialTarget;
            }
        }
        
        if (srcOwner instanceof AbstractProject) {
            // look for a local project of same type or create one
            return getOrCreateLocalProject((AbstractProject) srcOwner);
        } else {
            // No matching element in selection, step up
            MObject ownerTarget = getOrCreateTarget(srcOwner);
        
            // And create a stub
            MObject target = findSameChildOrCreateStub(ownerTarget, srcOwner);
            return target;
        }
        
    }

}
