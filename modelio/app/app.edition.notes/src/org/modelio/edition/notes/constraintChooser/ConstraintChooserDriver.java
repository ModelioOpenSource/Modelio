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

package org.modelio.edition.notes.constraintChooser;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.platform.model.ui.dialogs.elementChooser.ElementChooserSorter;
import org.modelio.platform.model.ui.dialogs.elementChooser.IElementChooserDriver;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;

@objid ("26d53d8e-186f-11e2-bc4e-002564c97630")
public class ConstraintChooserDriver implements IElementChooserDriver {
    @objid ("3516236e-186f-11e2-bc4e-002564c97630")
    protected String initialContent;

    @objid ("26d53d8f-186f-11e2-bc4e-002564c97630")
    protected ICoreSession session;

    @objid ("26d53d90-186f-11e2-bc4e-002564c97630")
    protected IMModelServices modelServices;

    @objid ("26d53d91-186f-11e2-bc4e-002564c97630")
    protected Constraint createdConstraint;

    @objid ("26d53d92-186f-11e2-bc4e-002564c97630")
    protected UmlModelElement element;

    @objid ("26d53d94-186f-11e2-bc4e-002564c97630")
    protected ConstraintChooserModel leftModel;

    @objid ("26d53d95-186f-11e2-bc4e-002564c97630")
    protected TreeViewer treeViewer;

    @objid ("26d53d96-186f-11e2-bc4e-002564c97630")
    protected ConstraintChooserSelectionListener constraintSelectionListener = null;

    @objid ("26d53d97-186f-11e2-bc4e-002564c97630")
    protected Object selectedAnnotationAdapter = null;

    @objid ("26d53d98-186f-11e2-bc4e-002564c97630")
    public ConstraintChooserDriver(ICoreSession session, IMModelServices modelServices, Object selectedAnnotationAdapter) {
        this.session = session;
        this.modelServices = modelServices;
        this.selectedAnnotationAdapter = selectedAnnotationAdapter;
        this.initialContent = EditionNotes.I18N.getString("EnterConstraintBody");
        this.constraintSelectionListener = new ConstraintChooserSelectionListener();
    }

    @objid ("26d53d9d-186f-11e2-bc4e-002564c97630")
    @Override
    public StructuredViewer createViewer(Composite parent) {
        this.treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI);
        this.treeViewer.setContentProvider(new ConstraintChooserContentProvider(this.modelServices));
        this.treeViewer.setLabelProvider(new ConstraintChooserLabelProvider());
        this.treeViewer.setSorter(new ElementChooserSorter());
        this.treeViewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
        this.treeViewer.addSelectionChangedListener(this.constraintSelectionListener);
        return this.treeViewer;
    }

    /**
     * Get the constraint created after the user has selected the note type. This method will return null until the user has
     * validated the dialog box.
     * 
     * @return the created note if any.
     */
    @objid ("26d53da3-186f-11e2-bc4e-002564c97630")
    public Constraint getCreatedConstraint() {
        return this.createdConstraint;
    }

    @objid ("26d53da8-186f-11e2-bc4e-002564c97630")
    @Override
    public String getLeftLabel() {
        return EditionNotes.I18N.getString("AddNoteLeftLabel");
    }

    @objid ("26d53dad-186f-11e2-bc4e-002564c97630")
    @Override
    public String getTitle() {
        return EditionNotes.I18N.getString("ConstraintChooserTitle");
    }

    @objid ("26d79ec2-186f-11e2-bc4e-002564c97630")
    @Override
    public void init(Element elementToAddConstraint) {
        if (elementToAddConstraint instanceof UmlModelElement) {
            this.element = (UmlModelElement) elementToAddConstraint;
            this.leftModel = new ConstraintChooserModel(this.modelServices, this.session.getMetamodel());
            this.treeViewer.setInput(this.leftModel);
        }
    }

    @objid ("26d79ec6-186f-11e2-bc4e-002564c97630")
    @Override
    public void performCancel() {
        // Nothing to do, the model was not changed
    }

    @objid ("26d79ec9-186f-11e2-bc4e-002564c97630")
    @Override
    public void performFinish(StructuredViewer viewer, List<Object> selection) {
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction(
                EditionNotes.I18N.getString("AddNote"))) {
            for (Object obj : selection) {
                this.createdConstraint = this.modelServices.getModelFactory().getFactory(IStandardModelFactory.class).createConstraint();
                this.createdConstraint.setName("");
                if (obj instanceof Stereotype) {
                    this.createdConstraint.getExtension().add((Stereotype) obj);
                }
                this.createdConstraint.setBody(this.initialContent);
                this.element.getConstraintDefinition().add(this.createdConstraint);
            }
            transaction.commit();
        }
    }

    @objid ("26d79ed0-186f-11e2-bc4e-002564c97630")
    @Override
    public String getMessage() {
        return EditionNotes.I18N.getString("ConstraintChooserMessage");
    }

    @objid ("26d79ed5-186f-11e2-bc4e-002564c97630")
    @Override
    public String getShellTitle() {
        return EditionNotes.I18N.getString("ConstraintChooserShellTitle");
    }

    @objid ("26d79eda-186f-11e2-bc4e-002564c97630")
    @Override
    protected void finalize() throws Throwable {
        this.treeViewer.removeSelectionChangedListener(this.constraintSelectionListener);
        super.finalize();
    }

    @objid ("26d79edd-186f-11e2-bc4e-002564c97630")
    private class ConstraintChooserSelectionListener implements ISelectionChangedListener {
        @objid ("26d79ede-186f-11e2-bc4e-002564c97630")
        private boolean enable = true;

        @objid ("26d79edf-186f-11e2-bc4e-002564c97630")
        public ConstraintChooserSelectionListener() {
            // Emtpy c'tor
        }

        @objid ("26d79ee1-186f-11e2-bc4e-002564c97630")
        @Override
        public void selectionChanged(SelectionChangedEvent event) {
            if (this.enable) {
                this.enable = false;
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection) {
                    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                    List<Object> selectedAdapters = structuredSelection.toList();
            
                    List<Object> selectedConstraintTypeAdapters = getConstraintTypeAdapters(selectedAdapters);
            
                    ConstraintChooserDriver.this.treeViewer.setSelection(new StructuredSelection(selectedConstraintTypeAdapters));
                }
                this.enable = true;
            }
        }

        @objid ("26d79ee5-186f-11e2-bc4e-002564c97630")
        private List<Object> getConstraintTypeAdapters(List<Object> selectedAdapters) {
            List<Object> adapters = new ArrayList<>();
            
            for (Object obj : selectedAdapters) {
                if (obj instanceof Stereotype) {
                    adapters.add(obj);
                } else if (obj == Constraint.class) {
                    adapters.add(obj);
                }
            }
            return adapters;
        }

    }

}
