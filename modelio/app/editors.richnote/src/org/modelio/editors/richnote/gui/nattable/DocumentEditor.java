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

package org.modelio.editors.richnote.gui.nattable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.edit.editor.AbstractCellEditor;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;
import org.eclipse.nebula.widgets.nattable.widget.EditModeEnum;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.app.project.core.prefs.ProjectPreferencesKeys;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.nattable.viewer.model.INatTableViewerContext;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.api.RichNoteFormatRegistry;
import org.modelio.editors.richnote.api.SupportLevel;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * NatTable editor for rich notes (aka {@link Document}) cells.
 * <p>
 * This editor opens an external rich note editor on click, automatically creating a new one in the model if it doesn't already exist.
 * </p>
 * <p>
 * Errors are reported in dialog boxes.
 * </p>
 */
@objid ("2d503f40-00a0-41d1-ba33-dd44c4605b49")
public class DocumentEditor extends AbstractCellEditor {
    @objid ("d0084ad2-89bc-4840-b277-ff3951cfd926")
    private static final String MODELER_MODULE = "ModelerModule";

    /**
     * The editor control, not really used because the editor loses focus as soon as the external editor is opened.
     */
    @objid ("191e10f7-2e38-41eb-b9bd-2020543b20a8")
    private Canvas canvas;

    @objid ("bb45e3ef-7d17-487f-9dc8-4c2e20cfbe58")
    private Document value;

    @objid ("3b014f67-962d-4837-903d-62d5ecbb2af9")
    private final IActivationService activationService;

    @objid ("8fc9d403-c4b9-4ec4-b2ae-b27fc4a9ff9b")
    private final ICoreSession session;

    @objid ("600dbeb8-b5aa-46c3-9c50-e31f99828e83")
    private final IPreferenceStore preferenceStore;

    /**
     * Build a new instance of this editor.
     * 
     * @param context the table project context
     */
    @objid ("c6016fef-e782-45fd-87eb-c61eeb7e09a9")
    public DocumentEditor(INatTableViewerContext context) {
        this.activationService = context.getActivationService();
        this.session = context.getSession();
        this.preferenceStore = context.getProjectPreferences(ProjectPreferencesKeys.NODE_ID);
    }

    /**
     * As soon as the editor is activated, activate the
     */
    @objid ("abfdd640-a06d-48f6-9f58-317a4ba6e7d5")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        setEditorValue(originalCanonicalValue);
        
        this.canvas = createEditorControl(parentComposite);
        
        commit(MoveDirectionEnum.NONE, false);
        
