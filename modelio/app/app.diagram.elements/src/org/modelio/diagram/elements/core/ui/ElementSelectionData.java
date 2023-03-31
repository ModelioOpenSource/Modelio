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
package org.modelio.diagram.elements.core.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a5e29d96-af41-4d91-8607-452915285067")
public class ElementSelectionData {
    @objid ("4367b7bd-c317-4c29-af37-8588df122f69")
    private boolean islodead = false;

    @objid ("5edbac47-9371-48c1-b7cb-04c6b88c1861")
    private Collection<Object> candidates = null;

    @objid ("17cf0754-8dc9-45ad-bde2-2589eebf9f3a")
    private Collection<Object> filtredCandidates = null;

    @objid ("1077e4ab-897b-429c-9bba-33ddb53ac690")
    private MClass metaclass;

    @objid ("4633bb0b-4505-48d7-9364-d4f9786e406c")
    private Object selectedElement;

    @objid ("835c96c0-aa22-40a9-a5c8-88c61cdbc317")
    private ElementPlaceolderData placeolder;

    @objid ("18dc25c4-9d6c-4f2a-a75d-c869d6e27fe3")
    private IGmDiagram diagramFilter;

    @objid ("35c8c294-5881-48ce-b282-257fb4040866")
    private MObject contextFilter;

    @objid ("8d0139d8-77cc-45ef-bbf4-7b1523f16e28")
    public  ElementSelectionData(MClass metaclass) {
        this.metaclass = metaclass;
        this.placeolder = new ElementPlaceolderData();
        this.placeolder.setMetaclass(this.metaclass);
        this.selectedElement = this.placeolder;
        
        this.candidates = new ArrayList<>();
        this.candidates.add(this.placeolder);
        this.filtredCandidates = this.candidates;
        
    }

    @objid ("52e642cf-bf43-4dad-9983-92f212962a2e")
    public void setCandidates(List<MObject> candidates) {
        this.candidates.addAll(candidates);
        this.filtredCandidates = this.candidates;
        this.islodead = true;
        
    }

    @objid ("7793a601-f812-4cf9-95dd-73d0ebec3753")
    public void setFilter(IGmDiagram diagramFilter) {
        this.diagramFilter = diagramFilter;
    }

    @objid ("d93ba7d1-e7c7-4315-bdbc-560132359eb5")
    public void setFilter(MObject contextFilter) {
        this.contextFilter = contextFilter;
    }

    @objid ("1db26e0f-d68d-41fe-b83c-9599763e74d5")
    public MClass getMetaclass() {
        return metaclass;
    }

    @objid ("cf26ef7f-7f05-409c-85b6-b7d65ecda2e8")
    public void setMetaclass(MClass metaclass) {
        this.metaclass = metaclass;
    }

    @objid ("3319c4c2-372a-4589-afe2-3df62176732d")
    public Object getSelectedElement() {
        return selectedElement;
    }

    @objid ("c589bb65-17e2-4794-a150-0dfed31970ab")
    public void setSelectedElement(Object selectedElement) {
        this.selectedElement = selectedElement;
    }

    @objid ("a21cdbf7-1bb1-466b-9692-35e65a9627b6")
    public Collection<Object> getFiltredCandidates() {
        return filtredCandidates;
    }

    @objid ("97204789-78c4-44b1-9231-32caba55113a")
    public void setFiltredCandidates(Collection<Object> filtredCandidates) {
        this.filtredCandidates = filtredCandidates;
    }

    @objid ("3f1cf342-4f04-4cf5-a730-939d22379fd5")
    public Collection<Object> getCandidates() {
        return candidates;
    }

    @objid ("b68b3d27-8beb-4c50-b442-f787276be2b5")
    public boolean isLodead() {
        return this.islodead;
    }

    @objid ("bee2391c-56c9-46e9-9ccd-3cc76608866b")
    public IGmDiagram getDiagramFilter() {
        return diagramFilter;
    }

    @objid ("6e2695f3-204a-4652-9390-3563f770de49")
    public MObject getContextFilter() {
        return contextFilter;
    }

}
