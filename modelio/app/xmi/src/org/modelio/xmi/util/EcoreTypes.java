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

package org.modelio.xmi.util;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.xmi.api.FormatExport;
import org.modelio.xmi.generation.GenerationProperties;

/**
 * This class made the mapping between Modelio predefined type and Ecore org.eclipse.uml2.uml.DataType
 * @author ebrosse
 */
@objid ("3bde5d61-890e-4db5-ae5e-bb6be6daf9ab")
public class EcoreTypes {
    @objid ("0d6e0dc8-50e0-4e68-b1d7-0e5bb2807c1f")
    private final String byteEcoreName = "EByte";

    @objid ("bb52081e-b7db-4aea-b7fb-3315992d8822")
    private final String charEcoreName = "EChar";

    @objid ("38247e1f-8a76-4787-9a48-739134f8d854")
    private final String dateEcoreName = "EDate";

    @objid ("4825d427-992e-43a5-a51d-fd23bd0f0d76")
    private final String doubleEcoreName = "EDouble";

    @objid ("e94bb146-33d0-4df1-9cfc-c71b02456203")
    private final String floatEcoreName = "EFloat";

    @objid ("4517774d-3f73-4646-8e80-9cd42a9c759c")
    private final String longEcoreName = "ELong";

    @objid ("c1fe226c-72e7-4df3-9360-55105ec43a8f")
    private final String shortEcoreName = "EShort";

    @objid ("82f89f53-bb7b-45d2-94c2-426947d9a67f")
    private final String eStringEcoreName = "EString";

    @objid ("92b9ab0e-08e7-41a9-a260-f05f9e47c5e9")
    private FormatExport formatExport = null;

    @objid ("63bc8320-a0c4-404a-8f30-2b279970a26b")
    private List<org.eclipse.uml2.uml.PrimitiveType> predefinedType = new ArrayList<>();

    @objid ("74b05b0b-3cd2-45d2-a9bc-8bb0664f8851")
    private org.eclipse.uml2.uml.PrimitiveType ESTRING = null;

    @objid ("4c494fda-0760-4b0c-8c69-9f92254d99ec")
    private org.eclipse.uml2.uml.PrimitiveType DATE = null;

    @objid ("604f3745-ee47-48c3-915b-d31081a8154d")
    private org.eclipse.uml2.uml.PrimitiveType FLOAT = null;

    @objid ("e4f2e757-f781-48d5-a920-f4cc93f270d5")
    private org.eclipse.uml2.uml.PrimitiveType SHORT = null;

    @objid ("853855d5-3826-46cd-a61d-51495d8541d6")
    private org.eclipse.uml2.uml.PrimitiveType CHAR = null;

    @objid ("acd466cc-a037-48c0-964b-388d4847afb8")
    private org.eclipse.uml2.uml.PrimitiveType LONG = null;

    @objid ("8f7d2be0-5892-4520-bc3a-5dde8dd5603a")
    private org.eclipse.uml2.uml.PrimitiveType BYTE = null;

    @objid ("d179f654-1810-417a-a546-e0c2ac5d213d")
    private org.eclipse.uml2.uml.PrimitiveType UNDEFINED = null;

    @objid ("ea6f0d09-a4b1-434e-8b53-d8751d354d57")
    private org.eclipse.uml2.uml.PrimitiveType DOUBLE = null;

