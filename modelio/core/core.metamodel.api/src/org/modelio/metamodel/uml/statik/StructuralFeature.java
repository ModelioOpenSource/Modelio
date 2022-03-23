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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;

/**
 * StructuralFeature v0.0.9054
 * 
 * 
 * null
 */
@objid ("001ee9c0-c4bf-1fd8-97fe-001ec947cd2a")
public interface StructuralFeature extends Feature {
    /**
     * The metaclass simple name.
     */
    @objid ("ac2c0c7a-4f5b-49e3-b1a4-14e1827de46e")
    public static final String MNAME = "StructuralFeature";

    /**
     * The metaclass qualified name.
     */
    @objid ("54e6187f-208b-442b-a082-8238f10c84bc")
    public static final String MQNAME = "Standard.StructuralFeature";

    /**
     * Getter for attribute 'StructuralFeature.Changeable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a726e297-2fc8-42b8-a27d-dba5eb61dc2a")
    KindOfAccess getChangeable();

    /**
     * Setter for attribute 'StructuralFeature.Changeable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("812dfc97-7935-486f-a65d-dfa6a428e64f")
    void setChangeable(KindOfAccess value);

    /**
     * Getter for attribute 'StructuralFeature.IsDerived'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8e4b939b-c77c-4e56-a25f-4534db668762")
    boolean isIsDerived();

    /**
     * Setter for attribute 'StructuralFeature.IsDerived'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("32440476-f2ae-44bf-b4fe-821af9336046")
    void setIsDerived(boolean value);

    /**
     * Getter for attribute 'StructuralFeature.IsOrdered'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("da9f05c8-b0be-4dd7-94dc-95febdcf0d54")
    boolean isIsOrdered();

    /**
     * Setter for attribute 'StructuralFeature.IsOrdered'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("aada7f9b-f41f-458d-941d-0bfaa38cbe19")
    void setIsOrdered(boolean value);

    /**
     * Getter for attribute 'StructuralFeature.IsUnique'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3aaf31d6-488b-4ea0-aeab-7c3a65ec3b8b")
    boolean isIsUnique();

    /**
     * Setter for attribute 'StructuralFeature.IsUnique'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4846687d-2c3d-4969-9aa4-c016835eaa5b")
    void setIsUnique(boolean value);

    /**
     * Getter for attribute 'StructuralFeature.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ec530ef9-3651-487e-b5bc-3e0d14d93021")
    String getMultiplicityMin();

    /**
     * Setter for attribute 'StructuralFeature.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("970057f6-8c4c-4052-8cbc-092ccc64b38c")
    void setMultiplicityMin(String value);

    /**
     * Getter for attribute 'StructuralFeature.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("08220db8-5a99-493e-9944-a63dcea69079")
    String getMultiplicityMax();

    /**
     * Setter for attribute 'StructuralFeature.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("69e5b29f-4672-421a-afb2-94d19430e7ac")
    void setMultiplicityMax(String value);

    /**
     * Getter for relation 'StructuralFeature->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1150de20-0868-44b4-be9a-0b40937e0745")
    EList<InformationFlow> getRealizedInformationFlow();

    /**
     * Filtered Getter for relation 'StructuralFeature->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6968ef6b-5e91-4c13-b2e4-674f48803edc")
    <T extends InformationFlow> List<T> getRealizedInformationFlow(java.lang.Class<T> filterClass);

}
