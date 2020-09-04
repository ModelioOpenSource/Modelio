/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.edition.notes.panelprovider;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.core.ui.dialogs.elementChooser.ElementChooserDlg;
import org.modelio.core.ui.swt.copy.PasteElementObject.PasteType;
import org.modelio.core.ui.swt.copy.PasteElementObject;
import org.modelio.core.ui.swt.copy.PasteElementTransfer;
import org.modelio.core.ui.swt.copy.TransferItem;
import org.modelio.edition.notes.constraintChooser.ConstraintChooserDriver;
import org.modelio.edition.notes.panelprovider.helpers.AddConstraintHelper;
import org.modelio.edition.notes.panelprovider.helpers.AddNoteHelper;
import org.modelio.edition.notes.panelprovider.helpers.CleanNoteContentHelper;
import org.modelio.edition.notes.panelprovider.helpers.MoveDownHelper;
import org.modelio.edition.notes.panelprovider.helpers.MoveUpHelper;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.editors.richnote.helper.AddEmbeddedDocumentHelper;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.script.engine.core.engine.IScriptRunner;
import org.modelio.script.view.ScriptView;
import org.modelio.script.view.ScriptViewSelectionGetter;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Controller of the annotations panel.
 */
@objid ("c6ce5778-e360-44b7-8ae6-e9538ea4c99f")
public class NotesPanelController {
    @objid ("5822cfbd-65ab-40bb-9c78-03e86c564ff9")
    private boolean isAutoLayout;

    @objid ("d18af8cb-7225-4954-9aa2-139d7d0ebb5d")
    private IActivationService activationService;

    /**
     * The element whose notes are being displayed/edited
     */
    @objid ("d6bdad35-c718-4be3-a05a-6b3eb7e8add9")
    private ModelElement currentInput;

    @objid ("0b0cafb2-7cda-4be2-a248-5cb7800f2e17")
    private ModelElement currentSelection;

    @objid ("e25ce4d2-7b3c-4f91-9fab-1b4e9809f9b2")
    private IEclipseContext eclipseContext;

    @objid ("92bd4f31-1163-4fbe-a7e6-10f13d1b2340")
    private IMModelServices modelServices;

    @objid ("5e9d40c8-a46c-4700-8893-09e068daf384")
    private ICoreSession session;

// // private final EContextService contextService;
    @objid ("65a57137-17bb-4fe8-8339-8ad5b161bab6")
    private NotesPanelView view;

    /**
     * Constructor.
     */
    @objid ("bf0c5ca6-f12e-4cf8-98ef-ef3d1dcf86e7")
    public NotesPanelController(IEclipseContext context) {
        this.eclipseContext = context;
    }

    @objid ("02687762-4ee3-45dc-aaa9-69459bd66e27")
    public boolean canAddConstraint() {
        return AddConstraintHelper.canExecute(this.currentInput, this.view.getSelectedNotes());
    }

    @objid ("7c37cdf0-08be-4000-bf65-215e7cd52be5")
    public boolean canAddNote() {
        return AddNoteHelper.canExecute(this.currentInput);
    }

    @objid ("48b2e663-2b10-4b60-a5f4-d9b03f40d051")
    public boolean canCleanContent() {
        if (this.currentInput == null || !this.currentInput.isModifiable()) {
            return false;
        }
        
        final List<ModelElement> selectedItems = this.view.getSelectedNotes();
        return selectedItems.size() == 1 && selectedItems.get(0).isModifiable();
    }

    @objid ("f2a653fd-fca6-4564-859e-ea32817be125")
    public boolean canCopy() {
        // Sanity checks
        if (this.session == null) {
            return false;
        }
        
        // Check focus
        if (!this.view.getTreeViewer().getControl().isFocusControl()) {
            return false;
        }
        
        // Check selected element
        if (this.currentInput == null || !this.currentInput.getStatus().isModifiable()) {
            return false;
        }
        
        // Check selected notes/constraints/documents
        List<ModelElement> selectedItems = this.view.getSelectedNotes();
        for (ModelElement me : selectedItems) {
            if (!(me instanceof Note) && !(me instanceof Constraint) && (!(me instanceof Document))) {
                return false;
            }
        }
        return (selectedItems.size() > 0);
    }

