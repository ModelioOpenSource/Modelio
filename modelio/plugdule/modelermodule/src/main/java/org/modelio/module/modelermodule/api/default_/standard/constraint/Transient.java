/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Constraint} with << transient >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("be1ed99b-3e3c-4bf1-87aa-3fdc95e547fe")
public class Transient {
    @objid ("2d06407b-ee52-4e8e-8f10-32f3dfbcd8a5")
    public static final String STEREOTYPE_NAME = "transient";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("3baa263b-217d-4989-8073-0c481a665f80")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Transient proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << transient >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c7f89131-0f02-41b9-861b-2dda4a4e6083")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Transient.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << transient >> then instantiate a {@link Transient} proxy.
     * 
     * @return a {@link Transient} proxy on the created {@link Constraint}.
     */
    @objid ("14f354cf-1753-4d33-abb5-9bba915e67e9")
    public static Transient create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Transient.STEREOTYPE_NAME);
        return Transient.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Transient} proxy from a {@link Constraint} stereotyped << transient >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Transient} proxy or <i>null</i>.
     */
    @objid ("8fa53b2a-3949-4b4b-b01a-ea111d9754ea")
    public static Transient instantiate(Constraint obj) {
        return Transient.canInstantiate(obj) ? new Transient(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Transient} proxy from a {@link Constraint} stereotyped << transient >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Transient} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5a09eed9-84a3-46e2-a038-2311aef5dde7")
    public static Transient safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Transient.canInstantiate(obj))
        	return new Transient(obj);
        else
        	throw new IllegalArgumentException("Transient: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("76242165-db4d-4d3e-a356-c6eb091585fb")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Transient other = (Transient) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("4640d343-1504-42f0-9ae3-d1bc2febbe24")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("57eced92-585d-44bc-b8e3-441713856bae")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f2527c0d-5fb0-4a59-8b32-753086a07bec")
    protected Transient(Constraint elt) {
        this.elt = elt;
    }

    @objid ("dd8d10f4-9b21-4aa7-810c-526be96a91dd")
    public static final class MdaTypes {
        @objid ("3f1fb264-60d8-46a2-9693-836858108098")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0f725879-fdd2-4357-8bf9-a5d457e2bae2")
        private static Stereotype MDAASSOCDEP;

        @objid ("5c1b37c5-a7f2-42e3-a27a-a6aab32e452f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("009d7cfb-fc6a-47b7-93aa-9c23d400313d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01fe-0000-000000000000");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
