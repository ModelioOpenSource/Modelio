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

package org.modelio.vcore.session.api.metamodel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;

@objid ("cd1e0bca-7974-41fa-9203-f4c0a18287ff")
public interface IMetamodelListener {
    @objid ("3c9beb85-02ae-49cf-9e8a-705ce7c6d97c")
    void metamodelFragmentAdded(MMetamodel metamodel, MMetamodelFragment fragment);

    @objid ("be5bda46-8f89-4318-ba59-a0baa03e66bf")
    void metamodelFragmentRemoved(MMetamodel metamodel, MMetamodelFragment fragment);

    @objid ("e0b04a1d-8f58-493c-be3e-17afb5f07a16")
    void removingMetamodelFragment(MMetamodel metamodel, MMetamodelFragment fragment);

}
