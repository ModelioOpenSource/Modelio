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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.uml.infrastructure.properties;

import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;

/**
 * PropertyTable v0.0.9054
 * 
 * 
 * <p>A property table has a name and a stores a key/value map.</p>
 * 
 * 
 * 
 */
@objid ("00688c1a-ec87-1098-b22e-001ec947cd2a")
public interface PropertyTable extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("a50e0bb3-6e3c-4af4-8238-18c0304c3a11")
    public static final String MNAME = "PropertyTable";

    /**
     * The metaclass qualified name.
     */
    @objid ("bbdf3f7c-75c0-43fb-88a7-99526ce320b8")
    public static final String MQNAME = "Infrastructure.PropertyTable";

    /**
     * Get a property value.
     * @param key a property
     * @return its value or <code>null</code>.
     */
    @objid ("5652fdad-2826-11e2-bf07-001ec947ccaf")
    String getProperty(String key);

    /**
     * Set the property table content.
     * @param newContent the new table content.
     */
    @objid ("12071b01-282d-11e2-bf07-001ec947ccaf")
    void setContent(Properties newContent);

    /**
     * Set a property value.
     * @param key a property.
     * @param value its value.
     */
    @objid ("5652fdb0-2826-11e2-bf07-001ec947ccaf")
    void setProperty(String key, String value);

    /**
     * Get a copy of the property table content.
     * <p>
     * Modifying the returned Properties will not have any effect on
     * the PropertyTable.
     * @return a copy of the properties.
     * @throws IllegalStateException if the 'Content' attribute has an illegal format.
     * This can only happen if {@link #setContent(String)} is called directly.
     */
    @objid ("5652fdb3-2826-11e2-bf07-001ec947ccaf")
    Properties toProperties() throws IllegalStateException;

    /**
     * Getter for attribute 'PropertyTable.name'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a874b4b6-8fdd-472c-9eb2-1274e0d9e401")
    String getName();

    /**
     * Setter for attribute 'PropertyTable.name'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("3d7d5669-4e20-4fe6-8083-dcbe314d514c")
    void setName(String value);

    /**
     * Getter for attribute 'PropertyTable.content'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("96693597-9d82-4b9f-9fc6-90387b431b7a")
    String getContent();

    /**
     * Setter for attribute 'PropertyTable.content'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("cb4427a0-30e9-4219-bb00-25a81501e984")
    void setContent(String value);

    /**
     * Getter for relation 'PropertyTable->OwnerValDef'
     * 
     * Metamodel description:
     * <i>Matrix value definition owning the property table.</i>
     * 
     */
    @objid ("65f9785b-0876-4e21-a049-97c5e3bfee65")
    MatrixValueDefinition getOwnerValDef();

    /**
     * Setter for relation 'PropertyTable->OwnerValDef'
     * 
     * Metamodel description:
     * <i>Matrix value definition owning the property table.</i>
     * 
     */
    @objid ("900bd246-84ef-4ffe-8ae7-5917a3bbbd4f")
    void setOwnerValDef(MatrixValueDefinition value);

    /**
     * Getter for relation 'PropertyTable->OwnerQuery'
     * 
     * Metamodel description:
     * <i>Query owner</i>
     * 
     */
    @objid ("b135b465-88ad-4869-96c6-21a9f4c8b8b0")
    QueryDefinition getOwnerQuery();

    /**
     * Setter for relation 'PropertyTable->OwnerQuery'
     * 
     * Metamodel description:
     * <i>Query owner</i>
     * 
     */
    @objid ("06fb1265-9057-48c1-a1aa-4b4e43392216")
    void setOwnerQuery(QueryDefinition value);

    /**
     * Getter for relation 'PropertyTable->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("16f96e8b-6b5f-4ef2-b50e-e28c4acd5cec")
    ModelElement getOwner();

    /**
     * Setter for relation 'PropertyTable->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1e41cf9c-cdc2-4c94-875f-5260d932d06f")
    void setOwner(ModelElement value);
}

