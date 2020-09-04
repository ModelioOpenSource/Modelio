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

package org.modelio.xmi.util;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IUMLTypes;
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

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
 */
@objid ("0b5cbd66-0f51-43ec-83ef-dc70ac0751ad")
public class ModelioTypes implements IUMLTypes {
    @objid ("9534b5de-9a2c-4ca6-9afe-180f4535ba91")
    private IMModelServices mmServices = null;

    @objid ("b7a6e7ef-4152-4962-aecc-271470043582")
    private final MClass dataTypeMClass;

    @objid ("475f738a-91a0-48e4-bb8f-17156a7a3fbd")
    public ModelioTypes(IMModelServices mmServices, MMetamodel metamodel) {
        this.mmServices = mmServices;
        this.dataTypeMClass = metamodel.getMClass(DataType.class);
    }

    @objid ("54fee4f6-0e45-450a-a9c3-bf045fa67c4a")
    @Override
    public DataType getBOOLEAN() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass, PredefinedTypes.BOOLEAN_UID);
    }

    @objid ("3bcd9c0f-4a0a-44e6-b6c4-485060954877")
    @Override
    public DataType getBYTE() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass, PredefinedTypes.BYTE_UID);
    }

    @objid ("7f2b85b2-029b-44bc-8e8c-75ca549d6c15")
    @Override
    public DataType getCHAR() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass, PredefinedTypes.CHAR_UID);
    }

    @objid ("adc61762-2ba6-4ef5-8b54-4d0fb951d0ed")
    @Override
    public DataType getDATE() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass, PredefinedTypes.DATE_UID);
    }

    @objid ("cd0f4cc0-dedb-40b9-a856-c0ecc4837c24")
    @Override
    public DataType getDOUBLE() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass, PredefinedTypes.DOUBLE_UID);
    }

    @objid ("99da8357-08d1-4466-8a08-88771168aa21")
    @Override
    public DataType getFLOAT() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass, PredefinedTypes.FLOAT_UID);
    }

    @objid ("0a86c116-df43-4861-a3ad-8d18226b7efa")
    @Override
    public DataType getINTEGER() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass, PredefinedTypes.INTEGER_UID);
    }

    @objid ("7414a385-4161-4310-99fe-8375576ed2f7")
    @Override
    public DataType getLONG() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass, PredefinedTypes.LONG_UID);
    }

    @objid ("feefaa94-ce55-4cf3-8210-7b66180b9d15")
    @Override
    public DataType getSHORT() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass,PredefinedTypes.SHORT_UID);
    }

    @objid ("fb65a0b6-9143-4fb4-9b17-fe709e6f536c")
    @Override
    public DataType getSTRING() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass, PredefinedTypes.STRING_UID);
    }

    @objid ("831887af-db6e-4307-a68e-96238bc74209")
    @Override
    public DataType getUNDEFINED() {
        return (DataType) this.mmServices.findById(this.dataTypeMClass, PredefinedTypes.UNDEFINED_UID);
    }

}
