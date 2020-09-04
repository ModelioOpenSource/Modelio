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

package org.modelio.vcore.session.impl.transactions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.transactions.smAction.AppendDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.CreateElementAction;
import org.modelio.vcore.session.impl.transactions.smAction.DeleteElementAction;
import org.modelio.vcore.session.impl.transactions.smAction.EraseDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.IActionManager;
import org.modelio.vcore.session.impl.transactions.smAction.MoveDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.SetAttributeAction;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Metaobject helper that records model modifications in transactions .
 */
@objid ("006f89ac-0d1e-1f20-85a5-001ec947cd2a")
public class ActionHandle {
    @objid ("006f0428-0d1e-1f20-85a5-001ec947cd2a")
    private boolean enabled = true;

    @objid ("7ea6e218-fa41-4bc2-a958-ee0e7ce0b39f")
    private IActionManager actionManager;

    /**
     * @param actionManager the transaction recorder
     */
    @objid ("006efee2-0d1e-1f20-85a5-001ec947cd2a")
    public ActionHandle(final IActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @objid ("006eff78-0d1e-1f20-85a5-001ec947cd2a")
    @SuppressWarnings("javadoc")
    public void appendObjDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val) {
        if (this.enabled) {
            this.actionManager.addAction(new AppendDependencyAction(obj, dep, dep_val));
        }
    }

    @objid ("006f000e-0d1e-1f20-85a5-001ec947cd2a")
    @SuppressWarnings("javadoc")
    public void appendObjDepValIndex(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val, final int index) {
        if (this.enabled) {
            this.actionManager.addAction(new AppendDependencyAction(obj, dep, dep_val, index));
        }
    }

    @objid ("006f00a4-0d1e-1f20-85a5-001ec947cd2a")
    @SuppressWarnings("javadoc")
    public void createObject(SmObjectImpl obj) {
        if (this.enabled) {
            this.actionManager.addAction(new CreateElementAction(obj));
        }
    }

    @objid ("006f0144-0d1e-1f20-85a5-001ec947cd2a")
    @SuppressWarnings("javadoc")
    public void deleteObject(SmObjectImpl obj) {
        if (this.enabled) {
            this.actionManager.addAction(new DeleteElementAction(obj));
        }
    }

    @objid ("006f01d0-0d1e-1f20-85a5-001ec947cd2a")
    @SuppressWarnings("javadoc")
    public void eraseObjDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val, final int index) {
        if (this.enabled) {
            this.actionManager.addAction(new EraseDependencyAction(obj, dep, dep_val, index));
        }
    }

    @objid ("006f0266-0d1e-1f20-85a5-001ec947cd2a")
    @SuppressWarnings("javadoc")
    public void moveObjDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl moving_ref, final int offset) {
        if (this.enabled) {
            this.actionManager.addAction(new MoveDependencyAction(obj, dep, moving_ref, offset));
        }
    }

    /**
     * Set whether action recording is enabled.
     * @param val whether action recording is enabled.
     */
    @objid ("006f02fc-0d1e-1f20-85a5-001ec947cd2a")
    public void setActionRecording(final boolean val) {
        this.enabled = val;
    }

    @objid ("006f0392-0d1e-1f20-85a5-001ec947cd2a")
    @SuppressWarnings("javadoc")
    public void setObjAttVal(SmObjectImpl obj, SmAttribute att, final Object att_val) {
        if (this.enabled) {
            // Highly tricky here: we were called with att_val being the old_value
            // and obj already modified with the new value
            Object newVal = att.getValue(obj.getData());
            Object oldVal = att_val;
        
            this.actionManager.addAction(new SetAttributeAction(obj, att, oldVal, newVal));
        }
    }

}
