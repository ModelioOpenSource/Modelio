/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
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
 * Proxy class to handle a {@link Dependency} with << manifestation >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("21713da7-5de8-4464-8371-298380f8461d")
public class Manifestation {
    @objid ("be2d12dd-7130-4953-bf88-c10fa14af1d9")
    public static final String STEREOTYPE_NAME = "manifestation";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("0a97e1ab-1d02-46f0-87bf-bc163e8975d7")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Manifestation proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << manifestation >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("673a4b79-d519-4a03-8f98-9537473bd04d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Manifestation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << manifestation >> then instantiate a {@link Manifestation} proxy.
     * 
     * @return a {@link Manifestation} proxy on the created {@link Dependency}.
     */
    @objid ("723a4956-87c1-4bdd-adcf-5db72a00e3d0")
    public static Manifestation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Manifestation.STEREOTYPE_NAME);
        return Manifestation.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Manifestation} proxy from a {@link Dependency} stereotyped << manifestation >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Manifestation} proxy or <i>null</i>.
     */
    @objid ("2dd20dd4-cc5e-490f-beeb-76118fbc62a9")
    public static Manifestation instantiate(Dependency obj) {
        return Manifestation.canInstantiate(obj) ? new Manifestation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Manifestation} proxy from a {@link Dependency} stereotyped << manifestation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Manifestation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e4786baa-9147-43d1-8640-3e2a37c226df")
    public static Manifestation safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Manifestation.canInstantiate(obj))
        	return new Manifestation(obj);
        else
        	throw new IllegalArgumentException("Manifestation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c1da8776-da3b-42c2-948f-884ac522fa28")
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
        Manifestation other = (Manifestation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("fa4fdafa-d87a-4110-962b-5c68f6a604fb")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8d539e38-f9ca-4827-ac8e-cdc94bb2506f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5cb94d7d-9054-46b4-a98c-09419193f4ac")
    protected Manifestation(Dependency elt) {
        this.elt = elt;
    }

    @objid ("a2578fb0-0629-4fb7-8870-be21ae2eb03a")
    public static final class MdaTypes {
        @objid ("3ca07f9f-9573-47e2-a2a0-1709a388c8c4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ff866a71-2fd8-4e69-8d52-45a2a1d0f7ef")
        private static Stereotype MDAASSOCDEP;

        @objid ("a5df4a86-d8bf-47fa-9a0b-b0b08bc31080")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8e5a53ea-7015-42be-883c-30282cd9b3be")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "d5bccf8e-79b3-48df-8c79-09200aa52d19");
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
