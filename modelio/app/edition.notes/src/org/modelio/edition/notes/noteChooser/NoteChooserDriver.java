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

package org.modelio.edition.notes.noteChooser;

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
import org.modelio.core.ui.dialogs.elementChooser.ElementChooserSorter;
import org.modelio.core.ui.dialogs.elementChooser.IElementChooserDriver;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;

@objid ("26e12441-186f-11e2-bc4e-002564c97630")
public class NoteChooserDriver implements IElementChooserDriver {
    @objid ("34fe5606-186f-11e2-bc4e-002564c97630")
    protected String initialContent;

    @objid ("26e12442-186f-11e2-bc4e-002564c97630")
    protected ICoreSession session;

    @objid ("26e12443-186f-11e2-bc4e-002564c97630")
    protected IMModelServices modelService;

    @objid ("26e12444-186f-11e2-bc4e-002564c97630")
    protected Note createdNote;

    @objid ("26e12445-186f-11e2-bc4e-002564c97630")
    protected ModelElement element;

    @objid ("26e12447-186f-11e2-bc4e-002564c97630")
    protected NoteChooserModel leftModel;

    @objid ("26e12448-186f-11e2-bc4e-002564c97630")
    protected TreeViewer leftViewer;

    @objid ("26e12449-186f-11e2-bc4e-002564c97630")
    protected NoteChooserSelectionListener noteSelectionListener = null;

    @objid ("26e1244a-186f-11e2-bc4e-002564c97630")
    public NoteChooserDriver(ICoreSession session, IMModelServices modelService) {
        this.session = session;
        this.modelService = modelService;
        this.createdNote = null;
        this.initialContent = EditionNotes.I18N.getString("EnterNoteBody");
        this.noteSelectionListener = new NoteChooserSelectionListener();
    }

    @objid ("26e1244e-186f-11e2-bc4e-002564c97630")
    @Override
    public StructuredViewer createViewer(Composite parent) {
        this.leftViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI);
        this.leftViewer.setContentProvider(new NoteChooserContentProvider(this.modelService));
        this.leftViewer.setLabelProvider(new NoteChooserLabelProvider());
        this.leftViewer.setSorter(new ElementChooserSorter());
        this.leftViewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
        this.leftViewer.addSelectionChangedListener(this.noteSelectionListener);
        return this.leftViewer;
    }

    /**
     * Get the note created after the user has selected the note type. This method will return null until the user has validated the
     * dialog box.
     * 
     * @return the created note if any.
     */
    @objid ("26e12454-186f-11e2-bc4e-002564c97630")
    public Note getCreatedNote() {
        return this.createdNote;
    }

    @objid ("26e12459-186f-11e2-bc4e-002564c97630")
    @Override
    public String getLeftLabel() {
        return EditionNotes.I18N.getString("AddNoteLeftLabel");
    }

    @objid ("26e1245e-186f-11e2-bc4e-002564c97630")
    @Override
    public String getTitle() {
        return EditionNotes.I18N.getString("NoteChooserTitle");
    }

    @objid ("26e12463-186f-11e2-bc4e-002564c97630")
    @Override
    public void init(Element anElement) {
        if (anElement instanceof ModelElement) {
            this.element = (ModelElement) anElement;
            this.leftModel = new NoteChooserModel(this.element);
            this.leftViewer.setInput(this.leftModel);
            setExpandedState();
        }
    }

    @objid ("26e12467-186f-11e2-bc4e-002564c97630")
    @Override
    public void performCancel() {
        // Nothing to cancel, the model wasn't modified
    }

    @objid ("26e1246a-186f-11e2-bc4e-002564c97630")
    @Override
    public void performFinish(StructuredViewer viewer, List<Object> selection) {
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction(
                EditionNotes.I18N.getString("AddNote"))) {
            IInfrastructureModelFactory factory = this.modelService.getModelFactory().getFactory(IInfrastructureModelFactory.class);
        
            for (Object obj : selection) {
                NoteType noteType = (NoteType) obj;
                this.createdNote = factory.createNote(noteType, element, this.initialContent);
            }
            transaction.commit();
        }
    }

    @objid ("26e12471-186f-11e2-bc4e-002564c97630")
    @Override
    public String getMessage() {
        return EditionNotes.I18N.getString("NoteChooserMessage");
    }

    @objid ("26e38578-186f-11e2-bc4e-002564c97630")
    @Override
    public String getShellTitle() {
        return EditionNotes.I18N.getString("NoteChooserShellTitle");
    }

    @objid ("26e3857d-186f-11e2-bc4e-002564c97630")
    @Override
    protected void finalize() throws Throwable {
        this.leftViewer.removeSelectionChangedListener(this.noteSelectionListener);
        super.finalize();
    }

    @objid ("26e38580-186f-11e2-bc4e-002564c97630")
    private void setExpandedState() {
        this.leftViewer.setAutoExpandLevel(3);
    }

    @objid ("26e38582-186f-11e2-bc4e-002564c97630")
    private class NoteChooserSelectionListener implements ISelectionChangedListener {
        @objid ("26e38583-186f-11e2-bc4e-002564c97630")
        private boolean enable = true;

        @objid ("26e38584-186f-11e2-bc4e-002564c97630")
        @Override
        @SuppressWarnings("unchecked")
        public void selectionChanged(SelectionChangedEvent event) {
            if (this.enable) {
                this.enable = false;
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection) {
                    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                    List<Object> selectedAdapters = structuredSelection.toList();
            
                    List<NoteType> selectedNoteTypeAdapters = getNoteTypeAdapters(selectedAdapters);
            
                    NoteChooserDriver.this.leftViewer.setSelection(new StructuredSelection(selectedNoteTypeAdapters));
                }
                this.enable = true;
            }
        }

        @objid ("26e38588-186f-11e2-bc4e-002564c97630")
        private List<NoteType> getNoteTypeAdapters(List<Object> selectedAdapters) {
            List<NoteType> adapters = new ArrayList<>();
            
            for (Object obj : selectedAdapters) {
                if (obj instanceof NoteType) {
                    adapters.add((NoteType) obj);
                }
            }
            return adapters;
        }

        @objid ("26e38591-186f-11e2-bc4e-002564c97630")
        NoteChooserSelectionListener() {
        }

    }

}
