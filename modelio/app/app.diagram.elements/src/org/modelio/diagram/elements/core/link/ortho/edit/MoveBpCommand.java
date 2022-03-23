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
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;

@objid ("3fac1828-38a0-4d7c-8740-d9a51fdf27f4")
class MoveBpCommand {
    @objid ("e757390e-73de-40f4-bf50-c6b147c89fdd")
    private int index;

    @objid ("e05065e3-08d4-420d-8fd1-12f7576f8989")
    private int snappedToIndex = -1;

    @objid ("343ac43a-3098-444c-897c-13fd1a17e790")
    private int blockedByIndex = -1;

    @objid ("8f5087a9-6c40-46cd-9ef8-ba3657c78156")
    private Boolean manual;

    @objid ("40fe2950-9ece-414f-aeb8-4036aa292346")
    public static final int NONE_IDX = -10;

    @objid ("2aee944e-8cae-4c52-818d-4461b74c8284")
    private final Point newLocation = new Point();

    @objid ("cd100b7b-be45-424d-9853-47bf019ca1d8")
    private ConnectionAnchor newSourceAnchor;

    @objid ("609588cc-1351-4d25-8291-6627ef6da717")
    private ConnectionAnchor newTargetAnchor;

    @objid ("1fcf6bea-e8f3-4bac-907f-354f0fb225ca")
    public int getIndex() {
        return this.index;
    }

    @objid ("0d9b6cbc-fdb4-42a8-8583-da515e1637e6")
    public MoveBpCommand setIndex(int index) {
        this.index = index;
        return this;
    }

    @objid ("b9960bb6-68db-4cc0-aced-410c6656ab1a")
    public int getSnappedToIndex() {
        return this.snappedToIndex;
    }

    @objid ("5b578128-6ae3-4942-82c4-3a5e2f364072")
    public MoveBpCommand setSnappedToIndex(int snappedToIndex) {
        this.snappedToIndex = snappedToIndex;
        return this;
    }

    @objid ("b8d71408-1bc2-4ec2-8086-6fd4f47a089f")
    public int getBlockedByIndex() {
        return this.blockedByIndex;
    }

    @objid ("8c9c5dc6-8548-4f02-b279-3a08c827cd3a")
    public MoveBpCommand setBlockedByIndex(int blockedByIndex) {
        this.blockedByIndex = blockedByIndex;
        return this;
    }

    @objid ("ee70eec9-41c3-4f44-80ab-0b6846a09dd8")
    public ConnectionAnchor getNewSourceAnchor() {
        return this.newSourceAnchor;
    }

    @objid ("e0283965-203d-4978-8c82-aa8cbcc64184")
    public MoveBpCommand setNewSourceAnchor(ConnectionAnchor newSourceAnchor) {
        this.newSourceAnchor = newSourceAnchor;
        return this;
    }

    @objid ("f5f7e486-39c2-4653-97ca-d1e2a9c21051")
    public ConnectionAnchor getNewTargetAnchor() {
        return this.newTargetAnchor;
    }

    @objid ("47510998-34d2-40a2-95b1-db941349b533")
    public MoveBpCommand setNewTargetAnchor(ConnectionAnchor newTargetAnchor) {
        this.newTargetAnchor = newTargetAnchor;
        return this;
    }

    @objid ("92898183-4dd0-4062-a18d-40cf5c537fe5")
    public Point getNewLocation() {
        return this.newLocation;
    }

    @objid ("93a7b5dd-9abe-444a-b0ec-145331827c2a")
    public MoveBpCommand setManual(Boolean manual) {
        this.manual = manual;
        return this;
    }

    @objid ("e47feaa2-d66f-4dd1-83b1-bf2a43457ae3")
    public Boolean getManual() {
        return this.manual;
    }

    @objid ("9db0c956-183a-4c38-999b-900116cb22da")
    public MoveBpCommand setNewLocation(Point location) {
        this.newLocation.setLocation(location);
        return this;
    }

    @objid ("eaa2e8ad-8fe4-409f-a982-50dc65561ba9")
    public MoveBpCommand reset() {
        this.blockedByIndex = NONE_IDX;
        this.index = NONE_IDX;
        this.manual = null;
        //this.newLocation.setLocation(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.newSourceAnchor = null;
        this.newTargetAnchor = null;
        this.snappedToIndex = NONE_IDX;
        return this;
    }

}
