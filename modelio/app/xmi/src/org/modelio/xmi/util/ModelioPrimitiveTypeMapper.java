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
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.xmi.generation.GenerationProperties;

/**
 * This class made the mapping between Modelio predefined type and Ecore org.eclipse.uml2.uml.DataType
 * @author ebrosse
 */
@objid ("21acda89-1645-4816-9b34-73e25bf2cf84")
public class ModelioPrimitiveTypeMapper {
    /**
     * This method returns the Ecore org.eclipse.uml2.uml.Type corresponding to a given Modelio Predefined org.eclipse.uml2.uml.Type
     * @param objingPredefinedType : the given Modelio Predefined org.eclipse.uml2.uml.Type
     * @return the matching Ecore org.eclipse.uml2.uml.Type
     */
    @objid ("a426f9fa-a327-4304-af9c-6d26d6c7756d")
    public static org.eclipse.uml2.uml.PrimitiveType getEcoreType(DataType objingPredefinedType) {
        org.eclipse.uml2.uml.PrimitiveType primitiveType = null;
        
        GenerationProperties genProp = GenerationProperties.getInstance();
        ModelioTypes modelioTypes = genProp.getModelioTypes();
        EcoreTypes ecoreTypes = genProp.getEcoreTypes();
        EcoreUMLTypes ecoreUmlTypes = genProp.getEcoreUMLTypes();
        
        if ((modelioTypes.getBOOLEAN() != null) && (modelioTypes.getBOOLEAN() .equals(objingPredefinedType))) {
            primitiveType = ecoreUmlTypes.getBoolean();
        }else if ((modelioTypes.getBYTE() != null) &&  (modelioTypes.getBYTE().equals(objingPredefinedType))) {
            primitiveType = ecoreTypes.getByte();
        } else if ((modelioTypes.getCHAR() != null) &&  (modelioTypes.getCHAR().equals(objingPredefinedType))) {
            primitiveType = ecoreTypes.getChar();
        }else if ((modelioTypes.getDOUBLE() != null) &&  modelioTypes.getDOUBLE().equals(objingPredefinedType)) {
            primitiveType = ecoreTypes.getDouble();
        }else if ((modelioTypes.getFLOAT() != null) &&  modelioTypes.getFLOAT().equals(objingPredefinedType)) {
            primitiveType = ecoreTypes.getFloat();
        }else if ((modelioTypes.getLONG() != null) &&  modelioTypes.getLONG().equals(objingPredefinedType)) {
            primitiveType = ecoreTypes.getLong();
        }else if ((modelioTypes.getSHORT() != null) &&  modelioTypes.getSHORT().equals(objingPredefinedType)) {
            primitiveType = ecoreTypes.getShort();
        } else if ((modelioTypes.getINTEGER() != null) &&  modelioTypes.getINTEGER().equals(objingPredefinedType)){
            primitiveType = ecoreUmlTypes.getInteger();
        }else if ((modelioTypes.getDATE() != null) &&  modelioTypes.getDATE().equals(objingPredefinedType)) {
            primitiveType = ecoreTypes.getDate();
        } else if ((modelioTypes.getSTRING() != null) &&  modelioTypes.getSTRING().equals(objingPredefinedType)){
            primitiveType = ecoreUmlTypes.getString();
        }else if ((modelioTypes.getUNDEFINED() != null) && modelioTypes.getUNDEFINED().equals(objingPredefinedType)) {      
            primitiveType = ecoreTypes.getUndefined();
        }else if (objingPredefinedType.getName().equals(genProp.getUnlimitedNaturalName())) {
            // Stores the original Modelio predefined type: "unlimitedNatural"
            primitiveType = ecoreUmlTypes.getUnlimitedNatural();
        }
        return primitiveType;
    }

    /**
     * This method types an Ecore org.eclipse.uml2.uml.TypedElement by a given Modelio org.eclipse.uml2.uml.DataType
     * @param typed : the Ecore org.eclipse.uml2.uml.TypedElement
     * @param objingPredefinedType : the Modelio org.eclipse.uml2.uml.DataType
     */
    @objid ("fa6337dc-63b4-4a66-80e0-5bbcdd9503b6")
    public static void setEcorePredefinedType(org.eclipse.uml2.uml.TypedElement typed, DataType objingPredefinedType) {
        org.eclipse.uml2.uml.PrimitiveType primitiveType = getEcoreType(objingPredefinedType);
        
        if (primitiveType != null)
            typed.setType(primitiveType);
    }

    /**
     * Test if a given GeneralClass is a Modelio Predefined org.eclipse.uml2.uml.Type
     * @param type : the tested GeneralClass
     * @return true if it is a Modelio Predefined org.eclipse.uml2.uml.Type
     */
    @objid ("f2bc1e7c-ba8c-4e14-931a-7516840a3811")
    public static boolean isPredefinedType(final GeneralClass type) {
        if (type != null) {
        
            GenerationProperties genProp = GenerationProperties.getInstance();
            ModelioTypes modelioTypes = genProp.getModelioTypes();
        
            if ((modelioTypes.getBOOLEAN()  != null) && (type.equals(modelioTypes.getBOOLEAN() )))
                return true;
            else if  ((modelioTypes.getCHAR() != null) && (type.equals(modelioTypes.getCHAR())))
                return true;
            else if ((modelioTypes.getINTEGER() != null) && (type.equals(modelioTypes.getINTEGER())))
                return true;
            else if ((modelioTypes.getBYTE() != null) && (type.equals(modelioTypes.getBYTE())))
                return true;
            else if ((modelioTypes.getDATE() != null) && (type.equals(modelioTypes.getDATE())))
                return true;
            else if ((modelioTypes.getDOUBLE() != null) && (type.equals(modelioTypes.getDOUBLE())))
                return true;
            else if ((modelioTypes.getFLOAT() != null) && (type.equals(modelioTypes.getFLOAT())))
                return true;
            else if ((modelioTypes.getLONG() != null) && (type.equals(modelioTypes.getLONG())))
                return true;
            else if ((modelioTypes.getSHORT() != null) && (type.equals(modelioTypes.getSHORT())))
                return true;
            else if ((modelioTypes.getSTRING() != null) && (type.equals(modelioTypes.getSTRING())))
                return true;
            else if ((modelioTypes.getUNDEFINED() != null) && (type.equals(modelioTypes.getUNDEFINED())))
                return true;
            else if (type.getName().equals(genProp.getUnlimitedNaturalName()))
                return true;
        }
        return false;
    }

}