        if (this.editMode == EditModeEnum.INLINE) {
            // Close editor so will react to subsequent clicks on the cell
            if (this.canvas != null && !this.canvas.isDisposed()) {
                this.canvas.getDisplay().asyncExec(() -> {
                    close();
        
                    // Open the rich note editor
                    if (this.activationService != null) {
                        this.activationService.activateMObject(this.value);
                    }
                });
            }
        }
        return this.canvas;
    }

    @objid ("d8f62cb7-b537-4c5f-be39-758672b06431")
    @Override
    public String getEditorValue() {
        return this.value != null ? this.value.toString() : "";
    }

    /**
     * Get the selected mime type from the project's preferences.
     * @see ProjectPreferencesKeys#RICHNOTE_DEFAULT_TYPE_PREFKEY
     * 
     * @return a mime type value.
     */
    @objid ("69d6c9d1-e420-4aad-9f14-c072780ebfba")
    private String getSelectedMimeType() {
        final String mimeType = this.preferenceStore.getString(ProjectPreferencesKeys.RICHNOTE_DEFAULT_TYPE_PREFKEY);
        if (mimeType != null && !mimeType.isEmpty()) {
            return mimeType;
        }
        
        // No mime type in preferences, use a default value
        if (System.getProperty("os.name").equals("Linux")) {
            return "application/vnd.oasis.opendocument.text";
        } else {
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        }
    }

    @objid ("c0b6d699-7157-47e1-9004-2fce516a611c")
    @Override
    public void setEditorValue(Object value) {
        if (value instanceof IDocumentNatValue) {
            IDocumentNatValue externDocumentValue = (IDocumentNatValue) value;
            if (externDocumentValue.getValue() == null) {
                String mimeType = getSelectedMimeType();
                if (isValidMimeType(mimeType)) {
                    this.value = createDocument(externDocumentValue.getOwner(), mimeType);
                }
            } else {
                this.value = externDocumentValue.getValue();
            }
        } else if (value != null) {
            final MRef mRef = (MRef) value;
            final IModel imodel = this.session.getModel();
        
            MObject referencedElement = imodel.findByRef(mRef, IModel.NODELETED);
            if (mRef.mc.equals(Document.MQNAME)) {
                this.value = (Document) referencedElement;
            } else {
                // Find the selected element to create the rich note...
                ModelElement owner = (ModelElement) imodel.findByRef(mRef);
        
                String mimeType = getSelectedMimeType();
                if (isValidMimeType(mimeType)) {
                    this.value = createDocument(owner, mimeType);
                }
            }
        } else {
            MessageDialog.openError(Display.getDefault().getActiveShell(),
                    EditorsRichNote.I18N.getMessage("ScopeRichTextCellEditor.InvalidValue"),
                    EditorsRichNote.I18N.getMessage("ScopeRichTextCellEditor.InvalidValueDetails"));
        }
    }

    /**
     * Create an {@link Document} on the element, with a default type.
     * 
     * @param element the element to create the {@link Document} on.
     * @param mimeType indicates the type of physical document to create.
     * @return the created rich note.
     */
    @objid ("132abd39-a545-40f4-8535-7469b3e33e65")
    protected Document createDocument(final ModelElement element, final String mimeType) {
        String docTypeName = getDefaultResourceTypeName(element);
        
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction("Create '" + mimeType + "' " + docTypeName + "rich note")) {
            IMModelServices modelSvc = new MModelServices(this.session);
            IInfrastructureModelFactory f = modelSvc.getModelFactory().getFactory(IInfrastructureModelFactory.class);
        
            ResourceType docType = modelSvc.getResourceType(DocumentEditor.MODELER_MODULE, ".*", docTypeName, element.getMClass());
            if (docType == null) {
                throw new FileNotFoundException("'" + docTypeName + "' rich note type not found in '" + DocumentEditor.MODELER_MODULE + "' module.");
            }
        
            Document externDoc = f.resourceBuilder()
                    .withOwner(element)
                    .withMimeType(mimeType)
                    .withRole(docType)
                    .createEmbeddedDocument();
            externDoc.setName(element.getName());
        
            transaction.commit();
        
            return externDoc;
        } catch (ElementNotUniqueException e) {
            // many rich note types with same name
            EditorsRichNote.LOG.error(e);
            MessageDialog.openError(Display.getDefault().getActiveShell(),
                    EditorsRichNote.I18N.getMessage("ScopeRichTextCellEditor.CannotCreateRichNote"),
                    e.getLocalizedMessage());
        } catch (UnknownServiceException e) {
            // no default content could be found.
            EditorsRichNote.LOG.error(e);
            MessageDialog.openError(Display.getDefault().getActiveShell(),
                    EditorsRichNote.I18N.getMessage("ScopeRichTextCellEditor.CannotCreateRichNote"),
                    e.getLocalizedMessage());
        } catch (IOException e) {
            // error trying to create the file.
            EditorsRichNote.LOG.error(e);
            MessageDialog.openError(Display.getDefault().getActiveShell(),
                    EditorsRichNote.I18N.getMessage("ScopeRichTextCellEditor.CannotCreateRichNote"),
                    FileUtils.getLocalizedMessage(e));
        }
        return null;
    }

    @objid ("2b6df358-aa7c-4f2b-abd7-d9ff936fcb0c")
    @Override
    public Canvas getEditorControl() {
        return this.canvas;
    }

    @objid ("8aa7e570-ad88-4969-a570-fb26e4dc58c9")
    @Override
    public Canvas createEditorControl(Composite parentComposite) {
        return new Canvas(parentComposite, SWT.NONE);
    }

    /**
     * As it doesn't make sense, multi edition is not supported by this editor.
     */
    @objid ("b8603e71-13f1-4ab7-9a3a-677a3738ddfd")
    @Override
    public boolean supportMultiEdit(IConfigRegistry aConfigRegistry, List<String> configLabels) {
        return false;
    }

    /**
     * Make sure this mime type is known by the {@link RichNoteFormatRegistry} and can be used.
     */
    @objid ("952b4488-b303-4bb2-bbc2-60501e609a30")
    private boolean isValidMimeType(String mimeType) {
        RichNoteFormat format = RichNoteFormatRegistry.getInstance().getDocumentFormatForMime(mimeType);
        if (format == null) {
            MessageDialog.openError(Display.getDefault().getActiveShell(),
                    EditorsRichNote.I18N.getMessage("ScopeRichTextCellEditor.InvalidMimeType"),
                    EditorsRichNote.I18N.getMessage("ScopeRichTextCellEditor.InvalidMimeTypeDetails", mimeType));
            return false;
        } else if (!format.isUsable() || format.getSupportLevel() != SupportLevel.Primary) {
            MessageDialog.openError(Display.getDefault().getActiveShell(),
                    EditorsRichNote.I18N.getMessage("ScopeRichTextCellEditor.InvalidMimeType"),
                    EditorsRichNote.I18N.getMessage("ScopeRichTextCellEditor.UnsupportedFormatDetails", format.getLabel()));
            return false;
        } else {
            return true;
        }
    }

    /**
     * Get the name of the default {@link ResourceType} according to the element's metaclass.
     * 
     * @param element the element to create the extern document on.
     * @return the name of an {@link ResourceType} belonging to ModelerModule.
     */
    @objid ("ba067147-5fa0-4f56-96b1-d212ae78a45c")
    private String getDefaultResourceTypeName(final ModelElement element) {
        String docTypeName;
        // TODO this is kind of ugly...
        String qName = element.getMClass().getQualifiedName();
        if (qName.equals("Analyst.Goal")) {
            docTypeName = "goal";
        } else if (qName.equals("Analyst.BusinessRule")) {
            docTypeName = "business_rule";
        } else if (qName.equals("Analyst.Requirement")) {
            docTypeName = "requirement";
        } else if (qName.equals("Analyst.Term")) {
            docTypeName = "term";
        } else if (qName.equals("Analyst.GenericAnalystElement")) {
            docTypeName = "generic_analyst";
        } else if (qName.equals("Analyst.AnalystElement")) {
            // Assume it's requirement
            docTypeName = "requirement";
        } else {
            docTypeName = "other";
        }
        return docTypeName;
    }

    /**
     * Build a new instance of this editor.
     * @param projectService project services
     * 
     * @param activationService activation service
     */
    @objid ("9c60cd60-f466-4552-8907-bdbe1b422cc3")
    public DocumentEditor(IProjectService projService, IActivationService activationService) {
        this.session = projService.getSession();
        this.activationService = activationService;
        this.preferenceStore = projService.getProjectPreferences(ProjectPreferencesKeys.NODE_ID);
    }

}
