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
import org.eclipse.uml2.uml.internal.impl.ModelImpl;
import org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.xmi.reverse.ReverseProperties;

/**
 * This class made the mapping between Modelio predefined type and Ecore org.eclipse.uml2.uml.DataType
 * @author ebrosse
 */
@objid ("d293344b-a8e4-4815-91a1-495786604653")
public class EcorePrimitiveTypeMapper {
    @objid ("d6c8d583-9bef-4f93-89b0-bba6301a889f")
    private static final String booleanEcoreName = "Boolean";

    @objid ("cb357aee-820e-4e82-93b8-1e6d43454e1c")
    private static final String byteEcoreName = "EByte";

    @objid ("bb821708-30b1-4dc2-bc5f-5881150cde8a")
    private static final String charEcoreName = "EChar";

    @objid ("5c7932c5-beee-4ef2-b31f-cdbef0fc87e1")
    private static final String dateEcoreName = "EDate";

    @objid ("0cbf2dd0-6439-49d9-b797-390bb6fc20d0")
    private static final String doubleEcoreName = "EDouble";

    @objid ("54d1ce3d-4368-4dae-b651-218db697b712")
    private static final String floatEcoreName = "EFloat";

    @objid ("b906a5e0-4d4f-4a5d-88d1-b3065483db63")
    private static final String integerEcoreName = "Integer";

    @objid ("be92eac3-3574-4868-ae32-8a1c4ef0b14d")
    private static final String longEcoreName = "ELong";

    @objid ("2a0f3d7d-724a-4b5f-ba39-f862e677d2b8")
    private static final String shortEcoreName = "EShort";

    @objid ("33aa1740-b3c3-4d0e-946b-15ecbaf3c5e4")
    private static final String stringEcoreName = "String";

    @objid ("c87a2db9-020f-44e7-8715-9432a30a7eb4")
    private static final String unlimitedNaturalName = "UnlimitedNatural";

    @objid ("42a07211-e763-433b-8048-c080dd1faa94")
    private static final String eStringEcoreName = "EString";

    @objid ("afd6f17e-0efc-4150-8df9-a6412f85097b")
    private static final String folderName = "EcorePrimitiveTypes";

    /**
     * provide the Modelio org.eclipse.uml2.uml.DataType cooresponding to a given Ecore org.eclipse.uml2.uml.Type
     * @param ecoreType : the given Ecore org.eclipse.uml2.uml.Type
     * @return the corresponding Modelio org.eclipse.uml2.uml.DataType
     */
    @objid ("e477b643-33e0-41fc-92d0-a6a5b730beed")
    public static DataType getPredefinedType(final org.eclipse.uml2.uml.Type ecoreType) {
        ModelioTypes modelioTypes = ReverseProperties.getInstance().getModelioTypes();
        
        //Compute primitiveType name
        String currentEcoreTypeName = ecoreType.getName();
        
        if (currentEcoreTypeName == null) {
            if (ecoreType instanceof PrimitiveTypeImpl){
                currentEcoreTypeName = ((PrimitiveTypeImpl) ecoreType).eProxyURI().fragment();
            }else
                currentEcoreTypeName = ecoreType.eResource().getURI().fragment();
        }
        
               
        if ((ecoreType.eContainer() instanceof ModelImpl) 
                && (((ModelImpl) ecoreType.eContainer()).getName() != null)
                && ((ModelImpl) ecoreType.eContainer()).getName().equals(folderName)){
            
            if (currentEcoreTypeName.equals(byteEcoreName)){
                return modelioTypes.getBYTE();
            }else if (currentEcoreTypeName.equals(dateEcoreName)){
                return modelioTypes.getDATE();
            }else if (currentEcoreTypeName.equals(doubleEcoreName)){
                return modelioTypes.getDOUBLE();
            }else if (currentEcoreTypeName.equals(floatEcoreName)){
                return modelioTypes.getFLOAT();
            }else if (currentEcoreTypeName.equals(longEcoreName)){
                return modelioTypes.getLONG();
            }else if (currentEcoreTypeName.equals(shortEcoreName)){
                return modelioTypes.getSHORT();
            }else if (currentEcoreTypeName.equals(charEcoreName)){
                return modelioTypes.getCHAR();
            }else if (currentEcoreTypeName.equals(eStringEcoreName)){
                return modelioTypes.getSTRING();
            }else if (currentEcoreTypeName.equals(unlimitedNaturalName)){
                return ReverseProperties.getInstance().getUnlimitedNatural();
            }
        }
        
        if (currentEcoreTypeName != null) {
            
            currentEcoreTypeName = currentEcoreTypeName.toLowerCase();
            if (currentEcoreTypeName.equals(modelioTypes.getINTEGER().getName())){
                return modelioTypes.getINTEGER();
            }else if (currentEcoreTypeName.equals(modelioTypes.getBOOLEAN() .getName())){
                return modelioTypes.getBOOLEAN() ;
            }else if (currentEcoreTypeName.toLowerCase().equals(modelioTypes.getSTRING().getName())){
                return modelioTypes.getSTRING();
            }else if (currentEcoreTypeName.equals(modelioTypes.getBYTE().getName())){
                return modelioTypes.getBYTE();
            }else if (currentEcoreTypeName.equals(modelioTypes.getDATE().getName())){
                return modelioTypes.getDATE();
            }else if (currentEcoreTypeName.equals(modelioTypes.getDOUBLE().getName())){
                return modelioTypes.getDOUBLE();
            }else if ((currentEcoreTypeName.equals(modelioTypes.getFLOAT().getName()))){
                return modelioTypes.getFLOAT();
            }else if (currentEcoreTypeName.equals(modelioTypes.getLONG().getName())){
                return modelioTypes.getLONG();
            }else if (currentEcoreTypeName.equals(modelioTypes.getSTRING().getName())){
                return modelioTypes.getSTRING();
            }else if (currentEcoreTypeName.equals(modelioTypes.getSHORT().getName())){
                return modelioTypes.getSHORT();
            }else if (currentEcoreTypeName.equals(modelioTypes.getCHAR().getName())){
                return modelioTypes.getCHAR();
            }else if (currentEcoreTypeName.equals(modelioTypes.getUNDEFINED().getName())){
                return modelioTypes.getUNDEFINED();
            }else if (currentEcoreTypeName.equals(unlimitedNaturalName.toLowerCase())){
                return ReverseProperties.getInstance().getUnlimitedNatural();
            }
        }
        return null;
    }