    @objid ("64da54db-ea03-46a1-970f-07c24a213871")
    public boolean canCut() {
        // Sanity checks
        if (this.session == null) {
            return false;
        }
        
        // Check focus
        if (!this.view.getTreeViewer().getControl().isFocusControl()) {
            return false;
        }
        
        // Check selected element
        if (this.currentInput == null || !this.currentInput.getStatus().isModifiable()) {
            return false;
        }
        
        // Check selected notes/constraints/documents
        List<ModelElement> selectedItems = this.view.getSelectedNotes();
        for (ModelElement me : selectedItems) {
            if (!(me instanceof Note) && !(me instanceof Constraint) && (!(me instanceof Document))) {
                return false;
            }
        }
        return (selectedItems.size() > 0);
    }

    @objid ("1333ed81-59c3-41b4-b2c6-ce596b159cfd")
    public boolean canHtmlConvert() {
        final List<ModelElement> noteItems = this.view.getSelectedNotes();
        if (noteItems.size() != 1) {
            return false;
        } else {
            if (noteItems.get(0) instanceof Note) {
                final Note note = (Note) noteItems.get(0);
                return note.isModifiable() && note.getModel().getMimeType().contains("html");
            } else {
                return false;
            }
        }
    }

    /**
     * Check that the currently selected note can be executed as a Jython script. conditions are:
     * <ul>
     * <li>selected note unique and non null</li>
     * <li>mime type python</li>
     * <li>application context window</li>
     * </ul>
     * @return
     */
    @objid ("04ab89a5-b17a-417d-a860-6892e14de162")
    public boolean canJyExec() {
        final List<ModelElement> noteItems = this.view.getSelectedNotes();
        if (noteItems.size() != 1) {
            return false;
        } else if (noteItems.get(0) instanceof Note) {
            MWindow window = this.eclipseContext.get(MWindow.class);
            final Note note = (Note) noteItems.get(0);
            return window != null && isJythonNote(note);
        } else {
            return false;
        }
    }

    @objid ("582b3276-c5b0-4f98-89db-093afe12d0b1")
    public boolean canMoveDown() {
        return MoveDownHelper.canExecute(this.currentInput, this.view.getSelectedNotes());
    }

    @objid ("03b46f3c-e5e4-4527-9c0b-38fd27f4851a")
    public boolean canMoveUp() {
        return MoveUpHelper.canExecute(this.currentInput, this.view.getSelectedNotes());
    }

    @objid ("8ac8ddcd-57ed-40c9-848c-0e9ae54f813d")
    public boolean canPaste() {
        // Sanity checks
        if (this.session == null) {
            return false;
        }
        
        // Check focus
        if (!this.view.getTreeViewer().getControl().isFocusControl()) {
            return false;
        }
        
        // Check selected element
        if (this.currentInput == null || !this.currentInput.getStatus().isModifiable()) {
            return false;
        }
        
        Clipboard clipboard = new Clipboard(this.view.getComposite().getDisplay());
        final PasteElementObject pastedObject = (PasteElementObject) clipboard
                .getContents(PasteElementTransfer.getInstance());
        // There is no data corresponding to PasteElementTransfer
        if (pastedObject == null) {
            return false;
        }
        
        MExpert expert = this.session.getMetamodel().getMExpert();
        
        final List<TransferItem> items = pastedObject.getTransferedItems();
        List<MObject> pastedElements = new ArrayList<>();
        for (TransferItem item : items) {
            MRef transferedElementRef = item.getTransferedElementRef();
            MObject transferedElement = this.session.getModel().findByRef(transferedElementRef);
            pastedElements.add(transferedElement);
        }
        
        for (MObject pasted : pastedElements) {
            switch (pastedObject.getPasteType()) {
            case CUT:
                if (!MTools.getAuthTool().canAddTo(pasted, this.currentInput)) {
                    return false;
                }
                if (!expert.canCompose(this.currentInput, pasted, null)) {
                    return false;
                }
                break;
            case COPY:
            default:
                if (!MTools.getAuthTool().canAdd(this.currentInput, pasted.getMClass())) {
                    return false;
                }
                if (!expert.canCompose(this.currentInput, pasted, null)) {
                    return false;
                }
                break;
            }
        }
        return true;
    }

