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
package org.modelio.editors.richnote.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.editors.richnote.editor.IRichNoteInput;
import org.modelio.editors.richnote.management.RichNotesSession;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Eclipse context function that generates a {@link IRichNoteInput}
 * from an eclipse context containing an {@link MPart}.
 */
@objid ("8841037e-0bca-4a2b-88b5-b425c31e3aba")
public class RichNoteInputProviderFunction extends ContextFunction {
    @objid ("501dafad-abd0-4e0f-8f01-be75955ad2bb")
    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        MPart inputPart = context.get(MPart.class);
        IProjectService ps = context.get(IProjectService.class);
        ICoreSession session = ps.getOpenedProject().getSession();
        
        String uri = inputPart.getPersistedState().get("inputURI");
        IRichNoteFileRepository fileManager = RichNotesSession.get(ps.getOpenedProject()).getFileRepository();
        return new RichNoteInput(session, fileManager, uri);
    }

    /**
     * Implementation if {@link IRichNoteInput}.
     */
    @objid ("bff8d84d-3b41-48e7-80a6-a739e33c8cc7")
    private static class RichNoteInput implements IRichNoteInput {
        @objid ("aa32c881-9264-4674-b445-c149f8140cea")
        private final MObject obj;

        @objid ("20c79cc8-83f1-41cd-8c56-182541ac90c7")
        private ICoreSession session;

        @objid ("7ddc2458-3765-4aff-9e0c-563c996b35e9")
        private IRichNoteFileRepository fileManager;

        @objid ("a5021c8d-321e-46c6-ae3d-acd39deec464")
        public  RichNoteInput(ICoreSession session, IRichNoteFileRepository fileManager, String uri) {
            MRef ref = new MRef(uri);
            this.obj = session.getModel().findByRef(ref);
            this.session = session;
            this.fileManager = fileManager;
            
        }

        @objid ("85eb3fba-2046-4001-a13d-2a89388b0203")
        @Override
        public MObject getEditedElement() {
            return this.obj;
        }

        @objid ("8ee1e6cb-7b0a-4755-b85e-59f01fb0695a")
        @Override
        public ICoreSession getSession() {
            return this.session;
        }

        @objid ("d8007542-de82-4b49-b523-140f2f2ceebe")
        @Override
        public IRichNoteFileRepository getFileManager() {
            return this.fileManager;
        }

    }

}
