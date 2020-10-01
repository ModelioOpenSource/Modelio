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

package org.modelio.vcore.smkernel.meta.fake;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectSmClass;
import org.modelio.vcore.smkernel.StatusState;

/**
 * Fake objects data class.
 */
@objid ("13e0b0d2-af78-4002-ad20-31dade2fc7ad")
public class FakeSmObjectData extends SmObjectData {
    @objid ("c9148d93-71f7-4db7-ba6d-d5b85940c174")
     String name;

    @objid ("93cd8d49-91b1-4d6e-95c4-e73d2a8d4a6e")
    private static final long serialVersionUID = 1L;

    /**
     * Contains the content of MAttributes and MDependencies.
     */
    @objid ("a7d52c8b-4666-4e1c-a4cd-72a8a1f91ff5")
     Map<Object, Object> content = new HashMap<>();

    @objid ("4b32fc48-581e-4fad-b347-f693dccc131b")
    protected FakeSmObjectData(SmObjectSmClass classof) {
        super(classof);
        
        setRFlags(IRStatus.SHELL, StatusState.TRUE);
    }

}
