/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << subsystem >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3f47f0f0-13fa-439b-be6a-0d13d837f14c")
public class Subsystem {
    @objid ("7b115923-1497-4a44-974f-ed30a31b5858")
    public static final String STEREOTYPE_NAME = "subsystem";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("81c91240-2b50-4990-87d8-9095340af16f")
    protected final Package elt;

    /**
     * Tells whether a {@link Subsystem proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << subsystem >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0ede5086-7842-48fd-ba97-6b9d4287a5d2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Subsystem.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << subsystem >> then instantiate a {@link Subsystem} proxy.
     * 
     * @return a {@link Subsystem} proxy on the created {@link Package}.
     */
    @objid ("2fcafa24-43e7-40d0-b1ac-7a3c8d6b12d1")
    public static Subsystem create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Subsystem.STEREOTYPE_NAME);
        return Subsystem.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Subsystem} proxy from a {@link Package} stereotyped << subsystem >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Subsystem} proxy or <i>null</i>.
     */
    @objid ("b2fd2b2c-fe42-494f-b3a9-453b3383d440")
    public static Subsystem instantiate(Package obj) {
        return Subsystem.canInstantiate(obj) ? new Subsystem(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Subsystem} proxy from a {@link Package} stereotyped << subsystem >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Subsystem} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2663c164-18ed-4b77-8894-fe1a7aff5797")
    public static Subsystem safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Subsystem.canInstantiate(obj))
        	return new Subsystem(obj);
        else
        	throw new IllegalArgumentException("Subsystem: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c0144320-583b-401c-9df1-21baf018ad89")
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
        Subsystem other = (Subsystem) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("dbdd0d2c-3eef-43c8-999b-0e7ea2705933")
    public Package getElement() {
        return this.elt;
    }

    @objid ("d66bb7d0-2632-4fea-b1f9-8524e310c8e3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("40dd6620-d9ee-4614-bb0d-64a3fc2e2d79")
    protected Subsystem(Package elt) {
        this.elt = elt;
    }

    @objid ("ce7bd25b-0a68-466f-8b35-0426dcef43a4")
    public static final class MdaTypes {
        @objid ("53d7917c-cfa8-421f-8d95-7a1c48e43b8e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ee2ec74a-b695-4bdd-b6b0-ba55a8ac7a5a")
        private static Stereotype MDAASSOCDEP;

        @objid ("445848d8-a9fd-4731-8850-fcfba56bc0b4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("08e669d8-dac6-4286-a396-bb0b1b39d16e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d3-0000-000000000000");
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