    /**
     * Test if a given Ecore org.eclipse.uml2.uml.Type is a Modelio Predefined org.eclipse.uml2.uml.Type
     * @param ecoreType : the tested Ecore org.eclipse.uml2.uml.Type
     * @return true if the Ecore org.eclipse.uml2.uml.Type corresponds to a Modelio Predefined org.eclipse.uml2.uml.Type
     */
    @objid ("895bb432-1b54-42d5-af9a-88ae798c9b79")
    public static boolean isPredefinedType(final org.eclipse.uml2.uml.Type ecoreType) {
        String ecoreTypeName = ecoreType.getName();
        ModelioTypes modelioTypes = ReverseProperties.getInstance().getModelioTypes();
        String ecoreContainerName = "";
        
        if (ecoreType.getPackage() != null)
            ecoreContainerName = ecoreType.getPackage().getName();
        
        if (ecoreTypeName == null) {
            if (ecoreType instanceof PrimitiveTypeImpl){
                ecoreTypeName = ((PrimitiveTypeImpl) ecoreType).eProxyURI().fragment();
                ecoreContainerName = ((PrimitiveTypeImpl) ecoreType).eProxyURI().lastSegment();
            }else if (ecoreType.eResource() != null)
                ecoreTypeName = ecoreType.eResource().getURI().fragment();
        }
        
        if (ecoreTypeName != null) {
        
            if ((ecoreContainerName != null) && (ecoreContainerName.contains("EcorePrimitiveTypes"))){
                if ((ecoreTypeName.equals(byteEcoreName))
                        || (ecoreTypeName.equals(dateEcoreName))
                        || (ecoreTypeName.equals(doubleEcoreName))
                        || (ecoreTypeName.equals(floatEcoreName))
                        || (ecoreTypeName.equals(longEcoreName))
                        || (ecoreTypeName.equals(shortEcoreName))
                        || (ecoreTypeName.equals(charEcoreName))
                        || (ecoreTypeName.equals(eStringEcoreName))){
                    return true;
                }
            }
        
            ecoreTypeName = ecoreTypeName.toLowerCase();
        
            return  (((ecoreType instanceof org.eclipse.uml2.uml.PrimitiveType) || (ecoreType instanceof org.eclipse.uml2.uml.DataType) )
                    && ((ecoreTypeName.equals(modelioTypes.getBOOLEAN() .getName()))
                    || (ecoreTypeName.equals(modelioTypes.getBYTE().getName()))
                    || (ecoreTypeName.equals(modelioTypes.getDATE().getName()))
                    || (ecoreTypeName.equals(modelioTypes.getDOUBLE().getName()))
                    || (ecoreTypeName.equals(modelioTypes.getFLOAT().getName()))
                    || (ecoreTypeName.equals(modelioTypes.getLONG().getName()))
                    || (ecoreTypeName.equals(modelioTypes.getSHORT().getName()))
                    || (ecoreTypeName.equals(modelioTypes.getCHAR().getName()))
                    || (ecoreTypeName.equals(modelioTypes.getINTEGER().getName()))
                    || (ecoreTypeName.equals(modelioTypes.getSTRING().getName()))
                    || (ecoreTypeName.equals(modelioTypes.getUNDEFINED().getName()))
                    || (ecoreTypeName.equals(integerEcoreName))
                    || (ecoreTypeName.equals(booleanEcoreName))
                    || (ecoreTypeName.equals(stringEcoreName))
                    || (ecoreTypeName.equals(unlimitedNaturalName.toLowerCase()))));
        }
        return false;
    }

    /**
     * @param ecoreType : the tested org.eclipse.uml2.uml.Type
     * @return true if the test org.eclipse.uml2.uml.Type is the Boolean org.eclipse.uml2.uml.Type
     */
    @objid ("93df5ad6-7b32-4b25-aa5c-6fb45a00e96f")
    public static boolean isBoolean(final org.eclipse.uml2.uml.Type ecoreType) {
        if (isPredefinedType(ecoreType)){
            DataType obType = getPredefinedType(ecoreType);
            return  (obType.equals(ReverseProperties.getInstance().getModelioTypes().getBOOLEAN() ));
        }
        return false;
    }

}
