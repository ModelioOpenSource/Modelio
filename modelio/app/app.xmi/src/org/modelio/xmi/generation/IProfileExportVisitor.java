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

package org.modelio.xmi.generation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.xmi.model.objing.profile.PExportAttribut;
import org.modelio.xmi.model.objing.profile.PExportGeneralization;
import org.modelio.xmi.model.objing.profile.PExportNoteType;
import org.modelio.xmi.model.objing.profile.PExportProfile;
import org.modelio.xmi.model.objing.profile.PExportReference;
import org.modelio.xmi.model.objing.profile.PExportStereotype;

@objid ("d23e76e5-ebcd-45a6-829e-a435e902e9dd")
public interface IProfileExportVisitor {
    @objid ("0f0d116e-cd53-49c3-b54f-e53e9ca29f68")
    void visit(PExportProfile profile);

    @objid ("9f9f6b87-24f7-476c-a6c6-d1181949ca8a")
    void visit(PExportStereotype stereotype);

    @objid ("14a03eaf-2894-484a-8e36-3bbed34df15c")
    void visit(PExportAttribut attribut);

    @objid ("b1c03bc6-9eec-4ff3-9c87-827dc6cd26d1")
    void visit(PExportGeneralization generalization);

    @objid ("37fa7fea-e0dd-420b-b622-329a964ac355")
    void visit(PExportNoteType noteType);

    @objid ("19773397-a5e1-4723-989d-266ded2e55c3")
    void visit(PExportReference reference);

}
