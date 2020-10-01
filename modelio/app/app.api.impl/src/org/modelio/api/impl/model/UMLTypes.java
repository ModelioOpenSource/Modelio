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

package org.modelio.api.impl.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IUMLTypes;
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.vcore.session.api.model.IModel;

/**
 * This class give access to UML types managed by Modelio.
 * 
 * <p>
 * The accessible types are boolean, char, integer, real, string, undefined.
 * </p>
 * 
 * <p>
 * undefined type is used to set a type to property when the real type of the property is not known. (A property should not be left
 * without type)
 * </p>
 * 
 * <p>
 * Exemple:
 * </p>
 * <code>
 * UMLModel model = Modelio.getInstance().getModelingSession().getModel();<br>
 * DataType type = model.getUmlTypes().BOOLEAN;
 * </code>
 */
@objid ("a479c418-2555-435b-988e-8c32a6fba116")
public class UMLTypes implements IUMLTypes {
    @objid ("d63fda90-01bd-4a4a-ab3d-705c26bb150d")
    private IModel model;

    @objid ("5db2e093-c984-4026-b33a-2bf1597065b0")
    UMLTypes(final IModel model) {
        this.model = model;
    }

    @objid ("74683d8c-343e-46eb-80a6-85ac9f063315")
    @Override
    public DataType getBOOLEAN() {
        return this.model.findById(DataType.class, PredefinedTypes.BOOLEAN_UID);
    }

    @objid ("69c1603b-afc7-43e1-bcde-e70ea3f24a99")
    @Override
    public DataType getBYTE() {
        return this.model.findById(DataType.class, PredefinedTypes.BYTE_UID);
    }

    @objid ("21c3ef72-7578-4fe4-9a84-2730931975fd")
    @Override
    public DataType getCHAR() {
        return this.model.findById(DataType.class, PredefinedTypes.CHAR_UID);
    }

    @objid ("39681b1c-01b1-4c0d-a5ef-8341426df674")
    @Override
    public DataType getDATE() {
        return this.model.findById(DataType.class, PredefinedTypes.DATE_UID);
    }

    @objid ("ec0aa747-28b2-4718-a175-dd3d09e18aaa")
    @Override
    public DataType getDOUBLE() {
        return this.model.findById(DataType.class, PredefinedTypes.DOUBLE_UID);
    }

    @objid ("42b810a4-1b8f-4426-8a84-28e8b0229509")
    @Override
    public DataType getFLOAT() {
        return this.model.findById(DataType.class, PredefinedTypes.FLOAT_UID);
    }

    @objid ("6eb87156-ca9b-471a-b0d9-e7ec04a05f97")
    @Override
    public DataType getINTEGER() {
        return this.model.findById(DataType.class, PredefinedTypes.INTEGER_UID);
    }

    @objid ("10515ffe-2605-4a19-8b5d-351eab2fb18c")
    @Override
    public DataType getLONG() {
        return this.model.findById(DataType.class, PredefinedTypes.LONG_UID);
    }

    @objid ("2045a38c-c39b-454a-acde-a081d1872b7c")
    @Override
    public DataType getSHORT() {
        return this.model.findById(DataType.class, PredefinedTypes.SHORT_UID);
    }

    @objid ("c8a911bd-5d88-40ce-b8e2-679c5260e6cd")
    @Override
    public DataType getSTRING() {
        return this.model.findById(DataType.class, PredefinedTypes.STRING_UID);
    }

    @objid ("f4b757c1-fee2-4285-aa8b-b61cf554cde6")
    @Override
    public DataType getUNDEFINED() {
        return this.model.findById(DataType.class, PredefinedTypes.UNDEFINED_UID);
    }

}
