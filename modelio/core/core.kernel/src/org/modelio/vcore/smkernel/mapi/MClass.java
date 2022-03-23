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
package org.modelio.vcore.smkernel.mapi;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

/**
 * This interface defines the services available to programmers outside 'vcore' for handling SmClass instances.
 * <p>
 * The MClass services are obviously a subset of those provided by SmClass. This is because external programmers are not expected to
 * modify the metamodel and therefore only limited 'get-like' accessors are provided.
 * <p>
 * The metaclass qualified name is the concatenation of the metamodel fragment, the '.' character
 * and the metaclass name.
 * <p>
 * It is advised to use the qualified name when looking for a metaclass in the case
 * several metamodel fragments define a metaclass with the same name.
 * 
 * @author phv
 */
@objid ("00433ca8-2596-1ffc-8433-001ec947cd2a")
public interface MClass {
    /**
     * qualified name separator between the fragment name and the metaclass name.
     */
    @objid ("08b83d05-64c6-4a5b-b260-e1b625350cd7")
    public static final char QUALIFIER_SEP = '.';

    /**
     * Tells whether this metaclass may have orphan model objects.
     * @since 3.6
     * @return true if orphan are allowed.
     */
    @objid ("91420a3a-15fa-40b0-a8f6-e3a208066a58")
    boolean areOrphansAllowed();

    /**
     * Get the attribute with the given name.
     * @param name an attribute name.
     * @return the found attribute or <code>null</code>.
     */
    @objid ("00903b16-2ef5-1ffc-8433-001ec947cd2a")
    MAttribute getAttribute(String name);

    /**
     * Get the class attributes.
     * @param includeInherited <code>true</code> to include inherited attributes.
     * @return the defined attributes.
     */
    @objid ("0090bee2-2ef5-1ffc-8433-001ec947cd2a")
    List<MAttribute> getAttributes(boolean includeInherited);

    /**
     * Get all defined metamodel dependencies
     * @param includeInherited <code>true</code> to include inherited metamodel dependency.
     * @return metamodel dependencies.
     */
    @objid ("00905b0a-2ef5-1ffc-8433-001ec947cd2a")
    List<MDependency> getDependencies(boolean includeInherited);

    /**
     * Get the metamodel dependency with the given name.
     * @param name a metamodel dependency name.
     * @return the found metamodel dependency or <code>null</code>.
     */
    @objid ("00904e8a-2ef5-1ffc-8433-001ec947cd2a")
    MDependency getDependency(String name);

    /**
     * Get the interface implemented by all objects of this metaclass.
     * @return The java interface.
     */
    @objid ("cc7e14c6-03c7-411e-92f8-8b183d33a64f")
    Class<? extends MObject> getJavaInterface();

    /**
     * Get for a link metaclass all source dependencies.
     * <p>
     * Also return inherited source dependencies.
     * Returns an empty collection for node metaclasses.
     * @return all source dependencies.
     */
    @objid ("5acec83c-4797-4ce7-b4cd-995609eef41b")
    Collection<MDependency> getLinkMetaclassSources();

    /**
     * Get for a link metaclass all target dependencies.
     * <p>
     * Also return inherited target dependencies.
     * Returns an empty collection for node metaclasses.
     * @return all target dependencies.
     */
    @objid ("1c8cfce7-eb23-4618-9965-98357f5a4060")
    Collection<MDependency> getLinkMetaclassTargets();

    /**
     * Return the metamodel owning this metaclass (the metamodel who loaded it and registered its providing fragment)
     * @return the owning metamodel.
     */
    @objid ("6cb897ec-393d-43e2-9fa4-3cb18a727f12")
    MMetamodel getMetamodel();

    /**
     * @return the metaclass name.
     */
    @objid ("0090268a-2ef5-1ffc-8433-001ec947cd2a")
    String getName();

    /**
     * Get the metamodel fragment providing this metaclass.
     * @return the metamodel fragment.
     */
    @objid ("2425df28-8987-488e-830f-e73e96485ce4")
    MMetamodelFragment getOrigin();

    /**
     * Get the metaclass qualified name.
     * <p>
     * The metaclass qualified name is the concatenation of the metamodel fragment,
     * the '.' character and the metaclass name.
     * <p>
     * It is advised to use the qualified name when looking for a metaclass in the case
     * several metamodel fragments define a metaclass with the same name.
     * @return the metaclass qualified name.
     */
    @objid ("b28da2d6-3d9e-49d2-9821-cf3b98e4c255")
    String getQualifiedName();

    /**
     * Get the sub classes.
     * @param recursive <code>true</code> to include all sub classe recursively.
     * @return the sub classes.
     */
    @objid ("0090e0fc-2ef5-1ffc-8433-001ec947cd2a")
    List<MClass> getSub(boolean recursive);

    /**
     * Get the parent metamodel class.
     * @return the parent class.
     */
    @objid ("0090d86e-2ef5-1ffc-8433-001ec947cd2a")
    MClass getSuper();

    /**
     * Get the metaclass version.
     * <p>
     * This may be used to detect changes in the metamodel
     * @return the metaclass versions.
     */
    @objid ("aa5bae66-0a12-47d7-84ed-df62399b9cd7")
    Version getVersion();

    /**
     * Check that <code>this</code> metaclass is a sub-metaclass of <code>parent</code> metaclass.
     * @param parent a metamodel class
     * @return <code>true</code> if <code>this</code> class inherits from the given class. <code>false</code> otherwise.
     */
    @objid ("497adf3c-9db8-4e20-8fc9-4f8ee2846981")
    boolean hasBase(MClass parent);

    /**
     * @return <code>true</code> if the class is abstract.
     */
    @objid ("d54ee427-137e-11e2-816a-001ec947ccaf")
    boolean isAbstract();

    /**
     * Tells whether this metamodel class is a CMS node.
     * <p>
     * CMS nodes define a structuring unit for some storages implementations. For EXML repositories, a CMS node represents an EXML
     * file.
     * @return <code>true</code> if the class is a CMS node.
     */
    @objid ("d54ee428-137e-11e2-816a-001ec947ccaf")
    boolean isCmsNode();

    /**
     * Tells whether this metaclass is a fake metaclass.
     * <p>
     * A fake metaclass is a shell metaclass that represents a missing metaclass.
     * Fake metaclass model objects are all shell objects.
     * @return true if this metaclass is fake.
     */
    @objid ("a67fa0ec-b321-4161-9c3f-29dce2586ca9")
    boolean isFake();

    /**
     * Tells whether this metaclass is a relationship metaclass.
     * <p>
     * A relationship metaclass elements represents links between other objects. They have source and target MDependencies.
     * @since toutatis
     * @return true if this metaclass is fake.
     */
    @objid ("8c58b4f1-1361-4b3d-9008-ad5fac903970")
    boolean isLinkMetaclass();

    @objid ("0ab11eb6-311d-4e31-aacd-a8d5d60d6d12")
    boolean isEnabled();

    /**
     * Tells whether the model object is an instance of this metaclass or a sub metaclass.
     * @param obj a model object or null.
     * @return true if the model object is an instance of this metaclass.
     */
    @objid ("0c56ec51-e600-480d-96a0-18bc3da7669f")
    default boolean isInstance(MObject obj) {
        return obj != null && obj.getMClass().hasBase(this);
    }

}
