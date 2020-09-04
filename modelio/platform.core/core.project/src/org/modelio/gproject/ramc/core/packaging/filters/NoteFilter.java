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

package org.modelio.gproject.ramc.core.packaging.filters;

import java.util.Collection;
import java.util.HashSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This filter refuses all Notes which type is not exported.
 */
@objid ("61790925-c746-11e1-96e9-001ec947ccaf")
class NoteFilter implements IObjectFilter {
    @objid ("6179090d-c746-11e1-96e9-001ec947ccaf")
    private Collection<NoteType> exportedNoteTypes = new HashSet<>();

    @objid ("24285f9a-ee98-497b-9522-d903cec429b5")
    private Artifact artifact;

    @objid ("61790789-c746-11e1-96e9-001ec947ccaf")
    @Override
    public boolean accept(MObject obj) {
        Note n = (Note) obj;
        return n.getSubject() == this.artifact || this.exportedNoteTypes.contains(n.getModel());
    }

    @objid ("61790859-c746-11e1-96e9-001ec947ccaf")
    public void addNoteType(NoteType noteType) {
        this.exportedNoteTypes.add(noteType);
    }

    @objid ("e9d8993c-41d1-422d-8c9e-c4e5da052353")
    public NoteFilter(Artifact artifact) {
        this.artifact = artifact;
    }

}
