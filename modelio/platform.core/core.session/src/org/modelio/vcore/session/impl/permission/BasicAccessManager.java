/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vcore.session.impl.permission;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.SmStatus;
import org.modelio.vcore.smkernel.StatusState;

/**
 * Basic access manager implementation.
 * <p>
 * Usable for non versioned repositories, do not use on versioned ones.
 * The only configuration points are a read only and a RAMC flags.
 */
@objid ("8dc8742f-38a3-11e2-920a-001ec947ccaf")
public class BasicAccessManager implements IAccessManager {
    @objid ("f6386489-3948-11e2-920a-001ec947ccaf")
    private boolean writeable = true;

    @objid ("7cda8e25-5a8e-4c0e-b1a8-5f45ef905deb")
    private StatusConf cmsNodeStatus = new StatusConf();

    @objid ("483b05bf-c9c2-4c20-9b0f-d0efdc0bd6c7")
    private StatusConf regularStatus = new StatusConf();

    @objid ("2adc63f1-38a9-11e2-920a-001ec947ccaf")
    @Override
    public void initStatus(SmObjectImpl obj, org.modelio.vcore.session.impl.storage.IModelLoader modelLoader) {
        if (obj.getMClass().isCmsNode())
            modelLoader.setRStatus(obj, this.cmsNodeStatus.on, this.cmsNodeStatus.off, this.cmsNodeStatus.undef);
        else
            modelLoader.setRStatus(obj, this.regularStatus.on, this.regularStatus.off, this.regularStatus.undef | IRStatus.MASK_CMS);
    }

    /**
     * Initialize the access manager.
     * <p>
     * The manager is configured to be writable, non versioned and non RAMC.
     */
    @objid ("f638648c-3948-11e2-920a-001ec947ccaf")
    public BasicAccessManager() {
        setWriteable(true);
        setRamc(false);
    }

    /**
     * Set elements as model component.
     * 
     * @param ramc <code>true</code> to set loaded elements as model component.
     */
    @objid ("f638648f-3948-11e2-920a-001ec947ccaf")
    public void setRamc(boolean ramc) {
        if (ramc) {
            this.cmsNodeStatus.set(IRStatus.RAMC, StatusState.TRUE);
            this.regularStatus.set(IRStatus.RAMC, StatusState.TRUE);
        } else {
            this.cmsNodeStatus.set(IRStatus.RAMC, StatusState.FALSE);
            this.regularStatus.set(IRStatus.RAMC, StatusState.FALSE);
        }
    }

    /**
     * Tells whether the access manager allow writing.
     * 
     * @return <code>true</code> if writing is allowed else <code>false</code>.
     */
    @objid ("f63ac6e0-3948-11e2-920a-001ec947ccaf")
    public boolean isWriteable() {
        return this.writeable;
    }

    /**
     * Allow write.
     * 
     * @param val <code>true</code> to allow writing, <code>false</code> to deny it.
     */
    @objid ("f63ac6e5-3948-11e2-920a-001ec947ccaf")
    public void setWriteable(boolean val) {
        this.writeable = val;
        if (val) {
            this.cmsNodeStatus.set(IRStatus.USERWRITE, StatusState.TRUE);
            this.regularStatus.set(IRStatus.USERWRITE, StatusState.TRUE);
        } else {
            this.cmsNodeStatus.set(IRStatus.USERWRITE, StatusState.FALSE);
            this.regularStatus.set(IRStatus.USERWRITE, StatusState.FALSE);
        }
    }

    @objid ("4e42cfb9-7725-4073-be66-5e2cc6d441fe")
    private static class StatusConf {
        @objid ("97052a76-c61a-4477-a1e1-34ce3c7ed912")
        public long on;

        @objid ("6369e53c-1e26-418d-ab35-b7abdd52a28c")
        public long off;

        @objid ("ea871128-7a59-4549-93e4-25f975fd0b5f")
        public long undef;

        @objid ("ee2a8261-c081-4d84-9ae8-d0ab4c0c2aa7")
        public StatusConf() {
            // noop
        }

        @objid ("36b560d6-c092-4a6e-b921-ec28bbb5c751")
        public void set(long bitdef, StatusState state) {
            switch (state) {
            case FALSE:
                this.on &= ~bitdef;
                this.off |= bitdef;
                this.undef &= ~bitdef;
                break;
            case TRUE:
                this.on |= bitdef;
                this.off &= ~bitdef;
                this.undef &= ~bitdef;
                break;
            case UNDEFINED:
                this.on &= ~bitdef;
                this.off &= ~bitdef;
                this.undef |= bitdef;
                break;
            default:
                assert false : state;
            }
        }

        @objid ("88c3489b-2421-4c21-ab79-c603ce54a475")
        public void ignore(long bitdef) {
            this.on &= ~bitdef;
            this.off &= ~bitdef;
            this.undef &= ~bitdef;
        }

        @objid ("13805952-35fe-4d1d-8708-bad40173ff6c")
        @Override
        public String toString() {
            return "StatusConf( on="+SmStatus.flagsToString(this.on)
                                                                    +",\n   off="+SmStatus.flagsToString(this.off)
                                                                    +",\n   undefine="+SmStatus.flagsToString(this.undef)+")";
        }

    }

}
