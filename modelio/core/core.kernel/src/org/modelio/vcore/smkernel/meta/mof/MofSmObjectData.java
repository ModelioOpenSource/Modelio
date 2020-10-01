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

package org.modelio.vcore.smkernel.meta.mof;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectData;

/**
 * MOF objects data class.
 */
@objid ("e8177a27-2b47-4982-816b-209a07a7192a")
public class MofSmObjectData extends SmObjectData {
    @objid ("f5e4602f-2e73-4772-8d5b-63f023442f5f")
    private static final long serialVersionUID = 1L;

    /**
     * Contains the content of MAttributes and MDependencies.
     */
    @objid ("8c8a3a85-9e80-4b86-afd7-802227280ddb")
     final Map<Object, Object> content = new HashMap<>();

    @objid ("a753d9e1-5d4f-4b46-ac4f-76af7f76949c")
    protected MofSmObjectData(MofSmClass classof) {
        super(classof);
    }

}
