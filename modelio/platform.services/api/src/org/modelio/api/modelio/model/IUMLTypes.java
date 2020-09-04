/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.DataType;

/**
 * This interface give access to UML types managed by Modelio.
 * 
 * <p>The accessibles types are boolean, byte, char, date, double, float, integer, long, short, string, undefined.</p>
 * 
 * <p>undefined type is used to set a type to property when the real type of the property is not known. (A property should not be left without type)</p>
 * 
 * <p>Exemple:</p>
 * <code>
 * IUmlModel model = Modelio.getInstance().getModelingSession().getModel();<br>
 * DataType type = model.getUmlTypes().getBOOLEAN();
 * </code>
 */
@objid ("18703007-6450-11e0-b650-001ec947cd2a")
public interface IUMLTypes {
    /**
     * @return the DataType that represent the UML "boolean" type.
     */
    @objid ("a3d66412-0ecc-11e2-96c4-002564c97630")
    DataType getBOOLEAN();

    /**
     * @return the DataType that represent the UML "byte" type.
     */
    @objid ("a3d68b23-0ecc-11e2-96c4-002564c97630")
    DataType getBYTE();

    /**
     * @return the DataType that represent the UML "char" type.
     */
    @objid ("a3d68b26-0ecc-11e2-96c4-002564c97630")
    DataType getCHAR();

    /**
     * @return the DataType that represent the UML "date" type.
     */
    @objid ("a3d68b29-0ecc-11e2-96c4-002564c97630")
    DataType getDATE();

    /**
     * @return the DataType that represent the UML "double" type.
     */
    @objid ("a3d6b236-0ecc-11e2-96c4-002564c97630")
    DataType getDOUBLE();

    /**
     * @return the DataType that represent the UML "float" type.
     */
    @objid ("a3d6b239-0ecc-11e2-96c4-002564c97630")
    DataType getFLOAT();

    /**
     * @return the DataType that represent the UML "integer" type.
     */
    @objid ("a3d6d945-0ecc-11e2-96c4-002564c97630")
    DataType getINTEGER();

    /**
     * @return the DataType that represent the UML "long" type.
     */
    @objid ("a3d6d948-0ecc-11e2-96c4-002564c97630")
    DataType getLONG();

    /**
     * @return the DataType that represent the UML "short" type.
     */
    @objid ("a3d6d94b-0ecc-11e2-96c4-002564c97630")
    DataType getSHORT();

    /**
     * @return the DataType that represent the UML "string" type.
     */
    @objid ("a3d70058-0ecc-11e2-96c4-002564c97630")
    DataType getSTRING();

    /**
     * @return the DataType that represent the UML "undefined" type.
     */
    @objid ("a3d7005b-0ecc-11e2-96c4-002564c97630")
    DataType getUNDEFINED();

}
