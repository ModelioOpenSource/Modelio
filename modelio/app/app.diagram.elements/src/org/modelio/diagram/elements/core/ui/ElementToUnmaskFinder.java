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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("20459492-e212-42c6-a80c-fa0cbed1db27")
public class ElementToUnmaskFinder {
    @objid ("5ef80eff-9c91-4c35-962e-83e39a02683a")
    private ICoreSession session;

    @objid ("def45d0c-b00e-4119-9a0a-d552cd9f8023")
    private MClass metaclass;

    @objid ("bada38ce-336b-49b5-9aa0-184dbc3433b8")
    private List<MObject> elementsCandidates;

    @objid ("48c27a6c-b3e8-48e0-80f8-9de4cff7e6ba")
    private IGmDiagram filterDiagram;

    @objid ("3eab18a3-9fd2-4414-95e3-7188ec795fc4")
    private MObject contextFilter;

    @objid ("7ae8bf4e-8ea7-47ab-b7a5-5d32d13ad5ca")
    public  ElementToUnmaskFinder(MClass metaclass, ICoreSession session) {
        this.metaclass = metaclass;
        this.session = session;
        
    }

    @objid ("fc2a1ebc-4b80-4ac1-9d19-9d443311ed55")
    public void setDiagramFilter(IGmDiagram diagram) {
        this.filterDiagram = diagram;
    }

    @objid ("0f04aeb1-a196-4184-96c8-1640bbeedbc9")
    public void setContextFilter(MObject context) {
        this.contextFilter = context;
    }

    @objid ("4db6eda9-1387-4e32-9cf2-868211f7956e")
    public void findElements() {
        this.elementsCandidates = new ArrayList<>();
        
        for (MObject elem : this.session.getModel().findByClass(this.metaclass)) {
            if (applyFilter(elem)) {
                this.elementsCandidates.add(elem);
            }
        
        }
        
        Collections.sort(this.elementsCandidates, new Comparator<MObject>() {
            @Override
            public int compare(MObject o1, MObject o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        
    }

    @objid ("04469616-3719-44d1-a840-a3d53591519d")
    private boolean applyFilter(MObject elem) {
        if (this.filterDiagram != null && this.filterDiagram.getExistingModelFor(elem) != null) {
            return false;
        }
        if (this.contextFilter != null && !this.contextFilter.equals(elem.getCompositionOwner())) {
            return false;
        }
        return true;
    }

    @objid ("570f2ce5-41db-4360-a010-1e3e71924ffc")
    public void asyncSearch(Consumer<List<MObject>> callback) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                findElements();
                callback.accept(elementsCandidates);
            }
        });
        thread.start();
        
    }

}
