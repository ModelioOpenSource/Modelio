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
package org.modelio.editors.richnote.management;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.editors.richnote.editor.IRichNoteEditor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("b4b38209-1b85-4d0f-9a58-a5bf18a40f9f")
class EditorsRegistry {
    @objid ("a56c6a3a-91bb-45ff-aaad-7812c04813df")
    private Map<MRef, RichNoteToken> openEditors = new HashMap<>();

    @objid ("494b9393-1cab-48e3-b098-bf036659da42")
     EditorsRegistry() {
        // nothing
    }

    @objid ("27f10ff6-b219-414f-a19d-5127e5b40fc6")
    public IRichNoteEditor getEditor(MObject obj) {
        RichNoteToken token = this.openEditors.get(new MRef(obj));
        return token == null ? null : token.editor;
    }

    @objid ("b795be08-9afe-4afd-a9b0-d6129479101b")
    public void addEditor(MObject model, IRichNoteEditor editor) {
        RichNoteToken token = new RichNoteToken(model, editor);
        this.openEditors.put(new MRef(model), token);
        
    }

    @objid ("12dc5faa-0959-435a-a9c1-259f3a45f24c")
    public void removeEditor(IRichNoteEditor editor) {
        this.openEditors.entrySet().removeIf( en -> en.getValue().editor == editor);
    }

    /**
     * @return all editors.
     */
    @objid ("f7f16b85-0abb-45ef-94a0-540786c52cda")
    Collection<RichNoteToken> getAllEditors() {
        return this.openEditors.values();
    }

    /**
     * Empty the whole registry.
     */
    @objid ("35e9e2a3-0ec1-4a5c-aa71-681d27d5d20d")
    public void clear() {
        this.openEditors = new HashMap<>();
    }

    @objid ("62c0d2f7-e199-45c8-b52b-3bd207ff9d84")
    public RichNoteToken getEditorToken(MRef ref) {
        return this.openEditors.get(ref);
    }

    @objid ("f4bbc736-efb1-4c0e-a74c-229b7fad51f2")
    static final class RichNoteToken {
        @objid ("0360ca70-7e0a-4a99-8530-bfbbb4273d8a")
        final MObject model;

        @objid ("332b5992-578b-4991-9750-1ff6db739698")
        final IRichNoteEditor editor;

        @objid ("837648cd-cf9e-4ed5-bf58-baec4f1ac42c")
        public  RichNoteToken(MObject model, IRichNoteEditor editor) {
            this.model = model;
            this.editor = editor;
            
        }

    }

}
