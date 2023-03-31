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
package org.modelio.app.model.merge.internal;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Merge elements dialog data model.
 */
@objid ("bf705344-7fb3-4f7a-bddb-30c759c3bddb")
public class MergeElementsData {
    @objid ("b99835ec-dd27-420e-90f3-0b50918d9ea0")
    private boolean deleteReflexiveLinks;

    @objid ("f77799a2-22fa-4e04-b1d0-2302893f5e87")
    private MObject target;

    @objid ("5442fb07-c54b-463c-894e-ae62f301172a")
    private Collection<MObject> input;

    @objid ("e2a0c7b8-0dec-4383-9d3f-3fddeec15ce8")
    public void setInput(Collection<MObject> input) {
        Objects.requireNonNull(input);
        this.input = input;
        
    }

    @objid ("41c732f9-cd09-4ca9-98c6-b2e4125a870d")
    public Collection<MObject> getInput() {
        return this.input;
    }

    @objid ("fa13056a-9ceb-4382-9766-c5e22debe26d")
    public void setTarget(MObject target) {
        this.target = target;
    }

    @objid ("20d89dc3-26f1-476d-abaa-c72f458126f1")
    public MObject getTarget() {
        return this.target;
    }

    @objid ("143dfa9c-e1ac-48ef-8b3a-3968370e757e")
    public Stream<MObject> streamTargets() {
        return this.input.stream().filter(o -> !o.equals(this.target));
    }

    @objid ("4f5eba3c-e3db-4916-aa1b-dbb01594011c")
    public void setDeleteReflexiveLinks(boolean value) {
        this.deleteReflexiveLinks = value;
    }

    @objid ("7aea8765-5dd6-4967-9fae-1e70e3722c56")
    public boolean isDeleteReflexiveLinks() {
        return this.deleteReflexiveLinks;
    }

}
