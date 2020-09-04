/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.editors.richnote.gui.creation.doctype;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

@objid ("7531d4b0-1780-4162-bd1a-393aba76f889")
class AdapterStereotype implements IAdaptable {
    @objid ("6ac8fb7e-594e-4468-8129-77160f568f82")
    private List<IAdaptable> noteAdapters = null;

    @objid ("e1df54cc-2b16-45e6-a915-87171d8f19d8")
    private AdapterModule parent;

    @objid ("61f9053a-e17b-4cb2-9782-2d456ebebb4b")
    private Stereotype stereotype;

    @objid ("abd82267-aa31-466b-8029-5df5b17012d0")
    public AdapterStereotype(Stereotype stereotype, AdapterModule parent) {
        this.stereotype = stereotype;
        this.parent = parent;
        this.noteAdapters = new ArrayList<>();
    }

    @objid ("00f78da4-0e10-40e9-b1bd-829b662ea573")
    @Override
    @SuppressWarnings({ "rawtypes" })
    public Object getAdapter(Class adapter) {
        return null;
    }

    @objid ("6c19aaa2-d26c-4953-9873-7a1f8fca0d26")
    public List<IAdaptable> getAdapters() {
        return this.noteAdapters;
    }

    @objid ("e1c9998b-2d32-4a45-aded-7bdb8e0899dd")
    public AdapterModule getParent() {
        return this.parent;
    }

    @objid ("3afb450b-3777-4928-aee0-f66d29eccf01")
    public Stereotype getStereotype() {
        return this.stereotype;
    }

    @objid ("72573762-0fc2-44a4-9c59-73bfc18c939c")
    void addDocType(final ResourceType docType) {
        this.noteAdapters.add(new AdapterRichNoteType(docType, this));
    }

}
