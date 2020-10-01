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

package org.modelio.vcore.smkernel.meta;

import java.util.Arrays;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.EAttribute;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * Cette classe permet d'acceder a un attribut d'un objet.
 * <p>
 * Elle verifie la coherence du type.
 * Elle provoque une erreur si le type est errone.
 * <p>
 * Generation de definition d'un attribut en Java. La definition d'un attribut d'un element semantique se traduit par la
 * creation d'une instance de la classe SmAttribut en Java.
 * <p>
 */
@objid ("008436ae-ed97-1f1f-85a5-001ec947cd2a")
public abstract class SmAttribute extends SmFeature implements MAttribute {
    @objid ("3d4dbd47-2f3f-11e2-8359-001ec947ccaf")
    private boolean isNameAtt;

    /**
     * The SmClass instance owning the attribute
     */
    @objid ("00841ad4-ed97-1f1f-85a5-001ec947cd2a")
    private SmClass owner;

    @objid ("778c4987-9b64-11e1-94a3-001ec947ccaf")
    private Class<?> type;

    /**
     * The EMF attribute.
     */
    @objid ("704d8856-3e62-4179-8ee1-9e1afefd59d2")
    private EAttribute emfAdapter;

    @objid ("00841a34-ed97-1f1f-85a5-001ec947cd2a")
    protected SmAttribute() {
    }

    @objid ("0053762c-df7d-1fe9-93a7-001ec947cd2a")
    @Override
    public void assertValueType(final SmObjectImpl smObjectImpl, final Object value) {
        if (!this.type.isInstance(value)) {
            throw new IllegalArgumentException(value + " is not a " + this.type.getSimpleName());
        }
    }

    /**
     * Get the EMF adapter for this attribute.
     * 
     * @return The EMF {@link EAttribute}.
     */
    @objid ("ef779bcd-bea9-11e1-b576-001ec947ccaf")
    public EAttribute getEmfAdapter() {
        return this.emfAdapter;
    }

    /**
     * @return the class owner.
     */
    @objid ("00841840-ed97-1f1f-85a5-001ec947cd2a")
    public SmClass getOwner() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.owner;
    }

    @objid ("0091dc14-eb1b-1f22-8c06-001ec947cd2a")
    @Override
    public Class<?> getType() {
        return this.type;
    }

    /**
     * Get the attribute value on the given model object.
     * 
     * @param object the model object
     * @return the attribute value
     */
    @objid ("008bac2c-0a97-1f20-85a5-001ec947cd2a")
    public abstract Object getValue(final ISmObjectData object);

    /**
     * Initialize the attribute.
     * 
     * @param name the attribute name
     * @param owner the owner
     * @param type the attribute type
     * @param flags the directives.
     */
    @objid ("0084199e-ed97-1f1f-85a5-001ec947cd2a")
    @SuppressWarnings("hiding")
    public final void init(final String name, final SmClass owner, Class<?> type, SmDirective... flags) {
        init(name, owner, type, Arrays.asList(flags));
    }

    /**
     * Initialize the EMF attribute adapter.
     * 
     * @param emfAdapter the EMF attribute.
     */
    @objid ("ef779bd2-bea9-11e1-b576-001ec947ccaf")
    public void setEmfAdapter(EAttribute emfAdapter) {
        this.emfAdapter = emfAdapter;
    }

    /**
     * Set the attribute value on the given model object data.
     * 
     * @param object a model object data
     * @param value the new attribute value
     */
    @objid ("008bc450-0a97-1f20-85a5-001ec947cd2a")
    public abstract void setValue(final ISmObjectData object, final Object value);

    @objid ("00923542-eb1b-1f22-8c06-001ec947cd2a")
    @Override
    public String toString() {
        return getName() + ": " + getType().getSimpleName() + " [" + getMin() + ".." + getMax() + "]";
    }

    /**
     * Tells whether this attribute is a name attribute.
     * <p>
     * The name of the attribute is compared to "name" case insensitively.
     * 
     * @return <code>true</code> if the attribute is a name attribute.
     */
    @objid ("3d4dbd4e-2f3f-11e2-8359-001ec947ccaf")
    public boolean isNameAtt() {
        return this.isNameAtt;
    }

    /**
     * Initialize the attribute.
     * 
     * @param name the attribute name
     * @param owner the owner
     * @param type the attribute type
     * @param flags the directives.
     */
    @objid ("4a7d2ec6-ac08-4f4e-9012-70e2c0530978")
    @SuppressWarnings("hiding")
    public void init(final String name, final SmClass owner, Class<?> type, Collection<SmDirective> flags) {
        setName(name);
        // phv: semantic attribute cannot be multiple for now (Jan 2012)
        setMin(0);
        setMax(1);
        this.owner = owner;
        this.type = type;
        this.isNameAtt = name.equalsIgnoreCase("name");
        initSmFlags(flags);
    }

}