    @objid ("04cfbf0d-e541-458d-b8b2-a3a238c8d2c8")
    public EcoreTypes(FormatExport formatExport) {
        this.formatExport = formatExport;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Byte Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Byte' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("2caa9669-beea-4bbd-8e99-72c49963f13e")
    public org.eclipse.uml2.uml.PrimitiveType getByte() {
        if (this.BYTE == null){
            if (this.formatExport.equals(FormatExport.EMF300)){
                this.BYTE = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getEcoreLibrary().getOwnedType(this.byteEcoreName);
            }else{
                this.BYTE = UMLFactory.eINSTANCE.createPrimitiveType();
                this.BYTE.setName(GenerationProperties.getInstance().getModelioTypes().getBYTE().getName());
                this.predefinedType.add(this.BYTE);
            }
        
        }
        return this.BYTE;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Char Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Char' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("0db97b7d-66a4-4cee-b9b9-4fbae1550e08")
    public org.eclipse.uml2.uml.PrimitiveType getChar() {
        if (this.CHAR == null){      
            if (this.formatExport.equals(FormatExport.EMF300)){
                this.CHAR = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getEcoreLibrary().getOwnedType(this.charEcoreName);
            }else{
                this.CHAR = UMLFactory.eINSTANCE.createPrimitiveType();
                this.CHAR.setName(GenerationProperties.getInstance().getModelioTypes().getCHAR().getName());
                this.predefinedType.add(this.CHAR);
            }
        }
        return this.CHAR;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Date Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Date' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("a819a838-b827-4ed4-b29d-ea11d96e34fd")
    public org.eclipse.uml2.uml.PrimitiveType getDate() {
        if (this.DATE == null){
            if (this.formatExport.equals(FormatExport.EMF300)){
                this.DATE = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getEcoreLibrary().getOwnedType(this.dateEcoreName);
            }else{
                this.DATE = UMLFactory.eINSTANCE.createPrimitiveType();
                this.DATE.setName(GenerationProperties.getInstance().getModelioTypes().getDATE().getName());
                this.predefinedType.add(this.DATE);
            }
        }
        return this.DATE;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Double Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Double' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("2626b422-2223-4df3-a0a2-4ae90e02ca46")
    public org.eclipse.uml2.uml.PrimitiveType getDouble() {
        if (this.DOUBLE == null){
            if (this.formatExport.equals(FormatExport.EMF300)){
                this.DOUBLE = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getEcoreLibrary().getOwnedType(this.doubleEcoreName);
            }else{
                this.DOUBLE = UMLFactory.eINSTANCE.createPrimitiveType();
                this.DOUBLE.setName(GenerationProperties.getInstance().getModelioTypes().getDOUBLE().getName());
                this.predefinedType.add(this.DOUBLE);
            }
        }
        return this.DOUBLE;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Float Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Float' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("19fa753a-2ab5-4d6f-a245-3b95ccd3ac65")
    public org.eclipse.uml2.uml.PrimitiveType getFloat() {
        if (this.FLOAT == null){
            if (this.formatExport.equals(FormatExport.EMF300)){
                this. FLOAT = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getEcoreLibrary().getOwnedType(this.floatEcoreName);
            }else{
                this.FLOAT = UMLFactory.eINSTANCE.createPrimitiveType();
                this.FLOAT.setName(GenerationProperties.getInstance().getModelioTypes().getFLOAT().getName());
                this.predefinedType.add(this.FLOAT);
            }
        }
        return this.FLOAT;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Long Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Long' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("cd0f2a7f-21ac-4106-bacf-1a9d38027a73")
    public org.eclipse.uml2.uml.PrimitiveType getLong() {
        if (this.LONG == null){
            if (this.formatExport.equals(FormatExport.EMF300)){
                this.LONG = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getEcoreLibrary().getOwnedType(this.longEcoreName);
            }else{
                this.LONG = UMLFactory.eINSTANCE.createPrimitiveType();
                this.LONG.setName(GenerationProperties.getInstance().getModelioTypes().getLONG().getName());
                this.predefinedType.add(this.LONG);
            }
        }
        return this.LONG;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Short Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Short' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("b1086453-6bfa-4022-813c-a3e612e89920")
    public org.eclipse.uml2.uml.PrimitiveType getShort() {
        if (this.SHORT == null){
            if (this.formatExport.equals(FormatExport.EMF300)){
                this.SHORT = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getEcoreLibrary().getOwnedType(this.shortEcoreName);
            }else{
                this. SHORT = UMLFactory.eINSTANCE.createPrimitiveType();
                this.SHORT.setName(GenerationProperties.getInstance().getModelioTypes().getSHORT().getName());
                this.predefinedType.add(this.SHORT);
            }
        }
        return this.SHORT;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Undefined Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Undefined' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("43eb5753-65c5-40de-bbea-6961f4ab1337")
    public org.eclipse.uml2.uml.PrimitiveType getUndefined() {
        if (this.UNDEFINED == null){
            this.UNDEFINED = UMLFactory.eINSTANCE.createPrimitiveType();
            this.UNDEFINED.setName(GenerationProperties.getInstance().getModelioTypes().getUNDEFINED().getName());
            this.predefinedType.add(this.UNDEFINED);
        }
        return this.UNDEFINED;
    }

    /**
     * This method returns a list containing all Ecore Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the list of all Ecore Predefined org.eclipse.uml2.uml.Type
     */
    @objid ("924620d6-61e4-441f-97d8-60c7a075744d")
    public List<org.eclipse.uml2.uml.PrimitiveType> getPredifinedTypeList() {
        return this.predefinedType;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the String Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'String' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("02a37901-7abb-45ec-aa70-8bfeabad5728")
    public org.eclipse.uml2.uml.PrimitiveType getEString() {
        if (this.ESTRING == null){        
            this.ESTRING = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getEcoreLibrary().getOwnedType(this.eStringEcoreName);
        }
        return this.ESTRING;
    }

}