    @objid ("c7df9aa7-c04d-49e8-86c4-2e3065bd6f36")
    public boolean canRemoveAnnotation() {
        if (this.currentInput == null || !this.currentInput.isModifiable()) {
            return false;
        }
        final List<ModelElement> selectedItems = this.view.getSelectedNotes();
        for (final ModelElement e : selectedItems) {
            if (!e.isModifiable()) {
                return false;
            }
        }
        return selectedItems.size() > 0;
    }

    /**
     * Creates the views in the given composite
     * 
     * @param parent a SWT Composite
     * @return the created panel view
     */
    @objid ("1382bc96-7947-4780-b1e5-18333c90ea89")
    public NotesPanelView createView(Composite parent) {
        this.view = new NotesPanelView(this);
        this.view.createContents(parent);
        return this.view;
    }

    /**
     * Disposes the panel controler and SWT widgets.
     */
    @objid ("90e169aa-6dcf-4fe2-be55-636248064d35")
    public void dispose() {
        this.view.dispose();
        this.currentInput = null;
        this.currentSelection = null;
        this.view = null;
    }

    /**
     * @return the activation service
     */
    @objid ("f7d4c954-c3aa-4cb8-ad93-58b3baea5949")
    public IActivationService getActivationService() {
        return this.activationService;
    }

    @objid ("d43361df-877d-4b62-af58-dcdec22244d0")
    public EContextService getContextService() {
        return this.eclipseContext != null ? this.eclipseContext.get(EContextService.class) : null;
    }

    @objid ("5cecdcce-b65a-49e9-8bcc-a89f43c4dc68")
    public ModelElement getInput() {
        return this.currentInput;
    }

    @objid ("f7f53a56-e10e-4f5e-b408-ef88e9eb4dbf")
    public boolean isAutoLayout() {
        return this.isAutoLayout;
    }

    @objid ("8dbcf58a-aaaa-4869-8a3c-4c075de02519")
    public boolean isHorizontalLayout() {
        return !this.isAutoLayout && this.view.getLayoutOrientation() == SWT.HORIZONTAL;
    }

    @objid ("a95781f4-dc44-4695-ab93-abb76c3734d9")
    public boolean isVerticalLayout() {
        return !this.isAutoLayout && this.view.getLayoutOrientation() == SWT.VERTICAL;
    }

    /**
     * Called when the end-user clicks on the 'add constraint' button.
     */
    @objid ("9008cd49-44b1-499b-9033-df19df8e2dab")
    public void onAddConstraint() {
        final Shell parentShell = this.view.getComposite().getShell();
        final ConstraintChooserDriver driver = new ConstraintChooserDriver(this.session, this.modelServices, null);
        final ElementChooserDlg dialog = new ElementChooserDlg(parentShell, driver, this.currentInput);
        // Don't return from open() until window closes
        dialog.setBlockOnOpen(true);
        // Open the main window
        dialog.open();
        
        final Constraint constraint = driver.getCreatedConstraint();
        setInputs(this.currentInput, constraint);
    }

