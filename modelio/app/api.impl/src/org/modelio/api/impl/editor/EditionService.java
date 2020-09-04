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

package org.modelio.api.impl.editor;

import java.io.File;
import java.io.IOException;
import java.net.UnknownServiceException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.modelio.editor.EditorType;
import org.modelio.api.modelio.editor.IEditionService;
import org.modelio.api.modelio.editor.IExternDocumentChangeListener;
import org.modelio.api.modelio.editor.IMDATextEditor;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.IModelioService;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.core.ui.MimeServices;
import org.modelio.creation.wizard.impl.CreationContributorManager;
import org.modelio.editors.richnote.api.RichNoteCreator;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.api.RichNoteFormatRegistry;
import org.modelio.editors.richnote.api.SupportLevel;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.editors.richnote.management.RichNotesSession;
import org.modelio.editors.service.EditionManager;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.statik.Artifact;

/**
 * Implementation of {@link IEditionService}
 */
@objid ("9b047d60-f973-4bec-a496-0667720f08b8")
public class EditionService implements IEditionService {
    @objid ("8fd7fbf0-1b3d-482d-b8f7-4afc85efc2dc")
    private IEclipseContext eclipseContext;

    @objid ("3228e593-d8d7-4787-af2c-1d8357b40952")
    private final IModelioEventService eventService;

    @objid ("46942bcf-e3ab-424f-bfd1-3c15c283967d")
    private final IModelioService modelioSvc = () -> "MDA API EditionService";

    @objid ("13bc8192-d095-48fe-8436-3c4b5c331e56")
    private final Map<IExternDocumentChangeListener, MdaRichNoteEditor> richNoteEditors = new HashMap<>();

    /**
     * Initialize the edition service.
     * 
     * @param eventService the Modelio event service
     */
    @objid ("83aae504-98c5-4e05-aa25-24ca50ff57ae")
    public EditionService(IModelioEventService eventService, IEclipseContext eclipseContext) {
        this.eventService = eventService;
        this.eclipseContext = eclipseContext;
    }

    @objid ("775aa980-fcae-4a9a-934c-5f05762c412f")
    @Override
    public void activateEditor(IMDATextEditor editor) {
        EditionManager.services().activateEditor(editor);
    }

    @objid ("63d697bb-bd59-4ef0-be04-315baf24008b")
    @Override
    public void closeEditor(IMDATextEditor editor) {
        EditionManager.services().closeEditor(editor);
    }

    @objid ("45d7d726-654e-406d-95e7-38312682375c")
    @Override
    public boolean createDocumentContent(final Document doc) throws IOException, UnknownServiceException {
        RichNoteCreator.createRichNote(doc);
        return true;
    }

    @objid ("27bc191b-9185-4002-b6bd-6dbf7efe4341")
    @Override
    public Path editRichNote(final Document doc, final IExternDocumentChangeListener listener) throws IOException {
        final IRichNoteFileRepository fileRepository = RichNotesSession.get(GProject.getProject(doc)).getFileRepository();
        
        if (listener != null) {
            // Store proxy editor in the map
            final MdaRichNoteEditor richNoteEditor = new MdaRichNoteEditor(doc, listener);
            this.richNoteEditors.put(listener, richNoteEditor);
            return fileRepository.openRichNote(doc, richNoteEditor);
        } else {
            return fileRepository.openRichNote(doc, null);
        }
    }

    @objid ("0f1883de-d93e-4bb5-8f34-0bd39a384dc7")
    @Override
    public List<String> getSupportedMimeTypes() {
        final Collection<RichNoteFormat> formats = RichNoteFormatRegistry.getInstance().getAllFormats();
        final List<String> ret = new ArrayList<>(formats.size());
        
        for (final RichNoteFormat f : formats) {
            if (f.getSupportLevel() == SupportLevel.Primary) {
                ret.add(f.getMimeType());
            }
        }
        return ret;
    }

    @objid ("70bb4c57-b5fc-496a-b5f7-6bd29692ac27")
    @Override
    public String html2text(String s) {
        return MimeServices.html2text(s);
    }

