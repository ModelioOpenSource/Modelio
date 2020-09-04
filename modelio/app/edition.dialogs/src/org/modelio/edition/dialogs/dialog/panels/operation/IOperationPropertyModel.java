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

package org.modelio.edition.dialogs.dialog.panels.operation;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.edition.dialogs.dialog.panels.operation.params.ParameterPropertyModel;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.MethodPassingMode;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.metamodel.uml.statik.VisibilityMode;

@objid ("07e5bed2-716f-4c7a-876c-3d32cc4ff717")
public interface IOperationPropertyModel {
    @objid ("d88db862-d3e6-447f-b1c6-96f3934c8c68")
    String getName();

    @objid ("9f43fbbc-bf69-4dbf-bb17-2e4e8586ac39")
    void setName(String name);

    @objid ("f3504abd-652a-4138-9d64-237e4cc4adb8")
    void setVisibility(VisibilityMode value);

    @objid ("33abfe1d-82ac-4f20-a447-58e15ee16dc3")
    void setAbstract(Boolean value);

    @objid ("64b9b618-d3c5-4502-8855-4d7ea964343b")
    void setClass(Boolean value);

    @objid ("c0840aa6-f667-48f0-b596-0fada7c0df7b")
    void setFinal(Boolean value);

    @objid ("44006af7-2d3f-4b04-b782-95eed36002e2")
    void setPassing(MethodPassingMode value);

    @objid ("fbf4059f-4149-4f3c-837d-ecae729c4ab0")
    VisibilityMode getVisibility();

    @objid ("345ec0e0-8611-4277-9858-d24e58c439b3")
    boolean isAbstract();

    @objid ("a732073c-c754-40d3-9319-5268185c3e4d")
    boolean isClass();

    @objid ("9fd4ab1d-ad35-4da8-a1c6-9b356c0e4cc9")
    boolean isFinal();

    @objid ("32659e56-6a2f-467e-9129-e278af684fec")
    MethodPassingMode getPassing();

    @objid ("e27184aa-701d-4587-a27e-5ddab9553653")
    boolean isModifiable();

    @objid ("02e09388-e23b-4e57-b492-8b0dc7f5763e")
    int getIOParameterSize();

    @objid ("df7e9cb9-1035-4b56-9e58-e243348cc5b6")
    PassingMode getParameterPassingMode(int index);

    @objid ("a130a133-34a2-4525-bb0b-9d873fd9c4c7")
    String getParameterName(int index);

    @objid ("86f8c1df-3258-48c6-9da1-e8251df326ab")
    GeneralClass getParameterType(int index);

    @objid ("02e02e7f-b53a-4878-930a-37c78956883a")
    String getParameterMultiplicityMin(int index);

    @objid ("4609aa1e-dbad-46ab-bee2-c94dcf70127c")
    String getParameterMultiplicityMax(int index);

    @objid ("7bbafa6a-13a4-4f56-a2ed-be7a6f67a09a")
    String getParameterDefaultValue(int index);

    @objid ("0ec4e099-1bfa-4e12-bd12-fc1d366a76e2")
    void setParameterPassingMode(int index, PassingMode value);

    @objid ("54e40d3e-a8af-48da-bca2-bc8ed1ab4e63")
    void setParameterName(int index, String value);

    @objid ("7d9ceb87-224a-4249-8070-de052650415c")
    void setParameterType(int index, GeneralClass value);

    @objid ("551c634a-1854-4a17-9ef4-a11a3fc2ac29")
    void setParameterDefaultValue(int index, String value);

    @objid ("65767968-4861-44a5-8673-6777466da2cd")
    void setParameterMultiplicityMin(int index, String value);

    @objid ("0f7a9818-b3c5-4582-963a-aff122faece4")
    void setParameterMultiplicityMax(int index, String value);

    @objid ("b67779f9-18e9-4587-8a4b-8450a0593a29")
    boolean isParameterModifiable(int index);

    @objid ("bb04b7af-4506-47e8-a0dc-5b9e2abe564f")
    boolean isReturnParameterModifiable();

    @objid ("ba1a4ca2-fd35-409c-8ee9-54116186439b")
    GeneralClass getReturnParameterType();

    @objid ("94e80e7a-34f4-4c29-870b-bfe6d1c981fc")
    String getReturnParameterMultiplicityMin();

    @objid ("0288ed07-bcb0-425a-8a8e-4a678ed336b4")
    String getReturnParameterMultiplicityMax();

    @objid ("f2670c30-2b23-4c31-9b0f-f84b0413cf98")
    void setReturnParameterType(GeneralClass value);

    @objid ("8a1e5397-b8c6-4054-80c7-d0cf03616ece")
    void setReturnParameterMultiplicityMin(String string);

    @objid ("85e7531d-b68c-46e9-b60a-c933ad140940")
    void setReturnParameterMultiplicityMax(String string);

    @objid ("dcaefb69-54d2-41af-a3fe-b66b7fdd27b2")
    void addParameter();

    @objid ("c77a840d-beb1-4dea-9093-c0c78c1dfc96")
    void removeParameter(List<Parameter> parameters);

    @objid ("7a1027a1-b4bc-4558-9682-5835778f38f3")
    Parameter getParameter(int index);

    @objid ("c40e1df8-0981-418c-bb66-cd1ef6e92a9f")
    void moveParametersDown(List<Parameter> parametersToMove);

    @objid ("ffe17950-c648-4a08-aa5a-62190f772a78")
    void moveParametersUp(List<Parameter> parametersToMove);

    @objid ("c71e1fcc-3f2f-4eb6-8833-553d50e8c330")
    int getParameterIndex(Parameter parameter);

    @objid ("5882dfb6-657b-4eb2-8144-fb0240de584b")
    Operation getOperation();

    @objid ("578c98f2-915b-441e-a219-044a40fb5abe")
    Parameter getReturnParameter();

    @objid ("ca26177c-43d3-4421-9768-9dd78c738e28")
    void setOperationType(String type);

    @objid ("9d9de9d6-7765-4580-b43a-1b9d97a477a4")
    String getOperationType();

    @objid ("e54221f6-0683-4ed0-a951-34e4fee792f4")
    List<ParameterPropertyModel> getParameters();

    @objid ("35d47dac-ded8-4c8d-bad6-2f50a3e5bebb")
    void addReturnParameter();

}
