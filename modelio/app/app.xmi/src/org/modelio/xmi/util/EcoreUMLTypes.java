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

package org.modelio.xmi.util;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This class made the mapping between Modelio predefined type and Ecore org.eclipse.uml2.uml.DataType
 * @author ebrosse
 */
@objid ("2d37f184-db8a-413a-894e-5d4deb6eb37e")
public class EcoreUMLTypes {
    @objid ("a14eeeba-b825-4bbf-8285-3de0f841c78a")
    private final String booleanEcoreName = "Boolean";

    @objid ("90d4a60a-3dda-4a79-aaa3-cb15b6f7f706")
    private final String integerEcoreName = "Integer";

    @objid ("2a587f49-dec9-4b53-81fe-d07069eff633")
    private final String stringEcoreName = "String";

    @objid ("e50dfb26-5a8d-4214-b74a-27292fdcd566")
    private final String unlimitedNaturalName = "UnlimitedNatural";

    @objid ("8c04bfe4-d3df-43b1-ae4c-572e01ac1c54")
    private org.eclipse.uml2.uml.PrimitiveType BOOLEAN = null;

    @objid ("f31a23cf-ca03-44ed-83c3-f93a2f08c265")
    private org.eclipse.uml2.uml.PrimitiveType STRING = null;

    @objid ("2c579106-5066-4851-a0aa-fe7ded7a02f8")
    private org.eclipse.uml2.uml.PrimitiveType INTEGER = null;

    @objid ("3c487ab8-ccd0-4aee-902f-4937c77453e0")
    private org.eclipse.uml2.uml.PrimitiveType UNLIMITED = null;

    @objid ("b04f043d-8217-423b-840c-5227d1aae01e")
    public EcoreUMLTypes() {
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the String Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'String' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("cdeb33c4-2cd8-45db-b1ec-f9b58c558d82")
    public org.eclipse.uml2.uml.PrimitiveType getString() {
        if (this.STRING == null){        
            this.STRING = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getUMLLibrary().getOwnedType(this.stringEcoreName);
        }
        return this.STRING;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Boolean Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Boolean' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("89afd048-acf0-4774-983a-d568bb035717")
    public org.eclipse.uml2.uml.PrimitiveType getBoolean() {
        if (this.BOOLEAN == null){
            this.BOOLEAN = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getUMLLibrary().getOwnedType(this.booleanEcoreName);
        }
        return this.BOOLEAN;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Integer Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Integer' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("95dc1bd0-ef31-45fc-9046-3f7ace9fa5b0")
    public org.eclipse.uml2.uml.PrimitiveType getInteger() {
        if (this.INTEGER == null){
            this.INTEGER = (org.eclipse.uml2.uml.PrimitiveType) UMLMetamodel.getInstance().getUMLLibrary().getOwnedType(this.integerEcoreName);
        }
        return this.INTEGER;
    }

    /**
     * This methods return the Ecore org.eclipse.uml2.uml.PrimitiveType associated to the Unlimited Modelio Predefined org.eclipse.uml2.uml.Type
     * 
     * @return the 'Unlimited' Ecore org.eclipse.uml2.uml.PrimitiveType
     */
    @objid ("917a075d-c728-43d7-87c7-4f4fcf53ddd3")
    public org.eclipse.uml2.uml.PrimitiveType getUnlimitedNatural() {
        if (this.UNLIMITED == null){
            this.UNLIMITED = (org.eclipse.uml2.uml.PrimitiveType ) UMLMetamodel.getInstance().getUMLLibrary().getOwnedType(this.unlimitedNaturalName);
        }
        return this.UNLIMITED;
    }

}
