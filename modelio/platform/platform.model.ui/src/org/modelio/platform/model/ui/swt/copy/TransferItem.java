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

package org.modelio.platform.model.ui.swt.copy;

import java.io.Serializable;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Transfer Item
 */
@objid ("fe7de46a-c172-4d6b-b8f5-7373a9f88cc6")
public class TransferItem implements Serializable {
    @objid ("42846ecf-0ead-4924-a462-b3a1dae8d65c")
    private static final long serialVersionUID = -8494001589964644762L;

    @objid ("b887adf8-e18a-47f3-b4f3-e52d15b0e820")
    private MRef transferedElement;

    @objid ("f65a8be9-1a4b-4b3d-927e-f3850da382ab")
    private MRef oldParent;

    @objid ("dd086b41-0d8f-4347-92b0-48759fa19bb6")
    public TransferItem(MObject transferedElement, MObject oldParent) {
        super();
        this.transferedElement = new MRef(transferedElement);
        this.oldParent = new MRef(oldParent);
    }

    @objid ("53e009c6-340c-4c6e-b405-3b10b4b30f22")
    public MRef getTransferedElementRef() {
        return this.transferedElement;
    }

    @objid ("5747fc3c-14bd-4635-ae83-95ce5436536a")
    public MRef getOldParentRef() {
        return this.oldParent;
    }

}
