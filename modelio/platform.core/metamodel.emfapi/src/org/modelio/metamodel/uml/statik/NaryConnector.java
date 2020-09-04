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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Binding;

/**
 * NaryConnector v0.0.9054
 * 
 * 
 * null
 */
@objid ("0005d2fa-c4bf-1fd8-97fe-001ec947cd2a")
public interface NaryConnector extends NaryLink {
    /**
     * The metaclass simple name.
     */
    @objid ("088e8a51-0826-4acf-8112-02dbe8890cef")
    public static final String MNAME = "NaryConnector";

    /**
     * The metaclass qualified name.
     */
    @objid ("7a1d5256-8cf8-4db8-b1d8-06021db857da")
    public static final String MQNAME = "Standard.NaryConnector";

    /**
     * Getter for relation 'NaryConnector->Representation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("198fe771-a3d2-430c-89ad-2b8b0114b473")
    EList<Binding> getRepresentation();

    /**
     * Filtered Getter for relation 'NaryConnector->Representation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fd1fef58-12ed-4ed8-8a9e-7400a66f492e")
    <T extends Binding> List<T> getRepresentation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NaryConnector->RepresentedFeature'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("556886d8-34f3-413a-bdce-ce12fb15977c")
    UmlModelElement getRepresentedFeature();

    /**
     * Setter for relation 'NaryConnector->RepresentedFeature'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e5e20cf3-1560-43cd-84cd-279de0014c41")
    void setRepresentedFeature(UmlModelElement value);

}