    @objid ("15b6eb9c-dce2-41fa-ab3e-156bc250f617")
    @Override
    public IMDATextEditor openEditor(ModelElement modelElement, File file, EditorType editorTypeID, boolean readonly) {
        return openEditor(modelElement, file, editorTypeID, readonly, EditionManager.DEFAULT_CHARSET_NAME, null, null);
    }

    @objid ("11cb29de-b0d5-41cb-bafe-977b0152d1f9")
    @Override
    public IMDATextEditor openEditor(ModelElement modelElement, File file, EditorType editorTypeID, boolean readonly, String charsetName) {
        return openEditor(modelElement, file, editorTypeID, readonly, charsetName, null, null);
    }

    @objid ("a36eec9b-ce6d-4891-a27c-5feca10158e8")
    @Override
    public void openEditor(final AbstractDiagram diagram) {
        this.eventService.postAsyncEvent(this.modelioSvc, ModelioEvent.EDIT_ELEMENT, diagram);
    }

    @objid ("9e8ed4f1-69e0-42c4-952e-343f44036857")
    @Override
    public void openEditor(final Artifact artifact) {
        this.eventService.postAsyncEvent(this.modelioSvc, ModelioEvent.EDIT_ELEMENT, artifact);
    }

    @objid ("e0754362-b51f-4f7e-aced-8108c7c3418f")
    @Override
    public void openEditor(final Document document) {
        this.eventService.postAsyncEvent(this.modelioSvc, ModelioEvent.EDIT_ELEMENT, document);
    }

    @objid ("40eca5da-da7f-4c2e-825b-ab45ca36d990")
    @Override
    public IMDATextEditor openEditor(ModelElement modelElement, File file, EditorType editorTypeID, boolean readonly, String charsetName, String askedTitle, String askedTooltip) {
        return EditionManager.services().openEditor(modelElement, file, editorTypeID, readonly, charsetName, askedTitle, askedTooltip);
    }

    @objid ("617d18e9-4374-4a79-81bd-4693506638af")
    @Override
    public void openEditor(final MatrixDefinition document) {
        this.eventService.postAsyncEvent(this.modelioSvc, ModelioEvent.EDIT_ELEMENT, document);
    }

    @objid ("f40d704b-dcfa-4275-aa70-feb7c391cb8b")
    @Override
    public void registerDiagramContributor(ContributorCategory category, IWizardContributor contributor) {
        getCreationContributorManager().addContributor(category, contributor);
    }

    @objid ("cd3dc372-aa67-4cdc-b2c3-7b711e77bbc9")
    @Override
    public void saveRichNote(final Document doc, Path fileToSave) throws IOException {
        final IRichNoteFileRepository fileRepository = RichNotesSession.get(GProject.getProject(doc)).getFileRepository();
        
        fileRepository.saveRichNote(doc, fileToSave);
    }

    @objid ("83876608-10e6-4747-a08f-f24f91066405")
    @Override
    public void setDocumentContent(final Document doc, Path content) throws IOException {
        // Initialize the document
        if (content != null) {
            final IRichNoteFileRepository fileRepository = RichNotesSession.get(doc).getFileRepository();
            fileRepository.initRichNoteFromFile(doc, content);
        }
    }

    @objid ("ee7ee03c-8aed-4c77-8734-918f9dea9570")
    @Override
    public void unregisterDiagramContributor(ContributorCategory category, IWizardContributor contributor) {
        getCreationContributorManager().removeContributor(category, contributor);
    }

    @objid ("9edad825-775a-48d9-aeee-1e4ee25cea36")
    @Override
    public void unregisterListener(final IExternDocumentChangeListener editor) {
        final MdaRichNoteEditor mdaRichNoteEditor = this.richNoteEditors.get(editor);
        
        final IRichNoteFileRepository fileRepository = RichNotesSession.get(GProject.getProject(mdaRichNoteEditor.getDoc())).getFileRepository();
        fileRepository.removeEditor(mdaRichNoteEditor);
    }

    @objid ("5e54bcd5-049f-4cc5-8a02-5160d2506e98")
    private CreationContributorManager getCreationContributorManager() {
        return Objects.requireNonNull(this.eclipseContext.get(CreationContributorManager.class));
    }

}
