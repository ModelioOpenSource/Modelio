/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << refine >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("065ef2c0-b7f4-4881-9acb-45b54c44a00d")
public class Refine {
    @objid ("b6022a1f-c878-4ea3-b71b-3add3cd4ce0a")
    public static final String STEREOTYPE_NAME = "refine";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("ade06f7b-6adb-45bb-b3ba-ebc126c61dba")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Refine proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << refine >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("af116f3f-b6b0-4ca1-afcf-1ebe55db8d29")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Refine.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << refine >> then instantiate a {@link Refine} proxy.
     * 
     * @return a {@link Refine} proxy on the created {@link Dependency}.
     */
    @objid ("ce66dbab-f870-4273-b1e6-2b199b01815f")
    public static Refine create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Refine.STEREOTYPE_NAME);
        return Refine.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Refine} proxy from a {@link Dependency} stereotyped << refine >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Refine} proxy or <i>null</i>.
     */
    @objid ("a41c62a0-a5ab-4afa-bb67-126d68a2fe6d")
    public static Refine instantiate(Dependency obj) {
        return Refine.canInstantiate(obj) ? new Refine(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Refine} proxy from a {@link Dependency} stereotyped << refine >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Refine} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("097f0f90-2d55-4341-803d-761dde120041")
    public static Refine safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Refine.canInstantiate(obj))
        	return new Refine(obj);
        else
        	throw new IllegalArgumentException("Refine: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("91e450e7-67b2-4fcc-a4f0-4fb810502c2c")
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
        Refine other = (Refine) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("8b190587-4b7a-44f1-9064-7ab38d0a91a9")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("3783d7c5-83b6-4f96-ab54-87f38153897e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b5e8dfff-4b53-4c74-873b-daf4f9ab5ea3")
    protected Refine(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6828706b-4056-48b1-9924-ed9cdf5d7d8d")
    public static final class MdaTypes {
        @objid ("35d6255e-451a-466d-be77-fe30b2b6ba34")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("450fa349-c58e-406d-b2ae-6b4d228e6c66")
        private static Stereotype MDAASSOCDEP;

        @objid ("49a4ae7f-b130-4374-9fe5-e59604526d4c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fed75518-73e8-40e6-878d-9065d776692d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-021f-0000-000000000000");
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
