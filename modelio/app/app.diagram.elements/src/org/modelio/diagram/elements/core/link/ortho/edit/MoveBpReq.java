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
package org.modelio.diagram.elements.core.link.ortho.edit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;

@objid ("1446c6e8-0f3c-45bd-950f-f3bce174571b")
class MoveBpReq {
    @objid ("178be61b-5046-45f8-8cb8-a03e84e119ae")
    private int pointIndex;

    @objid ("8eb2597b-1fcd-42d1-848c-c3f0af6f6c55")
    private Boolean manual;

    @objid ("f7538e9a-433f-437c-accb-a7b3765ea999")
    private AxisAccessor checkPrevious;

    @objid ("4b94e1e4-0be1-4a0f-977d-9023ed2cf615")
    private AxisAccessor checkNext;

    @objid ("4260b256-319d-4b2a-80ec-af58c29d2e6d")
    private final Point location = new Point();

    @objid ("e4059324-d149-4dc6-a9d0-811f9cbda3de")
    public boolean isSatisfiedBy(MoveBpCommand result) {
        return getLocation().equals(result.getNewLocation());
    }

    @objid ("017f91e1-6cc3-4f3e-bf60-759778e0cadc")
    public int getPointIndex() {
        return this.pointIndex;
    }

    @objid ("ab9e8ef6-0e1a-4908-bdd7-6472b31c6001")
    public MoveBpReq setPointIndex(int pointIndex) {
        this.pointIndex = pointIndex;
        return this;
    }

    @objid ("f5295b37-6fd9-4a55-9b52-11557b273cc6")
    public AxisAccessor getCheckPrevious() {
        return this.checkPrevious;
    }

    @objid ("6f29ac41-06b1-4e84-8dd0-397cde16458d")
    public MoveBpReq setCheckPrevious(AxisAccessor checkPrevious) {
        this.checkPrevious = checkPrevious;
        return this;
    }

    @objid ("a691c8de-9783-4e92-8ea1-401cce214134")
    public AxisAccessor getCheckNext() {
        return this.checkNext;
    }

    @objid ("f2efae23-31b2-41a0-8de5-a9b2d010dcfb")
    public MoveBpReq setCheckNext(AxisAccessor checkNext) {
        this.checkNext = checkNext;
        return this;
    }

    @objid ("8bbe4719-e692-4c31-bc8f-1350309d8a6f")
    public Point getLocation() {
        return this.location;
    }

    @objid ("b724751c-97a3-45d2-a947-dd776c5f90c7")
    public MoveBpReq setLocation(Point location) {
        this.location.setLocation(location);
        return this;
    }

    @objid ("08288c8c-d8ba-4527-9c8c-3780ebbea6ef")
    public MoveBpReq setManual(Boolean manual) {
        this.manual = manual;
        return this;
    }

    @objid ("bab37713-cae3-48e1-a308-205901d86985")
    public Boolean getManual() {
        return this.manual;
    }

}
