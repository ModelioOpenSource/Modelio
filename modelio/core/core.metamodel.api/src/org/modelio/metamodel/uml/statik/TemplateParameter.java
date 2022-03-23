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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * TemplateParameter v0.0.9054
 * 
 * 
 * NameSpaces and Operations can be templated by TemplateParameters. 
 * 
 * Concerning NameSpaces, this particularly applies for Classes, Packages and Collaborations. 
 * 
 * In Modelio, TemplateParameters belong to their parameterized NameSpace or Operation.
 */
@objid ("001d18e8-c4bf-1fd8-97fe-001ec947cd2a")
public interface TemplateParameter extends GeneralClass {
    /**
     * The metaclass simple name.
     */
    @objid ("b8d95a80-53e7-43e7-b147-16000a5fc14d")
    public static final String MNAME = "TemplateParameter";

    /**
     * The metaclass qualified name.
     */
    @objid ("f4d7dce7-3b9b-4b92-94dd-b883028949b5")
    public static final String MQNAME = "Standard.TemplateParameter";

    /**
     * Getter for attribute 'TemplateParameter.DefaultValue'
     * 
     * Metamodel description:
     * <i>Default parameter value, when the IsValueParameter is true.</i>
     */
    @objid ("5341107f-8f3d-4e4f-b9ff-ed7fcf942b5e")
    String getDefaultValue();

    /**
     * Setter for attribute 'TemplateParameter.DefaultValue'
     * 
     * Metamodel description:
     * <i>Default parameter value, when the IsValueParameter is true.</i>
     */
    @objid ("b38eb119-f468-4941-a51c-2a480c637182")
    void setDefaultValue(String value);

    /**
     * Getter for attribute 'TemplateParameter.IsValueParameter'
     * 
     * Metamodel description:
     * <i>Expresses if the parameter is a value, which is in this case "DefaultValue".</i>
     */
    @objid ("8069fb71-0d70-4b79-9a86-d3bd37a14aac")
    boolean isIsValueParameter();

    /**
     * Setter for attribute 'TemplateParameter.IsValueParameter'
     * 
     * Metamodel description:
     * <i>Expresses if the parameter is a value, which is in this case "DefaultValue".</i>
     */
    @objid ("4d0b7482-5d16-4080-a471-d696d8c13434")
    void setIsValueParameter(boolean value);

    /**
     * Getter for relation 'TemplateParameter->ParameterSubstitution'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("acba0b26-33bb-4da7-ade3-aed1211fab64")
    EList<TemplateParameterSubstitution> getParameterSubstitution();

    /**
     * Filtered Getter for relation 'TemplateParameter->ParameterSubstitution'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("12ab4c2f-9bd4-4ac8-b763-dfde76590637")
    <T extends TemplateParameterSubstitution> List<T> getParameterSubstitution(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'TemplateParameter->Type'
     * 
     * Metamodel description:
     * <i>Default parameter type, when IsValueParameter is false.</i>
     */
    @objid ("88acf422-649e-4f0c-96f9-3242f1bce808")
    UmlModelElement getType();

    /**
     * Setter for relation 'TemplateParameter->Type'
     * 
     * Metamodel description:
     * <i>Default parameter type, when IsValueParameter is false.</i>
     */
    @objid ("ce31ef04-3a41-4654-9b3c-1821e48559e3")
    void setType(UmlModelElement value);

    /**
     * Getter for relation 'TemplateParameter->Parameterized'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("51849e9a-8658-4448-ae07-d65ab393af12")
    NameSpace getParameterized();

    /**
     * Setter for relation 'TemplateParameter->Parameterized'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b495c0c7-63dc-4f38-b02e-085935587700")
    void setParameterized(NameSpace value);

    /**
     * Getter for relation 'TemplateParameter->OwnedParameterElement'
     * 
     * Metamodel description:
     * <i>A TemplateParameter references a ParameterableElement that is exposed as a formal template parameter in the container template. This parameterizable element is meaningful only within the template, or other templates that may have access to its content (for example, if the template supports specialization). The exposed parameterizable element may not be used in other parts of the model. A TemplateParameter may own the exposed ParameterableElement in situations where that element is only referenced from within the template.</i>
     */
    @objid ("0d254a89-1539-4e2c-8740-4089dde10f88")
    UmlModelElement getOwnedParameterElement();

    /**
     * Setter for relation 'TemplateParameter->OwnedParameterElement'
     * 
     * Metamodel description:
     * <i>A TemplateParameter references a ParameterableElement that is exposed as a formal template parameter in the container template. This parameterizable element is meaningful only within the template, or other templates that may have access to its content (for example, if the template supports specialization). The exposed parameterizable element may not be used in other parts of the model. A TemplateParameter may own the exposed ParameterableElement in situations where that element is only referenced from within the template.</i>
     */
    @objid ("cdea5137-c89b-4c9b-9bc9-c6b2c8e80ef5")
    void setOwnedParameterElement(UmlModelElement value);

    /**
     * Getter for relation 'TemplateParameter->DefaultType'
     * 
     * Metamodel description:
     * <i>Value that the parameter takes if no specific value is specified.</i>
     */
    @objid ("f04652b4-c9cc-42db-99ca-b8afe1a7d10a")
    UmlModelElement getDefaultType();

    /**
     * Setter for relation 'TemplateParameter->DefaultType'
     * 
     * Metamodel description:
     * <i>Value that the parameter takes if no specific value is specified.</i>
     */
    @objid ("361ae28c-582a-473c-b6dd-ce97873e7501")
    void setDefaultType(UmlModelElement value);

    /**
     * Getter for relation 'TemplateParameter->ParameterizedOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7da870fd-ce3e-4468-80e2-7b7f75ecd0e9")
    Operation getParameterizedOperation();

    /**
     * Setter for relation 'TemplateParameter->ParameterizedOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5cd8477a-b62e-446c-a266-f11df4883c8b")
    void setParameterizedOperation(Operation value);

}
