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
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.ParameterEffectKind;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.PassingMode;

/**
 * Parameter v0.0.9054
 * 
 * 
 * The main characteristics of a Parameter are its name, passing mode and type. 
 * 
 * The return value of an Operation is a specific case managed by a specific Association between Operation and Parameter. 
 * 
 * In Modelio, a Parameter belongs to its Operation.
 */
@objid ("0017c640-c4bf-1fd8-97fe-001ec947cd2a")
public interface Parameter extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("c307393d-5cb4-4341-a7a2-b077e2087f6a")
    public static final String MNAME = "Parameter";

    /**
     * The metaclass qualified name.
     */
    @objid ("cfb622f7-035c-4ebd-a326-fb1019d0d3b5")
    public static final String MQNAME = "Standard.Parameter";

    /**
     * Getter for attribute 'Parameter.ParameterPassing'
     * 
     * Metamodel description:
     * <i>Defines the passing mode (in, out or inout) of the Parameter.</i>
     */
    @objid ("2fdf6169-df67-4915-a591-f104acddee89")
    PassingMode getParameterPassing();

    /**
     * Setter for attribute 'Parameter.ParameterPassing'
     * 
     * Metamodel description:
     * <i>Defines the passing mode (in, out or inout) of the Parameter.</i>
     */
    @objid ("8665f067-62e7-4802-81ff-95bcc1ba1313")
    void setParameterPassing(PassingMode value);

    /**
     * Getter for attribute 'Parameter.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>Minimum value of the association's multiplicity. When placed on a target end, the multiplicity specifies the number of target instances that may be associated with a single source instance via the given Association.</i>
     */
    @objid ("3f77cdff-e0c1-41a0-b069-dde589e882c2")
    String getMultiplicityMin();

    /**
     * Setter for attribute 'Parameter.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>Minimum value of the association's multiplicity. When placed on a target end, the multiplicity specifies the number of target instances that may be associated with a single source instance via the given Association.</i>
     */
    @objid ("a1c67c52-b25e-4965-b84b-924c793b4b89")
    void setMultiplicityMin(String value);

    /**
     * Getter for attribute 'Parameter.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>Maximum value of the multiplicity.</i>
     */
    @objid ("f44268e6-3c0e-4ea6-9078-9b96ee36244e")
    String getMultiplicityMax();

    /**
     * Setter for attribute 'Parameter.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>Maximum value of the multiplicity.</i>
     */
    @objid ("8f07ddb5-98d2-4762-9f38-38af158cf2bb")
    void setMultiplicityMax(String value);

    /**
     * Getter for attribute 'Parameter.TypeConstraint'
     * 
     * Metamodel description:
     * <i>Construction parameter of the Parameter's class (for example, the size of a characters string).</i>
     */
    @objid ("6459f3bb-bb49-445d-8016-2aacee02bddd")
    String getTypeConstraint();

    /**
     * Setter for attribute 'Parameter.TypeConstraint'
     * 
     * Metamodel description:
     * <i>Construction parameter of the Parameter's class (for example, the size of a characters string).</i>
     */
    @objid ("8651ff43-470e-4476-8e14-089b2c3bbf88")
    void setTypeConstraint(String value);

    /**
     * Getter for attribute 'Parameter.DefaultValue'
     * 
     * Metamodel description:
     * <i>Default value of the Parameter. When the caller does not specify a value, then the default value is applied.</i>
     */
    @objid ("03e9d8f6-5402-4b6c-981b-4230212179a9")
    String getDefaultValue();

    /**
     * Setter for attribute 'Parameter.DefaultValue'
     * 
     * Metamodel description:
     * <i>Default value of the Parameter. When the caller does not specify a value, then the default value is applied.</i>
     */
    @objid ("20d5c3ad-7547-4756-af51-b516f4fca9a8")
    void setDefaultValue(String value);

    /**
     * Getter for attribute 'Parameter.IsOrdered'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c73b556a-ee0a-4f0d-bd07-6cedb34606bb")
    boolean isIsOrdered();

    /**
     * Setter for attribute 'Parameter.IsOrdered'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("62ff3912-124a-4ce7-a280-6bb37f3f4dea")
    void setIsOrdered(boolean value);

    /**
     * Getter for attribute 'Parameter.IsUnique'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d1c913e9-b52b-4c2f-a1ba-2e344ee232a5")
    boolean isIsUnique();

    /**
     * Setter for attribute 'Parameter.IsUnique'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8485c83a-414a-4c8b-84cb-0ad0b6786123")
    void setIsUnique(boolean value);

    /**
     * Getter for attribute 'Parameter.IsException'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6718adaf-39ae-4369-b0ac-273c4a00c52f")
    boolean isIsException();

    /**
     * Setter for attribute 'Parameter.IsException'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b85073f7-8d4f-49a1-b003-ae7290c68554")
    void setIsException(boolean value);

    /**
     * Getter for attribute 'Parameter.IsStream'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b27ce6aa-9291-4713-b30d-710a00a87cf5")
    boolean isIsStream();

    /**
     * Setter for attribute 'Parameter.IsStream'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7b0c8692-2556-4abb-a32c-134558f9cf5d")
    void setIsStream(boolean value);

    /**
     * Getter for attribute 'Parameter.Effect'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9b298f41-d747-4bc4-a48a-9169a1971be9")
    ParameterEffectKind getEffect();

    /**
     * Setter for attribute 'Parameter.Effect'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4983ee09-d10b-496f-85f9-ef2128168f1b")
    void setEffect(ParameterEffectKind value);

    /**
     * Getter for relation 'Parameter->Type'
     * 
     * Metamodel description:
     * <i>Defines the Class to which the Parameter belongs.</i>
     */
    @objid ("f68fb7ca-1559-49d3-9c97-4506b580e7e6")
    GeneralClass getType();

    /**
     * Setter for relation 'Parameter->Type'
     * 
     * Metamodel description:
     * <i>Defines the Class to which the Parameter belongs.</i>
     */
    @objid ("2cd4d8c9-5030-4a6c-a552-ce39cb6b2dc4")
    void setType(GeneralClass value);

    /**
     * Getter for relation 'Parameter->Composed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("94fee6ea-8534-4d87-9a72-4448a437a026")
    Operation getComposed();

    /**
     * Setter for relation 'Parameter->Composed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2a185fd8-b071-4998-b754-35727e93e35c")
    void setComposed(Operation value);

    /**
     * Getter for relation 'Parameter->Matching'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("a7dcdabe-2acb-435f-92ba-c95c2f4659b5")
    EList<Pin> getMatching();

    /**
     * Filtered Getter for relation 'Parameter->Matching'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("eb0bf696-7407-4e3e-9815-88a9fa51e2c1")
    <T extends Pin> List<T> getMatching(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Parameter->SRepresentation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("05552ccb-8cf2-43aa-baee-e44a1e9da684")
    EList<Signal> getSRepresentation();

    /**
     * Filtered Getter for relation 'Parameter->SRepresentation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b21050ad-68e2-440f-980a-8f85e0f2b4dd")
    <T extends Signal> List<T> getSRepresentation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Parameter->Returned'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("23f722e7-bf67-4eb0-a9c4-9fa5b7fec01a")
    Operation getReturned();

    /**
     * Setter for relation 'Parameter->Returned'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b1cfc41b-368b-4b7f-b95b-1c0d9257128c")
    void setReturned(Operation value);

    /**
     * Getter for relation 'Parameter->BehaviorParam'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e552605b-359f-4f90-afc6-0541eb90ee2f")
    EList<BehaviorParameter> getBehaviorParam();

    /**
     * Filtered Getter for relation 'Parameter->BehaviorParam'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("99ab1f4a-7734-4463-9324-a08bdc10e6df")
    <T extends BehaviorParameter> List<T> getBehaviorParam(java.lang.Class<T> filterClass);

}