    /**
     * Creates a description note.
     */
    @objid ("0102e1de-4864-45ce-ba5d-f61a1053aa6f")
    public void onAddDescription() {
        final AddNoteHelper handler = new AddNoteHelper(this.session, this.modelServices);
        final Shell parentShell = this.view.getComposite().getShell();
        
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction("Move annotation up")) {
            final Note note = handler.execute(parentShell, this.currentInput, "ModelerModule", ModelElement.MQNAME, "description");
            if (note != null) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
            setInputs(this.currentInput, note);
        } catch (final Exception e) {
            // Should catch InvalidModelManipulationException to display a popup
            // box, but it
            // is not a RuntimeException.
            NotesPanelController.reportException(e);
        }
    }

    /**
     * Calls the create note dialog.
     */
    @objid ("c5d70a26-135f-4b97-95cd-16f7f8b5c457")
    public void onAddNote() {
        final AddNoteHelper handler = new AddNoteHelper(this.session, this.modelServices);
        final Shell parentShell = this.view.getComposite().getShell();
        
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction("Move annotation up")) {
            final Note note = handler.execute(parentShell, this.currentInput, null, null, null);
            if (note != null) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
            setInputs(this.currentInput, note);
        } catch (final Exception e) {
            // Should catch InvalidModelManipulationException to display a popup
            // box, but it
            // is not a RuntimeException.
            NotesPanelController.reportException(e);
        }
    }

    @objid ("b1b16c90-a49a-4a19-9ae2-7bbac4e55419")
    public void onAutomaticLayout() {
        this.view.enableAutoLayout();
        this.isAutoLayout = true;
    }

    /**
     * Clear the note content
     */
    @objid ("9ac30f2a-813c-470a-9f90-64928f96c167")
    public void onCleanContent() {
        final CleanNoteContentHelper handler = new CleanNoteContentHelper();
        final List<ModelElement> selectedNotes = this.view.getSelectedNotes();
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction("Clear note content")) {
            if (handler.execute(this.currentInput, selectedNotes)) {
                transaction.commit();
                setInputs(this.currentInput, selectedNotes.get(0));
            } else {
                transaction.rollback();
            }
        
        } catch (final Exception e) {
            EditionNotes.LOG.error(EditionNotes.PLUGIN_ID, e);
        }
    }

    @objid ("7fefaad1-03c3-4ab5-90d9-e77b3228443c")
    public void onCopy() {
        List<ModelElement> selectedElements = this.view.getSelectedNotes();
        
        PasteElementObject toCopy = new PasteElementObject(PasteType.COPY);
        
        for (MObject element : selectedElements) {
            toCopy.addTransferedItems(new TransferItem(element, element.getCompositionOwner()));
        }
        
        Clipboard clipboard = new Clipboard(this.view.getComposite().getDisplay());
        clipboard.setContents(new Object[] { toCopy }, new Transfer[] { PasteElementTransfer.getInstance() });
    }

    @objid ("4681f3be-8886-45bc-8d3e-2e280a8c779b")
    public void onCut() {
        List<ModelElement> selectedElements = this.view.getSelectedNotes();
        PasteElementObject toCopy = new PasteElementObject(PasteType.CUT);
        for (MObject element : selectedElements) {
            toCopy.addTransferedItems(new TransferItem(element, element.getCompositionOwner()));
        }
        Clipboard clipboard = new Clipboard(this.view.getComposite().getDisplay());
        clipboard.setContents(new Object[] { toCopy }, new Transfer[] { PasteElementTransfer.getInstance() });
    }

    @objid ("2dc8ac0f-8a13-491a-91c6-3e30f5a586a6")
    public void onHorizontalLayout() {
        this.view.setHorizontalLayout();
        this.view.disableAutoLayout();
        this.isAutoLayout = false;
    }

    @objid ("2f0cd7b0-272e-40cb-8eff-3eba28443499")
    public void onHtmlConvert() {
        final List<ModelElement> noteItems = this.view.getSelectedNotes();
        
        if (noteItems.get(0) instanceof Note) {
            final Note note = (Note) noteItems.get(0);
            try (ITransaction t = this.session.getTransactionSupport().createTransaction("Switch Html/Text type")) {
                if (isHtmlNote(note)) {
                    note.setMimeType("text/plain");
                } else {
                    note.setMimeType("text/html");
                }
                t.commit();
            }
        }
    }

    /**
     * Called when the end-user double-clicks a Note or a Constraint in the tree
     * 
     * @param selection the double clicked selection
     */
    @objid ("6366391a-5686-4c22-a3fa-75e965dc0393")
    public void onItemDoubleClick(ISelection selection) {
        // System.out.println("NotesPanelController.onDoubleClick()");
        if (selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            final Object object = structuredSelection.getFirstElement();
            if (object != null && object instanceof MObject) {
                if (this.activationService != null) {
                    this.activationService.activateMObject((MObject) object);
                }
            }
        }
    }

    /**
     * Called when the end-user selects a Note or a Constraint in the tree
     * 
     * @param selection the new selection
     */
    @objid ("32b038c3-5263-42ae-b6df-d71135247543")
    public void onItemSelectionChange(ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            final Object object = structuredSelection.getFirstElement();
            if (object != null && object instanceof ModelElement) {
        
                if (object.equals(this.currentSelection)) {
                    // do nothing
                } else {
                    this.currentSelection = (ModelElement) object;
                    setInputs(this.currentInput, (ModelElement) object);
                }
        
            } else {
                setInputs(this.currentInput, null);
            }
        }
    }

    /**
     * Executes the currently selected note as a Jython script in the Modelio script view.
     */
    @objid ("d83c5b6f-2c9e-4546-a96e-9dfee7dafab2")
    public void onJyExec() {
        // Get the selected note
        MObject o = this.view.getSelectedNotes().get(0);
        if (!(o instanceof Note) || !isJythonNote((Note) o)) {
            return;
        }
        
        Note note = (Note) o;
        
        // Get the script from the note
        String script = note.getContent();
        
        // Get the script view
        
        ScriptView scriptView = getScriptView();
        if (scriptView == null) {
            EditionNotes.LOG.error("Execute Jython note: could not find script view.");
            return;
        }
        
        // Get the script runner from the script view
        IScriptRunner scriptRunner = scriptView.getScriptRunner();
        if (scriptRunner == null) {
            EditionNotes.LOG.error("Execute Jython note: could not get a Jython runner from script view.");
            return;
        }
        
        // JyExec
        // Execute code
        try (ITransaction t = CoreSession.getSession(note).getTransactionSupport().createTransaction("Execute Jython note")) {
            ScriptViewSelectionGetter selectionGetter = scriptView.getSelectionGetter();
            scriptRunner.runScript(script, selectionGetter.getSelection(), selectionGetter.getSelectedElements());
            t.commit();
        }
    }

    /**
     * Move down
     */
    @objid ("ce1e55d4-9f70-4fa7-ab88-c9a16bc417de")
    public void onMoveDown() {
        final MoveDownHelper handler = new MoveDownHelper();
        final List<ModelElement> selectedNotes = this.view.getSelectedNotes();
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction("Move annotation up")) {
            if (handler.execute(this.currentInput, selectedNotes)) {
                transaction.commit();
                setInputs(this.currentInput, selectedNotes.get(0));
            } else {
                transaction.rollback();
            }
        } catch (final Exception e) {
            // Should catch InvalidModelManipulationException to display a popup
            // box, but it
            // is not a RuntimeException.
            NotesPanelController.reportException(e);
        }
    }

    /**
     * Move up
     */
    @objid ("d6b21abc-5bba-4cb8-b592-144587627406")
    public void onMoveUp() {
        final MoveUpHelper handler = new MoveUpHelper();
        final List<ModelElement> selectedNotes = this.view.getSelectedNotes();
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction("Move annotation up")) {
            if (handler.execute(this.currentInput, selectedNotes)) {
                transaction.commit();
                setInputs(this.currentInput, selectedNotes.get(0));
            } else {
                transaction.rollback();
            }
        } catch (final Exception e) {
            // Should catch InvalidModelManipulationException to display a popup
            // box, but it
            // is not a RuntimeException.
            NotesPanelController.reportException(e);
        }
    }

    @objid ("dae10c4c-ede8-4c79-b87f-812ea45ccb4b")
    public void onPaste() {
        Clipboard clipboard = new Clipboard(this.view.getComposite().getDisplay());
        final PasteElementObject pastedObject = (PasteElementObject) clipboard
                .getContents(PasteElementTransfer.getInstance());
        
        final List<TransferItem> items = pastedObject.getTransferedItems();
        List<MObject> pastedElements = new ArrayList<>();
        for (TransferItem item : items) {
            MRef transferedElementRef = item.getTransferedElementRef();
            MObject transferedElement = this.session.getModel().findByRef(transferedElementRef);
            pastedElements.add(transferedElement);
        }
        
        MExpert expert = this.session.getMetamodel().getMExpert();
        for (MObject element : pastedElements) {
            if (!expert.canCompose(this.currentInput, element, null)) {
                return;
            }
        }
        
        if (pastedObject.getPasteType() == PasteType.COPY) {
            try (ITransaction transaction = this.session.getTransactionSupport().createTransaction("Paste")) {
                List<MObject> copyResult = new ArrayList<>();
        
                if (pastedElements.size() > 0) {
                    copyResult.addAll(MTools.getModelTool().copyElements(pastedElements, this.currentInput));
                }
                transaction.commit();
                setInputs(this.currentInput, (ModelElement) copyResult.get(0));
            } catch (Exception e) {
                // Should catch InvalidModelManipulationException to display a
                // popup box, but it
                // is not a RuntimeException.
                NotesPanelController.reportException(e);
            }
        } else if (pastedObject.getPasteType() == PasteType.CUT) {
            // cannot cut/paste an element onto itself or a child
            for (MObject element : pastedElements) {
                if (element.equals(this.currentInput) || isParentOf(element, this.currentInput)) {
                    return;
                }
            }
        
            try (ITransaction transaction = this.session.getTransactionSupport().createTransaction("Cut")) {
                for (TransferItem item : items) {
                    MRef oldParentRef = item.getOldParentRef();
                    MObject oldParent = this.session.getModel().findByRef(oldParentRef);
                    if (!pastedElements.isEmpty()) {
                        MTools.getModelTool().moveElements(pastedElements, this.currentInput, oldParent);
                    }
                }
        
                transaction.commit();
                setInputs(this.currentInput, (ModelElement) pastedElements.get(0));
        
                // Keep the elements in the clipboard, but as a copy
                pastedObject.setPasteType(PasteType.COPY);
                clipboard.setContents(new Object[] { pastedObject },
                        new Transfer[] { PasteElementTransfer.getInstance() });
            } catch (Exception e) {
                NotesPanelController.reportException(e);
            }
        }
    }

    /**
     * Delete an annotation
     */
    @objid ("efab7576-34fd-47c5-bbed-8040689cf738")
    public void onRemoveAnnotation() {
        final List<ModelElement> noteItems = this.view.getSelectedNotes();
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction("NoteDeletion")) {
            for (final ModelElement noteItem : noteItems) {
                noteItem.delete();
            }
            transaction.commit();
        } catch (final Exception e) {
            EditionNotes.LOG.error(EditionNotes.PLUGIN_ID, e);
        }
        setInputs(this.currentInput, null);
    }

    @objid ("7992016b-84ab-4482-92d1-d08fabbe70a7")
    public void onVerticalLayout() {
        this.view.setVerticalLayout();
        this.view.disableAutoLayout();
        this.isAutoLayout = false;
    }

    /**
     * Set the activation service.
     * 
     * @param activationService the activation service.
     */
    @objid ("0a377ddb-5843-4e2c-bcd4-62fcc9b153a2")
    public void setActivationService(IActivationService activationService) {
        this.activationService = activationService;
    }

    /**
     * The NotesPanelComposite has to be driven for two inputs:
     * <li>the edited element which is the element whose notes and constraints are currently being edited,
     * <li>and the current selection which is the note or constraint currently being displayed.
     * 
     * @param elt the note item whose contents is to be displayed in the content panel. May be null Refresh the whole notes view. Clean up the content of the currently selected note Set the note item object (Note or Constraint) currently displayed in the
     * content panel.
     * @param select the currently selected notes/constraints in the view
     */
    @objid ("7785731d-8ccd-4279-b9f9-cd782d218628")
    public void setInputs(ModelElement elt, ModelElement select) {
        // Check for deleted elements
        if (this.currentInput != null && !this.currentInput.isValid()) {
            this.currentInput = null;
            this.currentSelection = null;
        } else {
            if (this.currentSelection != null && !this.currentSelection.isValid()) {
                this.currentSelection = null;
            }
        }
        
        // If the current element is not null and the passed elt is the same as
        // the current element
        // we are only refreshing the view (mostly because of a model or status
        // change event
        // We'd better try to preserve the selected item for end-user's comfort
        if (this.currentInput != null && this.currentInput.isValid() && this.currentInput.equals(elt)) {
            this.currentSelection = select;
            this.view.setSelected(select);
            refreshInputs();
            return;
        }
        
        // If we were passed a null or invalid element we cannot go further
        if (elt == null || !elt.isValid()) {
            this.currentInput = null;
            this.currentSelection = null;
            if (this.view != null) {
                this.view.setInput(null);
                this.view.setSelected(null);
            }
            this.session = null;
            this.modelServices = null;
            return;
        } else {
        
            CoreSession newSess = CoreSession.getSession(elt);
            if (newSess != this.session) {
                this.session = newSess;
                GProject newProject = GProject.getProject(elt);
                if (newProject != null) {
                    this.modelServices = new MModelServices(newProject.getSession());
                } else {
                    this.modelServices = null;
                }
            }
        
            this.currentInput = elt;
            this.view.setInput(elt);
        
            // Drive the current selection
            if (this.currentSelection != null && this.currentSelection.equals(select)) {
                // Do nothing to avoid selecting already selected item
            } else {
                this.currentSelection = select;
                this.view.setSelected(select);
            }
        }
    }

    @objid ("0cd4271f-ce9b-46dc-bb1c-7c26d91806f0")
    static void reportException(Exception e) {
        // Show an error box
        final String title = EditionNotes.I18N.getMessage("CannotPasteClipboard");
        MessageDialog.openError(null, title, e.getLocalizedMessage());
        EditionNotes.LOG.error(e);
    }

    /**
     * Find the Jython script view of the application, possibly activating it if necessary.
     * @return
     */
    @objid ("e9a32459-69a5-4064-ac6f-0d18affac463")
    private ScriptView getScriptView() {
        EPartService partService = this.eclipseContext.get(EPartService.class);
        MWindow window = this.eclipseContext.get(MWindow.class);
        
        if (window == null) {
            return null;
        }
        
        MPart part = partService.findPart(ScriptView.PARTID);
        // If the part is not found try to browse the shared elements to find
        // the script part
        if ((part == null)) {
            for (MUIElement x : window.getSharedElements()) {
                if (x.getElementId().equals(ScriptView.PARTID)) {
                    part = (MPart) x;
                    break;
                }
            }
        }
        if (part != null) {
            if (part.getContext() == null) {
                // Create script view if it is not created yet in order to run
                // the script
                if (!partService.isPartVisible(part)) {
                    partService.showPart(part, PartState.CREATE);
                }
            } else {
                // Force the activation of the script view
                partService.showPart(part, PartState.ACTIVATE);
            }
        
            // Activate the part to give it focus
            partService.activate(part);
        
            return (ScriptView) part.getObject();
        }
        return null;
    }

    @objid ("46708c80-f6cb-48a9-8999-ea2fc2b867b2")
    private boolean isHtmlNote(Note note) {
        final String mimeType = (note.getMimeType() != null) && !note.getMimeType().isEmpty() ? note.getMimeType()
                : note.getModel().getMimeType();
        return mimeType.contains("html");
    }

    @objid ("9e69c5dd-53ef-4557-a698-e621cb812a5d")
    private boolean isJythonNote(Note note) {
        final String mimeType = (note.getMimeType() != null) && !note.getMimeType().isEmpty() ? note.getMimeType()
                : note.getModel().getMimeType();
        return mimeType.contains("jython");
    }

    /**
     * Returns true if parentCandidate is in the composition tree of element. (recursive search).
     * @param parentCandidate
     * 
     * @param element @return
     */
    @objid ("5c1fb019-2e06-43ad-a44e-52d6cf9df239")
    private boolean isParentOf(MObject parentCandidate, MObject element) {
        MObject parent = element.getCompositionOwner();
        
        if (parent == null) {
            return false;
        }
        
        if (parentCandidate.equals(parent)) {
            return true;
        }
        return isParentOf(parentCandidate, parent);
    }

    @objid ("5e158c5f-e99d-4893-8a06-1cb8414322b3")
    private void refreshInputs() {
        this.view.setInput(this.currentInput);
        this.view.setSelected(this.currentSelection);
    }

    /**
     * Creates a rich note
     */
    @objid ("e60633c9-31fa-42f8-b466-b5c0aa681f84")
    public void onAddRichNote() {
        final Shell parentShell = this.view.getComposite().getShell();
        
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction("AddDocument")) {
            Document doc = AddEmbeddedDocumentHelper.execute(parentShell, this.currentInput, this.modelServices);
            if (doc != null) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
            setInputs(this.currentInput, doc);
        } catch (final Exception e) {
            // Should catch InvalidModelManipulationException to display a popup
            // box, but it
            // is not a RuntimeException.
            reportException(e);
        }
    }

    @objid ("b8a8a38a-8664-4e38-9eb6-8fa9134edc6f")
    public void onNameChanged(Object editedElement, String value) {
        if (editedElement instanceof ModelElement) {
            ModelElement modelElement = (ModelElement) editedElement;
            if (!modelElement.getName().equals(value)) {
                try (ITransaction t = CoreSession.getSession(modelElement).getTransactionSupport().createTransaction("Rename a " + modelElement.getMClass().getName())) {
                    modelElement.setName(value);
                    t.commit();
                }
            }
        }
    }

    /**
     * This listener is called when the model is modified.<br>
     * Its responsibility is to refresh the NotesView for the current element.
     */
    @objid ("a30e4809-ca22-4945-9133-8f01d0f1f46d")
    private static class ModelChangeListener implements IModelChangeListener, IStatusChangeListener {
        @objid ("eb5de178-29ce-4a92-bb78-1ca68443b822")
        protected NotesPanelProvider notesView;

        @objid ("c551ab51-d847-46e3-8071-ca88f28a4a92")
        public ModelChangeListener(final NotesPanelProvider notesView) {
            this.notesView = notesView;
        }

        @objid ("a334d75f-c39b-4af7-b43a-63fc683022e4")
        @Override
        public void modelChanged(final IModelChangeEvent event) {
            // Re enter the UI thread
            final Display display = Display.getDefault();
            if (display != null) {
                display.asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        // ModelChangeListener.this.notesView.refreshView();
                    }
                });
            }
        }

        @objid ("eae571b9-3141-4573-8283-7cfce9349ec9")
        @Override
        public void statusChanged(final IStatusChangeEvent event) {
            // Re enter the UI thread
            final Display display = Display.getDefault();
            if (display != null) {
                display.asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        // ModelChangeListener.this.notesView.refreshView();
                    }
                });
            }
        }

    }

}
